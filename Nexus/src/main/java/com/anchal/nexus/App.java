package com.anchal.nexus;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.anchal.nexus.model.Asset;
import com.anchal.nexus.model.Market;
import com.anchal.nexus.model.User;
import com.anchal.nexus.model.Wallet;

/**
 * Hello world!
 *
 */
public class App {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Hello World!");
        Wallet wallet = new Wallet(1000d);
        Wallet walletMahek = new Wallet(1000d);
        User anchal = new User("anchal", wallet);
        User mahek = new User("makek", walletMahek);
        Asset house = new Asset("hosue", 100d, anchal);
        anchal.addAsset(house);
        boolean checkMarket = false;
        int count = 1;
        System.out.println(anchal);
        anchal.addInMarket(house);
        System.out.println(mahek);
        System.out.println("wanna check market ");
        checkMarket = sc.nextBoolean();
        if (checkMarket) {
            for (Asset asset : Market.getAllAssetInMarket()) {
                System.out.println(count + " : " + asset);
                count++;
            }
            List<Asset> marketAssetList = new ArrayList<>(Market.getAllAssetInMarket());
            System.out.println("select from above list " + marketAssetList);
            Asset selectedAsset = marketAssetList.get(sc.nextInt() - 1);
            Market.purchaseAsset(selectedAsset, mahek);
            System.out.println(mahek);
            System.out.println(anchal);

        }

        anchal.addInMarket(house);
        sc.close();
    }
}
