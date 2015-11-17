package com.example.patrick.radoctor;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;


public class DiseaseActivity_Activity extends ActionBarActivity {
     int patient_id;
     TextView das28, das28LastUpdate,cdai,sdai,csdaiLastUpdate;
     SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
     Date date;
     DBHelper db;
     @Override
     protected void onCreate(Bundle savedInstanceState) {
          super.onCreate(savedInstanceState);
          setContentView(R.layout.activity_disease);
          patient_id = getIntent().getExtras().getInt("patient_id");
          db = new DBHelper(this);
          das28 = (TextView)findViewById(R.id.diseaseActivitydas28Score);
          das28LastUpdate = (TextView)findViewById(R.id.diseaseActivitydas28lastUpdate);
          cdai = (TextView)findViewById(R.id.diseaseActivitycdaiScore);
          sdai = (TextView)findViewById(R.id.diseaseActivitysdai8Score);
          csdaiLastUpdate = (TextView)findViewById(R.id.diseaseActivitycsdailastUpdate);
          DiseaseActivity_Object dao = db.getLatestDiseaseActivity(patient_id);
          try {
               if (dao.isHas_record_das28()) {
                    das28.setText("DAS28 Score: " + Math.floor(dao.getDas28()*100)/100);
                    date = formatter.parse(dao.getLastUpdatedDas28());
                    das28LastUpdate.setText("Last Updated: " + date.toString());
               }
               if (dao.isHas_record_csdai()){
                    cdai.setText("CDAI Score: " + dao.getCdai());
                    sdai.setText("SDAI Score: " + dao.getSdai());
                    date = formatter.parse(dao.getLastUpdatedCSDai());
                    csdaiLastUpdate.setText("Last Updated: " + date.toString());
               }
          }catch(Exception e){
               e.toString();
          }
     }


     @Override
     public boolean onCreateOptionsMenu(Menu menu) {
          // Inflate the menu; this adds items to the action bar if it is present.
          getMenuInflater().inflate(R.menu.menu_disease, menu);
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

     public void onTextViewClicked(View view){
          boolean clicked = ((TextView)view).isPressed();
          switch(view.getId()){
               case R.id.das28Button:{
                    if(clicked){
                         Intent das28Screen = new Intent(DiseaseActivity_Activity.this,Das28_Activity.class);
                         das28Screen.putExtra("patient_id",patient_id);
                         startActivity(das28Screen);
                         break;
                    }
               }
               case R.id.cdaiSdaiButton:{
                    if(clicked){
                         Intent csdaiScreen = new Intent(DiseaseActivity_Activity.this,CSdai_Activity.class);
                         csdaiScreen.putExtra("patient_id",patient_id);
                         startActivity(csdaiScreen);
                    }
                         break;
               }
          }
     }
}
