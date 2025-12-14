package com.example.demo.Service;


import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class LocalEmbeddingService {
    private final RestTemplate restTemplate = new RestTemplate();
    private static final String EMBEDDING_URL = "http://localhost:5000/embed";

    public List<float[]> embedTexts(List<String> texts) {

        List<List<Double>> response =
                restTemplate.postForObject(
                        EMBEDDING_URL,
                        Map.of("texts", texts),
                        List.class
                );

        List<float[]> vectors = new ArrayList<>();

        for (List<Double> vec : response) {
            float[] arr = new float[vec.size()];
            for (int i = 0; i < vec.size(); i++) {
                arr[i] = vec.get(i).floatValue();
            }
            vectors.add(arr);
        }

        return vectors;
    }
}
