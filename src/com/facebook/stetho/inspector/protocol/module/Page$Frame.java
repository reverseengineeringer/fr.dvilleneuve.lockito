package com.facebook.stetho.inspector.protocol.module;

import com.facebook.stetho.json.annotation.JsonProperty;

class Page$Frame
{
  @JsonProperty(required=true)
  public String id;
  @JsonProperty(required=true)
  public String loaderId;
  @JsonProperty(required=true)
  public String mimeType;
  @JsonProperty
  public String name;
  @JsonProperty
  public String parentId;
  @JsonProperty(required=true)
  public String securityOrigin;
  @JsonProperty(required=true)
  public String url;
}

/* Location:
 * Qualified Name:     com.facebook.stetho.inspector.protocol.module.Page.Frame
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */