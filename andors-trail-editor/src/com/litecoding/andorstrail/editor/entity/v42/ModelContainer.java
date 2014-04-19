package com.litecoding.andorstrail.editor.entity.v42;

import java.io.DataInputStream;
import java.io.DataOutputStream;

import com.litecoding.andorstrail.editor.entity.RewindIsNotSupportedException;

/* Related to com.gpl.rpg.AndorsTrail.model.ModelContainer */
public class ModelContainer extends SaveEntity {
	public Player mPlayer;
	public String mCurrentMapId;
	public InterfaceData mInterfaceData;
	public GameStatistics mGameStatistics;
	public WorldData mWorldData;

	@Override
	public boolean read(DataInputStream dis, boolean rewindAfterRead) {
		//matches: version code 42
		if(rewindAfterRead) {
			mSavedException = new RewindIsNotSupportedException();
			return false;
		}
		
		mPlayer = new Player();
		if(!mPlayer.read(dis)) {
			mSavedException = mPlayer.getLastException();
			return false;
		}
		
		try {
			mCurrentMapId = dis.readUTF();
		} catch(Exception e) {
			mSavedException = e;
			return false;
		}
		
		mInterfaceData = new InterfaceData();
		if(!mInterfaceData.read(dis)) {
			mSavedException = mInterfaceData.getLastException();
			return false;
		}
		
		mGameStatistics = new GameStatistics();
		if(!mGameStatistics.read(dis)) {
			mSavedException = mGameStatistics.getLastException();
			return false;
		}
		
		mWorldData = new WorldData();
		if(!mWorldData.read(dis)) {
			mSavedException = mGameStatistics.getLastException();
			return false;
		}
		
		return true;
	}

	@Override
	public boolean write(DataOutputStream dos) {
		//matches: version code 42
		if(!mPlayer.write(dos)) {
			mSavedException = mPlayer.getLastException();
			return false;
		}
		
		try {
			dos.writeUTF(mCurrentMapId);
		} catch(Exception e) {
			mSavedException = e;
			return false;
		}
		
		if(!mInterfaceData.write(dos)) {
			mSavedException = mInterfaceData.getLastException();
			return false;
		}
		
		if(!mGameStatistics.write(dos)) {
			mSavedException = mGameStatistics.getLastException();
			return false;
		}
		
		if(!mWorldData.write(dos)) {
			mSavedException = mWorldData.getLastException();
			return false;
		}
		
		return true;
	}

}
