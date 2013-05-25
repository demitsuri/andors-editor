//GPL v2
/*
 * 
 */
package com.litecoding.andorstrail.editor.entity.v25;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import com.litecoding.andorstrail.editor.entity.annotation.Version;
import com.litecoding.andorstrail.editor.entity.common.ActorConditions;

@Version(ver = 25)
public class ActorConditions25 extends ActorConditions {
	
	public ActorConditions25(int version, DataInputStream dis) throws IOException {
		super(version, dis);
		mConditionTypeId = dis.readUTF();
		mMagnitude = dis.readInt();
		mDuration = dis.readInt();
	}
	
	public void writeToStream(DataOutputStream dos) throws IOException {
		dos.writeUTF(mConditionTypeId);
		dos.writeInt(mMagnitude);
		dos.writeInt(mDuration);
	}
}
