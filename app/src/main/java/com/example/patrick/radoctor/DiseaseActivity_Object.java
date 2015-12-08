package com.example.patrick.radoctor;

/**
 * Created by Patrick on 11/2/2015.
 */
public class DiseaseActivity_Object {

     private String lastUpdated;
     private Double das28esr;
     private Double das28crp;
     private Double cdai;
     private Double sdai;
     private Boolean has_record = false;

     public DiseaseActivity_Object(){

     }

     public void setCdai(Double cdai) {
          this.cdai = cdai;
     }

     public void setSdai(Double sdai) {
          this.sdai = sdai;
     }

     public void setDas28esr(Double das28esr) {
          this.das28esr = das28esr;
     }

     public void setDas28crp(Double das28crp){this.das28crp = das28crp;}

     public void setHas_record(Boolean has_record){
          this.has_record = has_record;
     }

     public void setLastUpdated(String lastUpdated) {
          this.lastUpdated = lastUpdated;
     }




     public String getLastUpdated(){
          return lastUpdated;
     }
     public Double getDas28esr(){
          return das28esr;
     }
     public Double getDas28crp() { return das28crp;}
     public Double getCdai(){
          return cdai;
     }
     public Double getSdai(){
          return sdai;
     }
     public Boolean isHas_record(){
          return has_record;
     }

}
