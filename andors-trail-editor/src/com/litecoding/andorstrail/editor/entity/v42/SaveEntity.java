package com.litecoding.andorstrail.editor.entity.v42;

import java.io.DataInputStream;
import java.io.DataOutputStream;

public abstract class SaveEntity {
	protected Exception mSavedException = null;
	
	public boolean read(DataInputStream dis) {
		return read(dis, false);
	}
	
	public abstract boolean read(DataInputStream dis, boolean rewindAfterRead);
	public abstract boolean write(DataOutputStream dos);
	
	public Exception getLastException() {
		return mSavedException;
	}
}
