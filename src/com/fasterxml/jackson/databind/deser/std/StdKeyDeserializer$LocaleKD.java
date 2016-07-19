package com.fasterxml.jackson.databind.deser.std;

import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import java.io.IOException;
import java.util.Locale;

@JacksonStdImpl
final class StdKeyDeserializer$LocaleKD
  extends StdKeyDeserializer
{
  private static final long serialVersionUID = 1L;
  protected JdkDeserializers.LocaleDeserializer _localeDeserializer = new JdkDeserializers.LocaleDeserializer();
  
  StdKeyDeserializer$LocaleKD()
  {
    super(Locale.class);
  }
  
  protected Locale _parse(String paramString, DeserializationContext paramDeserializationContext)
    throws JsonMappingException
  {
    try
    {
      Locale localLocale = _localeDeserializer._deserialize(paramString, paramDeserializationContext);
      return localLocale;
    }
    catch (IOException localIOException)
    {
      throw paramDeserializationContext.weirdKeyException(_keyClass, paramString, "unable to parse key as locale");
    }
  }
}

/* Location:
 * Qualified Name:     com.fasterxml.jackson.databind.deser.std.StdKeyDeserializer.LocaleKD
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */