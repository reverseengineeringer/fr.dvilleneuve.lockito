package org.springframework.util;

import java.io.Serializable;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

class CollectionUtils$MultiValueMapAdapter<K, V>
  implements MultiValueMap<K, V>, Serializable
{
  private static final long serialVersionUID = 1L;
  private final Map<K, List<V>> map;
  
  public CollectionUtils$MultiValueMapAdapter(Map<K, List<V>> paramMap)
  {
    Assert.notNull(paramMap, "'map' must not be null");
    map = paramMap;
  }
  
  public void add(K paramK, V paramV)
  {
    List localList = (List)map.get(paramK);
    Object localObject = localList;
    if (localList == null)
    {
      localObject = new LinkedList();
      map.put(paramK, localObject);
    }
    ((List)localObject).add(paramV);
  }
  
  public void clear()
  {
    map.clear();
  }
  
  public boolean containsKey(Object paramObject)
  {
    return map.containsKey(paramObject);
  }
  
  public boolean containsValue(Object paramObject)
  {
    return map.containsValue(paramObject);
  }
  
  public Set<Map.Entry<K, List<V>>> entrySet()
  {
    return map.entrySet();
  }
  
  public boolean equals(Object paramObject)
  {
    if (this == paramObject) {
      return true;
    }
    return map.equals(paramObject);
  }
  
  public List<V> get(Object paramObject)
  {
    return (List)map.get(paramObject);
  }
  
  public V getFirst(K paramK)
  {
    paramK = (List)map.get(paramK);
    if (paramK != null) {
      return (V)paramK.get(0);
    }
    return null;
  }
  
  public int hashCode()
  {
    return map.hashCode();
  }
  
  public boolean isEmpty()
  {
    return map.isEmpty();
  }
  
  public Set<K> keySet()
  {
    return map.keySet();
  }
  
  public List<V> put(K paramK, List<V> paramList)
  {
    return (List)map.put(paramK, paramList);
  }
  
  public void putAll(Map<? extends K, ? extends List<V>> paramMap)
  {
    map.putAll(paramMap);
  }
  
  public List<V> remove(Object paramObject)
  {
    return (List)map.remove(paramObject);
  }
  
  public void set(K paramK, V paramV)
  {
    LinkedList localLinkedList = new LinkedList();
    localLinkedList.add(paramV);
    map.put(paramK, localLinkedList);
  }
  
  public void setAll(Map<K, V> paramMap)
  {
    paramMap = paramMap.entrySet().iterator();
    while (paramMap.hasNext())
    {
      Map.Entry localEntry = (Map.Entry)paramMap.next();
      set(localEntry.getKey(), localEntry.getValue());
    }
  }
  
  public int size()
  {
    return map.size();
  }
  
  public Map<K, V> toSingleValueMap()
  {
    LinkedHashMap localLinkedHashMap = new LinkedHashMap(map.size());
    Iterator localIterator = map.entrySet().iterator();
    while (localIterator.hasNext())
    {
      Map.Entry localEntry = (Map.Entry)localIterator.next();
      localLinkedHashMap.put(localEntry.getKey(), ((List)localEntry.getValue()).get(0));
    }
    return localLinkedHashMap;
  }
  
  public String toString()
  {
    return map.toString();
  }
  
  public Collection<List<V>> values()
  {
    return map.values();
  }
}

/* Location:
 * Qualified Name:     org.springframework.util.CollectionUtils.MultiValueMapAdapter
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */