package com.fasterxml.jackson.databind;

import com.fasterxml.jackson.core.FormatSchema;
import com.fasterxml.jackson.core.JsonLocation;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import java.io.Closeable;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public class MappingIterator<T>
  implements Iterator<T>, Closeable
{
  protected static final MappingIterator<?> EMPTY_ITERATOR = new MappingIterator(null, null, null, null, false, null);
  protected final boolean _closeParser;
  protected final DeserializationContext _context;
  protected final JsonDeserializer<T> _deserializer;
  protected boolean _hasNextChecked;
  protected JsonParser _parser;
  protected final JavaType _type;
  protected final T _updatedValue;
  
  @Deprecated
  protected MappingIterator(JavaType paramJavaType, JsonParser paramJsonParser, DeserializationContext paramDeserializationContext, JsonDeserializer<?> paramJsonDeserializer)
  {
    this(paramJavaType, paramJsonParser, paramDeserializationContext, paramJsonDeserializer, true, null);
  }
  
  protected MappingIterator(JavaType paramJavaType, JsonParser paramJsonParser, DeserializationContext paramDeserializationContext, JsonDeserializer<?> paramJsonDeserializer, boolean paramBoolean, Object paramObject)
  {
    _type = paramJavaType;
    _parser = paramJsonParser;
    _context = paramDeserializationContext;
    _deserializer = paramJsonDeserializer;
    _closeParser = paramBoolean;
    if (paramObject == null) {}
    for (_updatedValue = null;; _updatedValue = paramObject)
    {
      if ((paramBoolean) && (paramJsonParser != null) && (paramJsonParser.getCurrentToken() == JsonToken.START_ARRAY)) {
        paramJsonParser.clearCurrentToken();
      }
      return;
    }
  }
  
  protected static <T> MappingIterator<T> emptyIterator()
  {
    return EMPTY_ITERATOR;
  }
  
  public void close()
    throws IOException
  {
    if (_parser != null) {
      _parser.close();
    }
  }
  
  public JsonLocation getCurrentLocation()
  {
    return _parser.getCurrentLocation();
  }
  
  public JsonParser getParser()
  {
    return _parser;
  }
  
  public FormatSchema getParserSchema()
  {
    return _parser.getSchema();
  }
  
  public boolean hasNext()
  {
    try
    {
      boolean bool = hasNextValue();
      return bool;
    }
    catch (JsonMappingException localJsonMappingException)
    {
      throw new RuntimeJsonMappingException(localJsonMappingException.getMessage(), localJsonMappingException);
    }
    catch (IOException localIOException)
    {
      throw new RuntimeException(localIOException.getMessage(), localIOException);
    }
  }
  
  public boolean hasNextValue()
    throws IOException
  {
    if (_parser == null) {}
    Object localObject;
    do
    {
      return false;
      if (_hasNextChecked) {
        break;
      }
      localObject = _parser.getCurrentToken();
      _hasNextChecked = true;
      if (localObject != null) {
        break;
      }
      localObject = _parser.nextToken();
      if ((localObject != null) && (localObject != JsonToken.END_ARRAY)) {
        break;
      }
      localObject = _parser;
      _parser = null;
    } while (!_closeParser);
    ((JsonParser)localObject).close();
    return false;
    return true;
  }
  
  public T next()
  {
    try
    {
      Object localObject = nextValue();
      return (T)localObject;
    }
    catch (JsonMappingException localJsonMappingException)
    {
      throw new RuntimeJsonMappingException(localJsonMappingException.getMessage(), localJsonMappingException);
    }
    catch (IOException localIOException)
    {
      throw new RuntimeException(localIOException.getMessage(), localIOException);
    }
  }
  
  public T nextValue()
    throws IOException
  {
    if ((!_hasNextChecked) && (!hasNextValue())) {
      throw new NoSuchElementException();
    }
    if (_parser == null) {
      throw new NoSuchElementException();
    }
    _hasNextChecked = false;
    if (_updatedValue == null) {}
    for (Object localObject = _deserializer.deserialize(_parser, _context);; localObject = _updatedValue)
    {
      _parser.clearCurrentToken();
      return (T)localObject;
      _deserializer.deserialize(_parser, _context, _updatedValue);
    }
  }
  
  public List<T> readAll()
    throws IOException
  {
    return readAll(new ArrayList());
  }
  
  public List<T> readAll(List<T> paramList)
    throws IOException
  {
    while (hasNextValue()) {
      paramList.add(nextValue());
    }
    return paramList;
  }
  
  public void remove()
  {
    throw new UnsupportedOperationException();
  }
}

/* Location:
 * Qualified Name:     com.fasterxml.jackson.databind.MappingIterator
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */