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


public class DiseaseActivity_Activity extends ActionBarActivity {
     int patient_id;
     TextView tender,swollen,esr,crp,rf,pga,ega,ccp, date;
     SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
     Date lastUpdate;
     DBHelper db;
     @Override
     protected void onCreate(Bundle savedInstanceState) {
          super.onCreate(savedInstanceState);
          setContentView(R.layout.activity_disease_activity);
          patient_id = getIntent().getExtras().getInt("patient_id");
          db = new DBHelper(this);
          date = (TextView) findViewById(R.id.daLastUpdated);
          tender = (TextView) findViewById(R.id.daTender);
          swollen = (TextView) findViewById(R.id.daSwollen);
          rf = (TextView)findViewById(R.id.daRF);
          esr = (TextView) findViewById(R.id.daESR);
          crp = (TextView) findViewById(R.id.daCRP);
          pga = (TextView) findViewById(R.id.daPGA);
          ega = (TextView) findViewById(R.id.daEGA);
          ccp = (TextView) findViewById(R.id.daCCP);
          Diagnosis patient = db.getDiagnosis(patient_id,1);
          if (patient.isHas_record()) {
               try {
                    lastUpdate = formatter.parse(patient.getDate());
               } catch (Exception e) {
                    System.out.println(e.toString());
               }

               date.setText("Last Updated: " + lastUpdate.toString());
               tender.setText(patient.getTender() + " tender joints");
               swollen.setText(patient.getSwollen() + " swollen joints");
               esr.setText(patient.getEsr() + " mm/hr");
               crp.setText(patient.getCrp() + " mg/L");
               rf.setText(patient.getRf() + " U/mL");
               ega.setText(String.valueOf(patient.getEga()));
               pga.setText(String.valueOf(patient.getPga()));
               ccp.setText(String.valueOf(patient.getCcp()));
          }
     }

     @Override
     public boolean onCreateOptionsMenu(Menu menu) {
          // Inflate the menu; this adds items to the action bar if it is present.
          getMenuInflater().inflate(R.menu.menu_disease, menu);
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

     public void onTextViewClicked(View view){
          boolean clicked = ((TextView)view).isPressed();
          switch(view.getId()){
               case R.id.viewDiseaseActivityMeasures:{
                    Intent intent = new Intent(DiseaseActivity_Activity.this, DiseaseActivityMeasures_Activity.class);
                    intent.putExtra("patient_id",patient_id);
                    startActivity(intent);
                    break;
               }
               case R.id.updateDiseaseActivity:{
                    Intent intent = new Intent(DiseaseActivity_Activity.this, DiagnosisUpdate_Activity.class);
                    intent.putExtra("patient_id",patient_id);
                    intent.putExtra("source_activity",2);
                    startActivity(intent);
                    break;
               }
          }
     }
}
