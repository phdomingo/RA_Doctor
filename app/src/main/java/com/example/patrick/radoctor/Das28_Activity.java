package com.example.patrick.radoctor;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;


public class Das28_Activity extends ActionBarActivity {
     EditText tjoint;
     EditText sjoint;
     EditText pga;
     EditText esrcrp;
     Button saveButton;
     DBHelper db;
     int tenderJointCount = 0;
     int swollenJointCount = 0;
     double das28 = 0;
     double PGA = 0;
     double ESR_CRP = 0;
     boolean esr_crp = true;
     int patient_id;

     public void calculateDAS28(){
          TextView classifiedLabel = (TextView)findViewById(R.id.classifiedLabel);
          TextView scoreLabel = (TextView)findViewById(R.id.scoreLabel);
          LinearLayout ll = (LinearLayout)findViewById(R.id.linearLayoutDas28_1);

          String temp;
          das28 = 0.0000;
          if(esr_crp == true && ESR_CRP != 0)
               das28 = 0.56*Math.sqrt(tenderJointCount) + 0.28*Math.sqrt(swollenJointCount)+
                         0.7*Math.log(ESR_CRP)+ 0.014*PGA;
          else if (esr_crp == false)
               das28 = 0.56*Math.sqrt(tenderJointCount) + 0.28*Math.sqrt(swollenJointCount)+
                       0.36*Math.log(ESR_CRP+1)+ 0.014*PGA + 0.96;
          temp = String.valueOf(das28);
          if(temp.length() != 3) temp = temp.substring(0,4);
          scoreLabel.setText(temp);
          if(das28 < 2.6){
               classifiedLabel.setText("Remission");
               ll.setBackgroundColor(Color.parseColor("#66FF00"));
          }
          else if(das28 >= 2.6 && das28 < 3.2){
               classifiedLabel.setText("Low Activity");
               ll.setBackgroundColor(Color.parseColor("#CCFF00"));
          }
          else if(das28 >= 3.2 && das28 <=5.1){
               classifiedLabel.setText("Moderate Activity");
               ll.setBackgroundColor(Color.parseColor("#FFCC00"));
          }
          else{
               classifiedLabel.setText("High Activity");
               ll.setBackgroundColor(Color.parseColor("#FF0000"));
          }
     }


     TextWatcher tenderJointCountChecker = new TextWatcher(){
          public void afterTextChanged(Editable s){

          }
          public void beforeTextChanged(CharSequence s, int start, int count, int after){

          }
          public void onTextChanged(CharSequence s, int start, int before, int count){
               String temp;
               temp = tjoint.getText().toString();
               if(temp.length() == 0) tenderJointCount = 0;
               else tenderJointCount = Integer.parseInt(temp);
               if(tenderJointCount > 28){
                    tjoint.setText("28");
                    tjoint.setSelection(tjoint.length());
                    tenderJointCount = 28;
               }
               calculateDAS28();
          }
     };

     TextWatcher swollenJointCountChecker = new TextWatcher(){
          public void afterTextChanged(Editable s){

          }
          public void beforeTextChanged(CharSequence s, int start, int count, int after){

          }
          public void onTextChanged(CharSequence s, int start, int before, int count){
               String temp = sjoint.getText().toString();
               if(temp.length() == 0) swollenJointCount = 0;
               else swollenJointCount = Integer.parseInt(temp);
               if(swollenJointCount > 28){
                    sjoint.setText("28");
                    sjoint.setSelection(sjoint.length());
                    swollenJointCount = 28;
               }
               calculateDAS28();
          }
     };

     TextWatcher pgaCountChecker = new TextWatcher(){
          public void afterTextChanged(Editable s){

          }
          public void beforeTextChanged(CharSequence s, int start, int count, int after){

          }
          public void onTextChanged(CharSequence s, int start, int before, int count){
               String temp = pga.getText().toString();
               if(temp.length() == 0) PGA = 0;
               else if (temp.length() == 1 && temp.charAt(0)== '.' ) PGA = 0;
               else PGA = Double.parseDouble(temp);
               if(PGA > 10){
                    pga.setText("10");
                    pga.setSelection(pga.length());
                    PGA = 10;
               }
               calculateDAS28();
          }
     };

     TextWatcher esrcrpCountChecker = new TextWatcher(){
          public void afterTextChanged(Editable s){

          }
          public void beforeTextChanged(CharSequence s, int start, int count, int after){

          }
          public void onTextChanged(CharSequence s, int start, int before, int count){
               String temp = esrcrp.getText().toString();
               if(temp.length() == 0) ESR_CRP = 0;
               else ESR_CRP = Double.parseDouble(esrcrp.getText().toString());
               if(ESR_CRP > 151){
                    esrcrp.setText("150");
                    esrcrp.setSelection(esrcrp.length());
                    ESR_CRP = 150;
               }
               calculateDAS28();
          }
     };




     @Override
     protected void onCreate(Bundle savedInstanceState) {
          super.onCreate(savedInstanceState);
          setContentView(R.layout.activity_das28);

          db = new DBHelper(this);

          patient_id = getIntent().getExtras().getInt("patient_id");

          saveButton = (Button)findViewById(R.id.das28SaveButton);
          tjoint = (EditText)findViewById(R.id.tenderJointDas28_Edit);
          sjoint = (EditText)findViewById(R.id.swollenJointDas28_Edit);
          pga = (EditText)findViewById(R.id.pgaDas28_Edit);
          esrcrp = (EditText)findViewById(R.id.esrcrpDas28_Edit);

          tjoint.addTextChangedListener(tenderJointCountChecker);
          sjoint.addTextChangedListener(swollenJointCountChecker);
          pga.addTextChangedListener(pgaCountChecker);
          esrcrp.addTextChangedListener(esrcrpCountChecker);


     }


     @Override
     public boolean onCreateOptionsMenu(Menu menu) {
          // Inflate the menu; this adds items to the action bar if it is present.
          getMenuInflater().inflate(R.menu.menu_das28, menu);
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

     public void onRadioButtonClicked(View view){
          LinearLayout ll = (LinearLayout)findViewById(R.id.lLayoutDas286);
          boolean checked = ((RadioButton)view).isChecked();
          switch(view.getId()){
               case R.id.ESRRadio:{
                    if(checked){
                         RadioButton rb = (RadioButton)findViewById(R.id.CRPRadio);
                         ll.setVisibility(View.INVISIBLE);
                         esrcrp.setHint("mm/h");
                         rb.setChecked(false);
                         esr_crp = true;
                         setTitle("DAS28-ESR");
                    }
                    break;
               }
               case R.id.CRPRadio:{
                    if(checked){
                         RadioButton rb = (RadioButton)findViewById(R.id.ESRRadio);
                         ll.setVisibility(View.VISIBLE);
                         esrcrp.setHint("mg/l");
                         rb.setChecked(false);
                         esr_crp = false;
                         setTitle("DAS28-CRP");
                    }
                    break;
               }
               case R.id.mglRadio:{
                    if(checked){
                         RadioButton rb = (RadioButton)findViewById(R.id.mgdlRadio);
                         esrcrp.setHint("mg/l");
                         rb.setChecked(false);
                    }
                    break;
               }
               case R.id.mgdlRadio:{
                    if(checked){
                         RadioButton rb = (RadioButton)findViewById(R.id.mglRadio);
                         esrcrp.setHint("mg/dl");
                         rb.setChecked(false);
                    }
                    break;
               }
          }
          calculateDAS28();
     }
     public void onSaveButtonClicked(View view){
          boolean clicked = view.isPressed();
          if(clicked) openPopup();
     }

     public void openPopup(){
          final AlertDialog.Builder popupbd = new AlertDialog.Builder(this);
          popupbd.setTitle("Save DAS28 Score?");
          popupbd.setCancelable(true);
          popupbd.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
               public void onClick(DialogInterface dialog, int data) {
               }
          });
          popupbd.setPositiveButton("Save",new DialogInterface.OnClickListener() {
               public void onClick(DialogInterface dialog, int data) {
                    if(db.insertPatientDas28(patient_id, das28)){
                         Toast.makeText(getApplicationContext(), "Successfully Saved!", Toast.LENGTH_SHORT).show();
                         Intent intent = new Intent(Das28_Activity.this, DiseaseActivity_Activity.class);
                         intent.putExtra("patient_id" , patient_id);
                         startActivity(intent);
                    }

                    else
                         Toast.makeText(getApplicationContext(),"Something bad happened =( Try saving again",Toast.LENGTH_SHORT).show();
               }
          });
          AlertDialog popup = popupbd.create();
          popup.show();
     }
}
