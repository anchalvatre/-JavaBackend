package com.anchal.nexus.model;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import com.anchal.nexus.utility.Utility;

public class User {

    private String userName;
    private UUID userId;
    private Wallet wallet;
    private Set<Asset> assets = new HashSet<>();
    private Utility utility = new Utility();

    public User(UUID userId, String userName, Wallet wallet) {
        if (userName == null || userName.isEmpty())
            throw new IllegalArgumentException();
        if(userId == null) throw new IllegalArgumentException("user is not present in csv");
        if(wallet == null) throw new IllegalArgumentException("wallet is null");
        this.userId =userId;
        this.userName = userName;
        this.wallet = wallet;
    }


    public User(String userName, BigDecimal value) throws IOException {
        if (userName == null || userName.isEmpty())
            throw new IllegalArgumentException();
        if(value.doubleValue() < 0) throw new IllegalArgumentException("value is negateive " );
        this.userName = userName;
        this.userId = UUID.randomUUID();
        this.wallet = new Wallet(value);
        System.out.println(userName + " username " + utility.findUserByUserName(userName));
        utility.createuser(this);
    }

    public BigDecimal getWalletValue() {
        return wallet.getValue();
    }

    public String getUserName() {
        return userName;
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

    public void retireAsset(Asset asset, Market market) {
        if(asset == null) throw new IllegalArgumentException("asset is null");
        if(!userId.equals(asset.getOwnerId())) throw new IllegalArgumentException("user is not owner");
        asset.retireAsset();
        removeFromMarket(asset, market);
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public String toString() {
        // return "name " + name + " : " + wallet.getValue() + " " + assets.size();
        return userId + ", " + userName + ", " + wallet.getWalletId(); 
    }
}
