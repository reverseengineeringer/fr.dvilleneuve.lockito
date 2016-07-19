package com.facebook.stetho.inspector.network;

import java.io.IOException;

public class DefaultResponseHandler
  implements ResponseHandler
{
  private int mBytesRead = 0;
  private int mDecodedBytesRead = -1;
  private final NetworkEventReporter mEventReporter;
  private final String mRequestId;
  
  public DefaultResponseHandler(NetworkEventReporter paramNetworkEventReporter, String paramString)
  {
    mEventReporter = paramNetworkEventReporter;
    mRequestId = paramString;
  }
  
  private void reportDataReceived()
  {
    NetworkEventReporter localNetworkEventReporter = mEventReporter;
    String str = mRequestId;
    int j = mBytesRead;
    if (mDecodedBytesRead >= 0) {}
    for (int i = mDecodedBytesRead;; i = mBytesRead)
    {
      localNetworkEventReporter.dataReceived(str, j, i);
      return;
    }
  }
  
  public void onEOF()
  {
    reportDataReceived();
    mEventReporter.responseReadFinished(mRequestId);
  }
  
  public void onError(IOException paramIOException)
  {
    reportDataReceived();
    mEventReporter.responseReadFailed(mRequestId, paramIOException.toString());
  }
  
  public void onRead(int paramInt)
  {
    mBytesRead += paramInt;
  }
  
  public void onReadDecoded(int paramInt)
  {
    if (mDecodedBytesRead == -1) {
      mDecodedBytesRead = 0;
    }
    mDecodedBytesRead += paramInt;
  }
}

/* Location:
 * Qualified Name:     com.facebook.stetho.inspector.network.DefaultResponseHandler
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */