package com.facebook.stetho.inspector.protocol.module;

import com.facebook.stetho.json.annotation.JsonProperty;
import java.util.List;

class Page$FrameResourceTree
{
  @JsonProperty
  public List<FrameResourceTree> childFrames;
  @JsonProperty(required=true)
  public Page.Frame frame;
  @JsonProperty(required=true)
  public List<Page.Resource> resources;
}

/* Location:
 * Qualified Name:     com.facebook.stetho.inspector.protocol.module.Page.FrameResourceTree
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */