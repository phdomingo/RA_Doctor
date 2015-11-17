package com.example.patrick.radoctor;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Patrick on 10/24/2015.
 */
public class PatientCustomListAdapter extends BaseAdapter {


     //private int id;
     private ArrayList<Patient> patients;
     private LayoutInflater mInflater;

     public PatientCustomListAdapter(Context context, ArrayList<Patient> patients) {
          mInflater = LayoutInflater.from(context);
          this.patients = patients;
     }

     @Override
     public int getCount() {
          return patients.size();
     }

     @Override
     public Object getItem(int position) {
          return patients.get(position);
     }

     @Override
     public long getItemId(int position) {
          return position;
     }

     @Override
     public View getView(int position, View v, ViewGroup parent){
          View view;
          ViewHolder holder;
          if(v == null){
               view = mInflater.inflate(R.layout.layout_listview_patients,parent,false);
               holder = new ViewHolder();
               holder.name = (TextView)view.findViewById(R.id.textViewPatientListView);
               view.setTag(holder);
          }
          else{
               view = v;
               holder = (ViewHolder)view.getTag();
          }
          Patient patient = patients.get(position);
          holder.name.setText(patient.getName());
          return view;
     }

     private class ViewHolder {
          public TextView name;
     }
}
