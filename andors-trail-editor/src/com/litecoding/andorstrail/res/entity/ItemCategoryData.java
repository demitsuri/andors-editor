package com.litecoding.andorstrail.res.entity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ItemCategoryData extends DataEntity {
	public static final int ACTIONTYPE_NONE  = 0;
	public static final int ACTIONTYPE_USE   = 1;
	public static final int ACTIONTYPE_EQUIP = 2;
	
	public static final int SIZE_NONE  = 0;
	public static final int SIZE_LIGHT = 1;
	public static final int SIZE_STD   = 2;
	public static final int SIZE_LARGE = 3;
	
	public final String id;
	public final String name;
	public final int actionType;
	public final int inventorySlot;
	public final int size;
	
	private static final List<Object> SCHEME_0_6_12;
	static {
		/*
		 * Repository contains resource scheme for v0.6.12, but released version doesn't.
		 * This code emulates scheme parsing for v0.6.12.
		 */
		List<Object> scheme = new ArrayList<Object>();
		
		scheme.add("id");
		scheme.add("name");
		scheme.add("actionType");
		scheme.add("inventorySlot");
		scheme.add("size");
		
		SCHEME_0_6_12 = Collections.unmodifiableList(scheme);
	}

	public ItemCategoryData(List<Object> scheme, List<Object> data) {
		super(scheme == null ? SCHEME_0_6_12 : scheme, data);
		
		int schemeLen = mScheme.size();
		Map<String, Object> mapping = new HashMap<String, Object>();
		
		for(int i = 0; i < schemeLen; i++ ) {
			String key = (String)scheme.get(i);
			Object value = data.get(i);
			mapping.put(key, value);
		}
		
		id = (String)mapping.get("id");
		name = (String)mapping.get("name");
		actionType = getIntValue(mapping, "actionType", ACTIONTYPE_NONE);
		inventorySlot = getIntValue(mapping, "inventorySlot", -1);
		size = getIntValue(mapping, "size", SIZE_NONE);
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		
		builder.append("(id: ");
		builder.append(id);
		builder.append(", name: ");
		builder.append(name);
		builder.append(", actionType: ");
		builder.append(actionType);
		builder.append(", inventorySlot: ");
		builder.append(inventorySlot);
		builder.append(", size:");
		builder.append(size);
		builder.append(")");
		
		return builder.toString();
	}

}
