package com.litecoding.andorstrail.editor.entity.v42;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.util.LinkedList;
import java.util.List;

import com.litecoding.andorstrail.editor.entity.RewindIsNotSupportedException;

/* Related to com.gpl.rpg.AndorsTrail.model.map.MonsterSpawnArea */
public class SpawnArea extends SaveEntity {
	public boolean mIsSpawned;
	public List<Monster> mMonsters = new LinkedList<Monster>();

	@Override
	public boolean read(DataInputStream dis, boolean rewindAfterRead) {
		//matches: version code 42
		if(rewindAfterRead) {
			mSavedException = new RewindIsNotSupportedException();
			return false;
		}
		
		int monsterCnt = 0;
		
		try {
			mIsSpawned = dis.readBoolean();
			monsterCnt = dis.readInt();
		} catch(Exception e) {
			mSavedException = e;
			return false;
		}
		
		for(int i = 0; i < monsterCnt; i++) {
			Monster monster = new Monster();
			if(!monster.read(dis)) {
				mSavedException = monster.getLastException();
				return false;
			}
			mMonsters.add(monster);
		}
		return true;
	}

	@Override
	public boolean write(DataOutputStream dos) {
		//matches: version code 42
		try {
			dos.writeBoolean(mIsSpawned);
			dos.writeInt(mMonsters.size());
		} catch(Exception e) {
			mSavedException = e;
			return false;
		}
		
		for(Monster monster : mMonsters) {
			if(!monster.write(dos)) {
				mSavedException = monster.getLastException();
				return false;
			}
		}
		return true;
	}

}
