//GPL v2
/*
 * 
 */
package com.litecoding.andorstrail.editor.entity;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import com.litecoding.andorstrail.editor.entity.common.SaveFile;

public class SaveFileUtils {
	
	public static SaveFile load(String filename) throws IOException {
		SaveFile save = null;
		
		FileInputStream fis = new FileInputStream(filename);
		BufferedInputStream bis = new BufferedInputStream(fis);
    	DataInputStream dis = new DataInputStream(bis);
    	
    	dis.mark(4);    	
    	int version = dis.readInt();
    	dis.reset();
    	
    	save = EntityFactory.createSaveFile(version, dis);
    	if(save == null)
    		throw new UnsupportedFileVersionException();

    	dis.close();
    	fis.close();
    	
    	return save;
	}
	
	public static void save(SaveFile save, String filename) throws IOException {
		FileOutputStream fos = new FileOutputStream(filename);
		DataOutputStream dos = new DataOutputStream(fos);
		save.writeToStream(dos);
		dos.flush();
		fos.flush();
		dos.close();
		fos.close();
	}
}
