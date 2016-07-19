package com.fasterxml.jackson.databind.ser.std;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import java.io.IOException;
import java.text.DateFormat;
import java.util.Calendar;

@JacksonStdImpl
public class CalendarSerializer
  extends DateTimeSerializerBase<Calendar>
{
  public static final CalendarSerializer instance = new CalendarSerializer();
  
  public CalendarSerializer()
  {
    this(false, null);
  }
  
  public CalendarSerializer(boolean paramBoolean, DateFormat paramDateFormat)
  {
    super(Calendar.class, paramBoolean, paramDateFormat);
  }
  
  protected long _timestamp(Calendar paramCalendar)
  {
    if (paramCalendar == null) {
      return 0L;
    }
    return paramCalendar.getTimeInMillis();
  }
  
  public void serialize(Calendar paramCalendar, JsonGenerator paramJsonGenerator, SerializerProvider arg3)
    throws IOException, JsonGenerationException
  {
    if (_useTimestamp)
    {
      paramJsonGenerator.writeNumber(_timestamp(paramCalendar));
      return;
    }
    if (_customFormat != null) {
      synchronized (_customFormat)
      {
        paramJsonGenerator.writeString(_customFormat.format(paramCalendar));
        return;
      }
    }
    ???.defaultSerializeDateValue(paramCalendar.getTime(), paramJsonGenerator);
  }
  
  public CalendarSerializer withFormat(boolean paramBoolean, DateFormat paramDateFormat)
  {
    if (paramBoolean) {
      return new CalendarSerializer(true, null);
    }
    return new CalendarSerializer(false, paramDateFormat);
  }
}

/* Location:
 * Qualified Name:     com.fasterxml.jackson.databind.ser.std.CalendarSerializer
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */