package com.fasterxml.jackson.databind.util;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map.Entry;

public class LRUMap<K, V>
  extends LinkedHashMap<K, V>
  implements Serializable
{
  private static final long serialVersionUID = 1L;
  protected transient int _jdkSerializeMaxEntries;
  protected final int _maxEntries;
  
  public LRUMap(int paramInt1, int paramInt2)
  {
    super(paramInt1, 0.8F, true);
    _maxEntries = paramInt2;
  }
  
  private void readObject(ObjectInputStream paramObjectInputStream)
    throws IOException
  {
    _jdkSerializeMaxEntries = paramObjectInputStream.readInt();
  }
  
  private void writeObject(ObjectOutputStream paramObjectOutputStream)
    throws IOException
  {
    paramObjectOutputStream.writeInt(_jdkSerializeMaxEntries);
  }
  
  protected Object readResolve()
  {
    return new LRUMap(_jdkSerializeMaxEntries, _jdkSerializeMaxEntries);
  }
  
  protected boolean removeEldestEntry(Map.Entry<K, V> paramEntry)
  {
    return size() > _maxEntries;
  }
}

/* Location:
 * Qualified Name:     com.fasterxml.jackson.databind.util.LRUMap
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */