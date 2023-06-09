package com.example.serwer;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GameStateController {

    @GetMapping("/")
    public GameState getData() {
        return GameState.getInstance();
    }

    @GetMapping("/getplayerid")
    public int getPlayerId() {
        int old = GameState.getInstance().getPlayerId();
        GameState.getInstance().setPlayerId(old+1);
        return GameState.getInstance().getPlayerId();
    }

    @GetMapping("/endturn")
    public void endTurn() {
        GameState.getInstance().endTurn();
    }
}
