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

    public UserDAO hailCar(String token, boolean h, String from, String to) {
        User u = users.findByAuthToken(token).get(0);
        if(h) {
            if(u.getAssignedUser() != null) {
                u.setAssignedUser(null);
                users.save(u);
                User driver = users.findOne(u.getAssignedUser());
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
        return u.getRideDao();
    }
    
    @Transactional
    public long acceptRide(String token, long userId) {
        User driver = users.findByAuthToken(token).get(0);
        User passenger = users.findOne(userId);
        if(passenger.isHailing()) {
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
}
