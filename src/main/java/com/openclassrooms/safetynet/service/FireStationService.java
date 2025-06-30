package com.openclassrooms.safetynet.service;

import com.openclassrooms.safetynet.dto.ChildDto;
import com.openclassrooms.safetynet.dto.FireStationNumber;
import com.openclassrooms.safetynet.dto.PersonDto;
import com.openclassrooms.safetynet.model.FireStation;
import com.openclassrooms.safetynet.model.MedicalRecord;
import com.openclassrooms.safetynet.model.Person;
import com.openclassrooms.safetynet.repository.FireStationRepository;
import com.openclassrooms.safetynet.repository.MedicalRecordRepository;
import com.openclassrooms.safetynet.repository.PersonRepository;
import com.openclassrooms.safetynet.repository.SearchRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RequiredArgsConstructor
@Service
public class FireStationService {
    private final FireStationRepository fireStationRepository;
    private final PersonRepository personRepository;
    private final MedicalRecordService medicalRecordService;

    public List<FireStation> getAllFireStations(){
        return fireStationRepository.getAllFireStation();
    }

    public Boolean updateFireStation(String address, FireStation fireStation){
        return fireStationRepository.updateFireStation(address, fireStation);
    }

    public Boolean deleteByAddress(String address) {
        return fireStationRepository.deleteByAddress(address);
    }

    public Boolean deleteByStation(String station) {
        return fireStationRepository.deleteByStation(station);
    }

    public Boolean saveStation(FireStation fireStation){
        return fireStationRepository.savingStation(fireStation);
    }

    public FireStationNumber getFireStationByStationNumber(int stationNumber){
        FireStationNumber fireStationNumber = new FireStationNumber();
        Set<FireStation> fireStations = fireStationRepository.getStationsByNumber(stationNumber);
        Set<String> addresses = fireStationRepository.findAllAddresses(fireStations);
        Set<PersonDto> personDtos = new HashSet<>();
        int adult = 0;
        int child = 0;

        for(String address : addresses){
            List<Person> persons = personRepository.findPersonByAddress(address);
            for(Person personA : persons){
                String firstName = personA.getFirstName();
                String lastName = personA.getLastName();
                int age = medicalRecordService.getAgeByFirstNameAndLastName(firstName, lastName);
                if(age <= 18){
                    child += 1;
                } else {
                    adult += 1;
                }
                personDtos.add(new PersonDto(firstName,lastName, address, personA.getPhone()));
            }
        }
        if(personDtos.isEmpty())  return null;
        fireStationNumber.setAdult(adult);
        fireStationNumber.setChild(child);
        fireStationNumber.setListPerson(personDtos);
        return fireStationNumber;
    }
}
