//GPL v2
/*
 * 
 */
package com.litecoding.andorstrail.editor.entity.v25;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import com.litecoding.andorstrail.editor.entity.EntityFactory;
import com.litecoding.andorstrail.editor.entity.annotation.Version;
import com.litecoding.andorstrail.editor.entity.common.Loot;
import com.litecoding.andorstrail.editor.entity.common.Place;
import com.litecoding.andorstrail.editor.entity.common.SpawnArea;

@Version(ver = 25)
public class Place25 extends Place {
	
	public Place25(int version, DataInputStream dis) throws IOException {
		super(version, dis);
		int spawnAreasCnt = dis.readInt();
		for(int i = 0; i < spawnAreasCnt; i++) {
			mSpawnAreas.add(EntityFactory.createSpawnArea(version, dis));
		}
		
		int lootCnt = dis.readInt();
		for(int i = 0; i < lootCnt; i++) {
			mLoot.add(EntityFactory.createLoot(version, dis));
		}
		
		mVisited = dis.readBoolean();
		
		mLastVisitTime = dis.readLong();
	}
	
	public void writeToStream(DataOutputStream dos) throws IOException {
		dos.writeInt(mSpawnAreas.size());
		for(SpawnArea spawnArea : mSpawnAreas) {
			spawnArea.writeToStream(dos);
		}
		
		dos.writeInt(mLoot.size());
		for(Loot loot : mLoot) {
			loot.writeToStream(dos);
		}
		
		dos.writeBoolean(mVisited);
		dos.writeLong(mLastVisitTime);
	}
}
