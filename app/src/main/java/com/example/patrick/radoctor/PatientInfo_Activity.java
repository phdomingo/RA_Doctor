package com.example.patrick.radoctor;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;


public class PatientInfo_Activity extends ActionBarActivity {
     int patient_id;
     DBHelper db;
     @Override
     protected void onCreate(Bundle savedInstanceState) {
          super.onCreate(savedInstanceState);
          setContentView(R.layout.activity_patient_info);
          TextView patientName = (TextView)findViewById(R.id.patientName);
          TextView patientAge = (TextView)findViewById(R.id.patientAge);
          TextView patientAoD = (TextView)findViewById(R.id.patientAoD);
          TextView patientAoP = (TextView)findViewById(R.id.patientAoO);
          ImageView patientPhoto = (ImageView)findViewById(R.id.patientPhoto);
          TextView patientRAscore = (TextView)findViewById(R.id.patientRAScore);
          Intent intent = getIntent();

          patient_id = intent.getExtras().getInt("patient_id");
          db = new DBHelper(this);

          Cursor patient = db.getData(patient_id);
          patient.moveToFirst();
          String name = patient.getString(patient.getColumnIndex(DBHelper.PATIENTS_NAME));
          String age = patient.getString(patient.getColumnIndex(DBHelper.PATIENTS_AGE));
          String sex = patient.getString(patient.getColumnIndex(DBHelper.PATIENTS_SEX));
          String aod = patient.getString(patient.getColumnIndex(DBHelper.PATIENTS_AOD));
          String aoo = patient.getString(patient.getColumnIndex(DBHelper.PATIENTS_AOO));
          String photo = patient.getString(patient.getColumnIndex(DBHelper.PATIENTS_PHOTO));
          String score = patient.getString(patient.getColumnIndex(DBHelper.PATIENTS_SCORE));

          if (!patient.isClosed())
          {
               patient.close();
          }

          patientName.setText("Name: " + name + " (" + sex + ")" );
          patientAge.setText("Age: "+ age);
          patientAoD.setText("Age of Diagnosis: "+ aod);
          patientAoP.setText("Age of Onset: "+ aoo);
          patientRAscore.setText("RA Criteria Score: " + score);
          try{
               if(!photo.equals("NULL")){
                    patientPhoto.setImageURI(Uri.fromFile(new File(photo)));
               }
          }catch (Exception e){
               e.printStackTrace();
          }
     }


     @Override
     public boolean onCreateOptionsMenu(Menu menu) {
          // Inflate the menu; this adds items to the action bar if it is present.
          getMenuInflater().inflate(R.menu.menu_patient_info, menu);
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

               case R.id.Diagnosis:{
                    if(clicked){
                         Intent intent = new Intent(PatientInfo_Activity.this,Diagnosis_Activity.class);
                         intent.putExtra("patient_id",patient_id);
                         startActivity(intent);
                    }
                    break;
               }
               case R.id.DiseaseActivity:{
                    if(clicked){
                         Intent intent = new Intent(PatientInfo_Activity.this,DiseaseActivity_Activity.class);
                         intent.putExtra("patient_id",patient_id);
                         startActivity(intent);
                    }
                    break;
               }
               case R.id.Drugs:{
                    if(clicked){
                         Intent intent = new Intent(PatientInfo_Activity.this,Drugs_Activity.class);
                         intent.putExtra("patient_id",patient_id);
                         startActivity(intent);

                    }
                    break;
               }
               case R.id.Biologics:{
                    if(clicked){
                         Intent intent = new Intent(PatientInfo_Activity.this,Questionnaire_Activity.class);
                         intent.putExtra("patient_id",patient_id);
                         startActivity(intent);
                    }
                    break;
               }
               case R.id.Graphs:{
                    if(clicked){
                         Intent intent = new Intent(PatientInfo_Activity.this,Graphs_Activity.class);
                         intent.putExtra("patient_id",patient_id);
                         startActivity(intent);
                    }
                    break;
               }
               case R.id.Photos:{
                    if(clicked){
                         Intent intent = new Intent(PatientInfo_Activity.this,Photos_Activity.class);
                         intent.putExtra("patient_id",patient_id);
                         startActivity(intent);
                    }
                    break;
               }
          }
     }
}
