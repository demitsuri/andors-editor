package com.litecoding.andorstrail.editor.entity.v31;

import com.litecoding.andorstrail.editor.entity.EntityFactory;
import com.litecoding.andorstrail.editor.entity.common.Monster;

public class Registrator {
	public static void register() {
		EntityFactory.registerEntity(Monster.class, Monster31.class);
	}
}
