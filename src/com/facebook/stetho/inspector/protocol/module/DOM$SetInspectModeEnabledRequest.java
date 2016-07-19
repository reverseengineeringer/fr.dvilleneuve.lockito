package com.facebook.stetho.inspector.protocol.module;

import com.facebook.stetho.json.annotation.JsonProperty;

class DOM$SetInspectModeEnabledRequest
{
  @JsonProperty(required=true)
  public boolean enabled;
  @JsonProperty
  public DOM.HighlightConfig highlightConfig;
  @JsonProperty
  public Boolean inspectShadowDOM;
}

/* Location:
 * Qualified Name:     com.facebook.stetho.inspector.protocol.module.DOM.SetInspectModeEnabledRequest
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */