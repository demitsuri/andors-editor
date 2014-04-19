package com.litecoding.andorstrail.editor.entity.v42;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.util.LinkedList;
import java.util.List;

import com.litecoding.andorstrail.editor.entity.RewindIsNotSupportedException;

/* Related to com.gpl.rpg.AndorsTrail.model.map.PredefinedMap */
public class Place extends SaveEntity {
	public String mName;
	public boolean mShouldLoadMapData;
	public List<SpawnArea> mSpawnAreas = new LinkedList<SpawnArea>();
	public List<Loot> mLoot = new LinkedList<Loot>();
	public boolean mVisited;
	public long mLastVisitTime;
	public String mLastScreenLayoutHash;

	@Override
	public boolean read(DataInputStream dis, boolean rewindAfterRead) {
		//matches: version code 42
		if(rewindAfterRead) {
			mSavedException = new RewindIsNotSupportedException();
			return false;
		}
		
		int spawnAreasCnt = 0; 
		
		try {
			mShouldLoadMapData = dis.readBoolean();
		} catch(Exception e) {
			mSavedException = e;
			return false;
		}
		
		if(mShouldLoadMapData) {
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
				mLastVisitTime = dis.readLong();
			} catch(Exception e) {
				mSavedException = e;
				return false;
			}
		}
		
		try {
			mVisited = dis.readBoolean();
			mLastScreenLayoutHash = dis.readUTF();
		} catch(Exception e) {
			mSavedException = e;
			return false;
		}
		
		return true;
	}

	@Override
	public boolean write(DataOutputStream dos) {
		try {
			dos.writeBoolean(mShouldLoadMapData);
			
			if(mShouldLoadMapData) {
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
				
				dos.writeLong(mLastVisitTime);
			}
			
			dos.writeBoolean(mVisited);
			dos.writeUTF(mLastScreenLayoutHash);
		} catch(Exception e) {
			mSavedException = e;
			return false;
		}
		return true;
	}

}
