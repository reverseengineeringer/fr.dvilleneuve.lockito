package com.facebook.stetho.inspector.protocol.module;

import com.facebook.stetho.json.annotation.JsonProperty;

public class Database$Error
{
  @JsonProperty(required=true)
  public int code;
  @JsonProperty(required=true)
  public String message;
}

/* Location:
 * Qualified Name:     com.facebook.stetho.inspector.protocol.module.Database.Error
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */