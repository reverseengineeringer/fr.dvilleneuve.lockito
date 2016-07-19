package com.facebook.stetho.inspector.jsonrpc.protocol;

import android.annotation.SuppressLint;
import com.facebook.stetho.json.annotation.JsonProperty;
import javax.annotation.Nullable;
import org.json.JSONObject;

@SuppressLint({"UsingDefaultJsonDeserializer", "EmptyJsonPropertyUse"})
public class JsonRpcEvent
{
  @JsonProperty(required=true)
  public String method;
  @JsonProperty
  public JSONObject params;
  
  public JsonRpcEvent() {}
  
  public JsonRpcEvent(String paramString, @Nullable JSONObject paramJSONObject)
  {
    method = paramString;
    params = paramJSONObject;
  }
}

/* Location:
 * Qualified Name:     com.facebook.stetho.inspector.jsonrpc.protocol.JsonRpcEvent
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */