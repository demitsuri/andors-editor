//GPL v2
/*
 * 
 */
package com.litecoding.andorstrail.editor.entity.v25;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import com.litecoding.andorstrail.editor.entity.EntityFactory;
import com.litecoding.andorstrail.editor.entity.annotation.Version;
import com.litecoding.andorstrail.editor.entity.common.MapsContainer;
import com.litecoding.andorstrail.editor.entity.common.Place;

@Version(ver = 25)
public class MapsContainer25 extends MapsContainer {
	
	public MapsContainer25(int version, DataInputStream dis) throws IOException {
		super(version, dis);
		int count = dis.readInt();
		for(int i = 0; i < count; i++) {
			mPlaces.add(EntityFactory.createPlace(version, dis));
		}
	}
	
	public void writeToStream(DataOutputStream dos) throws IOException {
		dos.writeInt(mPlaces.size());
		for(Place place : mPlaces) {
			place.writeToStream(dos);
		}
	}
}
