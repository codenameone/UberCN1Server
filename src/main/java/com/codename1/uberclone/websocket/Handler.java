package com.codename1.uberclone.websocket;

import com.codename1.uberclone.api.LocationService;
import com.codename1.uberclone.api.RideService;
import com.codename1.uberclone.api.UserService;
import com.codename1.uberclone.dao.UserDAO;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.nio.ByteBuffer;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.socket.BinaryMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.BinaryWebSocketHandler;

public class Handler extends BinaryWebSocketHandler {    
    private static final short MESSAGE_TYPE_LOCATION_UPDATE = 1;
    private static final short MESSAGE_TYPE_DRIVER_POSITIONS = 2;
    private static final short MESSAGE_TYPE_AVAILBLE_DRIVER_POSITIONS = 3;
    private static final short MESSAGE_TYPE_DRIVER_FOUND = 4;

    private static final short HAILING_OFF = 0;
    private static final short HAILING_ON = 1;
    private static final short HAILING_TURN_OFF = 2;
    
    @Autowired
    private LocationService loc;
    
    @Autowired
    private RideService rides;
    
    
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
                int seeking = b.get();
                loc.updateUserLocation(token, lat, lon, dir);
                List<UserDAO> response;
                UserDAO driver = null;
                short responseType;
                if(seeking == HAILING_ON) {
                    response = loc.findAvailableDrivers(lat, lon, radius);
                    responseType = MESSAGE_TYPE_DRIVER_POSITIONS;
                    
                    byte[] fromArray = new byte[b.getShort()];
                    b.get(fromArray);
                    byte[] toArray = new byte[b.getShort()];
                    b.get(toArray);
                    driver = rides.hailCar(token, true, new String(fromArray, "UTF-8"), new String(toArray, "UTF-8"));
                } else {
                    if(seeking == HAILING_TURN_OFF) {
                        rides.hailCar(token, false, null, null);
                    }
                    response = loc.findAllDrivers(lat, lon, radius);
                    responseType = MESSAGE_TYPE_AVAILBLE_DRIVER_POSITIONS;
                }
                if(driver != null) {
                    try(ByteArrayOutputStream bos = new ByteArrayOutputStream();
                            DataOutputStream dos = new DataOutputStream(bos)) {
                        dos.writeShort(MESSAGE_TYPE_DRIVER_FOUND);
                        dos.writeLong(driver.getId());
                        dos.writeUTF(notNull(driver.getCar()));
                        dos.writeUTF(notNull(driver.getGivenName()));
                        dos.writeUTF(notNull(driver.getSurname()));
                        dos.writeFloat(driver.getCurrentRating());
                        dos.writeLong(driver.getCurrentRide());
                        dos.flush();
                        BinaryMessage bin = new BinaryMessage(bos.toByteArray());
                        session.sendMessage(bin);
                    } 
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
                            dos.writeUTF(notNull(u.getPushToken(), ""));
                        }
                        dos.flush();
                        BinaryMessage bin = new BinaryMessage(bos.toByteArray());
                        session.sendMessage(bin);
                    } 
                }
                break;
        }
    }

    private String notNull(String val, String defaultVal) {
        if(val == null) {
            return defaultVal;
        }
        return val;
    }
    

    private String notNull(String val) {
        return notNull(val, "[unknown]");
    }
}
