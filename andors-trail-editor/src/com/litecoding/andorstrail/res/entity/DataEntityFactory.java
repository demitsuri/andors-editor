package com.litecoding.andorstrail.res.entity;

import java.util.List;

public class DataEntityFactory {
	public static final int TYPE_UNKNOWN = 0;
	public static final int TYPE_ITEM_CATEGORY = 1;
	public static final int TYPE_ITEM = 2;
	
	public static DataEntity create(int type, 
			List<Object> scheme, 
			List<Object> data) {
		switch(type) {
		case TYPE_ITEM_CATEGORY: {
			return createItemCategory(scheme, data);
		}
		case TYPE_ITEM: {
			return createItem(scheme, data);
		}
		default: {
			return null;
		}
		}
	}

	private static DataEntity createItem(List<Object> scheme, List<Object> data) {
		return new ItemData(scheme, data);
	}

	private static DataEntity createItemCategory(List<Object> scheme, List<Object> data) {
		return new ItemCategoryData(scheme, data);
	}

}
