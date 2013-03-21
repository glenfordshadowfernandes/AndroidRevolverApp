package com.example.revolver;

import java.util.ArrayList;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

public class PlacesScreen extends Activity {
	
	HorizontalScrollView imageScrollView;
	LinearLayout imageHolder;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.revolve_places_screen);
        
        imageScrollView = (HorizontalScrollView)findViewById(R.id.horizontalScrollView1);
        imageHolder = (LinearLayout)findViewById(R.id.horiImageHolder);
        
       
        
        ImageView img1 = (ImageView)findViewById(R.id.PlacePic1);
        ImageView img2 = (ImageView)findViewById(R.id.PlacePic2);
        ImageView img3 = (ImageView)findViewById(R.id.PlacePic3);
        ImageView img4 = (ImageView)findViewById(R.id.PlacePic4);
        ImageView img5 = (ImageView)findViewById(R.id.PlacePic5);
        ImageView img6 = (ImageView)findViewById(R.id.PlacePic6);
        
        img1.setImageResource(R.drawable.diary_icon_norma_extra_large);
        img2.setImageResource(R.drawable.fav_icon_norma_large);
        img3.setImageResource(R.drawable.friend_icon_normal_large);
        img4.setImageResource(R.drawable.places_icon_normal_large);
        img5.setImageResource(R.drawable.religious_icon_normal_large);
        img6.setImageResource(R.drawable.restau_icon_normal_large);
        
        
        
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
    
    

}