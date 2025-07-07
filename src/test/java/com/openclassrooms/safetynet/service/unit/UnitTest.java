package com.openclassrooms.safetynet.service.unit;

import com.openclassrooms.safetynet.dto.PersonRecord;
import com.openclassrooms.safetynet.model.FireStation;
import com.openclassrooms.safetynet.model.Person;
import com.openclassrooms.safetynet.repository.FireStationRepository;
import com.openclassrooms.safetynet.repository.PersonRepository;
import com.openclassrooms.safetynet.service.FireStationService;
import com.openclassrooms.safetynet.service.PersonService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UnitTest {
    @Mock
    private PersonRepository personRepository;
    @Mock
    private FireStationRepository fireStationRepository;

    @InjectMocks
    private PersonService personService;
    @InjectMocks
    private FireStationService fireStationService;

    @Test
    void savePersonDelegatesToRepository(){
        Person p = new Person("Cristiano", "Ronaldo", "1509 Culver St",
                "Culver", "97451", "841-874-651", "drk@email.com");
        when(personRepository.savePerson(p)).thenReturn(true);
        assertTrue(personService.savePerson(p));
        verify(personRepository).savePerson(p);
    }

    @Test
    void savePersonExistingPersonReturnsFalse() {
        Person p = new Person("John","Boyd", "1509 Culver St", "Culver", "97451", "841-874-6512", "jaboyd@email.com");
        when(personRepository.savePerson(p)).thenReturn(false);
        assertFalse(personService.savePerson(p));
        verify(personRepository).savePerson(p);
    }

    @Test
    void deletePersonDelegate(){
        when(personRepository.deletePerson("John", "Doe")).thenReturn(true);
        assertTrue(personService.deletePerson("John", "Doe"));
        verify(personRepository).deletePerson("John","Doe");
    }

    @Test
    void updatePersonDelegate(){
        Person updatedPerson = new Person("John", "Doe", "21 Wall Street",
                "New York", "2800", "111-874-651", "doe@email.com");
        when(personRepository.updatePerson("John", "Doe", updatedPerson)).thenReturn(true);
        assertTrue(personService.updatePerson("John", "Doe", updatedPerson));
        verify(personRepository).updatePerson("John", "Doe", updatedPerson);
    }

    @Test
    void saveFireStationDelegate(){
        FireStation fireStation = new FireStation("15 Wall Street", 3);
        when(fireStationRepository.savingStation(fireStation)).thenReturn(true);
        assertTrue(fireStationService.saveStation(fireStation));
        verify(fireStationRepository).savingStation(fireStation);
    }

    @Test
    void deleteFireStationDelegate(){
        when(fireStationRepository.deleteByAddress("15 Allee")).thenReturn(true);
        assertTrue(fireStationService.deleteByAddress("15 Allee"));
        verify(fireStationRepository).deleteByAddress("15 Allee");
    }

    @Test
    public void testSettersAndGetters() {
        PersonRecord pr = new PersonRecord();
        pr.setFirstName("John");
        assertEquals("John", pr.getFirstName());

        pr.setLastName("Doe");
        assertEquals("Doe", pr.getLastName());

        pr.setPhone("12345");
        assertEquals("12345", pr.getPhone());

        pr.setAge(30);
        assertEquals(30, pr.getAge());

        List<String> meds = new ArrayList<>();
        meds.add("med1");
        pr.setMedications(meds);
        assertEquals(meds, pr.getMedications());

        List<String> allergies = new ArrayList<>();
        allergies.add("allergy1");
        pr.setAllergies(allergies);
        assertEquals(allergies, pr.getAllergies());
    }
}
