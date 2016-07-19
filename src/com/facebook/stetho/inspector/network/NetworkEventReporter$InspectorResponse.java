package com.facebook.stetho.inspector.network;

public abstract interface NetworkEventReporter$InspectorResponse
  extends NetworkEventReporter.InspectorHeaders
{
  public abstract int connectionId();
  
  public abstract boolean connectionReused();
  
  public abstract boolean fromDiskCache();
  
  public abstract String reasonPhrase();
  
  public abstract String requestId();
  
  public abstract int statusCode();
  
  public abstract String url();
}

/* Location:
 * Qualified Name:     com.facebook.stetho.inspector.network.NetworkEventReporter.InspectorResponse
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */