//GPL v2
/*
 * 
 */
package com.litecoding.andorstrail.editor.entity.common;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public abstract class GameStatistics {
	public int mDeaths;
	public Map<String, Integer> mKilledMonsters = new HashMap<String, Integer>();
	public Map<String, Integer> mUsedItems = new HashMap<String, Integer>();
	public int mSpentGold;
	
	public GameStatistics(int version, DataInputStream dis) throws IOException {
		
	}
	
	public abstract void writeToStream(DataOutputStream dos) throws IOException;
	
}
