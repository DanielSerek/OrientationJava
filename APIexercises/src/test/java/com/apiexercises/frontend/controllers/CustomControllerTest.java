package com.apiexercises.frontend.controllers;

import com.apiexercises.frontend.models.DoubledNumber;
import com.apiexercises.frontend.services.DoublingService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class CustomControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DoublingService service;

    @Test
    void doublingReturnsDoubledValue() throws Exception {
        when(service.doubleNumber(5L)).thenReturn(new DoubledNumber(5L,10L));
        ObjectMapper Obj = new ObjectMapper();
        this.mockMvc.perform(get("/doubling?input=5")).andDo(print())
            .andExpect(content().string(containsString(Obj.writeValueAsString(new DoubledNumber(5L,10L)))));
    }

    @Test
    void greeterReturnsGreeting() throws Exception {
        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.get("/greeter?name=Dan&title=student");
        this.mockMvc.perform(builder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(content().string(containsString("{\"name\":\"Dan\",\"title\":\"student\",\"welcome_message\":\"Oh, hi there Dan, my dear student!\"}")))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    void doUntilSum5Returns15() throws Exception{
        this.mockMvc.perform(get("https://localhost:" + port + "/doubling?input=5")).andExpect(status().isOk())
                .andExpect(jsonPath("$.result").exists())
                .andExpect(jsonPath("$.result").value(10))
                .andDo(print());
    }


}