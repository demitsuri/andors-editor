package com.litecoding.andorstrail.editor.entity.v42;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.util.LinkedList;
import java.util.List;

import com.litecoding.andorstrail.editor.entity.RewindIsNotSupportedException;

/* Related to com.gpl.rpg.AndorsTrail.model.item.ItemContainer */
public class ItemContainer extends SaveEntity {
	public List<Item> mItems = new LinkedList<Item>();
	
	/* Related to com.gpl.rpg.AndorsTrail.model.item.ItemContainer.ItemEntry */
	public class Item extends SaveEntity {
		public String mItemTypeId;
		public int mQuantity;

		@Override
		public boolean read(DataInputStream dis, boolean rewindAfterRead) {
			//matches: version code 42
			if(rewindAfterRead) {
				mSavedException = new RewindIsNotSupportedException();
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
			//matches: version code 42
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
		//matches: version code 42
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
		//matches: version code 42
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
