package com.facebook.stetho.inspector.network;

import java.io.IOException;
import javax.annotation.Nullable;

public abstract interface NetworkEventReporter$InspectorRequest
  extends NetworkEventReporter.InspectorHeaders
{
  @Nullable
  public abstract byte[] body()
    throws IOException;
  
  public abstract String friendlyName();
  
  @Nullable
  public abstract Integer friendlyNameExtra();
  
  public abstract String id();
  
  public abstract String method();
  
  public abstract String url();
}

/* Location:
 * Qualified Name:     com.facebook.stetho.inspector.network.NetworkEventReporter.InspectorRequest
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */