package com.litecoding.andorstrail.res.entity;

import java.util.List;
import java.util.Map;

public abstract class DataEntity {
	protected List<Object> mScheme;
	protected List<Object> mData;
	
	public DataEntity(List<Object> scheme, List<Object> data) {
		this.mScheme = scheme;
		this.mData = data;
	}
	
	public List<Object> getData() {
		return mData;
	}
	
	public List<Object> getScheme() {
		return mScheme;
	}
	
	protected int getIntValue(Map<String, Object> map, String key, int defValue) {
		Object intVal = map.get(key);
		if(intVal == null || !(intVal instanceof Integer)) {
			return defValue;
		}
		
		return ((Integer)intVal).intValue();
	}
	
	public static final class NamedSchemePart {
		public final String name;
		public final List<Object> subScheme;
		
		public NamedSchemePart(String aName, List<Object> aSubScheme) {
			name = aName;
			subScheme = aSubScheme;
		}
	}
	
}
