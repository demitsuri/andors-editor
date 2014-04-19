package com.litecoding.andorstrail.editor.entity.v42;

import java.io.DataInputStream;
import java.io.DataOutputStream;

import com.litecoding.andorstrail.editor.entity.RewindIsNotSupportedException;

/* Related to com.gpl.rpg.AndorsTrail.model.InterfaceData */
public class InterfaceData extends SaveEntity {
	public boolean mIsMainActivityVisible;
	public boolean mIsInCombat;
	public boolean mHasSelectedPosition;
	public Coord mSelectedPosition;
	public String mSelectedTabHeroInfo;

	@Override
	public boolean read(DataInputStream dis, boolean rewindAfterRead) {
		//matches: version code 42
		if(rewindAfterRead) {
			mSavedException = new RewindIsNotSupportedException();
			return false;
		}
		
		try {
			mIsMainActivityVisible = dis.readBoolean();
			mIsInCombat = dis.readBoolean();
			mHasSelectedPosition = dis.readBoolean();
			if(mHasSelectedPosition) {
				mSelectedPosition = new Coord();
				if(!mSelectedPosition.read(dis)) {
					mSavedException = mSelectedPosition.getLastException();
					return false;
				}
			}
			mSelectedTabHeroInfo = dis.readUTF();
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
			dos.writeBoolean(mIsMainActivityVisible);
			dos.writeBoolean(mIsInCombat);
			dos.writeBoolean(mHasSelectedPosition);
			if(mHasSelectedPosition) {
				if(!mSelectedPosition.write(dos)) {
					mSavedException = mSelectedPosition.getLastException();
					return false;
				}
			}
			dos.writeUTF(mSelectedTabHeroInfo);
		} catch(Exception e) {
			mSavedException = e;
			return false;
		}
		
		return true;
	}

}
