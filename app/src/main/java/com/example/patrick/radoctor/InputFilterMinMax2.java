package com.example.patrick.radoctor;

import android.text.InputFilter;
import android.text.Spanned;

/**
 * Created by Patrick on 12/1/2015.
 */
public class InputFilterMinMax2 implements InputFilter {
     private double min, max;

     public InputFilterMinMax2(double min, double max){
          this.min = min;
          this.max = max;
     }
     public InputFilterMinMax2(String min, String max){
          this.min = Double.parseDouble(min);
          this.max = Double.parseDouble(max);
     }


     @Override
     public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
          try{
               String newVal = dest.toString().substring(0, dstart) + dest.toString().substring(dend, dest.toString().length());
               newVal = newVal.substring(0, dstart) + source.toString() + newVal.substring(dstart, newVal.length());
               double input = Double.parseDouble(newVal);
               if (isInRange(min, max, input))
                    return null;
          }catch (Exception e){ System.out.println(e.toString());}
          return "";
     }

     private boolean isInRange(double a, double b, double c){
          return b > a ? c >= a && c <= b : c >= b && c <= a;
     }
}
