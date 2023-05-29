package com.example.serwer;

public class Turn {
    private int whoseTurn;

    public Turn() {
        whoseTurn=1;
    }

    public int getWhoseTurn() {
        return whoseTurn;
    }

    public void endTurn(){
        whoseTurn++;
        if(whoseTurn==5) whoseTurn=1;
    }
}
