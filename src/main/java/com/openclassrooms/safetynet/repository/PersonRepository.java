package com.openclassrooms.safetynet.repository;

import com.openclassrooms.safetynet.model.FireStation;
import com.openclassrooms.safetynet.model.JsonDataConverter;
import com.openclassrooms.safetynet.model.Person;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;

@Repository
public class PersonRepository {
    private final JsonDataConverter data;
    private final FireStationRepository fireStationRepository;

    public PersonRepository(JsonDataConverter data, FireStationRepository fireStationRepository) {
        this.data = data;
        this.fireStationRepository = fireStationRepository;
    }

    public List<Person> getAllPersons(){
        return data.getPersons();
    }

    public List<FireStation> getAllFireStations(){
        return fireStationRepository.getAllFireStation();
    }

    public LinkedHashSet<String> getAllPhoneNumbers(int fireStationNumber){
        LinkedHashSet<String> phones = new LinkedHashSet<>();
        List<String> addresses = new ArrayList<>();
        List<FireStation> stations = this.getAllFireStations();
        for(FireStation fireStation : stations){
            if(fireStation.getStation() == fireStationNumber){
                addresses.add(fireStation.getAddress());
            }
        }
        if(addresses.isEmpty()) return null;

        for(String address : addresses){
            for(Person personA : this.getAllPersons()){
                if(personA.getAddress().equals(address)){
                    phones.add(personA.getPhone());
                }
            }
        }
        return phones;
    }

    // http://localhost:8080/person/communityEmail?city=Culver
    public LinkedHashSet<String> getEmailsByCity(String city){
        LinkedHashSet<String> set = new LinkedHashSet<>();
        for(Person personA : this.getAllPersons()){
            if(personA.getCity().equals(city)){
                set.add(personA.getEmail());
            }
        }
        if(set.isEmpty()) return null;
        return set;
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


}