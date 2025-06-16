package com.openclassrooms.safetynet.service;

import com.openclassrooms.safetynet.model.Person;
import com.openclassrooms.safetynet.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PersonService {

    PersonRepository personRepository;

    @Autowired
    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public List<Person> getAllPersons() {
        return personRepository.getAllPersons();
    }

    public Boolean savingPerson(Person person){
        return personRepository.savingPerson(person);
    }

    public Boolean deletePerson(String firstName, String lastName){
        return personRepository.deletePerson(firstName, lastName);
    }

    public Boolean updatePerson(String firstName, String lastName, Person person){
        return personRepository.updatePerson(firstName, lastName, person);
    }
}
