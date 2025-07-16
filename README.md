## SafetyNet
SafetyNet Alerts is an application that helps emergency services, such as firefighters, respond quickly in case of an emergency.
It allows them to:
 * Know where people live in a city or region
 * Identify individuals at risk at a specific address
 * Find out which fire station is responsible for a given address

<img width="250" height="250" alt="17159021463335_P5-01_SafetyNet" src="https://github.com/user-attachments/assets/cac14442-e285-4037-932e-c609d8051ecc" />

### How does it work?
SafetyNet Alerts uses a JSON file that contains:
* The names and addresses of residents
* Important medical information (such as medications)
* The mapping between addresses and fire stations
When a user or service makes a request (API call), the application reads the JSON file and responds with the appropriate information in JSON format.

### Unit Tests
This project includes unit tests to verify the behaviour of service classes in isolation.
For example, in PersonServiceTest, we mock the PersonRepository and check that the service correctly delegates the CRUD operations.
* Mocking with Mockito is used to isolate dependencies.
* Examples tested:
  * Saving a person: savePersonDelegatesToRepository()
  * ```
    @Test
    void savePersonDelegatesToRepository(){
        Person p = new Person("Cristiano", "Ronaldo", "1509 Culver St",
                "Culver", "97451", "841-874-651", "drk@email.com");
        when(personRepository.savePerson(p)).thenReturn(true);
        assertTrue(personService.savePerson(p));
        verify(personRepository).savePerson(p);
    }

```
  * Updating and deleting person data.
* Ensures that business logic behaves correctly without relying on external components.

###  Integration Tests
Integration tests verify the complete flow between the controller, service, and repository layers.
  * Performed using MockMvc to simulate real HTTP requests.
  * Examples tested:
    * Creating a person via a POST request to /person.
    * Retrieving data with endpoints like /childAlert, /phoneAlert, or /flood/stations.
* These tests confirm that:
  * JSON payloads are correctly handled.
  * HTTP responses have expected status codes and content.
  * The data flow from the controller to the repository is functional.
  ```
    @Test
    public void createAPersonShouldReturnTrue() throws Exception {
        Person person = new Person("Thomas", "Anderson", "15 Street John Kennedy",
                "New York", "28000", "11-555-9999", "t.anderson@gmail.com");
        MockHttpServletResponse response = mockMvc.perform(MockMvcRequestBuilders
                        .post("/person")
                        .content(objectMapper.writeValueAsString(person))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse();
        assertEquals("true", response.getContentAsString(StandardCharsets.UTF_8));
    }
```

### JaCoCo Report
<img width="852" height="160" alt="Surefire report" src="https://github.com/user-attachments/assets/a8b72881-cc92-42aa-bce0-b0e9c0b01eed" />

### Surefire Report
<img width="560" height="131" alt="JaCoCo" src="https://github.com/user-attachments/assets/bd2b26ee-ee82-4b11-b341-02c7f15c2df6" />
