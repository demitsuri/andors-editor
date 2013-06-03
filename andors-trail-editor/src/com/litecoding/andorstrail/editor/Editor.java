//GPL v2 or later
/*
 * 
 */
package com.litecoding.andorstrail.editor;

import java.util.List;

import org.holoeverywhere.app.Activity;
import org.holoeverywhere.app.ProgressDialog;
import org.holoeverywhere.widget.ListView;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.Toast;

import com.actionbarsherlock.app.ActionBar;
import com.litecoding.andorstrail.editor.entity.v33.ItemContainer.Item;
import com.litecoding.andorstrail.editor.entity.v33.SaveFile;
import com.litecoding.andorstrail.editor.util.ItemInfoMapper;
import com.litecoding.andorstrail.editor.util.SaveFileUtils;
import com.litecoding.andorstrail.editor.widget.NumberPicker;
import com.litecoding.classkit.view.ObjectAdapter;

public class Editor extends Activity {
	private ProgressDialog mProgressDialog;
	
	
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.editor);
        
        if(!getIntent().hasExtra(Main.EXTRA_FILEPATH)) {
        	finish();
        	Toast.makeText(this, R.string.msg_err_unexpected, Toast.LENGTH_LONG).show();
        	return;
        }
        
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(R.string.title_main_inventory);
        
        ListView itemList = (ListView)findViewById(R.id.listInventory);
                
        itemList.setOnItemClickListener(new OnItemClickListener() {

			public void onItemClick(AdapterView<?> adapterView, View view, int position,
					long id) {
				final Item item = (Item)adapterView.getItemAtPosition(position);
				Builder builder = new Builder(Editor.this);
				builder.setTitle(item.mItemTypeId);
				
				NumberPicker picker = new NumberPicker(Editor.this);
				picker.setRange(0, Integer.MAX_VALUE);
				picker.setCurrent(item.mQuantity);
				
				builder.setView(picker);
				builder.setPositiveButton(R.string.label_dialog_btn_positive, 
						new DialogInterface.OnClickListener() {
					
					public void onClick(DialogInterface dialog, int which) {
						EditText editText = (EditText)((AlertDialog)dialog).findViewById(R.id.timepicker_input);
						try {
							item.mQuantity = Integer.parseInt(editText.getText().toString());
						} catch(Exception e) {
							Log.e(Main.TAG, "Quantity parsing error", e);
						}
						//adapter.notifyDataSetChanged();
					}
				});
				builder.setNegativeButton(R.string.label_dialog_btn_negative, 
						new DialogInterface.OnClickListener() {
					
					public void onClick(DialogInterface dialog, int which) {
						dialog.dismiss();	
					}
				});
				
				builder.create().show();
			}
		});
        
        
        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setTitle(R.string.title_dialog_parse_save);
        mProgressDialog.show();
        
        
        ReadSaveTask task = new ReadSaveTask();
        task.execute(getIntent().getStringExtra(Main.EXTRA_FILEPATH));
    }
    
    /*
    @Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.editor_menu, menu);
		return true;
	}
    
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.editor_menu_save: {
			File savePath = new File(mSrcFilePath).getParentFile();
			String[] saveFiles = savePath.list(new FilenameFilter() {
				
				public boolean accept(File dir, String filename) {
					if(filename.startsWith("savegame"))
						return true;
					return false;
				}
			});
			
			int maxSlotNum = 0;
			for(String saveFile : saveFiles) {
				int slotNum = Integer.parseInt(saveFile.substring("savegame".length()));
				if(slotNum > maxSlotNum)
					maxSlotNum = slotNum;
			}
			
			maxSlotNum++;
			String saveFileName = savePath.getAbsolutePath().
					concat(File.separator).
					concat("savegame").
					concat(String.valueOf(maxSlotNum));
			
			try {
				SaveFileUtils.save(mSave, saveFileName);
				String msg = getResources().getString(R.string.msg_info_save_path, maxSlotNum);
				Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
			} catch(Exception e) {
				Log.e(Main.TAG, "Exception while saving", e);
				String newSaveFilePath = 
						Environment.getExternalStorageDirectory().getAbsolutePath().
		        		concat(File.separator).
		        		concat("andors-trail-editor").
		        		concat(File.separator);
				
				File newSaveDir = new File(newSaveFilePath);
				if(!newSaveDir.exists())
					newSaveDir.mkdirs();
				
				String newSaveFileName = newSaveFilePath.
		        		concat("savegame").
						concat(String.valueOf(maxSlotNum));
				try {
					SaveFileUtils.save(mSave, newSaveFileName);
					String msg = getResources().getString(R.string.msg_warn_alt_save_path, newSaveFileName);
					Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
				} catch(Exception ex) {
					Log.e(Main.TAG, "Exception while saving to own dir", ex);
					Toast.makeText(this, R.string.msg_err_cant_write, Toast.LENGTH_LONG).show();
				}
			}
			finish();
			
			return true;
		}
		}
		return super.onOptionsItemSelected(item);
	}
	*/
    
    class ReadSaveTask extends AsyncTask<String, String, SaveFile> {

		@Override
		protected SaveFile doInBackground(String... params) {
			String srcFilePath = params[0];
			SaveFile save = null;
	        try {
	        	save = SaveFileUtils.load(srcFilePath);
	        } catch(Exception e) {
	        	Log.e(Main.TAG, "Parsing error", e);
	        	Toast.makeText(Editor.this, R.string.msg_err_unexpected, Toast.LENGTH_LONG).show();
	        	return null;
	        }
			return save;
		}
    	
		protected void onProgressUpdate(String... progress) {
			if(progress.length == 0)
				return;
			
			if(Editor.this.mProgressDialog != null) {
				String msg = Editor.this.getString(R.string.msg_dialog_search_save_fmt, 
						progress[0]);
				Editor.this.mProgressDialog.setMessage(msg);
			}
		}
		
		protected void onPostExecute(SaveFile result) {
			List<Item> items = result.mModelContainer.mPlayer.mInventory.mItemContainer.mItems;
			ObjectAdapter<Item> adapter = 
				new ObjectAdapter<Item>(Editor.this.getLayoutInflater(), 
	        		items, 
	        		R.layout.item_inventory, 
	        		new ItemInfoMapper());
			
			ListView listView = (ListView)Editor.this.findViewById(R.id.listInventory);
			listView.setAdapter(adapter);
			
			if(Editor.this.mProgressDialog != null) {
				Editor.this.mProgressDialog.dismiss();
			}
		}
    }
}
