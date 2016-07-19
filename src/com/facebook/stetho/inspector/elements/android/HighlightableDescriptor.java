package com.facebook.stetho.inspector.elements.android;

import android.view.View;
import javax.annotation.Nullable;

abstract interface HighlightableDescriptor
{
  @Nullable
  public abstract View getViewForHighlighting(Object paramObject);
}

/* Location:
 * Qualified Name:     com.facebook.stetho.inspector.elements.android.HighlightableDescriptor
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */