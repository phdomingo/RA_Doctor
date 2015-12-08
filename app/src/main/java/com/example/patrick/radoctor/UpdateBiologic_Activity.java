package com.example.patrick.radoctor;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;


public class UpdateBiologic_Activity extends ActionBarActivity {
     int patient_id;
     int biologic_number;
     int dose;
     int inf;
     TextView biologicLabel;
     Spinner biologicDose;
     String inflix, inflix2, ritux, tocili, etan;
     DBHelper db;
     @Override
     protected void onCreate(Bundle savedInstanceState) {
          super.onCreate(savedInstanceState);
          setContentView(R.layout.activity_update_biologic_);
          patient_id = getIntent().getExtras().getInt("patient_id");
          db= new DBHelper(this);
          biologic_number = getIntent().getExtras().getInt("biologic");
          biologicLabel = (TextView)findViewById(R.id.updateBiologicLabel);
          biologicDose = (Spinner)findViewById(R.id.spinnerBiologicDose);
          inflix = getString(R.string.label_inflix);
          inflix2 = getString(R.string.label_inflix2);
          ritux= getString(R.string.label_ritux);
          tocili = getString(R.string.label_tocili);
          etan = getString(R.string.label_etaner);
          setBiologicLabel();
          setSpinners();
          setInf();

     }

     private void setInf() {
          Biologics bio = db.getLatestBiologics(patient_id);
          inf = 1;
          switch(biologic_number){
               case 1:{
                    if(bio.isHasInflix()){
                         inf = bio.getInflix().getInf() + 1;
                    }
                    break;
               }
               case 2:{
                    if(bio.isHasInflix2()){
                         inf = bio.getInflix2().getInf() + 1;
                    }
                    break;
               }
               case 3:{
                    if(bio.isHasRitux()){
                         inf = bio.getRitux().getInf() + 1;
                    }
                    break;
               }
               case 4:{
                    if(bio.isHasTocili()){
                         inf = bio.getTocili().getInf() + 1;
                    }
                    break;
               }
               case 5:{
                    if(bio.isHasEtaner()){
                         inf = bio.getEtaner().getInf() + 1;
                    }
                    break;
               }
          }
          TextView infNumber = (TextView)findViewById(R.id.updateBiologicInf);
          infNumber.setText(infNumber.getText() + String.valueOf(inf));
     }

     private void setSpinners() {
          ArrayAdapter<CharSequence> adapter1;
          switch(biologic_number){
               case 1:{
                    adapter1 =  ArrayAdapter.createFromResource(this,R.array.inflixSpinner_array,android.R.layout.simple_spinner_item);
                    break;
               }
               case 2:{
                    adapter1 =  ArrayAdapter.createFromResource(this,R.array.inflixSpinner_array,android.R.layout.simple_spinner_item);
                    break;
               }
               case 3: {
                    adapter1 = ArrayAdapter.createFromResource(this, R.array.rituxSpinner_array, android.R.layout.simple_spinner_item);
                    break;
               }
               case 4:{
                    adapter1 = ArrayAdapter.createFromResource(this, R.array.tociliSpinner_array, android.R.layout.simple_spinner_item);
                    break;
               }
               default:{
                    adapter1 =  ArrayAdapter.createFromResource(this, R.array.etanerSpinner_array, android.R.layout.simple_spinner_item);
               }

          }
          biologicDose.setAdapter(adapter1);
     }

     private void setBiologicLabel() {
          String temp = "";
          switch(biologic_number){
               case 1:{
                    temp = inflix;
                    break;
               }
               case 2:{
                    temp = inflix2;
                    break;
               }
               case 3:{
                    temp =ritux;
                    break;
               }
               case 4:{
                    temp = tocili;
                    break;
               }
               case 5:{
                    temp = etan;
                    break;
               }
          }
          biologicLabel.setText(temp);
     }


     @Override
     public boolean onCreateOptionsMenu(Menu menu) {
          // Inflate the menu; this adds items to the action bar if it is present.
          getMenuInflater().inflate(R.menu.menu_update_biologic_, menu);
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
               case R.id.biologicsCancel:{
                    Intent intent = new Intent(UpdateBiologic_Activity.this, Biologics_Activity.class);
                    intent.putExtra("patient_id",patient_id);
                    break;
               }
               case R.id.biologicsSave:{
                    openPopup();
               }
          }
     }

     private void openPopup() {
          final AlertDialog.Builder popupbd = new AlertDialog.Builder(this);
          popupbd.setTitle("Save biologic update?");
          popupbd.setCancelable(true);
          String temp = (String) biologicDose.getSelectedItem();
          temp = temp.substring(0,temp.length()-3);
          dose = Integer.parseInt(temp);
          popupbd.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
               public void onClick(DialogInterface dialog, int data) {
               }
          });
          popupbd.setPositiveButton("Save",new DialogInterface.OnClickListener() {
               public void onClick(DialogInterface dialog, int data) {
                    try{
                         if(db.insertPatientBiologics(patient_id,biologic_number,dose,inf)){
                              Toast.makeText(getApplicationContext(), "Successfully saved!", Toast.LENGTH_SHORT).show();
                         }
                         Intent intent = new Intent(UpdateBiologic_Activity.this,Biologics_Activity.class);
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
