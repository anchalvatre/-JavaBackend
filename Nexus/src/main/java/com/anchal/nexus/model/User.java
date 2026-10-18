package com.anchal.nexus.model;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class User {

    private String name;
    private UUID userId;
    private Wallet wallet;
    private Set<Asset> assets = new HashSet<>();

    public User(String name, BigDecimal value) {
        if (name == null || name.isEmpty())
            throw new IllegalArgumentException();
        if(value.doubleValue() < 0) throw new IllegalArgumentException("value is negateive " );
        this.name = name;
        this.userId = UUID.randomUUID();
        this.wallet = new Wallet(value);
    }

    public BigDecimal getWalletValue() {
        return wallet.getValue();
    }

    public String getName() {
        return name;
    }

    public UUID getUserId() {
        return userId;
    }

    void sellAsset(Asset newAsset) {
        if (!assets.contains(newAsset))
            throw new IllegalArgumentException("asset not present ");
        assets.remove(newAsset);
    }

    void addAsset(Asset asset) {
        assets.add(asset);
    }

    static void transfer(User buyer, User seller, Asset asset) {
        Wallet.transfer(buyer.wallet, seller.wallet, asset);
    }

    void purchaseAsset(Asset asset) {
        assets.add(asset);
        asset.updateOwner(this);
    }

    public Set<Asset> getAllAsset() {
        return Set.copyOf(assets);
    }

    public void addInMarket(Asset asset, Market market) {
        if (!userId.equals(asset.getOwnerId()))
            throw new IllegalArgumentException("asset does not belong to user");
        market.addInMarket(asset);
    }

    public void removeFromMarket(Asset asset, Market market) {
        if (!userId.equals(asset.getOwnerId()))
            throw new IllegalArgumentException("asset does not belong to user");
        market.removeFromMarket(asset);
    }

    @Override
    public String toString() {
        return "name " + name + " : " + wallet.getValue() + " " + assets.size();
    }
}
