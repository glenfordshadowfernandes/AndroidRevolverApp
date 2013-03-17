package com.example.revolver;

import android.os.Bundle;
import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class AppMainScreen extends Activity {
	
	
	MainActivity temp;
	String tag = "Revolver Log :";
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_screen);
        
        final SQLiteDatabase myDB = this.openOrCreateDatabase("RevolverDb", MODE_PRIVATE, null);
        Log.i(tag , "db opened");
        TextView useremailDisplay = (TextView)findViewById(R.id.UseremailText);
        Button closeButton = (Button)findViewById(R.id.CloseButton);
        Cursor c = myDB.rawQuery("SELECT * FROM UserId",null);
        Log.i(tag , "cursor selected");
        int col = c.getColumnIndex("username");
        Log.i(tag , "col = "+col);
        c.moveToFirst();
        String userEmail = c.getString(col);
        Log.i(tag , "useremail = "+userEmail);
        useremailDisplay.setText(userEmail);
        
        closeButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				myDB.close();
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
    

}