package com.litecoding.andorstrail.editor.entity.v33;

import java.io.DataInputStream;
import java.io.DataOutputStream;

public class CombatTraits extends SaveEntity {
	public int mAttackCost;
	public int mAttackChance;
	public int mCriticalSkill;
	public float mCriticalMultiplier;
	public Range mDamagePotential;
	public int mBlockChance;
	public int mDamageResistance;

	@Override
	public boolean read(DataInputStream dis, boolean rewindAfterRead) {
		if(rewindAfterRead == false) {
			return false;
		}
		
		try {
			mAttackCost = dis.readInt();
			mAttackChance = dis.readInt();
			mCriticalSkill = dis.readInt();
			mCriticalMultiplier = dis.readFloat();
			
			mDamagePotential = new Range();
			if(!mDamagePotential.read(dis)) {
				mSavedException = mDamagePotential.getLastException();
				return false;
			}
			
			mBlockChance = dis.readInt();
			mDamageResistance = dis.readInt();
		} catch(Exception e) {
			mSavedException = e;
			return false;
		}
		
		return true;
	}

	@Override
	public boolean write(DataOutputStream dos) {
		try {
			dos.writeInt(mAttackCost);
			dos.writeInt(mAttackChance);
			dos.writeInt(mCriticalSkill);
			dos.writeFloat(mCriticalMultiplier);
			
			if(!mDamagePotential.write(dos)) {
				mSavedException = mDamagePotential.getLastException();
				return false;
			}
			
			dos.writeInt(mBlockChance);
			dos.writeInt(mDamageResistance);
		} catch (Exception e) {
			mSavedException = e;
			return false;
		}
		
		return true;
	}

}
