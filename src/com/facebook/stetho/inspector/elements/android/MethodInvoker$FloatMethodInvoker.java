package com.facebook.stetho.inspector.elements.android;

class MethodInvoker$FloatMethodInvoker
  extends MethodInvoker.TypedMethodInvoker<Float>
{
  MethodInvoker$FloatMethodInvoker()
  {
    super(Float.TYPE);
  }
  
  Float convertArgument(String paramString)
  {
    return Float.valueOf(Float.parseFloat(paramString));
  }
}

/* Location:
 * Qualified Name:     com.facebook.stetho.inspector.elements.android.MethodInvoker.FloatMethodInvoker
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */