//GPL v2 or later
/*
 * 
 */
package com.litecoding.andorstrail.editor;

import java.io.File;
import java.io.FilenameFilter;
import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.litecoding.andorstrail.editor.entity.SaveFileUtils;
import com.litecoding.andorstrail.editor.entity.common.ItemContainer.Item;
import com.litecoding.andorstrail.editor.entity.common.SaveFile;
import com.litecoding.andorstrail.editor.widget.NumberPicker;
import com.litecoding.classkit.view.ObjectAdapter;
import com.litecoding.classkit.view.ObjectAdapter.ObjectMapper;

public class Editor extends Activity {
	private String mSrcFilePath;
	
	private SaveFile mSave;
	
	private class InventoryMapper implements ObjectMapper<Item> {

		public void mapData(int position, View view, Item item) {
			TextView label = (TextView)view.findViewById(R.id.labelName);
        	label.setText(item.mItemTypeId);
        	label = (TextView)view.findViewById(R.id.labelQuantity);
        	label.setText(getResources().getString(R.string.label_item_quantity_fmt, item.mQuantity));
		}
		
	}
	
	
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
        
        //TODO: remove this from UI thread
        try {
        	mSrcFilePath = getIntent().getStringExtra(Main.EXTRA_FILEPATH);
        	mSave = SaveFileUtils.load(mSrcFilePath);
        } catch(Exception e) {
        	Log.e(Main.TAG, "Parsing error", e);
        	finish();
        	Toast.makeText(this, R.string.msg_err_unexpected, Toast.LENGTH_LONG).show();
        	return;
        }
        
        GridView itemGrid = (GridView)findViewById(R.id.gridItems);
        
        List<Item> items = mSave.mModelContainer.mPlayer.mInventory.mItemContainer.mItems;
        final ObjectAdapter<Item> adapter = new ObjectAdapter<Item>(getLayoutInflater(), 
        		items, 
        		R.layout.item, 
        		new InventoryMapper());
        
        itemGrid.setAdapter(adapter);
        
        itemGrid.setOnItemClickListener(new OnItemClickListener() {

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
						adapter.notifyDataSetChanged();
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
        
    }
    
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

}
