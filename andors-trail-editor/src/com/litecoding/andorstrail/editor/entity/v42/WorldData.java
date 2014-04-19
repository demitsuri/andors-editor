package com.litecoding.andorstrail.editor.entity.v42;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.util.HashMap;
import java.util.Map.Entry;

/* Related to com.gpl.rpg.AndorsTrail.model.WorldData */
public class WorldData extends SaveEntity {
	public long mWorldTime;
	public HashMap<String, Long> mTimers = new HashMap<String, Long>();

	@Override
	public boolean read(DataInputStream dis, boolean rewindAfterRead) {
		//matches: version code 42
		try {
			mWorldTime = dis.readLong();
			int cnt = dis.readInt();
			for(int i = 0; i < cnt; i++) {
				mTimers.put(dis.readUTF(), dis.readLong());
			}
		} catch(Exception e) {
			mSavedException = e;
			return false;
		}
		
		return true;
	}

	@Override
	public boolean write(DataOutputStream dos) {
		//matches: version code 42
		try {
			dos.writeLong(mWorldTime);
			dos.writeInt(mTimers.size());
			for(Entry<String, Long> entry : mTimers.entrySet()) {
				dos.writeUTF(entry.getKey());
				dos.writeLong(entry.getValue());
			}
		} catch(Exception e) {
			mSavedException = e;
			return false;
		}
		
		return true;
	}

}
