package com.openclassrooms.safetynet.dto;

import java.util.List;

public class FloodStations {
    private List<MedicalRecordDto> medicalRecordList;
    public String address;

    public FloodStations(){

    }

    public FloodStations(List<MedicalRecordDto> medicalRecordList, String address) {
        this.medicalRecordList = medicalRecordList;
        this.address = address;
    }

    public List<MedicalRecordDto> getMedicalRecordList() {
        return medicalRecordList;
    }

    public void setMedicalRecordList(List<MedicalRecordDto> medicalRecordList) {
        this.medicalRecordList = medicalRecordList;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
