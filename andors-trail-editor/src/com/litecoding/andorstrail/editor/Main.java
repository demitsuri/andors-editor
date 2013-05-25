//GPL v2 or later
/*
 * 
 */
package com.litecoding.andorstrail.editor;

import java.io.File;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;
import android.widget.LinearLayout.LayoutParams;

public class Main extends Activity {
	public static final String TAG = "andors-trail-editor"; 
	public static final String EXTRA_FILEPATH = "filepath";
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        try {
        	Context atCtx = getApplicationContext().createPackageContext("com.gpl.rpg.AndorsTrail", Context.CONTEXT_IGNORE_SECURITY);
        	Resources atRes = atCtx.getResources();
        	int id = atRes.getIdentifier("app_description", "string", "com.gpl.rpg.AndorsTrail");
        	if(id == 0) {
        		Toast.makeText(this, "no such resource", Toast.LENGTH_LONG).show();
        	} else {
        		Toast.makeText(this, atRes.getText(id), Toast.LENGTH_LONG).show();
        	}
        } catch(Exception e) {
        	Toast.makeText(this, R.string.msg_err_cant_find_andors_trail, Toast.LENGTH_LONG).show();
        }
        
        final String basePath = Environment.getExternalStorageDirectory().getAbsolutePath().
        		concat(File.separator).
        		concat("andors-trail").
        		concat(File.separator);
        
        LinearLayout layout = (LinearLayout)findViewById(R.id.containerSaves);
        for(final String element : new File(basePath).list()) {
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
        	Log.d(TAG, element);
        }
        
        //Toast.makeText(this, R.string.msg_warn_savefile_version, Toast.LENGTH_LONG).show();
    }
}