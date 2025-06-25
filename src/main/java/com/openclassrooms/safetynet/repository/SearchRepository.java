package com.openclassrooms.safetynet.repository;

import com.openclassrooms.safetynet.dto.PersonInfoLastName;
import com.openclassrooms.safetynet.model.FireStation;
import com.openclassrooms.safetynet.model.JsonDataConverter;
import com.openclassrooms.safetynet.model.MedicalRecord;
import com.openclassrooms.safetynet.model.Person;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.List;

@RequiredArgsConstructor
@Repository
public class SearchRepository {
    private final FireStationRepository fireStationRepository;
    private final PersonRepository personRepository;
    private final MedicalRecordRepository medicalRecordRepository;

    public LinkedHashSet<String> getAllPhoneNumbers(int fireStationNumber){
        LinkedHashSet<String> phones = new LinkedHashSet<>();
        List<String> addresses = new ArrayList<>();

        for(FireStation fireStation : fireStationRepository.getAllFireStation()){
            if(fireStation.getStation() == fireStationNumber){
                addresses.add(fireStation.getAddress());
            }
        }
        if(addresses.isEmpty()) return null;
        for(String address : addresses){
            for(Person personA : personRepository.getAllPersons()){
                if(personA.getAddress().equals(address)){
                    phones.add(personA.getPhone());
                }
            }
        }
        return phones;
    }

    public LinkedHashSet<String> getEmailsByCity(String city){
        LinkedHashSet<String> set = new LinkedHashSet<>();
        for(Person personA : personRepository.getAllPersons()){
            if(personA.getCity().equals(city)){
                set.add(personA.getEmail());
            }
        }
        if(set.isEmpty()) return null;
        return set;
    }

    public MedicalRecord findMedicalRecordByName(String name, String lastName){
        for(MedicalRecord medicalRecord : medicalRecordRepository.getAllMedicalRecord()){
            if(medicalRecord.getFirstName().equals(name) && medicalRecord.getLastName().equals(lastName)){
                return medicalRecord;
            }
        }
        return null;
    }

    public int getAge(Date birthDate){
        LocalDate birthday = birthDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        Date date = new Date();
        LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        return Period.between(birthday, localDate).getYears();
    }

    public int getAgeByName(String name, String lastName){
        MedicalRecord medicalRecordByName = this.findMedicalRecordByName(name, lastName);
        Date birthDate = medicalRecordByName.getBirthdate();
        return this.getAge(birthDate);
    }

}