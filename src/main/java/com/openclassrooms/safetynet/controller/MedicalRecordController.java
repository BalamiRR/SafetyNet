package com.openclassrooms.safetynet.controller;

import com.openclassrooms.safetynet.model.MedicalRecord;
import com.openclassrooms.safetynet.service.MedicalRecordService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MedicalRecordController {
    MedicalRecordService medicalRecordService;
    Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    public MedicalRecordController(MedicalRecordService medicalRecordService){
        this.medicalRecordService = medicalRecordService;
    }

    @GetMapping("/medicalRecord")
    public List<MedicalRecord> getAllMedicalRecord(){
        return medicalRecordService.getAllMedicalRecord();
    }

    @PostMapping(path = "/medicalRecords")
    public ResponseEntity<Boolean> saveMedicalRecord(@RequestBody MedicalRecord medicalRecord){
        Boolean bool = medicalRecordService.saveMedicalRecord(medicalRecord);
        if(bool){
            logger.info("The new MedicalRecord " + medicalRecord + "is added !");
            return new ResponseEntity<>(true, HttpStatus.CREATED);
        }
        logger.error("The MedicalRecord " + medicalRecord + "is failed to add it !");
        return new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);
    }

}
