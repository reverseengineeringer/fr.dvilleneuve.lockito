package com.facebook.stetho;

import com.facebook.stetho.inspector.protocol.ChromeDevtoolsDomain;

public abstract interface InspectorModulesProvider
{
  public abstract Iterable<ChromeDevtoolsDomain> get();
}

/* Location:
 * Qualified Name:     com.facebook.stetho.InspectorModulesProvider
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */