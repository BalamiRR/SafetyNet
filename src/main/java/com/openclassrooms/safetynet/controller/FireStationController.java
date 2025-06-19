package com.openclassrooms.safetynet.controller;

import com.openclassrooms.safetynet.model.FireStation;
import com.openclassrooms.safetynet.service.FireStationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/fireStation")
public class FireStationController {
    private final FireStationService fireStationService;

    public FireStationController(FireStationService fireStationService){
        this.fireStationService = fireStationService;
    }

    @GetMapping
    public List<FireStation> getAllFireStationService(){
        return fireStationService.getAllFireStations();
    }

    @PostMapping
    public ResponseEntity<Boolean> saveStation(@RequestBody FireStation fireStation){
        boolean success = fireStationService.saveStation(fireStation);
        if(success){
            log.info("The new FireStation {} ", fireStation + "is added !");
            return new ResponseEntity<>(true, HttpStatus.CREATED);
        } else{
            log.error("FireStation {} ",  fireStation +"is failed to add it !");
            return new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping
    public ResponseEntity<Boolean> updateStation(@RequestBody FireStation fireStation){
        boolean success = fireStationService.updateFireStation(fireStation.getAddress(), fireStation);
        if(success){
            log.info("The FireStation {} ", fireStation + "is updated !");
            return new ResponseEntity<>(true, HttpStatus.ACCEPTED);
        } else{
            log.error("The FireStation {} ", fireStation + "is failed to update it !");
            return new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping
    public ResponseEntity<Boolean> deleteStation(@RequestParam(required = false) String address,
                                                 @RequestParam(required = false) String station){
        boolean success = false;
        if (address != null) {
            success = fireStationService.deleteByAddress(address);
            log.info("Delete by address [{}] result: {}", address, success);
        } else if (station != null) {
            success = fireStationService.deleteByStation(station);
            log.info("Delete by station [{}] result: {}", station, success);
        }

        if (success) {
            return new ResponseEntity<>(true, HttpStatus.ACCEPTED);
        }else {
            log.error("Delete failed for address: {} or station: {}", address, station);
            return new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);
        }
    }

}