//GPL v2
/*
 * 
 */
package com.litecoding.andorstrail.editor.entity.common;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public abstract class Inventory {
	public ItemContainer mItemContainer;
	public int mGold;
	public List<String> mWear = new ArrayList<String>();
	public List<String> mQuickSlot = new ArrayList<String>();
	
	public Inventory(int version, DataInputStream dis) throws IOException {
		
	}
	
	public abstract void writeToStream(DataOutputStream dos) throws IOException;
}
