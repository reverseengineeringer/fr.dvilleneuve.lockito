package com.fasterxml.jackson.databind.deser.impl;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.PropertyName;
import com.fasterxml.jackson.databind.deser.SettableAnyProperty;
import com.fasterxml.jackson.databind.deser.SettableBeanProperty;
import java.io.IOException;

public final class PropertyValueBuffer
{
  private PropertyValue _buffered;
  protected final DeserializationContext _context;
  protected final Object[] _creatorParameters;
  private Object _idValue;
  protected final ObjectIdReader _objectIdReader;
  private int _paramsNeeded;
  protected final JsonParser _parser;
  
  public PropertyValueBuffer(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext, int paramInt, ObjectIdReader paramObjectIdReader)
  {
    _parser = paramJsonParser;
    _context = paramDeserializationContext;
    _paramsNeeded = paramInt;
    _objectIdReader = paramObjectIdReader;
    _creatorParameters = new Object[paramInt];
  }
  
  public boolean assignParameter(int paramInt, Object paramObject)
  {
    _creatorParameters[paramInt] = paramObject;
    paramInt = _paramsNeeded - 1;
    _paramsNeeded = paramInt;
    return paramInt <= 0;
  }
  
  public void bufferAnyProperty(SettableAnyProperty paramSettableAnyProperty, String paramString, Object paramObject)
  {
    _buffered = new PropertyValue.Any(_buffered, paramObject, paramSettableAnyProperty, paramString);
  }
  
  public void bufferMapProperty(Object paramObject1, Object paramObject2)
  {
    _buffered = new PropertyValue.Map(_buffered, paramObject2, paramObject1);
  }
  
  public void bufferProperty(SettableBeanProperty paramSettableBeanProperty, Object paramObject)
  {
    _buffered = new PropertyValue.Regular(_buffered, paramObject, paramSettableBeanProperty);
  }
  
  protected PropertyValue buffered()
  {
    return _buffered;
  }
  
  protected final Object[] getParameters(Object[] paramArrayOfObject)
  {
    if (paramArrayOfObject != null)
    {
      int i = 0;
      int j = _creatorParameters.length;
      while (i < j)
      {
        if (_creatorParameters[i] == null)
        {
          Object localObject = paramArrayOfObject[i];
          if (localObject != null) {
            _creatorParameters[i] = localObject;
          }
        }
        i += 1;
      }
    }
    return _creatorParameters;
  }
  
  public Object handleIdValue(DeserializationContext paramDeserializationContext, Object paramObject)
    throws IOException
  {
    Object localObject = paramObject;
    if (_objectIdReader != null)
    {
      localObject = paramObject;
      if (_idValue != null)
      {
        paramDeserializationContext.findObjectId(_idValue, _objectIdReader.generator).bindItem(paramObject);
        paramDeserializationContext = _objectIdReader.idProperty;
        localObject = paramObject;
        if (paramDeserializationContext != null) {
          localObject = paramDeserializationContext.setAndReturn(paramObject, _idValue);
        }
      }
    }
    return localObject;
  }
  
  public void inject(SettableBeanProperty[] paramArrayOfSettableBeanProperty)
  {
    int i = 0;
    int j = paramArrayOfSettableBeanProperty.length;
    while (i < j)
    {
      SettableBeanProperty localSettableBeanProperty = paramArrayOfSettableBeanProperty[i];
      if (localSettableBeanProperty != null) {
        _creatorParameters[i] = _context.findInjectableValue(localSettableBeanProperty.getInjectableValueId(), localSettableBeanProperty, null);
      }
      i += 1;
    }
  }
  
  public boolean isComplete()
  {
    return _paramsNeeded <= 0;
  }
  
  public boolean readIdProperty(String paramString)
    throws IOException
  {
    if ((_objectIdReader != null) && (paramString.equals(_objectIdReader.propertyName.getSimpleName())))
    {
      _idValue = _objectIdReader.readObjectReference(_parser, _context);
      return true;
    }
    return false;
  }
}

/* Location:
 * Qualified Name:     com.fasterxml.jackson.databind.deser.impl.PropertyValueBuffer
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */