package com.openclassrooms.safetynet.repository;

import com.openclassrooms.safetynet.model.JsonDataConverter;
import com.openclassrooms.safetynet.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PersonRepository {
    JsonDataConverter data;

    @Autowired

    public PersonRepository(JsonDataConverter data) {
        this.data = data;
    }

    public List<Person> getAllPersons(){
        return data.getPersons();
    }

    public Boolean savingPerson(Person person){
        boolean personFound = false;
        if(person.getFirstName() == null || person.getLastName() == null){
            return false;
        }
        for( Person personA : this.getAllPersons()){
            if(personA.getFirstName().equals(person.getFirstName()) && personA.getLastName().equals(person.getLastName())){
                personFound = true;
                break;
            }
        }
        if(!personFound){
            this.getAllPersons().add(person);
        }
        return !personFound;
    }

    public Boolean deletePerson(Person person){
        if(person.getFirstName() != null && person.getLastName() != null){
            for( Person personA : this.getAllPersons()){
                if(personA.getFirstName().equals(person.getFirstName()) && personA.getLastName().equals(person.getLastName())){
                    return this.getAllPersons().remove(personA);
                }
            }
        }
        return false;
    }

    public Boolean updatePerson(Person person){
        if(person.getFirstName() != null && person.getLastName() != null){
            for( Person personA : this.getAllPersons()){
                if(personA.getFirstName().equals(person.getFirstName()) && personA.getLastName().equals(person.getLastName())){
                    personA.setAddress(person.getAddress());
                    personA.setCity(person.getCity());
                    personA.setZip(person.getZip());
                    personA.setPhone(person.getPhone());
                    personA.setEmail(person.getEmail());
                    return true;
                }
            }
        }
        return false;
    }

}