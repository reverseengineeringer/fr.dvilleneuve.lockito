package com.facebook.stetho.inspector.elements.android;

import android.view.View;
import com.facebook.stetho.inspector.elements.Descriptor.Host;
import javax.annotation.Nullable;

abstract interface AndroidDescriptorHost
  extends Descriptor.Host
{
  @Nullable
  public abstract View getHighlightingView(@Nullable Object paramObject);
}

/* Location:
 * Qualified Name:     com.facebook.stetho.inspector.elements.android.AndroidDescriptorHost
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */