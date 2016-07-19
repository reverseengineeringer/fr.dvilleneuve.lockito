package com.facebook.stetho.inspector.protocol.module;

import com.facebook.stetho.json.annotation.JsonProperty;

class CSS$CSSProperty
{
  @JsonProperty
  public Boolean disabled;
  @JsonProperty
  public Boolean implicit;
  @JsonProperty
  public Boolean important;
  @JsonProperty(required=true)
  public String name;
  @JsonProperty
  public Boolean parsedOk;
  @JsonProperty
  public CSS.SourceRange range;
  @JsonProperty
  public String text;
  @JsonProperty(required=true)
  public String value;
}

/* Location:
 * Qualified Name:     com.facebook.stetho.inspector.protocol.module.CSS.CSSProperty
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */