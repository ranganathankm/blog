package com.blogspot.ranganathankm.user.jwt.controller;

import com.blogspot.ranganathankm.user.jwt.model.AppAuth;
import javax.annotation.security.RolesAllowed;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author ranga
 */
@RestController
@RequestMapping("general")
public class GeneralController {
    @PreAuthorize(AppAuth.HAS_AUTHORITY_ADMIN)
    @GetMapping("info")
    public ResponseEntity<String> test() {
        return ResponseEntity.ok("general info");
    }

}
