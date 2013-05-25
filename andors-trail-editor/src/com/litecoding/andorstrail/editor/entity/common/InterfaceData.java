//GPL v2
/*
 * 
 */
package com.litecoding.andorstrail.editor.entity.common;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public abstract class InterfaceData {
	public boolean mIsMainActivityVisible;
	public boolean mIsInCombat;
	public Coord mSelectedPosition;
	public String mSelectedTabHeroInfo;
	
	public InterfaceData(int version, DataInputStream dis) throws IOException {
		
	}
	
	public abstract void writeToStream(DataOutputStream dos) throws IOException;
	
}
