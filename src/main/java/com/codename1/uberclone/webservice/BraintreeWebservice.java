package com.codename1.uberclone.webservice;

import com.codename1.uberclone.api.BraintreeService;
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
@RequestMapping("/pay")
public class BraintreeWebservice {
    @Autowired
    private BraintreeService payment;

    @RequestMapping(method=RequestMethod.GET,value = "/token")
    public @ResponseBody String getClientToken(long id) {
        return payment.getClientToken();
    }
    
    @RequestMapping(method=RequestMethod.GET,value="/nonce") 
    public @ResponseBody String nonce(@RequestParam(name="ride", required = true) long rideId, 
                @RequestParam(name="nonce", required = true) String nonce) {
        payment.saveNonce(rideId, nonce);
        return "OK";
    }
}
