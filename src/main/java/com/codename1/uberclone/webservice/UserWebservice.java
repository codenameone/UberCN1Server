package com.codename1.uberclone.webservice;

import com.codename1.uberclone.api.UserAuthenticationException;
import com.codename1.uberclone.api.UserService;
import com.codename1.uberclone.dao.ErrorDAO;
import com.codename1.uberclone.dao.UserDAO;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Maps the user API's to webservice
 *
 * @author Shai Almog
 */
@Controller
@RequestMapping("/user")
public class UserWebservice {
    @Autowired
    private UserService users;

    @ExceptionHandler(UserAuthenticationException.class)
    @ResponseStatus(value = HttpStatus.FORBIDDEN)
    public @ResponseBody ErrorDAO handleException(UserAuthenticationException e) {
        return new ErrorDAO("Invalid Password", ErrorDAO.ERROR_INVALID_PASSWORD);
    }    

    @RequestMapping(method=RequestMethod.GET,value = "/exists")
    public @ResponseBody boolean exists(String phone) {
        return users.existsByPhone(phone);
    }
    
    @RequestMapping(method=RequestMethod.GET,value = "/login")
    public @ResponseBody UserDAO login(@RequestParam(value="password", required=true) String password,
            String phone, String googleId, String facebookId) throws UserAuthenticationException {
        if(phone != null) {
            return users.loginByPhone(phone, password);
        }
        if(facebookId != null) {
            return users.loginByFacebook(facebookId, password);
        }
        if(googleId != null) {
            return users.loginByGoogle(googleId, password);
        }
        return null;
    }
    
    @RequestMapping(method = RequestMethod.POST,value = "/add")
    public @ResponseBody String addEditUser(@RequestBody UserDAO ud) throws IOException {
        if(ud.getId() != null) {
            users.updateUser(ud);
            return ud.getId().toString();
        } else {
            return users.addUser(ud);
        }
    }
}
