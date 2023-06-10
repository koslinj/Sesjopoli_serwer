package com.example.serwer;

import com.example.serwer.body.BuyFieldBody;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BuyField {

    @PostMapping("/house")
    public ResponseEntity<String> updateGameState(@RequestBody BuyFieldBody body) {
        int playerID = body.getPlayerID();
        int fieldNumber = body.getField();

        GameState.getInstance().getPositionOwners().set(fieldNumber,playerID);
        GameState.getInstance().changeMoneyWithHouse(fieldNumber,playerID);
        GameState.getInstance().checkAllSpecialFields();
        return ResponseEntity.ok("Game state updated successfully");
    }
}
