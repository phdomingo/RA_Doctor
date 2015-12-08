package com.example.patrick.radoctor;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.Calendar;


public class UpdateDrugs_Activity extends ActionBarActivity {
     Spinner drugAdmission;
     Spinner drugDose;
     TextView drugName;

     int patient_id;
     int drugNumber;
     double dose;

     String labelMtx,labelAza,labelHCQS,labelSterPred,labelSterMeth,admission;

     DBHelper db;
     @Override
     protected void onCreate(Bundle savedInstanceState) {
          super.onCreate(savedInstanceState);
          setContentView(R.layout.activity_update_drugs_);
          db = new DBHelper(this);
          drugAdmission = (Spinner)findViewById(R.id.spinnerDrugAdmission);
          drugDose = (Spinner)findViewById(R.id.spinnerDrugDose);
          drugName = (TextView)findViewById(R.id.updateDrugLabel);
          patient_id = getIntent().getExtras().getInt("patient_id");
          drugNumber = getIntent().getExtras().getInt("drugs");
          labelMtx = getString(R.string.label_mtx);
          labelAza = getString(R.string.label_aza);
          labelHCQS = getString(R.string.label_hcqs);
          labelSterPred = getString(R.string.label_pred);
          labelSterMeth = getString(R.string.label_methylpred);
          setDrugTitle(drugNumber);
          addItemsOnSpinner1();
          addItemsOnSpinner2(drugNumber);
     }

     private void setDrugTitle(int drugNumber){
          switch(drugNumber){
               case 1:{
                    drugName.setText(labelMtx);
                    break;
               }
               case 2:{
                    drugName.setText(labelAza);
                    break;
               }
               case 3:{
                    drugName.setText(labelHCQS);
                    break;
               }
               case 4:{
                    drugName.setText("Steroids - " + labelSterPred);
                    break;
               }
               case 5:{
                    drugName.setText("Steroids - " + labelSterMeth);
                    break;
               }

          }
     }
     private void addItemsOnSpinner1(){
          ArrayAdapter<CharSequence> adapter1 =  ArrayAdapter.createFromResource(this,R.array.drugsAdmissionSpinner_Array,android.R.layout.simple_spinner_item);
          adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
          drugAdmission.setAdapter(adapter1);
     }
     private void addItemsOnSpinner2(int drugNumber){
          ArrayAdapter<CharSequence> adapter1;
          switch(drugNumber){
               case 1:{
                    adapter1 =  ArrayAdapter.createFromResource(this,R.array.mtxSpinner_array,android.R.layout.simple_spinner_item);
                    break;
               }
               case 2:{
                    adapter1 =  ArrayAdapter.createFromResource(this,R.array.azaSpinner_array,android.R.layout.simple_spinner_item);
                    break;
               }
               case 3: {
                    adapter1 = ArrayAdapter.createFromResource(this, R.array.hcqsSpinner_array, android.R.layout.simple_spinner_item);
                    break;
               }
               case 4:{
                    adapter1 = ArrayAdapter.createFromResource(this, R.array.steroids1Spinner_array, android.R.layout.simple_spinner_item);
                    break;
               }
               default:{
                    adapter1 =  ArrayAdapter.createFromResource(this, R.array.steroids2Spinner_array, android.R.layout.simple_spinner_item);
               }

          }
          drugDose.setAdapter(adapter1);
     }

     @Override
     public boolean onCreateOptionsMenu(Menu menu) {
          // Inflate the menu; this adds items to the action bar if it is present.
          getMenuInflater().inflate(R.menu.menu_update_drugs_, menu);
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

     public void onClick(View view){
          switch(view.getId()){
               case R.id.buttonDrugsCancel:{
                    Intent intent = new Intent(UpdateDrugs_Activity.this, Drugs_Activity.class);
                    intent.putExtra("patient_id",patient_id);
                    startActivity(intent);
                    break;
               }
               case R.id.buttonDrugsSave:{
                    openPopup();
                    break;
               }
          }
     }

     public void openPopup(){
          final AlertDialog.Builder popupbd = new AlertDialog.Builder(this);
          popupbd.setTitle("Save drug update?");
          popupbd.setCancelable(true);
          admission = (String) drugAdmission.getSelectedItem();
          String temp = (String) drugDose.getSelectedItem();
          temp = temp.substring(0,temp.length()-3);
          dose = Double.parseDouble(temp);

          popupbd.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
               public void onClick(DialogInterface dialog, int data) {
               }
          });
          popupbd.setPositiveButton("Save",new DialogInterface.OnClickListener() {
               public void onClick(DialogInterface dialog, int data) {
                    try{
                         if(db.insertPatientDrugs(patient_id,drugNumber,admission,dose)){
                              Toast.makeText(getApplicationContext(), "Successfully Saved!", Toast.LENGTH_SHORT).show();
                         }
                         Intent intent = new Intent(UpdateDrugs_Activity.this,Drugs_Activity.class);
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
