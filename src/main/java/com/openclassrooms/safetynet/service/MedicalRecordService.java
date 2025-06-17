package com.openclassrooms.safetynet.service;

import com.openclassrooms.safetynet.model.MedicalRecord;
import com.openclassrooms.safetynet.repository.MedicalRecordRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MedicalRecordService {
    private final MedicalRecordRepository medicalRecordRepository;

    public MedicalRecordService(MedicalRecordRepository medicalRecordRepository){
        this.medicalRecordRepository = medicalRecordRepository;
    }

    public List<MedicalRecord> getAllMedicalRecord(){
        return medicalRecordRepository.getAllMedicalRecord();
    }

    public Boolean saveMedicalRecord(MedicalRecord medicalRecord){
        return medicalRecordRepository.saveMedicalRecord(medicalRecord);
    }
}
