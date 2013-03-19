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
import android.widget.Toast;

@SuppressLint("NewApi") public class MainScreen extends Activity {

	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.revolv_mainscreen);
        ImageView diary = (ImageView)findViewById(R.id.DiaryCircle);
        ImageView friends = (ImageView)findViewById(R.id.FriendsCircle);
        ImageView relig = (ImageView)findViewById(R.id.RelgCircle);
        ImageView rest = (ImageView)findViewById(R.id.RestCircle);
        ImageView starred = (ImageView)findViewById(R.id.StarredCircle);
        ImageView myPlaces = (ImageView)findViewById(R.id.MyPlCircle);
        //diary.setColorFilter(0xFFFF0000, PorterDuff.Mode.MULTIPLY);
        
        
        
        diary.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Toast msg = Toast.makeText(getBaseContext(), "finishes the current intent", Toast.LENGTH_SHORT);
				msg.show();
			}
		});
        
       
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
   
    
}

