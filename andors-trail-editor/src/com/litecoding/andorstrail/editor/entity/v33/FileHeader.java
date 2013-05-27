package com.litecoding.andorstrail.editor.entity.v33;

import java.io.DataInputStream;
import java.io.DataOutputStream;

public class FileHeader extends SaveEntity {
	public int mVer;
	public String mName;
	public String mSummary;

	public boolean read(DataInputStream dis) {
		return read(dis, false);
	}

	public boolean read(DataInputStream dis, boolean rewindAfterRead) {
		boolean result = false;
		try {
			if(rewindAfterRead && dis.markSupported()) {
				dis.mark(1024 * 1024);
			}
			mVer = dis.readInt();
			mName = dis.readUTF();
			mSummary = dis.readUTF();
			if(rewindAfterRead && dis.markSupported()) {
				dis.reset();
			}
			mSavedException = null;
			result = true;
		} catch(Exception e) {
			mSavedException = e;
			result = false;
		}
		return result;
	}

	public boolean write(DataOutputStream dos) {
		boolean result = false;
		try {
			dos.writeInt(mVer);
			dos.writeUTF(mName);
			dos.writeUTF(mSummary);
			mSavedException = null;
			result = true;
		} catch(Exception e) {
			mSavedException = e;
			result = false;
		}
		return result;
	}

}
