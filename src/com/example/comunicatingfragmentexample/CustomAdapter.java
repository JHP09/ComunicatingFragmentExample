package com.example.comunicatingfragmentexample;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import com.example.simplefragmentexample.R;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class CustomAdapter extends ArrayAdapter<String> {

	private final Activity context;
	private final String[] web;
	private final String[] imageId;

	public CustomAdapter(Activity context,String[] web, String[] imageId) {
		super(context, R.layout.sample_fragment_view, web);
		this.context = context;
		this.web = web;
		this.imageId = imageId;
	}
	
	public View getView(int position, View view, ViewGroup parent) {
		LayoutInflater inflater = context.getLayoutInflater();
		View rowView= inflater.inflate(R.layout.sample_fragment_view, null, true);
		TextView txtTitle = (TextView) rowView.findViewById(R.id.txt);
		ImageView imageView = (ImageView) rowView.findViewById(R.id.img);
		txtTitle.setText(web[position]);
		
		
		Bitmap mIcon_val = null;
		URL newurl;
		try {
			newurl = new URL(imageId[position]);
			mIcon_val = BitmapFactory.decodeStream(newurl.openConnection() .getInputStream());
			imageView.setImageBitmap(mIcon_val);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		return rowView;
	}
}
