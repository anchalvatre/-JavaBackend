package com.anchal.nexus.model;

public class Wallet {
    // private UUID walletId;
    private Money money; 

    public Wallet(Money money){
        // this.walletId = UUID.randomUUID();
        this.money = money;
    }

}
