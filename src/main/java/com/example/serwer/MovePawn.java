package com.example.serwer;

import com.example.serwer.body.MovePawnBody;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MovePawn {

    @PostMapping("/update")
    public ResponseEntity<String> updateGameState(@RequestBody MovePawnBody body) {
        GameState.getInstance().updatePlayerPosition(body);
        return ResponseEntity.ok("Game state updated successfully");
    }
}
