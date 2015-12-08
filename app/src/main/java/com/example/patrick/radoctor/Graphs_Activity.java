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
import java.util.Calendar;
import java.util.Date;


public class Graphs_Activity extends ActionBarActivity {
     int patient_id;
     GraphView graph;
     GraphView graph2;
     GraphView graph3;
     GraphView graph4;
     DBHelper db;
     SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

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
     //     db.produceDummy(5);
          printGraphs();
     }

     private void printGraphs() {
          ListHolders lh = db.getAllScores(patient_id);
          ArrayList<String> dates;
          ArrayList<Double> temp_score;
          String title;
          GraphView temp_graph;
          dates = lh.getDates();
          Date firstDate, lastDate;
          int size = dates.size();
          int max_y;
          double remission;
          if(size > 0) {
               for (int i = 0; i < 4; i++) {
                    if (i == 0) {
                         temp_score = lh.getDas28esr();
                         temp_graph = graph;
                         title = "DAS28 - ESR";
                         max_y = 10;
                         remission = 2.6;
                    } else if (i == 1) {
                         temp_score = lh.getDas28crp();
                         temp_graph = graph2;
                         title = "DAS28 - CRP";
                         max_y = 10;
                         remission = 2.6;
                    } else if (i == 2) {
                         temp_score = lh.getSdai();
                         temp_graph = graph3;
                         title = "SDAI";
                         max_y = 100;
                         remission = 3.3;
                    } else {
                         temp_score = lh.getCdai();
                         temp_graph = graph4;
                         title = "CDAI";
                         max_y = 100;
                         remission = 2.8;
                    }
                    DataPoint[] points = new DataPoint[size];
                    DataPoint[] points2 = new DataPoint[size];
                    try {
                         firstDate = formatter.parse(dates.get(0));
                         lastDate = formatter.parse(dates.get(size - 1));
                         Date date;

                         for(int j = 0; j < size; j++){
                              date = formatter.parse(dates.get(j));
                              DataPoint temp = new DataPoint(date,temp_score.get(j));
                              DataPoint temp2 = new DataPoint(date,  remission);
                              points[j] = temp;
                              points2[j] = temp2;
                         }
                         LineGraphSeries<DataPoint> series = new LineGraphSeries<>(points);
                         LineGraphSeries<DataPoint> series2 = new LineGraphSeries<>(points2);
                         series2.setColor(Color.parseColor("#FF0000"));
                         series2.setThickness(5);
                         series.setDrawDataPoints(true);
                         series.setDataPointsRadius(3);
                         temp_graph.addSeries(series);
                         temp_graph.addSeries(series2);
                         temp_graph.getGridLabelRenderer().setLabelFormatter(new DateAsXAxisLabelFormatter(this));
                         temp_graph.getGridLabelRenderer().setNumHorizontalLabels(3);
                         temp_graph.getViewport().setXAxisBoundsManual(true);
                         temp_graph.getViewport().setMinX(firstDate.getTime());
                         temp_graph.getViewport().setMaxX(lastDate.getTime());
                         temp_graph.getViewport().setYAxisBoundsManual(true);
                         temp_graph.getViewport().setMinY(0);
                         temp_graph.getViewport().setMaxY(max_y);
                         temp_graph.setTitle(title);
                    } catch (Exception e) {
                         System.out.println(e.toString());
                    }
               }
          }

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
