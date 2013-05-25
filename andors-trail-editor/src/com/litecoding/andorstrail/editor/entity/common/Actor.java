//GPL v2
/*
 * 
 */
package com.litecoding.andorstrail.editor.entity.common;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public abstract class Actor {
	public boolean mIsPlayer;
	public CombatTraits mCombatTraits;
	public ActorTraits mActorTraits;
	public Range mAP;
	public Range mHP;
	public Coord mPosition;
	public List<ActorConditions> mActorConditions = new LinkedList<ActorConditions>();
	
	public Actor(int version, DataInputStream dis, boolean isPlayer) throws IOException {
		mIsPlayer = isPlayer;
	}
	
	public abstract void writeActorData(DataOutputStream dos) throws IOException;
}
