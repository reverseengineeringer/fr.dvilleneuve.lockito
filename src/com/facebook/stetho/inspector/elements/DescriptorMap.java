package com.facebook.stetho.inspector.elements;

import com.facebook.stetho.common.Util;
import java.util.IdentityHashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import javax.annotation.Nullable;

public final class DescriptorMap
{
  private Descriptor.Host mHost;
  private boolean mIsInitializing;
  private final Map<Class<?>, Descriptor> mMap = new IdentityHashMap();
  
  @Nullable
  private Descriptor getImpl(Class<?> paramClass)
  {
    while (paramClass != null)
    {
      Descriptor localDescriptor = (Descriptor)mMap.get(paramClass);
      if (localDescriptor != null) {
        return localDescriptor;
      }
      paramClass = paramClass.getSuperclass();
    }
    return null;
  }
  
  public DescriptorMap beginInit()
  {
    Util.throwIf(mIsInitializing);
    mIsInitializing = true;
    return this;
  }
  
  public DescriptorMap endInit()
  {
    Util.throwIfNot(mIsInitializing);
    Util.throwIfNull(mHost);
    mIsInitializing = false;
    Iterator localIterator = mMap.keySet().iterator();
    while (localIterator.hasNext())
    {
      Class localClass = (Class)localIterator.next();
      Descriptor localDescriptor = (Descriptor)mMap.get(localClass);
      if ((localDescriptor instanceof ChainedDescriptor)) {
        ((ChainedDescriptor)localDescriptor).setSuper(getImpl(localClass.getSuperclass()));
      }
      localDescriptor.initialize(mHost);
    }
    return this;
  }
  
  @Nullable
  public Descriptor get(Class<?> paramClass)
  {
    Util.throwIfNull(paramClass);
    Util.throwIf(mIsInitializing);
    return getImpl(paramClass);
  }
  
  public DescriptorMap register(Class<?> paramClass, Descriptor paramDescriptor)
  {
    Util.throwIfNull(paramClass);
    Util.throwIfNull(paramDescriptor);
    Util.throwIf(paramDescriptor.isInitialized());
    Util.throwIfNot(mIsInitializing);
    if (mMap.containsKey(paramClass)) {
      throw new UnsupportedOperationException();
    }
    if (mMap.containsValue(paramDescriptor)) {
      throw new UnsupportedOperationException();
    }
    mMap.put(paramClass, paramDescriptor);
    return this;
  }
  
  public DescriptorMap setHost(Descriptor.Host paramHost)
  {
    Util.throwIfNull(paramHost);
    Util.throwIfNot(mIsInitializing);
    Util.throwIfNotNull(mHost);
    mHost = paramHost;
    return this;
  }
}

/* Location:
 * Qualified Name:     com.facebook.stetho.inspector.elements.DescriptorMap
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */