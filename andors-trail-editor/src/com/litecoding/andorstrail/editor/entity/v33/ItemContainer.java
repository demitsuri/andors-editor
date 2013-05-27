package com.litecoding.andorstrail.editor.entity.v33;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.util.LinkedList;
import java.util.List;

public class ItemContainer extends SaveEntity {
	public List<Item> mItems = new LinkedList<Item>();
	
	public class Item extends SaveEntity {
		public String mItemTypeId;
		public int mQuantity;

		@Override
		public boolean read(DataInputStream dis, boolean rewindAfterRead) {
			if(rewindAfterRead == false) {
				return false;
			}
			
			try {
				mItemTypeId = dis.readUTF();
				mQuantity = dis.readInt();
			} catch(Exception e) {
				mSavedException = e;
				return false;
			}
			
			return true;
		}

		@Override
		public boolean write(DataOutputStream dos) {
			try {
				dos.writeUTF(mItemTypeId);
				dos.writeInt(mQuantity);
			} catch(Exception e) {
				mSavedException = e;
				return false;
			}
			
			return true;
		}
	}

	@Override
	public boolean read(DataInputStream dis, boolean rewindAfterRead) {
		int cnt = 0;
		
		try {
			cnt = dis.readInt();
		} catch(Exception e) {
			mSavedException = e;
			return false;
		}

		for(int i = 0; i < cnt; i++) {
			Item item = new Item();
			if(!item.read(dis)) {
				mSavedException = item.getLastException();
				return false;
			}
			mItems.add(item);
		}

		return true;
	}

	@Override
	public boolean write(DataOutputStream dos) {
		try {
			dos.writeInt(mItems.size());
		} catch(Exception e) {
			mSavedException = e;
			return false;
		}
		
		for(Item item : mItems) {
			if(!item.write(dos)) {
				mSavedException = item.getLastException();
				return false;
			}
		}
		return true;
	}

}
