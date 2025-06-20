package com.openclassrooms.safetynet.controller;

import com.openclassrooms.safetynet.service.PersonService;
import com.openclassrooms.safetynet.service.SearchService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedHashSet;

@Slf4j
@RestController
@RequiredArgsConstructor
public class SearchController {

    private final SearchService searchService;

    @RequestMapping(path = "/communityEmail", method = RequestMethod.GET)
    public ResponseEntity<Object> getEmailsByCity(@RequestParam(required = false) String city){
        LinkedHashSet<String> emails = searchService.getEmailsByCity(city);
        if(emails != null){
            log.info(" {} is showed! : ",city);
            return new ResponseEntity<>(emails, HttpStatus.ACCEPTED);
        }else {
            log.error("Failed to show the city: {} ", city);
            return new ResponseEntity<>(emails, HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(path="/phoneAlert", method = RequestMethod.GET)
    public ResponseEntity<Object> getAllPhoneNumbers(@RequestParam(required = false) int fireStationNumber){
        LinkedHashSet<String> phones = searchService.getAllPhoneNumbers(fireStationNumber);
        if(phones != null){
            log.info(" FireStation {} :", fireStationNumber + " is showed");
            return new ResponseEntity<>(phones, HttpStatus.OK);
        }else {
            log.error("FireStation {} :", fireStationNumber + " is not showed");
            return new ResponseEntity<>(phones, HttpStatus.NOT_FOUND);
        }
    }
}
