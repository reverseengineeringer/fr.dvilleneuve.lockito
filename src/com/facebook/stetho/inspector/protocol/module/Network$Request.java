package com.facebook.stetho.inspector.protocol.module;

import com.facebook.stetho.json.annotation.JsonProperty;
import org.json.JSONObject;

public class Network$Request
{
  @JsonProperty(required=true)
  public JSONObject headers;
  @JsonProperty(required=true)
  public String method;
  @JsonProperty
  public String postData;
  @JsonProperty(required=true)
  public String url;
}

/* Location:
 * Qualified Name:     com.facebook.stetho.inspector.protocol.module.Network.Request
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */