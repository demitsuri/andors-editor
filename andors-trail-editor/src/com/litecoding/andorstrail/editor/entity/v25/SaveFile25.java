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
import com.litecoding.andorstrail.editor.entity.common.SaveFile;

@Version(ver = 25)
public class SaveFile25 extends SaveFile {
	
	public SaveFile25(int version, DataInputStream dis) throws IOException {
		super(version, dis);
		mFileHeader = EntityFactory.createFileHeader(version, dis);
		mMapsContainer = EntityFactory.createMapsContainer(version, dis);
		mModelContainer = EntityFactory.createModelContainer(version, dis);
	}
	
	public void writeToStream(DataOutputStream dos) throws IOException {
		mFileHeader.writeToStream(dos);
		mMapsContainer.writeToStream(dos);
		mModelContainer.writeToStream(dos);
	}
	
}
