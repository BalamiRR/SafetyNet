package com.openclassrooms.safetynet.controller;

import com.openclassrooms.safetynet.model.MedicalRecord;
import com.openclassrooms.safetynet.service.MedicalRecordService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("medicalRecord")
public class MedicalRecordController {
    private final MedicalRecordService medicalRecordService;

    public MedicalRecordController(MedicalRecordService medicalRecordService){
        this.medicalRecordService = medicalRecordService;
    }

    @GetMapping
    public List<MedicalRecord> getAllMedicalRecord(){
        return medicalRecordService.getAllMedicalRecord();
    }

    @PostMapping
    public ResponseEntity<Boolean> saveMedicalRecord(@RequestBody MedicalRecord medicalRecord){
        Boolean bool = medicalRecordService.saveMedicalRecord(medicalRecord);
        if(bool){
            log.info("The new MedicalRecord {} ", medicalRecord + "is added !");
            return new ResponseEntity<>(true, HttpStatus.CREATED);
        }
        log.error("The MedicalRecord {} ", medicalRecord + "is failed to add it !");
        return new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);
    }

}
