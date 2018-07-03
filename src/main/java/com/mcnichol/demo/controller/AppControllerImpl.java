package com.mcnichol.demo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
class AppControllerImpl implements AppController {

    @GetMapping("/hello")
    public ResponseEntity<String> hello(){
        return new ResponseEntity<>("World", HttpStatus.OK);
    }

    @GetMapping("/password/hello")
    public ResponseEntity<String> passwordHello(){
        return new ResponseEntity<>("Protected World", HttpStatus.OK);
    }
}
