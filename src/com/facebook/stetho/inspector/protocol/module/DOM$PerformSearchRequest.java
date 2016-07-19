package com.facebook.stetho.inspector.protocol.module;

import com.facebook.stetho.json.annotation.JsonProperty;

class DOM$PerformSearchRequest
{
  @JsonProperty
  public Boolean includeUserAgentShadowDOM;
  @JsonProperty(required=true)
  public String query;
}

/* Location:
 * Qualified Name:     com.facebook.stetho.inspector.protocol.module.DOM.PerformSearchRequest
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */