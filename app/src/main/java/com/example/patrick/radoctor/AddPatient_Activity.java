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
import android.widget.Toast;


public class AddPatient_Activity extends ActionBarActivity implements AdapterView.OnItemSelectedListener {
     EditText nameEditText;
     EditText ageEditText;
     EditText aodEditText;
     EditText aooEditText;
     Spinner sexSpinner;
     ImageButton ib;
   //  EditText aooEditText;

     String name;
     String sex = "Male";
     int age;
     int aod;
     int aoo;
     private int PICK_IMAGE_REQUEST = 1;
     String path = "NULL";
     DBHelper db;



     @Override
     public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
          Spinner spinner = (Spinner)parent;
          sex = spinner.getItemAtPosition(position).toString();
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
          ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this,R.array.sexSpinner_array,android.R.layout.simple_spinner_item);
          adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
          sexSpinner.setAdapter(adapter1);
          sexSpinner.setOnItemSelectedListener(this);
          ib = (ImageButton)findViewById(R.id.addImage);
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
                    if(clicked) openPopup();
                    break;
               }
               case R.id.cancelButtonAddPatient: {
                    if (clicked) startActivity(intent);
                    break;
               }
               case R.id.addImage:{
                    if(clicked){
                         Intent intent2 = new Intent();
                         intent2.setType("image/*");
                         intent2.setAction(Intent.ACTION_GET_CONTENT);
                         startActivityForResult(Intent.createChooser(intent2, "Select Picture"), PICK_IMAGE_REQUEST);
                    }
               }
          }
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
                              Toast.makeText(getApplicationContext(), "Invalid input! Age of diagnosis is greater than current age", Toast.LENGTH_SHORT).show();
                         else if (age < aoo)
                              Toast.makeText(getApplicationContext(), "Invalid input! Age of onset is greater than current age", Toast.LENGTH_SHORT).show();
                         else if (aod < aoo)
                              Toast.makeText(getApplicationContext(), "Invalid input! Age of onset is greater than age of diagnosis", Toast.LENGTH_SHORT).show();
                         else if (db.insertPatient(name, age, sex, aod, aoo,path)) {
                              Toast.makeText(getApplicationContext(), "Successfully Saved!", Toast.LENGTH_SHORT).show();
                              nameEditText.setText("");
                              ageEditText.setText("");
                              aodEditText.setText("");
                              aooEditText.setText("");
                              ib.setImageDrawable(null);
                         } else
                              Toast.makeText(getApplicationContext(), "Something Bad Happened =( Try saving again", Toast.LENGTH_SHORT).show();
                    }catch(Exception e){
                         Toast.makeText(getApplicationContext(), "Invalid input!", Toast.LENGTH_SHORT).show();
                    }
               }
          });
          AlertDialog popup = popupbd.create();
          popup.show();
     }
}
