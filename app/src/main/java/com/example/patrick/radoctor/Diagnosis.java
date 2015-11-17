package com.example.patrick.radoctor;

/**
 * Created by Patrick on 10/26/2015.
 */
public class Diagnosis {
     private int patient_id;
     private int tender;
     private int swollen;
     private double esr;
     private double crp;
     private double rf;
     private double gcsf;
     private boolean has_record;
     private String date;
     public Diagnosis(){
          has_record = false;
     }

     public Diagnosis(int patient_id, int tender, int swollen, double esr, double crp, double rf, double gcsf, String date){
          this.patient_id = patient_id;
          this.tender = tender;
          this.swollen = swollen;
          this.esr = esr;
          this.crp = crp;
          this.rf = rf;
          this.gcsf = gcsf;
          this.date = date;
          this.has_record = true;

     }

     public int getPatient_id(){
          return patient_id;
     }
     public int getTender(){
          return tender;
     }
     public int getSwollen(){
          return swollen;
     }
     public double getEsr(){
          return esr;
     }
     public double getCrp(){
          return crp;
     }
     public double getRf(){
          return rf;
     }
     public double getGcsf(){
          return gcsf;
     }
     public String getDate() { return date;}
     public boolean isHas_record(){
          return has_record;
     }
}
