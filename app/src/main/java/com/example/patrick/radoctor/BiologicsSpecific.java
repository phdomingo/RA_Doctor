package com.example.patrick.radoctor;

/**
 * Created by Patrick on 12/3/2015.
 */
public class BiologicsSpecific {
     private int dose, inf;
     private String lastUpdated;

     public BiologicsSpecific(int dose, int inf, String lastUpdated){
          this.dose = dose;
          this.inf = inf;
          this.lastUpdated = lastUpdated;
     }
     public int getDose(){
          return dose;
     }
     public int getInf(){
          return inf;
     }
     public String getLastUpdated(){
          return lastUpdated;
     }
}
