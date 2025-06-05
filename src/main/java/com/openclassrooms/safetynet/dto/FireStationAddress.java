package com.openclassrooms.safetynet.dto;

import java.util.List;

public class FireStationAddress {
    private List<MedicalRecordDto> medicalRecordList;
    private List<Integer> stationNumber;

    public FireStationAddress(){

    }

    public FireStationAddress(List<MedicalRecordDto> medicalRecordList, List<Integer> stationNumber) {
        this.medicalRecordList = medicalRecordList;
        this.stationNumber = stationNumber;
    }

    public List<MedicalRecordDto> getMedicalRecordList() {
        return medicalRecordList;
    }

    public void setMedicalRecordList(List<MedicalRecordDto> medicalRecordList) {
        this.medicalRecordList = medicalRecordList;
    }

    public List<Integer> getStationNumber() {
        return stationNumber;
    }

    public void setStationNumber(List<Integer> stationNumber) {
        this.stationNumber = stationNumber;
    }
}
