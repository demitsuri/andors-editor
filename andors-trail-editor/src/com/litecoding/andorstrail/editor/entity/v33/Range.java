//GPL v2
/*
 * 
 */
package com.litecoding.andorstrail.editor.entity.v33;

import java.io.DataInputStream;
import java.io.DataOutputStream;

public class Range extends SaveEntity {
	public int mMax;
	public int mCurrent;

	@Override
	public boolean read(DataInputStream dis, boolean rewindAfterRead) {
		if(rewindAfterRead && dis.markSupported()) {
			dis.mark(8);
		}
		
		try {
			mMax = dis.readInt();
			mCurrent = dis.readInt();
		} catch (Exception e) {
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
		try {
			dos.writeInt(mMax);
			dos.writeInt(mCurrent);
		} catch (Exception e) {
			mSavedException = e;
			return false;
		}
		
		return true;
	}
}
