package com.openclassrooms.safetynet.repository;

import com.openclassrooms.safetynet.model.JsonDataConverter;
import com.openclassrooms.safetynet.model.MedicalRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MedicalRecordRepository {
    JsonDataConverter jsonDataConverter;

    @Autowired
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
}
