package com.openclassrooms.safetynet.controller;

import com.openclassrooms.safetynet.model.Person;
import com.openclassrooms.safetynet.service.PersonService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashSet;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/person")
public class PersonController {
    private final PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping
    public List<Person> getAllPerson() {
        return personService.getAllPersons();
    }

    @PostMapping
    public ResponseEntity<Boolean> createPerson(@RequestBody Person person){
        boolean success = personService.savingPerson(person);
        if (success) {
            log.info("Person {} is created!", person);
            return new ResponseEntity<>(true, HttpStatus.CREATED);
        }else{
            log.error("Failed to create: {}", person);
            return new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);
        }

    }

    @DeleteMapping("/{firstName}/{lastName}")
    public ResponseEntity<Boolean> deletePerson(@PathVariable String firstName,
                                                @PathVariable String lastName){
        boolean success = personService.deletePerson(firstName, lastName);
        if (success) {
            log.info("Person {} {} is deleted!", firstName, lastName);
            return new ResponseEntity<>(true, HttpStatus.ACCEPTED);
        }else{
            log.error("Failed to delete: {} {}", firstName, lastName);
            return new ResponseEntity<>(false, HttpStatus.BAD_GATEWAY);
        }
    }

    @PutMapping("/{firstName}/{lastName}")
    public ResponseEntity<Boolean> updatePerson( @PathVariable String firstName,
                                                 @PathVariable String lastName,
                                                 @RequestBody Person person){
        boolean success = personService.updatePerson(firstName, lastName, person);
        if (success) {
            log.info("Person {} {} is updated! : {}", firstName, lastName, person);
            return new ResponseEntity<>(true, HttpStatus.ACCEPTED);
        }else {
            log.error("Failed to update: {} {}", firstName, lastName);
            return new ResponseEntity<>(false, HttpStatus.BAD_GATEWAY);
        }
    }

    @RequestMapping(path = "/communityEmail", method = RequestMethod.GET)
    public ResponseEntity<Object> getEmailsByCity(@RequestParam(required = false) String city){
        LinkedHashSet<String> emails = personService.getEmailsByCity(city);
        if(emails != null){
            log.info(" {} is showed! : ",city);
            return new ResponseEntity<>(emails, HttpStatus.ACCEPTED);
        }else {
            log.error("Failed to show the city: {} ", city);
            return new ResponseEntity<>(emails, HttpStatus.NOT_FOUND);
        }
    }

}