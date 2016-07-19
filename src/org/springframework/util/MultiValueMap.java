package org.springframework.util;

import java.util.List;
import java.util.Map;

public abstract interface MultiValueMap<K, V>
  extends Map<K, List<V>>
{
  public abstract void add(K paramK, V paramV);
  
  public abstract V getFirst(K paramK);
  
  public abstract void set(K paramK, V paramV);
  
  public abstract void setAll(Map<K, V> paramMap);
  
  public abstract Map<K, V> toSingleValueMap();
}

/* Location:
 * Qualified Name:     org.springframework.util.MultiValueMap
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */