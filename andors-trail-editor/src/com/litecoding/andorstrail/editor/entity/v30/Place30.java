//GPL v2
/*
 * 
 */
package com.litecoding.andorstrail.editor.entity.v30;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import com.litecoding.andorstrail.editor.entity.annotation.Version;
import com.litecoding.andorstrail.editor.entity.v25.Place25;

@Version(ver = 30)
public class Place30 extends Place25 {
	
	public Place30(int version, DataInputStream dis) throws IOException {
		super(version, dis);
		
		if(mVisited) {
			mLastVisitVersion = dis.readInt();
		}
	}
	
	public void writeToStream(DataOutputStream dos) throws IOException {
		super.writeToStream(dos);
		
		if(mVisited) {
			dos.writeInt(mLastVisitVersion);
		}
	}
}
