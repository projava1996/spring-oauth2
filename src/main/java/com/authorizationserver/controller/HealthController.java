package com.authorizationserver.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/health")
public class HealthController {
    private static final Logger logger = LoggerFactory.getLogger(HealthController.class);

    @GetMapping("/ping")
    public ResponseEntity<Object> ping() {
        return ResponseEntity.status(HttpStatus.OK).body("pong");
    }
}
