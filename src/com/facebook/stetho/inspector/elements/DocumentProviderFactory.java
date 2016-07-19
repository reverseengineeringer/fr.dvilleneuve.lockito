package com.facebook.stetho.inspector.elements;

import com.facebook.stetho.common.ThreadBound;

public abstract interface DocumentProviderFactory
  extends ThreadBound
{
  public abstract DocumentProvider create();
}

/* Location:
 * Qualified Name:     com.facebook.stetho.inspector.elements.DocumentProviderFactory
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */