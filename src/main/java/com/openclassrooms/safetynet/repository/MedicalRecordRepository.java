package com.openclassrooms.safetynet.repository;

import com.openclassrooms.safetynet.model.JsonDataConverter;
import com.openclassrooms.safetynet.model.MedicalRecord;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MedicalRecordRepository {
    private final JsonDataConverter jsonDataConverter;

    public MedicalRecordRepository(JsonDataConverter jsonDataConverter){
        this.jsonDataConverter = jsonDataConverter;
    }

    public List<MedicalRecord> getAllMedicalRecord(){
        return jsonDataConverter.getMedicalRecord();
    }

    public Boolean saveMedicalRecord(MedicalRecord medicalRecord){
        boolean foundMedicalRecord = false;
        if(medicalRecord.getFirstName() == null || medicalRecord.getLastName() == null){
            return false;
        }
        for(MedicalRecord medicalRecordA : this.getAllMedicalRecord()){
            if(medicalRecord.getFirstName().equals(medicalRecordA.getFirstName()) && medicalRecord.getLastName().equals(medicalRecordA.getLastName())){
                foundMedicalRecord = true;
                break;
            }
        }
        if(!foundMedicalRecord){
            this.getAllMedicalRecord().add(medicalRecord);
        }
        return !foundMedicalRecord;
    }

    public Boolean updateMedicalRecord(String firstName, String lastName, MedicalRecord medicalRecord){
        for(MedicalRecord medicalRecordA : this.getAllMedicalRecord()){
            if(medicalRecordA.getFirstName().equals(firstName) && medicalRecordA.getLastName().equals(lastName)){
                medicalRecordA.setBirthdate(medicalRecord.getBirthdate());
                medicalRecordA.setMedications(medicalRecord.getMedications());
                medicalRecordA.setAllergies(medicalRecord.getAllergies());
                return true;
            }
        }
        return false;
    }

    public Boolean deleteMedicalRecord(String firstName, String lastName){
        if(firstName == null && lastName == null){
            return false;
        }
        for(MedicalRecord medicalRecordA : this.getAllMedicalRecord()){
            if(medicalRecordA.getFirstName().equals(firstName) && medicalRecordA.getLastName().equals(lastName)){
                return this.getAllMedicalRecord().remove(medicalRecordA);
            }
        }
        return false;
    }
}
