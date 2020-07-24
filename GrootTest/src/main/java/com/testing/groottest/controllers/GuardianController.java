package com.testing.groottest.controllers;

import com.testing.groottest.models.ErrorMessage;
import com.testing.groottest.models.Translation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GuardianController {

    @GetMapping("/groot")
    public ResponseEntity<?> groot(@RequestParam (required = false) String message){
        if(message == null){
            return new ResponseEntity<ErrorMessage>(new ErrorMessage("I am Groot"), HttpStatus.BAD_REQUEST);
        }
        else{
            return new ResponseEntity<Translation>(new Translation(message), HttpStatus.OK);
        }
    }
}
