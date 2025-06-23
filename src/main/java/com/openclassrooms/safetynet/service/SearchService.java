package com.openclassrooms.safetynet.service;

import com.openclassrooms.safetynet.dto.ChildAlertDto;
import com.openclassrooms.safetynet.dto.ChildDto;
import com.openclassrooms.safetynet.model.Person;
import com.openclassrooms.safetynet.repository.PersonRepository;
import com.openclassrooms.safetynet.repository.SearchRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;

@RequiredArgsConstructor
@Service
public class SearchService {
    private final SearchRepository searchRepository;
    private final PersonRepository personRepository;

    public LinkedHashSet<String> getEmailsByCity(String city){
        return searchRepository.getEmailsByCity(city);
    }

    public LinkedHashSet<String> getAllPhoneNumbers(int fireStationNumber){
        return searchRepository.getAllPhoneNumbers(fireStationNumber);
    }

    public ChildAlertDto findChildByAddress(String address){
        ChildAlertDto childAlert = new ChildAlertDto();
        List<ChildDto> childDtos = new ArrayList<>();
        List<Person> persons = personRepository.findPersonByAddress(address);
        List<Person> family = new ArrayList<>();

        for(Person person : persons){
            String firstName = person.getFirstName();
            String lastName = person.getLastName();
            int age = searchRepository.getAgeByName(firstName, lastName);

            if(age <= 18){
                childDtos.add(new ChildDto(firstName, lastName, age));
            }
            if(person.getLastName().equals(lastName) && age > 18){
                family.add(person);
            }
        }
        if(childDtos.isEmpty()) return null;

        childAlert.setChildren(childDtos);
        childAlert.setFamilyMembers(family);
        return childAlert;
    }


}
