package com.facebook.stetho.inspector.elements.android;

import android.view.View;
import android.view.ViewDebug.ExportedProperty;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import javax.annotation.Nullable;

final class ViewDescriptor$FieldBackedCSSProperty
  extends ViewDescriptor.ViewCSSProperty
{
  private final Field mField;
  
  public ViewDescriptor$FieldBackedCSSProperty(ViewDescriptor paramViewDescriptor, Field paramField, @Nullable String paramString, ViewDebug.ExportedProperty paramExportedProperty)
  {
    super(paramViewDescriptor, paramString, paramExportedProperty);
    mField = paramField;
    mField.setAccessible(true);
  }
  
  public Object getValue(View paramView)
    throws InvocationTargetException, IllegalAccessException
  {
    return mField.get(paramView);
  }
}

/* Location:
 * Qualified Name:     com.facebook.stetho.inspector.elements.android.ViewDescriptor.FieldBackedCSSProperty
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */