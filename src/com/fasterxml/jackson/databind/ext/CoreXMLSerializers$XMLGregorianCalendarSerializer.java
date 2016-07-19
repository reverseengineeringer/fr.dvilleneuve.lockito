package com.fasterxml.jackson.databind.ext;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonFormatVisitorWrapper;
import com.fasterxml.jackson.databind.ser.std.CalendarSerializer;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import java.io.IOException;
import java.lang.reflect.Type;
import javax.xml.datatype.XMLGregorianCalendar;

public class CoreXMLSerializers$XMLGregorianCalendarSerializer
  extends StdSerializer<XMLGregorianCalendar>
{
  public static final XMLGregorianCalendarSerializer instance = new XMLGregorianCalendarSerializer();
  
  public CoreXMLSerializers$XMLGregorianCalendarSerializer()
  {
    super(XMLGregorianCalendar.class);
  }
  
  public void acceptJsonFormatVisitor(JsonFormatVisitorWrapper paramJsonFormatVisitorWrapper, JavaType paramJavaType)
    throws JsonMappingException
  {
    CalendarSerializer.instance.acceptJsonFormatVisitor(paramJsonFormatVisitorWrapper, null);
  }
  
  public JsonNode getSchema(SerializerProvider paramSerializerProvider, Type paramType)
    throws JsonMappingException
  {
    return CalendarSerializer.instance.getSchema(paramSerializerProvider, paramType);
  }
  
  public void serialize(XMLGregorianCalendar paramXMLGregorianCalendar, JsonGenerator paramJsonGenerator, SerializerProvider paramSerializerProvider)
    throws IOException, JsonGenerationException
  {
    CalendarSerializer.instance.serialize(paramXMLGregorianCalendar.toGregorianCalendar(), paramJsonGenerator, paramSerializerProvider);
  }
}

/* Location:
 * Qualified Name:     com.fasterxml.jackson.databind.ext.CoreXMLSerializers.XMLGregorianCalendarSerializer
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */