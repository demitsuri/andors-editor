package com.litecoding.andorstrail.res;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;

import com.litecoding.andorstrail.res.entity.DataEntity;
import com.litecoding.andorstrail.res.entity.DataEntityFactory;
import com.litecoding.andorstrail.res.entity.ItemCategoryData;
import com.litecoding.andorstrail.res.entity.ItemData;
import com.litecoding.andorstrail.res.parser.Parser;
import com.litecoding.andorstrail.res.parser.ParserException;
import com.litecoding.andorstrail.res.parser.Rule;

public class ExtRes {
	private static final String PACKAGE = "com.gpl.rpg.AndorsTrail";
	
	private static Context atContext;
	private static Resources atResources;
	
	private static List<ItemCategoryData> itemCategories = null;
	private static List<ItemData> items = null;
	
	public static boolean init(Context appContext) {
		if(appContext == null) {
			return false;
		}
		
		try {
			atContext = appContext.createPackageContext(PACKAGE, Context.CONTEXT_IGNORE_SECURITY);
	    	atResources = atContext.getResources();
		} catch(Exception e) {
			return false;
		}
		
		return true;
	}
	
	public static boolean isDataLoaded() {
		if(itemCategories == null || 
			items == null) {
			return false;
		}
		
		return true;
	}
	
	public static List<ItemCategoryData> getItemCategories() {
		return itemCategories;
	}
	
	public static List<ItemData> getItems() {
		return items;
	}
	
	public static boolean loadItemCategories() {
		int resId = getIdentifier("loadresource_itemcategories", "array");
		if(resId == 0) {
			return false;
		}
		
		boolean result = true;
		
		List<ItemCategoryData> itemCats = new ArrayList<ItemCategoryData>();
		
		TypedArray arr = atResources.obtainTypedArray(resId);
		for(int i = 0; i < arr.length(); i++) {
			@SuppressWarnings("unchecked")
			List<ItemCategoryData> list = 
					(List<ItemCategoryData>)parseData(arr.getString(i), 
							DataEntityFactory.TYPE_ITEM_CATEGORY);
			if(list == null) {
				result = false;
				break;
			}
			
			itemCats.addAll(list);
		}
		arr.recycle();
		
		if(result) {
			itemCategories = Collections.unmodifiableList(itemCats);
		} else {
			itemCategories = null;
		}
		
		return result;
	}
	
	public static boolean loadItems() {
		int resId = getIdentifier("loadresource_items", "array");
		if(resId == 0) {
			return false;
		}
		
		boolean result = true;
		
		List<ItemData> tmpItems = new ArrayList<ItemData>();
		
		TypedArray arr = atResources.obtainTypedArray(resId);
		for(int i = 0; i < arr.length(); i++) {
			System.err.println(bytesToHex(arr.getString(i).getBytes()));
			@SuppressWarnings("unchecked")
			List<ItemData> list = 
					(List<ItemData>)parseData(arr.getString(i), 
							DataEntityFactory.TYPE_ITEM);
			if(list == null) {
				result = false;
				break;
			}
			
			tmpItems.addAll(list);
		}
		arr.recycle();
		
		if(result) {
			items = Collections.unmodifiableList(tmpItems);
		} else {
			items = null;
		}
		
		return result;
	}
	
	private static int getIdentifier(String name, String scope) {
		return atResources.getIdentifier(name, scope, PACKAGE);
	}
	
	@SuppressWarnings("unchecked")
	private static List<? extends DataEntity> parseData(String data, int resType) {
		System.err.println(data);
		List<DataEntity> entities = null;
		try {
			Rule base = Parser.parse("resource", data);
			entities = (List<DataEntity>)base.accept(new ResourceVisitor(resType));
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return entities;
	}
	
	public static String bytesToHex(byte[] bytes) {
	    final char[] hexArray = {'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'};
	    char[] hexChars = new char[bytes.length * 2];
	    int v;
	    for ( int j = 0; j < bytes.length; j++ ) {
	        v = bytes[j] & 0xFF;
	        hexChars[j * 2] = hexArray[v >>> 4];
	        hexChars[j * 2 + 1] = hexArray[v & 0x0F];
	    }
	    return new String(hexChars);
	}
}
