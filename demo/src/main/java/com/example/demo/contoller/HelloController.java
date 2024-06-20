package com.example.demo.contoller;


import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    @GetMapping("/hello")
    @Operation(summary = "hello test", description = "no params required", method = "GET")
    public ResponseEntity<String> hello(){
        return new ResponseEntity<>("hello test", HttpStatus.OK);
    }
}
