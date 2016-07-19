package com.fasterxml.jackson.databind.deser.std;

import com.fasterxml.jackson.databind.DeserializationContext;
import java.net.URI;

public class JdkDeserializers$URIDeserializer
  extends FromStringDeserializer<URI>
{
  public static final URIDeserializer instance = new URIDeserializer();
  
  public JdkDeserializers$URIDeserializer()
  {
    super(URI.class);
  }
  
  protected URI _deserialize(String paramString, DeserializationContext paramDeserializationContext)
    throws IllegalArgumentException
  {
    return URI.create(paramString);
  }
}

/* Location:
 * Qualified Name:     com.fasterxml.jackson.databind.deser.std.JdkDeserializers.URIDeserializer
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */