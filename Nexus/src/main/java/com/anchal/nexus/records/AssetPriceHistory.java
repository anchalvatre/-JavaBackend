package com.anchal.nexus.records;

import java.math.BigDecimal;
import java.time.Instant;

public record AssetPriceHistory(Instant priceChangeInstant, BigDecimal oldPrice, BigDecimal newPrice) {
    public AssetPriceHistory {
        if(oldPrice == null) throw new IllegalArgumentException("oldprice is null");
        if(newPrice == null) throw new IllegalArgumentException("newPrice is null");
        if(newPrice.compareTo(BigDecimal.ZERO) < 0) throw new IllegalArgumentException("newPrice is -ve");
    }
}
