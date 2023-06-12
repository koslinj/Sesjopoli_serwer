package com.example.serwer.controllers;

import com.example.serwer.GameState;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ExitGame {

    public static final int PLAYER_EXITED_VALUE = -1000;

    @PostMapping("/exit")
    public ResponseEntity<String> exitGame(@RequestBody Integer id) {

        GameState.getInstance().changeMoney(id, PLAYER_EXITED_VALUE);
        return ResponseEntity.ok("Game state updated successfully");
    }
}
