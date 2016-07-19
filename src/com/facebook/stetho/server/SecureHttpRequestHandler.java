package com.facebook.stetho.server;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.Credentials;
import android.net.LocalSocket;
import com.facebook.stetho.common.LogUtil;
import java.io.IOException;
import org.apache.http.HttpConnection;
import org.apache.http.HttpException;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.entity.StringEntity;
import org.apache.http.protocol.HttpContext;
import org.apache.http.protocol.HttpRequestHandler;

public abstract class SecureHttpRequestHandler
  implements HttpRequestHandler
{
  private static final Class<?> TAG = SecureHttpRequestHandler.class;
  private final Context mContext;
  
  public SecureHttpRequestHandler(Context paramContext)
  {
    mContext = paramContext;
  }
  
  private static void enforcePermission(Context paramContext, LocalSocket paramLocalSocket)
    throws IOException, PeerAuthorizationException
  {
    paramLocalSocket = paramLocalSocket.getPeerCredentials();
    int i = paramLocalSocket.getUid();
    int j = paramLocalSocket.getPid();
    if (LogUtil.isLoggable(2)) {
      LogUtil.v("Got request from uid=%d, pid=%d", new Object[] { Integer.valueOf(i), Integer.valueOf(j) });
    }
    if (paramContext.checkPermission("android.permission.DUMP", j, i) != 0) {
      throw new PeerAuthorizationException("Peer pid=" + j + ", uid=" + i + " does not have " + "android.permission.DUMP");
    }
  }
  
  private void ensureSecureRequest(HttpRequest paramHttpRequest, HttpContext paramHttpContext)
    throws PeerAuthorizationException, IOException
  {
    paramHttpRequest = (HttpConnection)paramHttpContext.getAttribute("http.connection");
    if (!(paramHttpRequest instanceof LocalSocketHttpServerConnection)) {
      throw new PeerAuthorizationException("Unexpected connection class: " + paramHttpRequest.getClass().getName());
    }
    paramHttpRequest = ((LocalSocketHttpServerConnection)paramHttpRequest).getSocket();
    enforcePermission(mContext, paramHttpRequest);
  }
  
  @SuppressLint({"LogMethodNoExceptionInCatch"})
  public final void handle(HttpRequest paramHttpRequest, HttpResponse paramHttpResponse, HttpContext paramHttpContext)
    throws HttpException, IOException
  {
    try
    {
      ensureSecureRequest(paramHttpRequest, paramHttpContext);
      handleSecured(paramHttpRequest, paramHttpResponse, paramHttpContext);
      return;
    }
    catch (PeerAuthorizationException paramHttpRequest)
    {
      LogUtil.e("Unauthorized request: " + paramHttpRequest.getMessage());
      paramHttpResponse.setStatusCode(403);
      paramHttpResponse.setEntity(new StringEntity(paramHttpRequest.getMessage() + "\n", "UTF-8"));
    }
  }
  
  protected abstract void handleSecured(HttpRequest paramHttpRequest, HttpResponse paramHttpResponse, HttpContext paramHttpContext)
    throws HttpException, IOException;
}

/* Location:
 * Qualified Name:     com.facebook.stetho.server.SecureHttpRequestHandler
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */