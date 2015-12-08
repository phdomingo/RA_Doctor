package com.example.patrick.radoctor;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import java.util.ArrayList;


public class PatientDemographics_Activity extends ActionBarActivity {
     DBHelper db;
     TextView numPatients,numMale,numFemale,aveAoe,aveAod,aveAoo,aveRAScore ;
     ArrayList<Patient> patients;
     @Override
     protected void onCreate(Bundle savedInstanceState) {
          super.onCreate(savedInstanceState);
          setContentView(R.layout.activity_patient_demographics_);
          db = new DBHelper(this);
          numPatients = (TextView)findViewById(R.id.demoNumPatients);
          numMale = (TextView)findViewById(R.id.demoNumMale);
          numFemale = (TextView)findViewById(R.id.demoNumFemale);
          aveAoe = (TextView)findViewById(R.id.demoAveAoe);
          aveAod = (TextView)findViewById(R.id.demoAveAod);
          aveAoo = (TextView)findViewById(R.id.demoAveAoo);
          aveRAScore = (TextView)findViewById(R.id.demoRAScore);
          patients = db.getPatientStatistics();
          numPatients.setText(String.valueOf(patients.size()));
          setNumSex();
          setAves();
     }

     private void setAves() {
          int totalAoe = 0, totalAod = 0, totalAoo = 0,totalRascore = 0;
          double ave1, ave2, ave3, ave4;
          int size = patients.size();
          Patient temp;
          for(int i = 0; i < patients.size();i++){
               temp = patients.get(i);
               totalAoe += temp.getAge();
               totalAod += temp.getAod();
               totalAoo += temp.getAoo();
               totalRascore += temp.getRascore();
          }
          String temp2;
          ave1 = totalAoe/size;
          //temp2 = String.valueOf(temp2);
          ave2 = totalAod/size;
          ave3 = totalAoo/size;
          ave4 = totalRascore/size;
          aveAoe.setText(String.valueOf(ave1));
          aveAod.setText(String.valueOf(ave2));
          aveAoo.setText(String.valueOf(ave3));
          aveRAScore.setText(String.valueOf(ave4));
     }

     private void setNumSex() {
          int countMale = 0;
          int countFemale;
          String male = "Male";
          Patient temp;
          for(int i = 0; i < patients.size();i++){
               temp = patients.get(i);
               if(temp.getSex().equals(male)) countMale++;
          }
          countFemale = patients.size() - countMale;
          numMale.setText(String.valueOf(countMale));
          numFemale.setText(String.valueOf(countFemale));
     }


     @Override
     public boolean onCreateOptionsMenu(Menu menu) {
          // Inflate the menu; this adds items to the action bar if it is present.
          getMenuInflater().inflate(R.menu.menu_patient_demographics_, menu);
          return true;
     }

     @Override
     public boolean onOptionsItemSelected(MenuItem item) {
          // Handle action bar item clicks here. The action bar will
          // automatically handle clicks on the Home/Up button, so long
          // as you specify a parent activity in AndroidManifest.xml.
          int id = item.getItemId();

          //noinspection SimplifiableIfStatement
          if (id == R.id.action_settings) {
               return true;
          }

          return super.onOptionsItemSelected(item);
     }
}
