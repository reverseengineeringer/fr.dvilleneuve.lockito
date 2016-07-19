package com.facebook.stetho.inspector.protocol.module;

import com.facebook.stetho.json.annotation.JsonProperty;

public class Network$LoadingFinishedParams
{
  @JsonProperty(required=true)
  public String requestId;
  @JsonProperty(required=true)
  public double timestamp;
}

/* Location:
 * Qualified Name:     com.facebook.stetho.inspector.protocol.module.Network.LoadingFinishedParams
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */