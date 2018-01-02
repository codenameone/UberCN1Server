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
    
    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @return the passenger
     */
    public User getPassenger() {
        return passenger;
    }

    /**
     * @param passenger the passenger to set
     */
    public void setPassenger(User passenger) {
        this.passenger = passenger;
    }

    /**
     * @return the driver
     */
    public User getDriver() {
        return driver;
    }

    /**
     * @param driver the driver to set
     */
    public void setDriver(User driver) {
        this.driver = driver;
    }

    /**
     * @return the route
     */
    public Set<Waypoint> getRoute() {
        return route;
    }

    /**
     * @param route the route to set
     */
    public void setRoute(Set<Waypoint> route) {
        this.route = route;
    }

    /**
     * @return the cost
     */
    public BigDecimal getCost() {
        return cost;
    }

    /**
     * @param cost the cost to set
     */
    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    /**
     * @return the currency
     */
    public String getCurrency() {
        return currency;
    }

    /**
     * @param currency the currency to set
     */
    public void setCurrency(String currency) {
        this.currency = currency;
    }
    
    
}
