package com.openclassrooms.safetynet.service;

import com.openclassrooms.safetynet.model.Person;
import com.openclassrooms.safetynet.repository.PersonRepository;
import org.springframework.stereotype.Service;

import java.util.LinkedHashSet;
import java.util.List;

@Service
public class PersonService {
    private final PersonRepository personRepository;

    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public List<Person> getAllPersons() {
        return personRepository.getAllPersons();
    }

    public Boolean savePerson(Person person){
        return personRepository.savingPerson(person);
    }

    public Boolean deletePerson(String firstName, String lastName){
        return personRepository.deletePerson(firstName, lastName);
    }

    public Boolean updatePerson(String firstName, String lastName, Person person){
        return personRepository.updatePerson(firstName, lastName, person);
    }

    public LinkedHashSet<String> getEmailsByCity(String city){
        return personRepository.getEmailsByCity(city);
    }

    public LinkedHashSet<String> getAllPhoneNumbers(int fireStationNumber){
        return personRepository.getAllPhoneNumbers(fireStationNumber);
    }

}
