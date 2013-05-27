package com.litecoding.andorstrail.editor.entity.v33;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.util.LinkedList;
import java.util.List;

import com.litecoding.andorstrail.editor.entity.RewindIsNotSupportedException;

public class SpawnArea extends SaveEntity {
	public List<Monster> mMonsters = new LinkedList<Monster>();

	@Override
	public boolean read(DataInputStream dis, boolean rewindAfterRead) {
		if(rewindAfterRead) {
			mSavedException = new RewindIsNotSupportedException();
			return false;
		}
		
		int monsterCnt = 0;
		
		try {
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
		try {
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
