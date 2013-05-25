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
import com.litecoding.andorstrail.editor.entity.common.ActorTraits;
import com.litecoding.andorstrail.editor.entity.common.Size;

@Version(ver = 25)
public class ActorTraits25 extends ActorTraits {
	
	public ActorTraits25(int version, DataInputStream dis) throws IOException {
		super(version, dis);
		mIconId = dis.readInt();
		mTileSize = new Size(dis);
		mMaxAP = dis.readInt();
		mMaxHP = dis.readInt();
		mName = dis.readUTF();
		mMoveCost = dis.readInt();
		mBaseCombatTraits = EntityFactory.createCombatTraits(version, dis);
		mBaseMoveCost = dis.readInt();
	}
	
	public void writeToStream(DataOutputStream dos) throws IOException {
		dos.writeInt(mIconId);
		mTileSize.writeToStream(dos);
		dos.writeInt(mMaxAP);
		dos.writeInt(mMaxHP);
		dos.writeUTF(mName);
		dos.writeInt(mMoveCost);
		mBaseCombatTraits.writeToStream(dos);
		dos.writeInt(mBaseMoveCost);
	}
	
}
