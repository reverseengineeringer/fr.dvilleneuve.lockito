package com.facebook.stetho.inspector.protocol.module;

import com.facebook.stetho.json.annotation.JsonProperty;

class Database$ExecuteSQLRequest
{
  @JsonProperty(required=true)
  public String databaseId;
  @JsonProperty(required=true)
  public String query;
}

/* Location:
 * Qualified Name:     com.facebook.stetho.inspector.protocol.module.Database.ExecuteSQLRequest
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */