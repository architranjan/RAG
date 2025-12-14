package com.example.demo.Utlis;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


@Component
public class Chunking {
    public List<String> chunkText(String text) {

        int chunkSize = 500;
        int overlap = 100;

        List<String> chunks = new ArrayList<>();

        for (int start = 0;
             start < text.length();
             start += (chunkSize - overlap)) {

            int end = Math.min(start + chunkSize, text.length());
            chunks.add(text.substring(start, end));
        }

        return chunks;
    }
}


