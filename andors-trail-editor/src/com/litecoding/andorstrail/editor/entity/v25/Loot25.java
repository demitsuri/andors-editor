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
import com.litecoding.andorstrail.editor.entity.common.Coord;
import com.litecoding.andorstrail.editor.entity.common.Loot;

@Version(ver = 25)
public class Loot25 extends Loot {
	
	public Loot25(int version, DataInputStream dis) throws IOException {
		super(version, dis);
		mExp = dis.readInt();
		mGold = dis.readInt();
		mItemContainer = EntityFactory.createItemContainer(version, dis);
		mPosition = new Coord(dis);
		mIsVisible = dis.readBoolean();
	}
	
	public void writeToStream(DataOutputStream dos) throws IOException {
		dos.writeInt(mExp);
		dos.writeInt(mGold);
		mItemContainer.writeToStream(dos);
		mPosition.writeToStream(dos);
		dos.writeBoolean(mIsVisible);
	}
}
