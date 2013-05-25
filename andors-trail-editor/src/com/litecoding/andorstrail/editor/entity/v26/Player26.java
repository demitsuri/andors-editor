package com.litecoding.andorstrail.editor.entity.v26;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import com.litecoding.andorstrail.editor.entity.annotation.Version;
import com.litecoding.andorstrail.editor.entity.v25.Player25;

@Version(ver = 26)
public class Player26 extends Player25 {
	public Map<String, Integer> mAlignments = new HashMap<String, Integer>();

	public Player26(int version, DataInputStream dis) throws IOException {
		super(version, dis);
		
		int size = dis.readInt();
		for(int i = 0; i < size; i++) {
			String fraction = dis.readUTF();
			int alignment = dis.readInt();
			mAlignments.put(fraction, alignment);
		}
	}

	public void writeToStream(DataOutputStream dos) throws IOException {
		super.writeToStream(dos);
		
		dos.writeInt(mAlignments.size());
		for(Entry<String, Integer> alignment : mAlignments.entrySet()) {
			dos.writeUTF(alignment.getKey());
			dos.writeInt(alignment.getValue());
		}
	}
}
