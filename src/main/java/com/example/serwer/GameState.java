package com.example.serwer;

import java.util.ArrayList;

public class GameState {
    private ArrayList<Integer> playerPositions;

    public GameState() {
        playerPositions = new ArrayList<Integer>();
        playerPositions.add(0,0);
        playerPositions.add(1,0);
        playerPositions.add(2,0);
        playerPositions.add(3,0);
    }

    public ArrayList<Integer> getPlayerPositions() {
        return playerPositions;
    }

    public void updatePlayerPosition(PlayerPositionBody p){
        playerPositions.set(p.getPlayerId(),p.getField());
    }

    public void displayGameState(){
        for(int i=0;i<playerPositions.size(); ++i){
            System.out.println("ID: " + i + " POS: " + playerPositions.get(i));
        }
    }
}
