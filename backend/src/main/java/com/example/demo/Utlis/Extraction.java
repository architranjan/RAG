package com.example.demo.Utlis;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;


@Component
public class Extraction {
   public String extractText(MultipartFile file) throws Exception {

        PDDocument document =
                PDDocument.load(file.getInputStream());

        PDFTextStripper stripper = new PDFTextStripper();
        String text = stripper.getText(document);

        document.close();
        return text;
    }

}
