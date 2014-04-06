/* -----------------------------------------------------------------------------
 * Displayer.java
 * -----------------------------------------------------------------------------
 *
 * Producer : com.parse2.aparse.Parser 2.3
 * Produced : Fri Jun 07 20:56:54 MUT 2013
 *
 * -----------------------------------------------------------------------------
 */

package com.litecoding.andorstrail.res.parser;

import java.util.ArrayList;

public class Displayer implements Visitor
{

  public Object visit(Rule_resource rule)
  {
    return visitRules(rule.rules);
  }

  public Object visit(Rule_resDeclaration rule)
  {
    return visitRules(rule.rules);
  }

  public Object visit(Rule_prototype rule)
  {
    return visitRules(rule.rules);
  }

  public Object visit(Rule_protoVal rule)
  {
    return visitRules(rule.rules);
  }

  public Object visit(Rule_protoArr rule)
  {
    return visitRules(rule.rules);
  }

  public Object visit(Rule_qualifier rule)
  {
    return visitRules(rule.rules);
  }

  public Object visit(Rule_resDefinition rule)
  {
    return visitRules(rule.rules);
  }

  public Object visit(Rule_definition rule)
  {
    return visitRules(rule.rules);
  }

  public Object visit(Rule_strVal rule)
  {
    return visitRules(rule.rules);
  }

  public Object visit(Rule_intVal rule)
  {
    return visitRules(rule.rules);
  }

  public Object visit(Rule_arrVal rule)
  {
    return visitRules(rule.rules);
  }

  public Object visit(Rule_emptyVal rule)
  {
    return visitRules(rule.rules);
  }

  public Object visit(Rule_HTAB rule)
  {
    return visitRules(rule.rules);
  }

  public Object visit(Rule_CR rule)
  {
    return visitRules(rule.rules);
  }

  public Object visit(Rule_LF rule)
  {
    return visitRules(rule.rules);
  }

  public Object visit(Rule_SP rule)
  {
    return visitRules(rule.rules);
  }

  public Object visit(Rule_CRLF rule)
  {
    return visitRules(rule.rules);
  }

  public Object visit(Rule_QUOT rule)
  {
    return visitRules(rule.rules);
  }

  public Object visit(Rule_HASH rule)
  {
    return visitRules(rule.rules);
  }

  public Object visit(Rule_COMMA rule)
  {
    return visitRules(rule.rules);
  }

  public Object visit(Rule_DOT rule)
  {
    return visitRules(rule.rules);
  }

  public Object visit(Rule_COLON rule)
  {
    return visitRules(rule.rules);
  }

  public Object visit(Rule_SEMICOLON rule)
  {
    return visitRules(rule.rules);
  }

  public Object visit(Rule_EQ rule)
  {
    return visitRules(rule.rules);
  }

  public Object visit(Rule_UNDERSCORE rule)
  {
    return visitRules(rule.rules);
  }

  public Object visit(Rule_PIPE rule)
  {
    return visitRules(rule.rules);
  }

  public Object visit(Rule_SQR_BRACKET_L rule)
  {
    return visitRules(rule.rules);
  }

  public Object visit(Rule_SQR_BRACKET_R rule)
  {
    return visitRules(rule.rules);
  }

  public Object visit(Rule_CRL_BRACKET_L rule)
  {
    return visitRules(rule.rules);
  }

  public Object visit(Rule_CRL_BRACKET_R rule)
  {
    return visitRules(rule.rules);
  }

  public Object visit(Rule_BRACKET_L rule)
  {
    return visitRules(rule.rules);
  }

  public Object visit(Rule_BRACKET_R rule)
  {
    return visitRules(rule.rules);
  }

  public Object visit(Rule_ALPHA rule)
  {
    return visitRules(rule.rules);
  }

  public Object visit(Rule_DIGIT rule)
  {
    return visitRules(rule.rules);
  }

  public Object visit(Rule_HEXDIG rule)
  {
    return visitRules(rule.rules);
  }

  public Object visit(Rule_VCHAR rule)
  {
    return visitRules(rule.rules);
  }

  public Object visit(ExtRule_U_L rule)
  {
    System.out.print(rule.spelling);
    return null;
  }

  public Object visit(Terminal_StringValue value)
  {
    System.out.print(value.spelling);
    return null;
  }

  public Object visit(Terminal_NumericValue value)
  {
    System.out.print(value.spelling);
    return null;
  }

  private Object visitRules(ArrayList<Rule> rules)
  {
    for (Rule rule : rules)
      rule.accept(this);
    return null;
  }
}

/* -----------------------------------------------------------------------------
 * eof
 * -----------------------------------------------------------------------------
 */
