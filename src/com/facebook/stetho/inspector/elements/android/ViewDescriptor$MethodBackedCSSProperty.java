package com.facebook.stetho.inspector.elements.android;

import android.view.View;
import android.view.ViewDebug.ExportedProperty;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import javax.annotation.Nullable;

final class ViewDescriptor$MethodBackedCSSProperty
  extends ViewDescriptor.ViewCSSProperty
{
  private final Method mMethod;
  
  public ViewDescriptor$MethodBackedCSSProperty(ViewDescriptor paramViewDescriptor, Method paramMethod, @Nullable String paramString, ViewDebug.ExportedProperty paramExportedProperty)
  {
    super(paramViewDescriptor, paramString, paramExportedProperty);
    mMethod = paramMethod;
    mMethod.setAccessible(true);
  }
  
  public Object getValue(View paramView)
    throws InvocationTargetException, IllegalAccessException
  {
    return mMethod.invoke(paramView, new Object[0]);
  }
}

/* Location:
 * Qualified Name:     com.facebook.stetho.inspector.elements.android.ViewDescriptor.MethodBackedCSSProperty
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */