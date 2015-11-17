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
import android.widget.EditText;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.text.NumberFormat;


public class DiagnosisUpdate_Activity extends ActionBarActivity {
     int patient_id;
     int tender;
     int swollen;
     double esr;
     double crp;
     double rf;
     double gcsf;

     EditText tenderEditText;
     EditText swollenEditText;
     EditText esrEditText;
     EditText crpEditText;
     EditText rfEditText;
     EditText gcsfEditText;

     DBHelper db;



     @Override
     protected void onCreate(Bundle savedInstanceState) {
          super.onCreate(savedInstanceState);
          setContentView(R.layout.activity_diagnosis_update);
          patient_id = getIntent().getExtras().getInt("patient_id");
          db = new DBHelper(this);
          tenderEditText = (EditText) findViewById(R.id.diagnosis_tenderEditText);
          swollenEditText = (EditText) findViewById(R.id.diagnosis_swollenEditText);
          esrEditText = (EditText) findViewById(R.id.diagnosis_EsrEditText);
          crpEditText = (EditText) findViewById(R.id.diagnosis_crpEditText);
          rfEditText = (EditText) findViewById(R.id.diagnosis_RfEditText);
          gcsfEditText = (EditText) findViewById(R.id.diagnosis_GcsfEditText);

     }


     @Override
     public boolean onCreateOptionsMenu(Menu menu) {
          // Inflate the menu; this adds items to the action bar if it is present.
          getMenuInflater().inflate(R.menu.menu_diagnosis_update, menu);
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
          boolean pressed = ((Button)view).isPressed();
          switch(view.getId()){
               case R.id.diagnosis_UpdateSaveButton:{
                    openPopup();
                    break;
               }
               case R.id.diagnosis_UpdateCancelButton:{
                    Intent intent = new Intent(DiagnosisUpdate_Activity.this,Diagnosis_Activity.class);
                    intent.putExtra("patient_id",patient_id);
                    startActivity(intent);
                    break;
               }
          }
     }
     public void openPopup(){
          final AlertDialog.Builder popupbd = new AlertDialog.Builder(this);
          popupbd.setTitle("Update Diagnosis?");
          popupbd.setCancelable(true);

          popupbd.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
               public void onClick(DialogInterface dialog, int data) {
               }
          });
          popupbd.setPositiveButton("Save",new DialogInterface.OnClickListener() {
               public void onClick(DialogInterface dialog, int data) {
                    try{
                         tender = Integer.parseInt(tenderEditText.getText().toString());
                         swollen = Integer.parseInt(swollenEditText.getText().toString());
                         esr = Double.parseDouble(esrEditText.getText().toString());
                         crp = Double.parseDouble(crpEditText.getText().toString());
                         rf = Double.parseDouble(rfEditText.getText().toString());
                         gcsf = Double.parseDouble(gcsfEditText.getText().toString());
                         if(db.insertPatientDiagnosis(patient_id,tender,swollen,esr,crp,rf,gcsf)){
                              Toast.makeText(getApplicationContext(), "Successfully Saved!", Toast.LENGTH_SHORT).show();
                         }
                         Intent intent = new Intent(DiagnosisUpdate_Activity.this,Diagnosis_Activity.class);
                         intent.putExtra("patient_id",patient_id);
                         startActivity(intent);
                    }catch(Exception e){
                         System.out.println(e.toString());
                         Toast.makeText(getApplicationContext(), "Something Bad Happened =( Try saving again", Toast.LENGTH_SHORT).show();
                    }
               }
          });
          AlertDialog popup = popupbd.create();
          popup.show();
     }
}
