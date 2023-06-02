package com.example.serwer.body;

public class MovePawnBody {
    public int fieldNumber;
    public int playerID;

    public int getField() {
        return fieldNumber;
    }

    public int getPlayerId() {
        return playerID;
    }

    public void setField(int fieldNumber) {
        this.fieldNumber = fieldNumber;
    }
}