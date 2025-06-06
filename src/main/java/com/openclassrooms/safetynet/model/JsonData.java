package com.openclassrooms.safetynet.model;

import java.util.ArrayList;
import java.util.List;

public class JsonData {
    public static List<Person> person = new ArrayList<>();
    public static List<FireStation> firestation = new ArrayList<>();
    public static List<MedicalRecord> medicalrecord = new ArrayList<>();

    public static List<Person> getPerson() {
        return person;
    }

    public static void setPerson(List<Person> person) {
        JsonData.person = person;
    }

    public static List<FireStation> getFirestation() {
        return firestation;
    }

    public static void setFirestation(List<FireStation> firestation) {
        JsonData.firestation = firestation;
    }

    public static List<MedicalRecord> getMedicalrecord() {
        return medicalrecord;
    }

    public static void setMedicalrecord(List<MedicalRecord> medicalrecord) {
        JsonData.medicalrecord = medicalrecord;
    }
}
