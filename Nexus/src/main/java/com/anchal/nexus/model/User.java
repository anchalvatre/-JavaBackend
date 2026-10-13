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
        wallet.earnMoney(newAsset.getValue());
    }

    void addAsset(Asset asset) {
        assets.add(asset);
    }

    void purchseAsset(Asset newAsset, User currentOwner) {
        
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

    public void addInMarket(Asset asset) {
        if (!userId.equals(asset.getOwnerId()))
            throw new IllegalArgumentException("asset does not belong to user");
        Market.addInMarket(asset);
    }

    public void removeFromMarket(Asset asset) {
        if (!userId.equals(asset.getOwnerId()))
            throw new IllegalArgumentException("asset does not belong to user");
        Market.removeFromMarket(asset);
    }

    @Override
    public String toString() {
        return "name " + name + " : " + wallet.getValue() + " " + assets.size();
    }
}
