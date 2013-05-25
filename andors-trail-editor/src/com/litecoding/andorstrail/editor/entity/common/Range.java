//GPL v2
/*
 * 
 */
package com.litecoding.andorstrail.editor.entity.common;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class Range {
	public int mMax;
	public int mCurrent;
	
	public Range(DataInputStream dis) throws IOException {
		mMax = dis.readInt();
		mCurrent = dis.readInt();
	}
	
	public void writeToStream(DataOutputStream dos) throws IOException {
		dos.writeInt(mMax);
		dos.writeInt(mCurrent);
	}
}
