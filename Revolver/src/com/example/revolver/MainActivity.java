package com.example.revolver;

import java.io.ByteArrayOutputStream;

import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

@SuppressLint("NewApi") public class MainActivity extends Activity {

	SQLiteDatabase myDB = null;
	String TableName = "UserId";
	String tag = "Revolver Log :";
	int RESULT_LOAD_IMAGE = 1;
	ImageView userpicture;
	Bitmap userpic;
	byte[] userimg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.revolve_mainactivity);
        
        
        final EditText username = (EditText)findViewById(R.id.Username);
        Button createUser = (Button)findViewById(R.id.CreateUser);
    	userpicture = (ImageView)findViewById(R.id.userpicture);
        
        
        try {
			myDB = this.openOrCreateDatabase("RevolverDb", MODE_PRIVATE, null);
			 
			   /* Create a Table in the Database. */
			   myDB.execSQL("CREATE TABLE IF NOT EXISTS "
			     + TableName
			     + " (name VARCHAR, username VARCHAR, userimg BLOB);");
			 
			   Log.i(tag , "table created");
			   Cursor c = myDB.rawQuery("SELECT count(*) FROM UserId",null);
			   if(c!=null)
			   {
				   c.moveToFirst();
				   if(c.getInt(0)!=0){
					   Log.i(tag , "table rows = "+c.getInt(0));
					   c.close();
			        	Intent main_screen = new Intent(MainActivity.this,AppMainScreen.class);
						startActivity(main_screen);
				   }
				   else
				   {
					   Log.i(tag , "table has no rows");
				   }
			   }
		       
			   
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
      
        userpicture.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				
				 Intent i = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
	             startActivityForResult(i, RESULT_LOAD_IMAGE);	
				
			}
		});
        
        
       
        
        createUser.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				try {
					/*
						myDB.execSQL("INSERT INTO "
							     + TableName
							     + "(name, username)"
							     + " VALUES (' ', '"+username.getText().toString()+"');");
					*/
					userimg = getBitmapAsByteArray(userpic);
					ContentValues cv = new  ContentValues();
					cv.put("name", " ");
					cv.put("username",username.getText().toString());
					if(userimg!=null){
						cv.put("userimg", userimg);
					}
					myDB.insert("UserId", null, cv);
					
					Log.i(tag , "INSERTED VALUES IN DB");
			        
				} catch (Exception e) {
					// TODO: handle exception
					Log.i(tag , "NOT INSERTED");
				}
				finally{
					myDB.close();
				}
				
				
				
				Intent main_screen = new Intent(MainActivity.this,AppMainScreen.class);
				startActivity(main_screen);
				
			}
		});
        
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
    //this function catches any intent for activity 
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
         
        if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && null != data) {
            Uri selectedImage = data.getData();
            String[] filePathColumn = { MediaStore.Images.Media.DATA };
 
            Cursor cursor = getContentResolver().query(selectedImage,filePathColumn, null, null, null);
            cursor.moveToFirst();
 
            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            String picturePath = cursor.getString(columnIndex);
            cursor.close();
             
          
            userpicture.setImageBitmap(BitmapFactory.decodeFile(picturePath));
            userpicture.buildDrawingCache();
            userpic = userpicture.getDrawingCache();
          
            
        }
     
     
    }
    
    public static byte[] getBitmapAsByteArray(Bitmap bitmap) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        bitmap.compress(CompressFormat.PNG, 0, outputStream);       
        return outputStream.toByteArray();
    }
    
}

