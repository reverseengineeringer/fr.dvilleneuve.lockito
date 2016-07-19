package com.fasterxml.jackson.databind.deser.impl;

import com.fasterxml.jackson.databind.deser.SettableBeanProperty;
import java.io.Serializable;

final class BeanPropertyMap$Bucket
  implements Serializable
{
  private static final long serialVersionUID = 1L;
  public final int index;
  public final String key;
  public final Bucket next;
  public final SettableBeanProperty value;
  
  public BeanPropertyMap$Bucket(Bucket paramBucket, String paramString, SettableBeanProperty paramSettableBeanProperty, int paramInt)
  {
    next = paramBucket;
    key = paramString;
    value = paramSettableBeanProperty;
    index = paramInt;
  }
}

/* Location:
 * Qualified Name:     com.fasterxml.jackson.databind.deser.impl.BeanPropertyMap.Bucket
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */