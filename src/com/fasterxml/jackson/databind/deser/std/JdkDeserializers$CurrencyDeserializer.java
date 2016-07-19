package com.fasterxml.jackson.databind.deser.std;

import com.fasterxml.jackson.databind.DeserializationContext;
import java.util.Currency;

public class JdkDeserializers$CurrencyDeserializer
  extends FromStringDeserializer<Currency>
{
  public static final CurrencyDeserializer instance = new CurrencyDeserializer();
  
  public JdkDeserializers$CurrencyDeserializer()
  {
    super(Currency.class);
  }
  
  protected Currency _deserialize(String paramString, DeserializationContext paramDeserializationContext)
    throws IllegalArgumentException
  {
    return Currency.getInstance(paramString);
  }
}

/* Location:
 * Qualified Name:     com.fasterxml.jackson.databind.deser.std.JdkDeserializers.CurrencyDeserializer
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */