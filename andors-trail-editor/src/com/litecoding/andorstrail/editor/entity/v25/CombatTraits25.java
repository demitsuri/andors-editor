//GPL v2
/*
 * 
 */
package com.litecoding.andorstrail.editor.entity.v25;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import android.util.Log;

import com.litecoding.andorstrail.editor.Main;
import com.litecoding.andorstrail.editor.entity.annotation.Version;
import com.litecoding.andorstrail.editor.entity.common.CombatTraits;
import com.litecoding.andorstrail.editor.entity.common.Range;

@Version(ver = 25)
public class CombatTraits25 extends CombatTraits {
	
	public CombatTraits25(int version, DataInputStream dis) throws IOException {
		super(version, dis);
		Log.d(Main.TAG, "Parse CombatTraits");
		mAttackCost = dis.readInt();
		Log.d(Main.TAG, "mAttackCost: " + mAttackCost);
		mAttackChance = dis.readInt();
		Log.d(Main.TAG, "mAttackChance: " + mAttackChance);
		mCriticalSkill = dis.readInt();
		Log.d(Main.TAG, "mCriticalSkill: " + mCriticalSkill);
		mCriticalMultiplier = dis.readFloat();
		Log.d(Main.TAG, "mCriticalMultiplier: " + mCriticalMultiplier);
		mDamagePotential = new Range(dis);
		Log.d(Main.TAG, "mDamagePotential: " + mDamagePotential.mCurrent + " to " + mDamagePotential.mMax);
		mBlockChance = dis.readInt();
		Log.d(Main.TAG, "mBlockChance: " + mBlockChance);
		mDamageResistance = dis.readInt();
		Log.d(Main.TAG, "mDamageResistance: " + mDamageResistance);
		Log.d(Main.TAG, "_");
	}
	
	public void writeToStream(DataOutputStream dos) throws IOException {
		dos.writeInt(mAttackCost);
		dos.writeInt(mAttackChance);
		dos.writeInt(mCriticalSkill);
		dos.writeFloat(mCriticalMultiplier);
		mDamagePotential.writeToStream(dos);
		dos.writeInt(mBlockChance);
		dos.writeInt(mDamageResistance);
	}
}
