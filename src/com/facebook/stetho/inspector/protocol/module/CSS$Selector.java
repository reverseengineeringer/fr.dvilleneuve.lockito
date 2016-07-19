package com.facebook.stetho.inspector.protocol.module;

import com.facebook.stetho.json.annotation.JsonProperty;

class CSS$Selector
{
  @JsonProperty
  public CSS.SourceRange range;
  @JsonProperty(required=true)
  public String value;
}

/* Location:
 * Qualified Name:     com.facebook.stetho.inspector.protocol.module.CSS.Selector
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */