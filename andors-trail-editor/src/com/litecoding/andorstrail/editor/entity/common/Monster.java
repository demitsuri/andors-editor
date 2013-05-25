//GPL v2
/*
 * 
 */
package com.litecoding.andorstrail.editor.entity.common;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public abstract class Monster {
	public String mMonsterTypeId;
	public Actor mActorData;
	public boolean mIsAggressive;
	
	public Monster(int version, DataInputStream dis) throws IOException {
		
	}
	
	public abstract void writeToStream(DataOutputStream dos) throws IOException;
}
