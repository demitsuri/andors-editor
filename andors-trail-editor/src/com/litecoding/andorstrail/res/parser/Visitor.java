/* -----------------------------------------------------------------------------
 * Visitor.java
 * -----------------------------------------------------------------------------
 *
 * Producer : com.parse2.aparse.Parser 2.3
 * Produced : Mon Jun 03 09:53:42 MUT 2013
 *
 * -----------------------------------------------------------------------------
 */

package com.litecoding.andorstrail.res.parser;

public interface Visitor
{
  public Object visit(Rule_resource rule);
  public Object visit(Rule_resDeclaration rule);
  public Object visit(Rule_prototype rule);
  public Object visit(Rule_protoVal rule);
  public Object visit(Rule_protoArr rule);
  public Object visit(Rule_qualifier rule);
  public Object visit(Rule_resDefinition rule);
  public Object visit(Rule_definition rule);
  public Object visit(Rule_strVal rule);
  public Object visit(Rule_intVal rule);
  public Object visit(Rule_arrVal rule);
  public Object visit(Rule_emptyVal rule);
  public Object visit(Rule_HTAB rule);
  public Object visit(Rule_CR rule);
  public Object visit(Rule_LF rule);
  public Object visit(Rule_SP rule);
  public Object visit(Rule_CRLF rule);
  public Object visit(Rule_QUOT rule);
  public Object visit(Rule_HASH rule);
  public Object visit(Rule_COMMA rule);
  public Object visit(Rule_DOT rule);
  public Object visit(Rule_COLON rule);
  public Object visit(Rule_SEMICOLON rule);
  public Object visit(Rule_EQ rule);
  public Object visit(Rule_UNDERSCORE rule);
  public Object visit(Rule_PIPE rule);
  public Object visit(Rule_SQR_BRACKET_L rule);
  public Object visit(Rule_SQR_BRACKET_R rule);
  public Object visit(Rule_CRL_BRACKET_L rule);
  public Object visit(Rule_CRL_BRACKET_R rule);
  public Object visit(Rule_BRACKET_L rule);
  public Object visit(Rule_BRACKET_R rule);
  public Object visit(Rule_ALPHA rule);
  public Object visit(Rule_DIGIT rule);
  public Object visit(Rule_HEXDIG rule);
  public Object visit(Rule_VCHAR rule);

  public Object visit(Terminal_StringValue value);
  public Object visit(Terminal_NumericValue value);
}

/* -----------------------------------------------------------------------------
 * eof
 * -----------------------------------------------------------------------------
 */
