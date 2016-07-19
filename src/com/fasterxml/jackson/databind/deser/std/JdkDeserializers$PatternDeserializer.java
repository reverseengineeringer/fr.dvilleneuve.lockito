package com.fasterxml.jackson.databind.deser.std;

import com.fasterxml.jackson.databind.DeserializationContext;
import java.util.regex.Pattern;

public class JdkDeserializers$PatternDeserializer
  extends FromStringDeserializer<Pattern>
{
  public static final PatternDeserializer instance = new PatternDeserializer();
  
  public JdkDeserializers$PatternDeserializer()
  {
    super(Pattern.class);
  }
  
  protected Pattern _deserialize(String paramString, DeserializationContext paramDeserializationContext)
    throws IllegalArgumentException
  {
    return Pattern.compile(paramString);
  }
}

/* Location:
 * Qualified Name:     com.fasterxml.jackson.databind.deser.std.JdkDeserializers.PatternDeserializer
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */