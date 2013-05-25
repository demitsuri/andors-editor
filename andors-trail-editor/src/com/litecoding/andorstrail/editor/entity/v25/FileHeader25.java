//GPL v2
/*
 * 
 */
package com.litecoding.andorstrail.editor.entity.v25;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import com.litecoding.andorstrail.editor.entity.annotation.Version;
import com.litecoding.andorstrail.editor.entity.common.FileHeader;

@Version(ver = 25)
public class FileHeader25 extends FileHeader {
	
	public FileHeader25(int version, DataInputStream dis) throws IOException {
		super(version, dis);
		mVer = dis.readInt();
		mName = dis.readUTF();
		mSummary = dis.readUTF();
	}
	
	public void writeToStream(DataOutputStream dos) throws IOException {
		dos.writeInt(mVer);
		dos.writeUTF(mName);
		dos.writeUTF(mSummary);
	}
}
