package com.facebook.stetho.inspector.elements;

import com.facebook.stetho.common.ThreadBound;
import javax.annotation.Nullable;

public abstract interface Descriptor$Host
  extends ThreadBound
{
  @Nullable
  public abstract Descriptor getDescriptor(@Nullable Object paramObject);
  
  public abstract void onAttributeModified(Object paramObject, String paramString1, String paramString2);
  
  public abstract void onAttributeRemoved(Object paramObject, String paramString);
}

/* Location:
 * Qualified Name:     com.facebook.stetho.inspector.elements.Descriptor.Host
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */