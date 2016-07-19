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
import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import com.fasterxml.jackson.databind.deser.ContextualDeserializer;
import com.fasterxml.jackson.databind.util.StdDateFormat;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.TimeZone;

public class DateDeserializers
{
  private static final HashSet<String> _classNames;
  
  static
  {
    int i = 0;
    _classNames = new HashSet();
    Class[] arrayOfClass = new Class[6];
    arrayOfClass[0] = Calendar.class;
    arrayOfClass[1] = GregorianCalendar.class;
    arrayOfClass[2] = java.sql.Date.class;
    arrayOfClass[3] = java.util.Date.class;
    arrayOfClass[4] = Timestamp.class;
    arrayOfClass[5] = TimeZone.class;
    int j = arrayOfClass.length;
    while (i < j)
    {
      Class localClass = arrayOfClass[i];
      _classNames.add(localClass.getName());
      i += 1;
    }
  }
  
  public static JsonDeserializer<?> find(Class<?> paramClass, String paramString)
  {
    if (!_classNames.contains(paramString)) {
      return null;
    }
    if (paramClass == Calendar.class) {
      return CalendarDeserializer.instance;
    }
    if (paramClass == java.util.Date.class) {
      return DateDeserializer.instance;
    }
    if (paramClass == java.sql.Date.class) {
      return SqlDateDeserializer.instance;
    }
    if (paramClass == Timestamp.class) {
      return TimestampDeserializer.instance;
    }
    if (paramClass == TimeZone.class) {
      return TimeZoneDeserializer.instance;
    }
    if (paramClass == GregorianCalendar.class) {
      return CalendarDeserializer.gregorianInstance;
    }
    throw new IllegalArgumentException("Internal error: can't find deserializer for " + paramString);
  }
  
  @JacksonStdImpl
  public static class CalendarDeserializer
    extends DateDeserializers.DateBasedDeserializer<Calendar>
  {
    public static final CalendarDeserializer gregorianInstance = new CalendarDeserializer(GregorianCalendar.class);
    public static final CalendarDeserializer instance = new CalendarDeserializer();
    protected final Class<? extends Calendar> _calendarClass;
    
    public CalendarDeserializer()
    {
      super();
      _calendarClass = null;
    }
    
    public CalendarDeserializer(CalendarDeserializer paramCalendarDeserializer, DateFormat paramDateFormat, String paramString)
    {
      super(paramDateFormat, paramString);
      _calendarClass = _calendarClass;
    }
    
    public CalendarDeserializer(Class<? extends Calendar> paramClass)
    {
      super();
      _calendarClass = paramClass;
    }
    
    public Calendar deserialize(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext)
      throws IOException, JsonProcessingException
    {
      paramJsonParser = _parseDate(paramJsonParser, paramDeserializationContext);
      if (paramJsonParser == null) {
        paramJsonParser = null;
      }
      for (;;)
      {
        return paramJsonParser;
        if (_calendarClass == null) {
          return paramDeserializationContext.constructCalendar(paramJsonParser);
        }
        try
        {
          Calendar localCalendar = (Calendar)_calendarClass.newInstance();
          localCalendar.setTimeInMillis(paramJsonParser.getTime());
          TimeZone localTimeZone = paramDeserializationContext.getTimeZone();
          paramJsonParser = localCalendar;
          if (localTimeZone == null) {
            continue;
          }
          localCalendar.setTimeZone(localTimeZone);
          return localCalendar;
        }
        catch (Exception paramJsonParser)
        {
          throw paramDeserializationContext.instantiationException(_calendarClass, paramJsonParser);
        }
      }
    }
    
    protected CalendarDeserializer withDateFormat(DateFormat paramDateFormat, String paramString)
    {
      return new CalendarDeserializer(this, paramDateFormat, paramString);
    }
  }
  
  protected static abstract class DateBasedDeserializer<T>
    extends StdScalarDeserializer<T>
    implements ContextualDeserializer
  {
    protected final DateFormat _customFormat;
    protected final String _formatString;
    
    protected DateBasedDeserializer(DateBasedDeserializer<T> paramDateBasedDeserializer, DateFormat paramDateFormat, String paramString)
    {
      super();
      _customFormat = paramDateFormat;
      _formatString = paramString;
    }
    
    protected DateBasedDeserializer(Class<?> paramClass)
    {
      super();
      _customFormat = null;
      _formatString = null;
    }
    
    protected java.util.Date _parseDate(JsonParser arg1, DeserializationContext paramDeserializationContext)
      throws IOException, JsonProcessingException
    {
      if ((_customFormat != null) && (???.getCurrentToken() == JsonToken.VALUE_STRING))
      {
        paramDeserializationContext = ???.getText().trim();
        if (paramDeserializationContext.length() == 0) {
          return (java.util.Date)getEmptyValue();
        }
        try
        {
          synchronized (_customFormat)
          {
            java.util.Date localDate = _customFormat.parse(paramDeserializationContext);
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
  
  public static class DateDeserializer
    extends DateDeserializers.DateBasedDeserializer<java.util.Date>
  {
    public static final DateDeserializer instance = new DateDeserializer();
    
    public DateDeserializer()
    {
      super();
    }
    
    public DateDeserializer(DateDeserializer paramDateDeserializer, DateFormat paramDateFormat, String paramString)
    {
      super(paramDateFormat, paramString);
    }
    
    public java.util.Date deserialize(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext)
      throws IOException, JsonProcessingException
    {
      return _parseDate(paramJsonParser, paramDeserializationContext);
    }
    
    protected DateDeserializer withDateFormat(DateFormat paramDateFormat, String paramString)
    {
      return new DateDeserializer(this, paramDateFormat, paramString);
    }
  }
  
  public static class SqlDateDeserializer
    extends DateDeserializers.DateBasedDeserializer<java.sql.Date>
  {
    public static final SqlDateDeserializer instance = new SqlDateDeserializer();
    
    public SqlDateDeserializer()
    {
      super();
    }
    
    public SqlDateDeserializer(SqlDateDeserializer paramSqlDateDeserializer, DateFormat paramDateFormat, String paramString)
    {
      super(paramDateFormat, paramString);
    }
    
    public java.sql.Date deserialize(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext)
      throws IOException, JsonProcessingException
    {
      paramJsonParser = _parseDate(paramJsonParser, paramDeserializationContext);
      if (paramJsonParser == null) {
        return null;
      }
      return new java.sql.Date(paramJsonParser.getTime());
    }
    
    protected SqlDateDeserializer withDateFormat(DateFormat paramDateFormat, String paramString)
    {
      return new SqlDateDeserializer(this, paramDateFormat, paramString);
    }
  }
  
  protected static class TimeZoneDeserializer
    extends FromStringDeserializer<TimeZone>
  {
    public static final TimeZoneDeserializer instance = new TimeZoneDeserializer();
    
    public TimeZoneDeserializer()
    {
      super();
    }
    
    protected TimeZone _deserialize(String paramString, DeserializationContext paramDeserializationContext)
      throws IOException
    {
      return TimeZone.getTimeZone(paramString);
    }
  }
  
  public static class TimestampDeserializer
    extends DateDeserializers.DateBasedDeserializer<Timestamp>
  {
    public static final TimestampDeserializer instance = new TimestampDeserializer();
    
    public TimestampDeserializer()
    {
      super();
    }
    
    public TimestampDeserializer(TimestampDeserializer paramTimestampDeserializer, DateFormat paramDateFormat, String paramString)
    {
      super(paramDateFormat, paramString);
    }
    
    public Timestamp deserialize(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext)
      throws IOException, JsonProcessingException
    {
      return new Timestamp(_parseDate(paramJsonParser, paramDeserializationContext).getTime());
    }
    
    protected TimestampDeserializer withDateFormat(DateFormat paramDateFormat, String paramString)
    {
      return new TimestampDeserializer(this, paramDateFormat, paramString);
    }
  }
}

/* Location:
 * Qualified Name:     com.fasterxml.jackson.databind.deser.std.DateDeserializers
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */