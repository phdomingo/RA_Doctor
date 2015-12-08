package com.example.patrick.radoctor;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;


public class DiseaseActivityMeasures_Activity extends ActionBarActivity {
     int patient_id;
     TextView measureLastUpdated,measureDas28esr,measureDas28crp,measureCDAI,measureSDAI;
     SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
     Date lastUpdate;
     double das28esr, das28crp, cdai,sdai;
     DiseaseActivity_Object dao;
     DBHelper db;
     @Override
     protected void onCreate(Bundle savedInstanceState) {
          super.onCreate(savedInstanceState);
          setContentView(R.layout.activity_disease_activity_measures_);
          patient_id = getIntent().getExtras().getInt("patient_id");
          measureLastUpdated = (TextView)findViewById(R.id.measureLastUpdated);
          measureDas28esr = (TextView)findViewById(R.id.measureDas28esr);
          measureDas28crp = (TextView)findViewById(R.id.measureDas28crp);
          measureCDAI = (TextView)findViewById(R.id.measureCDAI);
          measureSDAI = (TextView)findViewById(R.id.measureSDAI);
          db = new DBHelper(this);
          setMeasures();
     }

     private void setMeasures() {
          dao = db.getScore(patient_id,2);
          String temp, temp2;
          if(dao.isHas_record()){
               try{
                    lastUpdate = formatter.parse(dao.getLastUpdated());
                    measureLastUpdated.setText("Last Updated: " + lastUpdate.toString());
               }catch(Exception e){e.toString();}
               das28esr = dao.getDas28esr();
               das28crp = dao.getDas28crp();
               cdai = dao.getCdai();
               sdai = dao.getSdai();

               //das28
               if(das28esr < 2.6) temp = "Remission";
               else if (das28esr >= 2.6 && das28esr < 3.2) temp = "Low Activity";
               else if (das28esr >= 3.2 && das28esr <= 5.1) temp = "Moderate Activity";
               else temp = "High Activity";
               temp2 = String.valueOf(das28esr);
               if(temp2.length() != 3) temp2 = temp2.substring(0,4);
               measureDas28esr.setText(measureDas28esr.getText() + " " + temp2 + " (" + temp + ")");
               //das28

               //crp
               if(das28crp < 2.6) temp = "Remission";
               else if (das28crp >= 2.6 && das28crp < 3.2) temp = "Low Activity";
               else if (das28crp >= 3.2 && das28crp <= 5.1) temp = "Moderate Activity";
               else temp = "High Activity";
               temp2 = String.valueOf(das28crp);
               if(temp2.length() != 3) temp2 = temp2.substring(0,4);
               measureDas28crp.setText(measureDas28crp.getText() + " " + temp2 + " (" + temp + ")");
               //crp

               //cdai
               if(cdai <= 2.8) temp = "Remission";
               else if (cdai >= 2.8 && cdai < 10) temp = "Low Activity";
               else if (cdai >= 10 && cdai <= 22) temp = "Moderate Activity";
               else temp = "High Activity";
               temp2 = String.valueOf(cdai);
               if(temp2.length() != 3) temp2 = temp2.substring(0,4);
               measureCDAI.setText(measureCDAI.getText() + " " + temp2 + " (" + temp + ")");
               //cdai

               //sdai
               if(sdai <= 3.3) temp = "Remission";
               else if (sdai >= 3.3 && sdai < 11) temp = "Low Activity";
               else if (sdai >= 11 && sdai <= 26) temp = "Moderate Activity";
               else temp = "High Activity";
               temp2 = String.valueOf(sdai);
               if(temp2.length() != 3) temp2 = temp2.substring(0,4);
               measureSDAI.setText(measureSDAI.getText() + " " + temp2 + " (" + temp + ")");
               //sdai
          }
     }


     @Override
     public boolean onCreateOptionsMenu(Menu menu) {
          // Inflate the menu; this adds items to the action bar if it is present.
          getMenuInflater().inflate(R.menu.menu_disease_activity_measures_, menu);
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
