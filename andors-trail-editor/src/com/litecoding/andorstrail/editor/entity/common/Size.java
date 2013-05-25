//GPL v2
/*
 * 
 */
package com.litecoding.andorstrail.editor.entity.common;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class Size {
	public int mWidth;
	public int mHeight;
	
	public Size(DataInputStream dis) throws IOException {
		mWidth = dis.readInt();
		mHeight = dis.readInt();
	}
	
	public void writeToStream(DataOutputStream dos) throws IOException {
		dos.writeInt(mWidth);
		dos.writeInt(mHeight);
	}
}
