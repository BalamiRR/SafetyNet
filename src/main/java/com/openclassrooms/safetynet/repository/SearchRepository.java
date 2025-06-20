package com.openclassrooms.safetynet.repository;

import com.openclassrooms.safetynet.model.FireStation;
import com.openclassrooms.safetynet.model.JsonDataConverter;
import com.openclassrooms.safetynet.model.Person;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;

@RequiredArgsConstructor
@Repository
public class SearchRepository {
    private final JsonDataConverter data;
    private final FireStationRepository fireStationRepository;
    private  final PersonRepository personRepository;

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

    // http://localhost:8080/person/communityEmail?city=Culver
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
}
