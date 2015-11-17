package com.example.patrick.radoctor;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.Calendar;


public class UpdateDrugs_Activity extends ActionBarActivity {
     Spinner mtxAdmission;
     Spinner mtxFrequency;
     static TextView mtxDate;
     int patient_id;
     static int myear;
     static int mmonth;
     static int mday;
     @Override
     protected void onCreate(Bundle savedInstanceState) {
          super.onCreate(savedInstanceState);
          setContentView(R.layout.activity_update_drugs_);
          mtxAdmission = (Spinner)findViewById(R.id.spinnerMtx);
          mtxFrequency = (Spinner)findViewById(R.id.spinnerMtxFrequency);
          mtxDate = (TextView)findViewById(R.id.mtxDate);
          addItemsOnSpinner1();
          addItemsOnSpinner2();
          final Calendar c = Calendar.getInstance();
          myear = c.get(Calendar.YEAR);
          mmonth = c.get(Calendar.MONTH);
          mday = c.get(Calendar.DAY_OF_MONTH);
          updateDisplay();
          mtxDate.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                   DialogFragment df = new DatePickerFragment();
                   df.show(getFragmentManager(),"datePicker");
               }
          });

     }
     public static class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener{
          @Override
          public Dialog onCreateDialog(Bundle savedInstancesState){
               return new DatePickerDialog(getActivity(), this, myear, mmonth, mday);
          }
          public void onDateSet(DatePicker view, int year, int month, int day) {
               myear = year;
               mmonth = month;
               mday = day;
               updateDisplay();
          }
     }

     private static void updateDisplay() {
          mtxDate.setText(mmonth+1 + "/"+mday+"/"+myear);
     }

     private void addItemsOnSpinner1(){
          ArrayAdapter<CharSequence> adapter1 =  ArrayAdapter.createFromResource(this,R.array.drugsAdmissionSpinner_Array,android.R.layout.simple_spinner_item);
          adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
          mtxAdmission.setAdapter(adapter1);
     }
     private void addItemsOnSpinner2(){
          ArrayAdapter<CharSequence> adapter1 =  ArrayAdapter.createFromResource(this,R.array.drugsFrequencySpinner_Array,android.R.layout.simple_spinner_item);
          adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
          mtxFrequency.setAdapter(adapter1);
     }

     @Override
     public boolean onCreateOptionsMenu(Menu menu) {
          // Inflate the menu; this adds items to the action bar if it is present.
          getMenuInflater().inflate(R.menu.menu_update_drugs_, menu);
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
