package com.facebook.stetho.inspector.elements;

import com.facebook.stetho.common.Accumulator;

public abstract interface Document$UpdateListener
{
  public abstract void onAttributeModified(Object paramObject, String paramString1, String paramString2);
  
  public abstract void onAttributeRemoved(Object paramObject, String paramString);
  
  public abstract void onChildNodeInserted(DocumentView paramDocumentView, Object paramObject, int paramInt1, int paramInt2, Accumulator<Object> paramAccumulator);
  
  public abstract void onChildNodeRemoved(int paramInt1, int paramInt2);
  
  public abstract void onInspectRequested(Object paramObject);
}

/* Location:
 * Qualified Name:     com.facebook.stetho.inspector.elements.Document.UpdateListener
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */