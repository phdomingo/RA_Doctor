package com.example.patrick.radoctor;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;


public class Drugs_Activity extends ActionBarActivity {
     int patient_id;
     DBHelper db;
     TextView admMtx,doseMtx,luMtx;
     TextView admAza,doseAza,luAza;
     TextView admHcqs,doseHcqs,luHcqs;
     TextView admSterPred,doseSterPred,luSterPred;
     TextView admSterMeth,doseSterMeth,luSterMeth;
     SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

     @Override
     protected void onCreate(Bundle savedInstanceState) {
          super.onCreate(savedInstanceState);
          setContentView(R.layout.activity_drugs);
          patient_id = getIntent().getExtras().getInt("patient_id");
          db = new DBHelper(this);
          Drugs drugs = db.getLatestDrugs(patient_id);
          if(drugs.isHasMtx()) setMtx(drugs.getMtx());
          if(drugs.isHasMAza()) setAza(drugs.getAza());
          if(drugs.isHasHcqs()) setHcqs(drugs.getHcqs());
          if(drugs.isHasSterPred()) setSterPred(drugs.getSterPred());
          if(drugs.isHasSterMeth()) setSterMeth(drugs.getSterMeth());
     }

     private void setSterMeth(DrugsSpecific sterMeth) {
          admSterMeth = (TextView) findViewById(R.id.admSterMeth);
          doseSterMeth = (TextView) findViewById(R.id.doseSterMeth);
          luSterMeth = (TextView) findViewById(R.id.luSterMeth);
          Date lastUpdate;
          admSterMeth.setText(admSterMeth.getText() + sterMeth.getAdmission());
          doseSterMeth.setText(doseSterMeth.getText() + String.valueOf(sterMeth.getDose()) + " mg");
          try{
               lastUpdate = formatter.parse(sterMeth.getDate());
               luSterMeth.setText(luSterMeth.getText() + lastUpdate.toString());
          }catch(Exception e){e.toString();}
     }

     private void setSterPred(DrugsSpecific sterPred) {
          admSterPred = (TextView) findViewById(R.id.admSterPred);
          doseSterPred = (TextView) findViewById(R.id.doseSterPred);
          luSterPred = (TextView) findViewById(R.id.luSterPred);
          Date lastUpdate;
          admSterPred.setText(admSterPred.getText() + sterPred.getAdmission());
          doseSterPred.setText(doseSterPred.getText() + String.valueOf(sterPred.getDose()) + " mg");
          try{
               lastUpdate = formatter.parse(sterPred.getDate());
               luSterPred.setText(luSterPred.getText() + lastUpdate.toString());
          }catch(Exception e){e.toString();}
     }

     private void setHcqs(DrugsSpecific hcqs) {
          admHcqs = (TextView) findViewById(R.id.admHcqs);
          doseHcqs = (TextView) findViewById(R.id.doseHcqs);
          luHcqs = (TextView) findViewById(R.id.luHcqs);
          Date lastUpdate;
          admHcqs.setText(admHcqs.getText() + hcqs.getAdmission());
          doseHcqs.setText(doseHcqs.getText() + String.valueOf(hcqs.getDose()) + " mg");
          try{
               lastUpdate = formatter.parse(hcqs.getDate());
               luHcqs.setText(luHcqs.getText() + lastUpdate.toString());
          }catch(Exception e){e.toString();}
     }

     private void setAza(DrugsSpecific aza) {
          admAza = (TextView) findViewById(R.id.admAza);
          doseAza = (TextView) findViewById(R.id.doseAza);
          luAza = (TextView) findViewById(R.id.luAza);
          Date lastUpdate;
          admAza.setText(admAza.getText() + aza.getAdmission());
          doseAza.setText(doseAza.getText() + String.valueOf(aza.getDose()) + " mg");
          try{
               lastUpdate = formatter.parse(aza.getDate());
               luAza.setText(luAza.getText() + lastUpdate.toString());
          }catch(Exception e){e.toString();}
     }

     private void setMtx(DrugsSpecific mtx) {
          admMtx = (TextView) findViewById(R.id.admMtx);
          doseMtx = (TextView) findViewById(R.id.doseMtx);
          luMtx = (TextView) findViewById(R.id.luMtx);
          Date lastUpdate;
          admMtx.setText(admMtx.getText() + mtx.getAdmission());
          doseMtx.setText(doseMtx.getText() + String.valueOf(mtx.getDose()) + " mg" );
          try{
               lastUpdate = formatter.parse(mtx.getDate());
               luMtx.setText(luMtx.getText() + lastUpdate.toString());
          }catch(Exception e){e.toString();}
     }


     @Override
     public boolean onCreateOptionsMenu(Menu menu) {
          // Inflate the menu; this adds items to the action bar if it is present.
          getMenuInflater().inflate(R.menu.menu_drugs, menu);
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
          Intent intent = new Intent(Drugs_Activity.this, UpdateDrugs_Activity.class);
          intent.putExtra("patient_id",patient_id);
          boolean pressed = ((LinearLayout)view).isPressed();
          switch(view.getId()){
               case R.id.drugsMtxLayout:{
                    intent.putExtra("drugs", 1);
                    break;
               }
               case R.id.drugsAzaLayout:{
                    intent.putExtra("drugs", 2);
                    break;
               }
               case R.id.drugsHCQSLayout:{
                    intent.putExtra("drugs", 3);
                    break;
               }
               case R.id.drugsSterPredLayout:{
                    intent.putExtra("drugs", 4);
                    break;
               }
               case R.id.drugsSterMethLayout:{
                    intent.putExtra("drugs", 5);
                    break;
               }
          }
          startActivity(intent);
     }


}
