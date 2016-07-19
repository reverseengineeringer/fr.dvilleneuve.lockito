package com.fasterxml.jackson.databind.deser.impl;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.SettableBeanProperty;
import com.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import com.fasterxml.jackson.databind.jsontype.TypeIdResolver;
import com.fasterxml.jackson.databind.util.TokenBuffer;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class ExternalTypeHandler
{
  private final HashMap<String, Integer> _nameToPropertyIndex;
  private final ExtTypedProperty[] _properties;
  private final TokenBuffer[] _tokens;
  private final String[] _typeIds;
  
  protected ExternalTypeHandler(ExternalTypeHandler paramExternalTypeHandler)
  {
    _properties = _properties;
    _nameToPropertyIndex = _nameToPropertyIndex;
    int i = _properties.length;
    _typeIds = new String[i];
    _tokens = new TokenBuffer[i];
  }
  
  protected ExternalTypeHandler(ExtTypedProperty[] paramArrayOfExtTypedProperty, HashMap<String, Integer> paramHashMap, String[] paramArrayOfString, TokenBuffer[] paramArrayOfTokenBuffer)
  {
    _properties = paramArrayOfExtTypedProperty;
    _nameToPropertyIndex = paramHashMap;
    _typeIds = paramArrayOfString;
    _tokens = paramArrayOfTokenBuffer;
  }
  
  protected final Object _deserialize(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext, int paramInt, String paramString)
    throws IOException, JsonProcessingException
  {
    TokenBuffer localTokenBuffer = new TokenBuffer(paramJsonParser);
    localTokenBuffer.writeStartArray();
    localTokenBuffer.writeString(paramString);
    paramString = _tokens[paramInt].asParser(paramJsonParser);
    paramString.nextToken();
    localTokenBuffer.copyCurrentStructure(paramString);
    localTokenBuffer.writeEndArray();
    paramJsonParser = localTokenBuffer.asParser(paramJsonParser);
    paramJsonParser.nextToken();
    return _properties[paramInt].getProperty().deserialize(paramJsonParser, paramDeserializationContext);
  }
  
  protected final void _deserializeAndSet(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext, Object paramObject, int paramInt, String paramString)
    throws IOException, JsonProcessingException
  {
    TokenBuffer localTokenBuffer = new TokenBuffer(paramJsonParser);
    localTokenBuffer.writeStartArray();
    localTokenBuffer.writeString(paramString);
    paramString = _tokens[paramInt].asParser(paramJsonParser);
    paramString.nextToken();
    localTokenBuffer.copyCurrentStructure(paramString);
    localTokenBuffer.writeEndArray();
    paramJsonParser = localTokenBuffer.asParser(paramJsonParser);
    paramJsonParser.nextToken();
    _properties[paramInt].getProperty().deserializeAndSet(paramJsonParser, paramDeserializationContext, paramObject);
  }
  
  public Object complete(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext, PropertyValueBuffer paramPropertyValueBuffer, PropertyBasedCreator paramPropertyBasedCreator)
    throws IOException, JsonProcessingException
  {
    int j = _properties.length;
    Object[] arrayOfObject = new Object[j];
    int i = 0;
    while (i < j)
    {
      String str = _typeIds[i];
      if (str == null)
      {
        if (_tokens[i] == null)
        {
          i += 1;
        }
        else
        {
          if (!_properties[i].hasDefaultType()) {
            throw paramDeserializationContext.mappingException("Missing external type id property '" + _properties[i].getTypePropertyName() + "'");
          }
          str = _properties[i].getDefaultTypeId();
        }
      }
      else
      {
        while (_tokens[i] != null) {
          for (;;)
          {
            arrayOfObject[i] = _deserialize(paramJsonParser, paramDeserializationContext, i, str);
          }
        }
        paramJsonParser = _properties[i].getProperty();
        throw paramDeserializationContext.mappingException("Missing property '" + paramJsonParser.getName() + "' for external type id '" + _properties[i].getTypePropertyName());
      }
    }
    i = 0;
    while (i < j)
    {
      paramJsonParser = _properties[i].getProperty();
      if (paramPropertyBasedCreator.findCreatorProperty(paramJsonParser.getName()) != null) {
        paramPropertyValueBuffer.assignParameter(paramJsonParser.getCreatorIndex(), arrayOfObject[i]);
      }
      i += 1;
    }
    paramJsonParser = paramPropertyBasedCreator.build(paramDeserializationContext, paramPropertyValueBuffer);
    i = 0;
    while (i < j)
    {
      paramDeserializationContext = _properties[i].getProperty();
      if (paramPropertyBasedCreator.findCreatorProperty(paramDeserializationContext.getName()) == null) {
        paramDeserializationContext.set(paramJsonParser, arrayOfObject[i]);
      }
      i += 1;
    }
    return paramJsonParser;
  }
  
  public Object complete(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext, Object paramObject)
    throws IOException, JsonProcessingException
  {
    int i = 0;
    int j = _properties.length;
    Object localObject2;
    if (i < j)
    {
      localObject2 = _typeIds[i];
      if (localObject2 == null)
      {
        TokenBuffer localTokenBuffer = _tokens[i];
        if (localTokenBuffer == null) {}
        for (;;)
        {
          i += 1;
          break;
          JsonToken localJsonToken = localTokenBuffer.firstToken();
          localObject1 = localObject2;
          if (localJsonToken == null) {
            break label200;
          }
          localObject1 = localObject2;
          if (!localJsonToken.isScalarValue()) {
            break label200;
          }
          localObject2 = localTokenBuffer.asParser(paramJsonParser);
          ((JsonParser)localObject2).nextToken();
          localObject1 = _properties[i].getProperty();
          localObject2 = TypeDeserializer.deserializeIfNatural((JsonParser)localObject2, paramDeserializationContext, ((SettableBeanProperty)localObject1).getType());
          if (localObject2 == null) {
            break label137;
          }
          ((SettableBeanProperty)localObject1).set(paramObject, localObject2);
        }
        label137:
        if (!_properties[i].hasDefaultType()) {
          throw paramDeserializationContext.mappingException("Missing external type id property '" + _properties[i].getTypePropertyName() + "'");
        }
      }
    }
    for (Object localObject1 = _properties[i].getDefaultTypeId();; localObject1 = localObject2)
    {
      label200:
      _deserializeAndSet(paramJsonParser, paramDeserializationContext, paramObject, i, (String)localObject1);
      break;
      if (_tokens[i] == null)
      {
        paramJsonParser = _properties[i].getProperty();
        throw paramDeserializationContext.mappingException("Missing property '" + paramJsonParser.getName() + "' for external type id '" + _properties[i].getTypePropertyName());
        return paramObject;
      }
    }
  }
  
  public boolean handlePropertyValue(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext, String paramString, Object paramObject)
    throws IOException, JsonProcessingException
  {
    int j = 0;
    Integer localInteger = (Integer)_nameToPropertyIndex.get(paramString);
    if (localInteger == null) {
      return false;
    }
    int k = localInteger.intValue();
    int i;
    if (_properties[k].hasTypePropertyName(paramString))
    {
      _typeIds[k] = paramJsonParser.getText();
      paramJsonParser.skipChildren();
      if ((paramObject != null) && (_tokens[k] != null)) {
        i = 1;
      }
    }
    for (;;)
    {
      if (i != 0)
      {
        paramString = _typeIds[k];
        _typeIds[k] = null;
        _deserializeAndSet(paramJsonParser, paramDeserializationContext, paramObject, k, paramString);
        _tokens[k] = null;
      }
      return true;
      i = 0;
      continue;
      paramString = new TokenBuffer(paramJsonParser);
      paramString.copyCurrentStructure(paramJsonParser);
      _tokens[k] = paramString;
      i = j;
      if (paramObject != null)
      {
        i = j;
        if (_typeIds[k] != null) {
          i = 1;
        }
      }
    }
  }
  
  public boolean handleTypePropertyValue(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext, String paramString, Object paramObject)
    throws IOException, JsonProcessingException
  {
    int j = 0;
    Integer localInteger = (Integer)_nameToPropertyIndex.get(paramString);
    if (localInteger == null) {
      return false;
    }
    int k = localInteger.intValue();
    if (!_properties[k].hasTypePropertyName(paramString)) {
      return false;
    }
    paramString = paramJsonParser.getText();
    int i = j;
    if (paramObject != null)
    {
      i = j;
      if (_tokens[k] != null) {
        i = 1;
      }
    }
    if (i != 0)
    {
      _deserializeAndSet(paramJsonParser, paramDeserializationContext, paramObject, k, paramString);
      _tokens[k] = null;
    }
    for (;;)
    {
      return true;
      _typeIds[k] = paramString;
    }
  }
  
  public ExternalTypeHandler start()
  {
    return new ExternalTypeHandler(this);
  }
  
  public static class Builder
  {
    private final HashMap<String, Integer> _nameToPropertyIndex = new HashMap();
    private final ArrayList<ExternalTypeHandler.ExtTypedProperty> _properties = new ArrayList();
    
    public void addExternal(SettableBeanProperty paramSettableBeanProperty, TypeDeserializer paramTypeDeserializer)
    {
      Integer localInteger = Integer.valueOf(_properties.size());
      _properties.add(new ExternalTypeHandler.ExtTypedProperty(paramSettableBeanProperty, paramTypeDeserializer));
      _nameToPropertyIndex.put(paramSettableBeanProperty.getName(), localInteger);
      _nameToPropertyIndex.put(paramTypeDeserializer.getPropertyName(), localInteger);
    }
    
    public ExternalTypeHandler build()
    {
      return new ExternalTypeHandler((ExternalTypeHandler.ExtTypedProperty[])_properties.toArray(new ExternalTypeHandler.ExtTypedProperty[_properties.size()]), _nameToPropertyIndex, null, null);
    }
  }
  
  private static final class ExtTypedProperty
  {
    private final SettableBeanProperty _property;
    private final TypeDeserializer _typeDeserializer;
    private final String _typePropertyName;
    
    public ExtTypedProperty(SettableBeanProperty paramSettableBeanProperty, TypeDeserializer paramTypeDeserializer)
    {
      _property = paramSettableBeanProperty;
      _typeDeserializer = paramTypeDeserializer;
      _typePropertyName = paramTypeDeserializer.getPropertyName();
    }
    
    public String getDefaultTypeId()
    {
      Class localClass = _typeDeserializer.getDefaultImpl();
      if (localClass == null) {
        return null;
      }
      return _typeDeserializer.getTypeIdResolver().idFromValueAndType(null, localClass);
    }
    
    public SettableBeanProperty getProperty()
    {
      return _property;
    }
    
    public String getTypePropertyName()
    {
      return _typePropertyName;
    }
    
    public boolean hasDefaultType()
    {
      return _typeDeserializer.getDefaultImpl() != null;
    }
    
    public boolean hasTypePropertyName(String paramString)
    {
      return paramString.equals(_typePropertyName);
    }
  }
}

/* Location:
 * Qualified Name:     com.fasterxml.jackson.databind.deser.impl.ExternalTypeHandler
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */