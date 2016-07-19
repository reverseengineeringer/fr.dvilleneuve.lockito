package com.facebook.stetho.inspector.protocol.module;

import com.facebook.stetho.json.annotation.JsonProperty;

public class Network$ResourceTiming
{
  @JsonProperty(required=true)
  public double connectionEnd;
  @JsonProperty(required=true)
  public double connectionStart;
  @JsonProperty(required=true)
  public double dnsEnd;
  @JsonProperty(required=true)
  public double dnsStart;
  @JsonProperty(required=true)
  public double proxyEnd;
  @JsonProperty(required=true)
  public double proxyStart;
  @JsonProperty(required=true)
  public double receivedHeadersEnd;
  @JsonProperty(required=true)
  public double requestTime;
  @JsonProperty(required=true)
  public double sendEnd;
  @JsonProperty(required=true)
  public double sendStart;
  @JsonProperty(required=true)
  public double sslEnd;
  @JsonProperty(required=true)
  public double sslStart;
}

/* Location:
 * Qualified Name:     com.facebook.stetho.inspector.protocol.module.Network.ResourceTiming
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */