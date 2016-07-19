package com.facebook.stetho.inspector.protocol.module;

import com.facebook.stetho.json.annotation.JsonProperty;
import java.util.List;

class CSS$InheritedStyleEntry
{
  @JsonProperty(required=true)
  public CSS.CSSStyle inlineStyle;
  @JsonProperty(required=true)
  public List<CSS.RuleMatch> matchedCSSRules;
}

/* Location:
 * Qualified Name:     com.facebook.stetho.inspector.protocol.module.CSS.InheritedStyleEntry
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */