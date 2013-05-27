//GPL v2
/*
 * 
 */
package com.litecoding.andorstrail.editor.entity.v33;

import java.io.DataInputStream;
import java.io.DataOutputStream;

public class Size extends SaveEntity {
	public int mWidth;
	public int mHeight;
		
	@Override
	public boolean read(DataInputStream dis, boolean rewindAfterRead) {
		if(rewindAfterRead && dis.markSupported()) {
			dis.mark(8);
		}
		
		try {
			mWidth = dis.readInt();
			mHeight = dis.readInt();
		} catch (Exception e) {
			mSavedException = e;
			return false;
		}
		
		if(rewindAfterRead && dis.markSupported()) {
			try {
				dis.reset();
			} catch (Exception e) {
				mSavedException = e;
				return false;
			}
		}
		
		return true;
	}
	
	@Override
	public boolean write(DataOutputStream dos) {
		try {
			dos.writeInt(mWidth);
			dos.writeInt(mHeight);
		} catch (Exception e) {
			mSavedException = e;
			return false;
		}
		
		return true;
	}
}
