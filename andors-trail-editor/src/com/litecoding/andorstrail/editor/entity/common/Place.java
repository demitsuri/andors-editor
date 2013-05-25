package com.litecoding.andorstrail.editor.entity.common;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public abstract class Place {
	public List<SpawnArea> mSpawnAreas = new LinkedList<SpawnArea>();
	public List<Loot> mLoot = new LinkedList<Loot>();
	public boolean mVisited;
	public long mLastVisitTime;
	public int mLastVisitVersion = 30;
	
	public Place(int version, DataInputStream dis) throws IOException {

	}
	
	public abstract void writeToStream(DataOutputStream dos) throws IOException;
	
}
