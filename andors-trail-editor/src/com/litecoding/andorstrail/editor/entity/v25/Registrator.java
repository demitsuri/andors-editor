package com.litecoding.andorstrail.editor.entity.v25;

import com.litecoding.andorstrail.editor.entity.EntityFactory;
import com.litecoding.andorstrail.editor.entity.common.Actor;
import com.litecoding.andorstrail.editor.entity.common.ActorConditions;
import com.litecoding.andorstrail.editor.entity.common.ActorTraits;
import com.litecoding.andorstrail.editor.entity.common.CombatTraits;
import com.litecoding.andorstrail.editor.entity.common.FileHeader;
import com.litecoding.andorstrail.editor.entity.common.GameStatistics;
import com.litecoding.andorstrail.editor.entity.common.InterfaceData;
import com.litecoding.andorstrail.editor.entity.common.Inventory;
import com.litecoding.andorstrail.editor.entity.common.ItemContainer;
import com.litecoding.andorstrail.editor.entity.common.Loot;
import com.litecoding.andorstrail.editor.entity.common.MapsContainer;
import com.litecoding.andorstrail.editor.entity.common.ModelContainer;
import com.litecoding.andorstrail.editor.entity.common.Monster;
import com.litecoding.andorstrail.editor.entity.common.Place;
import com.litecoding.andorstrail.editor.entity.common.Player;
import com.litecoding.andorstrail.editor.entity.common.SaveFile;
import com.litecoding.andorstrail.editor.entity.common.SpawnArea;

public class Registrator {
	public static void register() {
		EntityFactory.registerEntity(Actor.class, Actor25.class);
		EntityFactory.registerEntity(ActorConditions.class, ActorConditions25.class);
		EntityFactory.registerEntity(ActorTraits.class, ActorTraits25.class);
		EntityFactory.registerEntity(CombatTraits.class, CombatTraits25.class);
		EntityFactory.registerEntity(FileHeader.class, FileHeader25.class);
		EntityFactory.registerEntity(GameStatistics.class, GameStatistics25.class);
		EntityFactory.registerEntity(InterfaceData.class, InterfaceData25.class);
		EntityFactory.registerEntity(Inventory.class, Inventory25.class);
		EntityFactory.registerEntity(ItemContainer.class, ItemContainer25.class);
		EntityFactory.registerEntity(Loot.class, Loot25.class);
		EntityFactory.registerEntity(MapsContainer.class, MapsContainer25.class);
		EntityFactory.registerEntity(ModelContainer.class, ModelContainer25.class);
		EntityFactory.registerEntity(Monster.class, Monster25.class);
		EntityFactory.registerEntity(Place.class, Place25.class);
		EntityFactory.registerEntity(Player.class, Player25.class);
		EntityFactory.registerEntity(SaveFile.class, SaveFile25.class);
		EntityFactory.registerEntity(SpawnArea.class, SpawnArea25.class);
	}
}
