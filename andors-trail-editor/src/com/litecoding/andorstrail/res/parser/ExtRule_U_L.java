package com.litecoding.andorstrail.res.parser;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class ExtRule_U_L extends Rule {
	private static final Pattern PATTERN_U_L = Pattern.compile("^(?u)\\p{IsL}");
	
	public static ExtRule_U_L parse(ParserContext context)
	  {
	    /*
	     *  Let aparse know which rule has been called. 
	     */
	    context.push("ExtRule_U_L");
	    
	    ExtRule_U_L rule = null; 

	    try
	    {
	    	String str = context.text.substring(context.index);
	    	Matcher matcher = PATTERN_U_L.matcher(str);
	    	if(matcher.find()) {
	    		rule = 
	    	            new ExtRule_U_L(
	    	                context.text.substring(
	    	                    context.index, 
	    	                    context.index + matcher.end()), 
	    	                null);
	    		
	    		context.index += matcher.end() - matcher.start();
	    	}
	    }
	    catch (StringIndexOutOfBoundsException e) {}

	    /*
	     * Let aparse know whether or not the parse 
	     * was successful.
	     */
	    context.pop("ExtRule_U_L", rule != null);

	    /*
	     * Return an instance of this rule, or null
	     * if the parse failed, to the caller.
	     */
	    return rule;
	  }
	  
	  /**
	   * A private constructor that is used by the parse method.
	   */
	  private ExtRule_U_L(String spelling, ArrayList<Rule> rules)
	  {
	    super(spelling, rules);
	  }

	  /**
	   * The visitor accept method that passes this length prefixed  
	   * string to the specified visitor.
	   */
	  public Object accept(Visitor visitor) {
		  return visitor.visit(this);
	  }

}

