package com.facebook.stetho.inspector.network;

import javax.annotation.Nullable;

public abstract interface NetworkEventReporter$InspectorHeaders
{
  @Nullable
  public abstract String firstHeaderValue(String paramString);
  
  public abstract int headerCount();
  
  public abstract String headerName(int paramInt);
  
  public abstract String headerValue(int paramInt);
}

/* Location:
 * Qualified Name:     com.facebook.stetho.inspector.network.NetworkEventReporter.InspectorHeaders
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */