package com.one2tribe.demo.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.one2tribe.demo.dto.MessageDto;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

@SpringBootTest
@AutoConfigureMockMvc
public class MessageControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void shouldGetAllMessages() throws Exception {
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/message"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andReturn();
        String json = mvcResult.getResponse().getContentAsString();
        List<MessageDto> messageDtoList = objectMapper.readValue(json, new TypeReference<List<MessageDto>>(){});
        assertTrue(messageDtoList.size()>0);
    }

    @Test
    void shouldUpdateMessage() throws Exception{

        mockMvc.perform(MockMvcRequestBuilders.put("/message/2").content("{\"content\" : \"Wiadomosc xyz\"}")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.is(2) ))
                .andExpect(MockMvcResultMatchers.jsonPath("$.content", Matchers.is("Wiadomosc xyz") ))
        ;

    }

    @Test
    void shouldAddNewMessage() throws Exception{

        mockMvc.perform(MockMvcRequestBuilders.post("/message").content("{\"content\" : \"Wiadomosc foo\"}")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$.content", Matchers.is("Wiadomosc foo") ))
        ;

    }

    @Test
    void shouldGetRandomMessages() throws Exception {
        int number = 5;
      MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/message/random/"+number))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andReturn();
      String json = mvcResult.getResponse().getContentAsString();
       List<MessageDto> messageDtoList = objectMapper.readValue(json, new TypeReference<List<MessageDto>>(){});
        assertTrue(messageDtoList.size()==number);
    }


}
