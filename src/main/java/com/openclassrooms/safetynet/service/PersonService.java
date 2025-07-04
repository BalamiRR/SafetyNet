package com.openclassrooms.safetynet.service;

import com.openclassrooms.safetynet.model.Person;
import com.openclassrooms.safetynet.repository.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class PersonService {
    private final PersonRepository personRepository;

    public List<Person> getAllPersons() {
        return personRepository.getAllPersons();
    }

    public Boolean savePerson(Person person){
        return personRepository.savePerson(person);
    }

    public Boolean deletePerson(String firstName, String lastName){
        return personRepository.deletePerson(firstName, lastName);
    }

    public Boolean updatePerson(String firstName, String lastName, Person person){
        return personRepository.updatePerson(firstName, lastName, person);
    }


}
