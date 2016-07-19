package com.facebook.stetho.inspector.protocol.module;

import com.facebook.stetho.inspector.jsonrpc.JsonRpcResult;
import com.facebook.stetho.json.annotation.JsonProperty;
import java.util.List;

class Database$ExecuteSQLResponse
  implements JsonRpcResult
{
  @JsonProperty
  public List<String> columnNames;
  @JsonProperty
  public Database.Error sqlError;
  @JsonProperty
  public List<Object> values;
}

/* Location:
 * Qualified Name:     com.facebook.stetho.inspector.protocol.module.Database.ExecuteSQLResponse
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */