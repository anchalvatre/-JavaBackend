package com.anchal.nexus.model;

import java.util.LinkedHashSet;
import java.util.Set;

public class Market {

    private Set<Asset> assetsInMarket = new LinkedHashSet<>();

    void addInMarket(Asset asset) {
        if (asset == null)
            throw new IllegalArgumentException("asset is null");
        assetsInMarket.add(asset);
    }

    void removeFromMarket(Asset asset) {
        if (asset == null)
            throw new IllegalArgumentException("asset is null");
        if (!assetsInMarket.contains(asset))
            throw new IllegalArgumentException("asset is not there in market plz add it first");
        assetsInMarket.remove(asset);
    }

    public Set<Asset> getAllAssetInMarket() {
        return Set.copyOf(assetsInMarket);
    }

    public void purchaseAsset(Asset asset, User buyer) {

        System.out.println("enter purchasae asset");
        if (asset == null)
            throw new IllegalArgumentException("asset is null");
        synchronized (asset) {
            User seller = asset.getOwner();
            validate(asset, buyer, seller);
            User.transfer(buyer, seller, asset);
            seller.sellAsset(asset);
            buyer.purchaseAsset(asset);
        }

    }

    public void validate(Asset asset, User buyer, User seller) {

        if (seller == null)
            throw new IllegalCallerException("curent owner is null");
        if (buyer == null)
            throw new IllegalArgumentException("newOwner null");
        if (seller.getUserId().equals(buyer.getUserId()))
            throw new IllegalCallerException("currentOwner is new Owner");
        if (!getAllAssetInMarket().contains(asset))
            throw new IllegalArgumentException("asset is not in market");
    }
}
