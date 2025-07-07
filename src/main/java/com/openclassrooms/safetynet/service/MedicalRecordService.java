package com.openclassrooms.safetynet.service;

import com.openclassrooms.safetynet.model.MedicalRecord;
import com.openclassrooms.safetynet.repository.MedicalRecordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class MedicalRecordService {
    private final MedicalRecordRepository medicalRecordRepository;

    public List<MedicalRecord> getAllMedicalRecord(){
        return medicalRecordRepository.getAllMedicalRecord();
    }

    public Boolean saveMedicalRecord(MedicalRecord medicalRecord){
        return medicalRecordRepository.saveMedicalRecord(medicalRecord);
    }

    public Boolean updateMedicalRecord(String firstName, String lastName, MedicalRecord medicalRecord){
        return medicalRecordRepository.updateMedicalRecord(firstName, lastName, medicalRecord);
    }

    public Boolean deleteMedicalRecord(String firstName, String lastName){
        return medicalRecordRepository.deleteMedicalRecord(firstName, lastName);
    }

    public int getAgeByFirstNameAndLastName(String firstName, String lastName){
        return medicalRecordRepository.getAllMedicalRecord()
                .stream()
                .filter(m -> m.getFirstName().equalsIgnoreCase(firstName) && m.getLastName().equalsIgnoreCase(lastName))
                .findFirst()
                .map(MedicalRecord::getAge)
                .orElse(0);
    }

    public MedicalRecord getMedicalRecordByFirstNameAndLastName(String firstName, String lastName){
        return medicalRecordRepository.findMedicalRecordByName(firstName, lastName);
    }


}
