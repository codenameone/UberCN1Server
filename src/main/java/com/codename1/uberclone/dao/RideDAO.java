package com.codename1.uberclone.dao;

import java.io.Serializable;

/**
 *
 * @author Shai Almog
 */
public class RideDAO implements Serializable {
    private long userId;
    private String name;
    private String from;
    private String destination;

    public RideDAO() {
    }

    public RideDAO(long userId, String name, String from, String destination) {
        this.userId = userId;
        this.name = name;
        this.from = from;
        this.destination = destination;
    }
    
    /**
     * @return the userId
     */
    public long getUserId() {
        return userId;
    }

    /**
     * @param userId the userId to set
     */
    public void setUserId(long userId) {
        this.userId = userId;
    }

    /**
     * @return the from
     */
    public String getFrom() {
        return from;
    }

    /**
     * @param from the from to set
     */
    public void setFrom(String from) {
        this.from = from;
    }

    /**
     * @return the destination
     */
    public String getDestination() {
        return destination;
    }

    /**
     * @param destination the destination to set
     */
    public void setDestination(String destination) {
        this.destination = destination;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }
}
