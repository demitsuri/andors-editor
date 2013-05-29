//GPL v2 or later
/*
 * 
 */
package com.litecoding.andorstrail.editor;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;

import org.holoeverywhere.app.Activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.litecoding.andorstrail.editor.entity.v33.FileHeader;
import com.litecoding.andorstrail.editor.util.ExtRes;

public class Main extends Activity {
	public static final String TAG = "andors-trail-editor"; 
	public static final String EXTRA_FILEPATH = "filepath";
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        if(!ExtRes.init(getApplicationContext())) {
        	Toast.makeText(this, R.string.msg_err_cant_find_andors_trail, Toast.LENGTH_LONG).show();
        }
        
        final String basePath = Environment.getExternalStorageDirectory().getAbsolutePath().
        		concat(File.separator).
        		concat("andors-trail").
        		concat(File.separator);
        
        LinearLayout layout = (LinearLayout)findViewById(R.id.containerSaves);
        LayoutInflater inflater = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        for(final String element : new File(basePath).list()) {
        	if(!element.startsWith("savegame")) {
        		continue;
        	}
        	View v = inflater.inflate(R.layout.savefile, null);
        	
        	/*
        	Button btnSaveFile = new Button(this);
        	
        	btnSaveFile.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
        	btnSaveFile.setText(element);
        	btnSaveFile.setOnClickListener(new OnClickListener() {
				
				public void onClick(View v) {
					Intent intent = new Intent(Main.this, Editor.class);
					intent.putExtra(EXTRA_FILEPATH, basePath.concat(element));
					Main.this.startActivity(intent);
				}
			});
        	
        	layout.addView(btnSaveFile);
        	*/
        	
        	try {
	        	DataInputStream dis = new DataInputStream(new FileInputStream(basePath.concat(element)));
	        	FileHeader fh = new FileHeader();
	        	boolean res = fh.read(dis);
	        	dis.close();
	        	
	        	if(!res) {
	        		continue;
	        	}
	        	
	        	ImageView imgStatus = (ImageView)v.findViewById(R.id.status);
	        	if(fh.mVer < 33) {
	        		imgStatus.setBackgroundColor(Color.YELLOW);
	        		
	        		v.setOnClickListener(new OnClickListener() {
	    				
	    				public void onClick(View v) {
	    					Toast.makeText(Main.this, 
	    							R.string.msg_warn_old_save_ver, 
	    							Toast.LENGTH_LONG).show();
	    				}
	    			});
	        	} else if(fh.mVer == 33) {
	        		imgStatus.setBackgroundColor(Color.GREEN);
	        		
	        		v.setOnClickListener(new OnClickListener() {
	    				
	    				public void onClick(View v) {
	    					Intent intent = new Intent(Main.this, Editor.class);
	    					intent.putExtra(EXTRA_FILEPATH, basePath.concat(element));
	    					Main.this.startActivity(intent);
	    				}
	    			});
	        	} else {
	        		imgStatus.setBackgroundColor(Color.RED);
	        		
	        		v.setOnClickListener(new OnClickListener() {
	    				
	    				public void onClick(View v) {
	    					Toast.makeText(Main.this, 
	    							R.string.msg_err_new_save_ver, 
	    							Toast.LENGTH_LONG).show();
	    				}
	    			});
	        	}
	        	
	        	TextView labelName = (TextView)v.findViewById(R.id.labelName);
	        	labelName.setText(fh.mName);
	        	
	        	TextView labelSummary = (TextView)v.findViewById(R.id.labelSummary);
	        	labelSummary.setText(fh.mSummary);
	        	
	        	layout.addView(v);
	        	
        	} catch(Exception e) {
        		
        	}
        	
        	Log.d(TAG, element);
        }
        
    }
}