package com.example.patrick.radoctor;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.text.NumberFormat;


public class DiagnosisUpdate_Activity extends ActionBarActivity {
     int patient_id, source_activity;
     int tender, swollen;
     double esr, crp,rf,ega,pga;
     double das28esr, das28crp, cdai, sdai;
     String ccp;
     EditText tenderEditText, swollenEditText,esrEditText, crpEditText, rfEditText, egaEditText, pgaEditText;
     Spinner ccpSpinner;

     DBHelper db;







     @Override
     protected void onCreate(Bundle savedInstanceState) {
          super.onCreate(savedInstanceState);
          setContentView(R.layout.activity_diagnosis_update);
          patient_id = getIntent().getExtras().getInt("patient_id");
          source_activity = getIntent().getExtras().getInt("source_activity");
          db = new DBHelper(this);
          tenderEditText = (EditText) findViewById(R.id.diagnosis_tenderEditText);
          swollenEditText = (EditText) findViewById(R.id.diagnosis_swollenEditText);
          esrEditText = (EditText) findViewById(R.id.diagnosis_EsrEditText);
          crpEditText = (EditText) findViewById(R.id.diagnosis_crpEditText);
          rfEditText = (EditText) findViewById(R.id.diagnosis_RfEditText);
          egaEditText = (EditText) findViewById(R.id.diagnosis_egaEditText);
          pgaEditText = (EditText)findViewById(R.id.diagnosis_pgaEditText);
          ccpSpinner = (Spinner)findViewById(R.id.diagnosis_ccpSpinner);
          tenderEditText.setFilters(new InputFilter[]{new InputFilterMinMax("0","28")});
          swollenEditText.setFilters(new InputFilter[]{new InputFilterMinMax("0","28")});
          esrEditText.setFilters(new InputFilter[]{new InputFilterMinMax2("0","150")});
          crpEditText.setFilters(new InputFilter[]{new InputFilterMinMax2("0","150")});
          rfEditText.setFilters(new InputFilter[]{new InputFilterMinMax2("0","100")});
          egaEditText.setFilters(new InputFilter[]{new InputFilterMinMax2("0","10")});
          pgaEditText.setFilters(new InputFilter[]{new InputFilterMinMax2("0","10")});


          if(source_activity==1) this.setTitle("Add Diagnosis");
          else{
               this.setTitle("Update Disease Activity");
               Diagnosis patient = db.getDiagnosis(patient_id,1);
               if(patient.isHas_record()) {
                    tenderEditText.setText(String.valueOf(patient.getTender()));
                    swollenEditText.setText(String.valueOf(patient.getSwollen()));
                    esrEditText.setText(String.valueOf(patient.getEsr()));
                    crpEditText.setText(String.valueOf(patient.getCrp()));
                    rfEditText.setText(String.valueOf((patient.getRf())));
                    egaEditText.setText(String.valueOf(patient.getEga()));
                    pgaEditText.setText(String.valueOf(patient.getPga()));

               }else{
                    Toast.makeText(getApplicationContext(), "You have not entered a diagnosis yet. " +
                            "This will be used as the diagnosis", Toast.LENGTH_LONG).show();
               }
          }
          setSpinner();


     }

     private void setSpinner() {
          ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this,R.array.ccpSpinner_array,android.R.layout.simple_spinner_item);
          adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
          ccpSpinner.setAdapter(adapter1);
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
                    Intent intent;
                    if(source_activity == 1)
                         intent = new Intent(DiagnosisUpdate_Activity.this, Diagnosis_Activity.class);
                    else
                         intent = new Intent(DiagnosisUpdate_Activity.this, DiseaseActivity_Activity.class);
                    intent.putExtra("patient_id", patient_id);
                    startActivity(intent);
                    break;
               }
          }
     }
     public void openPopup(){
          final AlertDialog.Builder popupbd = new AlertDialog.Builder(this);
          if(source_activity == 1)
               popupbd.setTitle("Save Diagnosis?");
          else
               popupbd.setTitle("Update Disease Activity?");
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
                         ega = Double.parseDouble(egaEditText.getText().toString());
                         pga = Double.parseDouble(pgaEditText.getText().toString());
                         esr = Double.parseDouble(esrEditText.getText().toString());
                         crp = Double.parseDouble(crpEditText.getText().toString());
                         rf =  Double.parseDouble(rfEditText.getText().toString());
                         ccp = (String) ccpSpinner.getSelectedItem();

                         das28esr = 0.56*Math.sqrt(tender) + 0.28*Math.sqrt(swollen)+
                                 0.7*Math.log(esr)+ 0.014*pga;
                         das28crp = 0.56*Math.sqrt(tender) + 0.28*Math.sqrt(swollen)+
                                 0.36*Math.log(crp+1)+ 0.014*pga + 0.96;
                         cdai = tender + swollen + ega+ pga;
                         sdai = tender + swollen + ega + pga + crp/10;
                         if(db.insertPatientDiagnosis(patient_id,tender,swollen,esr,crp,rf,ega,pga,ccp)){
                              if(db.insertPatientScore(patient_id,das28esr,das28crp,cdai,sdai))
                                   Toast.makeText(getApplicationContext(), "Successfully Saved!", Toast.LENGTH_SHORT).show();
                         }

                         Intent intent;
                         if(source_activity == 1)
                              intent = new Intent(DiagnosisUpdate_Activity.this, Diagnosis_Activity.class);
                         else
                              intent = new Intent(DiagnosisUpdate_Activity.this, DiseaseActivity_Activity.class);
                         intent.putExtra("patient_id", patient_id);
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
