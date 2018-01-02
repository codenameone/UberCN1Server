package com.codename1.uberclone.dao;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Objects;

/**
 *
 * @author Shai Almog
 */
public class UserDAO implements Serializable {
    private Long id;
    private String givenName;
    private String surname;
    private String phone;
    private String email;
    private String facebookId;
    private String googleId;
    private boolean driver;
    private String car;
    private float currentRating;
    private double latitude;
    private double longitude;
    private float direction;
    private String authToken;
    private String password;
    private String pushToken;

    public UserDAO() {
    }

    public UserDAO(Long id, String givenName, String surname, String phone, String email, String facebookId, String googleId, boolean driver, String car, float currentRating, double latitude, double longitude,  float direction, String pushToken) {
        this.id = id;
        this.givenName = givenName;
        this.surname = surname;
        this.phone = phone;
        this.email = email;
        this.facebookId = facebookId;
        this.googleId = googleId;
        this.driver = driver;
        this.car = car;
        this.currentRating = currentRating;
        this.direction = direction;
        this.latitude = latitude;
        this.longitude = longitude;
        this.pushToken = pushToken;
    }
    
    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
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

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 37 * hash + Objects.hashCode(this.id);
        hash = 37 * hash + Objects.hashCode(this.phone);
        hash = 37 * hash + Objects.hashCode(this.email);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final UserDAO other = (UserDAO) obj;
        if (this.driver != other.driver) {
            return false;
        }
        if (Float.floatToIntBits(this.currentRating) != Float.floatToIntBits(other.currentRating)) {
            return false;
        }
        if (Double.doubleToLongBits(this.latitude) != Double.doubleToLongBits(other.latitude)) {
            return false;
        }
        if (Double.doubleToLongBits(this.longitude) != Double.doubleToLongBits(other.longitude)) {
            return false;
        }
        if (!Objects.equals(this.givenName, other.givenName)) {
            return false;
        }
        if (!Objects.equals(this.surname, other.surname)) {
            return false;
        }
        if (!Objects.equals(this.phone, other.phone)) {
            return false;
        }
        if (!Objects.equals(this.email, other.email)) {
            return false;
        }
        if (!Objects.equals(this.facebookId, other.facebookId)) {
            return false;
        }
        if (!Objects.equals(this.googleId, other.googleId)) {
            return false;
        }
        if (!Objects.equals(this.car, other.car)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
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

    /**
     * @return the pushToken
     */
    public String getPushToken() {
        return pushToken;
    }

    /**
     * @param pushToken the pushToken to set
     */
    public void setPushToken(String pushToken) {
        this.pushToken = pushToken;
    }
}
