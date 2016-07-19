package com.facebook.stetho.inspector.protocol.module;

import com.facebook.stetho.inspector.elements.Origin;
import com.facebook.stetho.json.annotation.JsonProperty;

class CSS$CSSRule
{
  @JsonProperty
  public Origin origin;
  @JsonProperty(required=true)
  public CSS.SelectorList selectorList;
  @JsonProperty
  public CSS.CSSStyle style;
  @JsonProperty
  public String styleSheetId;
}

/* Location:
 * Qualified Name:     com.facebook.stetho.inspector.protocol.module.CSS.CSSRule
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */