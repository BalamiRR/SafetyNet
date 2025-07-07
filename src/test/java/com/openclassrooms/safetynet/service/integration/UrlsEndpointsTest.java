package com.openclassrooms.safetynet.service.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.openclassrooms.safetynet.model.MedicalRecord;
import com.openclassrooms.safetynet.model.Person;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.*;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@RequiredArgsConstructor
@AutoConfigureMockMvc
@SpringBootTest
class UrlsEndpointsTest {

	@Autowired
	private MockMvc mockMvc;
	@Autowired
	private ObjectMapper objectMapper;

	@Test
	void contextLoads() {

	}

	@Test
	public void getAllEmailsGivenCityReturnAList() throws Exception{
		mockMvc.perform(get("/communityEmail?city=Culver"))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(content().string(containsString("drk@email.com")))
				.andExpect(jsonPath("$.length()", is(15)));
	}

	@Test
	public void getAllPhonesGivenFireStationNumberShouldReturnAList() throws Exception {
		mockMvc.perform(get("/phoneAlert?fireStationNumber=1"))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(content().string(containsString("841-874-6512")))
				.andExpect(jsonPath("$.length()", is(4)));
	}

	@Test
	public void getAllPhonesGivenNotExistFireStationShouldReturnEmptyList() throws Exception {
		mockMvc.perform(get("/phoneAlert?fireStationNumber=100"))
				.andDo(print())
				.andExpect(status().isNotFound())
				.andExpect(content().string(""))
				.andReturn();
	}

	@Test
	public void getPersonsNameAndStationsNumberGivenAddressShouldReturnCorrectData() throws Exception {
		mockMvc.perform(get("/fire?address=112 Steppes Pl"))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.stationNumber", containsInAnyOrder(3, 4)));
	}

	@Test
	public void getPersonsNameAndStationsNumberGivenNotExistAddressShouldReturnAnEmptyJson() throws Exception {
		mockMvc.perform(get("/fire?address=50 Rue anne-sophie Sarcelles"))
				.andDo(print())
				.andExpect(status().isNotFound())
				.andExpect(jsonPath("$.stationNumber").isEmpty());
	}

	@Test
	public void getPersonsGivenStationNumberShouldReturnPersons() throws Exception {
		mockMvc.perform(get("/fireStation/station?stationNumber=1"))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.listPerson.length()", is(6)))
				.andExpect(jsonPath("$.adult").value(5))
				.andExpect(jsonPath("$.child").value(1));
	}

	@Test
	public void getAddressesSortedGivenStationNumberShouldReturnPersons() throws Exception {
		mockMvc.perform(get("/flood/stations?stations=1"))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.length()", is(3)));
	}

	@Test
	public void getAddressesSortedGivenNotExistStationNumberShouldReturnEmptyList() throws Exception {
		mockMvc.perform(get("/flood/stations?stations=111"))
				.andDo(print())
				.andExpect(status().isNotFound())
				.andExpect(jsonPath("$.address").doesNotExist());
	}

	@Test
	public void getChildrenAndFamilyInfoByGivenAddressShouldReturnChildrenParents() throws Exception {
		mockMvc.perform(get("/childAlert?address=892 Downing Ct"))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.children[0].age").value(8))
				.andExpect(jsonPath("$.children.length()", is(1)))
				.andExpect(jsonPath("$.familyMembers.length()").value(2));
	}

	@Test
	public void getChildrenAndFamilyInfoByGivenNotExistAddressShouldReturnChildrenParents() throws Exception {
		mockMvc.perform(get("/childAlert?address=15 Wall Street"))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.children.length()", is(0)))
				.andExpect(jsonPath("$.familyMembers.length()").value(0));
	}

	// Log error
	@Test
	public void getAllEmailsGivenCityReturnAListFalse() throws Exception{
		mockMvc.perform(get("/communityEmail?city=Paris"))
				.andDo(print())
				.andExpect(status().isNotFound())
				.andExpect(content().string(containsString("")));
	}

	@Test
	public void updateRecord_withARecord_shouldReturnTrue() throws Exception {
		List<String> medications = new ArrayList<>();
		medications.add("\"abcd:350mg\"");
		medications.add("selenium:100mg");
		List<String> allergies = new ArrayList<>();
		allergies.add("");
		MedicalRecord record = new MedicalRecord( "John", "Boyd", new SimpleDateFormat( "yyyyMMdd" ).parse( "19840306" ), medications, allergies);
		MockHttpServletResponse response = mockMvc.perform( MockMvcRequestBuilders
						.put("/medicalRecord/John/Boyd")
						.content(objectMapper.writeValueAsString(record))
						.contentType(MediaType.APPLICATION_JSON)
						.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isBadRequest())
				.andReturn()
				.getResponse();
		assertEquals("false", response.getContentAsString(StandardCharsets.UTF_8));
	}

	@Test
	public void createPersonExistPersonShouldReturnFalse() throws Exception {
		Person person = new Person("Jacob", "Boyd", "1509 Culver St", "Culver", "97451", "841-874-651", "drk@email.com");
		MockHttpServletResponse response = mockMvc.perform(MockMvcRequestBuilders
						.post("/person")
						.content(objectMapper.writeValueAsString(person))
						.contentType(MediaType.APPLICATION_JSON)
						.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isBadRequest())
				.andReturn()
				.getResponse();
		assertEquals("false", response.getContentAsString(StandardCharsets.UTF_8));
	}

	@Test
	public void deleteNotExistMedicalRecordShouldReturnFalse() throws Exception {
		List<String> medications = new ArrayList<>();
		medications.add("\"aznol:350mg\"");
		medications.add("hydrapermazol:100mg");
		List<String> allergies = new ArrayList<>();
		allergies.add("");
		MedicalRecord record = new MedicalRecord( "FFF", "LLL", new SimpleDateFormat("yyyyMMdd").parse("19870707"), medications, allergies);
		MockHttpServletResponse response = mockMvc.perform( MockMvcRequestBuilders
						.delete("/medicalRecord/FFF/LLL")
						.content(objectMapper.writeValueAsString(record))
						.contentType(MediaType.APPLICATION_JSON)
						.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isBadGateway())
				.andReturn()
				.getResponse();
		assertEquals("false", response.getContentAsString(StandardCharsets.UTF_8));
	}

}