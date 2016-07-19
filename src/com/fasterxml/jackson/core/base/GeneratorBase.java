package com.fasterxml.jackson.core.base;

import com.fasterxml.jackson.core.Base64Variant;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonGenerator.Feature;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.core.SerializableString;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.core.json.DupDetector;
import com.fasterxml.jackson.core.json.JsonWriteContext;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.core.util.VersionUtil;
import java.io.IOException;
import java.io.InputStream;

public abstract class GeneratorBase
  extends JsonGenerator
{
  protected boolean _cfgNumbersAsStrings;
  protected boolean _closed;
  protected int _features;
  protected ObjectCodec _objectCodec;
  protected JsonWriteContext _writeContext;
  
  protected GeneratorBase(int paramInt, ObjectCodec paramObjectCodec)
  {
    _features = paramInt;
    if (JsonGenerator.Feature.STRICT_DUPLICATE_DETECTION.enabledIn(paramInt)) {}
    for (DupDetector localDupDetector = DupDetector.rootDetector(this);; localDupDetector = null)
    {
      _writeContext = JsonWriteContext.createRootContext(localDupDetector);
      _objectCodec = paramObjectCodec;
      _cfgNumbersAsStrings = JsonGenerator.Feature.WRITE_NUMBERS_AS_STRINGS.enabledIn(paramInt);
      return;
    }
  }
  
  protected abstract void _releaseBuffers();
  
  protected abstract void _verifyValueWrite(String paramString)
    throws IOException, JsonGenerationException;
  
  protected void _writeSimpleObject(Object paramObject)
    throws IOException, JsonGenerationException
  {
    super._writeSimpleObject(paramObject);
  }
  
  public void close()
    throws IOException
  {
    _closed = true;
  }
  
  public JsonGenerator disable(JsonGenerator.Feature paramFeature)
  {
    _features &= (paramFeature.getMask() ^ 0xFFFFFFFF);
    if (paramFeature == JsonGenerator.Feature.WRITE_NUMBERS_AS_STRINGS) {
      _cfgNumbersAsStrings = false;
    }
    while (paramFeature != JsonGenerator.Feature.ESCAPE_NON_ASCII) {
      return this;
    }
    setHighestNonEscapedChar(0);
    return this;
  }
  
  public JsonGenerator enable(JsonGenerator.Feature paramFeature)
  {
    _features |= paramFeature.getMask();
    if (paramFeature == JsonGenerator.Feature.WRITE_NUMBERS_AS_STRINGS) {
      _cfgNumbersAsStrings = true;
    }
    while (paramFeature != JsonGenerator.Feature.ESCAPE_NON_ASCII) {
      return this;
    }
    setHighestNonEscapedChar(127);
    return this;
  }
  
  public abstract void flush()
    throws IOException;
  
  public final ObjectCodec getCodec()
  {
    return _objectCodec;
  }
  
  public int getFeatureMask()
  {
    return _features;
  }
  
  public final JsonWriteContext getOutputContext()
  {
    return _writeContext;
  }
  
  public boolean isClosed()
  {
    return _closed;
  }
  
  public final boolean isEnabled(JsonGenerator.Feature paramFeature)
  {
    return (_features & paramFeature.getMask()) != 0;
  }
  
  public JsonGenerator setCodec(ObjectCodec paramObjectCodec)
  {
    _objectCodec = paramObjectCodec;
    return this;
  }
  
  public JsonGenerator setFeatureMask(int paramInt)
  {
    _features = paramInt;
    return this;
  }
  
  public JsonGenerator useDefaultPrettyPrinter()
  {
    if (getPrettyPrinter() != null) {
      return this;
    }
    return setPrettyPrinter(new DefaultPrettyPrinter());
  }
  
  public Version version()
  {
    return VersionUtil.versionFor(getClass());
  }
  
  public int writeBinary(Base64Variant paramBase64Variant, InputStream paramInputStream, int paramInt)
    throws IOException, JsonGenerationException
  {
    _reportUnsupportedOperation();
    return 0;
  }
  
  public void writeFieldName(SerializableString paramSerializableString)
    throws IOException, JsonGenerationException
  {
    writeFieldName(paramSerializableString.getValue());
  }
  
  public void writeObject(Object paramObject)
    throws IOException, JsonProcessingException
  {
    if (paramObject == null)
    {
      writeNull();
      return;
    }
    if (_objectCodec != null)
    {
      _objectCodec.writeValue(this, paramObject);
      return;
    }
    _writeSimpleObject(paramObject);
  }
  
  public void writeRawValue(String paramString)
    throws IOException, JsonGenerationException
  {
    _verifyValueWrite("write raw value");
    writeRaw(paramString);
  }
  
  public void writeRawValue(String paramString, int paramInt1, int paramInt2)
    throws IOException, JsonGenerationException
  {
    _verifyValueWrite("write raw value");
    writeRaw(paramString, paramInt1, paramInt2);
  }
  
  public void writeRawValue(char[] paramArrayOfChar, int paramInt1, int paramInt2)
    throws IOException, JsonGenerationException
  {
    _verifyValueWrite("write raw value");
    writeRaw(paramArrayOfChar, paramInt1, paramInt2);
  }
  
  public void writeString(SerializableString paramSerializableString)
    throws IOException, JsonGenerationException
  {
    writeString(paramSerializableString.getValue());
  }
  
  public void writeTree(TreeNode paramTreeNode)
    throws IOException, JsonProcessingException
  {
    if (paramTreeNode == null)
    {
      writeNull();
      return;
    }
    if (_objectCodec == null) {
      throw new IllegalStateException("No ObjectCodec defined");
    }
    _objectCodec.writeValue(this, paramTreeNode);
  }
}

/* Location:
 * Qualified Name:     com.fasterxml.jackson.core.base.GeneratorBase
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */