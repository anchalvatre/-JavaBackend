package com.anchal.nexus.model;

import java.math.BigDecimal;
import java.util.UUID;

public class Asset {

    private String name;
    private BigDecimal value;
    private User owner;

    public Asset(String name, BigDecimal value, User owner) {
        if (value.doubleValue() < 0)
            throw new IllegalArgumentException("value is negatve");
        if (name == null || name.isEmpty() || name.isBlank())
            throw new IllegalArgumentException("name is null");
        if (owner == null)
            throw new IllegalArgumentException("owner is null");
        this.name = name;
        this.value = value;
        this.owner = owner;
        owner.addAsset(this);
    }

    public void updatevalue(BigDecimal value) {
        if (value.compareTo(BigDecimal.ZERO) < 0)
            throw new IllegalArgumentException("vlue is negative ");
        this.value = value;
    }

    public UUID getOwnerId() {
        return owner.getUserId();
    }

    public BigDecimal getValue() {
        return value;
    }

    void updateOwner(User newOwner) {
        this.owner = newOwner;
    }

    User getOwner() {
        return owner;
        // return new User(owner.getName(), owner.getWallet());
    }

    public String getName() {
        return name;
    }

    public String toString() {
        return name;
    }
}
