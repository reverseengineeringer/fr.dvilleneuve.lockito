package com.fasterxml.jackson.databind.deser.std;

import com.fasterxml.jackson.annotation.JsonFormat.Value;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.AnnotationIntrospector;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.deser.ContextualDeserializer;
import com.fasterxml.jackson.databind.util.StdDateFormat;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public abstract class DateDeserializers$DateBasedDeserializer<T>
  extends StdScalarDeserializer<T>
  implements ContextualDeserializer
{
  protected final DateFormat _customFormat;
  protected final String _formatString;
  
  protected DateDeserializers$DateBasedDeserializer(DateBasedDeserializer<T> paramDateBasedDeserializer, DateFormat paramDateFormat, String paramString)
  {
    super(_valueClass);
    _customFormat = paramDateFormat;
    _formatString = paramString;
  }
  
  protected DateDeserializers$DateBasedDeserializer(Class<?> paramClass)
  {
    super(paramClass);
    _customFormat = null;
    _formatString = null;
  }
  
  protected Date _parseDate(JsonParser arg1, DeserializationContext paramDeserializationContext)
    throws IOException, JsonProcessingException
  {
    if ((_customFormat != null) && (???.getCurrentToken() == JsonToken.VALUE_STRING))
    {
      paramDeserializationContext = ???.getText().trim();
      if (paramDeserializationContext.length() == 0) {
        return (Date)getEmptyValue();
      }
      try
      {
        synchronized (_customFormat)
        {
          Date localDate = _customFormat.parse(paramDeserializationContext);
          return localDate;
        }
        return super._parseDate(???, paramDeserializationContext);
      }
      catch (ParseException localParseException)
      {
        throw new IllegalArgumentException("Failed to parse Date value '" + paramDeserializationContext + "' (format: \"" + _formatString + "\"): " + localParseException.getMessage());
      }
    }
  }
  
  public JsonDeserializer<?> createContextual(DeserializationContext paramDeserializationContext, BeanProperty paramBeanProperty)
    throws JsonMappingException
  {
    Object localObject = this;
    TimeZone localTimeZone;
    String str;
    if (paramBeanProperty != null)
    {
      paramBeanProperty = paramDeserializationContext.getAnnotationIntrospector().findFormat(paramBeanProperty.getMember());
      localObject = this;
      if (paramBeanProperty != null)
      {
        localTimeZone = paramBeanProperty.getTimeZone();
        str = paramBeanProperty.getPattern();
        if (str.length() <= 0) {
          break label98;
        }
        localObject = paramBeanProperty.getLocale();
        paramBeanProperty = (BeanProperty)localObject;
        if (localObject == null) {
          paramBeanProperty = paramDeserializationContext.getLocale();
        }
        paramBeanProperty = new SimpleDateFormat(str, paramBeanProperty);
        if (localTimeZone != null) {
          break label157;
        }
      }
    }
    label98:
    label157:
    for (paramDeserializationContext = paramDeserializationContext.getTimeZone();; paramDeserializationContext = localTimeZone)
    {
      paramBeanProperty.setTimeZone(paramDeserializationContext);
      localObject = withDateFormat(paramBeanProperty, str);
      do
      {
        return (JsonDeserializer<?>)localObject;
        localObject = this;
      } while (localTimeZone == null);
      paramDeserializationContext = paramDeserializationContext.getConfig().getDateFormat();
      if (paramDeserializationContext.getClass() == StdDateFormat.class) {
        paramDeserializationContext = ((StdDateFormat)paramDeserializationContext).withTimeZone(localTimeZone);
      }
      for (;;)
      {
        return withDateFormat(paramDeserializationContext, str);
        paramDeserializationContext = (DateFormat)paramDeserializationContext.clone();
        paramDeserializationContext.setTimeZone(localTimeZone);
      }
    }
  }
  
  protected abstract DateBasedDeserializer<T> withDateFormat(DateFormat paramDateFormat, String paramString);
}

/* Location:
 * Qualified Name:     com.fasterxml.jackson.databind.deser.std.DateDeserializers.DateBasedDeserializer
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */