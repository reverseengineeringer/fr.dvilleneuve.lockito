package com.facebook.stetho.server;

import android.net.LocalSocket;
import com.facebook.stetho.common.Util;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import org.apache.http.impl.io.AbstractSessionInputBuffer;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;

class LocalSocketHttpServerConnection$LocalSocketSessionInputBuffer
  extends AbstractSessionInputBuffer
{
  public LocalSocketHttpServerConnection$LocalSocketSessionInputBuffer(LocalSocket paramLocalSocket, int paramInt, HttpParams paramHttpParams)
    throws IOException
  {
    if (HttpConnectionParams.isStaleCheckingEnabled(paramHttpParams)) {
      throw new UnsupportedOperationException("Stale connection checking should not be used for local sockets");
    }
    init(paramLocalSocket.getInputStream(), paramInt, paramHttpParams);
  }
  
  public byte[] clearCurrentBuffer()
  {
    ByteArrayOutputStream localByteArrayOutputStream = new ByteArrayOutputStream();
    for (;;)
    {
      try
      {
        if (!hasBufferedData()) {
          break;
        }
        int i = read();
        boolean bool;
        if (i != -1)
        {
          bool = true;
          Util.throwIfNot(bool, "Buffered data cannot EOF", new Object[0]);
          localByteArrayOutputStream.write(i);
        }
        else
        {
          bool = false;
        }
      }
      catch (IOException localIOException)
      {
        throw new RuntimeException(localIOException);
      }
    }
    byte[] arrayOfByte = localIOException.toByteArray();
    return arrayOfByte;
  }
  
  public boolean isDataAvailable(int paramInt)
    throws IOException
  {
    throw new UnsupportedOperationException("CoreConnectionPNames.STALE_CONNECTION_CHECK appears to be true but that can't be?");
  }
}

/* Location:
 * Qualified Name:     com.facebook.stetho.server.LocalSocketHttpServerConnection.LocalSocketSessionInputBuffer
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */