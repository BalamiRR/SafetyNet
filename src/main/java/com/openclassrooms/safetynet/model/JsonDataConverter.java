package com.openclassrooms.safetynet.model;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Component
public class JsonDataConverter {

        private List<Person> persons = new ArrayList<>();
        private List<FireStation> fireStations = new ArrayList<>();
        private List<MedicalRecord> medicalRecord = new ArrayList<>();
        private static final ObjectMapper mapper = new ObjectMapper();

        @PostConstruct
        public void init() {
            try {
                InputStream is = getClass().getClassLoader().getResourceAsStream("data.json");
                if (is == null) {
                    throw new RuntimeException("data.json not found in resources!");
                }

                JsonDataContainer allInfo = mapper.readValue(is, JsonDataContainer.class);

                this.persons = allInfo.getPersons();
                this.fireStations = allInfo.getFirestations();
                this.medicalRecord = allInfo.getMedicalrecords();
                System.out.println("JSON uploaded : " + persons.size() + " person");

            } catch (IOException e) {
                e.printStackTrace();
            }
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
}

