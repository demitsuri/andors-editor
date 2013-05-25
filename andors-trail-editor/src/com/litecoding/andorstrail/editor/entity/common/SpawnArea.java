//GPL v2
/*
 * 
 */
package com.litecoding.andorstrail.editor.entity.common;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public abstract class SpawnArea {
	
	public List<Monster> mMonsters = new LinkedList<Monster>();
	
	public SpawnArea(int version, DataInputStream dis) throws IOException {
		
	}
	
	public abstract void writeToStream(DataOutputStream dos) throws IOException;
}
