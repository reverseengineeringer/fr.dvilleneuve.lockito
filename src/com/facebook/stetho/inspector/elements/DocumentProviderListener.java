package com.facebook.stetho.inspector.elements;

public abstract interface DocumentProviderListener
{
  public abstract void onAttributeModified(Object paramObject, String paramString1, String paramString2);
  
  public abstract void onAttributeRemoved(Object paramObject, String paramString);
  
  public abstract void onInspectRequested(Object paramObject);
  
  public abstract void onPossiblyChanged();
}

/* Location:
 * Qualified Name:     com.facebook.stetho.inspector.elements.DocumentProviderListener
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */