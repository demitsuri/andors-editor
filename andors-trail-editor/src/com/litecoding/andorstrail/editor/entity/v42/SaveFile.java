package com.litecoding.andorstrail.editor.entity.v42;

import java.io.DataInputStream;
import java.io.DataOutputStream;

import com.litecoding.andorstrail.editor.entity.RewindIsNotSupportedException;
import com.litecoding.andorstrail.editor.entity.v42.FileHeader;

/* Related to com.gpl.rpg.AndorsTrail.savegames.Savegames */
public class SaveFile extends SaveEntity {
	public FileHeader mFileHeader;
	public MapsContainer mMapsContainer;
	public ModelContainer mModelContainer;

	public boolean read(DataInputStream dis, boolean rewindAfterRead) {
		//matches: version code 42
		if(rewindAfterRead) {
			mSavedException = new RewindIsNotSupportedException();
			return false;
		}
		
		mFileHeader = new FileHeader();
		if(!mFileHeader.read(dis)) {
			mSavedException = mFileHeader.getLastException();
			return false;
		}
		
		mMapsContainer = new MapsContainer();
		if(!mMapsContainer.read(dis)) {
			mSavedException = mMapsContainer.getLastException();
			return false;
		}
		
		mModelContainer = new ModelContainer();
		if(!mModelContainer.read(dis)) {
			mSavedException = mModelContainer.getLastException();
			return false;
		}
		
		return true;
	}

	public boolean write(DataOutputStream dos) {
		//matches: version code 42
		boolean result = false;

		if(!mFileHeader.write(dos)) {
			mSavedException = mFileHeader.getLastException();
			return false;
		}
		
		if(!mMapsContainer.write(dos)) {
			mSavedException = mMapsContainer.getLastException();
			return false;
		}
		
		if(!mModelContainer.write(dos)) {
			mSavedException = mModelContainer.getLastException();
			return false;
		}
		
		mSavedException = null;
		result = true;
		
		return result;
	}

}
