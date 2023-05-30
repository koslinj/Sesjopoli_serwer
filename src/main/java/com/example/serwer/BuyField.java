package com.example.serwer;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BuyField {

    int playerID;
    int fieldNumber;
    @GetMapping("/house")
    public DataResponse getData() {
       return new DataResponse(playerID, fieldNumber);
    }

    @PostMapping("/house")
    public ResponseEntity<String> updateGameState(@RequestBody DataResponse dataResponse) {
        playerID = dataResponse.playerID;
        fieldNumber = dataResponse.fieldNumber;
        System.out.println(playerID);
        System.out.println(fieldNumber);
        return ResponseEntity.ok("Game state updated successfully");
    }

    public static class DataResponse {
        public int playerID;
        public int fieldNumber;

        public DataResponse(int playerID, int fieldNumber) {
            this.playerID = playerID;
            this.fieldNumber = fieldNumber;
        }

        public int getPlayerID() {
            return playerID;
        }

        public int getFieldNumber() {
            return fieldNumber;
        }
    }
}
