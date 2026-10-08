package com.anchal.nexus.model;

public class Money {

    private Double value;
    
    public Money (Double value) {
        if(value < 0 ) throw new IllegalStateException("money cannot be -ve");
        this.value = value;
    }

    public void earnMoney(Double value) {
        if(value < 0) throw new IllegalArgumentException("vlaue cannot be -ve");
        this.value += value;
    }

    public void spendMoney(Double value) {
        if(value < 0) throw new IllegalArgumentException("vlaue cannot be -ve");
        if(this.value - value < 0) throw new IllegalArgumentException("price too high ");
        this.value -= value;
    }

    public void withdrawMoney(Double value) {
        if(value < 0) throw new IllegalArgumentException("vlaue cannot be -ve");
        if(value > this.value) throw new IllegalArgumentException("no money in account");
        this.value -= value;
    }

}
