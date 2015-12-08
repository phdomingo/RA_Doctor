package com.example.patrick.radoctor;

import android.app.AlertDialog;
import android.content.DialogInterface;
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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


public class AddPatient_Activity extends ActionBarActivity implements AdapterView.OnItemSelectedListener {
     EditText nameEditText, ageEditText, aodEditText, aooEditText;
     Spinner sexSpinner, joint, rf, acpa, duration, crp, esr;
     TextView raLabel;
     ImageButton ib;

   //  EditText aooEditText;

     String name, sex = "Male", path = "NULL", ra = "Rheumatoid Arthritis Criteria (Current Score = ";
     int age, aod, aoo;
     int serologyScore = 0, durationScore = 0, jointScore = 0, arpScore = 0;
     int ra_score = 0;
     private int PICK_IMAGE_REQUEST = 1;
     DBHelper db;



     @Override
     public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
          Spinner spinner = (Spinner)parent;
          parent.getItemAtPosition(pos);
          int parent_id = spinner.getId();
          if(parent_id==R.id.sexSpinner)
               sex = spinner.getItemAtPosition(pos).toString();
          if(parent_id==joint.getId()){
               if (pos == 0)
                    jointScore = 0;
               else if (pos == 1)
                    jointScore = 1;
               else if (pos == 2)
                    jointScore = 2;
               else if (pos==3)
                    jointScore = 3;
               else jointScore = 5;
          }
          else if(parent_id==duration.getId()){
               if (pos == 0)
                    durationScore = 0;
               else durationScore = 1;
          }
          else if(parent_id == rf.getId()){
               int score_acpa = acpa.getSelectedItemPosition();
               if(pos == 2)
                    serologyScore = 3;
               else if(score_acpa == 2)
                    serologyScore = 3;
               else if(pos == 1)
                    serologyScore = 2;
               else if(score_acpa == 1)
                    serologyScore = 2;
               else serologyScore = 0;
          }
          else if(parent_id == acpa.getId()){
               int score_rf = rf.getSelectedItemPosition();
               if(pos == 2)
                    serologyScore = 3;
               else if(score_rf == 2)
                    serologyScore = 3;
               else if(pos == 1)
                    serologyScore = 2;
               else if(score_rf == 1)
                    serologyScore = 2;
               else serologyScore = 0;
          }
          else if(parent_id == crp.getId()){
               int score_esr = esr.getSelectedItemPosition();
               if(pos == 0 && score_esr == 0)
                    arpScore = 0;
               else arpScore = 1;
          }
          else if(parent_id == esr.getId()){
               int score_crp = crp.getSelectedItemPosition();
               if(pos == 0 && score_crp == 0)
                    arpScore = 0;
               else arpScore = 1;
          }
          ra_score = jointScore+serologyScore+durationScore+arpScore;
          raLabel.setText(ra + ra_score + ")");
     }

     @Override
     public void onNothingSelected(AdapterView<?> parent) {
          sex = "Male";
     }

     @Override
     protected void onCreate(Bundle savedInstanceState) {
          super.onCreate(savedInstanceState);
          setContentView(R.layout.activity_add_patient);
          db = new DBHelper (this);
          nameEditText = (EditText)findViewById(R.id.nameEditText);
          ageEditText = (EditText)findViewById(R.id.ageEditText);
          aodEditText = (EditText)findViewById(R.id.aodEditText);
          aooEditText = (EditText)findViewById(R.id.aooEditText);
          sexSpinner = (Spinner)findViewById(R.id.sexSpinner);
     //     ib = (ImageButton)findViewById(R.id.addImage);
          joint = (Spinner)findViewById(R.id.spinnerJoints);
          rf = (Spinner)findViewById(R.id.spinnerRF);
          acpa = (Spinner)findViewById(R.id.spinnerAcpa);
          duration = (Spinner)findViewById(R.id.spinnerDuration);
          esr = (Spinner)findViewById(R.id.spinnerEsr);
          crp = (Spinner)findViewById(R.id.spinnerCrp);
          raLabel = (TextView)findViewById(R.id.RACriteriaLabel);
          setSexSpinner();
          setRASpinner();
     }

     private void setRASpinner(){
          ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this,R.array.jointSpinner_array,android.R.layout.simple_spinner_item);
          ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this,R.array.rfSpinner_array,android.R.layout.simple_spinner_item);
          ArrayAdapter<CharSequence> adapter3 = ArrayAdapter.createFromResource(this,R.array.acpaSpinner_array,android.R.layout.simple_spinner_item);
          ArrayAdapter<CharSequence> adapter4 = ArrayAdapter.createFromResource(this,R.array.durationSpinner_array,android.R.layout.simple_spinner_item);
          ArrayAdapter<CharSequence> adapter5 = ArrayAdapter.createFromResource(this,R.array.esrSpinner_array,android.R.layout.simple_spinner_item);
          ArrayAdapter<CharSequence> adapter6 = ArrayAdapter.createFromResource(this,R.array.crpSpinner_array,android.R.layout.simple_spinner_item);
          adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
          adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
          adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
          adapter4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
          adapter5.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
          adapter6.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
          joint.setAdapter(adapter1);
          rf.setAdapter(adapter2);
          acpa.setAdapter(adapter3);
          duration.setAdapter(adapter4);
          esr.setAdapter(adapter5);
          crp.setAdapter(adapter6);
          joint.setOnItemSelectedListener(this);
          rf.setOnItemSelectedListener(this);
          acpa.setOnItemSelectedListener(this);
          duration.setOnItemSelectedListener(this);
          esr.setOnItemSelectedListener(this);
          crp.setOnItemSelectedListener(this);
     }

     private void setSexSpinner(){
          ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this,R.array.sexSpinner_array,android.R.layout.simple_spinner_item);
          adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
          sexSpinner.setAdapter(adapter1);
          sexSpinner.setOnItemSelectedListener(this);
     }

     @Override
     public boolean onCreateOptionsMenu(Menu menu) {
          // Inflate the menu; this adds items to the action bar if it is present.
          getMenuInflater().inflate(R.menu.menu_add_patient, menu);
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
          Intent intent = new Intent(AddPatient_Activity.this,StartScreen_Activity.class);
          boolean clicked = view.isPressed();
          switch(view.getId()){
               case R.id.saveButtonAddPatient:{
                    if(ra_score < 6) openErrMess(5);
                    else openPopup();
                    break;
               }
               case R.id.cancelButtonAddPatient: {
                    if (clicked) startActivity(intent);
                    break;
               }
     /*          case R.id.addImage:{
                    if(clicked){
                         Intent intent2 = new Intent();
                         intent2.setType("image/*");
                         intent2.setAction(Intent.ACTION_GET_CONTENT);
                         startActivityForResult(Intent.createChooser(intent2, "Select Picture"), PICK_IMAGE_REQUEST);
                    }
               } */
          }
     }

     private void openErrMess(int err_num) {
          final AlertDialog.Builder popupbd = new AlertDialog.Builder(this);
          if(err_num == 1) popupbd.setTitle("Invalid input! Age of diagnosis is greater than current age");
          else if(err_num==2) popupbd.setTitle("Invalid input! Age of onset is greater than current age");
          else if(err_num==3) popupbd.setTitle("Invalid input! Age of onset is greater than age of diagnosis");
          else if(err_num==4) popupbd.setTitle("Invalid input!");
          else if(err_num==5) popupbd.setTitle("Error! RA Criteria is less than 6! Not classified as RA!");
          else popupbd.setTitle("Something bad happened =( Try saving again");
          popupbd.setCancelable(true);
          popupbd.setPositiveButton("OK", new DialogInterface.OnClickListener() {
               public void onClick(DialogInterface dialog, int data) {
               }
          });
          AlertDialog popup = popupbd.create();
          popup.show();
     }

     @Override
     protected void onActivityResult(int requestCode, int resultCode, Intent data){
          super.onActivityResult(requestCode, resultCode, data);
          if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
               Uri uri = data.getData();
               try{
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(),uri);
                    ib.setImageBitmap(bitmap);
                    String[] projection = { MediaStore.Images.Media.DATA };
                    Cursor cursor = getContentResolver().query(uri, projection, null, null, null);
                    cursor.moveToFirst();
                    int columnIndex = cursor.getColumnIndex(projection[0]);
                    path = cursor.getString(columnIndex);
                    cursor.close();
               }catch(Exception e){
                    e.printStackTrace();
               }
          }
     }

     public void openPopup(){


          final AlertDialog.Builder popupbd = new AlertDialog.Builder(this);
          popupbd.setTitle("Add Patient");
          popupbd.setCancelable(true);

          popupbd.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
               public void onClick(DialogInterface dialog, int data) {
               }
          });

          popupbd.setPositiveButton("Save",new DialogInterface.OnClickListener(){
               public void onClick(DialogInterface dialog, int data) {
                    try {
                         name = nameEditText.getText().toString();
                         age = Integer.parseInt(ageEditText.getText().toString());
                         aod = Integer.parseInt(aodEditText.getText().toString());
                         aoo = Integer.parseInt(aooEditText.getText().toString());
                         if (age < aod)
                              openErrMess(1);
                              //Toast.makeText(getApplicationContext(), "Invalid input! Age of diagnosis is greater than current age", Toast.LENGTH_SHORT).show();
                         else if (age < aoo)
                              openErrMess(2);
                            //  Toast.makeText(getApplicationContext(), "Invalid input! Age of onset is greater than current age", Toast.LENGTH_SHORT).show();
                         else if (aod < aoo)
                              openErrMess(3);
                            //  Toast.makeText(getApplicationContext(), "Invalid input! Age of onset is greater than age of diagnosis", Toast.LENGTH_SHORT).show();
                         else if (db.insertPatient(name, age, sex, aod, aoo,path, ra_score)) {
                              Toast.makeText(getApplicationContext(), "Successfully Saved!", Toast.LENGTH_SHORT).show();
                              Intent intent = new Intent(AddPatient_Activity.this, StartScreen_Activity.class);
                              startActivity(intent);
                          //    ib.setImageDrawable(null);
                         } else
                             openErrMess(6);
                    }catch(Exception e){
                         openErrMess(4);
                       //  Toast.makeText(getApplicationContext(), "Invalid input!", Toast.LENGTH_SHORT).show();
                    }
               }
          });
          AlertDialog popup = popupbd.create();
          popup.show();
     }
}
