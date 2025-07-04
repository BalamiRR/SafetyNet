package com.openclassrooms.safetynet.service;

import com.openclassrooms.safetynet.dto.*;
import com.openclassrooms.safetynet.model.FireStation;
import com.openclassrooms.safetynet.model.MedicalRecord;
import com.openclassrooms.safetynet.model.Person;
import com.openclassrooms.safetynet.repository.FireStationRepository;
import com.openclassrooms.safetynet.repository.MedicalRecordRepository;
import com.openclassrooms.safetynet.repository.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@RequiredArgsConstructor
@Service
public class SearchService {
    private final PersonRepository personRepository;
    private final FireStationRepository fireStationRepository;
    private final MedicalRecordService medicalRecordService;
    private final MedicalRecordRepository medicalRecordRepository;

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

    public ChildAlertDto findChildByAddress(String address){
        List<ChildDto> childDtos = new ArrayList<>();
        List<Person> persons = personRepository.findPersonByAddress(address);
        List<Person> family = new ArrayList<>();

        for(Person person : persons){
            String firstName = person.getFirstName();
            String lastName = person.getLastName();
            int age = medicalRecordService.getAgeByFirstNameAndLastName(firstName, lastName);

            if(age <= 18){
                childDtos.add(new ChildDto(firstName, lastName, age));
            }
            if(person.getLastName().equals(lastName) && age > 18){
                family.add(person);
            }
        }
        ChildAlertDto childAlert = new ChildAlertDto();
        childAlert.setChildren(childDtos);
        childAlert.setFamilyMembers(family);
        return childAlert;
    }

    public List<PersonInfoLastName> getAllPersonsByLastName(String lastName) {
        List<PersonInfoLastName> resultList = new ArrayList<>();
        List<Person> persons = personRepository.getAllPersons();
        for (Person person : persons) {
            if (person.getLastName().equalsIgnoreCase(lastName)) {
                MedicalRecord record = medicalRecordRepository.findMedicalRecordByName(person.getFirstName(), person.getLastName());

                if (record != null) {
                    PersonInfoLastName info = new PersonInfoLastName();
                    info.setFirstName(person.getFirstName());
                    info.setLastName(person.getLastName());
                    info.setAddress(person.getAddress());
                    info.setEmail(person.getEmail());
                    info.setAge(record.getAge());
                    info.setMedications(record.getMedications());
                    info.setAllergies(record.getAllergies());

                    resultList.add(info);
                }
            }
        }
        return resultList;
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
                    MedicalRecord m = medicalRecordRepository.findMedicalRecordByName(p.getFirstName(), p.getLastName());
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

    public FireStationAddress getResidenceOfAddress(String address){
        FireStationAddress fireStationAddress = new FireStationAddress();
        List<FireStation> fireStations = fireStationRepository.findStationByAddress(address);
        List<Integer> numbers = new ArrayList<>();
        for(FireStation fireStation : fireStations){
            numbers.add(fireStation.getStation());
        }

        List<PersonRecord> personRecords = getPersonRecord(address);
        fireStationAddress.setStationNumber(numbers);
        fireStationAddress.setMedicalRecordList(personRecords);
        return fireStationAddress;

    }

    private List<PersonRecord> getPersonRecord(String address){
        List<PersonRecord> personRecords = new ArrayList<>();
        List<Person> persons = personRepository.findPersonByAddress(address);
        for (Person person : persons){
            String firstName = person.getFirstName();
            String lastName = person.getLastName();
            String phone = person.getPhone();
            MedicalRecord medicalRecord = medicalRecordService.getMedicalRecordByFirstNameAndLastName(firstName, lastName);
            if(medicalRecord != null){
                int age = medicalRecord.getAge();
                List<String> medications = medicalRecord.getMedications();
                List<String> allergies = medicalRecord.getAllergies();
                personRecords.add(new PersonRecord(firstName, lastName, phone, age, medications, allergies));
            }else{
                personRecords.add(new PersonRecord(firstName, lastName, phone));
            }
        }
        return personRecords;
    }

}