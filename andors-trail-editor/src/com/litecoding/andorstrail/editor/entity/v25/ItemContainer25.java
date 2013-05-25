//GPL v2
/*
 * 
 */
package com.litecoding.andorstrail.editor.entity.v25;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import android.util.Log;

import com.litecoding.andorstrail.editor.Main;
import com.litecoding.andorstrail.editor.entity.annotation.Version;
import com.litecoding.andorstrail.editor.entity.common.ItemContainer;

@Version(ver = 25)
public class ItemContainer25 extends ItemContainer {
	
	public class Item25 extends ItemContainer.Item {
		
		public Item25(DataInputStream dis) throws IOException {
			super(dis);
			Log.d(Main.TAG, "Parsing Item");
			mItemTypeId = dis.readUTF();
			Log.d(Main.TAG, "mItemTypeId: " + mItemTypeId);
			mQuantity = dis.readInt();
			Log.d(Main.TAG, "mQuantity: " + mQuantity);
			Log.d(Main.TAG, "_");
		}
		
		public void writeToStream(DataOutputStream dos) throws IOException {
			dos.writeUTF(mItemTypeId);
			dos.writeInt(mQuantity);
		}
	}
	
	public ItemContainer25(int version, DataInputStream dis) throws IOException {
		super(version, dis);
		Log.d(Main.TAG, "Parsing ItemContainer");
		int cnt = dis.readInt();
		Log.d(Main.TAG, "Items found: " + cnt);
		for(int i = 0; i < cnt; i++) {
			mItems.add(new Item25(dis));
		}
		Log.d(Main.TAG, "_");
	}
	
	public void writeToStream(DataOutputStream dos) throws IOException {
		dos.writeInt(mItems.size());
		for(Item item : mItems) {
			item.writeToStream(dos);
		}
	}
}
