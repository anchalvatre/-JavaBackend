package com.anchal.nexus.model;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class User {

    private String name;
    private UUID userId;
    private Wallet wallet;
    private Set<Asset> assets = new HashSet<>();

    public User(String name, Wallet wallet) {
        if (name == null || name.isEmpty())
            throw new IllegalAccessError();
        this.name = name;
        this.userId = UUID.randomUUID();
        this.wallet = wallet;
    }

    public UUID getUserId() {
        return userId;
    }

    public void addAsset(Asset newAsset) {
        assets.add(newAsset);
        newAsset.updateOwner(this);
    }

    public void removeAsset(Asset newAsset) {
        assets.remove(newAsset);
    }

    public Wallet getWallet() {
        return wallet;
    }

    public Set<Asset> getAllAsset() {
        return Set.copyOf(assets);
    }

    public void addInMarket(Asset asset) {
        if(!userId.equals(asset.getOwnerId()))
                throw new IllegalArgumentException("asset does not belong to user");
        Market.addInMarket(asset);
    }

    public void removeFromMarket(Asset asset) {
        Market.removeFromMarket(asset);
    }

    @Override
    public String toString() {
        return "name " + name + " : " + wallet.getValue() + " " + assets.size();
    }
}
