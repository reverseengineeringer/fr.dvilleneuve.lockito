package com.facebook.stetho.inspector.protocol.module;

import com.facebook.stetho.json.annotation.JsonProperty;

class DOM$HighlightNodeRequest
{
  @JsonProperty(required=true)
  public DOM.HighlightConfig highlightConfig;
  @JsonProperty
  public Integer nodeId;
  @JsonProperty
  public String objectId;
}

/* Location:
 * Qualified Name:     com.facebook.stetho.inspector.protocol.module.DOM.HighlightNodeRequest
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */