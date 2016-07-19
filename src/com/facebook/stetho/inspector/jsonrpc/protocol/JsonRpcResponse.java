package com.facebook.stetho.inspector.jsonrpc.protocol;

import android.annotation.SuppressLint;
import com.facebook.stetho.json.annotation.JsonProperty;
import org.json.JSONObject;

@SuppressLint({"UsingDefaultJsonDeserializer", "EmptyJsonPropertyUse"})
public class JsonRpcResponse
{
  @JsonProperty
  public JSONObject error;
  @JsonProperty(required=true)
  public long id;
  @JsonProperty
  public JSONObject result;
}

/* Location:
 * Qualified Name:     com.facebook.stetho.inspector.jsonrpc.protocol.JsonRpcResponse
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */