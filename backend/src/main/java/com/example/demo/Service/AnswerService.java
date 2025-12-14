package com.example.demo.Service;

import com.example.demo.Models.PdfChunk;
import com.example.demo.Repository.ChunkRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AnswerService {

    @Autowired
   ChunkRepo repo;
   @Autowired
   LocalEmbeddingService embedding;

   @Autowired
   LLMService llm;

   public String getAnswer(String question){

       List<String> q = new ArrayList<>();
       q.add(question);
       float[] questionEmbedding = embedding.embedTexts(q).get(0);

       String vectorLiteral = toVectorLiteral(questionEmbedding);

       List<PdfChunk> chunks =  repo.findSimilarChunks(vectorLiteral, 5);

       StringBuilder context = new StringBuilder();
       //System.out.println(chunks.size());
       for (PdfChunk chunk : chunks) {
           context.append(chunk.getContent()).append("\n\n");
       }

       String prompt = """
            Answer the question using ONLY the context below.
            If the answer is not present, say "Sorry I could not get this from the provided pdf".

            Context:
            %s

            Question:
            %s
            """.formatted(context.toString(), question);

       return llm.getResponse(prompt);










   }

    private String toVectorLiteral(float[] vector) {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < vector.length; i++) {
            sb.append(vector[i]);
            if (i < vector.length - 1) sb.append(",");
        }
        sb.append("]");
        return sb.toString();
    }










   }









