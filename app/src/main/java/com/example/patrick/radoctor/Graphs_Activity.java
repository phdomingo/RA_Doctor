package com.example.patrick.radoctor;

import android.graphics.Color;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.helper.DateAsXAxisLabelFormatter;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


public class Graphs_Activity extends ActionBarActivity {
     int patient_id;
     GraphView graph;
     GraphView graph2;
     GraphView graph3;
     GraphView graph4;
     ListHolders das28;
     ListHolders csdai;
     ListHolders esr_crp;
     DBHelper db;
     SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

     private void printDAS28GRAPH(){
          das28 = db.getAllDas28(patient_id);
          ArrayList<String> dates = das28.getDates();
          ArrayList<Double> scores = das28.getDas28Scores();
          int size = dates.size();
          DataPoint[] points = new DataPoint[size];
          Date firstDate;
          Date lastDate;

          try {
               firstDate = formatter.parse(dates.get(0));
               lastDate = formatter.parse(dates.get(size - 1));
               Date date = new Date();
               for(int i = 0; i < dates.size();i++) {
                    date = formatter.parse(dates.get(i));
                    DataPoint temp = new DataPoint(date,scores.get(i));
                    points[i] = temp;
               }
               LineGraphSeries<DataPoint> series = new LineGraphSeries<>(points);
               graph.addSeries(series);
               graph.getGridLabelRenderer().setLabelFormatter(new DateAsXAxisLabelFormatter(this));
               graph.getGridLabelRenderer().setNumHorizontalLabels(3);
               graph.getViewport().setMinX(firstDate.getTime());
               graph.getViewport().setMaxX(lastDate.getTime());
               graph.getViewport().setXAxisBoundsManual(true);
               graph.getViewport().setMinY(0);
               graph.getViewport().setMaxY(10);
               graph.getViewport().setYAxisBoundsManual(true);

          }catch(Exception e){
               System.out.println(e.toString());
          }
     }

     private void printCSDAIGRAPH() {
          csdai = db.getAllCSdai(patient_id);
          ArrayList<String> dates = csdai.getDates();
          ArrayList<Double> cdai_scores = csdai.getCdaiScores();
          ArrayList<Double> sdai_scores = csdai.getSdaiScores();
          int size = dates.size();
          DataPoint[] points = new DataPoint[size];
          DataPoint[] points2 = new DataPoint[size];
          Date firstDate;
          Date lastDate;
          try{
               firstDate = formatter.parse(dates.get(0));
               lastDate = formatter.parse(dates.get(size - 1));
               Date date;
               for(int i = 0; i < dates.size();i++) {
                    date = formatter.parse(dates.get(i));
                    DataPoint temp = new DataPoint(date,cdai_scores.get(i));
                    DataPoint temp2 = new DataPoint(date,sdai_scores.get(i));
                    points[i] = temp;
                    points2[i] = temp2;
               }
               LineGraphSeries<DataPoint> series = new LineGraphSeries<>(points);
               LineGraphSeries<DataPoint> series2 = new LineGraphSeries<>(points2);
               series2.setColor(Color.parseColor("#ff0000"));
               graph2.addSeries(series);
               graph2.addSeries(series2);
               graph2.getGridLabelRenderer().setLabelFormatter(new DateAsXAxisLabelFormatter(this));
               graph2.getGridLabelRenderer().setNumHorizontalLabels(3);
               graph2.getViewport().setMinX(firstDate.getTime());
               graph2.getViewport().setMaxX(lastDate.getTime());
               graph2.getViewport().setXAxisBoundsManual(true);
               graph2.getViewport().setMinY(0);
              // graph2.getViewport().setMaxY(10);
               graph2.getViewport().setYAxisBoundsManual(true);
          }catch(Exception e){
               System.out.println(e.toString());
          }
     }

     private void printESRGRAPH(){
          esr_crp = db.getEsr(patient_id);
          ArrayList<String> dates = esr_crp.getDates();
          ArrayList<Double> esr = esr_crp.getEsr_crp();
          int size = dates.size();
          DataPoint[] points = new DataPoint[size];
          Date firstDate;
          Date lastDate;
          try {
               firstDate = formatter.parse(dates.get(0));
               lastDate = formatter.parse(dates.get(size - 1));
               Date date;
               for(int i = 0; i < dates.size();i++) {
                    date = formatter.parse(dates.get(i));
                    DataPoint temp = new DataPoint(date,esr.get(i));
                    points[i] = temp;
               }
               LineGraphSeries<DataPoint> series = new LineGraphSeries<>(points);
               graph3.addSeries(series);
               graph3.getGridLabelRenderer().setLabelFormatter(new DateAsXAxisLabelFormatter(this));
               graph3.getGridLabelRenderer().setNumHorizontalLabels(3);
               graph3.getViewport().setMinX(firstDate.getTime());
               graph3.getViewport().setMaxX(lastDate.getTime());
               graph3.getViewport().setXAxisBoundsManual(true);
               graph3.getViewport().setMinY(0);
               graph3.getViewport().setYAxisBoundsManual(true);
          }catch(Exception e){
               System.out.println(e.toString());
          }
     }

     private void printCRPGRAPH(){
          esr_crp = db.getCrp(patient_id);
          ArrayList<String> dates = esr_crp.getDates();
          ArrayList<Double> crp = esr_crp.getEsr_crp();
          int size = dates.size();
          System.out.println("CRP SIZE: " + size);
          DataPoint[] points = new DataPoint[size];
          Date firstDate;
          Date lastDate;
          try {
               firstDate = formatter.parse(dates.get(0));
               lastDate = formatter.parse(dates.get(size - 1));
               Date date;
               for(int i = 0; i < dates.size();i++) {
                    date = formatter.parse(dates.get(i));
                    DataPoint temp = new DataPoint(date,crp.get(i));
                    points[i] = temp;
               }
               LineGraphSeries<DataPoint> series = new LineGraphSeries<>(points);
               graph4.addSeries(series);
               graph4.getGridLabelRenderer().setLabelFormatter(new DateAsXAxisLabelFormatter(this));
               graph4.getGridLabelRenderer().setNumHorizontalLabels(3);
               graph4.getViewport().setMinX(firstDate.getTime());
               graph4.getViewport().setMaxX(lastDate.getTime());
               graph4.getViewport().setXAxisBoundsManual(true);
               graph4.getViewport().setMinY(0);
               graph4.getViewport().setYAxisBoundsManual(true);
          }catch(Exception e){
               System.out.println(e.toString());
          }
     }

     @Override
     protected void onCreate(Bundle savedInstanceState) {
          super.onCreate(savedInstanceState);
          setContentView(R.layout.activity_graphs);

          patient_id = getIntent().getExtras().getInt("patient_id");
          graph = (GraphView) findViewById(R.id.graph);
          graph2 = (GraphView) findViewById(R.id.graph2);
          graph3 = (GraphView) findViewById(R.id.graph3);
          graph4 = (GraphView) findViewById(R.id.graph4);
          db = new DBHelper(this);
          printDAS28GRAPH();
          printCSDAIGRAPH();
          printESRGRAPH();
          printCRPGRAPH();
     }

     @Override
     public boolean onCreateOptionsMenu(Menu menu) {
          // Inflate the menu; this adds items to the action bar if it is present.
          getMenuInflater().inflate(R.menu.menu_graphs, menu);
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
