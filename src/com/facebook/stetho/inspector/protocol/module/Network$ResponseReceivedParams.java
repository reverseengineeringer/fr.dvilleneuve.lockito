package com.facebook.stetho.inspector.protocol.module;

import com.facebook.stetho.json.annotation.JsonProperty;

public class Network$ResponseReceivedParams
{
  @JsonProperty(required=true)
  public String frameId;
  @JsonProperty(required=true)
  public String loaderId;
  @JsonProperty(required=true)
  public String requestId;
  @JsonProperty(required=true)
  public Network.Response response;
  @JsonProperty(required=true)
  public double timestamp;
  @JsonProperty(required=true)
  public Page.ResourceType type;
}

/* Location:
 * Qualified Name:     com.facebook.stetho.inspector.protocol.module.Network.ResponseReceivedParams
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */