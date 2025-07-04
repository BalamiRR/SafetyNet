package com.openclassrooms.safetynet;

import com.openclassrooms.safetynet.model.Person;
import com.openclassrooms.safetynet.repository.PersonRepository;
import com.openclassrooms.safetynet.service.PersonService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UnitTest {
    @Mock
    private PersonRepository personRepository;

    @InjectMocks
    private PersonService personService;

    @Test
    void savePersonDelegatesToRepository(){
        Person p = new Person("Cristiano", "Ronaldo", "1509 Culver St",
                "Culver", "97451", "841-874-651", "drk@email.com");
        when(personRepository.savePerson(p)).thenReturn(true);
        assertTrue(personService.savePerson(p));
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



}
