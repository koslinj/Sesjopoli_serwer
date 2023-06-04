package com.example.serwer;

import com.example.serwer.body.GiveAnswerBody;
import com.example.serwer.body.MovePawnBody;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GiveAnswer {

    @PostMapping("/answer")
    public ResponseEntity<String> updateGameState(@RequestBody GiveAnswerBody body) {
        Question q = GameState.getInstance().getQuestions().get(body.getQuestionIndex());
        if(q.isCorrectAnswer(body.getAnswerIndex())){
            GameState.getInstance().changeMoney(body.getPlayerId(),GameState.getInstance().getMoney().get(body.getPlayerId()) + 4);
        }
        System.out.println(body.getAnswerIndex());
        System.out.println(body.getQuestionIndex());
        System.out.println(body.getPlayerId());
        return ResponseEntity.ok("Game state updated successfully POZYCJA");
    }
}
