package com.litecoding.andorstrail.editor.entity.v33;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.util.ArrayList;
import java.util.List;

import com.litecoding.andorstrail.editor.entity.RewindIsNotSupportedException;


public class Inventory extends SaveEntity {
	public ItemContainer mItemContainer;
	public int mGold;
	public List<String> mWear = new ArrayList<String>();
	public List<String> mQuickSlot = new ArrayList<String>();

	@Override
	public boolean read(DataInputStream dis, boolean rewindAfterRead) {
		if(rewindAfterRead) {
			mSavedException = new RewindIsNotSupportedException();
			return false;
		}
		
		mItemContainer = new ItemContainer();
		if(!mItemContainer.read(dis)) {
			mSavedException = mItemContainer.getLastException();
			return false;
		}
		
		try {
			mGold = dis.readInt();
			
			int cnt = dis.readInt();
			for(int i = 0; i < cnt; i++) {
				if(dis.readBoolean()) {
					String wear = dis.readUTF();
					mWear.add(wear);
				} else {
					mWear.add(null);
				}
			}
			
			cnt = dis.readInt();
			for(int i = 0; i < cnt; i++) {
				if(dis.readBoolean()) {
					String item = dis.readUTF();
					mQuickSlot.add(item);
				} else {
					mQuickSlot.add(null);
				}
			}
		} catch (Exception e) {
			mSavedException = e;
			return false;
		}

		return true;
	}

	@Override
	public boolean write(DataOutputStream dos) {
		if(!mItemContainer.write(dos)) {
			mSavedException = mItemContainer.getLastException();
			return false;
		}
		
		try {
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
		} catch (Exception e) {
			mSavedException = e;
			return false;
		}
		
		return true;
	}

}
