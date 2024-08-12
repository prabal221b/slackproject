package com.slackproject.slackproject;



import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;


@RestController
public class SlackMessageController {

    @Value("${slack.webhook.url}")
    private String slackWebhookUrl;

    private final RestTemplate restTemplate;

    public SlackMessageController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;

    }

    @PostMapping("/sendSlackMessage")
    public ResponseEntity<String> sendSlackMessage(@RequestBody Map<String, String> payload) {
        String message = payload.get("message");
        Map<String, String> slackMessage = new HashMap<>();
        slackMessage.put("text", message);

        // Send message using RestTemplate (blocking)
        restTemplate.postForEntity(slackWebhookUrl, slackMessage, String.class);


        return new ResponseEntity<>("Message sent!", HttpStatus.OK);
    }
}
