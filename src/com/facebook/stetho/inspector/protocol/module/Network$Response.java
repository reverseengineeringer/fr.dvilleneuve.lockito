package com.facebook.stetho.inspector.protocol.module;

import com.facebook.stetho.json.annotation.JsonProperty;
import org.json.JSONObject;

public class Network$Response
{
  @JsonProperty(required=true)
  public int connectionId;
  @JsonProperty(required=true)
  public boolean connectionReused;
  @JsonProperty(required=true)
  public Boolean fromDiskCache;
  @JsonProperty(required=true)
  public JSONObject headers;
  @JsonProperty
  public String headersText;
  @JsonProperty(required=true)
  public String mimeType;
  @JsonProperty
  public JSONObject requestHeaders;
  @JsonProperty
  public String requestHeadersTest;
  @JsonProperty(required=true)
  public int status;
  @JsonProperty(required=true)
  public String statusText;
  @JsonProperty
  public Network.ResourceTiming timing;
  @JsonProperty(required=true)
  public String url;
}

/* Location:
 * Qualified Name:     com.facebook.stetho.inspector.protocol.module.Network.Response
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */