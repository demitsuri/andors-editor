package com.litecoding.andorstrail.editor;

import android.content.Context;
import android.content.res.Resources;

public class ExtRes {
	private static final String PACKAGE = "com.gpl.rpg.AndorsTrail";
	
	private static Context atContext;
	private static Resources atResources;
	
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
	
	public static String getString(String name) {
		int id = atResources.getIdentifier(name, "string", PACKAGE);
		return atResources.getString(id);
	}
}
