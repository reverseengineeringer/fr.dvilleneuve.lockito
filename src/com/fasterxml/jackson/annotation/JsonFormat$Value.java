package com.fasterxml.jackson.annotation;

import java.util.Locale;
import java.util.TimeZone;

public class JsonFormat$Value
{
  private final Locale locale;
  private final String pattern;
  private final JsonFormat.Shape shape;
  private final TimeZone timezone;
  
  public JsonFormat$Value()
  {
    this("", JsonFormat.Shape.ANY, "", "");
  }
  
  public JsonFormat$Value(JsonFormat paramJsonFormat)
  {
    this(paramJsonFormat.pattern(), paramJsonFormat.shape(), paramJsonFormat.locale(), paramJsonFormat.timezone());
  }
  
  public JsonFormat$Value(String paramString1, JsonFormat.Shape paramShape, String paramString2, String paramString3) {}
  
  public JsonFormat$Value(String paramString, JsonFormat.Shape paramShape, Locale paramLocale, TimeZone paramTimeZone)
  {
    pattern = paramString;
    shape = paramShape;
    locale = paramLocale;
    timezone = paramTimeZone;
  }
  
  public Locale getLocale()
  {
    return locale;
  }
  
  public String getPattern()
  {
    return pattern;
  }
  
  public JsonFormat.Shape getShape()
  {
    return shape;
  }
  
  public TimeZone getTimeZone()
  {
    return timezone;
  }
  
  public Value withLocale(Locale paramLocale)
  {
    return new Value(pattern, shape, paramLocale, timezone);
  }
  
  public Value withPattern(String paramString)
  {
    return new Value(paramString, shape, locale, timezone);
  }
  
  public Value withShape(JsonFormat.Shape paramShape)
  {
    return new Value(pattern, paramShape, locale, timezone);
  }
  
  public Value withTimeZone(TimeZone paramTimeZone)
  {
    return new Value(pattern, shape, locale, paramTimeZone);
  }
}

/* Location:
 * Qualified Name:     com.fasterxml.jackson.annotation.JsonFormat.Value
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */