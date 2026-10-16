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

    void earnMoneyAndSellAsset(BigDecimal value, User newOwenr, Asset asset) {
        if(this.getUserId().equals(newOwenr.getUserId()))
            throw new IllegalArgumentException("invalid newOnewr");
        if(assets.contains(asset)) throw new IllegalArgumentException("asset not present of current owenr");
        wallet.earnMoney(value);
        assets.remove(asset);
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

    void purchaseAsset(Asset newAsset, User currentOwner) {
        
        BigDecimal newOwnerWalletValue = getWalletValue();

        if (newAsset == null) throw new IllegalArgumentException("new asset is null");
        if  (!assets.contains(newAsset))
            throw new IllegalArgumentException("asset not present ");
        if (wallet.getValue().compareTo(newAsset.getValue()) < 0)
            throw new IllegalArgumentException("new owner is poor ");
        // currentowner lose ownership
        currentOwner.assets.remove(newAsset);
        currentOwner.wallet.earnMoney(newAsset.getValue());
        System.out.println(currentOwner.getWalletValue() +  "currentOwner wallet ");
 
        //new owner spend mony and take ownership
        if (wallet.getValue().compareTo(newAsset.getValue()) < 0)
            throw new IllegalArgumentException("new owner is poor ");
        newOwnerWalletValue = newOwnerWalletValue.subtract(newAsset.getValue()); 
        System.out.println("value after udpate is " + getWalletValue());
        // wallet.spendMoney(newAsset.getValue());
        assets.add(newAsset);
        newAsset.updateOwner(this);
        this.wallet.spendMoney(newAsset.getValue());
    
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
