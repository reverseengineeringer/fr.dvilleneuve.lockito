package com.facebook.stetho.inspector.helper;

import android.util.SparseArray;
import java.util.IdentityHashMap;
import java.util.Map;
import javax.annotation.Nullable;
import javax.annotation.concurrent.GuardedBy;

public class ObjectIdMapper
{
  @GuardedBy("mSync")
  private SparseArray<Object> mIdToObjectMap = new SparseArray();
  @GuardedBy("mSync")
  private int mNextId = 1;
  @GuardedBy("mSync")
  private final Map<Object, Integer> mObjectToIdMap = new IdentityHashMap();
  protected final Object mSync = new Object();
  
  public void clear()
  {
    synchronized (mSync)
    {
      SparseArray localSparseArray = mIdToObjectMap;
      mObjectToIdMap.clear();
      mIdToObjectMap = new SparseArray();
      int j = localSparseArray.size();
      int i = 0;
      if (i < j)
      {
        int k = localSparseArray.keyAt(i);
        onUnmapped(localSparseArray.valueAt(i), k);
        i += 1;
      }
    }
  }
  
  public boolean containsId(int paramInt)
  {
    for (;;)
    {
      synchronized (mSync)
      {
        if (mIdToObjectMap.get(paramInt) != null)
        {
          bool = true;
          return bool;
        }
      }
      boolean bool = false;
    }
  }
  
  public boolean containsObject(Object paramObject)
  {
    synchronized (mSync)
    {
      boolean bool = mObjectToIdMap.containsKey(paramObject);
      return bool;
    }
  }
  
  @Nullable
  public Integer getIdForObject(Object paramObject)
  {
    synchronized (mSync)
    {
      paramObject = (Integer)mObjectToIdMap.get(paramObject);
      return (Integer)paramObject;
    }
  }
  
  @Nullable
  public Object getObjectForId(int paramInt)
  {
    synchronized (mSync)
    {
      Object localObject2 = mIdToObjectMap.get(paramInt);
      return localObject2;
    }
  }
  
  protected void onMapped(Object paramObject, int paramInt) {}
  
  protected void onUnmapped(Object paramObject, int paramInt) {}
  
  public int putObject(Object paramObject)
  {
    synchronized (mSync)
    {
      Integer localInteger = (Integer)mObjectToIdMap.get(paramObject);
      if (localInteger != null)
      {
        i = localInteger.intValue();
        return i;
      }
      int i = mNextId;
      mNextId = (i + 1);
      localInteger = Integer.valueOf(i);
      mObjectToIdMap.put(paramObject, localInteger);
      mIdToObjectMap.put(localInteger.intValue(), paramObject);
      onMapped(paramObject, localInteger.intValue());
      return localInteger.intValue();
    }
  }
  
  @Nullable
  public Integer removeObject(Object paramObject)
  {
    synchronized (mSync)
    {
      Integer localInteger = (Integer)mObjectToIdMap.remove(paramObject);
      if (localInteger == null) {
        return null;
      }
      mIdToObjectMap.remove(localInteger.intValue());
      onUnmapped(paramObject, localInteger.intValue());
      return localInteger;
    }
  }
  
  @Nullable
  public Object removeObjectById(int paramInt)
  {
    synchronized (mSync)
    {
      Object localObject2 = mIdToObjectMap.get(paramInt);
      if (localObject2 == null) {
        return null;
      }
      mIdToObjectMap.remove(paramInt);
      mObjectToIdMap.remove(localObject2);
      onUnmapped(localObject2, paramInt);
      return localObject2;
    }
  }
  
  public int size()
  {
    synchronized (mSync)
    {
      int i = mObjectToIdMap.size();
      return i;
    }
  }
}

/* Location:
 * Qualified Name:     com.facebook.stetho.inspector.helper.ObjectIdMapper
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */