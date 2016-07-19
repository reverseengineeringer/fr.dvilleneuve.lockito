package com.facebook.stetho.inspector.protocol.module;

import com.facebook.stetho.json.annotation.JsonProperty;

class DOM$GetSearchResultsRequest
{
  @JsonProperty(required=true)
  public int fromIndex;
  @JsonProperty(required=true)
  public String searchId;
  @JsonProperty(required=true)
  public int toIndex;
}

/* Location:
 * Qualified Name:     com.facebook.stetho.inspector.protocol.module.DOM.GetSearchResultsRequest
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */