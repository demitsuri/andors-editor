package com.litecoding.andorstrail.res;

import com.litecoding.andorstrail.res.parser.ExtRule_U_L;

@Deprecated
public abstract class BasicUnicodeResourceVisitor extends BasicResourceVisitor {
	@Override
	public Object visit(ExtRule_U_L rule) {
		return rule.spelling;
	}

}
