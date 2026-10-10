package com.anchal.nexus.model;

import java.util.LinkedHashSet;
import java.util.Set;

public class Market {

    private static Set<Asset> assetsInMarket = new LinkedHashSet<>();

    public static void addInMarket(Asset asset) {
        
        if (asset == null) 
            throw new IllegalArgumentException("asset is null");
        assetsInMarket.add(asset);
    }

    public static void removeFromMarket(Asset asset) {
        if (asset == null)
            throw new IllegalArgumentException("asset is null");
        if (!assetsInMarket.contains(asset))
            throw new IllegalArgumentException("asset is not there in market plz add it first");
        assetsInMarket.remove(asset);
    }

    public static Set<Asset> getAllAssetInMarket() {
        return Set.copyOf(assetsInMarket);
    }

    public static void sellAsset(Asset asset) {
        User currentOwner = asset.getOwner();
        System.out.println(currentOwner + " : user " + currentOwner.getWallet().getValue());
        currentOwner.getWallet().earnMoney(asset.getValue());
        currentOwner.removeAsset(asset);
        System.out.println(currentOwner + " : user ----------- " + currentOwner.getWallet().getValue());
    }

    public static void purchaseAsset(Asset asset, User newOwner) {

        System.out.println("enter purchasae asset");
        if (newOwner == null)
            throw new IllegalArgumentException("newOwner null");
        if (asset.getOwnerId().equals(newOwner.getUserId()))
            throw new IllegalArgumentException("new and currentOwner are smae");
        if (newOwner.getWallet().getValue() < asset.getValue())
            throw new IllegalArgumentException("new owner is poor ");
        if(!Market.getAllAssetInMarket().contains(asset)) 
            throw new IllegalArgumentException("asset is not in market");
        sellAsset(asset);
        asset.updateOwner(newOwner);
        newOwner.addAsset(asset);
        newOwner.getWallet().spendMoney(asset.getValue());
        newOwner.removeFromMarket(asset);

    }

}
