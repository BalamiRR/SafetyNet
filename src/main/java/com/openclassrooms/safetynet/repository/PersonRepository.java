package com.openclassrooms.safetynet.repository;

import com.openclassrooms.safetynet.model.Database;
import com.openclassrooms.safetynet.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class PersonRepository {
    Database database;

    @Autowired

    public PersonRepository(Database database) {
        this.database = database;
    }

    public List<Person> getAllPersons(){
        return database.getPersons();
    }



}
