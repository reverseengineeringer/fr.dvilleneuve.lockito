package com.facebook.stetho.inspector.protocol.module;

import com.facebook.stetho.json.annotation.JsonProperty;
import java.util.List;

class Runtime$CallFunctionOnRequest
{
  @JsonProperty
  public List<Runtime.CallArgument> arguments;
  @JsonProperty(required=false)
  public Boolean doNotPauseOnExceptionsAndMuteConsole;
  @JsonProperty
  public String functionDeclaration;
  @JsonProperty(required=false)
  public Boolean generatePreview;
  @JsonProperty
  public String objectId;
  @JsonProperty(required=false)
  public Boolean returnByValue;
}

/* Location:
 * Qualified Name:     com.facebook.stetho.inspector.protocol.module.Runtime.CallFunctionOnRequest
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */