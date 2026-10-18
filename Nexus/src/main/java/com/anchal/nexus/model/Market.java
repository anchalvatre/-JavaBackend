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
        System.out.println("entering method purchasae asset");
        if (asset == null)
            throw new IllegalArgumentException("asset is null");
        synchronized (asset) {
            boolean sellAsset = false, userTransfer = false;
            User seller = asset.getOwner();
            validate(asset, buyer, seller);
            try {
                User.transfer(buyer, seller, asset);
                userTransfer = true;
                seller.sellAsset(asset);
                sellAsset = true;
                buyer.purchaseAsset(asset);
                // throw new RuntimeException("exceptio naa gyi ");
            } catch (RuntimeException re) {
                if (buyer.getAllAsset().contains(asset))
                    buyer.sellAsset(asset);
                if (asset.getOwnerId().equals(buyer.getUserId()))
                    asset.updateOwner(seller);
                if (sellAsset)
                    seller.addAsset(asset);
                if (userTransfer)
                    User.transfer(seller, buyer, asset);
                throw re;
            }
        }
    }

    public void validate(Asset asset, User buyer, User seller) {

        if (seller == null)
            throw new IllegalCallerException("curent owner is null");
        if (buyer == null)
            throw new IllegalArgumentException("newOwner null");
        if (buyer.getWalletValue().compareTo(asset.getValue()) < 0)
            throw new IllegalArgumentException("buyer is poor");
        if (!seller.getAllAsset().contains(asset))
            throw new IllegalArgumentException("seller is not owner");
        if (seller.getUserId().equals(buyer.getUserId()))
            throw new IllegalCallerException("currentOwner is new Owner");
        if (!getAllAssetInMarket().contains(asset))
            throw new IllegalArgumentException("asset is not in market");
    }
}
