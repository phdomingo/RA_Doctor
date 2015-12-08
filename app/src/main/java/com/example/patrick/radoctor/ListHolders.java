package com.example.patrick.radoctor;

import java.util.ArrayList;

/**
 * Created by Patrick on 10/24/2015.
 */
public class ListHolders {
     private ArrayList<String> dates;
     private ArrayList<Double> das28esr;
     private ArrayList<Double> das28crp;
     private ArrayList<Double> cdai;
     private ArrayList<Double> sdai;
     private ArrayList<String> file_paths;
     public ListHolders(){
          dates = new ArrayList<>();
          das28esr = new ArrayList<>();
          das28crp = new ArrayList<>();
          cdai = new ArrayList<>();
          sdai = new ArrayList<>();
     }

     public ListHolders(ArrayList<String> dates, ArrayList<Double> das28esr, ArrayList<Double> das28crp, ArrayList<Double> cdai, ArrayList
                        <Double> sdai){
          this.dates = dates;
          this.das28esr = das28esr;
          this.das28crp = das28crp;
          this.cdai = cdai;
          this.sdai = sdai;
     }

     public void setDates(ArrayList<String> dates){
          this.dates = dates;
     }
     public void setFile_paths(ArrayList<String> path) {this.file_paths = path;}
     public void setDas28esr(ArrayList<Double> das28esr){this.das28esr = das28esr;}
     public void setDas28crp(ArrayList<Double> das28crp){this.das28crp = das28crp;}
     public void setCdai(ArrayList<Double> cdai){this.cdai = cdai;}
     public void setSdai(ArrayList<Double> sdai){this.sdai = sdai;}
     public ArrayList<String> getDates(){
          return dates;
     }
     public ArrayList<String> getFile_paths() {return file_paths;}
     public ArrayList<Double> getDas28esr(){return das28esr;}
     public ArrayList<Double> getDas28crp(){return das28crp;}
     public ArrayList<Double> getCdai(){return cdai;}
     public ArrayList<Double> getSdai(){return sdai;}
}
