package com.fasterxml.jackson.databind.deser.std;

import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import java.io.IOException;
import java.net.InetSocketAddress;

public class InetSocketAddressDeserializer
  extends FromStringDeserializer<InetSocketAddress>
{
  public static final InetSocketAddressDeserializer instance = new InetSocketAddressDeserializer();
  private static final long serialVersionUID = 1L;
  
  public InetSocketAddressDeserializer()
  {
    super(InetSocketAddress.class);
  }
  
  protected InetSocketAddress _deserialize(String paramString, DeserializationContext paramDeserializationContext)
    throws IOException
  {
    int j;
    if (paramString.startsWith("["))
    {
      j = paramString.lastIndexOf(']');
      if (j == -1) {
        throw new InvalidFormatException("Bracketed IPv6 address must contain closing bracket.", paramString, InetSocketAddress.class);
      }
      i = paramString.indexOf(':', j);
      if (i > -1) {}
      for (i = Integer.parseInt(paramString.substring(i + 1));; i = 0) {
        return new InetSocketAddress(paramString.substring(0, j + 1), i);
      }
    }
    int i = paramString.indexOf(':');
    if ((i != -1) && (paramString.indexOf(':', i + 1) == -1))
    {
      j = Integer.parseInt(paramString.substring(i));
      return new InetSocketAddress(paramString.substring(0, i), j);
    }
    return new InetSocketAddress(paramString, 0);
  }
}

/* Location:
 * Qualified Name:     com.fasterxml.jackson.databind.deser.std.InetSocketAddressDeserializer
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */