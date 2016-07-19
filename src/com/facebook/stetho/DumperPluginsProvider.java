package com.facebook.stetho;

import com.facebook.stetho.dumpapp.DumperPlugin;

public abstract interface DumperPluginsProvider
{
  public abstract Iterable<DumperPlugin> get();
}

/* Location:
 * Qualified Name:     com.facebook.stetho.DumperPluginsProvider
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */