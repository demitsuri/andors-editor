//GPL v2
/*
 * 
 */
package com.litecoding.andorstrail.editor.entity.common;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public abstract class ItemContainer {
	public List<Item> mItems = new LinkedList<Item>();
	
	public abstract class Item {
		public String mItemTypeId;
		public int mQuantity;
		
		public Item(DataInputStream dis) throws IOException {
			
		}
		
		public abstract void writeToStream(DataOutputStream dos) throws IOException;
	}
	
	public ItemContainer(int version, DataInputStream dis) throws IOException {
		
	}
	
	public abstract void writeToStream(DataOutputStream dos) throws IOException;

}
