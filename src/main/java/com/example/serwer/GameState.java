package com.example.serwer;

import com.example.serwer.body.MovePawnBody;
import com.example.serwer.controllers.ExitGame;

import java.util.*;

public class GameState {
    public static final int NUMBER_OF_FIELDS = 24;
    public static final int INNOVATION_FIELD_INDEX = 18;
    public static final int STOP_FIELD_INDEX = 6;

    public static final int MAX_AMOUNT_PLAYERS = 4;
    private ArrayList<Integer> playerPositions;
    private ArrayList<Integer> money;
    private ArrayList<Integer> positionOwners;
    private ArrayList<Integer> fieldPrices;
    private ArrayList<Integer> fieldPunishments;
    private PunishmentInfo punishmentInfo;
    private ArrayList<String> names;
    private ArrayList<Boolean> stopFieldFlags;
    private ArrayList<Boolean> playerLostFlags;
    private ArrayList<Question> questions;
    private HashMap<String, ArrayList<Integer>> colorsOfFields;
    private int whoseTurn;
    private int playerId;

    private static volatile GameState instance = null;

    private GameState() {
        whoseTurn = 1;
        playerId = 0;
        punishmentInfo = new PunishmentInfo(-1, -1, -1, -1);
        arraysConstructor();
        initArrays(playerPositions, MAX_AMOUNT_PLAYERS, 0);
        initArrays(money, MAX_AMOUNT_PLAYERS, 30);
        initArrays(positionOwners, NUMBER_OF_FIELDS, -1);
        initArrays(stopFieldFlags, MAX_AMOUNT_PLAYERS, false);
        initArrays(playerLostFlags, MAX_AMOUNT_PLAYERS, false);
        initNames();
        initFieldPricesAndPunishments();
        initQuestions();
        initColorsOfFields();
    }


    private void initFieldPricesAndPunishments() {
        int firstMysteriousField = new Random().nextInt(6) + 1;
        int secondMysteriousField = new Random().nextInt(6) + 1;
        fieldPunishments = new ArrayList<>(Arrays.asList(0, 2, 2, 0, firstMysteriousField, secondMysteriousField, 0, 2, 3, 0, 3, 4, 0, 1, 4, 0, 5, 6, 0, 1, 0, 6, 8, 10));
        firstMysteriousField = new Random().nextInt(6) + 1;
        secondMysteriousField = new Random().nextInt(6) + 1;
        fieldPrices = new ArrayList<>(Arrays.asList(0, 2, 2, 0, firstMysteriousField, secondMysteriousField, 0, 2, 3, 0, 3, 4, 0, 1, 4, 0, 5, 6, 0, 1, 0, 6, 8, 10));
    }

    private void initColorsOfFields() {
        colorsOfFields = new HashMap<>();
        colorsOfFields.put("Orange", new ArrayList<>(List.of(1, 2)));
        colorsOfFields.put("Yellow", new ArrayList<>(List.of(4, 5)));
        colorsOfFields.put("Purple", new ArrayList<>(List.of(7, 8)));
        colorsOfFields.put("Green", new ArrayList<>(List.of(10, 11)));
        colorsOfFields.put("Blue", new ArrayList<>(List.of(14, 16, 17)));
        colorsOfFields.put("Red", new ArrayList<>(List.of(21, 22, 23)));
    }

    private void initNames() {
        names = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            names.add("");
        }
    }

    private void initQuestions() {
        questions = new ArrayList<>();
        questions.add(new Question("Liczba 6 zapisana bitowo to:", new ArrayList<String>(Arrays.asList("1100", "0110", "1001", "0011")), 1));
        questions.add(new Question("Liczba 1010 zapisana dziesiętnie to:", new ArrayList<String>(Arrays.asList("10", "11", "6", "8")), 0));
        questions.add(new Question("Ile Nilsen stworzył heurystyk ?", new ArrayList<String>(Arrays.asList("6", "12", "10", "8")), 2));
        questions.add(new Question("Ile instancji singletonu można stworzyć ?", new ArrayList<String>(Arrays.asList("8", "0", "niesk.", "1")), 3));
        questions.add(new Question("Która wartość jest największa ?", new ArrayList<String>(Arrays.asList("10 000MB", "1TB", "100GB", "10KB")), 1));
        questions.add(new Question("Urządzeniem której warstwy jest switch ?", new ArrayList<String>(Arrays.asList("3", "1", "5", "2")), 3));
        questions.add(new Question("W jaki sposób UNION łączy rekordy ?", new ArrayList<String>(Arrays.asList("Pionowo", "Poziomo", "Krzyżowo", "Okrężnie")), 0));
        questions.add(new Question("W jaki sposób JOIN łączy rekordy ?", new ArrayList<String>(Arrays.asList("Pionowo", "Poziomo", "Krzyżowo", "Okrężnie")), 1));
        questions.add(new Question("Ile jedynek jest w binarnym zapisie 17 ?", new ArrayList<String>(Arrays.asList("3", "0", "2", "1")), 2));
        questions.add(new Question("Ile ustawionych bitów ma maska 255.255.192.0 ?", new ArrayList<String>(Arrays.asList("16", "18", "20", "24")), 1));
    }

    private void arraysConstructor() {
        playerPositions = new ArrayList<>();
        money = new ArrayList<>();
        positionOwners = new ArrayList<>();
        stopFieldFlags = new ArrayList<>();
        playerLostFlags = new ArrayList<>();
    }

    private <T> void initArrays(ArrayList<T> array, int size, T value) {
        for (int i = 0; i < size; i++) {
            array.add(value);
        }
    }

    public static GameState getInstance() {
        if (instance == null) {
            instance = new GameState();
        }
        return instance;
    }

    public void updatePlayerPosition(MovePawnBody body) {
        setPunishmentInfo(-1, -1, -1, -1);
        if (body.getField() >= NUMBER_OF_FIELDS) {
            body.setField(body.getField() % NUMBER_OF_FIELDS);
            changeMoney(body.getPlayerId(), GameState.getInstance().getMoney().get(body.getPlayerId()) + 3);
        }
        if (body.getField() == STOP_FIELD_INDEX) {
            stopFieldFlags.set(body.getPlayerId(), true);
        }
        if (body.getField() == INNOVATION_FIELD_INDEX) {
            int cost;
            do {
                cost = new Random().nextInt(-5, 6);
            } while (cost == 0);
            changeMoney(body.getPlayerId(), GameState.getInstance().getMoney().get(body.getPlayerId()) + cost);
            setPunishmentInfo(-1, body.getPlayerId(), cost, 18);
        }
        playerPositions.set(body.getPlayerId(), body.getField());
        for (int fieldIndexCounter = 0; fieldIndexCounter < positionOwners.size(); fieldIndexCounter++) {
            if (hasSteppedIntoSomeonesField(body, fieldIndexCounter)) {

                int punishment = getPunishment(body, fieldIndexCounter);
                changeMoney(body.getPlayerId(), GameState.getInstance().getMoney().get(body.getPlayerId()) - punishment);
                changeMoney(positionOwners.get(fieldIndexCounter), GameState.getInstance().getMoney().get(positionOwners.get(fieldIndexCounter)) + punishment);
                setPunishmentInfo(body.getPlayerId(), positionOwners.get(fieldIndexCounter), punishment, fieldIndexCounter);
            }
        }
    }

    private int getPunishment(MovePawnBody body, int fieldIndex) {
        int exitFlag = 0;
        int punishment = GameState.getInstance().getFieldPunishments().get(fieldIndex);

        for (ArrayList<Integer> arrayOfFields : colorsOfFields.values()) {
            if (arrayOfFields.contains(body.getField())) {
                for (int fieldId : arrayOfFields) {
                    if (positionOwners.get(fieldId) != positionOwners.get(body.getField())) {
                        exitFlag = 1;
                        break;
                    }
                }
                if (exitFlag != 1)
                    punishment *= 2;
            }
        }
        return punishment;
    }

    private boolean hasSteppedIntoSomeonesField(MovePawnBody body, int fieldIndex) {
        return playerPositions.get(body.getPlayerId()) == fieldIndex && positionOwners.get(fieldIndex) != -1 && positionOwners.get(fieldIndex) != body.getPlayerId();
    }


    public ArrayList<Integer> getMoney() {
        return money;
    }

    public void changeMoney(int id, int value) {
        GameState.getInstance().getMoney().set(id, value);
        if (GameState.getInstance().getMoney().get(id) < 0) {
            playerLostFlags.set(id, true);
            if(value != ExitGame.PLAYER_EXITED_VALUE || GameState.getInstance().whoseTurn-1 == id){
                GameState.getInstance().endTurn();
            }
            for (int i = 0; i < NUMBER_OF_FIELDS; ++i) {
                if (positionOwners.get(i) == id) {
                    positionOwners.set(i, -1);
                }
            }
            if (Collections.frequency(playerLostFlags, true) == playerId - 1 && playerId > 1) {
                whoseTurn = 0;
            }
        }
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

    public ArrayList<Question> getQuestions() {
        return questions;
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

    public void endTurn() {
        whoseTurn++;
        if (whoseTurn == playerId + 1) {
            whoseTurn = 1;
        }
        while (playerLostFlags.get(whoseTurn - 1) || hasToSkipTurn(whoseTurn - 1) || (whoseTurn == playerId + 1)) {
            if (hasToSkipTurn(whoseTurn - 1)) {
                stopFieldFlags.set(whoseTurn - 1, false);
            }
            whoseTurn++;
            if (whoseTurn == playerId + 1) {
                whoseTurn = 1;
            }
        }
    }

    private Boolean hasToSkipTurn(int index) {
        return stopFieldFlags.get(index);
    }

    public ArrayList<Boolean> getPlayerLostFlags() {
        return playerLostFlags;
    }

    public PunishmentInfo getPunishmentInfo() {
        return punishmentInfo;
    }

    public void setPunishmentInfo(int payerId, int payeeId, int cost, int field) {
        this.punishmentInfo.setPayerId(payerId);
        this.punishmentInfo.setPayeeId(payeeId);
        this.punishmentInfo.setCost(cost);
        this.punishmentInfo.setField(field);
    }

    public void checkAllSpecialFields() {
        if (positionOwners.get(13) == positionOwners.get(19) && positionOwners.get(19) != -1) {
            fieldPunishments.set(13, 5);
            fieldPunishments.set(19, 5);
        }
    }

    public void changeMoneyWithHouse(int fieldNumber, int playerID) {
        changeMoney(playerID, money.get(playerID) - fieldPrices.get(fieldNumber));
    }

    public void changeMoneyForQuiz(int playerId){
        changeMoney(playerId,money.get(playerId) + 4);

    }
}
