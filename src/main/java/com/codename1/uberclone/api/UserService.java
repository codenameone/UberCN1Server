package com.codename1.uberclone.api;

import com.codename1.uberclone.dao.UserDAO;
import com.codename1.uberclone.entities.User;
import com.codename1.uberclone.entities.UserRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * API's related to user access
 *
 * @author Shai Almog
 */
@Service
public class UserService {
    @Autowired
    private UserRepository users;

    @Autowired
    private PasswordEncoder encoder;
    
    public String addUser(UserDAO user) {
        User u = new User(user);
        u.setPassword(encoder.encode(user.getPassword()));
        users.save(u);
        return u.getAuthToken();
    }

    public byte[] getAvatar(Long id) {
        User u = users.findOne(id);
        return u.getAvatar();
    }
    
    public void setAvatar(String token, byte[] a) {
        User u = users.findByAuthToken(token).get(0);
        u.setAvatar(a);
        users.save(u);
    }
    
    public void updateUser(UserDAO user) {
        User u = users.findByAuthToken(user.getAuthToken()).get(0);
        u.setCar(user.getCar());
        u.setEmail(user.getEmail());
        u.setFacebookId(user.getFacebookId());
        u.setGivenName(user.getGivenName());
        u.setSurname(user.getSurname());
        u.setGoogleId(user.getGoogleId());
        u.setLatitude(user.getLatitude());
        u.setLongitude(user.getLongitude());
        u.setPhone(user.getPhone());
        users.save(u);
    }
            
    public UserDAO loginByPhone(String phone, String password) throws UserAuthenticationException {
        return loginImpl(users.findByPhone(phone), password);
    }

    public UserDAO loginByFacebook(String facebookId, String password) throws UserAuthenticationException {
        return loginImpl(users.findByFacebookId(facebookId), password);
    }
    
    public UserDAO loginByGoogle(String googleId, String password) throws UserAuthenticationException {
        return loginImpl(users.findByGoogleId(googleId), password);
    }

    private UserDAO loginImpl(List<User> us, String password) throws UserAuthenticationException {
        if(us == null || us.isEmpty()) {
            return null;
        }
        if(us.size() > 1) {
            throw new RuntimeException("Illegal state " + us.size() + " users with the same phone are listed!");
        }
        User u = us.get(0);
        if(!encoder.matches(password, u.getPassword())) {
            throw new UserAuthenticationException();
        }
        UserDAO d = u.getDao();
        d.setAuthToken(u.getAuthToken());
        return d;
    }
    
    public boolean existsByPhone(String phone) {
        List<User> us = users.findByPhone(phone);
        return !us.isEmpty();
    }
}
