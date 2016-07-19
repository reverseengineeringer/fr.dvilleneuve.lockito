package com.fasterxml.jackson.databind.ext;

import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.FromStringDeserializer;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.Duration;

public class CoreXMLDeserializers$DurationDeserializer
  extends FromStringDeserializer<Duration>
{
  public static final DurationDeserializer instance = new DurationDeserializer();
  private static final long serialVersionUID = 1L;
  
  public CoreXMLDeserializers$DurationDeserializer()
  {
    super(Duration.class);
  }
  
  protected Duration _deserialize(String paramString, DeserializationContext paramDeserializationContext)
    throws IllegalArgumentException
  {
    return CoreXMLDeserializers._dataTypeFactory.newDuration(paramString);
  }
}

/* Location:
 * Qualified Name:     com.fasterxml.jackson.databind.ext.CoreXMLDeserializers.DurationDeserializer
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */