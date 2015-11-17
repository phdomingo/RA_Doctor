package com.example.patrick.radoctor;

/**
 * Created by Patrick on 11/2/2015.
 */
public class DiseaseActivity_Object {

     private String lastUpdatedDas28;
     private String lastUpdatedCSDai;
     private Double das28;
     private Double cdai;
     private Double sdai;
     private Boolean has_record_das28 = false;
     private Boolean has_record_csdai = false;

     public DiseaseActivity_Object(){

     }

     public void setCdai(Double cdai) {
          this.cdai = cdai;
     }

     public void setSdai(Double sdai) {
          this.sdai = sdai;
     }

     public void setDas28(Double das28) {
          this.das28 = das28;
     }

     public void setHas_record_csdai(Boolean has_record_csdai) {
          this.has_record_csdai = has_record_csdai;
     }

     public void setHas_record_das28(Boolean has_record_das28) {
          this.has_record_das28 = has_record_das28;
     }

     public void setLastUpdatedCSDai(String lastUpdatedCSDai) {
          this.lastUpdatedCSDai = lastUpdatedCSDai;
     }

     public void setLastUpdatedDas28(String lastUpdatedDas28) {
          this.lastUpdatedDas28 = lastUpdatedDas28;
     }



     public String getLastUpdatedDas28(){
          return lastUpdatedDas28;
     }
     public String getLastUpdatedCSDai(){
          return lastUpdatedCSDai;
     }
     public Double getDas28(){
          return das28;
     }
     public Double getCdai(){
          return cdai;
     }
     public Double getSdai(){
          return sdai;
     }
     public Boolean isHas_record_das28(){
          return has_record_das28;
     }
     public Boolean isHas_record_csdai(){
          return has_record_csdai;
     }

}
