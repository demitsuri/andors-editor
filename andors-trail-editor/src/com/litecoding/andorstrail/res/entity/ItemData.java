package com.litecoding.andorstrail.res.entity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ItemData extends DataEntity {
	public final String id;
	public final String iconId;
	public final String name;
	public final String category;
	
	private static final List<Object> SCHEME_0_6_12;
	static {
		/*
		 * Repository contains resource scheme for v0.6.12, but released version doesn't.
		 * This code emulates scheme parsing for v0.6.12.
		 */
		List<Object> scheme = new ArrayList<Object>();
		List<Object> subScheme;
		
		scheme.add("displaytype");
		scheme.add("hasManualPrice");
		scheme.add("baseMarketCost");
		scheme.add("hasEquipEffect");
		scheme.add("equip_boostMaxHP");
		scheme.add("equip_boostMaxAP");
		scheme.add("equip_moveCostPenalty");
		scheme.add("equip_attackCost");
		scheme.add("equip_attackChance");
		scheme.add("equip_criticalChance");
		scheme.add("equip_criticalMultiplier");
		scheme.add("equip_attackDamage_Min");
		scheme.add("equip_attackDamage_Max");
		scheme.add("equip_blockChance");
		scheme.add("equip_damageResistance");
		
		subScheme = new ArrayList<Object>();
		subScheme.add("condition");
		subScheme.add("magnitude");
		scheme.add(new NamedSchemePart("equip_conditions", 
				Collections.unmodifiableList(subScheme)));
		
		scheme.add("hasUseEffect");
		scheme.add("use_boostHP_Min");
		scheme.add("use_boostHP_Max");
		scheme.add("use_boostAP_Min");
		scheme.add("use_boostAP_Max");
		
		subScheme = new ArrayList<Object>();
		subScheme.add("condition");
		subScheme.add("magnitude");
		subScheme.add("duration");
		subScheme.add("chance");
		scheme.add(new NamedSchemePart("use_conditionsSource", 
				Collections.unmodifiableList(subScheme)));
		
		scheme.add("hasHitEffect");
		scheme.add("hit_boostHP_Min");
		scheme.add("hit_boostHP_Max");
		scheme.add("hit_boostAP_Min");
		scheme.add("hit_boostAP_Max");
		
		subScheme = new ArrayList<Object>();
		subScheme.add("condition");
		subScheme.add("magnitude");
		subScheme.add("duration");
		subScheme.add("chance");
		scheme.add(new NamedSchemePart("hit_conditionsSource", 
				Collections.unmodifiableList(subScheme)));
		
		subScheme = new ArrayList<Object>();
		subScheme.add("condition");
		subScheme.add("magnitude");
		subScheme.add("duration");
		subScheme.add("chance");
		scheme.add(new NamedSchemePart("hit_conditionsTarget", 
				Collections.unmodifiableList(subScheme)));
		
		scheme.add("hasKillEffect");
		scheme.add("kill_boostHP_Min");
		scheme.add("kill_boostHP_Max");
		scheme.add("kill_boostAP_Min");
		scheme.add("kill_boostAP_Max");
		
		subScheme = new ArrayList<Object>();
		subScheme.add("condition");
		subScheme.add("magnitude");
		subScheme.add("duration");
		subScheme.add("chance");
		scheme.add(new NamedSchemePart("kill_conditionsSource", 
				Collections.unmodifiableList(subScheme)));
		
		SCHEME_0_6_12 = Collections.unmodifiableList(scheme);
	}
	
	public ItemData(List<Object> scheme, List<Object> data) {
		super(scheme == null ? SCHEME_0_6_12 : scheme, data);
		
		int schemeLen = mScheme.size();
		Map<String, Object> mapping = new HashMap<String, Object>();
		
		for(int i = 0; i < schemeLen; i++ ) {
			String key = (String)scheme.get(i);
			Object value = data.get(i);
			mapping.put(key, value);
		}
		
		id = (String)mapping.get("id");
		iconId = (String)mapping.get("iconID");
		name = (String)mapping.get("name");
		category = (String)mapping.get("category");
	}

}
