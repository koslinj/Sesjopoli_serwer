package com.example.serwer;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
public class GameStateController {
    private GameState g;

    GameStateController(){
        g = new GameState();
    }

    @PostMapping("/update")
    public ResponseEntity<String> updateGameState(@RequestBody PlayerPositionBody p) {
        g.updatePlayerPosition(p);
        g.displayGameState();
        return ResponseEntity.ok("Game state updated successfully");
    }

    @GetMapping("/getpositions")
    public ArrayList<Integer> getPositions() {
        return g.getPlayerPositions();
    }
}
