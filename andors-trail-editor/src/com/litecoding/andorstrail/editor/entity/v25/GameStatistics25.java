//GPL v2
/*
 * 
 */
package com.litecoding.andorstrail.editor.entity.v25;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import com.litecoding.andorstrail.editor.entity.annotation.Version;
import com.litecoding.andorstrail.editor.entity.common.GameStatistics;

@Version(ver = 25)
public class GameStatistics25 extends GameStatistics {
	
	public GameStatistics25(int version, DataInputStream dis) throws IOException {
		super(version, dis);
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
	}
	
	public void writeToStream(DataOutputStream dos) throws IOException {
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
	}
}
