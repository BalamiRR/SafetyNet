package com.openclassrooms.safetynet.service.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.openclassrooms.safetynet.model.MedicalRecord;
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
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RequiredArgsConstructor
@AutoConfigureMockMvc
@SpringBootTest
public class MedicalRecordEndServiceTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void getAllMedicalRecords() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders
                        .get("/medicalRecord"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()", is(23)));
    }

    @Test
    public void createMedicalRecordShouldReturnTrue() throws Exception {
        List<String> medications = new ArrayList<>();
        medications.add("\"parazol:350mg\"");
        medications.add("selenium:100mg");
        List<String> allergies = new ArrayList<>();
        allergies.add("apple");
        MedicalRecord record = new MedicalRecord( "Cristiano", "Ronaldo", new SimpleDateFormat("yyyyMMdd").parse("19840306"), medications, allergies);
        MockHttpServletResponse response = mockMvc.perform( MockMvcRequestBuilders
                        .post("/medicalRecord")
                        .content(objectMapper.writeValueAsString(record))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse();
        assertEquals("true", response.getContentAsString(StandardCharsets.UTF_8));
    }

    @Test
    public void updateMedicalRecordShouldReturnTrue() throws Exception {
        List<String> medications = new ArrayList<>();
        medications.add("\"aznol:350mg\"");
        medications.add("hydrapermazol:100mg");
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
    public void deleteMedicalRecordShouldReturnTrue() throws Exception {
        List<String> medications = new ArrayList<>();
        medications.add("\"aznol:350mg\"");
        medications.add("hydrapermazol:100mg");
        List<String> allergies = new ArrayList<>();
        allergies.add("");
        MedicalRecord record = new MedicalRecord( "John", "Boyd", new SimpleDateFormat("yyyyMMdd").parse("19870707"), medications, allergies);
        MockHttpServletResponse response = mockMvc.perform( MockMvcRequestBuilders
                        .delete("/medicalRecord/John/Boyd")
                        .content(objectMapper.writeValueAsString(record))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isAccepted())
                .andReturn()
                .getResponse();
        assertEquals("true", response.getContentAsString(StandardCharsets.UTF_8));
    }
}
