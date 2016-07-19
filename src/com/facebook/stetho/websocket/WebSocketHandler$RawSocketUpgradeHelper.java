package com.facebook.stetho.websocket;

import android.net.LocalSocket;
import com.facebook.stetho.server.LocalSocketHttpServerConnection;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.apache.http.HttpServerConnection;
import org.apache.http.protocol.HttpContext;

class WebSocketHandler$RawSocketUpgradeHelper
{
  private final HttpServerConnection mConn;
  private final InputStream mIn;
  private final OutputStream mOut;
  
  private WebSocketHandler$RawSocketUpgradeHelper(HttpServerConnection paramHttpServerConnection, InputStream paramInputStream, OutputStream paramOutputStream)
  {
    mConn = paramHttpServerConnection;
    mIn = paramInputStream;
    mOut = paramOutputStream;
  }
  
  public static RawSocketUpgradeHelper fromApacheContext(HttpContext paramHttpContext)
    throws IOException
  {
    paramHttpContext = (LocalSocketHttpServerConnection)paramHttpContext.getAttribute("http.connection");
    LocalSocket localLocalSocket = paramHttpContext.getSocket();
    return new RawSocketUpgradeHelper(paramHttpContext, joinInputStreams(new InputStream[] { new ByteArrayInputStream(paramHttpContext.clearInputBuffer()), localLocalSocket.getInputStream() }), localLocalSocket.getOutputStream());
  }
  
  private static InputStream joinInputStreams(InputStream... paramVarArgs)
    throws IOException
  {
    return new CompositeInputStream(paramVarArgs);
  }
  
  public InputStream getInputStream()
  {
    return mIn;
  }
  
  public OutputStream getOutputStream()
  {
    return mOut;
  }
  
  public HttpServerConnection getServerConnection()
  {
    return mConn;
  }
}

/* Location:
 * Qualified Name:     com.facebook.stetho.websocket.WebSocketHandler.RawSocketUpgradeHelper
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */