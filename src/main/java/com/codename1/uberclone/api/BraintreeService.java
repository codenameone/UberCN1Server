package com.codename1.uberclone.api;

import com.braintreegateway.BraintreeGateway;
import com.braintreegateway.Environment;
import com.braintreegateway.TransactionRequest;
import com.codename1.uberclone.entities.Ride;
import com.codename1.uberclone.entities.RideRepository;
import java.math.BigDecimal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Shai Almog
 */
@Service
public class BraintreeService {
    private final static BraintreeGateway gateway = new BraintreeGateway(
      Environment.SANDBOX,
      "your_merchant_id",
      "your_public_key",
      "your_private_key"
    );
    
    @Autowired
    private RideRepository rides;

    public String getClientToken() {
        return gateway.clientToken().generate();
    }
    
    public void saveNonce(long rideId, String nonce) {
        Ride r = rides.findOne(rideId);
        r.setNonce(nonce);
        rides.save(r);
    } 
    
    public void pay(BigDecimal amount, String nonce) {
        TransactionRequest requestT = new TransactionRequest()
            .amount(amount)
            .paymentMethodNonce(nonce)
            .options()
              .submitForSettlement(true)
              .done();
    }
}
