package com.litecoding.andorstrail.editor.util;

import org.holoeverywhere.widget.TextView;

import android.view.View;

import com.litecoding.andorstrail.editor.R;
import com.litecoding.andorstrail.editor.entity.v33.ItemContainer.Item;
import com.litecoding.classkit.view.ObjectAdapter.ObjectMapper;

public class ItemInfoMapper implements ObjectMapper<Item> {

	@Override
	public void mapData(int pos, View view,Item data) {
		TextView labelName = (TextView)view.findViewById(R.id.labelName);
    	TextView labelQuantity = (TextView)view.findViewById(R.id.labelQuantity);
		
		if(labelName != null) {
			labelName.setText(data.mItemTypeId);
		}
		
		if(labelQuantity != null) {
			labelQuantity.setText(String.valueOf(data.mQuantity));
		}
	}

}
