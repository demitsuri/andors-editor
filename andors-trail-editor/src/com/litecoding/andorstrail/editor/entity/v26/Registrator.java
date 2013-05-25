package com.litecoding.andorstrail.editor.entity.v26;

import com.litecoding.andorstrail.editor.entity.EntityFactory;
import com.litecoding.andorstrail.editor.entity.common.Player;

public class Registrator {
	public static void register() {
		EntityFactory.registerEntity(Player.class, Player26.class);
	}
}
