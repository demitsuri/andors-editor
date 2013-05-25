//GPL v2
/*
 * 
 */
package com.litecoding.andorstrail.editor.entity.common;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public abstract class CombatTraits {
	public int mAttackCost;
	public int mAttackChance;
	public int mCriticalSkill;
	public float mCriticalMultiplier;
	public Range mDamagePotential;
	public int mBlockChance;
	public int mDamageResistance;
	
	public CombatTraits(int version, DataInputStream dis) throws IOException {
		
	}
	
	public abstract void writeToStream(DataOutputStream dos) throws IOException;
}
