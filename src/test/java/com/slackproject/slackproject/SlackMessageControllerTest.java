package com.slackproject.slackproject;

import static org.mockito.ArgumentMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.client.RestTemplate;

@WebMvcTest(SlackMessageController.class)
class SlackMessageControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RestTemplate restTemplate;

    @Test
    void testSendSlackMessage() throws Exception {
        // Mocking RestTemplate response
        Mockito.when(restTemplate.postForEntity(anyString(), any(), eq(String.class)))
               .thenReturn(new ResponseEntity<>("OK", HttpStatus.OK));

        mockMvc.perform(MockMvcRequestBuilders.post("/sendSlackMessage")
                .contentType("application/json")
                .content("{\"message\":\"Hello, Slack!\"}"))
                .andExpect(status().isOk());
    }
}
