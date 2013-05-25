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
import com.litecoding.andorstrail.editor.entity.common.Monster;
import com.litecoding.andorstrail.editor.entity.common.SpawnArea;

@Version(ver = 25)
public class SpawnArea25 extends SpawnArea {
		
	public SpawnArea25(int version, DataInputStream dis) throws IOException {
		super(version, dis);
		int monsterCnt = dis.readInt();
		
		for(int i = 0; i < monsterCnt; i++) {
			mMonsters.add(EntityFactory.createMonster(version, dis));
		}
	}
	
	public void writeToStream(DataOutputStream dos) throws IOException {
		dos.writeInt(mMonsters.size());
		for(Monster monster : mMonsters) {
			monster.writeToStream(dos);
		}
	}
}
