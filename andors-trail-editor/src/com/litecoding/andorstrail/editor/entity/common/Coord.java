//GPL v2
/*
 * 
 */
package com.litecoding.andorstrail.editor.entity.common;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class Coord {
	public int mX;
	public int mY;
	
	public Coord(DataInputStream dis) throws IOException {
		mX = dis.readInt();
		mY = dis.readInt();
	}
	
	public void writeToStream(DataOutputStream dos) throws IOException {
		dos.writeInt(mX);
		dos.writeInt(mY);
	}
}
