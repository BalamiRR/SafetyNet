package com.openclassrooms.safetynet.controller;

import com.openclassrooms.safetynet.model.FireStation;
import com.openclassrooms.safetynet.service.FireStationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping(path = "/firestations")
    public ResponseEntity<Boolean> saveStation(@RequestBody FireStation fireStation){
        boolean bool = fireStationService.saveStation(fireStation);
        if(bool){
            logger.info("The new FireStation " + fireStation + "is added !");
            return new ResponseEntity<>(true, HttpStatus.ACCEPTED);
        } else{
            logger.error("FireStation " + fireStation + "is failed to add it !");
            return new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping(path = "/firestations")
    public ResponseEntity<Boolean> updateStation(@RequestBody FireStation fireStation){
        boolean bool = fireStationService.updateFireStation(fireStation);
        if(bool){
            logger.info("The FireStation " + fireStation + "is updated !");
            return new ResponseEntity<>(true, HttpStatus.ACCEPTED);
        } else{
            logger.error("The FireStation " + fireStation + "is failed to update it !");
            return new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping(path = "/firestations")
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
