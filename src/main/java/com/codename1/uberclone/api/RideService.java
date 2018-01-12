package com.codename1.uberclone.api;

import com.codename1.uberclone.dao.RideDAO;
import com.codename1.uberclone.dao.UserDAO;
import com.codename1.uberclone.entities.Ride;
import com.codename1.uberclone.entities.RideRepository;
import com.codename1.uberclone.entities.User;
import com.codename1.uberclone.entities.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Shai Almog
 */
@Service
public class RideService {
    @Autowired
    private UserRepository users;

    @Autowired
    private RideRepository rides;

    @Transactional
    public UserDAO hailCar(String token, boolean h, String from, String to) {
        User u = users.findByAuthToken(token).get(0);
        if(h) {
            if(u.getAssignedUser() != null) {
                long driverId = u.getAssignedUser();
                u.setAssignedUser(null);
                users.save(u);
                User driver = users.findOne(driverId);
                return driver.getPartialDao();
            }
        } else {
            u.setAssignedUser(null);
        }
        u.setHailing(h);
        u.setHailingFrom(from);
        u.setHailingTo(to);
        users.save(u);
        return null;
    }
    
    
    public RideDAO getRideData(long userId) {
        User u = users.findOne(userId);
        if(u == null) {
            return null;
        }
        return u.getRideDao();
    }
    
    @Transactional
    public long acceptRide(String token, long userId) {
        User driver = users.findByAuthToken(token).get(0);
        User passenger = users.findOne(userId);
        if(!passenger.isHailing()) {
            throw new RuntimeException("Not hailing");
        }
        passenger.setHailing(false);
        passenger.setAssignedUser(driver.getId());
        driver.setAssignedUser(userId);
        users.save(driver);
        users.save(passenger);
        Ride r = new Ride();
        r.setDriver(driver);
        r.setPassenger(passenger);
        rides.save(r);
        return r.getId();
    }
    
    public void startRide(long rideId) {
        Ride current = rides.findOne(rideId);
        current.setStarted(true);
        rides.save(current);
    }

    public void finishRide(long rideId) {
        Ride current = rides.findOne(rideId);
        current.setFinished(true);
        rides.save(current);
    }
}
