package com.facebook.stetho.dumpapp;

public abstract interface DumperPlugin
{
  public abstract void dump(DumperContext paramDumperContext)
    throws DumpException;
  
  public abstract String getName();
}

/* Location:
 * Qualified Name:     com.facebook.stetho.dumpapp.DumperPlugin
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */