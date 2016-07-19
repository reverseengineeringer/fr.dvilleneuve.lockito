package com.fasterxml.jackson.databind.deser.std;

import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import java.net.InetAddress;

class InetAddressDeserializer
  extends FromStringDeserializer<InetAddress>
{
  public static final InetAddressDeserializer instance = new InetAddressDeserializer();
  private static final long serialVersionUID = 1L;
  
  public InetAddressDeserializer()
  {
    super(InetAddress.class);
  }
  
  protected InetAddress _deserialize(String paramString, DeserializationContext paramDeserializationContext)
    throws IOException
  {
    return InetAddress.getByName(paramString);
  }
}

/* Location:
 * Qualified Name:     com.fasterxml.jackson.databind.deser.std.InetAddressDeserializer
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */