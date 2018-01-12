package com.codename1.uberclone.api;

import com.codename1.uberclone.dao.UserDAO;
import com.codename1.uberclone.entities.Ride;
import com.codename1.uberclone.entities.RideRepository;
import com.codename1.uberclone.entities.User;
import com.codename1.uberclone.entities.UserRepository;
import com.codename1.uberclone.entities.Waypoint;
import com.codename1.uberclone.entities.WaypointRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Location based API's that update the user location and 
 *
 * @author Shai Almog
 */
@Service
public class LocationService {
    @Autowired
    private UserRepository users;

    @Autowired
    private RideRepository rides; 
    
    @Autowired
    private WaypointRepository waypoints; 
    
    public void updateUserLocation(String token, double lat, double lon, float dir) {
        List<User> us = users.findByAuthToken(token);
        User u = us.get(0);
        u.setLatitude(lat);
        u.setLongitude(lat);
        u.setDirection(dir);
        users.save(u);
        if(u.isDriver() && u.getAssignedUser() != null) {
            List<Ride> r = rides.findByNotFinishedUser(u.getId());
            if(r != null && !r.isEmpty()) {
                Ride ride = r.get(0);
                if(ride.isStarted() && !ride.isFinished()) {
                    Set<Waypoint> route = ride.getRoute();
                    Waypoint newPosition = new Waypoint(System.currentTimeMillis(), lat, lon, dir);
                    waypoints.save(newPosition);
                    route.add(newPosition);
                    ride.setRoute(route);
                    rides.save(ride);
                }
            }
        }
    }
    
    public List<UserDAO> findAllDrivers(double lat, double lon, double radius) {
        double minLat = lat - radius * 0.009044;
        double minLon = lon - radius * 0.0089831;
        double maxLat = lat + radius * 0.009044;
        double maxLon = lon + radius * 0.0089831;
        return toDaoList(users.findByDriver(minLat, maxLat, minLon, maxLon));
    }

    public List<UserDAO> findAvailableDrivers(double lat, double lon, double radius) {
        double minLat = lat - radius * 0.009044;
        double minLon = lon - radius * 0.0089831;
        double maxLat = lat + radius * 0.009044;
        double maxLon = lon + radius * 0.0089831;
        return toDaoList(users.findByAvailableDriver(minLat, maxLat, minLon, maxLon));
    }
    
    private List<UserDAO> toDaoList(List<User> us) {
        ArrayList<UserDAO> respone = new ArrayList<>();
        for(User u : us) {
            respone.add(u.getPartialDao());
        }
        return respone;
    }
}
