package com.facebook.stetho.inspector.elements.android;

class MethodInvoker$IntegerMethodInvoker
  extends MethodInvoker.TypedMethodInvoker<Integer>
{
  MethodInvoker$IntegerMethodInvoker()
  {
    super(Integer.TYPE);
  }
  
  Integer convertArgument(String paramString)
  {
    return Integer.valueOf(Integer.parseInt(paramString));
  }
}

/* Location:
 * Qualified Name:     com.facebook.stetho.inspector.elements.android.MethodInvoker.IntegerMethodInvoker
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */