package com.facebook.stetho.inspector.console;

public abstract interface RuntimeRepl
{
  public abstract Object evaluate(String paramString)
    throws Throwable;
}

/* Location:
 * Qualified Name:     com.facebook.stetho.inspector.console.RuntimeRepl
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */