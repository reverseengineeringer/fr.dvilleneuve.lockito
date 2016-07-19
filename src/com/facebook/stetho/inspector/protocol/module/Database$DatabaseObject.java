package com.facebook.stetho.inspector.protocol.module;

import com.facebook.stetho.json.annotation.JsonProperty;

public class Database$DatabaseObject
{
  @JsonProperty(required=true)
  public String domain;
  @JsonProperty(required=true)
  public String id;
  @JsonProperty(required=true)
  public String name;
  @JsonProperty(required=true)
  public String version;
}

/* Location:
 * Qualified Name:     com.facebook.stetho.inspector.protocol.module.Database.DatabaseObject
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */