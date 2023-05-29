package com.example.serwer;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TurnController {
    private Turn t;

    TurnController(){
        t = new Turn();
    }

    @GetMapping("/getturn")
    public int getTurn() {
        return t.getWhoseTurn();
    }

    @GetMapping("/endturn")
    public void endTurn() {
        t.endTurn();
    }
}
