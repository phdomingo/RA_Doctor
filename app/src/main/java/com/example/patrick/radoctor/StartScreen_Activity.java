package com.example.patrick.radoctor;

import android.app.ActionBar;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;


public class StartScreen_Activity extends ActionBarActivity {
     DBHelper db;
     private ListView obj;

     @Override
     protected void onCreate(Bundle savedInstanceState) {
          super.onCreate(savedInstanceState);
          setContentView(R.layout.activity_start_screen);
          db = new DBHelper(this);
          obj = (ListView)findViewById(R.id.listViewStartScreen);

          ArrayList<String> arrayList = db.getAllPatients();

          ArrayAdapter arrayAdapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,arrayList);

         obj.setAdapter(arrayAdapter);
          obj.setOnItemClickListener(new AdapterView.OnItemClickListener() {
               @Override
               public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Intent intent = new Intent(getApplicationContext(),PatientInfo_Activity.class);
                    System.out.println("Position: " + position);
                    intent.putExtra("patient_id",position+1);
                    startActivity(intent);
               }
          });
     }


     @Override
     public boolean onCreateOptionsMenu(Menu menu) {
          // Inflate the menu; this adds items to the action bar if it is present.
          getMenuInflater().inflate(R.menu.menu_start_screen, menu);
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
          else if(id == R.id.action_addPatient){
               Intent intent = new Intent(StartScreen_Activity.this,AddPatient_Activity.class);
               startActivity(intent);
          }
          return super.onOptionsItemSelected(item);
     }

     public void onClick(View view){
          Intent intent;
          if(view.getId() == R.id.addPatientButton)
               intent = new Intent(StartScreen_Activity.this,AddPatient_Activity.class);
          else
               intent = new Intent(StartScreen_Activity.this,PatientStatistics_Activity.class);
          startActivity(intent);
     }
}
