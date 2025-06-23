package com.openclassrooms.safetynet.repository;

import com.openclassrooms.safetynet.model.JsonDataConverter;
import com.openclassrooms.safetynet.model.Person;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Repository
public class PersonRepository {
    private final JsonDataConverter data;

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

    public Boolean deletePerson(String firstName, String lastName){
        for( Person personA : this.getAllPersons()){
            if(personA.getFirstName().equals(firstName) && personA.getLastName().equals(lastName)){
                return this.getAllPersons().remove(personA);
            }
        }
    return false;
    }

    public Boolean updatePerson(String firstName, String lastName, Person person){
        for( Person personA : this.getAllPersons()){
            if(personA.getFirstName().equalsIgnoreCase(firstName) && personA.getLastName().equalsIgnoreCase(lastName)){
                personA.setAddress(person.getAddress());
                personA.setCity(person.getCity());
                personA.setZip(person.getZip());
                personA.setPhone(person.getPhone());
                personA.setEmail(person.getEmail());
                return true;
            }
        }
    return false;
    }

    public List<Person> findPersonByAddress(String address){
        List<Person> persons = this.getAllPersons();
        List<Person> list = new ArrayList<>();
        for(Person person : persons){
            if(person.getAddress().equals(address)){
                list.add(person);
            }
        }
        return list;
    }

}