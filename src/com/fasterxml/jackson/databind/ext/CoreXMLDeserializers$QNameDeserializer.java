package com.fasterxml.jackson.databind.ext;

import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.FromStringDeserializer;
import javax.xml.namespace.QName;

public class CoreXMLDeserializers$QNameDeserializer
  extends FromStringDeserializer<QName>
{
  public static final QNameDeserializer instance = new QNameDeserializer();
  private static final long serialVersionUID = 1L;
  
  public CoreXMLDeserializers$QNameDeserializer()
  {
    super(QName.class);
  }
  
  protected QName _deserialize(String paramString, DeserializationContext paramDeserializationContext)
    throws IllegalArgumentException
  {
    return QName.valueOf(paramString);
  }
}

/* Location:
 * Qualified Name:     com.fasterxml.jackson.databind.ext.CoreXMLDeserializers.QNameDeserializer
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */