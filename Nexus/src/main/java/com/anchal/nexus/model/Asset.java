package com.anchal.nexus.model;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.swing.text.html.Option;

import com.anchal.nexus.records.AssetPriceHistory;

public class Asset {

    private String name;
    private BigDecimal value;
    private User owner;
    private List<AssetPriceHistory> priceHistory = new ArrayList<>();

    public Asset(String name, BigDecimal value, User owner) {
        if (value == null || value.compareTo(BigDecimal.ZERO) < 0)
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

    public synchronized void updatevalue(User caller, BigDecimal value) {
        if(caller == null) throw new IllegalArgumentException("caller is null");
        if(!this.owner.getUserId().equals(caller.getUserId())) throw new IllegalArgumentException("asset udpate value - caller is not owner ");
        if (value == null || value.compareTo(BigDecimal.ZERO) < 0)
            throw new IllegalArgumentException("vlue is negative or null");
        BigDecimal oldPrice = this.value;
        this.value = value;
        priceHistory.add(new AssetPriceHistory(Instant.now(), oldPrice, this.value));
    }

    public UUID getOwnerId() {
        return owner.getUserId();
    }

    public BigDecimal getValue() {
        return value;
    }

    synchronized void updateOwner(User newOwner) {
        this.owner = newOwner;
    }

    User getOwner() {
        return owner;
        // return new User(owner.getName(), owner.getWallet());
    }

    public String getName() {
        return name;
    }

    public List<AssetPriceHistory> getPriceHistory() {
        return Collections.unmodifiableList(priceHistory);
    }

    public Optional<BigDecimal> getLastValidPrice() {
        if(priceHistory.isEmpty() || priceHistory == null) throw new NullPointerException("history is null");
        BigDecimal result = priceHistory.get(priceHistory.size() - 1).oldPrice();
        return Optional.of(result);

    }

    public String toString() {
        return name + " owner : " + owner.getName();
    }
}
