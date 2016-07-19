package com.facebook.stetho.server;

import org.apache.http.protocol.HttpRequestHandlerRegistry;

public abstract interface RegistryInitializer
{
  public abstract HttpRequestHandlerRegistry getRegistry();
}

/* Location:
 * Qualified Name:     com.facebook.stetho.server.RegistryInitializer
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */