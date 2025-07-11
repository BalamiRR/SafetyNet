package com.openclassrooms.safetynet.controller;

import com.openclassrooms.safetynet.model.MedicalRecord;
import com.openclassrooms.safetynet.service.MedicalRecordService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@Slf4j
@RestController
@RequestMapping("medicalRecord")
public class MedicalRecordController {
    private final MedicalRecordService medicalRecordService;

    @GetMapping
    public List<MedicalRecord> getAllMedicalRecord(){
        return medicalRecordService.getAllMedicalRecord();
    }

    @PostMapping
    public ResponseEntity<Boolean> saveMedicalRecord(@RequestBody MedicalRecord medicalRecord){
        Boolean success = medicalRecordService.saveMedicalRecord(medicalRecord);
        if(success){
            log.info("The new MedicalRecord {} ", medicalRecord + "is added !");
            return new ResponseEntity<>(true, HttpStatus.CREATED);
        }
        log.error("The MedicalRecord {} ", medicalRecord + "is failed to add it !");
        return new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);
    }

    @PutMapping("/{firstName}/{lastName}")
    public ResponseEntity<Boolean> updateMedicalRecord(@PathVariable String firstName,
                                                       @PathVariable String lastName,
                                                       @RequestBody MedicalRecord medicalRecord){
        Boolean success = medicalRecordService.updateMedicalRecord(firstName, lastName, medicalRecord);
        if(success){
            log.info("The medicalRecord {} {} {} ", firstName, lastName, medicalRecord + "is updated !");
            return new ResponseEntity<>(true, HttpStatus.ACCEPTED);
        }else {
            log.error("Failed to update : {} {} ", firstName, lastName);
            return new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{firstName}/{lastName}")
    public ResponseEntity<Boolean> deleteMedicalRecord(@PathVariable String firstName,
                                                       @PathVariable String lastName){
        Boolean success = medicalRecordService.deleteMedicalRecord(firstName, lastName);
        if(success){
            log.info("The medicalRecord {} {} is deleted!", firstName, lastName);
            return new ResponseEntity<>(true, HttpStatus.ACCEPTED);
        }else {
            log.error("Failed to delete: {} {}", firstName, lastName);
            return new ResponseEntity<>(false, HttpStatus.BAD_GATEWAY);
        }
    }

}
