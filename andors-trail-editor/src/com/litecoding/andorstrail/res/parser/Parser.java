/* -----------------------------------------------------------------------------
 * Parser.java
 * -----------------------------------------------------------------------------
 *
 * Producer : com.parse2.aparse.Parser 2.3
 * Produced : Mon Jun 03 09:53:42 MUT 2013
 *
 * -----------------------------------------------------------------------------
 */

package com.litecoding.andorstrail.res.parser;

import java.util.Stack;
import java.util.Properties;
import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.IOException;

public class Parser
{
  private Parser() {}

  static public void main(String[] args)
  {
    Properties arguments = new Properties();
    String error = "";
    boolean ok = args.length > 0;

    if (ok)
    {
      arguments.setProperty("Trace", "Off");
      arguments.setProperty("Rule", "resource");

      for (int i = 0; i < args.length; i++)
      {
        if (args[i].equals("-trace"))
          arguments.setProperty("Trace", "On");
        else if (args[i].equals("-visitor"))
          arguments.setProperty("Visitor", args[++i]);
        else if (args[i].equals("-file"))
          arguments.setProperty("File", args[++i]);
        else if (args[i].equals("-string"))
          arguments.setProperty("String", args[++i]);
        else if (args[i].equals("-rule"))
          arguments.setProperty("Rule", args[++i]);
        else
        {
          error = "unknown argument: " + args[i];
          ok = false;
        }
      }
    }

    if (ok)
    {
      if (arguments.getProperty("File") == null &&
          arguments.getProperty("String") == null)
      {
        error = "insufficient arguments: -file or -string required";
        ok = false;
      }
    }

    if (!ok)
    {
      System.out.println("error: " + error);
      System.out.println("usage: Parser [-rule rulename] [-trace] <-file file | -string string> [-visitor visitor]");
    }
    else
    {
      try
      {
        Rule rule = null;

        if (arguments.getProperty("File") != null)
        {
          rule = 
            parse(
              arguments.getProperty("Rule"), 
              new File(arguments.getProperty("File")), 
              arguments.getProperty("Trace").equals("On"));
        }
        else if (arguments.getProperty("String") != null)
        {
          rule = 
            parse(
              arguments.getProperty("Rule"), 
              arguments.getProperty("String"), 
              arguments.getProperty("Trace").equals("On"));
        }

        if (arguments.getProperty("Visitor") != null)
        {
          Visitor visitor = 
            (Visitor)Class.forName(arguments.getProperty("Visitor")).newInstance();
          rule.accept(visitor);
        }
      }
      catch (IllegalArgumentException e)
      {
        System.out.println("argument error: " + e.getMessage());
      }
      catch (IOException e)
      {
        System.out.println("io error: " + e.getMessage());
      }
      catch (ParserException e)
      {
        System.out.println("parser error: " + e.getMessage());
      }
      catch (ClassNotFoundException e)
      {
        System.out.println("visitor error: class not found - " + e.getMessage());
      }
      catch (IllegalAccessException e)
      {
        System.out.println("visitor error: illegal access - " + e.getMessage());
      }
      catch (InstantiationException e)
      {
        System.out.println("visitor error: instantiation failure - " + e.getMessage());
      }
    }
  }

  static public Rule parse(String rulename, String string)
  throws IllegalArgumentException,
         ParserException
  {
    return parse(rulename, string, false);
  }

  static public Rule parse(String rulename, InputStream in)
  throws IllegalArgumentException,
         IOException,
         ParserException
  {
    return parse(rulename, in, false);
  }

  static public Rule parse(String rulename, File file)
  throws IllegalArgumentException,
         IOException,
         ParserException
  {
    return parse(rulename, file, false);
  }

  static private Rule parse(String rulename, String string, boolean trace)
  throws IllegalArgumentException,
         ParserException
  {
    if (rulename == null)
      throw new IllegalArgumentException("null rulename");
    if (string == null)
      throw new IllegalArgumentException("null string");

    ParserContext context = new ParserContext(string, trace);

    Rule rule = null;
    if (rulename.equalsIgnoreCase("resource")) rule = Rule_resource.parse(context);
    else if (rulename.equalsIgnoreCase("resDeclaration")) rule = Rule_resDeclaration.parse(context);
    else if (rulename.equalsIgnoreCase("prototype")) rule = Rule_prototype.parse(context);
    else if (rulename.equalsIgnoreCase("protoVal")) rule = Rule_protoVal.parse(context);
    else if (rulename.equalsIgnoreCase("protoArr")) rule = Rule_protoArr.parse(context);
    else if (rulename.equalsIgnoreCase("qualifier")) rule = Rule_qualifier.parse(context);
    else if (rulename.equalsIgnoreCase("resDefinition")) rule = Rule_resDefinition.parse(context);
    else if (rulename.equalsIgnoreCase("definition")) rule = Rule_definition.parse(context);
    else if (rulename.equalsIgnoreCase("strVal")) rule = Rule_strVal.parse(context);
    else if (rulename.equalsIgnoreCase("intVal")) rule = Rule_intVal.parse(context);
    else if (rulename.equalsIgnoreCase("arrVal")) rule = Rule_arrVal.parse(context);
    else if (rulename.equalsIgnoreCase("emptyVal")) rule = Rule_emptyVal.parse(context);
    else if (rulename.equalsIgnoreCase("HTAB")) rule = Rule_HTAB.parse(context);
    else if (rulename.equalsIgnoreCase("CR")) rule = Rule_CR.parse(context);
    else if (rulename.equalsIgnoreCase("LF")) rule = Rule_LF.parse(context);
    else if (rulename.equalsIgnoreCase("SP")) rule = Rule_SP.parse(context);
    else if (rulename.equalsIgnoreCase("CRLF")) rule = Rule_CRLF.parse(context);
    else if (rulename.equalsIgnoreCase("QUOT")) rule = Rule_QUOT.parse(context);
    else if (rulename.equalsIgnoreCase("HASH")) rule = Rule_HASH.parse(context);
    else if (rulename.equalsIgnoreCase("COMMA")) rule = Rule_COMMA.parse(context);
    else if (rulename.equalsIgnoreCase("DOT")) rule = Rule_DOT.parse(context);
    else if (rulename.equalsIgnoreCase("COLON")) rule = Rule_COLON.parse(context);
    else if (rulename.equalsIgnoreCase("SEMICOLON")) rule = Rule_SEMICOLON.parse(context);
    else if (rulename.equalsIgnoreCase("EQ")) rule = Rule_EQ.parse(context);
    else if (rulename.equalsIgnoreCase("UNDERSCORE")) rule = Rule_UNDERSCORE.parse(context);
    else if (rulename.equalsIgnoreCase("PIPE")) rule = Rule_PIPE.parse(context);
    else if (rulename.equalsIgnoreCase("SQR_BRACKET_L")) rule = Rule_SQR_BRACKET_L.parse(context);
    else if (rulename.equalsIgnoreCase("SQR_BRACKET_R")) rule = Rule_SQR_BRACKET_R.parse(context);
    else if (rulename.equalsIgnoreCase("CRL_BRACKET_L")) rule = Rule_CRL_BRACKET_L.parse(context);
    else if (rulename.equalsIgnoreCase("CRL_BRACKET_R")) rule = Rule_CRL_BRACKET_R.parse(context);
    else if (rulename.equalsIgnoreCase("BRACKET_L")) rule = Rule_BRACKET_L.parse(context);
    else if (rulename.equalsIgnoreCase("BRACKET_R")) rule = Rule_BRACKET_R.parse(context);
    else if (rulename.equalsIgnoreCase("ALPHA")) rule = Rule_ALPHA.parse(context);
    else if (rulename.equalsIgnoreCase("DIGIT")) rule = Rule_DIGIT.parse(context);
    else if (rulename.equalsIgnoreCase("HEXDIG")) rule = Rule_HEXDIG.parse(context);
    else if (rulename.equalsIgnoreCase("VCHAR")) rule = Rule_VCHAR.parse(context);
    else throw new IllegalArgumentException("unknown rule");

    if (rule == null)
    {
      throw new ParserException(
        "rule \"" + (String)context.getErrorStack().peek() + "\" failed",
        context.text,
        context.getErrorIndex(),
        context.getErrorStack());
    }

    if (context.text.length() > context.index)
    {
      ParserException primaryError = 
        new ParserException(
          "extra data found",
          context.text,
          context.index,
          new Stack<String>());

      if (context.getErrorIndex() > context.index)
      {
        ParserException secondaryError = 
          new ParserException(
            "rule \"" + (String)context.getErrorStack().peek() + "\" failed",
            context.text,
            context.getErrorIndex(),
            context.getErrorStack());

        primaryError.initCause(secondaryError);
      }

      throw primaryError;
    }

    return rule;
  }

  static private Rule parse(String rulename, InputStream in, boolean trace)
  throws IllegalArgumentException,
         IOException,
         ParserException
  {
    if (rulename == null)
      throw new IllegalArgumentException("null rulename");
    if (in == null)
      throw new IllegalArgumentException("null input stream");

    int ch = 0;
    StringBuffer out = new StringBuffer();
    while ((ch = in.read()) != -1)
      out.append((char)ch);

    return parse(rulename, out.toString(), trace);
  }

  static private Rule parse(String rulename, File file, boolean trace)
  throws IllegalArgumentException,
         IOException,
         ParserException
  {
    if (rulename == null)
      throw new IllegalArgumentException("null rulename");
    if (file == null)
      throw new IllegalArgumentException("null file");

    BufferedReader in = new BufferedReader(new FileReader(file));
    int ch = 0;
    StringBuffer out = new StringBuffer();
    while ((ch = in.read()) != -1)
      out.append((char)ch);

    in.close();

    return parse(rulename, out.toString(), trace);
  }
}

/* -----------------------------------------------------------------------------
 * eof
 * -----------------------------------------------------------------------------
 */
