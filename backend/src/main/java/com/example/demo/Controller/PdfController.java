package com.example.demo.Controller;


import com.example.demo.Service.PdfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000")
public class PdfController {

    @Autowired
    PdfService service;



  @PostMapping("/upload")
    public ResponseEntity<?> uploadPdf(@RequestParam("file") MultipartFile file){

        if(file.isEmpty()){
            return new ResponseEntity<>("File is Empty" , HttpStatus.BAD_REQUEST);
        }


        service.processPdf(file);

        return new ResponseEntity<>("File Uploaded SucessFully" , HttpStatus.ACCEPTED);
    }








}
