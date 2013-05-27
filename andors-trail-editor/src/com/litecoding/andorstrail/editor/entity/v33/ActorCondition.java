package com.litecoding.andorstrail.editor.entity.v33;

import java.io.DataInputStream;
import java.io.DataOutputStream;

import com.litecoding.andorstrail.editor.entity.RewindIsNotSupportedException;

public class ActorCondition extends SaveEntity {
	public String mConditionTypeId;
	public int mMagnitude;
	public int mDuration;

	@Override
	public boolean read(DataInputStream dis, boolean rewindAfterRead) {
		if(rewindAfterRead) {
			mSavedException = new RewindIsNotSupportedException();
			return false;
		}
		
		try {
			mConditionTypeId = dis.readUTF();
			mMagnitude = dis.readInt();
			mDuration = dis.readInt();
		} catch (Exception e) {
			mSavedException = e;
			return false;
		}
		
		return true;
	}

	@Override
	public boolean write(DataOutputStream dos) {
		try {
			dos.writeUTF(mConditionTypeId);
			dos.writeInt(mMagnitude);
			dos.writeInt(mDuration);
		} catch (Exception e) {
			mSavedException = e;
			return false;
		}
		
		return true;
	}

}
