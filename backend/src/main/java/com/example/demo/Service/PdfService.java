package com.example.demo.Service;


import com.example.demo.Models.PdfChunk;
import com.example.demo.Models.PdfData;
import com.example.demo.Repository.ChunkRepo;
import com.example.demo.Repository.PdfRepo;
import com.example.demo.Utlis.Chunking;
import com.example.demo.Utlis.Extraction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


import java.util.List;

@Service
public class PdfService {

    @Autowired
    PdfRepo repo;
    @Autowired
    ChunkRepo crepo;
    @Autowired
    Chunking chunking;
    @Autowired
    Extraction extract;

    @Autowired
    LocalEmbeddingService embedding;




    public void processPdf(MultipartFile file){
       try {


           crepo.deleteAll();
           repo.deleteAll();

           PdfData pdf = new PdfData();

           pdf.setName(file.getOriginalFilename());

           repo.save(pdf);

           String Text = extract.extractText(file);

           List<String> chunkOfText = chunking.chunkText(Text);






           List<float[]> embeddings = embedding.embedTexts(chunkOfText);




           for (int i = 0; i < chunkOfText.size(); i++) {
               String chunkText = chunkOfText.get(i);
               float[] embed = embeddings.get(i);
               PdfChunk chunk = new PdfChunk();
               chunk.setContent(chunkText);
               chunk.setEmbedding(embed);
               chunk.setPdf(pdf);


               crepo.save(chunk);
           }
       } catch (Exception e) {
           e.printStackTrace();
           throw new RuntimeException("PDF processing failed", e);
       }




    }


}
