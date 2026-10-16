package com.anchal.nexus.model;

import java.math.BigDecimal;

public class Wallet {

    private static final Object TRANSFER_LOCK = new Object();
    private BigDecimal value;

    public Wallet(BigDecimal value) {
        if (value == null)
            throw new IllegalArgumentException("value is null");
        if (value.compareTo(BigDecimal.ZERO) < 0)
            throw new IllegalArgumentException("value is negative");
        this.value = value;

    }

    public void earnMoney(BigDecimal value) {
        if (value == null)
            throw new IllegalArgumentException("value is null");
        if (value.compareTo(BigDecimal.ZERO) < 0)
            throw new IllegalArgumentException("value is negative ");
        this.value = this.value.add(value);
    }

    public void spendMoney(BigDecimal value) {
        if (value == null)
            throw new IllegalArgumentException("value is null");
        if (value.compareTo(BigDecimal.ZERO) < 0)
            throw new IllegalArgumentException("value is negative ");
        if (this.value.compareTo(value) < 0)
            throw new IllegalArgumentException("owner is poor");
        this.value = this.value.subtract(value);
    }

    public BigDecimal getValue() {
        return this.value;
    }

    static void transfer(Wallet buyerWallet, Wallet sellerWallet, Asset asset) {

        validateTransfer(buyerWallet, sellerWallet, asset);

        synchronized (TRANSFER_LOCK) {
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

}
