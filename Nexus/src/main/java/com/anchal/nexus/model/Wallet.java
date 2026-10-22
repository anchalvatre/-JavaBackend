package com.anchal.nexus.model;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.UUID;

import com.anchal.nexus.utility.Utility;

public class Wallet {

    private static final Object TRANSFER_LOCK = new Object();
    private BigDecimal value;
    private UUID walletId;
    private Utility utility = new Utility();

    public Wallet(UUID walletId, BigDecimal value) {
        if (value == null)
            throw new IllegalArgumentException("value is null");
        if (value.compareTo(BigDecimal.ZERO) < 0)
            throw new IllegalArgumentException("value is negative");
        if (walletId == null)
            throw new IllegalArgumentException("walletId is null");
        this.walletId = walletId;
        this.value = value;
    }

    public UUID getWalletId() {
        return this.walletId;
    }

    public Wallet(BigDecimal value) throws IOException {
        if (value == null)
            throw new IllegalArgumentException("value is null");
        if (value.compareTo(BigDecimal.ZERO) < 0)
            throw new IllegalArgumentException("value is negative");
        this.value = value;
        this.walletId = UUID.randomUUID();
        utility.createWalletCSV(this);
    }

    public BigDecimal getValue() {
        return this.value;
    }

    static void transfer(Wallet buyerWallet, Wallet sellerWallet, Asset asset) {
        synchronized (TRANSFER_LOCK) {
            validateTransfer(buyerWallet, sellerWallet, asset);
            BigDecimal buyersNewBalance = buyerWallet.value.subtract(asset.getValue());
            BigDecimal sellerNewBalance = sellerWallet.value.add(asset.getValue());
            buyerWallet.value = buyersNewBalance;
            sellerWallet.value = sellerNewBalance;
        }
    }

    static void validateTransfer(Wallet buyerWallet, Wallet sellerWallet, Asset asset) {
        if (asset == null)
            throw new IllegalArgumentException("aset is null");
        if (buyerWallet.value.compareTo(asset.getValue()) < 0)
            throw new IllegalArgumentException("buyer is poor");
    }

    @Override
    public String toString() {
        return walletId + ", " + value;
    }

}
