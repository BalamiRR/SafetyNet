package com.openclassrooms.safetynet.service;

import com.openclassrooms.safetynet.dto.*;
import com.openclassrooms.safetynet.model.FireStation;
import com.openclassrooms.safetynet.model.MedicalRecord;
import com.openclassrooms.safetynet.model.Person;
import com.openclassrooms.safetynet.repository.FireStationRepository;
import com.openclassrooms.safetynet.repository.MedicalRecordRepository;
import com.openclassrooms.safetynet.repository.PersonRepository;
import com.openclassrooms.safetynet.repository.SearchRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@RequiredArgsConstructor
@Service
public class SearchService {
    private final SearchRepository searchRepository;
    private final PersonRepository personRepository;
    private final FireStationRepository fireStationRepository;
    private final MedicalRecordRepository medicalRecordRepository;

    public LinkedHashSet<String> getEmailsByCity(String city){
        return searchRepository.getEmailsByCity(city);
    }

    public LinkedHashSet<String> getAllPhoneNumbers(int fireStationNumber){
        return searchRepository.getAllPhoneNumbers(fireStationNumber);
    }

    public ChildAlertDto findChildByAddress(String address){
        ChildAlertDto childAlert = new ChildAlertDto();
        List<ChildDto> childDtos = new ArrayList<>();
        List<Person> persons = personRepository.findPersonByAddress(address);
        List<Person> family = new ArrayList<>();

        for(Person person : persons){
            String firstName = person.getFirstName();
            String lastName = person.getLastName();
            int age = searchRepository.getAgeByName(firstName, lastName);

            if(age <= 18){
                childDtos.add(new ChildDto(firstName, lastName, age));
            }
            if(person.getLastName().equals(lastName) && age > 18){
                family.add(person);
            }
        }
        if(childDtos.isEmpty()) return null;

        childAlert.setChildren(childDtos);
        childAlert.setFamilyMembers(family);
        return childAlert;
    }

    public List<PersonInfoLastName> getAllPersonsByLastName(String lastName) {
        List<PersonInfoLastName> resultList = new ArrayList<>();
        List<Person> persons = personRepository.getAllPersons();
        for (Person person : persons) {
            if (person.getLastName().equalsIgnoreCase(lastName)) {
                MedicalRecord record = searchRepository.findMedicalRecordByName(person.getFirstName(), person.getLastName());

                if (record != null) {
                    PersonInfoLastName info = new PersonInfoLastName();
                    info.setFirstName(person.getFirstName());
                    info.setLastName(person.getLastName());
                    info.setAddress(person.getAddress());
                    info.setEmail(person.getEmail());
                    info.setAge(searchRepository.getAge(record.getBirthdate()));
                    info.setMedications(record.getMedications());
                    info.setAllergies(record.getAllergies());

                    resultList.add(info);
                }
            }
        }
        return resultList.isEmpty() ? null : resultList;
    }

    public List<FloodStations> getFloodDataByStations(List<Integer> stationNumbers) {
        Set<String> addresses = new HashSet<>();
        for (FireStation fs : fireStationRepository.getAllFireStation()) {
            if (stationNumbers.contains(fs.getStation())) {
                addresses.add(fs.getAddress());
            }
        }

        List<FloodStations> result = new ArrayList<>();
        for (String address : addresses) {
            List<MedicalRecordDto> residents = new ArrayList<>();

            for (Person p : personRepository.getAllPersons()) {
                if (p.getAddress().equals(address)) {
                    MedicalRecord m = searchRepository.findMedicalRecordByName(p.getFirstName(), p.getLastName());

                    if (m != null) {
                        residents.add(new MedicalRecordDto(
                                p.getFirstName(),
                                p.getLastName(),
                                p.getPhone(),
                                m.getBirthdate(),
                                m.getMedications(),
                                m.getAllergies()
                        ));
                    }
                }
            }
            if (!residents.isEmpty()) {
                FloodStations info = new FloodStations();
                info.setAddress(address);
                info.setMedicalRecordList(residents);
                result.add(info);
            }
        }
        return result;
    }
}