package com.blogspot.ranganathankm.user.jwt.controller;

import com.blogspot.ranganathankm.user.jwt.model.SignupUser;
import com.blogspot.ranganathankm.user.jwt.model.AppRole;
import com.blogspot.ranganathankm.user.jwt.model.AppUser;
import com.blogspot.ranganathankm.user.jwt.service.UserCreationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author ranga
 */
@RestController
@RequestMapping("signup")
public class SignupController {
    
    @Autowired
    private UserCreationService userCreationService;
    
    @PostMapping
    public void signupNewUser(@RequestBody SignupUser signUpUser) {
        userCreationService.addUser(signUpUser.getLoginName(), signUpUser.getPassword(), AppRole.USER);
    }
}
