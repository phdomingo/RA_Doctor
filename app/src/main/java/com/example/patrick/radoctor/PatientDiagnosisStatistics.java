package com.example.patrick.radoctor;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;


public class PatientDiagnosisStatistics extends ActionBarActivity {
     TextView diagEsr, diagCrp, diagSdai, diagCdai;
     TextView diagEsr2, diagCrp2, diagSdai2, diagCdai2;
     DBHelper db;
     @Override
     protected void onCreate(Bundle savedInstanceState) {
          super.onCreate(savedInstanceState);
          setContentView(R.layout.activity_patient_diagnosis_statistics);
          db = new DBHelper(this);
          diagEsr = (TextView)findViewById(R.id.diagAveEsr);
          diagCrp = (TextView)findViewById(R.id.diagAveCrp);
          diagCdai = (TextView)findViewById(R.id.diagAveCdai);
          diagSdai = (TextView)findViewById(R.id.diagAveSdai);
          diagEsr2 = (TextView)findViewById(R.id.diagAveEsr2);
          diagCrp2 = (TextView)findViewById(R.id.diagAveCrp2);
          diagCdai2 = (TextView)findViewById(R.id.diagAveCdai2);
          diagSdai2 = (TextView)findViewById(R.id.diagAveSdai2);
          setAveonDiagnosis();
          setAve();
     }

     private void setAveonDiagnosis() {
          int size = db.getAllPatients().size();
          double totdas28esr = 0, totdas28crp = 0,totcdai = 0,totsdai = 0;
          double avedas28esr, avedas28crp,avecdai,avesdai;
          DiseaseActivity_Object temp;
          int size2 = size;
          for(int i = 0; i < size; i++){
               temp = db.getScore(i+1,1);
               if(temp.isHas_record()) {
                    totdas28esr += temp.getDas28esr();
                    totdas28crp += temp.getDas28crp();
                    totcdai += temp.getCdai();
                    totsdai += temp.getSdai();
               }else size2--;
          }
          String temp2;
          avedas28esr = totdas28esr/size2;
          temp2 = String.valueOf(avedas28esr);
          temp2 = temp2.substring(0,4);
          diagEsr.setText(temp2);

          avedas28crp = totdas28crp/size2;
          temp2 = String.valueOf(avedas28crp);
          temp2 = temp2.substring(0,4);
          diagCrp.setText(temp2);

          avecdai = totcdai/size2;
          avesdai = totsdai/size2;
          diagCdai.setText(String.valueOf(avecdai));
          diagSdai.setText(String.valueOf(avesdai));
     }

     private void setAve() {
          DiseaseActivity_Object temp;
          ArrayList<DiseaseActivity_Object> dao = db.getAllScoresStatistics();
          double totdas28esr = 0, totdas28crp = 0,totcdai = 0,totsdai = 0;
          double avedas28esr, avedas28crp,avecdai,avesdai;
          int size = dao.size();
          for(int i = 0; i < size; i++){
               temp = dao.get(i);
               totdas28esr += temp.getDas28esr();
               totdas28crp += temp.getDas28crp();
               totcdai += temp.getCdai();
               totsdai += temp.getSdai();
          }
          String temp2;
          avedas28esr = totdas28esr/size;
          temp2 = String.valueOf(avedas28esr);
          temp2 = temp2.substring(0,4);
          diagEsr2.setText(temp2);

          avedas28crp = totdas28crp/size;
          temp2 = String.valueOf(avedas28crp);
          temp2 = temp2.substring(0,4);
          diagCrp2.setText(temp2);

          avecdai = totcdai/size;
          avesdai = totsdai/size;
          diagCdai2.setText(String.valueOf(avecdai));
          diagSdai2.setText(String.valueOf(avesdai));

     }


     @Override
     public boolean onCreateOptionsMenu(Menu menu) {
          // Inflate the menu; this adds items to the action bar if it is present.
          getMenuInflater().inflate(R.menu.menu_patient_diagnosis_statistics, menu);
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
