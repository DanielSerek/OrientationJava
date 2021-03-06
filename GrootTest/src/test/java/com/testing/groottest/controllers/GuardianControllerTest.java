package com.testing.groottest.controllers;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(GuardianController.class)
public class GuardianControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void grootMessageGET_withParameter() throws Exception{
        mockMvc.perform(get("/groot?message=somemessage")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.translated", is("I am Groot!")))
                .andReturn();
    }

    @Test
    public void grootMessageGET_withoutParameter() throws Exception{
        mockMvc.perform(get("/groot")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.error", is("I am Groot")))
                .andReturn();
    }
}