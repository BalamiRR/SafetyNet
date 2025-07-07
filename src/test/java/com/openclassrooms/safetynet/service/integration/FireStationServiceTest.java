package com.openclassrooms.safetynet.service.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.openclassrooms.safetynet.model.FireStation;
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
public class FireStationServiceTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void getAllFireStations() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders
                        .get("/fireStation"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()", is(13)));
    }

    @Test
    public void addNewFireStationInfoShouldReturnTrue() throws Exception {
        FireStation fireStation = new FireStation("16 Wall Street. Las Vegas", 3);
        MockHttpServletResponse response = mockMvc.perform(MockMvcRequestBuilders
                        .post("/fireStation")
                        .content(objectMapper.writeValueAsString(fireStation))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse();
        assertEquals("true", response.getContentAsString(StandardCharsets.UTF_8));
    }

    @Test
    public void updateFireStationInfoShouldReturnTrue() throws Exception {
        FireStation fireStation = new FireStation("No Address", 88);
        MockHttpServletResponse response = mockMvc.perform(MockMvcRequestBuilders
                        .put("/fireStation")
                        .content(objectMapper.writeValueAsString(fireStation))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andReturn()
                .getResponse();
        assertEquals("false", response.getContentAsString(StandardCharsets.UTF_8));
    }

    @Test
    public void deleteFireStationByAddressShouldReturnTrue() throws Exception {
        FireStation fireStation = new FireStation("951 LoneTree Rd", 2);
        MockHttpServletResponse response = mockMvc.perform(MockMvcRequestBuilders
                        .delete("/fireStation?address=951 LoneTree Rd")
                        .content(objectMapper.writeValueAsString(fireStation))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isAccepted())
                .andReturn()
                .getResponse();
        assertEquals("true", response.getContentAsString(StandardCharsets.UTF_8));
    }

    @Test
    public void deleteFireStationByStationNumberShouldReturnTrue() throws Exception {
        FireStation fireStation = new FireStation("951 LoneTree Rd", 2);
        MockHttpServletResponse response = mockMvc.perform(MockMvcRequestBuilders
                        .delete("/fireStation?station=2")
                        .content(objectMapper.writeValueAsString(fireStation))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isAccepted())
                .andReturn()
                .getResponse();
        assertEquals("true", response.getContentAsString(StandardCharsets.UTF_8));
    }

}
