//GPL v2 or later
/*
 * 
 */
package com.litecoding.andorstrail.editor;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.util.LinkedList;
import java.util.List;

import org.holoeverywhere.app.Activity;
import org.holoeverywhere.widget.ListView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListAdapter;
import android.widget.Toast;

import com.litecoding.andorstrail.editor.entity.v33.FileHeader;
import com.litecoding.andorstrail.editor.util.ExtRes;
import com.litecoding.andorstrail.editor.util.ExtendedFileHeader;
import com.litecoding.andorstrail.editor.util.SaveInfoMapper;
import com.litecoding.classkit.view.ObjectAdapter;

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
        
        List<FileHeader> saveHeaders = new LinkedList<FileHeader>();
        ListView listView = (ListView)findViewById(R.id.listSaves);
        ObjectAdapter<FileHeader> adapter = new ObjectAdapter<FileHeader>(getLayoutInflater(), 
        		saveHeaders, 
        		R.layout.item_savefile, 
        		new SaveInfoMapper());
        
        for(final String element : new File(basePath).list()) {
        	if(!element.startsWith("savegame")) {
        		continue;
        	}
        	
        	try {
	        	DataInputStream dis = new DataInputStream(new FileInputStream(basePath.concat(element)));
	        	ExtendedFileHeader fh = new ExtendedFileHeader();
	        	boolean res = fh.read(dis);
	        	dis.close();
	        	
	        	if(!res) {
	        		continue;
	        	}
	        	
	        	fh.mPath = basePath;
	        	fh.mFilename = element;
	        	
	        	saveHeaders.add(fh);
        	} catch(Exception e) {
        		
        	}
        	
        } //for
        
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> adapterView, View view, int pos,
					long id) {
				ListAdapter adapter = (ListAdapter)adapterView.getAdapter();
				ExtendedFileHeader fh = (ExtendedFileHeader)adapter.getItem(pos);
				
				if(fh.mVer < 33) {
					Toast.makeText(Main.this, 
						R.string.msg_warn_old_save_ver, 
						Toast.LENGTH_LONG).show();
				} else if(fh.mVer > 33) {
					Toast.makeText(Main.this, 
						R.string.msg_err_new_save_ver, 
						Toast.LENGTH_LONG).show();
				} else {
					Intent intent = new Intent(Main.this, Editor.class);
					intent.putExtra(EXTRA_FILEPATH, fh.mPath.concat(fh.mFilename));
					Main.this.startActivity(intent);
				}
			}
		});
        adapter.notifyDataSetChanged();
    }
}