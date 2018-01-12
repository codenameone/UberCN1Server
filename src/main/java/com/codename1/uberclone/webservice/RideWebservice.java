package com.codename1.uberclone.webservice;

import com.codename1.uberclone.api.RideService;
import com.codename1.uberclone.dao.RideDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Maps the ride API's to webservice
 *
 * @author Shai Almog
 */
@Controller
@RequestMapping("/ride")
public class RideWebservice {
    @Autowired
    private RideService rides;

    @RequestMapping(method=RequestMethod.GET,value = "/get")
    public @ResponseBody RideDAO getRideData(long id) {
        return rides.getRideData(id);
    }
    
    @RequestMapping(method=RequestMethod.GET,value="/accept") 
    public @ResponseBody String acceptRide(@RequestParam(name="token", required = true) String token, 
                @RequestParam(name="userId", required = true) long userId) {
        long val = rides.acceptRide(token, userId);
        return "" + val;
    }
    
    @RequestMapping(method=RequestMethod.POST,value="/start") 
    public @ResponseBody String startRide(@RequestParam(name="id", required = true) long rideId) {
        rides.startRide(rideId);
        return "OK";
    }
    
    
    @RequestMapping(method=RequestMethod.POST,value="/finish") 
    public @ResponseBody String finishRide(@RequestParam(name="id", required = true) long rideId) {
        rides.finishRide(rideId);
        return "OK";
    }
}
