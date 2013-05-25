//GPL v2
/*
 * 
 */
package com.litecoding.andorstrail.editor.entity.common;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public abstract class SaveFile {
	public FileHeader mFileHeader;
	public MapsContainer mMapsContainer;
	public ModelContainer mModelContainer;

	public SaveFile(int version, DataInputStream dis) throws IOException {
		
	}
	
	public abstract void writeToStream(DataOutputStream dos) throws IOException;
	
}
