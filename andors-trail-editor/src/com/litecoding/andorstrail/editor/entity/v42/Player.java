package com.litecoding.andorstrail.editor.entity.v42;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.litecoding.andorstrail.editor.entity.RewindIsNotSupportedException;

/* Related to com.gpl.rpg.AndorsTrail.model.actor.Player */
public class Player extends SaveEntity {
	public int mIconID;
	public int mBaseMaxAP;
	public int mBaseMaxHP;
	public String mName;
	public int mBaseMoveCost;
	public int mBaseAttackCost;
	public int mBaseAttackChance;
	public int mBaseCriticalSkill;
	public float mBaseCriticalMultiplier;
	public Range mBaseDamagePotential;
	public int mBaseBlockChance;
	public int mBaseDamageResistance;
	public int mBaseUseItemCost;
	public int mBaseReequipCost;
	
	public Range mAP;
	public Range mHP;
	public Coord mPosition;
	public List<ActorCondition> mActorConditions = new LinkedList<ActorCondition>();
	
	public Coord mLastPosition;
	public Coord mNextPosition;
	public int mLevel;
	public int mTotalExperience;
	public Inventory mInventory;
	public int mUseItemCost;
	public int mReEquipCost;
	public List<SkillInfo> mSkills = new LinkedList<SkillInfo>();
	public String mSpawnMap;
	public String mSpawnPlace;
	public List<QuestInfo> mQuests = new LinkedList<QuestInfo>();
	public int mAvailableSkillIncreases;
	public Map<String, Integer> mAlignments = new HashMap<String, Integer>();
	
	public class SkillInfo extends SaveEntity {
		public int mSkillId;
		public int mSkillLevel;		

		@Override
		public boolean read(DataInputStream dis, boolean rewindAfterRead) {
			//matches: version code 42
			if(rewindAfterRead && dis.markSupported()) {
				dis.mark(8);
			}
			
			try {
				mSkillId = dis.readInt();
				mSkillLevel = dis.readInt();
			} catch(Exception e) {
				mSavedException = e;
				return false;
			}
			
			if(rewindAfterRead && dis.markSupported()) {
				try {
					dis.reset();
				} catch(Exception e) {
					mSavedException = e;
					return false;
				}
			}
			
			return true;
		}

		@Override
		public boolean write(DataOutputStream dos) {
			//matches: version code 42
			try {
				dos.writeInt(mSkillId);
				dos.writeInt(mSkillLevel);
			} catch(Exception e) {
				mSavedException = e;
				return false;
			}
			
			return true;
		}
	}
	
	public class QuestInfo extends SaveEntity {
		public String mQuestId;
		public Set<Integer> mProgress = new HashSet<Integer>();

		@Override
		public boolean read(DataInputStream dis, boolean rewindAfterRead) {
			//matches: version code 42
			if(rewindAfterRead) {
				mSavedException = new RewindIsNotSupportedException();
				return false;
			}
			
			try {
				mQuestId = dis.readUTF();
				
				int cnt = dis.readInt();
				for(int i = 0; i < cnt; i++) {
					mProgress.add(dis.readInt());
				}
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
				dos.writeUTF(mQuestId);
				
				dos.writeInt(mProgress.size());
				for(Integer progress : mProgress) {
					dos.writeInt(progress);
				}
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
		if(rewindAfterRead) {
			mSavedException = new RewindIsNotSupportedException();
			return false;
		}
		
		try {
			//Actor fields
			mIconID = dis.readInt();
			mBaseMaxAP = dis.readInt();
			mBaseMaxHP = dis.readInt();
			mName = dis.readUTF();
			mBaseMoveCost = dis.readInt();
			mBaseAttackCost = dis.readInt();
			mBaseAttackChance = dis.readInt();
			mBaseCriticalSkill = dis.readInt();
			mBaseCriticalMultiplier = dis.readFloat();
			
			mBaseDamagePotential = new Range();
			if(!mBaseDamagePotential.read(dis)) {
				mSavedException = mBaseDamagePotential.getLastException();
				return false;
			}
			
			mBaseBlockChance = dis.readInt();
			mBaseDamageResistance = dis.readInt();
			mBaseMoveCost = dis.readInt();
			
			mAP = new Range();
			if(!mAP.read(dis)) {
				mSavedException = mAP.getLastException();
				return false;
			}
			
			mHP = new Range();
			if(!mHP.read(dis)) {
				mSavedException = mHP.getLastException();
				return false;
			}
			
			mPosition = new Coord();
			if(!mPosition.read(dis)) {
				mSavedException = mPosition.getLastException();
				return false;
			}
			
			int cnt = dis.readInt();
			for(int i = 0; i < cnt; i++) {
				ActorCondition actorCond = new ActorCondition();
				if(!actorCond.read(dis)) {
					mSavedException = actorCond.getLastException();
					return false;
				}
				
				mActorConditions.add(actorCond);
			}
			
			mLastPosition = new Coord();
			if(!mLastPosition.read(dis)) {
				mSavedException = mLastPosition.getLastException();
				return false;
			}
			
			mNextPosition = new Coord();
			if(!mNextPosition.read(dis)) {
				mSavedException = mNextPosition.getLastException();
				return false;
			}
			
			mLevel = dis.readInt();
			
			mTotalExperience = dis.readInt();
			
			mInventory = new Inventory();
			if(!mInventory.read(dis)) {
				mSavedException = mInventory.getLastException();
				return false;
			}
			
			mUseItemCost = dis.readInt();
			mReEquipCost = dis.readInt();
			
			cnt = dis.readInt();
			for(int i = 0; i < cnt; i++) {
				SkillInfo skill = new SkillInfo();
				if(!skill.read(dis)) {
					mSavedException = skill.getLastException();
					return false;
				}
				
				mSkills.add(skill);
			}
			
			mSpawnMap = dis.readUTF();
			mSpawnPlace = dis.readUTF();
			
			cnt = dis.readInt();
			for(int i = 0; i < cnt; i++) {
				QuestInfo quest = new QuestInfo();
				if(!quest.read(dis)) {
					mSavedException = quest.getLastException();
					return false;
				}
				
				mQuests.add(quest);
			}
			
			mAvailableSkillIncreases = dis.readInt();
			
			cnt = dis.readInt();
			for(int i = 0; i < cnt; i++) {
				String faction = dis.readUTF();
				int alignment = dis.readInt();
				mAlignments.put(faction, alignment);
			}
		} catch (Exception e) {
			mSavedException = e;
			return false;
		}
		
		return true;
	}

	@Override
	public boolean write(DataOutputStream dos) {
		//matches: version code 42
		try {
			dos.writeInt(mIconID);
			dos.writeInt(mBaseMaxAP);
			dos.writeInt(mBaseMaxHP);
			dos.writeUTF(mName);
			dos.writeInt(mBaseMoveCost);
			dos.writeInt(mBaseAttackCost);
			dos.writeInt(mBaseAttackChance);
			dos.writeInt(mBaseCriticalSkill);
			dos.writeFloat(mBaseCriticalMultiplier);
			
			if(!mBaseDamagePotential.write(dos)) {
				mSavedException = mBaseDamagePotential.getLastException();
				return false;
			}
			
			dos.writeInt(mBaseBlockChance);
			dos.writeInt(mBaseDamageResistance);
			dos.writeInt(mBaseMoveCost);
			
			if(!mAP.write(dos)) {
				mSavedException = mAP.getLastException();
				return false;
			}
			if(!mHP.write(dos)) {
				mSavedException = mHP.getLastException();
				return false;
			}
			if(!mPosition.write(dos)) {
				mSavedException = mPosition.getLastException();
				return false;
			}
			
			dos.writeInt(mActorConditions.size());
			for(ActorCondition actorCondition : mActorConditions) {
				if(!actorCondition.write(dos)) {
					mSavedException = actorCondition.getLastException();
					return false;
				}
			}

			if(!mLastPosition.write(dos)) {
				mSavedException = mLastPosition.getLastException();
				return false;
			}
			
			if(!mNextPosition.write(dos)) {
				mSavedException = mNextPosition.getLastException();
				return false;
			}
			
			dos.writeInt(mLevel);
			dos.writeInt(mTotalExperience);
			
			if(!mInventory.write(dos)) {
				mSavedException = mInventory.getLastException();
				return false;
			}
			
			dos.writeInt(mUseItemCost);
			dos.writeInt(mReEquipCost);
			
			dos.writeInt(mSkills.size());
			for(SkillInfo skill : mSkills) {
				if(!skill.write(dos)) {
					mSavedException = skill.getLastException();
					return false;
				}
			}
			
			dos.writeUTF(mSpawnMap);
			dos.writeUTF(mSpawnPlace);
			
			dos.writeInt(mQuests.size());
			for(QuestInfo quest : mQuests) {
				if(!quest.write(dos)) {
					mSavedException = quest.getLastException();
					return false;
				}
			}
			
			dos.writeInt(mAvailableSkillIncreases);
			
			dos.writeInt(mAlignments.keySet().size());
			for(String key: mAlignments.keySet()) {
				dos.writeUTF(key);
				dos.writeInt(mAlignments.get(key));
			}
		} catch (Exception e) {
			mSavedException = e;
			return false;
		}
		
		return true;
	}

}
