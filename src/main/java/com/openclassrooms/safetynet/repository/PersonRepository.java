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

    /**
     * If a person does not exist yet, by checking the unique firstName and lastName, then add it.
     * @param person A Person Object.
     * @return true if success, or false if failed.
     */
    public Boolean savePerson(Person person){
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

    /**
     * Deletes a person from the list by matching firstName and lastName.
     *
     * @param firstName The first name of the person to delete.
     * @param lastName The last name of the person to delete.
     * @return true if the person was found and removed; false otherwise.
     */
    public Boolean deletePerson(String firstName, String lastName){
        for( Person personA : this.getAllPersons()){
            if(personA.getFirstName().equals(firstName) && personA.getLastName().equals(lastName)){
                return this.getAllPersons().remove(personA);
            }
        }
    return false;
    }

    /**
     * Updates the details of an existing person identified by firstName and lastName.
     * If a matching person is found in the list, their address, city, zip, phone, and email will be updated.
     *
     * @param firstName The first name of the person to update.
     * @param lastName The last name of the person to update.
     * @param person A Person object containing the updated details.
     * @return true if the person was found and updated; false otherwise.
     */
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