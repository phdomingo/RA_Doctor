package com.example.patrick.radoctor;

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


public class CSdai_Activity extends ActionBarActivity {
     DBHelper db;
     EditText tenderJointEditText;
     EditText swollenJointEditText;
     EditText pgaEditText;
     EditText egaEditText;
     EditText crpEditText;
     int tenderJoint = 0;
     int swollenJoint = 0;
     double pga = 0;
     double ega = 0;
     double crp = 0;
     boolean crp_mgl = true;
     double sdai = 0;
     double cdai = 0;
     int patient_id;
     public void calculateCDAI(){
          TextView clCDAI = (TextView)findViewById(R.id.CDAIClassifiedLabel);
          TextView cCDAI = (TextView)findViewById(R.id.CDAIScoreLabel);
          LinearLayout ll = (LinearLayout)findViewById(R.id.linearLayoutcdaisdai_2);
          String temp;
          cdai = tenderJoint + swollenJoint + pga + ega;
          temp = String.valueOf(cdai);
          if(temp.length() != 3) temp = temp.substring(0,4);
          cCDAI.setText(temp);
          if(cdai <= 2.8){
               clCDAI.setText("CDAI Remission");
               ll.setBackgroundColor(Color.parseColor("#66FF00"));
          }
          else if(cdai > 2.8 && cdai < 10){
               clCDAI.setText("CDAI Low Activity");
               ll.setBackgroundColor(Color.parseColor("#CCFF00"));
          }
          else if (cdai >= 10 && cdai <= 22){
               clCDAI.setText("CDAI Medium Activity");
               ll.setBackgroundColor(Color.parseColor("#FFCC00"));
          }
          else{
               clCDAI.setText("CDAI High Activity");
               ll.setBackgroundColor(Color.parseColor("#FF0000"));
          }
     }

     public void calculateSDAI(){
          TextView clSDAI = (TextView)findViewById(R.id.SDAIClassifiedLabel);
          TextView cSDAI = (TextView)findViewById(R.id.SDAIScoreLabel);
          LinearLayout ll = (LinearLayout)findViewById(R.id.linearLayoutcdaisdai_1);
          String temp;
          if(crp_mgl) sdai = tenderJoint + swollenJoint + pga + ega + crp/10;
          else sdai = tenderJoint + swollenJoint + pga + ega + crp;
          temp = String.valueOf(sdai);
          if(temp.length() != 3) temp = temp.substring(0,4);
          cSDAI.setText(temp);
          if(sdai <= 3.3){
               clSDAI.setText("SDAI Remission");
               ll.setBackgroundColor(Color.parseColor("#66FF00"));
          }
          else if(sdai > 3.3 && sdai < 11){
               clSDAI.setText("SDAI Low Activity");
               ll.setBackgroundColor(Color.parseColor("#CCFF00"));
          }
          else if (sdai >= 11 && sdai <= 26){
               clSDAI.setText("SDAI Medium Activity");
               ll.setBackgroundColor(Color.parseColor("#FFCC00"));
          }
          else{
               clSDAI.setText("SDAI High Activity");
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
               temp = tenderJointEditText.getText().toString();
               if(temp.length() == 0) tenderJoint = 0;
               else tenderJoint = Integer.parseInt(temp);
               if(tenderJoint > 28){
                    tenderJointEditText.setText("28");
                    tenderJointEditText.setSelection(tenderJointEditText.length());
                    tenderJoint = 28;
               }
               calculateCDAI();
               calculateSDAI();
          }
     };

     TextWatcher swollenJointCountChecker = new TextWatcher(){
          public void afterTextChanged(Editable s){

          }
          public void beforeTextChanged(CharSequence s, int start, int count, int after){

          }
          public void onTextChanged(CharSequence s, int start, int before, int count){
               String temp = swollenJointEditText.getText().toString();
               if(temp.length() == 0) swollenJoint = 0;
               else swollenJoint = Integer.parseInt(temp);
               if(swollenJoint > 28){
                    swollenJointEditText.setText("28");
                    swollenJointEditText.setSelection(swollenJointEditText.length());
                    swollenJoint = 28;
               }
               calculateCDAI();
               calculateSDAI();
          }
     };

     TextWatcher pgaCountChecker = new TextWatcher(){
          public void afterTextChanged(Editable s){

          }
          public void beforeTextChanged(CharSequence s, int start, int count, int after){

          }
          public void onTextChanged(CharSequence s, int start, int before, int count){
               String temp = pgaEditText.getText().toString();
               if(temp.length() == 0) pga = 0;
               else if (temp.length() == 1 && temp.charAt(0)== '.' ) pga = 0;
               else pga = Double.parseDouble(temp);
               if(pga > 10){
                    pgaEditText.setText("10");
                    pgaEditText.setSelection(pgaEditText.length());
                    pga = 10;
               }
               calculateCDAI();
               calculateSDAI();
          }
     };

     TextWatcher egaCountChecker = new TextWatcher(){
          public void afterTextChanged(Editable s){

          }
          public void beforeTextChanged(CharSequence s, int start, int count, int after){

          }
          public void onTextChanged(CharSequence s, int start, int before, int count){
               String temp = egaEditText.getText().toString();
               if(temp.length() == 0) ega = 0;
               else if (temp.length() == 1 && temp.charAt(0)== '.' ) ega = 0;
               else ega = Double.parseDouble(temp);
               if(ega > 10){
                    egaEditText.setText("10");
                    egaEditText.setSelection(egaEditText.length());
                    ega = 10;
               }
               calculateCDAI();
               calculateSDAI();
          }
     };

     TextWatcher crpCountChecker = new TextWatcher(){
          public void afterTextChanged(Editable s){

          }
          public void beforeTextChanged(CharSequence s, int start, int count, int after){

          }
          public void onTextChanged(CharSequence s, int start, int before, int count){
               String temp = crpEditText.getText().toString();
               if(temp.length() == 0) crp = 0;
               else crp = Double.parseDouble(crpEditText.getText().toString());
               if(crp> 151){
                    crpEditText.setText("150");
                    crpEditText.setSelection(crpEditText.length());
                    crp = 150;
               }
               calculateCDAI();
               calculateSDAI();
          }
     };
     @Override
     protected void onCreate(Bundle savedInstanceState) {
          super.onCreate(savedInstanceState);
          setContentView(R.layout.activity_csdai);
          db = new DBHelper(this);
          tenderJointEditText = (EditText)findViewById(R.id.tenderJointcsdai_Edit);
          swollenJointEditText = (EditText)findViewById(R.id.swollenJointcsdai_Edit);
          pgaEditText = (EditText)findViewById(R.id.pgacsdai_Edit);
          egaEditText = (EditText)findViewById(R.id.egacsdai_Edit);
          crpEditText = (EditText) findViewById(R.id.crpcsdai_Edit);

          tenderJointEditText.addTextChangedListener(tenderJointCountChecker);
          swollenJointEditText.addTextChangedListener(swollenJointCountChecker);
          pgaEditText.addTextChangedListener(pgaCountChecker);
          egaEditText.addTextChangedListener(egaCountChecker);
          crpEditText.addTextChangedListener(crpCountChecker);

          patient_id = getIntent().getExtras().getInt("patient_id");
     }


     @Override
     public boolean onCreateOptionsMenu(Menu menu) {
          // Inflate the menu; this adds items to the action bar if it is present.
          getMenuInflater().inflate(R.menu.menu_csdai, menu);
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
          boolean checked = ((RadioButton)view).isChecked();

          switch(view.getId()){
               case R.id.mglCSDAIRadio:{
                    if(checked){
                         RadioButton rb = (RadioButton)findViewById(R.id.mgdlCSDAIRadio);
                         crpEditText.setHint("mg/l");
                         rb.setChecked(false);
                         crp_mgl = true;
                         break;
                    }
               }
               case R.id.mgdlCSDAIRadio:{
                    if(checked){
                         RadioButton rb = (RadioButton)findViewById(R.id.mglCSDAIRadio);
                         crpEditText.setHint("mg/dl");
                         rb.setChecked(false);
                         crp_mgl=false;
                    }
               }
          }
          calculateCDAI();
          calculateSDAI();
     }

     public void onButtonClicked(View view){
          boolean pressed = ((Button)view).isPressed();
          if(view.getId() == R.id.csdai_saveButton) openPopup();
     }

     public void openPopup(){
          final AlertDialog.Builder popupbd = new AlertDialog.Builder(this);
          popupbd.setTitle("Save CDAI/SDAI Score?");
          popupbd.setCancelable(true);
          popupbd.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
               public void onClick(DialogInterface dialog, int data) {
               }
          });
          popupbd.setPositiveButton("Save",new DialogInterface.OnClickListener() {
               public void onClick(DialogInterface dialog, int data) {
                    if(db.insertPatientCDAI_SDAI(patient_id,cdai,sdai)) {
                         Toast.makeText(getApplicationContext(), "Successfully Saved!", Toast.LENGTH_SHORT).show();
                         Intent intent = new Intent(CSdai_Activity.this, DiseaseActivity_Activity.class);
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
