package com.openclassrooms.safetynet.model;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Component
public class JsonDataConverter implements CommandLineRunner {

    private List<Person> persons = new ArrayList<>();
    private List<FireStation> fireStations = new ArrayList<>();
    private List<MedicalRecord> medicalRecord = new ArrayList<>();
    private static final ObjectMapper mapper = new ObjectMapper();
    private static JsonDataContainer jsonDataContainer;

    public static JsonDataContainer getData(){
            return jsonDataContainer;
    }

    public static void setData(JsonDataContainer jsonDataContainer){
        JsonDataConverter.jsonDataContainer = jsonDataContainer;
    }

    public List<Person> getPersons() {
        return persons;
    }

    public List<FireStation> getFireStations() {
        return fireStations;
    }

    public void setPersons(List<Person> persons) {
        this.persons = persons;
    }

    public void setFireStations(List<FireStation> fireStations) {
        this.fireStations = fireStations;
    }

    public List<MedicalRecord> getMedicalRecord() {
        return medicalRecord;
    }

    public void setMedicalRecord(List<MedicalRecord> medicalRecord) {
        this.medicalRecord = medicalRecord;
    }

    @Override
    public void run(String... args) throws Exception {
        try {
            InputStream is = getClass().getClassLoader().getResourceAsStream("data.json");
            if (is == null) {
                throw new RuntimeException("data.json not found in resources!");
            }
            JsonDataContainer allInfo = mapper.readValue(is, JsonDataContainer.class);
            JsonData.setPerson(allInfo.getPersons());
            JsonData.setFirestation(allInfo.getFirestations());
            JsonData.setMedicalrecord(allInfo.getMedicalrecords());
            this.persons = allInfo.getPersons();
            this.fireStations = allInfo.getFirestations();
            this.medicalRecord = allInfo.getMedicalrecords();
            System.out.println("JSON uploaded : " + allInfo.getPersons().size() + " person");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


