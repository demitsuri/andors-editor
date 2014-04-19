package com.litecoding.andorstrail.editor.entity.v42;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.util.HashMap;
import java.util.Map;

import com.litecoding.andorstrail.editor.entity.RewindIsNotSupportedException;

/* Related to com.gpl.rpg.AndorsTrail.model.GameStatistics */
public class GameStatistics extends SaveEntity {
	public int mDeaths;
	public Map<String, Integer> mKilledMonsters = new HashMap<String, Integer>();
	public Map<String, Integer> mUsedItems = new HashMap<String, Integer>();
	public int mSpentGold;

	@Override
	public boolean read(DataInputStream dis, boolean rewindAfterRead) {
		//matches: version code 42
		if(rewindAfterRead) {
			mSavedException = new RewindIsNotSupportedException();
			return false;
		}
		
		try {
			mDeaths = dis.readInt();
			
			int cnt = dis.readInt();
			for(int i = 0; i < cnt; i++) {
				String monsterName = dis.readUTF();
				int monsterCount = dis.readInt();
				mKilledMonsters.put(monsterName, monsterCount);
			}
			
			cnt = dis.readInt();
			for(int i = 0; i < cnt; i++) {
				String itemName = dis.readUTF();
				int itemCount = dis.readInt();
				mUsedItems.put(itemName, itemCount);
			}
			
			mSpentGold = dis.readInt();
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
			dos.writeInt(mDeaths);
			
			dos.writeInt(mKilledMonsters.size());
			for(String key : mKilledMonsters.keySet()) {
				dos.writeUTF(key);
				dos.writeInt(mKilledMonsters.get(key));
			}
			
			dos.writeInt(mUsedItems.size());
			for(String key : mUsedItems.keySet()) {
				dos.writeUTF(key);
				dos.writeInt(mUsedItems.get(key));
			}
	
			dos.writeInt(mSpentGold);
		} catch(Exception e) {
			mSavedException = e;
			return false;
		}
		
		return true;
	}

}
