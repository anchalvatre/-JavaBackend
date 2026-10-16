package com.anchal.nexus;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.anchal.nexus.model.Asset;
import com.anchal.nexus.model.Market;
import com.anchal.nexus.model.User;


/**
 * Hello world!
 *
 */
public class App {
    public static void main(String[] args) {
        //
        // test1 t1 = new test1();
        // test t = new test();
        //
        // Thread tt = new Thread(() -> {
        //     synchronized(t1) {
        //     }
        // });
        // t.start();

        Market market = new Market();
        Scanner sc = new Scanner(System.in);
        System.out.println("Hello World!");
        User anchal = new User("anchal", BigDecimal.valueOf(10000));
        User mahek = new User("makek", BigDecimal.valueOf(10000));
        Asset house = new Asset("hosue", BigDecimal.valueOf(5000), anchal);
        boolean checkMarket = false;
        int count = 1;
        System.out.println(anchal);
        anchal.addInMarket(house, market);
        System.out.println(mahek);
        System.out.println("wanna check market ");
        checkMarket = sc.nextBoolean();
        if (checkMarket) {
            for (Asset asset : market.getAllAssetInMarket()){
                System.out.println(count + " : " + asset);
                count++;
            }
            List<Asset> marketAssetList = new ArrayList<>(market.getAllAssetInMarket());
            System.out.println("select from above list " + marketAssetList);
            Asset selectedAsset = marketAssetList.get(sc.nextInt() - 1);
            market.purchaseAsset(selectedAsset, mahek);
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
    }
}
 class test {
     public void tt() {
        System.out.println("est1");

    }
}
