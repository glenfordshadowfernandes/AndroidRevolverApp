package com.example.revolver;

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
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class DiaryList extends Activity {
	
	
	
	ListView diaryList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.revolve_diary_list);
        
        diaryList = (ListView)findViewById(R.id.diaryList);
        String[] diaryListValues = new String[] { "My Places", "Restaurants", "Notes", "My Friends" };
        
        ArrayAdapter<String> diaryAdapter = new ArrayAdapter<String>(this,
        		android.R.layout.simple_list_item_1, android.R.id.text1, diaryListValues);
        diaryList.setAdapter(diaryAdapter);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
    

}