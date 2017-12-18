package com.codename1.uberclone.entities;

import com.codename1.uberclone.dao.UserDAO;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;

/**
 *
 * @author Shai Almog
 */
@Entity
public class User {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    private String givenName;
    private String surname;

    private String phone;
    private String email;
    private String password;

    private String facebookId;
    private String googleId;
    
    private boolean driver;
    private String car;
    
    private boolean hailing;
    private Long assignedUser;
    
    private float currentRating;

    private double latitude;
    private double longitude;
    private float direction;

    @Column(unique=true)
    private String authToken;
    
    @Lob
    private byte[] avatar;

    public User() {
        authToken = UUID.randomUUID().toString();
    }

    public User(UserDAO ud) {
        this(ud.getGivenName(), ud.getSurname(), ud.getPhone(), ud.getEmail(), null, ud.getFacebookId(), ud.getGoogleId(), ud.isDriver(), ud.getCar());
        this.latitude = ud.getLatitude();
        this.longitude = ud.getLongitude();
        this.direction = ud.getDirection();
        currentRating = ud.getCurrentRating();
    }
    
    public UserDAO getDao() {
        return new UserDAO(id, givenName, surname, phone, email, facebookId, googleId, driver, car, currentRating, latitude, longitude, direction);
    }

    public UserDAO getPartialDao() {
        return new UserDAO(id, givenName, surname, null, null, null, null, driver, car, currentRating, latitude, longitude, direction);
    }
    
    public User(String givenName, String surname, String phone, String email, String password, String facebookId, String googleId, boolean driver, String car) {
        this();
        this.givenName = givenName;
        this.surname = surname;
        this.phone = phone;
        this.email = email;
        this.password = password;
        this.facebookId = facebookId;
        this.googleId = googleId;
        this.driver = driver;
        this.car = car;
    }

    
    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @return the givenName
     */
    public String getGivenName() {
        return givenName;
    }

    /**
     * @param givenName the givenName to set
     */
    public void setGivenName(String givenName) {
        this.givenName = givenName;
    }

    /**
     * @return the surname
     */
    public String getSurname() {
        return surname;
    }

    /**
     * @param surname the surname to set
     */
    public void setSurname(String surname) {
        this.surname = surname;
    }

    /**
     * @return the phone
     */
    public String getPhone() {
        return phone;
    }

    /**
     * @param phone the phone to set
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the facebookId
     */
    public String getFacebookId() {
        return facebookId;
    }

    /**
     * @param facebookId the facebookId to set
     */
    public void setFacebookId(String facebookId) {
        this.facebookId = facebookId;
    }

    /**
     * @return the googleId
     */
    public String getGoogleId() {
        return googleId;
    }

    /**
     * @param googleId the googleId to set
     */
    public void setGoogleId(String googleId) {
        this.googleId = googleId;
    }

    /**
     * @return the driver
     */
    public boolean isDriver() {
        return driver;
    }

    /**
     * @param driver the driver to set
     */
    public void setDriver(boolean driver) {
        this.driver = driver;
    }

    /**
     * @return the car
     */
    public String getCar() {
        return car;
    }

    /**
     * @param car the car to set
     */
    public void setCar(String car) {
        this.car = car;
    }

    /**
     * @return the hailing
     */
    public boolean isHailing() {
        return hailing;
    }

    /**
     * @param hailing the hailing to set
     */
    public void setHailing(boolean hailing) {
        this.hailing = hailing;
    }

    /**
     * @return the assignedUser
     */
    public Long getAssignedUser() {
        return assignedUser;
    }

    /**
     * @param assignedUser the assignedUser to set
     */
    public void setAssignedUser(Long assignedUser) {
        this.assignedUser = assignedUser;
    }

    /**
     * @return the currentRating
     */
    public float getCurrentRating() {
        return currentRating;
    }

    /**
     * @param currentRating the currentRating to set
     */
    public void setCurrentRating(float currentRating) {
        this.currentRating = currentRating;
    }

    /**
     * @return the avatar
     */
    public byte[] getAvatar() {
        return avatar;
    }

    /**
     * @param avatar the avatar to set
     */
    public void setAvatar(byte[] avatar) {
        this.avatar = avatar;
    }

    /**
     * @return the latitude
     */
    public double getLatitude() {
        return latitude;
    }

    /**
     * @param latitude the latitude to set
     */
    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    /**
     * @return the longitude
     */
    public double getLongitude() {
        return longitude;
    }

    /**
     * @param longitude the longitude to set
     */
    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    /**
     * @return the authToken
     */
    public String getAuthToken() {
        return authToken;
    }

    /**
     * @param authToken the authToken to set
     */
    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }

    /**
     * @return the direction
     */
    public float getDirection() {
        return direction;
    }

    /**
     * @param direction the direction to set
     */
    public void setDirection(float direction) {
        this.direction = direction;
    }
    
    
}
