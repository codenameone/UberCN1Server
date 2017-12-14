package com.codename1.uberclone.entities;

import org.springframework.data.repository.CrudRepository;

/**
 * Crud repo interface for entity
 *
 * @author Shai Almog
 */
public interface RideRepository  extends CrudRepository<Ride, Long> { 
}
