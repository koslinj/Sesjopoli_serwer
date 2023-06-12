package com.example.serwer.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GameState {

    @GetMapping("/")
    public com.example.serwer.GameState getData() {
        return com.example.serwer.GameState.getInstance();
    }

    @GetMapping("/getplayerid")
    public int getPlayerId() {
        int old = com.example.serwer.GameState.getInstance().getPlayerId();
        com.example.serwer.GameState.getInstance().setPlayerId(old+1);
        return com.example.serwer.GameState.getInstance().getPlayerId();
    }

    @GetMapping("/endturn")
    public void endTurn() {
        com.example.serwer.GameState.getInstance().endTurn();
    }
}
