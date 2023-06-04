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
        GameState.getInstance().changeMoney(playerID,GameState.getInstance().getMoney().get(playerID)- GameState.getInstance().getFieldPrices().get(fieldNumber));

        System.out.println(playerID);
        System.out.println(fieldNumber);
        return ResponseEntity.ok("Game state updated successfully STRING");
    }
}
