//GPL v2
/*
 * 
 */
package com.litecoding.andorstrail.editor.entity.v25;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import android.util.Log;

import com.litecoding.andorstrail.editor.Main;
import com.litecoding.andorstrail.editor.entity.EntityFactory;
import com.litecoding.andorstrail.editor.entity.annotation.Version;
import com.litecoding.andorstrail.editor.entity.common.Coord;
import com.litecoding.andorstrail.editor.entity.common.Player;

@Version(ver = 25)
public class Player25 extends Player {
	
	public class SkillInfo25 extends Player.SkillInfo {
		
		public SkillInfo25(DataInputStream dis) throws IOException {
			super(dis);
			mSkillId = dis.readInt();
			mSkillLevel = dis.readInt();
		}
		
		public void writeToStream(DataOutputStream dos) throws IOException {
			dos.writeInt(mSkillId);
			dos.writeInt(mSkillLevel);
		}
	}
	
	public class QuestInfo25 extends Player.QuestInfo{
		public String mQuestId;
		public Set<Integer> mProgress = new HashSet<Integer>();
		
		public QuestInfo25(DataInputStream dis) throws IOException {
			super(dis);
			mQuestId = dis.readUTF();
			
			int cnt = dis.readInt();
			for(int i = 0; i < cnt; i++) {
				mProgress.add(dis.readInt());
			}
		}
		
		public void writeToStream(DataOutputStream dos) throws IOException {
			dos.writeUTF(mQuestId);
			
			dos.writeInt(mProgress.size());
			for(Integer progress : mProgress) {
				dos.writeInt(progress);
			}
		}
	}
	
	public Player25(int version, DataInputStream dis) throws IOException {
		super(version, dis);
		Log.d(Main.TAG, "Parsing Player");
		mActorInfo = EntityFactory.createActor(version, dis, true);
		mActorInfo.mIsPlayer = true;
		mLastPosition = new Coord(dis);
		Log.d(Main.TAG, "mLastPosition: x=" + mLastPosition.mX + " y=" + mLastPosition.mY);
		mNextPosition = new Coord(dis);
		Log.d(Main.TAG, "mNextPosition: x=" + mNextPosition.mX + " y=" + mNextPosition.mY);
		mLevel = dis.readInt();
		Log.d(Main.TAG, "mLevel: " + mLevel);
		mTotalExperience = dis.readInt();
		Log.d(Main.TAG, "mTotalExperience: " + mTotalExperience);
		mInventory = EntityFactory.createInventory(version, dis);
		mUseItemCost = dis.readInt();
		mReEquipCost = dis.readInt();
		
		int cnt = dis.readInt();
		for(int i = 0; i < cnt; i++) {
			mSkills.add(new SkillInfo25(dis));
		}
		
		mSpawnMap = dis.readUTF();
		mSpawnPlace = dis.readUTF();
		
		cnt = dis.readInt();
		for(int i = 0; i < cnt; i++) {
			mQuests.add(new QuestInfo25(dis));
		}
		
		mAvailableSkillIncreases = dis.readInt();
		Log.d(Main.TAG, "_");
	}
	
	public void writeToStream(DataOutputStream dos) throws IOException {
		mActorInfo.writeActorData(dos);
		mLastPosition.writeToStream(dos);
		mNextPosition.writeToStream(dos);
		dos.writeInt(mLevel);
		dos.writeInt(mTotalExperience);
		mInventory.writeToStream(dos);
		dos.writeInt(mUseItemCost);
		dos.writeInt(mReEquipCost);
		
		dos.writeInt(mSkills.size());
		for(SkillInfo skill : mSkills) {
			skill.writeToStream(dos);
		}
		
		dos.writeUTF(mSpawnMap);
		dos.writeUTF(mSpawnPlace);
		
		dos.writeInt(mQuests.size());
		for(QuestInfo quest : mQuests) {
			quest.writeToStream(dos);
		}
		
		dos.writeInt(mAvailableSkillIncreases);
	}
}
