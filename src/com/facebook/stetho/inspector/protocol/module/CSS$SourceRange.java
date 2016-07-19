package com.facebook.stetho.inspector.protocol.module;

import com.facebook.stetho.json.annotation.JsonProperty;

class CSS$SourceRange
{
  @JsonProperty(required=true)
  public int endColumn;
  @JsonProperty(required=true)
  public int endLine;
  @JsonProperty(required=true)
  public int startColumn;
  @JsonProperty(required=true)
  public int startLine;
}

/* Location:
 * Qualified Name:     com.facebook.stetho.inspector.protocol.module.CSS.SourceRange
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */