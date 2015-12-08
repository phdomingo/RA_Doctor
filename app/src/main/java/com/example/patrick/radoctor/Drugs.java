package com.example.patrick.radoctor;

/**
 * Created by Patrick on 12/3/2015.
 */
public class Drugs {
     private int patient_id;
     private boolean hasMtx, hasAza, hasHcqs, hasSterPred, hasSterMeth;
     DrugsSpecific mtx, aza, hcqs, sterPred, sterMeth;
     public Drugs (){
          hasMtx = false;
          hasAza = false;
          hasHcqs = false;
          hasSterPred = false;
          hasSterMeth = false;
     }
     public void setPatient_id(int patient_id){
          this.patient_id = patient_id;
     }
     public void setMtx(String date, String admission, double dose){
          mtx = new DrugsSpecific(date,admission,dose);
          hasMtx = true;
     }
     public void setAza(String date, String admission, double dose){
          aza = new DrugsSpecific(date,admission,dose);
          hasAza = true;
     }
     public void setHcqs(String date, String admission, double dose){
          hcqs = new DrugsSpecific(date,admission,dose);
          hasHcqs = true;
     }
     public void setSterPred(String date, String admission, double dose){
          sterPred = new DrugsSpecific(date,admission,dose);
          hasSterPred = true;
     }
     public void setSterMeth(String date, String admission, double dose){
          sterMeth = new DrugsSpecific(date,admission,dose);
          hasSterMeth = true;
     }

     public int getPatient_id(){
          return patient_id;
     }
     public boolean isHasMtx(){
          return hasMtx;
     }
     public boolean isHasMAza(){
          return hasAza;
     }
     public boolean isHasHcqs(){
          return hasHcqs;
     }
     public boolean isHasSterPred(){
          return hasSterPred;
     }
     public boolean isHasSterMeth(){
          return hasSterMeth;
     }
     public DrugsSpecific getMtx(){
          return mtx;
     }
     public DrugsSpecific getAza(){
          return aza;
     }
     public DrugsSpecific getHcqs(){
          return hcqs;
     }
     public DrugsSpecific getSterPred(){
          return sterPred;
     }
     public DrugsSpecific getSterMeth(){
          return sterMeth;
     }

}
