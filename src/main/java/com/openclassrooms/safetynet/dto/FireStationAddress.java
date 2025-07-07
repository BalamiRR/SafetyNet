package com.openclassrooms.safetynet.dto;

import java.util.List;

public class FireStationAddress {
    private List<PersonRecord> medicalRecordList;
    private List<Integer> stationNumber;

    public FireStationAddress(){

    }

    public List<PersonRecord> getMedicalRecordList() {
        return medicalRecordList;
    }

    public void setMedicalRecordList(List<PersonRecord> medicalRecordList) {
        this.medicalRecordList = medicalRecordList;
    }

    public List<Integer> getStationNumber() {
        return stationNumber;
    }

    public void setStationNumber(List<Integer> stationNumber) {
        this.stationNumber = stationNumber;
    }
}
