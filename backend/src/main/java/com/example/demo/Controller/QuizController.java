package com.example.demo.Controller;


import com.example.demo.Models.QuizResponse;
import com.example.demo.Service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000")
public class QuizController {
    @Autowired
    QuizService service;

    @GetMapping("/quiz")
    public QuizResponse generate(){
        return service.generateQuiz();
    }

}
