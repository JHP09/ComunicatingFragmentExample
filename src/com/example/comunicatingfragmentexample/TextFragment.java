package com.example.comunicatingfragmentexample;

import com.example.simplefragmentexample.R;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class TextFragment extends Fragment {

	public TextView textview;	
	public ImageView imageview;
	private Integer[] imageId;
	
    @Override
    public View onCreateView(LayoutInflater inflater, 
              ViewGroup container, Bundle savedInstanceState) {
        
        View view = inflater.inflate(R.layout.text_fragment, 
                   container, false);
        
        textview = (TextView) view.findViewById(R.id.textView1);
    
        return view;
    }
    
    public void changeTextProperties(int fontsize, String text)
    {
    	textview.setTextSize(fontsize);
    	textview.setText(text);
    } 
    
	public void changeImageProperties(Integer img)
    {
    	imageview.setImageResource(imageId[img]);
    } 
	
}