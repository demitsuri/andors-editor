//GPL v2
/*
 * 
 */
package com.litecoding.andorstrail.editor.entity.common;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public abstract class ActorTraits {
	public int mIconId;
	public Size mTileSize;
	public int mMaxAP;
	public int mMaxHP;
	public String mName;
	public int mMoveCost;
	public CombatTraits mBaseCombatTraits;
	public int mBaseMoveCost;
	
	public ActorTraits(int version, DataInputStream dis) throws IOException {
		
	}
	
	public abstract void writeToStream(DataOutputStream dos) throws IOException;
}
