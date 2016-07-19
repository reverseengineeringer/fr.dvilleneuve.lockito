package com.fasterxml.jackson.databind.deser.std;

import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import java.net.URL;

public class JdkDeserializers$URLDeserializer
  extends FromStringDeserializer<URL>
{
  public static final URLDeserializer instance = new URLDeserializer();
  
  public JdkDeserializers$URLDeserializer()
  {
    super(URL.class);
  }
  
  protected URL _deserialize(String paramString, DeserializationContext paramDeserializationContext)
    throws IOException
  {
    return new URL(paramString);
  }
}

/* Location:
 * Qualified Name:     com.fasterxml.jackson.databind.deser.std.JdkDeserializers.URLDeserializer
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */