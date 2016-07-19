package com.facebook.stetho.inspector.jsonrpc;

import javax.annotation.Nullable;

public class PendingRequest
{
  @Nullable
  public final PendingRequestCallback callback;
  public final long requestId;
  
  public PendingRequest(long paramLong, @Nullable PendingRequestCallback paramPendingRequestCallback)
  {
    requestId = paramLong;
    callback = paramPendingRequestCallback;
  }
}

/* Location:
 * Qualified Name:     com.facebook.stetho.inspector.jsonrpc.PendingRequest
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */