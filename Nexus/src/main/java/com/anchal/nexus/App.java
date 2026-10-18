package com.anchal.nexus;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;
import com.anchal.nexus.records.Ledger;
import com.anchal.nexus.model.Asset;
import com.anchal.nexus.model.Market;
import com.anchal.nexus.model.User;

/**
 * Hello world!
 *
 */
public class App {
    static int a = 10;
    public static void main(String[] args) {
        //
        // test1 t1 = new test1();
        // test t = new test();
        //
        // Thread tt = new Thread(() -> {
        // synchronized(t1) {
        // }
        // });
        // t.start();

        List<Ledger> ledger = new ArrayList<Ledger>();
        Market market = new Market();
        System.out.println("Hello World!");
        User anchal = new User("anchal", BigDecimal.valueOf(10000));
        User mahek = new User("makek", BigDecimal.valueOf(10000));
        Asset house = new Asset("hosue", BigDecimal.valueOf(5000), anchal);
        System.out.println(anchal);
        anchal.addInMarket(house, market);
        Scanner sc = new Scanner(System.in);
        System.out.println(mahek);
        System.out.println("wanna check market ");
        boolean checkMarket = false;
        checkMarket = sc.nextBoolean();
        int count = 1;
        if (checkMarket) {
            for (Asset asset : market.getAllAssetInMarket()) {
                System.out.println(count + " : " + asset);
                count++;
            }
            List<Asset> marketAssetList = new ArrayList<>(market.getAllAssetInMarket());
            System.out.println("select from above list " + marketAssetList);
            Asset selectedAsset = marketAssetList.get(sc.nextInt() - 1);
            UUID ownerId = house.getOwnerId();
            BigDecimal assetPrice = house.getValue();
            market.purchaseAsset(selectedAsset, mahek);
            ledger.add(new Ledger(ownerId, mahek.getUserId(),assetPrice,house.getName(), Instant.now()));
            System.out.println(mahek);
            System.out.println(anchal);
            System.out.println(house.toString());
        }
        sc.close();
    }
}

class test1 {
    public void te() {
        System.out.println("test1");
        // user this for multi user run
        // new Thread(() -> {
        // Scanner sc = new Scanner(System.in);
        // System.out.println(mahek);
        // System.out.println("wanna check market ");
        // boolean checkMarket = false;
        // checkMarket = sc.nextBoolean();
        // int count = 1;
        // if (checkMarket) {
        // for (Asset asset : market.getAllAssetInMarket()) {
        // System.out.println(count + " : " + asset);
        // count++;
        // }
        // List<Asset> marketAssetList = new ArrayList<>(market.getAllAssetInMarket());
        // System.out.println("select from above list " + marketAssetList);
        // Asset selectedAsset = marketAssetList.get(sc.nextInt() - 1);
        // market.purchaseAsset(selectedAsset, mahek);
        // System.out.println(mahek);
        // System.out.println(anchal);
        // System.out.println(house.toString());
        // }
        // sc.close();
        // }).start();
        // new Thread(() -> {
        // Scanner sc = new Scanner(System.in);
        // System.out.println(mahek);
        // System.out.println("wanna check market ");
        // boolean checkMarket = false;
        // checkMarket = sc.nextBoolean();
        // int count = 1;
        // if (checkMarket) {
        // for (Asset asset : market.getAllAssetInMarket()) {
        // System.out.println(count + " : " + asset);
        // count++;
        // }
        // List<Asset> marketAssetList = new ArrayList<>(market.getAllAssetInMarket());
        // System.out.println("select from above list " + marketAssetList);
        // Asset selectedAsset = marketAssetList.get(sc.nextInt() - 1);
        // market.purchaseAsset(selectedAsset, mahek);
        // System.out.println(mahek);
        // System.out.println(anchal);
        // System.out.println(house.toString());
        // }
        // sc.close();
        // }).start();
    }
}

class test {
    public void tt() {
        System.out.println("est1");

    }
}
