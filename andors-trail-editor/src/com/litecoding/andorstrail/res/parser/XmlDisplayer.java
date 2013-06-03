/* -----------------------------------------------------------------------------
 * XmlDisplayer.java
 * -----------------------------------------------------------------------------
 *
 * Producer : com.parse2.aparse.Parser 2.3
 * Produced : Mon Jun 03 09:53:42 MUT 2013
 *
 * -----------------------------------------------------------------------------
 */

package com.litecoding.andorstrail.res.parser;

import java.util.ArrayList;

public class XmlDisplayer implements Visitor
{
  private boolean terminal = true;

  public Object visit(Rule_resource rule)
  {
    if (!terminal) System.out.println();
    System.out.print("<resource>");
    terminal = false;
    visitRules(rule.rules);
    if (!terminal) System.out.println();
    System.out.print("</resource>");
    terminal = false;
    return null;
  }

  public Object visit(Rule_resDeclaration rule)
  {
    if (!terminal) System.out.println();
    System.out.print("<resDeclaration>");
    terminal = false;
    visitRules(rule.rules);
    if (!terminal) System.out.println();
    System.out.print("</resDeclaration>");
    terminal = false;
    return null;
  }

  public Object visit(Rule_prototype rule)
  {
    if (!terminal) System.out.println();
    System.out.print("<prototype>");
    terminal = false;
    visitRules(rule.rules);
    if (!terminal) System.out.println();
    System.out.print("</prototype>");
    terminal = false;
    return null;
  }

  public Object visit(Rule_protoVal rule)
  {
    if (!terminal) System.out.println();
    System.out.print("<protoVal>");
    terminal = false;
    visitRules(rule.rules);
    if (!terminal) System.out.println();
    System.out.print("</protoVal>");
    terminal = false;
    return null;
  }

  public Object visit(Rule_protoArr rule)
  {
    if (!terminal) System.out.println();
    System.out.print("<protoArr>");
    terminal = false;
    visitRules(rule.rules);
    if (!terminal) System.out.println();
    System.out.print("</protoArr>");
    terminal = false;
    return null;
  }

  public Object visit(Rule_qualifier rule)
  {
    if (!terminal) System.out.println();
    System.out.print("<qualifier>");
    terminal = false;
    visitRules(rule.rules);
    if (!terminal) System.out.println();
    System.out.print("</qualifier>");
    terminal = false;
    return null;
  }

  public Object visit(Rule_resDefinition rule)
  {
    if (!terminal) System.out.println();
    System.out.print("<resDefinition>");
    terminal = false;
    visitRules(rule.rules);
    if (!terminal) System.out.println();
    System.out.print("</resDefinition>");
    terminal = false;
    return null;
  }

  public Object visit(Rule_definition rule)
  {
    if (!terminal) System.out.println();
    System.out.print("<definition>");
    terminal = false;
    visitRules(rule.rules);
    if (!terminal) System.out.println();
    System.out.print("</definition>");
    terminal = false;
    return null;
  }

  public Object visit(Rule_strVal rule)
  {
    if (!terminal) System.out.println();
    System.out.print("<strVal>");
    terminal = false;
    visitRules(rule.rules);
    if (!terminal) System.out.println();
    System.out.print("</strVal>");
    terminal = false;
    return null;
  }

  public Object visit(Rule_intVal rule)
  {
    if (!terminal) System.out.println();
    System.out.print("<intVal>");
    terminal = false;
    visitRules(rule.rules);
    if (!terminal) System.out.println();
    System.out.print("</intVal>");
    terminal = false;
    return null;
  }

  public Object visit(Rule_arrVal rule)
  {
    if (!terminal) System.out.println();
    System.out.print("<arrVal>");
    terminal = false;
    visitRules(rule.rules);
    if (!terminal) System.out.println();
    System.out.print("</arrVal>");
    terminal = false;
    return null;
  }

  public Object visit(Rule_emptyVal rule)
  {
    if (!terminal) System.out.println();
    System.out.print("<emptyVal>");
    terminal = false;
    visitRules(rule.rules);
    if (!terminal) System.out.println();
    System.out.print("</emptyVal>");
    terminal = false;
    return null;
  }

  public Object visit(Rule_HTAB rule)
  {
    if (!terminal) System.out.println();
    System.out.print("<HTAB>");
    terminal = false;
    visitRules(rule.rules);
    if (!terminal) System.out.println();
    System.out.print("</HTAB>");
    terminal = false;
    return null;
  }

  public Object visit(Rule_CR rule)
  {
    if (!terminal) System.out.println();
    System.out.print("<CR>");
    terminal = false;
    visitRules(rule.rules);
    if (!terminal) System.out.println();
    System.out.print("</CR>");
    terminal = false;
    return null;
  }

  public Object visit(Rule_LF rule)
  {
    if (!terminal) System.out.println();
    System.out.print("<LF>");
    terminal = false;
    visitRules(rule.rules);
    if (!terminal) System.out.println();
    System.out.print("</LF>");
    terminal = false;
    return null;
  }

  public Object visit(Rule_SP rule)
  {
    if (!terminal) System.out.println();
    System.out.print("<SP>");
    terminal = false;
    visitRules(rule.rules);
    if (!terminal) System.out.println();
    System.out.print("</SP>");
    terminal = false;
    return null;
  }

  public Object visit(Rule_CRLF rule)
  {
    if (!terminal) System.out.println();
    System.out.print("<CRLF>");
    terminal = false;
    visitRules(rule.rules);
    if (!terminal) System.out.println();
    System.out.print("</CRLF>");
    terminal = false;
    return null;
  }

  public Object visit(Rule_QUOT rule)
  {
    if (!terminal) System.out.println();
    System.out.print("<QUOT>");
    terminal = false;
    visitRules(rule.rules);
    if (!terminal) System.out.println();
    System.out.print("</QUOT>");
    terminal = false;
    return null;
  }

  public Object visit(Rule_HASH rule)
  {
    if (!terminal) System.out.println();
    System.out.print("<HASH>");
    terminal = false;
    visitRules(rule.rules);
    if (!terminal) System.out.println();
    System.out.print("</HASH>");
    terminal = false;
    return null;
  }

  public Object visit(Rule_COMMA rule)
  {
    if (!terminal) System.out.println();
    System.out.print("<COMMA>");
    terminal = false;
    visitRules(rule.rules);
    if (!terminal) System.out.println();
    System.out.print("</COMMA>");
    terminal = false;
    return null;
  }

  public Object visit(Rule_DOT rule)
  {
    if (!terminal) System.out.println();
    System.out.print("<DOT>");
    terminal = false;
    visitRules(rule.rules);
    if (!terminal) System.out.println();
    System.out.print("</DOT>");
    terminal = false;
    return null;
  }

  public Object visit(Rule_COLON rule)
  {
    if (!terminal) System.out.println();
    System.out.print("<COLON>");
    terminal = false;
    visitRules(rule.rules);
    if (!terminal) System.out.println();
    System.out.print("</COLON>");
    terminal = false;
    return null;
  }

  public Object visit(Rule_SEMICOLON rule)
  {
    if (!terminal) System.out.println();
    System.out.print("<SEMICOLON>");
    terminal = false;
    visitRules(rule.rules);
    if (!terminal) System.out.println();
    System.out.print("</SEMICOLON>");
    terminal = false;
    return null;
  }

  public Object visit(Rule_EQ rule)
  {
    if (!terminal) System.out.println();
    System.out.print("<EQ>");
    terminal = false;
    visitRules(rule.rules);
    if (!terminal) System.out.println();
    System.out.print("</EQ>");
    terminal = false;
    return null;
  }

  public Object visit(Rule_UNDERSCORE rule)
  {
    if (!terminal) System.out.println();
    System.out.print("<UNDERSCORE>");
    terminal = false;
    visitRules(rule.rules);
    if (!terminal) System.out.println();
    System.out.print("</UNDERSCORE>");
    terminal = false;
    return null;
  }

  public Object visit(Rule_PIPE rule)
  {
    if (!terminal) System.out.println();
    System.out.print("<PIPE>");
    terminal = false;
    visitRules(rule.rules);
    if (!terminal) System.out.println();
    System.out.print("</PIPE>");
    terminal = false;
    return null;
  }

  public Object visit(Rule_SQR_BRACKET_L rule)
  {
    if (!terminal) System.out.println();
    System.out.print("<SQR_BRACKET_L>");
    terminal = false;
    visitRules(rule.rules);
    if (!terminal) System.out.println();
    System.out.print("</SQR_BRACKET_L>");
    terminal = false;
    return null;
  }

  public Object visit(Rule_SQR_BRACKET_R rule)
  {
    if (!terminal) System.out.println();
    System.out.print("<SQR_BRACKET_R>");
    terminal = false;
    visitRules(rule.rules);
    if (!terminal) System.out.println();
    System.out.print("</SQR_BRACKET_R>");
    terminal = false;
    return null;
  }

  public Object visit(Rule_CRL_BRACKET_L rule)
  {
    if (!terminal) System.out.println();
    System.out.print("<CRL_BRACKET_L>");
    terminal = false;
    visitRules(rule.rules);
    if (!terminal) System.out.println();
    System.out.print("</CRL_BRACKET_L>");
    terminal = false;
    return null;
  }

  public Object visit(Rule_CRL_BRACKET_R rule)
  {
    if (!terminal) System.out.println();
    System.out.print("<CRL_BRACKET_R>");
    terminal = false;
    visitRules(rule.rules);
    if (!terminal) System.out.println();
    System.out.print("</CRL_BRACKET_R>");
    terminal = false;
    return null;
  }

  public Object visit(Rule_BRACKET_L rule)
  {
    if (!terminal) System.out.println();
    System.out.print("<BRACKET_L>");
    terminal = false;
    visitRules(rule.rules);
    if (!terminal) System.out.println();
    System.out.print("</BRACKET_L>");
    terminal = false;
    return null;
  }

  public Object visit(Rule_BRACKET_R rule)
  {
    if (!terminal) System.out.println();
    System.out.print("<BRACKET_R>");
    terminal = false;
    visitRules(rule.rules);
    if (!terminal) System.out.println();
    System.out.print("</BRACKET_R>");
    terminal = false;
    return null;
  }

  public Object visit(Rule_ALPHA rule)
  {
    if (!terminal) System.out.println();
    System.out.print("<ALPHA>");
    terminal = false;
    visitRules(rule.rules);
    if (!terminal) System.out.println();
    System.out.print("</ALPHA>");
    terminal = false;
    return null;
  }

  public Object visit(Rule_DIGIT rule)
  {
    if (!terminal) System.out.println();
    System.out.print("<DIGIT>");
    terminal = false;
    visitRules(rule.rules);
    if (!terminal) System.out.println();
    System.out.print("</DIGIT>");
    terminal = false;
    return null;
  }

  public Object visit(Rule_HEXDIG rule)
  {
    if (!terminal) System.out.println();
    System.out.print("<HEXDIG>");
    terminal = false;
    visitRules(rule.rules);
    if (!terminal) System.out.println();
    System.out.print("</HEXDIG>");
    terminal = false;
    return null;
  }

  public Object visit(Rule_VCHAR rule)
  {
    if (!terminal) System.out.println();
    System.out.print("<VCHAR>");
    terminal = false;
    visitRules(rule.rules);
    if (!terminal) System.out.println();
    System.out.print("</VCHAR>");
    terminal = false;
    return null;
  }

  public Object visit(Terminal_StringValue value)
  {
    System.out.print(value.spelling);
    terminal = true;
    return null;
  }

  public Object visit(Terminal_NumericValue value)
  {
    System.out.print(value.spelling);
    terminal = true;
    return null;
  }

  private Boolean visitRules(ArrayList<Rule> rules)
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
