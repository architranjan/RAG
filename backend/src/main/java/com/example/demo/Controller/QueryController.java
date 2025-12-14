package com.example.demo.Controller;


import com.example.demo.Service.AnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000")
public class QueryController {



    @Autowired
    AnswerService result;
    @PostMapping("/query")
    public ResponseEntity<?> getAnswer(@RequestBody String question){

        String answer = result.getAnswer(question);

        return new ResponseEntity<>(answer , HttpStatus.ACCEPTED);


    }


}
