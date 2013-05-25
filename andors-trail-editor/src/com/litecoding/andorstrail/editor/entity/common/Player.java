//GPL v2
/*
 * 
 */
package com.litecoding.andorstrail.editor.entity.common;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public abstract class Player {
	public Actor mActorInfo;
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
	
	public abstract class SkillInfo {
		public int mSkillId;
		public int mSkillLevel;
		
		public SkillInfo(DataInputStream dis) throws IOException {
			
		}
		
		public abstract void writeToStream(DataOutputStream dos) throws IOException;
	}
	
	public abstract class QuestInfo {
		public String mQuestId;
		public Set<Integer> mProgress = new HashSet<Integer>();
		
		public QuestInfo(DataInputStream dis) throws IOException {
			
		}
		
		public abstract void writeToStream(DataOutputStream dos) throws IOException;
	}
	
	public Player(int version, DataInputStream dis) throws IOException {
		
	}
	
	public abstract void writeToStream(DataOutputStream dos) throws IOException;
}
