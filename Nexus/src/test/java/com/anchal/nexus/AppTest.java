package com.anchal.nexus;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;

import org.junit.Test;

import com.anchal.nexus.model.Asset;
import com.anchal.nexus.model.Market;
import com.anchal.nexus.model.User;

/**
 * Unit test for simple App.
 */
public class AppTest {
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue() {
        assertTrue(true);

    }

    @Test
    public void buySellAssetTest() {
        User anchal = new User("anchal", BigDecimal.valueOf(10000));
        User mahek = new User("makek", BigDecimal.valueOf(10000));
        Asset house = new Asset("hosue", BigDecimal.valueOf(5000), anchal);
        // anchal.addInMarket(house);
        // Market.purchaseAsset(house, mahek);
        assertEquals(BigDecimal.valueOf(5000), anchal.getWalletValue());
        assertEquals(BigDecimal.valueOf(5000), mahek.getWalletValue());
        assertEquals(mahek.getUserId(), house.getOwnerId());
        assertFalse(anchal.getAllAsset().contains(house));
        assertTrue(mahek.getAllAsset().contains(house));
        // assertFalse(Market.getAllAssetInMarket().contains(house));

    }
}
