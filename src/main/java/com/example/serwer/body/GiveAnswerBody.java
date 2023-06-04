package com.example.serwer.body;

public class GiveAnswerBody {
    public int answerIndex;
    public int questionIndex;
    public int playerID;

    public int getAnswerIndex() {
        return answerIndex;
    }

    public int getQuestionIndex() {
        return questionIndex;
    }

    public int getPlayerId() {
        return playerID;
    }
}
