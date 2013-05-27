package com.litecoding.andorstrail.editor.entity.v33;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.util.LinkedList;
import java.util.List;

public class Monster extends SaveEntity {
	/* actor fields*/
	public CombatTraits mCombatTraits;
	public Range mAP;
	public Range mHP;
	public Coord mPosition;
	public List<ActorCondition> mActorConditions = new LinkedList<ActorCondition>();

	/* monster fields */
	public String mMonsterTypeId;
	public boolean mIsAggressive;
	public boolean mHasShopItems;
	public ItemContainer mShopItems;
	
	@Override
	public boolean read(DataInputStream dis, boolean rewindAfterRead) {
		if(rewindAfterRead == false) {
			return false;
		}
		
		try {
			mMonsterTypeId = dis.readUTF();
	
			//Actor fields
			boolean isReadCombatTraits = dis.readBoolean();
			if(isReadCombatTraits) {
				mCombatTraits = new CombatTraits();
				if(!mCombatTraits.read(dis)) {
					mSavedException = mCombatTraits.getLastException();
					return false;
				}
			}
			
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
			
			//Monster fields
			mIsAggressive = dis.readBoolean();
			
			mHasShopItems = dis.readBoolean();
			if(mHasShopItems) {
				mShopItems = new ItemContainer();
				if(!mShopItems.read(dis)) {
					mSavedException = mShopItems.getLastException();
					return false;
				}
			}
		} catch (Exception e) {
			mSavedException = e;
			return false;
		}
		
		return true;
	}

	@Override
	public boolean write(DataOutputStream dos) {
		try {
			dos.writeUTF(mMonsterTypeId);
			
			//Actor fields
			if(mCombatTraits == null)
				dos.writeBoolean(false);
			else {
				dos.writeBoolean(true);
				if(!mCombatTraits.write(dos)) {
					mSavedException = mCombatTraits.getLastException();
					return false;
				}
			}
			
			mAP.write(dos);
			mHP.write(dos);
			mPosition.write(dos);
			
			dos.writeInt(mActorConditions.size());
			for(ActorCondition actorCondition : mActorConditions) {
				if(!actorCondition.write(dos)) {
					mSavedException = actorCondition.getLastException();
					return false;
				}
			}
			
			dos.writeBoolean(mIsAggressive);
			dos.writeBoolean(mHasShopItems);
			if(mHasShopItems) {
				if(!mShopItems.write(dos)) {
					mSavedException = mShopItems.getLastException();
					return false;
				}
			}
		} catch (Exception e) {
			mSavedException = e;
			return false;
		}
		
		return true;
	}

}
