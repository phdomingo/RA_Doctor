package com.example.patrick.radoctor;

/**
 * Created by Patrick on 10/11/2015.
 */
public class Patient {
     private int id;
     private String name;
     private String sex;
     private int age;
     private int aod;
     private int aoo;
     private int rascore;
     public Patient(){
     }

     public Patient(int id, String name, String sex, int age, int aod, int aoo, int rascore){
          this.id = id;
          this.name = name;
          this.sex = sex;
          this.age = age;
          this.aod = aod;
          this.aoo = aoo;
          this.rascore = rascore;
     }
     //setters
     public void setId(int id){
          this.id = id;
     }
     public void setName(String name){
          this.name = name;
     }
     public void setSex(String sex){
          this.sex = sex;
     }
     public void setAge(int age){
          this.age = age;
     }
     public void setAod(int aod){
          this.aod = aod;
     }
     public void setAoo(int aoo){
          this.aoo = aoo;
     }
     public void setRascore(int rascore){this.rascore = rascore;}
     //getters
     public int getId(){
          return id;
     }
     public String getName(){
          return name;
     }
     public String getSex(){
          return sex;
     }
     public int getAge(){
          return age;
     }
     public int getAod(){
          return aod;
     }
     public int getAoo(){
          return aoo;
     }
     public int getRascore(){return rascore;}
}
