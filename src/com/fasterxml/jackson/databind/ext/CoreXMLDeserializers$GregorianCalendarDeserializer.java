package com.fasterxml.jackson.databind.ext;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdScalarDeserializer;
import java.io.IOException;
import java.util.Date;
import java.util.GregorianCalendar;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

public class CoreXMLDeserializers$GregorianCalendarDeserializer
  extends StdScalarDeserializer<XMLGregorianCalendar>
{
  public static final GregorianCalendarDeserializer instance = new GregorianCalendarDeserializer();
  private static final long serialVersionUID = 1L;
  
  public CoreXMLDeserializers$GregorianCalendarDeserializer()
  {
    super(XMLGregorianCalendar.class);
  }
  
  public XMLGregorianCalendar deserialize(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext)
    throws IOException, JsonProcessingException
  {
    Date localDate = _parseDate(paramJsonParser, paramDeserializationContext);
    if (localDate == null) {
      return null;
    }
    paramJsonParser = new GregorianCalendar();
    paramJsonParser.setTime(localDate);
    paramDeserializationContext = paramDeserializationContext.getTimeZone();
    if (paramDeserializationContext != null) {
      paramJsonParser.setTimeZone(paramDeserializationContext);
    }
    return CoreXMLDeserializers._dataTypeFactory.newXMLGregorianCalendar(paramJsonParser);
  }
}

/* Location:
 * Qualified Name:     com.fasterxml.jackson.databind.ext.CoreXMLDeserializers.GregorianCalendarDeserializer
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */