package com.facebook.stetho.inspector.protocol.module;

import com.facebook.stetho.json.annotation.JsonProperty;
import java.util.List;

public class Network$Initiator
{
  @JsonProperty
  public List<Console.CallFrame> stackTrace;
  @JsonProperty(required=true)
  public Network.InitiatorType type;
}

/* Location:
 * Qualified Name:     com.facebook.stetho.inspector.protocol.module.Network.Initiator
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */