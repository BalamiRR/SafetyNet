package com.openclassrooms.safetynet.service.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
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

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RequiredArgsConstructor
@AutoConfigureMockMvc
@SpringBootTest
public class PersonServiceTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void getAllPerson() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders
                        .get("/person"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()", is(23)));
    }

    @Test
    public void createAPersonShouldReturnTrue() throws Exception {
        Person person = new Person("Thomas", "Anderson", "15 Street John Kennedy", "New York", "28000", "11-555-9999", "t.anderson@gmail.com");
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

    @Test
    public void deletePersonWithAPersonShouldReturnTrue() throws Exception {
        Person person = new Person("John", "Boyd", "1509 Culver St", "Culver", "97451", "841-874-6512", "jaboyd@ymail.com");
        MockHttpServletResponse response = mockMvc.perform(MockMvcRequestBuilders
                        .delete("/person/John/Boyd")
                        .contentType(objectMapper.writeValueAsString(person))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isAccepted())
                .andReturn()
                .getResponse();
        assertEquals("true", response.getContentAsString(StandardCharsets.UTF_8));
    }

    @Test
    public void updatePersonWithPersonShouldReturnTrue() throws Exception {
        Person person = new Person("John", "Boyd", "Gedikkaya Caddesi. Beyzanur", "Giresun", "28000", "0454-27-00-31-", "f.k@gmail.com");
        MockHttpServletResponse response = mockMvc.perform(MockMvcRequestBuilders
                        .put("/person/John/Boyd")
                        .content(objectMapper.writeValueAsString(person))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isAccepted())
                .andReturn()
                .getResponse();
        assertEquals("true", response.getContentAsString(StandardCharsets.UTF_8));
    }
}
