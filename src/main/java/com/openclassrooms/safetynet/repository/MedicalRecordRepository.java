package com.openclassrooms.safetynet.repository;

import com.openclassrooms.safetynet.model.JsonDataConverter;
import com.openclassrooms.safetynet.model.MedicalRecord;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@RequiredArgsConstructor
@Repository
public class MedicalRecordRepository {
    private final JsonDataConverter jsonDataConverter;

    public List<MedicalRecord> getAllMedicalRecord(){
        return jsonDataConverter.getMedicalRecord();
    }

    /**
     * Saves a new medical record if a record with the same first and last name doesn't already exist.
     * @param medicalRecord The medical record to be saved.
     * @return true if the record was added; false if it already exists or input is invalid.
     */
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

    /**
     * Updates an existing medical record identified by first name and last name.
     *
     * @param firstName The first name of the person whose record will be updated.
     * @param lastName The last name of the person whose record will be updated.
     * @param medicalRecord The medical record containing updated information.
     * @return true if the record was found and updated; false otherwise.
     */
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
    /**
     * Deletes a medical record by matching first name and last name.
     *
     * @param firstName The first name of the medical record to delete.
     * @param lastName The last name of the medical record to delete.
     * @return true if a record was found and deleted; false otherwise.
     */
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

    public MedicalRecord findMedicalRecordByName(String name, String lastName){
        for(MedicalRecord medicalRecord : this.getAllMedicalRecord()){
            if(medicalRecord.getFirstName().equals(name) && medicalRecord.getLastName().equals(lastName)){
                return medicalRecord;
            }
        }
        return null;
    }
}
