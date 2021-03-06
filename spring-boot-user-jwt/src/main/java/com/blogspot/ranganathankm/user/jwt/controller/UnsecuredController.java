package com.blogspot.ranganathankm.user.jwt.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author ranga
 */
@RestController
@RequestMapping("unsecured")
public class UnsecuredController {
    @GetMapping("info")
    public ResponseEntity<String> test() {
        return ResponseEntity.ok("this might be unsecured");
    }
}
