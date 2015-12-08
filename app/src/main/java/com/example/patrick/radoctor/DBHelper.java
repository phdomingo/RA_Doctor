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
     public static final int DATABASE_VERSION = 2;

     //SQL TABLES
     public static final String TABLE_ONE = "Patients";
     public static final String TABLE_TWO = "PatientScores";
     public static final String TABLE_THREE = "Biologics";
     public static final String TABLE_FOUR = "Diagnosis";
     public static final String TABLE_FIVE = "Photos";
     public static final String TABLE_SIX = "Drugs";
     public static final String TABLE_SEVEN = "Questionnaire";

    //TABLE ONE COLUMNS
     public static final String PATIENTS_ID = "id";
     public static final String PATIENTS_NAME = "Name";
     public static final String PATIENTS_AGE = "Age";
     public static final String PATIENTS_SEX = "Sex";
     public static final String PATIENTS_AOD = "Age_of_Diagnosis";
     public static final String PATIENTS_AOO = "Age_of_Onset";
     public static final String PATIENTS_DATE = "Date_Added";
     public static final String PATIENTS_PHOTO = "Photo";
     public static final String PATIENTS_SCORE = "RA_Criteria_Score";

    //TABLE TWO COLUMNS
     public static final String SCORE_ID = "id";
     public static final String SCORE_PatientId = "Patient_Id";
     public static final String SCORE_DAS28_ESR = "DAS28_ESR_Score";
     public static final String SCORE_DAS28_CRP = "DAS28_CRP_Score";
     public static final String SCORE_CDAI = "CDAI_Score";
     public static final String SCORE_SDAI = "SDAI_Score";
     public static final String SCORE_Date = "Date_Updated";

    // TABLE THREE COLUMNS
     public static final String BIOLOGICS_ID = "id";
     public static final String BIOLOGICS_PatientId = "Patient_Id";
     public static final String BIOLOGICS_TYPE = "Type";
     public static final String BIOLOGICS_DOSE = "Dose";
     public static final String BIOLOGICS_INFUSION = "Infusion_Number";
     public static final String BIOLOGICS_DATE= "Date_Updated";

     //TABLE FOUR COLUMNS
     public static final String DIAGNOSIS_ID = "id";
     public static final String DIAGNOSIS_PatientId = "Patient_Id";
     public static final String DIAGNOSIS_TenderJoints = "Tender_Joints";
     public static final String DIAGNOSIS_SwollenJoints = "Swollen_Joints";
     public static final String DIAGNOSIS_ESR = "ESR";
     public static final String DIAGNOSIS_CRP = "CRP";
     public static final String DIAGNOSIS_RF = "RF";
     public static final String DIAGNOSIS_EGA = "EGA";
     public static final String DIAGNOSIS_PGA = "PGA";
     public static final String DIAGNOSIS_CCP = "CCP";
     public static final String DIAGNOSIS_DATE = "Date_Updated";

     //TABLE FIVE COLUMNS
     public static final String PHOTO_ID = "id";
     public static final String PHOTO_PATIENT_ID = "Patient_id";
     public static final String PHOTO_PATH = "Path";
     public static final String PHOTO_DATE = "Date_Updated";

     //TABLE SIX COLUMNS
     public static final String DRUGS_ID = "id";
     public static final String DRUGS_PATIENT_ID = "Patient_id";
     public static final String DRUGS_DRUG_TYPE = "Type";
     public static final String DRUGS_DOSE = "Dose";
     public static final String DRUGS_ADMISSION = "Admission";
     public static final String DRUGS_DATE = "Date_Updated";


     //TABLE SEVEN COLUMNS
     public static final String QUESTIONNAIRE_ID = "id";
     public static final String QUESTIONNAIRE_PATIENT_ID = "Patient_id";
     public static final String QUESTIONNAIRE_DATE = "Date_Answered";
     public static final String QUESTIONNAIRE_TB = "TB";
     public static final String QUESTIONNAIRE_HEPA = "Hepa";
     public static final String QUESTIONNAIRE_CANCER = "Cancer";


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
                   PATIENTS_PHOTO + " VARCHAR DEFAULT 'NULL' ," +
                   PATIENTS_SCORE + " INTEGER"  +
                    ");";
     private static final String SQL_CREATE_TABLE_TWO =
             "CREATE TABLE IF NOT EXISTS " + TABLE_TWO + "(" +
                     SCORE_ID + " INTEGER primary key autoincrement," +
                     SCORE_PatientId + " INTEGER," +
                     SCORE_DAS28_ESR + " DOUBLE PRECISION," +
                     SCORE_DAS28_CRP + " DOUBLE PRECISION," +
                     SCORE_CDAI + " DOUBLE PRECISION," +
                     SCORE_SDAI + " DOUBLE PRECISION," +
                     SCORE_Date + " DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP" +
                     ");";
     private static final String SQL_CREATE_TABLE_THREE =
             "CREATE TABLE IF NOT EXISTS " + TABLE_THREE + "(" +
                     BIOLOGICS_ID + " INTEGER primary key autoincrement," +
                     BIOLOGICS_PatientId + " INTEGER," +
                     BIOLOGICS_TYPE + " INTEGER," +
                     BIOLOGICS_DOSE + " INTEGER," +
                     BIOLOGICS_INFUSION + " INTEGER," +
                     BIOLOGICS_DATE + " DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP" +
                     ");";
     private static final String SQL_CREATE_TABLE_FOUR =
             "CREATE TABLE IF NOT EXISTS " + TABLE_FOUR + "("+
                     DIAGNOSIS_ID + " INTEGER primary key autoincrement," +
                     DIAGNOSIS_PatientId + " INTEGER," +
                     DIAGNOSIS_TenderJoints + " INTEGER," +
                     DIAGNOSIS_SwollenJoints + " INTEGER," +
                     DIAGNOSIS_ESR + " DOUBLE," +
                     DIAGNOSIS_CRP + " DOUBLE," +
                     DIAGNOSIS_RF + " DOUBLE,"+
                     DIAGNOSIS_EGA + " DOUBLE," +
                     DIAGNOSIS_PGA + " DOUBLE," +
                     DIAGNOSIS_CCP + " VARCHAR," +
                     DIAGNOSIS_DATE + " DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP" +
                     ");";
     private static final String SQL_CREATE_TABLE_FIVE =
             "CREATE TABLE IF NOT EXISTS " + TABLE_FIVE + "("+
                     PHOTO_ID + " INTEGER primary key autoincrement," +
                     PHOTO_PATIENT_ID + " INTEGER," +
                     PHOTO_PATH + " VARCHAR," +
                     PHOTO_DATE + " DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP" +
                     ");";

     private static final String SQL_CREATE_TABLE_SIX =
             "CREATE TABLE IF NOT EXISTS " + TABLE_SIX + "(" +
                    DRUGS_ID + " INTEGER primary key autoincrement, " +
                    DRUGS_PATIENT_ID + " INTEGER," +
                    DRUGS_DRUG_TYPE + " INTEGER," +
                    DRUGS_ADMISSION + " VARCHAR," +
                    DRUGS_DOSE + " DOUBLE," +
                    DRUGS_DATE + " DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP"+
             ");";
     private static final String SQL_CREATE_TABLE_SEVEN =
             "CREATE TABLE IF NOT EXISTS " + TABLE_SEVEN + "(" +
                     QUESTIONNAIRE_ID + " INTEGER primary key autoincrement, "+
                     QUESTIONNAIRE_PATIENT_ID + " INTEGER," +
                     QUESTIONNAIRE_TB + " VARCHAR," +
                     QUESTIONNAIRE_HEPA + " VARCHAR," +
                     QUESTIONNAIRE_CANCER + " VARCHAR," +
                     QUESTIONNAIRE_DATE + " DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP" +
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
     private static final String SQL_DELETE_TABLE_SIX =
             "DROP TABLE IF EXISTS " + TABLE_SIX;
     private static final String SQL_DELETE_TABLE_SEVEN =
             "DROP TABLE IF EXISTS " + TABLE_SEVEN;

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
          db.execSQL(SQL_CREATE_TABLE_SIX);
          db.execSQL(SQL_CREATE_TABLE_SEVEN);
     }
     @Override
     public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
       //   db.execSQL(SQL_DELETE_TABLE_ONE);
       //   db.execSQL(SQL_DELETE_TABLE_TWO);
       //   db.execSQL(SQL_DELETE_TABLE_THREE);
       //  db.execSQL(SQL_DELETE_TABLE_FOUR);
       //   db.execSQL(SQL_DELETE_TABLE_FIVE);
       //   db.execSQL(SQL_DELETE_TABLE_SIX);
          db.execSQL(SQL_DELETE_TABLE_SEVEN);
          onCreate(db);
     }
     @Override
     public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion){
          onUpgrade(db, oldVersion, newVersion);
     }


     // PATIENT RELATED
     public boolean insertPatient(String name, int age, String sex, int aod, int aoo, String path,int score){
          SQLiteDatabase db = this.getWritableDatabase();
          ContentValues contentValues = new ContentValues();
          contentValues.put(PATIENTS_NAME,name);
          contentValues.put(PATIENTS_AGE,age);
          contentValues.put(PATIENTS_SEX,sex);
          contentValues.put(PATIENTS_AOD,aod);
          contentValues.put(PATIENTS_AOO, aoo);
          contentValues.put(PATIENTS_PHOTO, path);
          contentValues.put(PATIENTS_SCORE, score);
          db.insert(TABLE_ONE,null,contentValues);
          return true;
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

     public ArrayList<Patient> getPatientStatistics(){
          ArrayList<Patient> patients = new ArrayList<>();
          String sex;
          int age, aoo, aod, rascore;
          SQLiteDatabase db = this.getReadableDatabase();
          try{
               Cursor res = db.rawQuery("Select * from Patients",null);
               res.moveToFirst();
               while(!res.isAfterLast()){
                    sex = res.getString(res.getColumnIndex(PATIENTS_SEX));
                    age = res.getInt(res.getColumnIndex(PATIENTS_AGE));
                    aoo = res.getInt(res.getColumnIndex(PATIENTS_AOO));
                    aod = res.getInt(res.getColumnIndex(PATIENTS_AOD));
                    rascore = res.getInt(res.getColumnIndex(PATIENTS_SCORE));
                    Patient temp = new Patient();
                    temp.setAge(age);
                    temp.setAod(aod);
                    temp.setAoo(aoo);
                    temp.setRascore(rascore);
                    temp.setSex(sex);
                    patients.add(temp);
                    res.moveToNext();
               }
               res.close();
          }catch(Exception e){};
          return patients;
     }

     public Cursor getData (int id){
          SQLiteDatabase db = this.getReadableDatabase();
          Cursor res = db.rawQuery("Select * from Patients where id = " + id + "", null);
          return res;
     }

     public boolean insertPhoto(int id, String path){
          SQLiteDatabase db = this.getWritableDatabase();
          ContentValues contentValues = new ContentValues();
          contentValues.put(PHOTO_PATIENT_ID, id);
          contentValues.put(PHOTO_PATH, path );
          db.insert(TABLE_FIVE,null,contentValues);
          return true;
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
     // PATIENT RELATED


     //DIAGNOSIS RELATED
     public boolean insertPatientDiagnosis(int id, int tender, int swollen, double esr, double crp, double rf, double ega, double pga, String ccp ){
          SQLiteDatabase db = this.getWritableDatabase();
          ContentValues contentValues = new ContentValues();
          contentValues.put(DIAGNOSIS_PatientId, id);
          contentValues.put(DIAGNOSIS_TenderJoints, tender);
          contentValues.put(DIAGNOSIS_SwollenJoints, swollen);
          contentValues.put(DIAGNOSIS_ESR, esr);
          contentValues.put(DIAGNOSIS_CRP, crp);
          contentValues.put(DIAGNOSIS_RF, rf);
          contentValues.put(DIAGNOSIS_EGA, ega);
          contentValues.put(DIAGNOSIS_PGA, pga);
          contentValues.put(DIAGNOSIS_CCP, ccp);
          db.insert(TABLE_FOUR,null,contentValues);
          return true;
     }

     public Diagnosis getDiagnosis(int id, int mode){
          SQLiteDatabase db = this.getReadableDatabase();
          Cursor res;
          if(mode == 1) { //GET LATEST
               res = db.rawQuery("Select * from " + TABLE_FOUR +
                       " where " + DIAGNOSIS_PatientId + "=" + id +
                       " ORDER BY " + DIAGNOSIS_DATE + " DESC"
                       , null);
          }
          else{     //GET FIRST
               res = db.rawQuery("Select * from " + TABLE_FOUR +
                       " where " + DIAGNOSIS_PatientId + "=" + id +
                       " ORDER BY " + DIAGNOSIS_DATE
                       ,null);
          }
          Diagnosis patient;
          int tender, swollen;
          double esr,crp,rf,ega,pga;
          String date, ccp;

          if (res.moveToFirst()) {
               tender = res.getInt(res.getColumnIndex(DIAGNOSIS_TenderJoints));
               swollen = res.getInt(res.getColumnIndex(DIAGNOSIS_SwollenJoints));
               esr = res.getDouble(res.getColumnIndex(DIAGNOSIS_ESR));
               crp = res.getDouble(res.getColumnIndex(DIAGNOSIS_CRP));
               rf = res.getDouble(res.getColumnIndex(DIAGNOSIS_RF));
               ega = res.getDouble(res.getColumnIndex(DIAGNOSIS_EGA));
               pga = res.getDouble(res.getColumnIndex(DIAGNOSIS_PGA));
               ccp = res.getString(res.getColumnIndex(DIAGNOSIS_CCP));
               date = res.getString(res.getColumnIndex(DIAGNOSIS_DATE));
               patient = new Diagnosis(id, tender, swollen, esr, crp, rf, ega, pga, ccp, date);
               res.close();
          }
          else{
               res.close();
               return new Diagnosis();
          }
          return patient;
     }
     //DIAGNOSIS RELATED


     //SCORES RELATED
     public boolean insertPatientScore(int id, double das28esr, double das28crp, double cdai, double sdai){
          SQLiteDatabase db = this.getWritableDatabase();
          ContentValues contentValues = new ContentValues();
          contentValues.put(SCORE_PatientId, id);
          contentValues.put(SCORE_DAS28_ESR, das28esr);
          contentValues.put(SCORE_DAS28_CRP, das28crp);
          contentValues.put(SCORE_CDAI, cdai);
          contentValues.put(SCORE_SDAI, sdai);
          db.insert(TABLE_TWO,null,contentValues);
          return true;
     }

     public DiseaseActivity_Object getScore(int id, int mode){
          SQLiteDatabase db = this.getReadableDatabase();
          Cursor res;
          if(mode == 1) { //first
               res = db.rawQuery("Select * from " + TABLE_TWO +
                       " where " + SCORE_PatientId + "=" + id +
                       " ORDER BY " + SCORE_Date
                       , null);
          }
          else {         //latest
               res = db.rawQuery("Select * from " + TABLE_TWO +
                       " where " + SCORE_PatientId + "=" + id +
                       " ORDER BY " + SCORE_Date + " DESC"
                       , null);
          }
          String date1;
          double das28esr, das28crp, cdai, sdai;
          DiseaseActivity_Object dao = new DiseaseActivity_Object();
          if(res.moveToFirst()){
               das28esr = res.getDouble(res.getColumnIndex(SCORE_DAS28_ESR));
               das28crp = res.getDouble(res.getColumnIndex(SCORE_DAS28_CRP));
               cdai = res.getDouble(res.getColumnIndex(SCORE_CDAI));
               sdai = res.getDouble(res.getColumnIndex(SCORE_SDAI));
               date1 = res.getString(res.getColumnIndex(SCORE_Date));
               dao.setDas28esr(das28esr);
               dao.setDas28crp(das28crp);
               dao.setCdai(cdai);
               dao.setSdai(sdai);
               dao.setLastUpdated(date1);
               dao.setHas_record(true);
          }
          res.close();
          return dao;
     }

     public ArrayList<DiseaseActivity_Object> getAllScoresStatistics(){
          ArrayList<DiseaseActivity_Object> dao = new ArrayList<>();
          SQLiteDatabase db = this.getReadableDatabase();
          double das28esr,das28crp,sdai,cdai;
          DiseaseActivity_Object temp;
          Cursor res = db.rawQuery("SELECT * from " + TABLE_TWO,null);
          try{
               res.moveToFirst();
               if(!res.isAfterLast()){
                    das28esr = res.getDouble(res.getColumnIndex(SCORE_DAS28_ESR));
                    das28crp = res.getDouble(res.getColumnIndex(SCORE_DAS28_CRP));
                    sdai = res.getDouble(res.getColumnIndex(SCORE_SDAI));
                    cdai = res.getDouble(res.getColumnIndex(SCORE_CDAI));
                    temp = new DiseaseActivity_Object();
                    temp.setDas28esr(das28esr);
                    temp.setDas28crp(das28crp);
                    temp.setSdai(sdai);
                    temp.setCdai(cdai);
                    dao.add(temp);
                    res.moveToNext();
               }
          }catch (Exception e){}
          return dao;
     }
     //SCORES RELATED

     //DRUGS RELATED
     public boolean insertPatientDrugs(int id, int type, String admission, double dose){
          SQLiteDatabase db = this.getWritableDatabase();
          ContentValues contentValues = new ContentValues();
          contentValues.put(DRUGS_PATIENT_ID, id);
          contentValues.put(DRUGS_DRUG_TYPE, type);
          contentValues.put(DRUGS_ADMISSION, admission);
          contentValues.put(DRUGS_DOSE, dose);
          db.insert(TABLE_SIX,null,contentValues);
          return true;
     }

     public Drugs getLatestDrugs(int id){
          SQLiteDatabase db = this.getReadableDatabase();
          Drugs drugs = new Drugs();
          double dose;
          String date, admission;
          final String part1 = "Select * from " + TABLE_SIX + " where " +
                                   DRUGS_PATIENT_ID + "=" + id + " AND " + DRUGS_DRUG_TYPE + "=";
          final String part2 = " ORDER BY " + DRUGS_DATE + " DESC";
          for(int i = 1; i < 6; i++){
               Cursor res = db.rawQuery(part1 + i + part2, null);
               if(res.moveToFirst()){
                    date = res.getString(res.getColumnIndex(DRUGS_DATE));
                    admission = res.getString(res.getColumnIndex(DRUGS_ADMISSION));
                    dose = res.getDouble(res.getColumnIndex(DRUGS_DOSE));
                    res.close();
                    if (i == 1) drugs.setMtx(date,admission,dose);
                    else if (i == 2) drugs.setAza(date,admission,dose);
                    else if (i == 3) drugs.setHcqs(date,admission,dose);
                    else if (i == 4) drugs.setSterPred(date,admission,dose);
                    else drugs.setSterMeth(date,admission,dose);
               }
          }
          return drugs;
     }
     //DRUGS RELATED

     //BIOLOGICS RELATED
     public boolean insertPatientBiologics(int id, int type, int dose, int inf){
          SQLiteDatabase db = this.getWritableDatabase();
          ContentValues contentValues = new ContentValues();
          contentValues.put(BIOLOGICS_PatientId,id);
          contentValues.put(BIOLOGICS_TYPE,type);
          contentValues.put(BIOLOGICS_DOSE,dose);
          contentValues.put(BIOLOGICS_INFUSION,inf);
          db.insert(TABLE_THREE,null,contentValues);
          return true;
     }
     public Biologics getLatestBiologics(int id){
          SQLiteDatabase db = this.getReadableDatabase();
          Biologics biologics = new Biologics();
          int dose, inf;
          String date;
          final String part1 = "Select * from " + TABLE_THREE + " where " +
                  BIOLOGICS_PatientId + "=" + id + " AND " + BIOLOGICS_TYPE + "=";
          final String part2 = " ORDER BY " + BIOLOGICS_DATE + " DESC";
          for(int i = 1; i <6; i++){
               Cursor res = db.rawQuery(part1 + i + part2, null);
               if(res.moveToFirst()){
                    date = res.getString(res.getColumnIndex(BIOLOGICS_DATE));
                    dose = res.getInt(res.getColumnIndex(BIOLOGICS_DOSE));
                    inf = res.getInt(res.getColumnIndex(BIOLOGICS_INFUSION));
                    res.close();
                    if(i == 1) biologics.setInflix(dose,inf,date);
                    else if (i==2) biologics.setInflix2(dose,inf,date);
                    else if (i==3) biologics.setRitux(dose,inf,date);
                    else if (i==4) biologics.setTocili(dose,inf,date);
                    else if (i==5) biologics.setEtaner(dose,inf,date);
               }
          }
          return biologics;
     }
     //BIOLOGICS RELATED

     //GRAPHS RELATED
     public ListHolders getAllScores(int id) {
          SQLiteDatabase db = this.getReadableDatabase();
          ListHolders lh = new ListHolders();
          ArrayList<String> dates = new ArrayList<>();
          ArrayList<Double> das28esr = new ArrayList<>();
          ArrayList<Double> das28crp = new ArrayList<>();
          ArrayList<Double> cdai = new ArrayList<>();
          ArrayList<Double> sdai = new ArrayList<>();
          String temp_date;
          double temp_das28esr, temp_das28crp, temp_cdai, temp_sdai;
          try {
               Cursor res = db.rawQuery("SELECT * from " + TABLE_TWO + " where " + SCORE_PatientId + "=" + id + " "
                       + " ORDER BY " + SCORE_Date, null);
               res.moveToFirst();
               while (!res.isAfterLast()) {
                    temp_date = res.getString(res.getColumnIndex(SCORE_Date));
                    temp_das28esr = res.getDouble(res.getColumnIndex(SCORE_DAS28_ESR));
                    temp_das28crp = res.getDouble(res.getColumnIndex(SCORE_DAS28_CRP));
                    temp_cdai = res.getDouble(res.getColumnIndex(SCORE_CDAI));
                    temp_sdai = res.getDouble(res.getColumnIndex(SCORE_SDAI));
                    dates.add(temp_date);
                    das28esr.add(temp_das28esr);
                    das28crp.add(temp_das28crp);
                    cdai.add(temp_cdai);
                    sdai.add(temp_sdai);
                    res.moveToNext();
               }
               res.close();
               lh.setDates(dates);
               lh.setDas28esr(das28esr);
               lh.setDas28crp(das28crp);
               lh.setSdai(sdai);
               lh.setCdai(cdai);
          } catch (Exception e) {
               System.out.println(e.toString());
          }
          return lh;
     }
     //GRAPHS RELATED

     //QUESTIONNAIRE RELATED
     public boolean insertQuestionnaireEntry(int id, String tb, String hepa, String cancer){
          SQLiteDatabase db = this.getWritableDatabase();
          ContentValues contentValues = new ContentValues();
          contentValues.put(QUESTIONNAIRE_PATIENT_ID,id );
          contentValues.put(QUESTIONNAIRE_TB,tb);
          contentValues.put(QUESTIONNAIRE_HEPA,hepa);
          contentValues.put(QUESTIONNAIRE_CANCER,cancer);
          db.insert(TABLE_SEVEN,null,contentValues);
          return true;
     }
     //QUESTIONNAIRE RELATED


   /*  public void produceDummy(int id){
          SQLiteDatabase db= this.getWritableDatabase();
          ContentValues contentValues = new ContentValues();
          contentValues.put(SCORE_PatientId,id);
          contentValues.put(SCORE_Date,"2015-06-03 14:48:14");
          contentValues.put(SCORE_DAS28_ESR,5);
          contentValues.put(SCORE_DAS28_CRP,8);
          contentValues.put(SCORE_CDAI,20);
          contentValues.put(SCORE_SDAI,40);
          db.insert(TABLE_TWO,null,contentValues);
          contentValues = new ContentValues();
          contentValues.put(SCORE_PatientId,id);
          contentValues.put(SCORE_Date,"2015-06-13 14:48:14");
          contentValues.put(SCORE_DAS28_ESR,2);
          contentValues.put(SCORE_DAS28_CRP,4);
          contentValues.put(SCORE_CDAI,60);
          contentValues.put(SCORE_SDAI,15);
          db.insert(TABLE_TWO,null,contentValues);
     }*/
}

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