package com.anchal.nexus.model;

public class Wallet {

    private double value;

    public Wallet(double value) {
        this.value = value;
    }

    public void earnMoney(double value) {
        this.value += value;
    }

    public void spendMoney(double value) {
        this.value -= value;
    }

    public double getValue() {
        return this.value;
    }


}
