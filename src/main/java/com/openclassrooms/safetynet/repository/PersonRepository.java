package com.openclassrooms.safetynet.repository;

import com.openclassrooms.safetynet.model.JsonDataConverter;
import com.openclassrooms.safetynet.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class PersonRepository {
    JsonDataConverter database;

    @Autowired

    public PersonRepository(JsonDataConverter database) {
        this.database = database;
    }

    public List<Person> getAllPersons(){
        return database.getPersons();
    }



}
