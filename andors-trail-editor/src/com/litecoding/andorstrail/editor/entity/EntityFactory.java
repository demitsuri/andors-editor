package com.litecoding.andorstrail.editor.entity;

import java.io.DataInputStream;
import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import android.util.Log;

import com.litecoding.andorstrail.editor.Main;
import com.litecoding.andorstrail.editor.entity.annotation.Version;
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

public class EntityFactory {
	private static Map<Class<?>, List<Class<?>>> classes = new HashMap<Class<?>, List<Class<?>>>();

	static {
		com.litecoding.andorstrail.editor.entity.v25.Registrator.register();
		com.litecoding.andorstrail.editor.entity.v26.Registrator.register();
	}
	
	public static void registerEntity(Class<?> baseClass, Class<?> entityClass) {
		synchronized (classes) {
			if(!classes.containsKey(baseClass))
				classes.put(baseClass, new LinkedList<Class<?>>());
			
			List<Class<?>> list = classes.get(baseClass);
			if(!list.contains(entityClass))
				list.add(entityClass);
		}
	}
	
	private static Object create(Class<?> baseClass, int version, DataInputStream dis) {
		Object retVal = null;
		synchronized (classes) {
			List<Class<?>> list = classes.get(baseClass);
			int maxAppliedVersion = 0;
			Class<?> classCandidate = null;
			for(Class<?> tmp : list) {
				Version ver = tmp.getAnnotation(Version.class);
				if(ver.ver() > maxAppliedVersion && ver.ver() <= version) {
					maxAppliedVersion = ver.ver();
					classCandidate = tmp;
				}
			}
			
			try{
				Constructor<?> constructor = classCandidate.getConstructor(int.class, DataInputStream.class);
				retVal = constructor.newInstance(version, dis);
			} catch(Exception e) {
				Log.e(Main.TAG, "Error while instantiating entity", e);
			}
		}
		
		return retVal;
	}
	
	public static Actor createActor(int version, DataInputStream dis, boolean isPlayer) {
		Actor retVal = null;
		synchronized (classes) {
			List<Class<?>> list = classes.get(Actor.class);
			int maxAppliedVersion = 0;
			Class<?> classCandidate = null;
			for(Class<?> tmp : list) {
				Version ver = tmp.getAnnotation(Version.class);
				if(ver.ver() > maxAppliedVersion && ver.ver() <= version) {
					maxAppliedVersion = ver.ver();
					classCandidate = tmp;
				}
			}
			
			try{
				Constructor<?> constructor = classCandidate.getConstructor(int.class, DataInputStream.class, boolean.class);
				retVal = (Actor)constructor.newInstance(version, dis, isPlayer);
			} catch(Exception e) {
				Log.e(Main.TAG, "Error while instantiating actor", e);
			}
		}
		
		return retVal;
	}
	
	public static ActorConditions createActorConditions(int version, DataInputStream dis) {
		return (ActorConditions)create(ActorConditions.class, version, dis);
	}
	
	public static ActorTraits createActorTraits(int version, DataInputStream dis) {
		return (ActorTraits)create(ActorTraits.class, version, dis);
	}
	
	public static CombatTraits createCombatTraits(int version, DataInputStream dis) {
		return (CombatTraits)create(CombatTraits.class, version, dis);
	}
	
	public static FileHeader createFileHeader(int version, DataInputStream dis) {
		return (FileHeader)create(FileHeader.class, version, dis);
	}
	
	public static GameStatistics createGameStatistics(int version, DataInputStream dis) {
		return (GameStatistics)create(GameStatistics.class, version, dis);
	}
	
	public static InterfaceData createInterfaceData(int version, DataInputStream dis) {
		return (InterfaceData)create(InterfaceData.class, version, dis);
	}
	
	public static Inventory createInventory(int version, DataInputStream dis) {
		return (Inventory)create(Inventory.class, version, dis);
	}
	
	public static ItemContainer createItemContainer(int version, DataInputStream dis) {
		return (ItemContainer)create(ItemContainer.class, version, dis);
	}
	
	public static Loot createLoot(int version, DataInputStream dis) {
		return (Loot)create(Loot.class, version, dis);
	}
	
	public static MapsContainer createMapsContainer(int version, DataInputStream dis) {
		return (MapsContainer)create(MapsContainer.class, version, dis);
	}
	
	public static ModelContainer createModelContainer(int version, DataInputStream dis) {
		return (ModelContainer)create(ModelContainer.class, version, dis);
	}
	
	public static Monster createMonster(int version, DataInputStream dis) {
		return (Monster)create(Monster.class, version, dis);
	}
	
	public static Place createPlace(int version, DataInputStream dis) {
		return (Place)create(Place.class, version, dis);
	}
	
	public static Player createPlayer(int version, DataInputStream dis) {
		return (Player)create(Player.class, version, dis);
	}
	
	public static SaveFile createSaveFile(int version, DataInputStream dis) {
		return (SaveFile)create(SaveFile.class, version, dis);
	}
	
	public static SpawnArea createSpawnArea(int version, DataInputStream dis) {
		return (SpawnArea)create(SpawnArea.class, version, dis);
	}
}
