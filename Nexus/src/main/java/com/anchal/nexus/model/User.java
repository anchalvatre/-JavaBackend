package com.anchal.nexus.model;
import java.util.UUID;

public class User {
    
    private String name;
    private UUID userId;
    private Wallet wallet;

    public User(String name, Wallet wallet){
        if(name == null || name.isEmpty()) throw new IllegalAccessError(); 
        this.name = name;
        this.userId = UUID.randomUUID();
        this.wallet = wallet; 
    }

}
