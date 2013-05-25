//GPL v2
/*
 * 
 */
package com.litecoding.andorstrail.editor.entity.v34;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import com.litecoding.andorstrail.editor.entity.annotation.Version;
import com.litecoding.andorstrail.editor.entity.v25.Actor25;

@Version(ver = 25)
public class Actor34 extends Actor25 {
	public int mMoveCost;
	
	public Actor34(int version, DataInputStream dis, boolean isPlayer) throws IOException {
		super(version, dis, isPlayer);
		
		mMoveCost = dis.readInt();
	}
	
	public void writeActorData(DataOutputStream dos) throws IOException {
		super.writeActorData(dos);
		
		//TODO: write combat traits only if it's non-standard
		
		dos.writeInt(mMoveCost);
	}
}
