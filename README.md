## SafetyNet
SafetyNet Alerts est une application qui aide les services de secours comme les pompiers à réagir rapidement en cas d’urgence.
Elle permet de :
 - Savoir où les personnes habitent dans une ville ou une région.
 - Identifier les personnes en danger à une adresse donnée.
 - Trouver quelle caserne de pompiers est responsable d'une adresse.

<img width="250" height="250" alt="17159021463335_P5-01_SafetyNet" src="https://github.com/user-attachments/assets/cac14442-e285-4037-932e-c609d8051ecc" />

#### Comment ça fonctionne ?
SafetyNet Alerts utilise un fichier JSON qui contient :
Les noms et adresses des habitants,
Les informations médicales importantes (comme les médicaments),
La correspondance entre les adresses et les casernes de pompiers.
Quand un utilisateur ou un service fait une demande (appel API), l’application lit le fichier JSON et répond avec les bonnes informations au format JSON.

#### Unit Tests
This project includes unit tests to verify the behaviour of service classes in isolation.
For example, in PersonServiceTest, we mock the PersonRepository and check that the service correctly delegates the CRUD operations.
* Mocking with Mockito is used to isolate dependencies.
* Examples tested:
  * Saving a person: savePersonDelegatesToRepository()
  * Updating and deleting person data.
* Ensures that business logic behaves correctly without relying on external components.

####  Integration Tests
Integration tests verify the complete flow between the controller, service, and repository layers.
  * Performed using MockMvc to simulate real HTTP requests.
  * Examples tested:
    * Creating a person via a POST request to /person.
    * Retrieving data with endpoints like /childAlert, /phoneAlert, or /flood/stations.
* These tests confirm that:
  * JSON payloads are correctly handled.
  * HTTP responses have expected status codes and content.
  * The data flow from the controller to the repository is functional.
  
#### JaCoCo Report
<img width="852" height="160" alt="Surefire report" src="https://github.com/user-attachments/assets/a8b72881-cc92-42aa-bce0-b0e9c0b01eed" />

#### Surefire Report
<img width="560" height="131" alt="JaCoCo" src="https://github.com/user-attachments/assets/bd2b26ee-ee82-4b11-b341-02c7f15c2df6" />
