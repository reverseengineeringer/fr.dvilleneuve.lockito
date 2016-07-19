package com.facebook.stetho.inspector;

public class MismatchedResponseException
  extends MessageHandlingException
{
  public long mRequestId;
  
  public MismatchedResponseException(long paramLong)
  {
    super("Response for request id " + paramLong + ", but no such request is pending");
    mRequestId = paramLong;
  }
  
  public long getRequestId()
  {
    return mRequestId;
  }
}

/* Location:
 * Qualified Name:     com.facebook.stetho.inspector.MismatchedResponseException
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */