//GPL v2
/*
 * 
 */
package com.litecoding.andorstrail.editor.entity.v25;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import com.litecoding.andorstrail.editor.entity.EntityFactory;
import com.litecoding.andorstrail.editor.entity.annotation.Version;
import com.litecoding.andorstrail.editor.entity.common.Monster;

@Version(ver = 25)
public class Monster25 extends Monster {
	
	public Monster25(int version, DataInputStream dis) throws IOException {
		super(version, dis);
		mMonsterTypeId = dis.readUTF();
		mActorData = EntityFactory.createActor(version, dis, false);
		mActorData.mIsPlayer = false;
		mIsAggressive = dis.readBoolean();
	}
	
	public void writeToStream(DataOutputStream dos) throws IOException {
		dos.writeUTF(mMonsterTypeId);
		mActorData.writeActorData(dos);
		dos.writeBoolean(mIsAggressive);
	}
}

