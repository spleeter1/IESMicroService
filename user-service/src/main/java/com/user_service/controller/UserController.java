package com.user_service.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    @GetMapping("/agent")
    public ResponseEntity<?> agentInfo (){
        try{
            return ResponseEntity.ok("this is agent info");
        }
        catch (Exception e){
            return ResponseEntity.status(201).body("error");
        }
    }
}
