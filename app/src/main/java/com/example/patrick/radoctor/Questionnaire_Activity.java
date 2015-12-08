package com.example.patrick.radoctor;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;


public class Questionnaire_Activity extends ActionBarActivity {
     int patient_id;
     Spinner spinnerTB, spinnerHepa, spinnerCancer;

     @Override
     protected void onCreate(Bundle savedInstanceState) {
          super.onCreate(savedInstanceState);
          setContentView(R.layout.activity_questionnaire_);
          patient_id = getIntent().getExtras().getInt("patient_id");
          spinnerTB = (Spinner)findViewById(R.id.spinnerTB);
          spinnerHepa = (Spinner)findViewById(R.id.spinnerHepa);
          spinnerCancer = (Spinner)findViewById(R.id.spinnerCancer);
          setSpinners();
     }

     private void setSpinners() {
          ArrayAdapter<CharSequence> adapter1 =  ArrayAdapter.createFromResource(this,R.array.booleanSpinner_array,android.R.layout.simple_spinner_item);
          adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
          spinnerTB.setAdapter(adapter1);
          spinnerHepa.setAdapter(adapter1);
          spinnerCancer.setAdapter(adapter1);
     }


     @Override
     public boolean onCreateOptionsMenu(Menu menu) {
          // Inflate the menu; this adds items to the action bar if it is present.
          getMenuInflater().inflate(R.menu.menu_questionnaire_, menu);
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
               case R.id.buttonBiologicSubmit:{
                    DBHelper db = new DBHelper(this);
                    String tb, hepa, cancer;
                    tb = (String) spinnerTB.getSelectedItem();
                    hepa = (String) spinnerHepa.getSelectedItem();
                    cancer = (String) spinnerCancer.getSelectedItem();
                    if(db.insertQuestionnaireEntry(patient_id,tb,hepa,cancer)){
                         Toast.makeText(getApplicationContext(), "Successfully Saved!", Toast.LENGTH_SHORT).show();
                         Intent intent = new Intent(Questionnaire_Activity.this, Biologics_Activity.class);
                         intent.putExtra("patient_id",patient_id);
                         startActivity(intent);
                    }
                    else Toast.makeText(getApplicationContext(), "Something bad happened =(!", Toast.LENGTH_SHORT).show();
                    break;
               }
               case R.id.buttonBiologicCancel:{
                    Intent intent = new Intent(Questionnaire_Activity.this, PatientInfo_Activity.class);
                    intent.putExtra("patient_id",patient_id);
                    startActivity(intent);
                    break;
               }
          }
     }
}
