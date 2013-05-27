package com.litecoding.andorstrail.editor.entity.v33;

import java.io.DataInputStream;
import java.io.DataOutputStream;

import com.litecoding.andorstrail.editor.entity.v33.FileHeader;

public class SaveFile extends SaveEntity {
	public FileHeader mFileHeader;
	public MapsContainer mMapsContainer;
	public ModelContainer mModelContainer;

	public boolean read(DataInputStream dis, boolean rewindAfterRead) {
		if(rewindAfterRead == false) {
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
