package com.example.patrick.radoctor;

import android.app.ActionBar;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.provider.Contacts;
import android.provider.MediaStore;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.io.File;
import java.nio.InvalidMarkException;
import java.util.ArrayList;


public class Photos_Activity extends ActionBarActivity {
     private int PICK_IMAGE_REQUEST = 1;
     private int patient_id;
     DBHelper db;
     LinearLayout main1;
     Button button;
     
     void printAllImages(ArrayList<String> paths){
          ImageView temp;
          LinearLayout newLayout = new LinearLayout(this);
          int i;
          int count = 0;
          for (i = 0; i < paths.size(); i++){
               final String path = paths.get(i);
               temp = new ImageView(this);
               try{
                    LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(200,200);
                    layoutParams.setMargins(10,10,10,10);
                    temp.setImageURI(Uri.fromFile(new File(paths.get(i))));
                    temp.setLayoutParams(layoutParams);
                    temp.setBackgroundColor(Color.parseColor("#000000"));
                    temp.setScaleType(ImageView.ScaleType.FIT_XY);
                    temp.setPadding(1, 1, 1, 1);
                    temp.setAdjustViewBounds(true);
                    temp.setContentDescription(paths.get(i));
                    temp.setOnClickListener(new View.OnClickListener() {
                         @Override
                         public void onClick(View v) {
                              Intent intent = new Intent(Photos_Activity.this, Zoomin_Activity.class);
                              intent.putExtra("path",path);
                              startActivity(intent);
                         }
                    });
                    count++;
               }catch(Exception e){ e.printStackTrace();}
               newLayout.addView(temp);
               if(count%3 == 0) {
                    main1.addView(newLayout);
                    newLayout = new LinearLayout(this);
                    newLayout.setOrientation(LinearLayout.HORIZONTAL);
               }
          }
          if(count%3 != 0){ main1.addView(newLayout); }
     }


     @Override
     protected void onCreate(Bundle savedInstanceState) {
          super.onCreate(savedInstanceState);
          setContentView(R.layout.activity_photos);
          patient_id = getIntent().getExtras().getInt("patient_id");
          db = new DBHelper(this);
          main1 = (LinearLayout)findViewById(R.id.addPhotosLinearLayout);
          ArrayList<String> paths = db.getAllPhotos(patient_id).getFile_paths();
          printAllImages(paths);
          button = (Button)findViewById(R.id.buttonAddPhoto);
     }


     @Override
     public boolean onCreateOptionsMenu(Menu menu) {
          // Inflate the menu; this adds items to the action bar if it is present.
          getMenuInflater().inflate(R.menu.menu_photos, menu);
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

     public void onButtonClicked(View view){
          boolean pressed = ((Button)view).isPressed();
          switch(view.getId()){
               case R.id.buttonAddPhoto:{
                    Intent intent = new Intent();
                    intent.setType("image/*");
                    intent.setAction(Intent.ACTION_GET_CONTENT);
                    startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
               }
          }
     }
     @Override
     protected void onActivityResult(int requestCode, int resultCode, Intent data){
          String path = "";
          ImageView temp = new ImageView(this);
          super.onActivityResult(requestCode, resultCode, data);
          if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
               Uri uri = data.getData();
               String[] projection = { MediaStore.Images.Media.DATA};
               Cursor cursor = getContentResolver().query(uri, projection, null, null, null);
               cursor.moveToFirst();
               int columnIndex = cursor.getColumnIndex(projection[0]);
               path = cursor.getString(columnIndex);
               cursor.close();
               if(db.insertPhoto(patient_id,path)){
                    Toast.makeText(getApplicationContext(), "Successfully Added!", Toast.LENGTH_SHORT).show();
               }
               LinearLayout ll = (LinearLayout)findViewById(R.id.addPhotosLinearLayout);
               ll.removeAllViews();
               main1.addView(button);
               ArrayList<String> paths = db.getAllPhotos(patient_id).getFile_paths();
               printAllImages(paths);
          }
     }
}
