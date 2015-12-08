package com.example.patrick.radoctor;

/**
 * Created by Patrick on 12/3/2015.
 */
public class Biologics {
     private BiologicsSpecific inflix,inflix2,ritux,tocili,etaner;
     private boolean hasInflix, hasInflix2, hasRitux, hasTocili, hasEtaner;

     public Biologics(){
          hasInflix = false;
          hasInflix2 = false;
          hasRitux = false;
          hasTocili = false;
          hasRitux = false;
     }

     public void setInflix(int dose, int inf, String date){
          inflix = new BiologicsSpecific(dose,inf,date);
          hasInflix = true;
     }
     public void setInflix2(int dose, int inf, String date){
          inflix2 = new BiologicsSpecific(dose,inf,date);
          hasInflix2 = true;
     }
     public void setRitux(int dose, int inf, String date){
          ritux = new BiologicsSpecific(dose,inf,date);
          hasRitux = true;
     }
     public void setTocili(int dose, int inf, String date){
          tocili = new BiologicsSpecific(dose,inf,date);
          hasTocili = true;
     }
     public void setEtaner(int dose, int inf, String date){
          etaner = new BiologicsSpecific(dose,inf,date);
          hasEtaner = true;
     }

     public boolean isHasInflix() {
          return hasInflix;
     }
     public boolean isHasInflix2() {
          return hasInflix2;
     }
     public boolean isHasRitux() {
          return hasRitux;
     }public boolean isHasTocili() {
          return hasTocili;
     }
     public boolean isHasEtaner() {
          return hasEtaner;
     }
     public BiologicsSpecific getInflix(){
          return inflix;
     }
     public BiologicsSpecific getInflix2(){
          return inflix2;
     }
     public BiologicsSpecific getRitux(){
          return ritux;
     }
     public BiologicsSpecific getTocili(){
          return tocili;
     }
     public BiologicsSpecific getEtaner(){
          return etaner;
     }
}
