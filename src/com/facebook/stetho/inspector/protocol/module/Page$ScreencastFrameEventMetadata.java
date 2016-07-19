package com.facebook.stetho.inspector.protocol.module;

import com.facebook.stetho.json.annotation.JsonProperty;

public class Page$ScreencastFrameEventMetadata
{
  @JsonProperty(required=true)
  public int deviceHeight;
  @JsonProperty(required=true)
  public int deviceWidth;
  @JsonProperty(required=true)
  public int offsetTop;
  @JsonProperty(required=true)
  public int pageScaleFactor;
  @JsonProperty(required=true)
  public int scrollOffsetX;
  @JsonProperty(required=true)
  public int scrollOffsetY;
}

/* Location:
 * Qualified Name:     com.facebook.stetho.inspector.protocol.module.Page.ScreencastFrameEventMetadata
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */