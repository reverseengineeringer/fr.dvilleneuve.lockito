package com.facebook.stetho.inspector.protocol.module;

import com.facebook.stetho.json.annotation.JsonProperty;
import java.util.ArrayList;
import java.util.List;

class CSS$PseudoIdMatches
{
  @JsonProperty(required=true)
  public List<CSS.RuleMatch> matches = new ArrayList();
  @JsonProperty(required=true)
  public int pseudoId;
}

/* Location:
 * Qualified Name:     com.facebook.stetho.inspector.protocol.module.CSS.PseudoIdMatches
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */