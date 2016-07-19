package com.fasterxml.jackson.databind.ser.impl;

import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ser.SerializerCache.TypeKey;

final class JsonSerializerMap$Bucket
{
  public final SerializerCache.TypeKey key;
  public final Bucket next;
  public final JsonSerializer<Object> value;
  
  public JsonSerializerMap$Bucket(Bucket paramBucket, SerializerCache.TypeKey paramTypeKey, JsonSerializer<Object> paramJsonSerializer)
  {
    next = paramBucket;
    key = paramTypeKey;
    value = paramJsonSerializer;
  }
}

/* Location:
 * Qualified Name:     com.fasterxml.jackson.databind.ser.impl.JsonSerializerMap.Bucket
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */