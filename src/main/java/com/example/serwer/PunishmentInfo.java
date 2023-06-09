package com.example.serwer;

public class PunishmentInfo {
    private int payerId;
    private int payeeId;
    private int cost;
    private int field;

    public PunishmentInfo(int payerId, int payeeId, int cost, int field){
        this.payerId = payerId;
        this.payeeId = payeeId;
        this.cost = cost;
        this.field = field;
    }

    public int getPayerId() {
        return payerId;
    }

    public void setPayerId(int payerId) {
        this.payerId = payerId;
    }

    public int getPayeeId() {
        return payeeId;
    }

    public void setPayeeId(int payeeId) {
        this.payeeId = payeeId;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public int getField() {
        return field;
    }

    public void setField(int field) {
        this.field = field;
    }
}
