package com.codename1.uberclone.entities;

import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

/**
 * Crud repo interface for entity
 *
 * @author Shai Almog
 */
public interface RideRepository  extends CrudRepository<Ride, Long> { 
    @Query("select b from Ride b where b.finished = false and b.driver.id = ?1")
    public List<Ride> findByNotFinishedUser(long id);
}
