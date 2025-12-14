package com.example.demo.Service;

import com.example.demo.Models.PdfChunk;
import com.example.demo.Models.QuizResponse;
import com.example.demo.Repository.ChunkRepo;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class QuizService {

    @Autowired
    ObjectMapper mapper;


    @Autowired
    ChunkRepo repo;

   @Autowired
    LLMService llm;

    public QuizResponse generateQuiz(){
        List<PdfChunk> chunks =  repo.findRandomChunks(5);


        StringBuilder context = new StringBuilder();
        //System.out.println(chunks.size());
        for (PdfChunk chunk : chunks) {
            context.append(chunk.getContent()).append("\n\n");
        }

        String prompt = """
    Assume you are a teacher
    Generate 5 multiple choice questions using ONLY the context below.

    Rules:
    - Each question must have exactly 4 options
    - Only ONE option must be correct
    - Use correctAnswer as the index (0-3)
    - Return ONLY valid JSON
    - Do NOT add explanations or extra text

    JSON format:
    {
      "questions": [
        {
          "question": "",
          "options": ["", "", "", ""],
          "correctAnswer": 0
        }
      ]
    }

    Context:
    %s
    """.formatted(context);

       String response =  extractJson(llm.getResponse(prompt));
//       System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
//       System.out.println(response);

        // 4. Parse JSON
        try {
            return mapper.readValue(response, QuizResponse.class);
        } catch (Exception e) {
            throw new RuntimeException("LLM returned invalid quiz JSON");
        }

    }

    private String extractJson(String text) {

        int start = text.indexOf("{");
        int end = text.lastIndexOf("}");

        if (start == -1 || end == -1 || start > end) {
            throw new RuntimeException("No JSON found in LLM response");
        }

        return text.substring(start, end + 1);
    }


}
