package com.facebook.stetho.inspector.jsonrpc;

import com.facebook.stetho.common.Util;
import com.facebook.stetho.inspector.jsonrpc.protocol.JsonRpcError;

public class JsonRpcException
  extends Exception
{
  private final JsonRpcError mErrorMessage;
  
  public JsonRpcException(JsonRpcError paramJsonRpcError)
  {
    super(code + ": " + message);
    mErrorMessage = ((JsonRpcError)Util.throwIfNull(paramJsonRpcError));
  }
  
  public JsonRpcError getErrorMessage()
  {
    return mErrorMessage;
  }
}

/* Location:
 * Qualified Name:     com.facebook.stetho.inspector.jsonrpc.JsonRpcException
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */