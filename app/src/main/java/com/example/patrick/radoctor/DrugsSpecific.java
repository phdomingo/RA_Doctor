package com.example.patrick.radoctor;

/**
 * Created by Patrick on 12/3/2015.
 */
public class DrugsSpecific {
     private String date;
     private String admission;
     private double dose;
     private boolean hasContent;

     public DrugsSpecific(String date, String admission, double dose){
          this.date = date;
          this.admission = admission;
          this.dose = dose;
          this.hasContent = true;
     }
     public boolean isHasContent(){
          return hasContent;
     }
     public String getDate(){
          return date;
     }
     public String getAdmission(){
          return admission;
     }
     public Double getDose(){
          return dose;
     }
}
