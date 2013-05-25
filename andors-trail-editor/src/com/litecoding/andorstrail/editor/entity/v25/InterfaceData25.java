//GPL v2
/*
 * 
 */
package com.litecoding.andorstrail.editor.entity.v25;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import com.litecoding.andorstrail.editor.entity.annotation.Version;
import com.litecoding.andorstrail.editor.entity.common.Coord;
import com.litecoding.andorstrail.editor.entity.common.InterfaceData;

@Version(ver = 25)
public class InterfaceData25 extends InterfaceData {
	
	public InterfaceData25(int version, DataInputStream dis) throws IOException {
		super(version, dis);
		mIsMainActivityVisible = dis.readBoolean();
		mIsInCombat = dis.readBoolean();
		if(dis.readBoolean())
			mSelectedPosition = new Coord(dis);
		mSelectedTabHeroInfo = dis.readUTF();
	}
	
	public void writeToStream(DataOutputStream dos) throws IOException {
		dos.writeBoolean(mIsMainActivityVisible);
		dos.writeBoolean(mIsInCombat);
		if(mSelectedPosition == null)
			dos.writeBoolean(false);
		else {
			dos.writeBoolean(true);
			mSelectedPosition.writeToStream(dos);
		}
		dos.writeUTF(mSelectedTabHeroInfo);
	}
}
