//GPL v2
/*
 * 
 */
package com.litecoding.andorstrail.editor.entity.common;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public abstract class FileHeader {
	public int mVer;
	public String mName;
	public String mSummary;
	
	public FileHeader(int version, DataInputStream dis) {
		mVer = version;
	}
	
	public abstract void writeToStream(DataOutputStream dos) throws IOException;
}
