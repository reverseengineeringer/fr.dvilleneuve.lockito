package com.facebook.stetho.inspector.protocol.module;

import com.facebook.stetho.json.annotation.JsonProperty;

public class Network$RequestWillBeSentParams
{
  @JsonProperty(required=true)
  public String documentURL;
  @JsonProperty(required=true)
  public String frameId;
  @JsonProperty(required=true)
  public Network.Initiator initiator;
  @JsonProperty(required=true)
  public String loaderId;
  @JsonProperty
  public Network.Response redirectResponse;
  @JsonProperty(required=true)
  public Network.Request request;
  @JsonProperty(required=true)
  public String requestId;
  @JsonProperty(required=true)
  public double timestamp;
  @JsonProperty
  public Page.ResourceType type;
}

/* Location:
 * Qualified Name:     com.facebook.stetho.inspector.protocol.module.Network.RequestWillBeSentParams
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */