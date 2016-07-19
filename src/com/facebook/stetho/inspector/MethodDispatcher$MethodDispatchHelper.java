package com.facebook.stetho.inspector;

import com.facebook.stetho.inspector.jsonrpc.JsonRpcException;
import com.facebook.stetho.inspector.jsonrpc.JsonRpcPeer;
import com.facebook.stetho.inspector.jsonrpc.JsonRpcResult;
import com.facebook.stetho.inspector.jsonrpc.protocol.EmptyResult;
import com.facebook.stetho.inspector.protocol.ChromeDevtoolsDomain;
import com.facebook.stetho.json.ObjectMapper;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import javax.annotation.Nullable;
import org.json.JSONException;
import org.json.JSONObject;

class MethodDispatcher$MethodDispatchHelper
{
  private final ChromeDevtoolsDomain mInstance;
  private final Method mMethod;
  private final ObjectMapper mObjectMapper;
  
  public MethodDispatcher$MethodDispatchHelper(ObjectMapper paramObjectMapper, ChromeDevtoolsDomain paramChromeDevtoolsDomain, Method paramMethod)
  {
    mObjectMapper = paramObjectMapper;
    mInstance = paramChromeDevtoolsDomain;
    mMethod = paramMethod;
  }
  
  public JSONObject invoke(JsonRpcPeer paramJsonRpcPeer, @Nullable JSONObject paramJSONObject)
    throws InvocationTargetException, IllegalAccessException, JSONException, JsonRpcException
  {
    paramJsonRpcPeer = mMethod.invoke(mInstance, new Object[] { paramJsonRpcPeer, paramJSONObject });
    if ((paramJsonRpcPeer == null) || ((paramJsonRpcPeer instanceof EmptyResult))) {
      return new JSONObject();
    }
    paramJsonRpcPeer = (JsonRpcResult)paramJsonRpcPeer;
    return (JSONObject)mObjectMapper.convertValue(paramJsonRpcPeer, JSONObject.class);
  }
}

/* Location:
 * Qualified Name:     com.facebook.stetho.inspector.MethodDispatcher.MethodDispatchHelper
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */