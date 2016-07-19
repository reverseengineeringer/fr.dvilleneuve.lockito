package com.fasterxml.jackson.databind.deser.impl;

import java.io.IOException;

public class ReadableObjectId
{
  public final Object id;
  public Object item;
  
  public ReadableObjectId(Object paramObject)
  {
    id = paramObject;
  }
  
  public void bindItem(Object paramObject)
    throws IOException
  {
    if (item != null) {
      throw new IllegalStateException("Already had POJO for id (" + id.getClass().getName() + ") [" + id + "]");
    }
    item = paramObject;
  }
}

/* Location:
 * Qualified Name:     com.fasterxml.jackson.databind.deser.impl.ReadableObjectId
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */