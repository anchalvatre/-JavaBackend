package com.anchal.nexus.model;

import java.util.UUID;

public class Asset {

   private String name;
   private double value;
   private User owner;

   public Asset(String name, double value, User owner) {
       this.name = name;
       this.value = value;
       this.owner = owner;
   }

   public UUID getOwnerId() {
      return owner.getUserId();
   }

   public double getValue() {
       return value;
   }

   public void updateOwner(User newOwner) {
       this.owner = newOwner;
   }

   public User getOwner() {
       return owner;
   }
   
   public String toString() {
       return name;
   }
}
