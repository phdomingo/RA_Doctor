package com.example.patrick.radoctor;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Created by Patrick on 10/11/2015.
 */
public class DBHelper extends SQLiteOpenHelper {


     //SQL META-DATA
     public static final String DATABASE_NAME = "RADoctor.db";
     public static final int DATABASE_VERSION = 1;

     //SQL TABLES
     public static final String TABLE_ONE = "Patients";
     public static final String TABLE_TWO = "DAS28";
     public static final String TABLE_THREE = "CDAI_SDAI";
     public static final String TABLE_FOUR = "Diagnosis";
     public static final String TABLE_FIVE = "Photos";

    //TABLE ONE COLUMNS
     public static final String PATIENTS_ID = "id";
     public static final String PATIENTS_NAME = "Name";
     public static final String PATIENTS_AGE = "Age";
     public static final String PATIENTS_SEX = "Sex";
     public static final String PATIENTS_AOD = "Age_of_Diagnosis";
     public static final String PATIENTS_AOO = "Age_of_Onset";
     public static final String PATIENTS_DATE = "Date_Added";
     public static final String PATIENTS_PHOTO = "Photo";

    //TABLE TWO COLUMNS
     public static final String DAS28_ID = "id";
     public static final String DAS28_PatientId = "Patient_Id";
     public static final String DAS28_Score = "Score";
     public static final String DAS28_Date = "Date_Updated";

    //TABLE THREE COLUMNS
     public static final String CDAI_SDAI_ID = "id";
     public static final String CDAI_SDAI_PatientId = "Patient_Id";
     public static final String CDAI_SDAI_ScoreCDAI = "CDAI_Score";
     public static final String CDAI_SDAI_ScoreSDAI = "SDAI_Score";
     public static final String CDAI_SDAI_Date = "Date_Updated";

     //TABLE FOUR COLUMNS
     public static final String DIAGNOSIS_ID = "id";
     public static final String DIAGNOSIS_PatientId = "Patient_Id";
     public static final String DIAGNOSIS_TenderJoints = "Tender_Joints";
     public static final String DIAGNOSIS_SwollenJoints = "Swollen_Joints";
     public static final String DIAGNOSIS_ESR = "ESR";
     public static final String DIAGNOSIS_CRP = "CRP";
     public static final String DIAGNOSIS_RF = "RF";
     public static final String DIAGNOSIS_GCSF = "GCSF";
     public static final String DIAGNOSIS_DATE = "Date_Updated";

     //TABLE FIVE COLUMNS
     public static final String PHOTO_ID = "id";
     public static final String PHOTO_PATIENT_ID = "Patient_id";
     public static final String PHOTO_PATH = "Path";
     public static final String PHOTO_DATE = "Date_Updated";

     //SQL ENTRIES
     private static final String SQL_CREATE_TABLE_ONE =
           "CREATE TABLE IF NOT EXISTS " + TABLE_ONE + "(" +
                   PATIENTS_ID + " INTEGER primary key autoincrement," +
                   PATIENTS_NAME + " VARCHAR," +
                   PATIENTS_AGE + " INTEGER," +
                   PATIENTS_SEX + " VARCHAR," +
                   PATIENTS_AOD + " INTEGER," +
                   PATIENTS_AOO + " INTEGER," +
                   PATIENTS_DATE + " DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP," +
                   PATIENTS_PHOTO + " VARCHAR DEFAULT 'NULL'" +
                   ");";
     private static final String SQL_CREATE_TABLE_TWO =
             "CREATE TABLE IF NOT EXISTS " + TABLE_TWO + "(" +
                     DAS28_ID + " INTEGER primary key autoincrement," +
                     DAS28_PatientId + " INTEGER," +
                     DAS28_Score + " DOUBLE PRECISION," +
                     DAS28_Date + " DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP" +
                     ");";
     private static final String SQL_CREATE_TABLE_THREE =
             "CREATE TABLE IF NOT EXISTS " + TABLE_THREE + "(" +
                     CDAI_SDAI_ID + " INTEGER primary key autoincrement," +
                     CDAI_SDAI_PatientId + " INTEGER," +
                     CDAI_SDAI_ScoreCDAI + " DOUBLE PRECISION," +
                     CDAI_SDAI_ScoreSDAI + " DOUBLE PRECISION," +
                     CDAI_SDAI_Date + " DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP" +
                     ");";
     private static final String SQL_CREATE_TABLE_FOUR =
             "CREATE TABLE IF NOT EXISTS " + TABLE_FOUR + "("+
                     DIAGNOSIS_ID + " INTEGER primary key autoincrement," +
                     DIAGNOSIS_PatientId + " INTEGER," +
                     DIAGNOSIS_TenderJoints + " INTEGER," +
                     DIAGNOSIS_SwollenJoints + " INTEGER," +
                     DIAGNOSIS_ESR + " DOUBLE PRECISION," +
                     DIAGNOSIS_CRP + " DOUBLE PRECISION," +
                     DIAGNOSIS_RF + " DOUBLE PRECISION,"+
                     DIAGNOSIS_GCSF + " DOUBLE PRECISION," +
                     DIAGNOSIS_DATE + " DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP" +
                     ");";
     private static final String SQL_CREATE_TABLE_FIVE =
             "CREATE TABLE IF NOT EXISTS " + TABLE_FIVE + "("+
                     PHOTO_ID + " INTEGER primary key autoincrement," +
                     PHOTO_PATIENT_ID + " INTEGER," +
                     PHOTO_PATH + " VARCHAR," +
                     PHOTO_DATE + " DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP" +
                     ");";

     private static final String SQL_DELETE_TABLE_ONE =
             "DROP TABLE IF EXISTS " + TABLE_ONE;
     private static final String SQL_DELETE_TABLE_TWO =
             "DROP TABLE IF EXISTS " + TABLE_TWO;
     private static final String SQL_DELETE_TABLE_THREE =
             "DROP TABLE IF EXISTS " + TABLE_THREE;
     private static final String SQL_DELETE_TABLE_FOUR =
             "DROP TABLE IF EXISTS " + TABLE_FOUR;
     private static final String SQL_DELETE_TABLE_FIVE =
             "DROP TABLE IF EXISTS " + TABLE_FIVE;

     public DBHelper(Context context) {
         super(context, DATABASE_NAME, null, DATABASE_VERSION);
     }

     @Override
     public void onCreate(SQLiteDatabase db) {
          db.execSQL(SQL_CREATE_TABLE_ONE);
          db.execSQL(SQL_CREATE_TABLE_TWO);
          db.execSQL(SQL_CREATE_TABLE_THREE);
          db.execSQL(SQL_CREATE_TABLE_FOUR);
          db.execSQL(SQL_CREATE_TABLE_FIVE);
     }
     @Override
     public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
         db.execSQL(SQL_DELETE_TABLE_ONE);
       /*   db.execSQL(SQL_DELETE_TABLE_TWO);
          db.execSQL(SQL_DELETE_TABLE_THREE);
          db.execSQL(SQL_DELETE_TABLE_FOUR);*/
          onCreate(db);
     }
     @Override
     public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion){
          onUpgrade(db, oldVersion, newVersion);
     }

     public boolean insertPhoto(int id, String path){
          SQLiteDatabase db = this.getWritableDatabase();
          ContentValues contentValues = new ContentValues();
          contentValues.put(PHOTO_PATIENT_ID, id);
          contentValues.put(PHOTO_PATH, path );
          db.insert(TABLE_FIVE,null,contentValues);
          return true;
     }

     public boolean insertPatientDiagnosis(int id, int tender, int swollen, double esr, double crp, double rf, double gcsf ){
          SQLiteDatabase db = this.getWritableDatabase();
          ContentValues contentValues = new ContentValues();
          contentValues.put(DIAGNOSIS_PatientId, id);
          contentValues.put(DIAGNOSIS_TenderJoints, tender);
          contentValues.put(DIAGNOSIS_SwollenJoints, swollen);
          contentValues.put(DIAGNOSIS_ESR, esr);
          contentValues.put(DIAGNOSIS_CRP, crp);
          contentValues.put(DIAGNOSIS_RF, rf);
          contentValues.put(DIAGNOSIS_GCSF, gcsf);
          db.insert(TABLE_FOUR,null,contentValues);
          return true;
     }

     public boolean insertPatientCDAI_SDAI(int id, double score_cdai, double score_sdai){
          SQLiteDatabase db = this.getWritableDatabase();
          ContentValues contentValues = new ContentValues();
          contentValues.put(CDAI_SDAI_PatientId, id);
          contentValues.put(CDAI_SDAI_ScoreCDAI, score_cdai);
          contentValues.put(CDAI_SDAI_ScoreSDAI, score_sdai);
          db.insert(TABLE_THREE,null,contentValues);
          return true;
     }

     public boolean insertPatientDas28(int id, double score_das28){
          SQLiteDatabase db = this.getWritableDatabase();
          ContentValues contentValues = new ContentValues();
          contentValues.put(DAS28_PatientId, id);
          contentValues.put(DAS28_Score, score_das28);
          db.insert(TABLE_TWO,null,contentValues);
          return true;
     }

     public boolean insertPatient(String name, int age, String sex, int aod, int aoo, String path){
          SQLiteDatabase db = this.getWritableDatabase();
          ContentValues contentValues = new ContentValues();
          contentValues.put(PATIENTS_NAME,name);
          contentValues.put(PATIENTS_AGE,age);
          contentValues.put(PATIENTS_SEX,sex);
          contentValues.put(PATIENTS_AOD,aod);
          contentValues.put(PATIENTS_AOO, aoo);
          contentValues.put(PATIENTS_PHOTO, path);
          db.insert(TABLE_ONE,null,contentValues);
          return true;
     }

     public Cursor getData (int id){
          SQLiteDatabase db = this.getReadableDatabase();
          Cursor res = db.rawQuery("Select * from Patients where id = " + id + "", null);
          return res;
     }

     public ListHolders getAllPhotos(int id){
          SQLiteDatabase db = this.getReadableDatabase();
          ListHolders array_list = new ListHolders();
          ArrayList<String> path = new ArrayList<>();
          Cursor res = db.rawQuery("Select * from " + TABLE_FIVE +
                    " where " + PHOTO_PATIENT_ID + "=" + id +
                    " ORDER BY " + PHOTO_DATE + " DESC", null);
          String temp;
          try{
               res.moveToFirst();
               while(!res.isAfterLast()){
                    temp = res.getString(res.getColumnIndex(PHOTO_PATH));
                    path.add(temp);
                    res.moveToNext();
               }
               array_list.setFile_paths(path);

          }catch (Exception e){
               System.out.println(e.toString());
          }
          res.close();
          return array_list;
     }

     public DiseaseActivity_Object getLatestDiseaseActivity(int id){
          SQLiteDatabase db = this.getReadableDatabase();
          Cursor res = db.rawQuery("Select * from " + TABLE_TWO +
                  " where " + DAS28_PatientId + "=" + id +
                  " ORDER BY " + DAS28_Date + " DESC"
                  ,null);
          Cursor res2 = db.rawQuery("Select * from " + TABLE_THREE +
                  " where " + CDAI_SDAI_PatientId + "=" + id +
                  " ORDER BY " + CDAI_SDAI_Date + " DESC"
                  ,null);
          String date1, date2;
          Double das28, cdai, sdai;
          DiseaseActivity_Object dao = new DiseaseActivity_Object();
          if(res.moveToFirst()){
               das28 = res.getDouble(res.getColumnIndex(DAS28_Score));
               date1 = res.getString(res.getColumnIndex(DAS28_Date));
               dao.setHas_record_das28(true);
               dao.setDas28(das28);
               dao.setLastUpdatedDas28(date1);
          }
          if(res2.moveToFirst()){
               cdai = res2.getDouble(res2.getColumnIndex(CDAI_SDAI_ScoreCDAI));
               sdai = res2.getDouble(res2.getColumnIndex(CDAI_SDAI_ScoreSDAI));
               date2 = res2.getString(res2.getColumnIndex(CDAI_SDAI_Date));
               dao.setHas_record_csdai(true);
               dao.setCdai(cdai);
               dao.setSdai(sdai);
               dao.setLastUpdatedCSDai(date2);
          }
          res.close();
          res2.close();
          return dao;
     }


     public Diagnosis getLatestDiagnosis(int id){
          SQLiteDatabase db = this.getReadableDatabase();
          Cursor res = db.rawQuery("Select * from " + TABLE_FOUR +
                   " where " + DIAGNOSIS_PatientId + "=" + id +
                  " ORDER BY " + DIAGNOSIS_DATE + " DESC"
                  ,null);
          Diagnosis patient;
          int tender, swollen;
          double esr,crp,rf,gcsf;
          String date;
          if (res.moveToFirst()) {
               tender = res.getInt(res.getColumnIndex(DIAGNOSIS_TenderJoints));
               swollen = res.getInt(res.getColumnIndex(DIAGNOSIS_SwollenJoints));
               esr = res.getDouble(res.getColumnIndex(DIAGNOSIS_ESR));
               crp = res.getDouble(res.getColumnIndex(DIAGNOSIS_CRP));
               rf = res.getDouble(res.getColumnIndex(DIAGNOSIS_RF));
               gcsf = res.getDouble(res.getColumnIndex(DIAGNOSIS_GCSF));
               date = res.getString(res.getColumnIndex(DIAGNOSIS_DATE));
               patient = new Diagnosis(id, tender, swollen, esr, crp, rf, gcsf,date);
               res.close();
          }
          else{
               res.close();
               return new Diagnosis();
          }
          return patient;
     }

     public ArrayList<String> getAllPatients(){
          ArrayList<String> array_list = new ArrayList<>();
          SQLiteDatabase db = this.getReadableDatabase();
          String temp;
          try{
               Cursor res = db.rawQuery("Select * from Patients", null);
               res.moveToFirst();
               while (!res.isAfterLast()) {
                    temp = res.getString(res.getColumnIndex(PATIENTS_NAME));
                    array_list.add(temp);
                    res.moveToNext();
               }
               res.close();
          }catch(Exception e){}
          return array_list;
     }

     public ListHolders getAllCSdai(int id){
          SQLiteDatabase db = this.getReadableDatabase();
          ListHolders array_list = new ListHolders();
          ArrayList<Double> cdai_score = new ArrayList<>();
          ArrayList<Double> sdai_scores = new ArrayList<>();
          ArrayList<String> dates = new ArrayList<>();
          String temp_date;
          double temp_cdai, temp_sdai;
          try{
               Cursor res = db.rawQuery("Select * from " + TABLE_THREE + " where " + CDAI_SDAI_PatientId + "=" + id + " "
                       + " ORDER BY " + CDAI_SDAI_Date, null);
               res.moveToFirst();
               while(!res.isAfterLast()){
                    temp_cdai = res.getDouble(res.getColumnIndex(CDAI_SDAI_ScoreCDAI));
                    temp_sdai = res.getDouble(res.getColumnIndex(CDAI_SDAI_ScoreSDAI));
                    temp_date = res.getString(res.getColumnIndex(CDAI_SDAI_Date));
                    dates.add(temp_date);
                    cdai_score.add(temp_cdai);
                    sdai_scores.add(temp_sdai);
                    res.moveToNext();
               }
               array_list.setDates(dates);
               array_list.setCdaiScores(cdai_score);
               array_list.setSdaiScores(sdai_scores);
               res.close();
          }catch(Exception e){
               System.out.println(e.toString());
          }

          return array_list;
     }


     public ListHolders getAllDas28(int id){
          SQLiteDatabase db = this.getReadableDatabase();
          ListHolders array_list = new ListHolders();
          ArrayList<Double> das28scores = new ArrayList<>();
          ArrayList<String> dates = new ArrayList<>();
          String temp_date;
          double temp_score;
          try{
               Cursor res = db.rawQuery("Select * from " + TABLE_TWO + " where " + DAS28_PatientId + "=" + id + " "
                       + " ORDER BY " + DAS28_Date, null);
               res.moveToFirst();
               while (!res.isAfterLast()){
                    temp_score = res.getDouble(res.getColumnIndex(DAS28_Score));
                    temp_date = res.getString(res.getColumnIndex(DAS28_Date));
                    das28scores.add(temp_score);
                    dates.add(temp_date);
                    res.moveToNext();
               }
               array_list.setDates(dates);
               array_list.setDas28Scores(das28scores);
               res.close();
          }catch(Exception e){
               System.out.println(e.toString());
          }
          return array_list;
     }

     public ListHolders getEsr(int id){
          SQLiteDatabase db = this.getReadableDatabase();
          ListHolders array_list = new ListHolders();
          ArrayList<Double> esr = new ArrayList<>();
          ArrayList<String> dates = new ArrayList<>();
          String temp_date;
          double temp_esr;
          try{
               Cursor res = db.rawQuery("Select * from " + TABLE_FOUR + " where " + DIAGNOSIS_PatientId + "=" + id + " "
                       + "ORDER BY " + DIAGNOSIS_DATE,null );
               res.moveToFirst();
               while (!res.isAfterLast()){
                    temp_esr = res.getDouble(res.getColumnIndex(DIAGNOSIS_ESR));
                    temp_date = res.getString(res.getColumnIndex(DIAGNOSIS_DATE));
                    esr.add(temp_esr);
                    dates.add(temp_date);
                    res.moveToNext();
               }
               array_list.setDates(dates);
               array_list.setEsr_crp(esr);
               res.close();
          }catch(Exception e){
               System.out.println(e.toString());
          }

          return array_list;
     }

     public ListHolders getCrp(int id){
          SQLiteDatabase db = this.getReadableDatabase();
          ListHolders array_list = new ListHolders();
          ArrayList<Double> crp = new ArrayList<>();
          ArrayList<String> dates = new ArrayList<>();
          String temp_date;
          double temp_crp;
          try{
               Cursor res = db.rawQuery("Select * from " + TABLE_FOUR + " where " + DIAGNOSIS_PatientId + "=" + id + " "
                       + "ORDER BY " + DIAGNOSIS_DATE,null );
               res.moveToFirst();
               while (!res.isAfterLast()){
                    temp_crp = res.getDouble(res.getColumnIndex(DIAGNOSIS_CRP));
                    temp_date = res.getString(res.getColumnIndex(DIAGNOSIS_DATE));
                    crp.add(temp_crp);
                    dates.add(temp_date);
                    res.moveToNext();
               }
               array_list.setDates(dates);
               array_list.setEsr_crp(crp);
               res.close();
          }catch(Exception e){
               System.out.println(e.toString());
          }
          return array_list;
     }
}
/* FOR FUTURE USE
   public ArrayList<Patient> getAllPatients(){
         ArrayList<String> array_list = new ArrayList<>();
          ArrayList<Patient> array_list = new ArrayList<>();
          Patient temp;
          SQLiteDatabase db = this.getReadableDatabase();
          try {
               Cursor res = db.rawQuery("Select * from Patients", null);
               res.moveToFirst();
               while (!res.isAfterLast()) {
                    temp = new Patient();
                    temp.setId(res.getInt(res.getColumnIndex(PATIENTS_ID)));
                    temp.setName(res.getString(res.getColumnIndex(PATIENTS_NAME)));
                    temp.setAge(res.getInt(res.getColumnIndex(PATIENTS_AGE)));
                    temp.setSex(res.getString(res.getColumnIndex(PATIENTS_SEX)));
                    temp.setAod(res.getInt(res.getColumnIndex(PATIENTS_AOD)));
                    temp.setAoo(res.getInt(res.getColumnIndex(PATIENTS_AOO)));
                    array_list.add(temp);
                    res.moveToNext();
               }
          }catch(Exception e){}
          return array_list;
     }
 */
/* Dummy Data
          SQLiteDatabase db2 = this.getWritableDatabase();
          ContentValues contentValues = new ContentValues();
          contentValues.put(DAS28_PatientId, id);
          contentValues.put(DAS28_Score, 1);
          contentValues.put(DAS28_Date,"2015-11-03 14:48:14");
          db2.insert(TABLE_TWO,null,contentValues);

          contentValues = new ContentValues();
          contentValues.put(DAS28_PatientId, id);
          contentValues.put(DAS28_Score, 2);
          contentValues.put(DAS28_Date,"2015-06-03 14:48:14");
          db2.insert(TABLE_TWO,null,contentValues);

          contentValues = new ContentValues();
          contentValues.put(DAS28_PatientId, id);
          contentValues.put(DAS28_Score, 4.5);
          contentValues.put(DAS28_Date,"2015-12-21 14:48:14");
          db2.insert(TABLE_TWO,null,contentValues);

          contentValues = new ContentValues();
          contentValues.put(DAS28_PatientId, id);
          contentValues.put(DAS28_Score, 6);
          contentValues.put(DAS28_Date,"2015-01-21 14:48:14");
          db2.insert(TABLE_TWO,null,contentValues);


          contentValues = new ContentValues();
          contentValues.put(DAS28_PatientId, id);
          contentValues.put(DAS28_Score, 7);
          contentValues.put(DAS28_Date,"2016-01-21 14:48:14");
          db2.insert(TABLE_TWO,null,contentValues);

          contentValues = new ContentValues();
          contentValues.put(DAS28_PatientId, id);
          contentValues.put(DAS28_Score, 3);
          contentValues.put(DAS28_Date,"2016-02-21 14:48:14");
          db2.insert(TABLE_TWO,null,contentValues);
          */