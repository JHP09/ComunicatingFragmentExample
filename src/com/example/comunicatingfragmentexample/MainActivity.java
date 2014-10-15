package com.example.comunicatingfragmentexample;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.os.Bundle;
import android.os.StrictMode;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.comunicatingfragmentexample.SampleFragment.SampleListener;
import com.example.comunicatingfragmentexample.MainActivity;
import com.example.simplefragmentexample.R;

public class MainActivity extends FragmentActivity implements SampleListener,
		AsyncResponse {
	
	ListView list;
	String[] web = { "", "", ""};	
	String[] synopsis = { "", "", "" };
	String[] imageId = { "", "", "" };
	String json = "";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		LongRunningGetIO jsonObj = new LongRunningGetIO();
		jsonObj.execute();
		jsonObj.delegate = this;

		
		  if (android.os.Build.VERSION.SDK_INT > 9) {
		      StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
		      StrictMode.setThreadPolicy(policy);
		    }

		CustomAdapter adapter = new CustomAdapter(MainActivity.this, web,imageId);
		list = (ListView) findViewById(R.id.list);
		list.setAdapter(adapter);
		list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				
				TextFragment textFragment = (TextFragment) getSupportFragmentManager().findFragmentById(R.id.text_fragment);
				textFragment.changeTextProperties(12, synopsis[+position]);

				 
			}
		});
	}

	public void processFinish(String output) {
		// this you will received result fired from async class of
		// onPostExecute(result) method.
		
		JSONObject obj; 
		try {
			
			obj = new JSONObject(output);
		    final JSONArray geodata = obj.getJSONArray("movies"); 
		    final int n = geodata.length();
		    for (int i = 0; i < n; ++i) { 
		    	final JSONObject movies = geodata.getJSONObject(i);
		    	String title = movies.getString("title"); 
		    	web[i] = title;
		    	synopsis[i] = movies.getString("synopsis");
		    	imageId[i] = movies.getJSONObject("posters").getString("thumbnail");
		    	
		    } 
		    
		    CustomAdapter adapter = new CustomAdapter(MainActivity.this, web, imageId);
			list = (ListView) findViewById(R.id.list);
			list.setAdapter(adapter);
		} 
		catch (JSONException e) { // TODO Auto-generated catchblock 
			  e.printStackTrace(); 
		}
		
	}

	public void onButtonClick(int fontsize, String text) {

	}

}
