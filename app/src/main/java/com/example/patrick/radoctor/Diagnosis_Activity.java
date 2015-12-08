package com.example.patrick.radoctor;

import android.app.AlertDialog;
import android.content.DialogInterface;
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
     TextView tender, swollen, esr, crp, rf, ega,pga,ccp, date;
     Button button;
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
          ega = (TextView)findViewById(R.id.EGATextView);
          pga = (TextView)findViewById(R.id.PGATextView);
          ccp = (TextView)findViewById(R.id.CCPTextView);
          button = (Button)findViewById(R.id.updateButtonDiagnosis);
          Diagnosis patient = db.getDiagnosis(patient_id,0);
          if (patient.isHas_record()) {
               try {
                    lastUpdate = formatter.parse(patient.getDate());
               } catch(Exception e){
                    System.out.println(e.toString());
               }
               button.setEnabled(false);
               button.setVisibility(Button.INVISIBLE);
               date.setText("Date Diagnosed: " + lastUpdate.toString());
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

     private void popup(){
          final AlertDialog.Builder popupbd = new AlertDialog.Builder(this);
          popupbd.setTitle("Warning: You can only set this once!");
          popupbd.setCancelable(true);

          popupbd.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
               public void onClick(DialogInterface dialog, int data) {
               }
          });
          popupbd.setPositiveButton("Ok",new DialogInterface.OnClickListener(){
               public void onClick(DialogInterface dialog, int data) {
                    Intent intent = new Intent(Diagnosis_Activity.this,DiagnosisUpdate_Activity.class);
                    intent.putExtra("patient_id",patient_id);
                    intent.putExtra("source_activity",1);
                    startActivity(intent);
               }
           });
          AlertDialog popup = popupbd.create();
          popup.show();
     }

     public void onButtonClicked(View view){
          boolean clicked = ((Button)view).isPressed();
          switch(view.getId()){
               case R.id.updateButtonDiagnosis:{
                    popup();
                    break;
               }
          }
     }
}
