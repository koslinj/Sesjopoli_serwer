package com.example.serwer;

import java.util.ArrayList;

public class Question {
    private String question;
    private ArrayList<String> answers;
    private int correctAnswerIndex;

    public Question(String question, ArrayList<String> answers, int correctAnswerIndex) {
        this.question = question;
        this.answers = answers;
        this.correctAnswerIndex = correctAnswerIndex;
    }

    public String getQuestion() {
        return question;
    }

    public ArrayList<String> getAnswers() {
        return answers;
    }

    public boolean isCorrectAnswer(int answerIndex) {
        return answerIndex == correctAnswerIndex;
    }
}
