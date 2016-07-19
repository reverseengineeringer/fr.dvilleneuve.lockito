package com.fasterxml.jackson.core.util;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import java.io.IOException;
import java.util.Arrays;

public class DefaultPrettyPrinter$Lf2SpacesIndenter
  extends DefaultPrettyPrinter.NopIndenter
{
  static final char[] SPACES;
  static final int SPACE_COUNT = 64;
  private static final String SYS_LF;
  public static final Lf2SpacesIndenter instance = new Lf2SpacesIndenter();
  protected final String _lf;
  
  static
  {
    Object localObject1 = null;
    try
    {
      localObject2 = System.getProperty("line.separator");
      localObject1 = localObject2;
    }
    catch (Throwable localThrowable)
    {
      Object localObject2;
      for (;;) {}
    }
    localObject2 = localObject1;
    if (localObject1 == null) {
      localObject2 = "\n";
    }
    SYS_LF = (String)localObject2;
    SPACES = new char[64];
    Arrays.fill(SPACES, ' ');
  }
  
  public DefaultPrettyPrinter$Lf2SpacesIndenter()
  {
    this(SYS_LF);
  }
  
  public DefaultPrettyPrinter$Lf2SpacesIndenter(String paramString)
  {
    _lf = paramString;
  }
  
  public boolean isInline()
  {
    return false;
  }
  
  public Lf2SpacesIndenter withLinefeed(String paramString)
  {
    if (paramString.equals(_lf)) {
      return this;
    }
    return new Lf2SpacesIndenter(paramString);
  }
  
  public void writeIndentation(JsonGenerator paramJsonGenerator, int paramInt)
    throws IOException, JsonGenerationException
  {
    paramJsonGenerator.writeRaw(_lf);
    if (paramInt > 0)
    {
      paramInt += paramInt;
      while (paramInt > 64)
      {
        paramJsonGenerator.writeRaw(SPACES, 0, 64);
        paramInt -= SPACES.length;
      }
      paramJsonGenerator.writeRaw(SPACES, 0, paramInt);
    }
  }
}

/* Location:
 * Qualified Name:     com.fasterxml.jackson.core.util.DefaultPrettyPrinter.Lf2SpacesIndenter
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */