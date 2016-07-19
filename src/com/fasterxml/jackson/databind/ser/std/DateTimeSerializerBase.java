package com.fasterxml.jackson.databind.ser.std;

import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import com.fasterxml.jackson.annotation.JsonFormat.Value;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser.NumberType;
import com.fasterxml.jackson.databind.AnnotationIntrospector;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializationConfig;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonFormatVisitorWrapper;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonIntegerFormatVisitor;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonStringFormatVisitor;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonValueFormat;
import com.fasterxml.jackson.databind.ser.ContextualSerializer;
import com.fasterxml.jackson.databind.util.StdDateFormat;
import java.io.IOException;
import java.lang.reflect.Type;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.TimeZone;

public abstract class DateTimeSerializerBase<T>
  extends StdScalarSerializer<T>
  implements ContextualSerializer
{
  protected final DateFormat _customFormat;
  protected final boolean _useTimestamp;
  
  protected DateTimeSerializerBase(Class<T> paramClass, boolean paramBoolean, DateFormat paramDateFormat)
  {
    super(paramClass);
    _useTimestamp = paramBoolean;
    _customFormat = paramDateFormat;
  }
  
  protected abstract long _timestamp(T paramT);
  
  public void acceptJsonFormatVisitor(JsonFormatVisitorWrapper paramJsonFormatVisitorWrapper, JavaType paramJavaType)
    throws JsonMappingException
  {
    boolean bool2 = _useTimestamp;
    boolean bool1 = bool2;
    if (!bool2)
    {
      bool1 = bool2;
      if (_customFormat == null) {
        bool1 = paramJsonFormatVisitorWrapper.getProvider().isEnabled(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
      }
    }
    if (bool1)
    {
      paramJsonFormatVisitorWrapper = paramJsonFormatVisitorWrapper.expectIntegerFormat(paramJavaType);
      if (paramJsonFormatVisitorWrapper != null)
      {
        paramJsonFormatVisitorWrapper.numberType(JsonParser.NumberType.LONG);
        paramJsonFormatVisitorWrapper.format(JsonValueFormat.UTC_MILLISEC);
      }
    }
    do
    {
      return;
      paramJsonFormatVisitorWrapper = paramJsonFormatVisitorWrapper.expectStringFormat(paramJavaType);
    } while (paramJsonFormatVisitorWrapper == null);
    paramJsonFormatVisitorWrapper.format(JsonValueFormat.DATE_TIME);
  }
  
  public JsonSerializer<?> createContextual(SerializerProvider paramSerializerProvider, BeanProperty paramBeanProperty)
    throws JsonMappingException
  {
    Object localObject = this;
    if (paramBeanProperty != null)
    {
      paramBeanProperty = paramSerializerProvider.getAnnotationIntrospector().findFormat(paramBeanProperty.getMember());
      localObject = this;
      if (paramBeanProperty != null)
      {
        if (!paramBeanProperty.getShape().isNumeric()) {
          break label45;
        }
        localObject = withFormat(true, null);
      }
    }
    return (JsonSerializer<?>)localObject;
    label45:
    TimeZone localTimeZone = paramBeanProperty.getTimeZone();
    String str = paramBeanProperty.getPattern();
    if (str.length() > 0)
    {
      localObject = paramBeanProperty.getLocale();
      paramBeanProperty = (BeanProperty)localObject;
      if (localObject == null) {
        paramBeanProperty = paramSerializerProvider.getLocale();
      }
      paramBeanProperty = new SimpleDateFormat(str, paramBeanProperty);
      if (localTimeZone != null) {
        break label168;
      }
    }
    label168:
    for (paramSerializerProvider = paramSerializerProvider.getTimeZone();; paramSerializerProvider = localTimeZone)
    {
      paramBeanProperty.setTimeZone(paramSerializerProvider);
      return withFormat(false, paramBeanProperty);
      localObject = this;
      if (localTimeZone == null) {
        break;
      }
      paramSerializerProvider = paramSerializerProvider.getConfig().getDateFormat();
      if (paramSerializerProvider.getClass() == StdDateFormat.class) {
        paramSerializerProvider = StdDateFormat.getISO8601Format(localTimeZone);
      }
      for (;;)
      {
        return withFormat(false, paramSerializerProvider);
        paramSerializerProvider = (DateFormat)paramSerializerProvider.clone();
        paramSerializerProvider.setTimeZone(localTimeZone);
      }
    }
  }
  
  public JsonNode getSchema(SerializerProvider paramSerializerProvider, Type paramType)
  {
    boolean bool2 = _useTimestamp;
    boolean bool1 = bool2;
    if (!bool2)
    {
      bool1 = bool2;
      if (_customFormat == null) {
        bool1 = paramSerializerProvider.isEnabled(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
      }
    }
    if (bool1) {}
    for (paramSerializerProvider = "number";; paramSerializerProvider = "string") {
      return createSchemaNode(paramSerializerProvider, true);
    }
  }
  
  public boolean isEmpty(T paramT)
  {
    return (paramT == null) || (_timestamp(paramT) == 0L);
  }
  
  public abstract void serialize(T paramT, JsonGenerator paramJsonGenerator, SerializerProvider paramSerializerProvider)
    throws IOException, JsonGenerationException;
  
  public abstract DateTimeSerializerBase<T> withFormat(boolean paramBoolean, DateFormat paramDateFormat);
}

/* Location:
 * Qualified Name:     com.fasterxml.jackson.databind.ser.std.DateTimeSerializerBase
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */