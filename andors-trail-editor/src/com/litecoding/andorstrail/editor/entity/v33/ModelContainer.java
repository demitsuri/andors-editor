package com.litecoding.andorstrail.editor.entity.v33;

import java.io.DataInputStream;
import java.io.DataOutputStream;


public class ModelContainer extends SaveEntity {
	public Player mPlayer;
	public String mCurrentMapId;
	public InterfaceData mInterfaceData;
	public GameStatistics mGameStatistics;

	@Override
	public boolean read(DataInputStream dis, boolean rewindAfterRead) {
		if(rewindAfterRead == false) {
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
		
		return true;
	}

	@Override
	public boolean write(DataOutputStream dos) {
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
		
		return true;
	}

}
