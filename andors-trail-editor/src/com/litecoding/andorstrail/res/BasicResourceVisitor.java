package com.litecoding.andorstrail.res;

import com.litecoding.andorstrail.res.parser.*;

public abstract class BasicResourceVisitor implements Visitor {

	@Override
	public Object visit(Rule_HTAB rule) {
		return rule.spelling;
	}

	@Override
	public Object visit(Rule_CR rule) {
		return rule.spelling;
	}

	@Override
	public Object visit(Rule_LF rule) {
		return rule.spelling;
	}

	@Override
	public Object visit(Rule_SP rule) {
		return rule.spelling;
	}

	@Override
	public Object visit(Rule_CRLF rule) {
		return rule.spelling;
	}

	@Override
	public Object visit(Rule_QUOT rule) {
		return rule.spelling;
	}

	@Override
	public Object visit(Rule_HASH rule) {
		return rule.spelling;
	}

	@Override
	public Object visit(Rule_COMMA rule) {
		return rule.spelling;
	}

	@Override
	public Object visit(Rule_DOT rule) {
		return rule.spelling;
	}

	@Override
	public Object visit(Rule_COLON rule) {
		return rule.spelling;
	}

	@Override
	public Object visit(Rule_SEMICOLON rule) {
		return rule.spelling;
	}

	@Override
	public Object visit(Rule_EQ rule) {
		return rule.spelling;
	}

	@Override
	public Object visit(Rule_UNDERSCORE rule) {
		return rule.spelling;
	}

	@Override
	public Object visit(Rule_PIPE rule) {
		return rule.spelling;
	}

	@Override
	public Object visit(Rule_SQR_BRACKET_L rule) {
		return rule.spelling;
	}

	@Override
	public Object visit(Rule_SQR_BRACKET_R rule) {
		return rule.spelling;
	}

	@Override
	public Object visit(Rule_CRL_BRACKET_L rule) {
		return rule.spelling;
	}

	@Override
	public Object visit(Rule_CRL_BRACKET_R rule) {
		return rule.spelling;
	}

	@Override
	public Object visit(Rule_BRACKET_L rule) {
		return rule.spelling;
	}

	@Override
	public Object visit(Rule_BRACKET_R rule) {
		return rule.spelling;
	}

	@Override
	public Object visit(Rule_ALPHA rule) {
		return rule.spelling;
	}

	@Override
	public Object visit(Rule_DIGIT rule) {
		return rule.spelling;
	}

	@Override
	public Object visit(Rule_HEXDIG rule) {
		return rule.spelling;
	}

	@Override
	public Object visit(Rule_VCHAR rule) {
		return rule.spelling;
	}

	@Override
	public Object visit(Terminal_StringValue value) {
		return value.spelling;
	}

	@Override
	public Object visit(Terminal_NumericValue value) {
		return value.spelling;
	}

}
