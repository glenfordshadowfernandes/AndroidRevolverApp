package com.example.revolver;

import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

@SuppressLint("NewApi") public class MainActivity extends Activity {

	SQLiteDatabase myDB = null;
	String TableName = "UserId";
	String tag = "Revolver Log :";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        try {
			myDB = this.openOrCreateDatabase("RevolverDb", MODE_PRIVATE, null);
			 
			   /* Create a Table in the Database. */
			   myDB.execSQL("CREATE TABLE IF NOT EXISTS "
			     + TableName
			     + " (name VARCHAR, username VARCHAR);");
			 
			   
			   Log.i(tag , "table created");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
        
        final EditText username = (EditText)findViewById(R.id.Username);
        Button createUser = (Button)findViewById(R.id.CreateUser);
        
        createUser.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				try {
					Cursor c = myDB.rawQuery("SELECT * FROM UserId",null);
			        //Log.i(tag , "cursor selected");
			        int col = c.getColumnIndex("username");
			        //Log.i(tag , "col = "+col);
			        c.moveToFirst();
			        String userEmail = c.getString(col);
			        
			        if(userEmail.isEmpty())
			        {
						myDB.execSQL("INSERT INTO "
							     + TableName
							     + "(name, username)"
							     + " VALUES (' ', '"+username.getText().toString()+"');");
						Log.i(tag , "INSERTED VALUES IN DB");
			        }
			        else
			        {
			        	Log.i(tag , "NOT INSERTED coz value present");
			        }
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
    

}