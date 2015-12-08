package com.example.patrick.radoctor;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;


public class Biologics_Activity extends ActionBarActivity {
     int patient_id;
     DBHelper db;
     SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
     TextView doseInflix, infInflix, luInflix;
     TextView doseInflix2, infInflix2, luInflix2;
     TextView doseRitux, infRitux, luRitux;
     TextView doseTocili, infTocili, luTocili;
     TextView doseEtaner, infEtaner, luEtaner;
     Biologics biologics;
     @Override
     protected void onCreate(Bundle savedInstanceState) {
          super.onCreate(savedInstanceState);
          setContentView(R.layout.activity_biologics);
          patient_id = getIntent().getExtras().getInt("patient_id");
          db = new DBHelper(this);
          biologics = db.getLatestBiologics(patient_id);
          if(biologics.isHasInflix()) setInflix(biologics.getInflix());
          if(biologics.isHasInflix2()) setInflix2(biologics.getInflix2());
          if(biologics.isHasRitux()) setRitux(biologics.getRitux());
          if(biologics.isHasTocili()) setTocili(biologics.getTocili());
          if(biologics.isHasEtaner()) setEtaner(biologics.getEtaner());
     }

     private void setEtaner(BiologicsSpecific etaner) {
          doseEtaner = (TextView)findViewById(R.id.doseEtaner);
          infEtaner = (TextView)findViewById(R.id.infEtaner);
          luEtaner = (TextView)findViewById(R.id.luEtaner);
          Date lastUpdated;
          doseEtaner.setText(doseEtaner.getText() + String.valueOf(etaner.getDose()) + " mg");
          infEtaner.setText(infEtaner.getText() + String.valueOf(etaner.getInf()));
          try{
               lastUpdated = formatter.parse(etaner.getLastUpdated());
               luEtaner.setText(luEtaner.getText() + lastUpdated.toString());
          }catch(Exception e){};
     }

     private void setTocili(BiologicsSpecific tocili) {
          doseTocili = (TextView)findViewById(R.id.doseTocili);
          infTocili = (TextView)findViewById(R.id.infTocili);
          luTocili = (TextView)findViewById(R.id.luTocili);Date lastUpdated;
          doseTocili.setText(doseTocili.getText() + String.valueOf(tocili.getDose()) + " mg");
          infTocili.setText(infTocili.getText() + String.valueOf(tocili.getInf()));
          try{
               lastUpdated = formatter.parse(tocili.getLastUpdated());
               luTocili.setText(luTocili.getText() + lastUpdated.toString());
          }catch(Exception e){};

     }

     private void setRitux(BiologicsSpecific ritux) {
          doseRitux = (TextView)findViewById(R.id.doseRitux);
          infRitux = (TextView)findViewById(R.id.infRitux);
          luRitux = (TextView)findViewById(R.id.luRitux);
          Date lastUpdated;
          doseRitux.setText(doseRitux.getText() + String.valueOf(ritux.getDose())+ " mg");
          infRitux.setText(infRitux.getText() + String.valueOf(ritux.getInf()));
          try{
               lastUpdated = formatter.parse(ritux.getLastUpdated());
               luRitux.setText(luRitux.getText() + lastUpdated.toString());
          }catch(Exception e){};
     }

     private void setInflix2(BiologicsSpecific inflix2) {
          doseInflix2 = (TextView)findViewById(R.id.doseInflix2);
          infInflix2 = (TextView)findViewById(R.id.infInflix2);
          luInflix2 = (TextView)findViewById(R.id.luInflix2);
          Date lastUpdated;
          doseInflix2.setText(doseInflix2.getText() + String.valueOf(inflix2.getDose())+ " mg");
          infInflix2.setText(infInflix2.getText() + String.valueOf(inflix2.getInf()));
          try{
               lastUpdated = formatter.parse(inflix2.getLastUpdated());
               luInflix2.setText(luInflix2.getText() + lastUpdated.toString());
          }catch(Exception e){};
     }

     private void setInflix(BiologicsSpecific inflix) {
          doseInflix = (TextView)findViewById(R.id.doseInflix);
          infInflix = (TextView)findViewById(R.id.infInflix);
          luInflix = (TextView)findViewById(R.id.luInflix);
          Date lastUpdated;
          doseInflix.setText(doseInflix.getText() + String.valueOf(inflix.getDose())+ " mg");
          infInflix.setText(infInflix.getText() + String.valueOf(inflix.getInf()));
          try{
               lastUpdated = formatter.parse(inflix.getLastUpdated());
               luInflix.setText(luInflix.getText() + lastUpdated.toString());
          }catch(Exception e){};
     }


     @Override
     public boolean onCreateOptionsMenu(Menu menu) {
          // Inflate the menu; this adds items to the action bar if it is present.
          getMenuInflater().inflate(R.menu.menu_biologics, menu);
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
          boolean pressed = ((LinearLayout)view).isPressed();
          Intent intent = new Intent(Biologics_Activity.this, UpdateBiologic_Activity.class);
          intent.putExtra("patient_id",patient_id);

          switch (view.getId()){
               case R.id.biologic1:{
                    intent.putExtra("biologic",1);
                    break;
               }
               case R.id.biologic2:{
                    intent.putExtra("biologic",2);
                    break;
               }
               case R.id.biologic3:{
                    intent.putExtra("biologic",3);
                    break;
               }
               case R.id.biologic4:{
                    intent.putExtra("biologic",4);
                    break;
               }
               case R.id.biologic5:{
                    intent.putExtra("biologic",5);
                    break;
               }
          }
          startActivity(intent);
     }
}
