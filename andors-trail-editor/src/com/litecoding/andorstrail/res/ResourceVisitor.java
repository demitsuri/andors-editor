package com.litecoding.andorstrail.res;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import com.litecoding.andorstrail.res.entity.DataEntity;
import com.litecoding.andorstrail.res.entity.DataEntity.NamedSchemePart;
import com.litecoding.andorstrail.res.entity.DataEntityFactory;
import com.litecoding.andorstrail.res.parser.Rule;
import com.litecoding.andorstrail.res.parser.Rule_arrVal;
import com.litecoding.andorstrail.res.parser.Rule_definition;
import com.litecoding.andorstrail.res.parser.Rule_emptyVal;
import com.litecoding.andorstrail.res.parser.Rule_intVal;
import com.litecoding.andorstrail.res.parser.Rule_protoArr;
import com.litecoding.andorstrail.res.parser.Rule_protoVal;
import com.litecoding.andorstrail.res.parser.Rule_prototype;
import com.litecoding.andorstrail.res.parser.Rule_qualifier;
import com.litecoding.andorstrail.res.parser.Rule_resDeclaration;
import com.litecoding.andorstrail.res.parser.Rule_resDefinition;
import com.litecoding.andorstrail.res.parser.Rule_resource;
import com.litecoding.andorstrail.res.parser.Rule_strVal;

public class ResourceVisitor extends BasicResourceVisitor {
	private int mResouceType = DataEntityFactory.TYPE_UNKNOWN;
	
	public ResourceVisitor() {
		this(DataEntityFactory.TYPE_UNKNOWN);
	}
	
	public ResourceVisitor(int resourceType) {
		mResouceType = resourceType;
	}
	
	public int getResourceType() {
		return mResouceType;
	}
	
	public void setResourceType(int resourceType) {
		mResouceType = resourceType;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<DataEntity> visit(Rule_resource rule) {
		List<Object> scheme = null;
		List<DataEntity> entities = new LinkedList<DataEntity>();
		
		for(Rule nestedRule: rule.rules) {
			if(nestedRule instanceof Rule_resDeclaration) {
				scheme = (List<Object>) nestedRule.accept(this);
			} else if(nestedRule instanceof Rule_resDefinition) {
				if(scheme != null) {
					List<Object> data = (List<Object>) nestedRule.accept(this);
					DataEntity entity = DataEntityFactory.create(mResouceType, scheme, data);
					entities.add(entity);
				}
			}
			nestedRule.accept(this);
		}
		
		return entities;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Object visit(Rule_resDeclaration rule) {
		List<Object> scheme = null;
		
		for(Rule nestedRule: rule.rules) {
			if(nestedRule instanceof Rule_prototype) {
				scheme = (List<Object>) nestedRule.accept(this);
			}
		}
		
		return scheme;
	}

	@Override
	public Object visit(Rule_prototype rule) {
		List<Object> scheme = new ArrayList<Object>();
		
		for(Rule nestedRule: rule.rules) {
			if(nestedRule instanceof Rule_protoVal || 
				nestedRule instanceof Rule_protoArr) {
				scheme.add(nestedRule.accept(this));
			} 
		}
		
		return Collections.unmodifiableList(scheme);
	}

	@Override
	public Object visit(Rule_protoVal rule) {
		return rule.spelling;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Object visit(Rule_protoArr rule) {
		String name = null;
		List<Object> subScheme = null;
		
		for(Rule nestedRule: rule.rules) {
			if(nestedRule instanceof Rule_qualifier) {
				name = (String) nestedRule.accept(this);
			} else if(nestedRule instanceof Rule_prototype) {
				subScheme = (List<Object>) nestedRule.accept(this);
			}
		}
		
		return new NamedSchemePart(name, subScheme);
	}

	@Override
	public Object visit(Rule_qualifier rule) {
		return rule.spelling;
	}

	@Override
	public Object visit(Rule_resDefinition rule) {
		Object result = null;
		
		for(Rule nestedRule: rule.rules) {
			if(nestedRule instanceof Rule_definition) {
				result = nestedRule.accept(this);
			}
		}
		
		return result;
	}

	@Override
	public Object visit(Rule_definition rule) {
		List<Object> data = new ArrayList<Object>();
		
		for(Rule nestedRule: rule.rules) {
			if(nestedRule instanceof Rule_strVal || 
				nestedRule instanceof Rule_intVal ||
				nestedRule instanceof Rule_arrVal || 
				nestedRule instanceof Rule_emptyVal) {
				data.add(nestedRule.accept(this));
			}
		}
		
		return Collections.unmodifiableList(data);
	}

	@Override
	public Object visit(Rule_strVal rule) {
		return rule.spelling;
	}

	@Override
	public Object visit(Rule_intVal rule) {
		return Integer.parseInt(rule.spelling);
	}

	@Override
	public Object visit(Rule_arrVal rule) {
		Object result = null;
		
		for(Rule nestedRule: rule.rules) {
			if(nestedRule instanceof Rule_definition) {
				result = nestedRule.accept(this);
			}
		}
		
		return result;
	}

	@Override
	public Object visit(Rule_emptyVal rule) {
		//empty value is null
		return null;
	}
}
