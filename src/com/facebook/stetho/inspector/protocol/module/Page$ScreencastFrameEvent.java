package com.facebook.stetho.inspector.protocol.module;

import com.facebook.stetho.json.annotation.JsonProperty;

public class Page$ScreencastFrameEvent
{
  @JsonProperty(required=true)
  public String data;
  @JsonProperty(required=true)
  public Page.ScreencastFrameEventMetadata metadata;
}

/* Location:
 * Qualified Name:     com.facebook.stetho.inspector.protocol.module.Page.ScreencastFrameEvent
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */