package com.anchal.nexus.model;

import java.math.BigDecimal;

public class Wallet {

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

}
