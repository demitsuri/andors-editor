package com.litecoding.andorstrail.editor.entity.v42;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.util.LinkedList;
import java.util.List;

import com.litecoding.andorstrail.editor.entity.RewindIsNotSupportedException;

/* Related to com.gpl.rpg.AndorsTrail.model.map.MapCollection */
public class MapsContainer extends SaveEntity {
	public List<Place> mPlaces = new LinkedList<Place>();

	@Override
	public boolean read(DataInputStream dis, boolean rewindAfterRead) {
		//matches: version code 42
		if(rewindAfterRead) {
			mSavedException = new RewindIsNotSupportedException();
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
			String name = ""; //TODO: predefined data?
			try {
				name = dis.readUTF();
			} catch(Exception e) {
				mSavedException = e;
				return false;
			}
			Place place = new Place();
			place.mName = name;
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
		//matches: version code 42
		boolean result = false;
		
		try {
			dos.writeInt(mPlaces.size());
		} catch(Exception e) {
			mSavedException = e;
			return false;
		}
		
		for(Place place : mPlaces) {
			try {
				dos.writeUTF(place.mName);
			} catch(Exception e) {
				mSavedException = e;
				return false;
			}
			
			result = place.write(dos);
			if(!result) {
				mSavedException = place.getLastException();
				return false;
			}
		}
		return true;
	}

}
