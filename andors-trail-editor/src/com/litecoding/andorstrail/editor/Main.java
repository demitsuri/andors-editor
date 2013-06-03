//GPL v2 or later
/*
 * 
 */
package com.litecoding.andorstrail.editor;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.holoeverywhere.app.Activity;
import org.holoeverywhere.app.ProgressDialog;
import org.holoeverywhere.widget.ListView;

import android.content.Intent;
import android.content.res.TypedArray;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListAdapter;
import android.widget.Toast;

import com.actionbarsherlock.app.ActionBar;
import com.litecoding.andorstrail.editor.util.ExtendedFileHeader;
import com.litecoding.andorstrail.editor.util.SaveInfoMapper;
import com.litecoding.andorstrail.res.ExtRes;
import com.litecoding.classkit.view.ObjectAdapter;

public class Main extends Activity {
	public static final String TAG = "andors-trail-editor"; 
	public static final String EXTRA_FILEPATH = "filepath";
	
	private ProgressDialog mProgressDialog;
	private String mBasePath;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        if(!ExtRes.init(getApplicationContext())) {
        	Toast.makeText(this, R.string.msg_err_cant_find_andors_trail, Toast.LENGTH_LONG).show();
        }
        
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(R.string.title_main_select_save);
        
        mBasePath = Environment.getExternalStorageDirectory().getAbsolutePath().
        		concat(File.separator).
        		concat("andors-trail").
        		concat(File.separator);
        
        ListView listView = (ListView)findViewById(R.id.listSaves);
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
        
        mProgressDialog = new ProgressDialog(this);
        readGameDataTask();
    }
    
    protected void readGameDataTask() {
    	mProgressDialog.setTitle(R.string.title_dialog_load_resources);
        mProgressDialog.show();
        
        ReadGameDataTask task = new ReadGameDataTask();
        task.execute();
    }
    
    protected void readGameDataTaskFinished() {
		Main.this.mProgressDialog.dismiss();
		readHeadersTask();
    }
    
    protected void readHeadersTask() {
    	mProgressDialog.setTitle(R.string.title_dialog_search_save);
        mProgressDialog.show();
        
        ReadHeadersTask task = new ReadHeadersTask();
        task.execute(mBasePath);
    }
    
    protected void readHeadersTaskFinished() {
		Main.this.mProgressDialog.dismiss();
    }
    
    class ReadGameDataTask extends AsyncTask<Void, String, Map<String, Object>> {

		@Override
		protected Map<String, Object> doInBackground(Void... params) {
			Map<String, Object> result = new HashMap<String, Object>();
			
			if(ExtRes.isDataLoaded()) {
				return result;
			}
			
			if(!ExtRes.loadItemCategories())
				return result;
			
			if(!ExtRes.loadItems())
				return result;
			
			return result;
		}
    	
		protected void onProgressUpdate(String... progress) {
			if(progress.length == 0)
				return;
		}
		
		protected void onPostExecute(Map<String, Object> result) {		
			Main.this.readGameDataTaskFinished();
		}
    }
    
    class ReadHeadersTask extends AsyncTask<String, String, List<ExtendedFileHeader>> {

		@Override
		protected List<ExtendedFileHeader> doInBackground(String... params) {
			String basePath = params[0];
			List<ExtendedFileHeader> result = new LinkedList<ExtendedFileHeader>();
			
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
		        	
		        	result.add(fh);
		        	publishProgress(element);
	        	} catch(Exception e) {
	        		
	        	}
	        	
	        } //for
			
			return result;
		}
    	
		protected void onProgressUpdate(String... progress) {
			if(progress.length == 0)
				return;
			
			if(Main.this.mProgressDialog != null) {
				String msg = Main.this.getString(R.string.msg_dialog_search_save_fmt, 
						progress[0]);
				Main.this.mProgressDialog.setMessage(msg);
			}
		}
		
		protected void onPostExecute(List<ExtendedFileHeader> result) {
			ObjectAdapter<ExtendedFileHeader> adapter = 
				new ObjectAdapter<ExtendedFileHeader>(Main.this.getLayoutInflater(), 
	        		result, 
	        		R.layout.item_savefile, 
	        		new SaveInfoMapper());
			
			ListView listView = (ListView)Main.this.findViewById(R.id.listSaves);
			listView.setAdapter(adapter);
			
			Main.this.readHeadersTaskFinished();
		}
    }
}
