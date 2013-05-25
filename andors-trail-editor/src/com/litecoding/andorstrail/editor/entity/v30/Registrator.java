package com.litecoding.andorstrail.editor.entity.v30;

import com.litecoding.andorstrail.editor.entity.EntityFactory;
import com.litecoding.andorstrail.editor.entity.common.Place;

public class Registrator {
	public static void register() {
		EntityFactory.registerEntity(Place.class, Place30.class);
	}
}
