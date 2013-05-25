//GPL v2
/*
 * 
 */
package com.litecoding.andorstrail.editor.entity.v31;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import com.litecoding.andorstrail.editor.entity.EntityFactory;
import com.litecoding.andorstrail.editor.entity.annotation.Version;
import com.litecoding.andorstrail.editor.entity.common.ItemContainer;
import com.litecoding.andorstrail.editor.entity.v25.Monster25;

@Version(ver = 31)
public class Monster31 extends Monster25 {
	public ItemContainer mShopItems = null;
	
	public Monster31(int version, DataInputStream dis) throws IOException {
		super(version, dis);
		if(dis.readBoolean()) {
			mShopItems = EntityFactory.createItemContainer(version, dis);
		}
	}
	
	public void writeToStream(DataOutputStream dos) throws IOException {
		super.writeToStream(dos);
		
		if(mShopItems != null) {
			dos.writeBoolean(true);
			mShopItems.writeToStream(dos);
		} else {
			dos.writeBoolean(false);
		}
	}
}