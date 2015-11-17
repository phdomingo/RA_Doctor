package com.example.patrick.radoctor;

import java.util.ArrayList;

/**
 * Created by Patrick on 10/24/2015.
 */
public class ListHolders {
     private ArrayList<String> dates;
     private ArrayList<Double> das28Scores;
     private ArrayList<Double> cdaiScores;
     private ArrayList<Double> sdaiScores;
     private ArrayList<Double> esr_crp;
     private ArrayList<String> file_paths;
     public ListHolders(){

     }

     public ListHolders(ArrayList<Double> das28Scores, ArrayList<String> dates){
          this.das28Scores = das28Scores;
          this.dates = dates;
     }
     public ListHolders(ArrayList<Double> cdaiScores, ArrayList<Double> sdaiScores,ArrayList<String> dates){
          this.cdaiScores = cdaiScores;
          this.sdaiScores = sdaiScores;
          this.dates = dates;
     }



     public void setDates(ArrayList<String> dates){
          this.dates = dates;
     }
     public void setDas28Scores(ArrayList<Double> das28Scores){
          this.das28Scores = das28Scores;
     }
     public void setCdaiScores(ArrayList<Double> cdaiScores){
          this.cdaiScores = cdaiScores;
     }
     public void setSdaiScores(ArrayList<Double> sdaiScores){
          this.sdaiScores = sdaiScores;
     }
     public void setEsr_crp(ArrayList<Double> esr_crp){this.esr_crp=esr_crp;}
     public void setFile_paths(ArrayList<String> path) {this.file_paths = path;};
     public ArrayList<String> getDates(){
          return dates;
     }
     public ArrayList<Double> getDas28Scores(){
          return das28Scores;
     }
     public ArrayList<Double> getCdaiScores(){
          return cdaiScores;
     }
     public ArrayList<Double> getSdaiScores(){
          return sdaiScores;
     }
     public ArrayList<Double> getEsr_crp() {return esr_crp;}
     public ArrayList<String> getFile_paths() {return file_paths;}
}
