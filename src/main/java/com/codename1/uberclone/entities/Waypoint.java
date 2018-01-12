package com.codename1.uberclone.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author Shai Almog
 */
@Entity
public class Waypoint {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    private long time;
    private double latitude;
    private double longitude;
    private float direction;

    public Waypoint() {
    }

    public Waypoint(long time, double latitude, double longitude, float direction) {
        this.time = time;
        this.latitude = latitude;
        this.longitude = longitude;
        this.direction = direction;
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
     * @return the time
     */
    public long getTime() {
        return time;
    }

    /**
     * @param time the time to set
     */
    public void setTime(long time) {
        this.time = time;
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
