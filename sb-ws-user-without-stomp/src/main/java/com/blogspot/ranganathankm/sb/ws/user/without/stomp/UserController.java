package com.blogspot.ranganathankm.sb.ws.user.without.stomp;

import java.io.IOException;
import java.security.Principal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author ranga
 */
@RestController
@RequestMapping("user")
public class UserController {

    @GetMapping("getUser")
    public Principal getLoggedinUser(Principal user) {
        return user;
    }
}
