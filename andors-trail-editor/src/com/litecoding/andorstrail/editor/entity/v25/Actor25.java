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
import com.litecoding.andorstrail.editor.entity.EntityFactory;
import com.litecoding.andorstrail.editor.entity.annotation.Version;
import com.litecoding.andorstrail.editor.entity.common.Actor;
import com.litecoding.andorstrail.editor.entity.common.ActorConditions;
import com.litecoding.andorstrail.editor.entity.common.Coord;
import com.litecoding.andorstrail.editor.entity.common.Range;

@Version(ver = 25)
public class Actor25 extends Actor {
	
	public Actor25(int version, DataInputStream dis, boolean isPlayer) throws IOException {
		super(version, dis, isPlayer);
		
		mIsPlayer = isPlayer;
		
		boolean isReadCombatTraits = dis.readBoolean();
		Log.d(Main.TAG, "isReadCombatTraits: " + isReadCombatTraits);
		if(isReadCombatTraits) {
			mCombatTraits = EntityFactory.createCombatTraits(version, dis);
		}
		
		if(mIsPlayer)
			mActorTraits = EntityFactory.createActorTraits(version, dis);
		
		mAP = new Range(dis);
		Log.d(Main.TAG, "mAP: " + mAP.mCurrent + " of " + mAP.mMax);
		mHP = new Range(dis);
		Log.d(Main.TAG, "mHP: " + mHP.mCurrent + " of " + mHP.mMax);
		mPosition = new Coord(dis);
		Log.d(Main.TAG, "mPosition: x=" + mPosition.mX + " y=" + mPosition.mY);
		
		int cnt = dis.readInt();
		Log.d(Main.TAG, "ActorConditions found: " + cnt);
		for(int i = 0; i < cnt; i++) {
			mActorConditions.add(EntityFactory.createActorConditions(version, dis));
		}
	}
	
	public void writeActorData(DataOutputStream dos) throws IOException {
		if(mCombatTraits == null)
			dos.writeBoolean(false);
		else {
			dos.writeBoolean(true);
			mCombatTraits.writeToStream(dos);
		}
		
		if(mIsPlayer)
			mActorTraits.writeToStream(dos);
		
		mAP.writeToStream(dos);
		mHP.writeToStream(dos);
		mPosition.writeToStream(dos);
		
		dos.writeInt(mActorConditions.size());
		for(ActorConditions actorCondition : mActorConditions) {
			((ActorConditions25)actorCondition).writeToStream(dos);
		}
	}
}
