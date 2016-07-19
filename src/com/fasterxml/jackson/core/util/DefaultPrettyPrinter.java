package com.fasterxml.jackson.core.util;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.PrettyPrinter;
import com.fasterxml.jackson.core.SerializableString;
import com.fasterxml.jackson.core.io.SerializedString;
import java.io.IOException;
import java.io.Serializable;
import java.util.Arrays;

public class DefaultPrettyPrinter
  implements PrettyPrinter, Instantiatable<DefaultPrettyPrinter>, Serializable
{
  public static final SerializedString DEFAULT_ROOT_VALUE_SEPARATOR = new SerializedString(" ");
  private static final long serialVersionUID = -5512586643324525213L;
  protected Indenter _arrayIndenter = FixedSpaceIndenter.instance;
  protected transient int _nesting = 0;
  protected Indenter _objectIndenter = Lf2SpacesIndenter.instance;
  protected final SerializableString _rootSeparator;
  protected boolean _spacesInObjectEntries = true;
  
  public DefaultPrettyPrinter()
  {
    this(DEFAULT_ROOT_VALUE_SEPARATOR);
  }
  
  public DefaultPrettyPrinter(SerializableString paramSerializableString)
  {
    _rootSeparator = paramSerializableString;
  }
  
  public DefaultPrettyPrinter(DefaultPrettyPrinter paramDefaultPrettyPrinter)
  {
    this(paramDefaultPrettyPrinter, _rootSeparator);
  }
  
  public DefaultPrettyPrinter(DefaultPrettyPrinter paramDefaultPrettyPrinter, SerializableString paramSerializableString)
  {
    _arrayIndenter = _arrayIndenter;
    _objectIndenter = _objectIndenter;
    _spacesInObjectEntries = _spacesInObjectEntries;
    _nesting = _nesting;
    _rootSeparator = paramSerializableString;
  }
  
  public DefaultPrettyPrinter(String paramString) {}
  
  protected DefaultPrettyPrinter _withSpaces(boolean paramBoolean)
  {
    if (_spacesInObjectEntries == paramBoolean) {
      return this;
    }
    DefaultPrettyPrinter localDefaultPrettyPrinter = new DefaultPrettyPrinter(this);
    _spacesInObjectEntries = paramBoolean;
    return localDefaultPrettyPrinter;
  }
  
  public void beforeArrayValues(JsonGenerator paramJsonGenerator)
    throws IOException, JsonGenerationException
  {
    _arrayIndenter.writeIndentation(paramJsonGenerator, _nesting);
  }
  
  public void beforeObjectEntries(JsonGenerator paramJsonGenerator)
    throws IOException, JsonGenerationException
  {
    _objectIndenter.writeIndentation(paramJsonGenerator, _nesting);
  }
  
  public DefaultPrettyPrinter createInstance()
  {
    return new DefaultPrettyPrinter(this);
  }
  
  public void indentArraysWith(Indenter paramIndenter)
  {
    Object localObject = paramIndenter;
    if (paramIndenter == null) {
      localObject = NopIndenter.instance;
    }
    _arrayIndenter = ((Indenter)localObject);
  }
  
  public void indentObjectsWith(Indenter paramIndenter)
  {
    Object localObject = paramIndenter;
    if (paramIndenter == null) {
      localObject = NopIndenter.instance;
    }
    _objectIndenter = ((Indenter)localObject);
  }
  
  @Deprecated
  public void spacesInObjectEntries(boolean paramBoolean)
  {
    _spacesInObjectEntries = paramBoolean;
  }
  
  public DefaultPrettyPrinter withArrayIndenter(Indenter paramIndenter)
  {
    Object localObject = paramIndenter;
    if (paramIndenter == null) {
      localObject = NopIndenter.instance;
    }
    if (_arrayIndenter == localObject) {
      return this;
    }
    paramIndenter = new DefaultPrettyPrinter(this);
    _arrayIndenter = ((Indenter)localObject);
    return paramIndenter;
  }
  
  public DefaultPrettyPrinter withObjectIndenter(Indenter paramIndenter)
  {
    Object localObject = paramIndenter;
    if (paramIndenter == null) {
      localObject = NopIndenter.instance;
    }
    if (_objectIndenter == localObject) {
      return this;
    }
    paramIndenter = new DefaultPrettyPrinter(this);
    _objectIndenter = ((Indenter)localObject);
    return paramIndenter;
  }
  
  public DefaultPrettyPrinter withRootSeparator(SerializableString paramSerializableString)
  {
    if ((_rootSeparator == paramSerializableString) || ((paramSerializableString != null) && (paramSerializableString.equals(_rootSeparator)))) {
      return this;
    }
    return new DefaultPrettyPrinter(this, paramSerializableString);
  }
  
  public DefaultPrettyPrinter withSpacesInObjectEntries()
  {
    return _withSpaces(true);
  }
  
  public DefaultPrettyPrinter withoutSpacesInObjectEntries()
  {
    return _withSpaces(false);
  }
  
  public void writeArrayValueSeparator(JsonGenerator paramJsonGenerator)
    throws IOException, JsonGenerationException
  {
    paramJsonGenerator.writeRaw(',');
    _arrayIndenter.writeIndentation(paramJsonGenerator, _nesting);
  }
  
  public void writeEndArray(JsonGenerator paramJsonGenerator, int paramInt)
    throws IOException, JsonGenerationException
  {
    if (!_arrayIndenter.isInline()) {
      _nesting -= 1;
    }
    if (paramInt > 0) {
      _arrayIndenter.writeIndentation(paramJsonGenerator, _nesting);
    }
    for (;;)
    {
      paramJsonGenerator.writeRaw(']');
      return;
      paramJsonGenerator.writeRaw(' ');
    }
  }
  
  public void writeEndObject(JsonGenerator paramJsonGenerator, int paramInt)
    throws IOException, JsonGenerationException
  {
    if (!_objectIndenter.isInline()) {
      _nesting -= 1;
    }
    if (paramInt > 0) {
      _objectIndenter.writeIndentation(paramJsonGenerator, _nesting);
    }
    for (;;)
    {
      paramJsonGenerator.writeRaw('}');
      return;
      paramJsonGenerator.writeRaw(' ');
    }
  }
  
  public void writeObjectEntrySeparator(JsonGenerator paramJsonGenerator)
    throws IOException, JsonGenerationException
  {
    paramJsonGenerator.writeRaw(',');
    _objectIndenter.writeIndentation(paramJsonGenerator, _nesting);
  }
  
  public void writeObjectFieldValueSeparator(JsonGenerator paramJsonGenerator)
    throws IOException, JsonGenerationException
  {
    if (_spacesInObjectEntries)
    {
      paramJsonGenerator.writeRaw(" : ");
      return;
    }
    paramJsonGenerator.writeRaw(':');
  }
  
  public void writeRootValueSeparator(JsonGenerator paramJsonGenerator)
    throws IOException, JsonGenerationException
  {
    if (_rootSeparator != null) {
      paramJsonGenerator.writeRaw(_rootSeparator);
    }
  }
  
  public void writeStartArray(JsonGenerator paramJsonGenerator)
    throws IOException, JsonGenerationException
  {
    if (!_arrayIndenter.isInline()) {
      _nesting += 1;
    }
    paramJsonGenerator.writeRaw('[');
  }
  
  public void writeStartObject(JsonGenerator paramJsonGenerator)
    throws IOException, JsonGenerationException
  {
    paramJsonGenerator.writeRaw('{');
    if (!_objectIndenter.isInline()) {
      _nesting += 1;
    }
  }
  
  public static class FixedSpaceIndenter
    extends DefaultPrettyPrinter.NopIndenter
  {
    public static final FixedSpaceIndenter instance = new FixedSpaceIndenter();
    
    public boolean isInline()
    {
      return true;
    }
    
    public void writeIndentation(JsonGenerator paramJsonGenerator, int paramInt)
      throws IOException, JsonGenerationException
    {
      paramJsonGenerator.writeRaw(' ');
    }
  }
  
  public static abstract interface Indenter
  {
    public abstract boolean isInline();
    
    public abstract void writeIndentation(JsonGenerator paramJsonGenerator, int paramInt)
      throws IOException, JsonGenerationException;
  }
  
  public static class Lf2SpacesIndenter
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
    
    public Lf2SpacesIndenter()
    {
      this(SYS_LF);
    }
    
    public Lf2SpacesIndenter(String paramString)
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
  
  public static class NopIndenter
    implements DefaultPrettyPrinter.Indenter, Serializable
  {
    public static final NopIndenter instance = new NopIndenter();
    
    public boolean isInline()
    {
      return true;
    }
    
    public void writeIndentation(JsonGenerator paramJsonGenerator, int paramInt)
      throws IOException, JsonGenerationException
    {}
  }
}

/* Location:
 * Qualified Name:     com.fasterxml.jackson.core.util.DefaultPrettyPrinter
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */