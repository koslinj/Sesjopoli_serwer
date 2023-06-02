package com.example.serwer;

import com.example.serwer.body.MovePawnBody;

import java.util.ArrayList;
import java.util.Random;

public class GameState {
    public static final int NUMBER_OF_FIELDS = 24;
    public static final int INNOVATION_FIELD_INDEX = 18;
    public static final int STOP_FIELD_INDEX = 6;
    private ArrayList<Integer> playerPositions;
    private ArrayList<Integer> money;
    private ArrayList<Integer> positionOwners;
    private ArrayList<Integer> fieldPrices;
    private ArrayList<Integer> fieldPunishments;
    private ArrayList<String> names;
    private ArrayList<Boolean> stopFieldFlags;
    private int whoseTurn;
    private int playerId; // do zmiany

    private static volatile GameState instance = null;


    private GameState() {
        whoseTurn=1;
        playerId=0;
        arraysConstructor();
        initNumberArrays(playerPositions, 4, 0);
        initNumberArrays(money, 4, 30);
        initNumberArrays(positionOwners, NUMBER_OF_FIELDS, -1);
        initNumberArrays(stopFieldFlags,4,false);
        initNames();
        initFieldPrices();
        initFieldPunishments();
    }

    private void initFieldPunishments() {
        fieldPunishments = new ArrayList<>();
        fieldPunishments.add(0);
        fieldPunishments.add(2);
        fieldPunishments.add(2);
        fieldPunishments.add(0);
        fieldPunishments.add(-1);
        fieldPunishments.add(-1);
        fieldPunishments.add(0);
        fieldPunishments.add(2);
        fieldPunishments.add(3);
        fieldPunishments.add(0);
        fieldPunishments.add(3);
        fieldPunishments.add(4);
        fieldPunishments.add(0);
        fieldPunishments.add(0); //index
        fieldPunishments.add(4);
        fieldPunishments.add(0);
        fieldPunishments.add(5);
        fieldPunishments.add(6);
        fieldPunishments.add(0);
        fieldPunishments.add(0);
        fieldPunishments.add(0);
        fieldPunishments.add(6);
        fieldPunishments.add(8);
        fieldPunishments.add(10);
    }

    private void initFieldPrices() {
        fieldPrices = new ArrayList<>();
        fieldPrices.add(0);
        fieldPrices.add(2);
        fieldPrices.add(2);
        fieldPrices.add(0);
        fieldPrices.add(new Random().nextInt(6) + 1);
        fieldPrices.add(new Random().nextInt(6) + 1);
        fieldPrices.add(0);
        fieldPrices.add(2);
        fieldPrices.add(3);
        fieldPrices.add(0);
        fieldPrices.add(3);
        fieldPrices.add(4);
        fieldPrices.add(0);
        fieldPrices.add(0); //index
        fieldPrices.add(4);
        fieldPrices.add(0);
        fieldPrices.add(5);
        fieldPrices.add(6);
        fieldPrices.add(0);
        fieldPrices.add(0);
        fieldPrices.add(0);
        fieldPrices.add(6);
        fieldPrices.add(8);
        fieldPrices.add(10);
    }

    private void initNames() {
        names = new ArrayList<>();
        for (int i=0;i<4;i++) {
            names.add("");
        }
    }

    private void arraysConstructor() {
        playerPositions = new ArrayList<>();
        money = new ArrayList<>();
        positionOwners = new ArrayList<>();
        stopFieldFlags = new ArrayList<>();
    }

    private <T> void initNumberArrays(ArrayList<T> array, int size, T value) {
        for (int i = 0; i < size; i++) {
            array.add(value);
        }
    }

    public static GameState getInstance(){
        if (instance == null) {
            instance = new GameState();
        }
        return instance;
    }

    public void updatePlayerPosition(MovePawnBody body){
        if(body.getField()>= NUMBER_OF_FIELDS){
            body.setField(body.getField()% NUMBER_OF_FIELDS);
            changeMoney(body.getPlayerId(), 3);
        }
        if(body.getField() == STOP_FIELD_INDEX){
            stopFieldFlags.set(body.getPlayerId(),true);
        }
        if(body.getField() == INNOVATION_FIELD_INDEX){
            changeMoney(body.getPlayerId(), new Random().nextInt(-5,6));
        }
        playerPositions.set(body.getPlayerId(),body.getField());
        for(int i=0;i< positionOwners.size();i++){
            if(playerPositions.get(body.getPlayerId())==i && positionOwners.get(i)!=-1 && positionOwners.get(i)!=body.getPlayerId()){
                int punishment = GameState.getInstance().getFieldPunishments().get(i);
                changeMoney(body.getPlayerId(), -1*punishment);
                changeMoney(positionOwners.get(i), punishment);
            }
        }
    }

    public void displayGameState(){
        for(int i=0;i<playerPositions.size(); ++i){
            System.out.println("ID: " + i + " POS: " + playerPositions.get(i));
        }
    }

    public ArrayList<Integer> getMoney() {
        return money;
    }

    public void changeMoney(int id, int value){
        int before = GameState.getInstance().getMoney().get(id);
        GameState.getInstance().getMoney().set(id,before + value);
    }

    public ArrayList<Integer> getPositionOwners() {
        return positionOwners;
    }

    public ArrayList<Integer> getFieldPrices() {
        return fieldPrices;
    }

    public ArrayList<String> getNames() {
        return names;
    }

    public ArrayList<Integer> getFieldPunishments() {
        return fieldPunishments;
    }

    public ArrayList<Integer> getPlayerPositions() {
        return playerPositions;
    }

    public int getPlayerId() {
        return playerId;
    }

    public void setPlayerId(int playerId) {
        this.playerId = playerId;
    }

    public int getWhoseTurn() {
        return whoseTurn;
    }

    public void endTurn(){
        whoseTurn++;
        if(hasToSkipTurn()){
            whoseTurn++;
            stopFieldFlags.set(whoseTurn-2,false);
        }
        if(whoseTurn==playerId+1) whoseTurn=1;
    }

    private Boolean hasToSkipTurn() {
        return stopFieldFlags.get(whoseTurn - 1);
    }

}
