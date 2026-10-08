package com.anchal.nexus;

import com.anchal.nexus.model.User;
import com.anchal.nexus.model.Wallet;
import com.anchal.nexus.model.Money;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        Money money = new Money(1000d);
        Wallet wallet = new Wallet(money);
        User user = new User("anchal", wallet);

        System.out.println(user);
    }
}
