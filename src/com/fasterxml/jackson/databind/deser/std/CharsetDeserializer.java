package com.fasterxml.jackson.databind.deser.std;

import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import java.nio.charset.Charset;

public class CharsetDeserializer
  extends FromStringDeserializer<Charset>
{
  private static final long serialVersionUID = 1L;
  
  public CharsetDeserializer()
  {
    super(Charset.class);
  }
  
  protected Charset _deserialize(String paramString, DeserializationContext paramDeserializationContext)
    throws IOException
  {
    return Charset.forName(paramString);
  }
}

/* Location:
 * Qualified Name:     com.fasterxml.jackson.databind.deser.std.CharsetDeserializer
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */