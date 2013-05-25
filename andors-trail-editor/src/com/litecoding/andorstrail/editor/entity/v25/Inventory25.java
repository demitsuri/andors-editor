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
import com.litecoding.andorstrail.editor.entity.EntityFactory;
import com.litecoding.andorstrail.editor.entity.annotation.Version;
import com.litecoding.andorstrail.editor.entity.common.Inventory;

@Version(ver = 25)
public class Inventory25 extends Inventory {
	
	public Inventory25(int version, DataInputStream dis) throws IOException {
		super(version, dis);
		mItemContainer = EntityFactory.createItemContainer(version, dis);
		
		Log.d(Main.TAG, "Parsing Inventory");
		
		mGold = dis.readInt();
		Log.d(Main.TAG, "mGold: " + mGold);
		
		int cnt = dis.readInt();
		Log.d(Main.TAG, "Weared items found: " + cnt);
		for(int i = 0; i < cnt; i++) {
			if(dis.readBoolean()) {
				String wear = dis.readUTF();
				mWear.add(wear);
				Log.d(Main.TAG, "wear: " + wear);
			} else {
				mWear.add(null);
				Log.d(Main.TAG, "wear: none");
			}
		}
		
		cnt = dis.readInt();
		Log.d(Main.TAG, "Quick slots found: " + cnt);
		for(int i = 0; i < cnt; i++) {
			if(dis.readBoolean()) {
				String item = dis.readUTF();
				mQuickSlot.add(item);
				Log.d(Main.TAG, "quick slot item: " + item);
			} else {
				mQuickSlot.add(null);
				Log.d(Main.TAG, "quick slot item: none");
			}
		}
		Log.d(Main.TAG, "_");
	}
	
	public void writeToStream(DataOutputStream dos) throws IOException {
		mItemContainer.writeToStream(dos);
		dos.writeInt(mGold);
		
		dos.writeInt(mWear.size());
		for(String wear : mWear) {
			if(wear == null)
				dos.writeBoolean(false);
			else {
				dos.writeBoolean(true);
				dos.writeUTF(wear);
			}
		}
		
		dos.writeInt(mQuickSlot.size());
		for(String item : mQuickSlot) {
			if(item == null)
				dos.writeBoolean(false);
			else {
				dos.writeBoolean(true);
				dos.writeUTF(item);
			}
		}
	}
}
