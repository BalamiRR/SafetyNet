package com.openclassrooms.safetynet.controller;

import com.openclassrooms.safetynet.model.Person;
import com.openclassrooms.safetynet.service.PersonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PersonController {

    @Autowired
    PersonService personService;
    Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping("/person")
    public List<Person> getAllPerson() {
        return personService.getAllPersons();
    }

    @PostMapping(path="/persons")
    public ResponseEntity<Boolean> createPerson(@RequestBody Person person){
        boolean bool = personService.savingPerson(person);
        if(bool){
            logger.info("Person " + person + "is created !");
            return new ResponseEntity<>(true, HttpStatus.CREATED);
        }
        logger.error("Failed to create :  " + person);
        return new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping(path="/persons")
    public ResponseEntity<Boolean> deletePerson(@RequestBody Person person){
        boolean bool = personService.deletePerson(person);
        if(bool){
            logger.info("Person " + person + "is deleted !");
            return new ResponseEntity<>(true, HttpStatus.ACCEPTED);
        }
        logger.error("Failed to delete : " + person);
        return new ResponseEntity<>(false, HttpStatus.BAD_GATEWAY);
    }

    @PutMapping(path="/persons")
    public ResponseEntity<Boolean> updatePerson(@RequestBody Person person){
        boolean bool = personService.updatePerson(person);
        if(bool){
            logger.info("Person " + person + "is updated !");
            return new ResponseEntity<>(true, HttpStatus.ACCEPTED);
        }
        logger.error("Failed to update : " + person);
        return new ResponseEntity<>(false, HttpStatus.BAD_GATEWAY);
    }

}
