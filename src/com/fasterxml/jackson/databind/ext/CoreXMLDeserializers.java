package com.fasterxml.jackson.databind.ext;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.BeanDescription;
import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.deser.Deserializers.Base;
import com.fasterxml.jackson.databind.deser.std.FromStringDeserializer;
import com.fasterxml.jackson.databind.deser.std.StdScalarDeserializer;
import java.io.IOException;
import java.util.Date;
import java.util.GregorianCalendar;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.Duration;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.namespace.QName;

public class CoreXMLDeserializers
  extends Deserializers.Base
{
  static final DatatypeFactory _dataTypeFactory;
  
  static
  {
    try
    {
      _dataTypeFactory = DatatypeFactory.newInstance();
      return;
    }
    catch (DatatypeConfigurationException localDatatypeConfigurationException)
    {
      throw new RuntimeException(localDatatypeConfigurationException);
    }
  }
  
  public JsonDeserializer<?> findBeanDeserializer(JavaType paramJavaType, DeserializationConfig paramDeserializationConfig, BeanDescription paramBeanDescription)
  {
    paramJavaType = paramJavaType.getRawClass();
    if (paramJavaType == QName.class) {
      return QNameDeserializer.instance;
    }
    if (paramJavaType == XMLGregorianCalendar.class) {
      return GregorianCalendarDeserializer.instance;
    }
    if (paramJavaType == Duration.class) {
      return DurationDeserializer.instance;
    }
    return null;
  }
  
  public static class DurationDeserializer
    extends FromStringDeserializer<Duration>
  {
    public static final DurationDeserializer instance = new DurationDeserializer();
    private static final long serialVersionUID = 1L;
    
    public DurationDeserializer()
    {
      super();
    }
    
    protected Duration _deserialize(String paramString, DeserializationContext paramDeserializationContext)
      throws IllegalArgumentException
    {
      return CoreXMLDeserializers._dataTypeFactory.newDuration(paramString);
    }
  }
  
  public static class GregorianCalendarDeserializer
    extends StdScalarDeserializer<XMLGregorianCalendar>
  {
    public static final GregorianCalendarDeserializer instance = new GregorianCalendarDeserializer();
    private static final long serialVersionUID = 1L;
    
    public GregorianCalendarDeserializer()
    {
      super();
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
  
  public static class QNameDeserializer
    extends FromStringDeserializer<QName>
  {
    public static final QNameDeserializer instance = new QNameDeserializer();
    private static final long serialVersionUID = 1L;
    
    public QNameDeserializer()
    {
      super();
    }
    
    protected QName _deserialize(String paramString, DeserializationContext paramDeserializationContext)
      throws IllegalArgumentException
    {
      return QName.valueOf(paramString);
    }
  }
}

/* Location:
 * Qualified Name:     com.fasterxml.jackson.databind.ext.CoreXMLDeserializers
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */