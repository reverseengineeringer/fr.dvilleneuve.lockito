package com.facebook.stetho.inspector.protocol.module;

import com.facebook.stetho.inspector.jsonrpc.JsonRpcResult;
import com.facebook.stetho.json.annotation.JsonProperty;
import java.util.List;

class CSS$GetMatchedStylesForNodeResult
  implements JsonRpcResult
{
  @JsonProperty
  public List<CSS.InheritedStyleEntry> inherited;
  @JsonProperty
  public List<CSS.RuleMatch> matchedCSSRules;
  @JsonProperty
  public List<CSS.PseudoIdMatches> pseudoElements;
}

/* Location:
 * Qualified Name:     com.facebook.stetho.inspector.protocol.module.CSS.GetMatchedStylesForNodeResult
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */