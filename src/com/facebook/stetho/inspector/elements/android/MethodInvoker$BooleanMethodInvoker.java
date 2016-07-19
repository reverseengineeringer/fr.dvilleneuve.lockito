package com.facebook.stetho.inspector.elements.android;

class MethodInvoker$BooleanMethodInvoker
  extends MethodInvoker.TypedMethodInvoker<Boolean>
{
  MethodInvoker$BooleanMethodInvoker()
  {
    super(Boolean.TYPE);
  }
  
  Boolean convertArgument(String paramString)
  {
    return Boolean.valueOf(Boolean.parseBoolean(paramString));
  }
}

/* Location:
 * Qualified Name:     com.facebook.stetho.inspector.elements.android.MethodInvoker.BooleanMethodInvoker
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */