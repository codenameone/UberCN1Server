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
}
