package com.example.patrick.radoctor;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;


public class Diagnosis_Activity extends ActionBarActivity {
     int patient_id;
     DBHelper db;
     TextView tender, swollen, esr, crp, rf, gcsf, date;
     SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
     Date lastUpdate;
     @Override
     protected void onCreate(Bundle savedInstanceState) {
          super.onCreate(savedInstanceState);
          setContentView(R.layout.activity_diagnosis);
          patient_id = getIntent().getExtras().getInt("patient_id");
          db = new DBHelper(this);
          date = (TextView) findViewById(R.id.diagnosisLastUpdated);
          tender = (TextView) findViewById(R.id.TenderTextView);
          swollen = (TextView) findViewById(R.id.SwollenTextView);
          esr = (TextView) findViewById(R.id.ESRTextView);
          crp = (TextView) findViewById(R.id.CRPTextView);
          rf = (TextView) findViewById(R.id.RFTextView);
          gcsf = (TextView) findViewById(R.id.GCSFTextView);
          Diagnosis patient = db.getLatestDiagnosis(patient_id);
          if (patient.isHas_record()) {
               try {
                    lastUpdate = formatter.parse(patient.getDate());
               } catch(Exception e){
                    System.out.println(e.toString());
               }
               date.setText("Last Updated: " + lastUpdate.toString());
               tender.setText(patient.getTender() + " tender joints");
               swollen.setText(patient.getSwollen() + " swollen joints");
               esr.setText(patient.getEsr() + " mm/hr");
               crp.setText(patient.getCrp() + " mg/L");
               rf.setText(patient.getRf() + " U/mL");
               gcsf.setText(patient.getGcsf() + " pg/mL");
          }
     }


     @Override
     public boolean onCreateOptionsMenu(Menu menu) {
          // Inflate the menu; this adds items to the action bar if it is present.
          getMenuInflater().inflate(R.menu.menu_diagnosis, menu);
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

     public void onButtonClicked(View view){
          boolean clicked = ((Button)view).isPressed();
          switch(view.getId()){
               case R.id.updateButtonDiagnosis:{
                    Intent intent = new Intent(Diagnosis_Activity.this,DiagnosisUpdate_Activity.class);
                    intent.putExtra("patient_id",patient_id);
                    startActivity(intent);
                    break;
               }
               case R.id.photosButtonDiagnosis:{
                    Intent intent = new Intent(Diagnosis_Activity.this, Photos_Activity.class);
                    intent.putExtra("patient_id", patient_id);
                    startActivity(intent);
               }
          }
     }
}
