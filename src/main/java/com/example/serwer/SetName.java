package com.example.serwer;

import com.example.serwer.body.SetNameBody;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SetName {

    @PostMapping("/name")
    public ResponseEntity<String> updateGameState(@RequestBody SetNameBody body) {
        int playerID = body.getPlayerID();
        String name = body.getName();
        GameState.getInstance().getNames().set(playerID, name);
        return ResponseEntity.ok("Game state updated successfully");
    }

}
