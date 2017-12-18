package com.codename1.uberclone.websocket;

import com.codename1.uberclone.api.LocationService;
import com.codename1.uberclone.dao.UserDAO;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.socket.BinaryMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.BinaryWebSocketHandler;

public class Handler extends BinaryWebSocketHandler {    
    public static final short MESSAGE_TYPE_LOCATION_UPDATE = 1;
    public static final short MESSAGE_TYPE_DRIVER_POSITIONS = 2;
    public static final short MESSAGE_TYPE_AVAILBLE_DRIVER_POSITIONS = 3;
    
    @Autowired
    private LocationService loc;

    @Override
    protected void handleBinaryMessage(WebSocketSession session, BinaryMessage message) throws Exception {
        ByteBuffer b = message.getPayload();
        
        short messageType = b.getShort();
        short stringLength = b.getShort();
        StringBuilder bld = new StringBuilder();
        for(int iter = 0 ; iter < stringLength ; iter++) {
            // we can use bytes for encoding since the token has ascii characters only
            bld.append((char)b.get());
        }
        String token = bld.toString();
        switch(messageType) {
            case MESSAGE_TYPE_LOCATION_UPDATE: 
                double lat = b.getDouble();
                double lon = b.getDouble();
                float dir = b.getFloat();
                double radius = b.getDouble();
                boolean seeking = b.get() == 1;
                loc.updateUserLocation(token, lat, lon, dir);
                List<UserDAO> response;
                short responseType;
                if(seeking) {
                    response = loc.findAllDrivers(lat, lon, radius);
                    responseType = MESSAGE_TYPE_DRIVER_POSITIONS;
                } else {
                    response = loc.findAvailableDrivers(lat, lon, radius);
                    responseType = MESSAGE_TYPE_AVAILBLE_DRIVER_POSITIONS;
                }
                if(response != null && response.size() > 0) {
                    try(ByteArrayOutputStream bos = new ByteArrayOutputStream();
                            DataOutputStream dos = new DataOutputStream(bos)) {
                        dos.writeShort(responseType);
                        dos.writeInt(response.size());
                        for(UserDAO u : response) {
                            dos.writeLong(u.getId());
                            dos.writeDouble(u.getLatitude());
                            dos.writeDouble(u.getLongitude());
                            dos.writeFloat(u.getDirection());
                        }
                        dos.flush();
                        BinaryMessage bin = new BinaryMessage(bos.toByteArray());
                        session.sendMessage(bin);
                    } 
                }
                break;
        }
    }
    
}
