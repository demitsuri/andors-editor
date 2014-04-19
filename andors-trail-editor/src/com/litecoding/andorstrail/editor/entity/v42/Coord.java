//GPL v2
/*
 * 
 */
package com.litecoding.andorstrail.editor.entity.v42;

import java.io.DataInputStream;
import java.io.DataOutputStream;

public class Coord extends SaveEntity {
	public int mX;
	public int mY;
		
	public boolean write(DataOutputStream dos) {
		try {
			dos.writeInt(mX);
			dos.writeInt(mY);
		} catch(Exception e) {
			mSavedException = e;
			return false;
		}
		
		return true;
	}

	@Override
	public boolean read(DataInputStream dis, boolean rewindAfterRead) {
		if(rewindAfterRead && dis.markSupported()) {
			dis.mark(8);
		}
		
		try {
			mX = dis.readInt();
			mY = dis.readInt();
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

}
