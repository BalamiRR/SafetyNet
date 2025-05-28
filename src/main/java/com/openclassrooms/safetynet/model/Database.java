package com.openclassrooms.safetynet.model;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Component
public class Database {

        private List<Person> persons = new ArrayList<>();
        private List<FireStation> firestations = new ArrayList<>();
        private List<MedicalRecord> medicalrecords = new ArrayList<>();
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
                this.firestations = allInfo.getFirestations();
                this.medicalrecords = allInfo.getMedicalrecords();
                System.out.println("Yerel JSON yüklendi: " + persons.size() + " kişi");

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    public List<Person> getPersons() {
        return persons;
    }

    public List<FireStation> getFirestations() {
        return firestations;
    }

    public List<MedicalRecord> getMedicalrecords() {
        return medicalrecords;
    }

    public void setPersons(List<Person> persons) {
        this.persons = persons;
    }

    public void setFirestations(List<FireStation> firestations) {
        this.firestations = firestations;
    }

    public void setMedicalrecords(List<MedicalRecord> medicalrecords) {
        this.medicalrecords = medicalrecords;
    }
}

