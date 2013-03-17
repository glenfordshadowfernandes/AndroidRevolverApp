package com.example.revolver;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class UserProfile extends Activity {
	
	
	
	MainActivity temp;
	String tag = "Revolver Log :";
	SQLiteDatabase myDB;
	ImageView userpic;
	TextView phoneNo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.revolv_appmainscreen);
        
        
        myDB = this.openOrCreateDatabase("RevolverDb", MODE_PRIVATE, null);
        Log.i(tag , "db opened");
        TextView useremailDisplay = (TextView)findViewById(R.id.UseremailText);
        Button closeButton = (Button)findViewById(R.id.CloseButton);
        userpic = (ImageView)findViewById(R.id.userimage);
        phoneNo = (TextView)findViewById(R.id.phoneNo);
        
        Cursor c = myDB.rawQuery("SELECT * FROM UserId",null);
        Log.i(tag , "cursor selected");
        int col = c.getColumnIndex("username");
        Log.i(tag , "col = "+col);
        c.moveToFirst();
        String userEmail = c.getString(col);
        Log.i(tag , "useremail = "+userEmail);
        TelephonyManager tMgr =(TelephonyManager)getSystemService(Context.TELEPHONY_SERVICE);
        String tempNo = tMgr.getLine1Number();
        
        useremailDisplay.setText(userEmail);
        Bitmap userimage = getImage();
        userpic.setImageBitmap(userimage);
        phoneNo.setText(tempNo);
        
        myDB.close();
        
        closeButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				
				finish();
				
			}
		});
        
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
    public Bitmap getImage(){

        String qu = "select userimg from UserId where rowid=1";
        Cursor cur = myDB.rawQuery(qu, null);

        if (cur.moveToFirst()){
            byte[] imgByte = cur.getBlob(0);
            cur.close();
            return BitmapFactory.decodeByteArray(imgByte, 0, imgByte.length);
        }
        if (cur != null && !cur.isClosed()) {
            cur.close();
        }       

        return null ;
    } 

}