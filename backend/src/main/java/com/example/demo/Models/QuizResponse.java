package com.example.demo.Models;

import java.util.List;

public class QuizResponse {
    private List<QuestionDTO> questions;

    public QuizResponse(){

    }

    public List<QuestionDTO> getQuestions() {
        return questions;
    }

    public QuizResponse(List<QuestionDTO> questions) {
        this.questions = questions;
    }

    @Override
    public String toString() {
        return "QuizResponse{" +
                "questions=" + questions +
                '}';
    }

    public void setQuestions(List<QuestionDTO> questions) {
        this.questions = questions;
    }
}
