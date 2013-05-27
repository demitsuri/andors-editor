package com.litecoding.andorstrail.editor.entity.v33;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.util.LinkedList;
import java.util.List;

public class MapsContainer extends SaveEntity {
	public List<Place> mPlaces = new LinkedList<Place>();

	@Override
	public boolean read(DataInputStream dis, boolean rewindAfterRead) {
		if(rewindAfterRead == false) {
			return false;
		}
		
		int count = 0;
		try {
			count = dis.readInt();
		} catch(Exception e) {
			mSavedException = e;
			return false;
		}
		
		for(int i = 0; i < count; i++) {
			Place place = new Place();
			boolean result = place.read(dis);
			if(!result) {
				mSavedException = place.getLastException();
				return false;
			}
			mPlaces.add(place);
		}
		return true;
	}

	@Override
	public boolean write(DataOutputStream dos) {
		boolean result = false;
		
		try {
			dos.writeInt(mPlaces.size());
		} catch(Exception e) {
			mSavedException = e;
			return false;
		}
		
		for(Place place : mPlaces) {
			result = place.write(dos);
			if(!result) {
				mSavedException = place.getLastException();
				return false;
			}
		}
		return true;
	}

}
