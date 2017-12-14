package com.codename1.uberclone.entities;

import java.math.BigDecimal;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;

/**
 * Logs historic rides between users
 * 
 * @author Shai Almog
 */
@Entity
public class Ride {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    @ManyToOne
    private User passenger;
    
    @ManyToOne
    private User driver;
    
    @OneToMany
    @OrderBy("time ASC")
    private Set<Waypoint> route;
    
    private BigDecimal cost;
    
    private String currency;

    public Ride() {        
    }
    
    
}
