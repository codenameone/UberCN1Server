package com.codename1.uberclone.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 * Allows rating a user/driver 
 *
 * @author Shai Almog
 */
@Entity
public class Rating {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    private float score;
    private String comment;
    
    @ManyToOne
    private User passenger;
    
    @ManyToOne
    private User driver;

    
}
