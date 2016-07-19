package com.facebook.stetho.inspector.protocol.module;

import com.facebook.stetho.json.annotation.JsonProperty;

public class Network$LoadingFailedParams
{
  @JsonProperty(required=true)
  public String errorText;
  @JsonProperty(required=true)
  public String requestId;
  @JsonProperty(required=true)
  public double timestamp;
  @JsonProperty
  public Page.ResourceType type;
}

/* Location:
 * Qualified Name:     com.facebook.stetho.inspector.protocol.module.Network.LoadingFailedParams
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */