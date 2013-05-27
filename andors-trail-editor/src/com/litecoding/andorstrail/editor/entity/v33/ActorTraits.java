package com.litecoding.andorstrail.editor.entity.v33;

import java.io.DataInputStream;
import java.io.DataOutputStream;

import com.litecoding.andorstrail.editor.entity.RewindIsNotSupportedException;

public class ActorTraits extends SaveEntity {
	public int mIconId;
	public Size mTileSize;
	public int mMaxAP;
	public int mMaxHP;
	public String mName;
	public int mMoveCost;
	public CombatTraits mBaseCombatTraits;
	public int mBaseMoveCost;

	@Override
	public boolean read(DataInputStream dis, boolean rewindAfterRead) {
		if(rewindAfterRead) {
			mSavedException = new RewindIsNotSupportedException();
			return false;
		}
		
		try {
			mIconId = dis.readInt();
			
			mTileSize = new Size();
			if(!mTileSize.read(dis)) {
				mSavedException = mTileSize.getLastException();
				return false;
			}
			
			mMaxAP = dis.readInt();
			mMaxHP = dis.readInt();
			mName = dis.readUTF();
			mMoveCost = dis.readInt();
			
			mBaseCombatTraits = new CombatTraits();
			if(!mBaseCombatTraits.read(dis)) {
				mSavedException = mBaseCombatTraits.getLastException();
				return false;
			}
			
			mBaseMoveCost = dis.readInt();
		} catch (Exception e) {
			mSavedException = e;
			return false;
		}
		
		return true;
	}

	@Override
	public boolean write(DataOutputStream dos) {
		try {
			dos.writeInt(mIconId);
			
			if(!mTileSize.write(dos)) {
				mSavedException = mTileSize.getLastException();
				return false;
			}
			
			dos.writeInt(mMaxAP);
			dos.writeInt(mMaxHP);
			dos.writeUTF(mName);
			dos.writeInt(mMoveCost);
			
			if(!mBaseCombatTraits.write(dos)) {
				mSavedException = mBaseCombatTraits.getLastException();
				return false;
			}
			
			dos.writeInt(mBaseMoveCost);
		} catch (Exception e) {
			mSavedException = e;
			return false;
		}
		
		return true;
	}

}
