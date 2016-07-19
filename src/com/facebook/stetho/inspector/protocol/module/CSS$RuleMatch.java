package com.facebook.stetho.inspector.protocol.module;

import com.facebook.stetho.json.annotation.JsonProperty;
import java.util.List;

class CSS$RuleMatch
{
  @JsonProperty
  public List<Integer> matchingSelectors;
  @JsonProperty
  public CSS.CSSRule rule;
}

/* Location:
 * Qualified Name:     com.facebook.stetho.inspector.protocol.module.CSS.RuleMatch
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */