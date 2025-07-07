package com.openclassrooms.safetynet.service;

import com.openclassrooms.safetynet.dto.FireStationNumber;
import com.openclassrooms.safetynet.dto.PersonDto;
import com.openclassrooms.safetynet.model.FireStation;
import com.openclassrooms.safetynet.model.Person;
import com.openclassrooms.safetynet.repository.FireStationRepository;
import com.openclassrooms.safetynet.repository.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
        Set<FireStation> stations = fireStationRepository.getStationsByNumber(stationNumber);
        Set<String> addresses = fireStationRepository.findAllAddresses(stations);
        return buildFireStationNumber(addresses);
    }

    private FireStationNumber buildFireStationNumber(Set<String> addresses) {
        Set<PersonDto> personDtos = new HashSet<>();
        int adult  = 0;
        int child  = 0;
        for (String address : addresses) {
            for (Person p : personRepository.findPersonByAddress(address)) {
                int age = medicalRecordService.getAgeByFirstNameAndLastName(
                        p.getFirstName(), p.getLastName());

                if (age <= 18) child++; else adult++;

                personDtos.add(new PersonDto(
                        p.getFirstName(),
                        p.getLastName(),
                        address,
                        p.getPhone()));
            }
        }
        if (personDtos.isEmpty()) {
            return null;
        }
        FireStationNumber dto = new FireStationNumber();
        dto.setAdult(adult);
        dto.setChild(child);
        dto.setListPerson(personDtos);
        return dto;
    }



}
