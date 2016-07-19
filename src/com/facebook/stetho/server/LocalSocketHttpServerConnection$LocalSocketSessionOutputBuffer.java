package com.facebook.stetho.server;

import android.net.LocalSocket;
import java.io.IOException;
import org.apache.http.impl.io.AbstractSessionOutputBuffer;
import org.apache.http.params.HttpParams;

class LocalSocketHttpServerConnection$LocalSocketSessionOutputBuffer
  extends AbstractSessionOutputBuffer
{
  public LocalSocketHttpServerConnection$LocalSocketSessionOutputBuffer(LocalSocket paramLocalSocket, int paramInt, HttpParams paramHttpParams)
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

/* Location:
 * Qualified Name:     com.facebook.stetho.server.LocalSocketHttpServerConnection.LocalSocketSessionOutputBuffer
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */