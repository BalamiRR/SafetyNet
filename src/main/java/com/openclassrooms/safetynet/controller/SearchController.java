package com.openclassrooms.safetynet.controller;

import com.openclassrooms.safetynet.dto.ChildAlertDto;
import com.openclassrooms.safetynet.dto.FloodStations;
import com.openclassrooms.safetynet.dto.PersonInfoLastName;
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
import java.util.List;
import java.util.Set;

@Slf4j
@RestController
@RequiredArgsConstructor
public class SearchController {

    private final SearchService searchService;

    @RequestMapping(path="/communityEmail", method = RequestMethod.GET)
    public ResponseEntity<Set<String>> getEmailsByCity(@RequestParam(required = false) String city){
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
    public ResponseEntity<Set<String>> getAllPhoneNumbers(@RequestParam(required = false) int fireStationNumber){
        LinkedHashSet<String> phones = searchService.getAllPhoneNumbers(fireStationNumber);
        if(phones != null){
            log.info(" FireStation {} :", fireStationNumber + " is showed");
            return new ResponseEntity<>(phones, HttpStatus.OK);
        }else {
            log.error("FireStation {} :", fireStationNumber + " is not showed");
            return new ResponseEntity<>(phones, HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(path="/childAlert", method = RequestMethod.GET)
    public ResponseEntity<ChildAlertDto> findChildByAddress(@RequestParam String address){
        ChildAlertDto child = searchService.findChildByAddress(address);
        if(child != null){
            log.info("Get children alert by address: {}", address);
            return new ResponseEntity<>(child, HttpStatus.OK);
        }else {
            log.error("Error getting children alert by address {} :", address);
            return new ResponseEntity<>(child, HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(path = "/personInfolastName", method = RequestMethod.GET)
    public ResponseEntity<List<PersonInfoLastName>> getPersonsByLastName(@RequestParam String lastName) {
        List<PersonInfoLastName> persons = searchService.getAllPersonsByLastName(lastName);

        if (persons != null) {
            log.info("Found {} person(s) with lastName: {}", persons.size(), lastName);
            return new ResponseEntity<>(persons, HttpStatus.OK);
        } else {
            log.error("No persons found with lastName: {}", lastName);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(path="/flood/stations", method = RequestMethod.GET)
    public ResponseEntity<List<FloodStations>> getFloodData(@RequestParam List<Integer> stations) {
        List<FloodStations> result = searchService.getFloodDataByStations(stations);

        if (result == null || result.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
