package com.facebook.stetho.inspector.elements.android;

import android.view.View;
import android.view.ViewDebug.ExportedProperty;
import java.lang.reflect.InvocationTargetException;
import javax.annotation.Nullable;

abstract class ViewDescriptor$ViewCSSProperty
{
  private final ViewDebug.ExportedProperty mAnnotation;
  private final String mCSSName;
  
  public ViewDescriptor$ViewCSSProperty(ViewDescriptor paramViewDescriptor, @Nullable String paramString, ViewDebug.ExportedProperty paramExportedProperty)
  {
    mCSSName = paramString;
    mAnnotation = paramExportedProperty;
  }
  
  @Nullable
  public final ViewDebug.ExportedProperty getAnnotation()
  {
    return mAnnotation;
  }
  
  public final String getCSSName()
  {
    return mCSSName;
  }
  
  public abstract Object getValue(View paramView)
    throws InvocationTargetException, IllegalAccessException;
}

/* Location:
 * Qualified Name:     com.facebook.stetho.inspector.elements.android.ViewDescriptor.ViewCSSProperty
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */