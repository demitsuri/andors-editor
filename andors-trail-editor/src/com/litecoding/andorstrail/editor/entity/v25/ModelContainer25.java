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
import com.litecoding.andorstrail.editor.entity.common.ModelContainer;

@Version(ver = 25)
public class ModelContainer25 extends ModelContainer {
	
	public ModelContainer25(int version, DataInputStream dis) throws IOException {
		super(version, dis);
		mPlayer = EntityFactory.createPlayer(version, dis);
		mCurrentMapId = dis.readUTF();
		mInterfaceData = EntityFactory.createInterfaceData(version, dis);
		mGameStatistics = EntityFactory.createGameStatistics(version, dis);
	}
	
	public void writeToStream(DataOutputStream dos) throws IOException {
		mPlayer.writeToStream(dos);
		dos.writeUTF(mCurrentMapId);
		mInterfaceData.writeToStream(dos);
		mGameStatistics.writeToStream(dos);
	}
}
