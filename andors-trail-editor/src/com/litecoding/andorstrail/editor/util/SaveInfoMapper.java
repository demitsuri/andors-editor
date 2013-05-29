package com.litecoding.andorstrail.editor.util;

import org.holoeverywhere.widget.TextView;

import android.view.View;
import android.widget.ImageView;

import com.litecoding.andorstrail.editor.R;
import com.litecoding.classkit.view.ObjectAdapter.ObjectMapper;

public class SaveInfoMapper implements ObjectMapper<ExtendedFileHeader> {

	@Override
	public void mapData(int pos, View view, ExtendedFileHeader data) {
		ImageView imgStatus = (ImageView)view.findViewById(R.id.status);
		TextView labelName = (TextView)view.findViewById(R.id.labelName);
    	TextView labelSummary = (TextView)view.findViewById(R.id.labelSummary);
		
    	int statusResId = 0;
		if(data.mVer < 33) {
			statusResId = R.drawable.temporarily_not_available;
		} else if(data.mVer > 33) {
			statusResId = R.drawable.not_available;
		} else {
			statusResId = R.drawable.available;
		}
		
		if(imgStatus != null) {
			imgStatus.setImageResource(statusResId);
		}
		
		if(labelName != null) {
			labelName.setText(data.mName);
		}
		
		if(labelSummary != null) {
			labelSummary.setText(data.mSummary);
		}
	}

}
