package com.example.patrick.radoctor;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;


public class RACriteria_Activity extends ActionBarActivity implements AdapterView.OnItemSelectedListener {
     int jointScore = 0;
     int serologyScore = 0;
     int durationScore = 0;
     int arpScore = 0;
     String patient_id;
     public void calculateRA(){
          TextView scoreText = (TextView) findViewById(R.id.scoreLabel);
          TextView label = (TextView) findViewById(R.id.classifiedLabel);
          LinearLayout ll = (LinearLayout) findViewById(R.id.linearLayout2);
          int currentScore = jointScore + serologyScore + durationScore + arpScore;

          scoreText.setText(String.valueOf(currentScore) + "/10");
          if(currentScore < 6){
               label.setText("Not classified as RA");
               ll.setBackgroundColor(Color.parseColor("#66FF00"));
          }
          else{
               label.setText("Classified as RA");
               ll.setBackgroundColor(Color.parseColor("#FF0000"));
          }
     }

     public void onItemSelected(AdapterView<?> parent, View view,
                                int pos, long id) {
          // An item was selected. You can retrieve the selected item using
          // parent.getItemAtPosition(pos)
          Spinner spinner = (Spinner)parent;
          parent.getItemAtPosition(pos);
          if(spinner.getId() == R.id.jointsSpinner ){
               switch(pos){
                    case 0:{
                         jointScore = 0;
                         break;
                    }
                    case 1:{
                         jointScore = 1;
                         break;
                    }
                    case 2:{
                         jointScore = 2;
                         break;
                    }
                    case 3:{
                         jointScore = 3;
                         break;
                    }
                    case 4:{
                         jointScore = 5;
                         break;
                    }
               }
          }
          else if (spinner.getId() == R.id.serologySpinner){
               switch(pos) {
                    case 0: {
                         serologyScore = 0;
                         break;
                    }
                    case 1: {
                         serologyScore = 2;
                         break;
                    }
                    case 2: {
                         serologyScore = 3;
                         break;
                    }
               }
          }
          else if (spinner.getId() == R.id.durationSpinner){
               switch(pos){
                    case 0:{
                         durationScore = 0;
                         break;
                    }
                    case 1:{
                         durationScore = 1;
                         break;
                    }
               }
          }
          else if (spinner.getId()==R.id.APRSpinner){
               switch(pos){
                    case 0:{
                         arpScore = 0;
                         break;
                    }
                    case 1:{
                         arpScore = 1;
                         break;
                    }
               }
          }
          calculateRA();
     }

     public void onNothingSelected(AdapterView<?> parent) {
          // Another interface callback
          parent.getItemAtPosition(0);
     }

     @Override
     protected void onCreate(Bundle savedInstanceState) {
          super.onCreate(savedInstanceState);
          setContentView(R.layout.activity_racriteria);
          Spinner jointSpinner = (Spinner)findViewById(R.id.jointsSpinner);
          Spinner durationSpinner = (Spinner)findViewById(R.id.durationSpinner);
          Spinner serologySpinner = (Spinner)findViewById(R.id.serologySpinner);
          Spinner aprSpinner = (Spinner)findViewById(R.id.APRSpinner);

          ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this,R.array.jointSpinner_array,android.R.layout.simple_spinner_item);
          adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
          jointSpinner.setAdapter(adapter1);

          ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this,R.array.serologySpinner_array,android.R.layout.simple_spinner_item);
          adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
          serologySpinner.setAdapter(adapter2);

          ArrayAdapter<CharSequence> adapter3 = ArrayAdapter.createFromResource(this,R.array.durationSpinner_array,android.R.layout.simple_spinner_item);
          adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
          durationSpinner.setAdapter(adapter3);

          ArrayAdapter<CharSequence> adapter4 = ArrayAdapter.createFromResource(this,R.array.aprSpinner_array,android.R.layout.simple_spinner_item);
          adapter4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
          aprSpinner.setAdapter(adapter4);

          jointSpinner.setOnItemSelectedListener(this);
          serologySpinner.setOnItemSelectedListener(this);
          durationSpinner.setOnItemSelectedListener(this);
          aprSpinner.setOnItemSelectedListener(this);

          Intent intent = getIntent();
          patient_id = intent.getStringExtra("patient");

     }


     @Override
     public boolean onCreateOptionsMenu(Menu menu) {
          // Inflate the menu; this adds items to the action bar if it is present.
          getMenuInflater().inflate(R.menu.menu_racriteria, menu);
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
}
