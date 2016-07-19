package com.facebook.stetho.server;

import android.net.LocalSocket;
import com.facebook.stetho.common.Util;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import org.apache.http.impl.AbstractHttpServerConnection;
import org.apache.http.impl.io.AbstractSessionInputBuffer;
import org.apache.http.impl.io.AbstractSessionOutputBuffer;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;

public class LocalSocketHttpServerConnection
  extends AbstractHttpServerConnection
{
  private volatile LocalSocketSessionInputBuffer mInputBuffer;
  private volatile boolean mOpen;
  private volatile LocalSocket mSocket;
  
  private void close(boolean paramBoolean)
    throws IOException
  {
    if (!mOpen) {
      return;
    }
    mOpen = false;
    if (paramBoolean) {
      doFlush();
    }
    mSocket.close();
  }
  
  protected void assertOpen()
    throws IllegalStateException
  {
    Util.throwIfNot(mOpen);
  }
  
  public void bind(LocalSocket paramLocalSocket, HttpParams paramHttpParams)
    throws IOException
  {
    Util.throwIfNull(paramLocalSocket);
    Util.throwIfNull(paramHttpParams);
    mSocket = paramLocalSocket;
    int i = HttpConnectionParams.getSocketBufferSize(paramHttpParams);
    mInputBuffer = new LocalSocketSessionInputBuffer(paramLocalSocket, i, paramHttpParams);
    init(mInputBuffer, new LocalSocketSessionOutputBuffer(paramLocalSocket, i, paramHttpParams), paramHttpParams);
    mOpen = true;
  }
  
  public byte[] clearInputBuffer()
  {
    return mInputBuffer.clearCurrentBuffer();
  }
  
  public void close()
    throws IOException
  {
    close(true);
  }
  
  public LocalSocket getSocket()
  {
    return mSocket;
  }
  
  public int getSocketTimeout()
  {
    try
    {
      int i = mSocket.getSoTimeout();
      return i;
    }
    catch (IOException localIOException)
    {
      Util.throwIfNot(mSocket.isClosed());
    }
    return -1;
  }
  
  public boolean isOpen()
  {
    return mOpen;
  }
  
  public void setSocketTimeout(int paramInt)
  {
    try
    {
      mSocket.setSoTimeout(paramInt);
      return;
    }
    catch (IOException localIOException)
    {
      Util.throwIfNot(mSocket.isClosed());
    }
  }
  
  public void shutdown()
    throws IOException
  {
    close(false);
  }
  
  private static class LocalSocketSessionInputBuffer
    extends AbstractSessionInputBuffer
  {
    public LocalSocketSessionInputBuffer(LocalSocket paramLocalSocket, int paramInt, HttpParams paramHttpParams)
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
  
  private static class LocalSocketSessionOutputBuffer
    extends AbstractSessionOutputBuffer
  {
    public LocalSocketSessionOutputBuffer(LocalSocket paramLocalSocket, int paramInt, HttpParams paramHttpParams)
      throws IOException
    {
      init(paramLocalSocket.getOutputStream(), paramInt, paramHttpParams);
    }
    
    public void flush()
      throws IOException
    {
      flushBuffer();
    }
  }
}

/* Location:
 * Qualified Name:     com.facebook.stetho.server.LocalSocketHttpServerConnection
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */