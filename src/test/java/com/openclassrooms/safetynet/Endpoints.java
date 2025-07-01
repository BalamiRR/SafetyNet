package com.openclassrooms.safetynet;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RequiredArgsConstructor
@AutoConfigureMockMvc
@SpringBootTest
public class Endpoints {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void getAllPerson() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders
                .get("/person"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()", is(23)));
    }

    @Test
    public void getAllFireStations() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders
                        .get("/fireStation"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()", is(13)));
    }

    @Test
    public void getAllMedicalRecords() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders
                        .get("/medicalRecord"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()", is(23)));
    }
}
