package com.example.revolver;

import java.util.ArrayList;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import android.os.AsyncTask;
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
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class PlacesScreen extends Activity {
	
	HorizontalScrollView imageScrollView;
	LinearLayout imageHolder;
	Boolean Favflag = false;
	ListView _friendList;
	String _map_Android_API_KEY = "AIzaSyAreNy2bN3zL0LRfYWqwaCeznxKbSB_X1A";
	String _map_API_KEY = "AIzaSyBYHDjXAWUoeeIHraRvdenfpUUXQL3c56w";
	String tag = "http_CHECK";
	JSONObject jObject;
	String _PLACE_NAME;
	JSONArray _PLACE_RESULTS;
	TextView placeName;
	ArrayList<String> _PHOTO_REFERENCE_KEYS = new ArrayList<String>();
	Bitmap placePic;
	ImageView img1,img2,img3,img4,img5,img6;
	
	@SuppressWarnings("unused")
	protected class httpRequest extends AsyncTask<String, Void, String> {
        
		protected void onPostExecute(String result) {
			
			placeName = (TextView)findViewById(R.id.textView2);
			placeName.setText(result);
			
			String photoFetch1 ="https://maps.googleapis.com/maps/api/place/photo?maxwidth=400&photoreference="+_PHOTO_REFERENCE_KEYS.get(0)+"&sensor=false&key="+_map_API_KEY;
	        String photoFetch2 ="https://maps.googleapis.com/maps/api/place/photo?maxwidth=400&photoreference="+_PHOTO_REFERENCE_KEYS.get(1)+"&sensor=false&key="+_map_API_KEY;
	        String photoFetch3 ="https://maps.googleapis.com/maps/api/place/photo?maxwidth=400&photoreference="+_PHOTO_REFERENCE_KEYS.get(2)+"&sensor=false&key="+_map_API_KEY;
	        String photoFetch4 ="https://maps.googleapis.com/maps/api/place/photo?maxwidth=400&photoreference="+_PHOTO_REFERENCE_KEYS.get(3)+"&sensor=false&key="+_map_API_KEY;
	        String photoFetch5 ="https://maps.googleapis.com/maps/api/place/photo?maxwidth=400&photoreference="+_PHOTO_REFERENCE_KEYS.get(4)+"&sensor=false&key="+_map_API_KEY;
	        
	        new httpPhotoRequest().execute(photoFetch1 , Integer.toString(0));
	        new httpPhotoRequest().execute(photoFetch2 , Integer.toString(1));
	        new httpPhotoRequest().execute(photoFetch3 , Integer.toString(2));
	        new httpPhotoRequest().execute(photoFetch4 , Integer.toString(3));
	        new httpPhotoRequest().execute(photoFetch5 , Integer.toString(4));
			
	        Log.i(tag  ,"photo array size = "+ _PHOTO_REFERENCE_KEYS.size());
	    }

		@Override
		protected String doInBackground(String... url) {
			// TODO Auto-generated method stub
			 HttpClient httpClient = new DefaultHttpClient();
			   
		        try {
		        	
		        	HttpGet httpGet = new HttpGet(url[0]);
				     HttpResponse httpResponseGet = httpClient.execute(httpGet);
				     HttpEntity resEntityGet = httpResponseGet.getEntity(); 
				           if (resEntityGet != null) 
				           {
				            String responseString = EntityUtils.toString(resEntityGet);             
				                         
				            Log.i(tag ,"Response = "+responseString);
				            
				            jObject = new JSONObject(responseString);
				            _PLACE_RESULTS = jObject.getJSONArray("results");
				            
				            JSONObject _PLACE_NAME_OBJ =  _PLACE_RESULTS.getJSONObject(0);
				            _PLACE_NAME = _PLACE_NAME_OBJ.getString("name");
				            
				            for (int i = 0; i <= 4; i++) {
								
				            	JSONObject _PLACE_PHOTO_REFERENCE =  _PLACE_RESULTS.getJSONObject(i);
				            	JSONArray _TEMP_ARRAY = _PLACE_PHOTO_REFERENCE.getJSONArray("photos");
				            	JSONObject _TEMP_PHOTO_OBJECT =  _TEMP_ARRAY.getJSONObject(0);
				            	_PHOTO_REFERENCE_KEYS.add(_TEMP_PHOTO_OBJECT.getString("photo_reference"));
				            	Log.i(tag ,"_PLACE_RESULTS SIZE = "+ _PLACE_RESULTS.length());
				            	//Log.i(tag ,"TEMP_ARRAY SIZE = "+_TEMP_PHOTO_OBJECT.getString("photo_reference"));
							}
				            
				            Log.i(tag ,"_PHOTO_REFERENCE_KEYS SIZE = "+ _PHOTO_REFERENCE_KEYS.size());
							for (int i = 0; i < _PHOTO_REFERENCE_KEYS.size(); i++) {
								
								Log.i(tag ,"PHOTO REFERENCE KEY = "+_PHOTO_REFERENCE_KEYS.get(i));
							}
				            
				            Log.i(tag ,"Place name = "+_PLACE_NAME);
				            
				           }
				           
				} catch (Exception e) {
					// TODO: handle exception
					Log.i(tag, e.toString());
					
				}
		        
				Log.i(tag  ,"Passed https ");
				
			return _PLACE_NAME;
		}
		
    }
	
	@SuppressWarnings("unused")
	protected class httpPhotoRequest extends AsyncTask<String, Void, Bitmap> {
        
		protected void onPostExecute(Bitmap placePhoto, String... pictNum) {
			
			String count = pictNum[1].toString();
			Log.i(tag  ,"String name = "+pictNum[1].toString());
			img1.setImageBitmap(placePhoto);
	    }

		@Override
		protected Bitmap doInBackground(String... url) {
			// TODO Auto-generated method stub
			 HttpClient httpClient = new DefaultHttpClient();
			   
		        try {
		        	
		        	HttpGet httpGet = new HttpGet(url[0]);
				     HttpResponse httpResponseGet = httpClient.execute(httpGet);
				     HttpEntity resEntityGet = httpResponseGet.getEntity(); 
				     placePic = BitmapFactory.decodeStream(resEntityGet.getContent());
				     Log.i(tag  ,"Bitmap works");
				     
				} catch (Exception e) {
					// TODO: handle exception
					Log.i(tag, e.toString());
					
				}
		        
				//Log.i(tag  ,"Passed https ");
				
			return placePic;
		}
		
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.revolve_places_screen);
        
        imageScrollView = (HorizontalScrollView)findViewById(R.id.horizontalScrollView1);
        imageHolder = (LinearLayout)findViewById(R.id.horiImageHolder);
        _friendList = (ListView)findViewById(R.id._visitedFreindList);
        
        
        img1 = (ImageView)findViewById(R.id.PlacePic1);
        img2 = (ImageView)findViewById(R.id.PlacePic2);
        img3 = (ImageView)findViewById(R.id.PlacePic3);
        img4 = (ImageView)findViewById(R.id.PlacePic4);
        img5 = (ImageView)findViewById(R.id.PlacePic5);
        img6 = (ImageView)findViewById(R.id.PlacePic6);
        
        /*
        img1.setImageResource(R.drawable.diary_icon_norma_extra_large);
        img2.setImageResource(R.drawable.fav_icon_norma_large);
        img3.setImageResource(R.drawable.friend_icon_normal_large);
        img4.setImageResource(R.drawable.places_icon_normal_large);
        img5.setImageResource(R.drawable.religious_icon_normal_large);
        img6.setImageResource(R.drawable.restau_icon_normal_large);
        */
        
        String[] friendListValues = new String[] { "Friend1", "Friend2", "Friend3", "Friend4","Friend5","Friend6" };
        
        ArrayAdapter<String> diaryAdapter = new ArrayAdapter<String>(this,
        		android.R.layout.simple_list_item_1, android.R.id.text1, friendListValues);
        _friendList.setAdapter(diaryAdapter);
        
        
        //CODE TO FETCH GOOGLE PLACE API RESULTS --- START        
        
        String url = "https://maps.googleapis.com/maps/api/place/nearbysearch/json?location=-33.8670522,151.1957362&radius=500&types=food&name=harbour&sensor=true&key="+_map_API_KEY;
        new httpRequest().execute(url);  
        Log.i(tag  ,"out of http request ");
        
       /* for (int i = 0; i < _PHOTO_REFERENCE_KEYS.size(); i++) {
        	Log.i(tag  ,"in photo loop ");
        	String photoFetch ="https://maps.googleapis.com/maps/api/place/photo?maxwidth=400&photoreference="+_PHOTO_REFERENCE_KEYS.get(i)+"&sensor=false&key="+_map_API_KEY;
        	 new httpPhotoRequest().execute(photoFetch , Integer.toString(i)); 
		}  */
        
      /*  String photoFetch1 ="https://maps.googleapis.com/maps/api/place/photo?maxwidth=400&photoreference="+_PHOTO_REFERENCE_KEYS.get(0)+"&sensor=false&key="+_map_API_KEY;
        String photoFetch2 ="https://maps.googleapis.com/maps/api/place/photo?maxwidth=400&photoreference="+_PHOTO_REFERENCE_KEYS.get(1)+"&sensor=false&key="+_map_API_KEY;
        String photoFetch3 ="https://maps.googleapis.com/maps/api/place/photo?maxwidth=400&photoreference="+_PHOTO_REFERENCE_KEYS.get(2)+"&sensor=false&key="+_map_API_KEY;
        String photoFetch4 ="https://maps.googleapis.com/maps/api/place/photo?maxwidth=400&photoreference="+_PHOTO_REFERENCE_KEYS.get(3)+"&sensor=false&key="+_map_API_KEY;
        String photoFetch5 ="https://maps.googleapis.com/maps/api/place/photo?maxwidth=400&photoreference="+_PHOTO_REFERENCE_KEYS.get(4)+"&sensor=false&key="+_map_API_KEY;
        
        new httpPhotoRequest().execute(photoFetch1 , Integer.toString(0));
        new httpPhotoRequest().execute(photoFetch2 , Integer.toString(1));
        new httpPhotoRequest().execute(photoFetch3 , Integer.toString(2));
        new httpPhotoRequest().execute(photoFetch4 , Integer.toString(3));
        new httpPhotoRequest().execute(photoFetch5 , Integer.toString(4));
        */
        Log.i(tag  ,"photo array size = "+ _PHOTO_REFERENCE_KEYS.size());
        
        Log.i(tag  ,"out of all http request ");
        
        //CODE TO FETCH GOOGLE PLACE API RESULTS --- END
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.items, menu);
        return super.onCreateOptionsMenu(menu);
        //return true;
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
 
        super.onOptionsItemSelected(item);
 
        
        switch(item.getItemId()){
            case R.id.markFav:
                Toast.makeText(getBaseContext(), "You selected Fav", Toast.LENGTH_SHORT).show();
                if(!Favflag)
                {
                	item.setIcon(android.R.drawable.star_on);
                	this.Favflag = true;
                }
                else
                {
                	item.setIcon(android.R.drawable.star_off);
                	this.Favflag = false;
                }
                break;
 
            case R.id.visited:
                Toast.makeText(getBaseContext(), "You selected Visited", Toast.LENGTH_SHORT).show();
                break;
 
        }
        
        
        return true;
 
    }
    
    

}