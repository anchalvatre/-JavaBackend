package com.anchal.nexus.records;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

public record Ledger(UUID seller, UUID buyer, BigDecimal sellingPrice, String assetName, Instant instant) {

    public Ledger {
        if(seller == null) throw new IllegalArgumentException();
        if(buyer == null) throw new IllegalArgumentException();
        if(sellingPrice == null) throw new IllegalArgumentException();
        if(seller.equals(buyer)) throw new IllegalArgumentException();
    }
}
