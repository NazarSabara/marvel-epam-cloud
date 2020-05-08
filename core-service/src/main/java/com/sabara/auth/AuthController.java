package com.sabara.auth;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    @GetMapping(path = "/auth")
    public ResponseEntity<String> authenticate() {
        return ResponseEntity.ok("success");
    }
}
