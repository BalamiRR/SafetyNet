package com.openclassrooms.safetynet.controller;

import com.openclassrooms.safetynet.model.FireStation;
import com.openclassrooms.safetynet.service.FireStationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class FireStationController {
    FireStationService fireStationService;
    Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    public FireStationController(FireStationService fireStationService){
        this.fireStationService = fireStationService;
    }

    @GetMapping("/firestation")
    public List<FireStation> getAllFireStationService(){
        return fireStationService.getAllFireStations();
    }

    @DeleteMapping(path = "/firestation")
    public ResponseEntity<Boolean> deleteStation(@RequestBody FireStation fireStation){
        boolean bool = fireStationService.deleteStation(fireStation);
        if(bool){
            logger.info("FireStation " + fireStation + "is deleted !");
            return new ResponseEntity<>(true, HttpStatus.ACCEPTED);
        } else{
            logger.error("FireStation " + fireStation + "is failed to delete it !");
            return new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);
        }
    }
}
