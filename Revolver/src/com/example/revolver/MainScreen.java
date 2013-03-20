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
import android.view.MenuInflater;
import android.view.MenuItem;

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
       
        
        
        
        diary.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent diaryListIntent = new Intent(MainScreen.this,DiaryList.class);
				startActivity(diaryListIntent);
			}
		});
        
        friends.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				
				Toast msg = Toast.makeText(getBaseContext(), "called friends", Toast.LENGTH_SHORT);
				msg.show();
			}
		});
        
        relig.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				
				Toast msg = Toast.makeText(getBaseContext(), "called religs", Toast.LENGTH_SHORT);
				msg.show();
			}
		});
        
        rest.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				
				Toast msg = Toast.makeText(getBaseContext(), "called restaurants", Toast.LENGTH_SHORT);
				msg.show();
			}
		});
       
        starred.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				
				Toast msg = Toast.makeText(getBaseContext(), "called favs", Toast.LENGTH_SHORT);
				msg.show();
			}
		});
        
        myPlaces.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				
				Toast msg = Toast.makeText(getBaseContext(), "called My places", Toast.LENGTH_SHORT);
				msg.show();
			}
		});
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.main, menu);
    	super.onCreateOptionsMenu(menu);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);
        return true;
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.user_profile_setter: 
            startActivity(new Intent(this, UserProfile.class));
        }
        return true;
    }
    
}

