package com.fasterxml.jackson.core.json;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.JsonStreamContext;

public class JsonWriteContext
  extends JsonStreamContext
{
  public static final int STATUS_EXPECT_NAME = 5;
  public static final int STATUS_EXPECT_VALUE = 4;
  public static final int STATUS_OK_AFTER_COLON = 2;
  public static final int STATUS_OK_AFTER_COMMA = 1;
  public static final int STATUS_OK_AFTER_SPACE = 3;
  public static final int STATUS_OK_AS_IS = 0;
  protected JsonWriteContext _child = null;
  protected String _currentName;
  protected final DupDetector _dups;
  protected boolean _gotName;
  protected final JsonWriteContext _parent;
  
  protected JsonWriteContext(int paramInt, JsonWriteContext paramJsonWriteContext, DupDetector paramDupDetector)
  {
    _type = paramInt;
    _parent = paramJsonWriteContext;
    _dups = paramDupDetector;
    _index = -1;
  }
  
  private void _checkDup(DupDetector paramDupDetector, String paramString)
    throws JsonProcessingException
  {
    if (paramDupDetector.isDup(paramString)) {
      throw new JsonGenerationException("Duplicate field '" + paramString + "'");
    }
  }
  
  @Deprecated
  public static JsonWriteContext createRootContext()
  {
    return createRootContext(null);
  }
  
  public static JsonWriteContext createRootContext(DupDetector paramDupDetector)
  {
    return new JsonWriteContext(0, null, paramDupDetector);
  }
  
  protected final void appendDesc(StringBuilder paramStringBuilder)
  {
    if (_type == 2)
    {
      paramStringBuilder.append('{');
      if (_currentName != null)
      {
        paramStringBuilder.append('"');
        paramStringBuilder.append(_currentName);
        paramStringBuilder.append('"');
      }
      for (;;)
      {
        paramStringBuilder.append('}');
        return;
        paramStringBuilder.append('?');
      }
    }
    if (_type == 1)
    {
      paramStringBuilder.append('[');
      paramStringBuilder.append(getCurrentIndex());
      paramStringBuilder.append(']');
      return;
    }
    paramStringBuilder.append("/");
  }
  
  public JsonWriteContext createChildArrayContext()
  {
    Object localObject = _child;
    if (localObject == null)
    {
      if (_dups == null) {}
      for (localObject = null;; localObject = _dups.child())
      {
        localObject = new JsonWriteContext(1, this, (DupDetector)localObject);
        _child = ((JsonWriteContext)localObject);
        return (JsonWriteContext)localObject;
      }
    }
    return ((JsonWriteContext)localObject).reset(1);
  }
  
  public JsonWriteContext createChildObjectContext()
  {
    Object localObject = _child;
    if (localObject == null)
    {
      if (_dups == null) {}
      for (localObject = null;; localObject = _dups.child())
      {
        localObject = new JsonWriteContext(2, this, (DupDetector)localObject);
        _child = ((JsonWriteContext)localObject);
        return (JsonWriteContext)localObject;
      }
    }
    return ((JsonWriteContext)localObject).reset(2);
  }
  
  public final String getCurrentName()
  {
    return _currentName;
  }
  
  public final JsonWriteContext getParent()
  {
    return _parent;
  }
  
  protected JsonWriteContext reset(int paramInt)
  {
    _type = paramInt;
    _index = -1;
    _currentName = null;
    _gotName = false;
    if (_dups != null) {
      _dups.reset();
    }
    return this;
  }
  
  public final String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder(64);
    appendDesc(localStringBuilder);
    return localStringBuilder.toString();
  }
  
  public final int writeFieldName(String paramString)
    throws JsonProcessingException
  {
    int i = 1;
    _gotName = true;
    _currentName = paramString;
    if (_dups != null) {
      _checkDup(_dups, paramString);
    }
    if (_index < 0) {
      i = 0;
    }
    return i;
  }
  
  public final int writeValue()
  {
    int i = 0;
    if (_type == 2)
    {
      _gotName = false;
      _index += 1;
      i = 2;
    }
    do
    {
      int j;
      do
      {
        return i;
        if (_type != 1) {
          break;
        }
        j = _index;
        _index += 1;
      } while (j < 0);
      return 1;
      _index += 1;
    } while (_index == 0);
    return 3;
  }
}

/* Location:
 * Qualified Name:     com.fasterxml.jackson.core.json.JsonWriteContext
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */