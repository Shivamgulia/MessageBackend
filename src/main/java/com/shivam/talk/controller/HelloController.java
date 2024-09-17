package com.shivam.talk.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class HelloController {


    @GetMapping("/hello")
    public ResponseEntity<String> HelloWorld() {
        return ResponseEntity.ok("Hello World");
    }


}
