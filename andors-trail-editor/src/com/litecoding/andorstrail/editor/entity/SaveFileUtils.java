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

import com.litecoding.andorstrail.editor.entity.v33.SaveFile;

public class SaveFileUtils {
	
	public static SaveFile load(String filename) throws Exception {
		SaveFile save = null;
		
		FileInputStream fis = new FileInputStream(filename);
		BufferedInputStream bis = new BufferedInputStream(fis);
    	DataInputStream dis = new DataInputStream(bis);
    	
    	save = new SaveFile();
    	if(!save.read(dis)) {
    		throw save.getLastException();
    	}

    	dis.close();
    	fis.close();
    	
    	return save;
	}
	
	public static void save(SaveFile save, String filename) throws Exception {
		FileOutputStream fos = new FileOutputStream(filename);
		DataOutputStream dos = new DataOutputStream(fos);
		if(!save.write(dos)) {
			throw save.getLastException();
		}
		dos.flush();
		fos.flush();
		dos.close();
		fos.close();
	}
}
