package com.facebook.stetho.inspector.protocol.module;

import com.facebook.stetho.json.annotation.JsonProperty;
import java.util.List;

class CSS$CSSStyle
{
  @JsonProperty(required=true)
  public List<CSS.CSSProperty> cssProperties;
  @JsonProperty
  public String cssText;
  @JsonProperty
  public CSS.SourceRange range;
  @JsonProperty
  public List<CSS.ShorthandEntry> shorthandEntries;
  @JsonProperty
  public String styleSheetId;
}

/* Location:
 * Qualified Name:     com.facebook.stetho.inspector.protocol.module.CSS.CSSStyle
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */