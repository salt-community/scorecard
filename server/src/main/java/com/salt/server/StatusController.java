package com.salt.server;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StatusController {

    @GetMapping("/status")
    public ResponseEntity<String> statusOk() {
        return new ResponseEntity<>("Server is live! 200 OK. version 0.4.3",HttpStatus.OK);
    }
}
