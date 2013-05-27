package com.litecoding.andorstrail.editor.entity.v33;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.util.LinkedList;
import java.util.List;

public class Place extends SaveEntity {
	public List<SpawnArea> mSpawnAreas = new LinkedList<SpawnArea>();
	public List<Loot> mLoot = new LinkedList<Loot>();
	public boolean mVisited;
	public long mLastVisitTime;
	public int mLastVisitVersion = 30;

	@Override
	public boolean read(DataInputStream dis, boolean rewindAfterRead) {
		if(rewindAfterRead == false) {
			return false;
		}
		
		int spawnAreasCnt = 0; 
		
		try {
			spawnAreasCnt = dis.readInt();
		} catch(Exception e) {
			mSavedException = e;
			return false;
		}
		
		for(int i = 0; i < spawnAreasCnt; i++) {
			SpawnArea spawnArea = new SpawnArea();
			boolean result = spawnArea.read(dis);
			if(!result) {
				mSavedException = spawnArea.getLastException();
				return false;
			}
			
			mSpawnAreas.add(spawnArea);
		}
		
		int lootCnt = 0;
		try {
			lootCnt = dis.readInt();
		} catch(Exception e) {
			mSavedException = e;
			return false;
		}
		
		for(int i = 0; i < lootCnt; i++) {
			Loot loot = new Loot();
			boolean result = loot.read(dis);
			if(!result) {
				mSavedException = loot.getLastException();
				return false;
			}
			
			mLoot.add(loot);
		}
		
		try {
			mVisited = dis.readBoolean();
			mLastVisitTime = dis.readLong();
			if(mVisited) {
				mLastVisitVersion = dis.readInt();
			}
		} catch(Exception e) {
			mSavedException = e;
			return false;
		}
		
		return true;
	}

	@Override
	public boolean write(DataOutputStream dos) {
		try {
			dos.writeInt(mSpawnAreas.size());
			for(SpawnArea spawnArea : mSpawnAreas) {
				boolean result = spawnArea.write(dos);
				if(!result) {
					mSavedException = spawnArea.getLastException();
					return false;
				}
			}
			
			dos.writeInt(mLoot.size());
			for(Loot loot : mLoot) {
				boolean result = loot.write(dos);
				if(!result) {
					mSavedException = loot.getLastException();
					return false;
				}
			}
			
			dos.writeBoolean(mVisited);
			dos.writeLong(mLastVisitTime);
			if(mVisited) {
				dos.writeInt(mLastVisitVersion);
			}
		} catch(Exception e) {
			mSavedException = e;
			return false;
		}
		return true;
	}

}
