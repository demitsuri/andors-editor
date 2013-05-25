//GPL v2
/*
 * 
 */
package com.litecoding.andorstrail.editor.entity.common;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public abstract class ModelContainer {
	public Player mPlayer;
	public String mCurrentMapId;
	public InterfaceData mInterfaceData;
	public GameStatistics mGameStatistics;
	
	public ModelContainer(int version, DataInputStream dis) throws IOException {
		
	}
	
	public abstract void writeToStream(DataOutputStream dos) throws IOException;
	
}
