package com.example.demo.Service;

import com.example.demo.dto.GeminiRequest;
import com.example.demo.dto.GeminiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.Map;

@Service
public class LLMService {

    @Value("${gemini.api-key}")
    private String apiKey;

    @Value("${gemini.model}")
    private String model;

    @Autowired
    WebClient webClient;


    public String getResponse(String prompt) {


        String url = "https://generativelanguage.googleapis.com/v1beta/models/"
                + model + ":generateContent";

        GeminiRequest request = new GeminiRequest(
                List.of(
                        new GeminiRequest.Content(
                                List.of(new GeminiRequest.Part(prompt))
                        )
                )
        );

        GeminiResponse response = webClient.post()
                .uri(url + "?key=" + apiKey)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(request)
                .retrieve()
                .bodyToMono(GeminiResponse.class)
                .block();

        if (response == null ||
                response.getCandidates() == null ||
                response.getCandidates().isEmpty()) {
            return "No response from Gemini";
        }

        return response.getCandidates()
                .get(0)
                .getContent()
                .getParts()
                .get(0)
                .getText();
    }

    }
