package com.facebook.stetho.websocket;

import android.content.Context;
import android.net.LocalSocket;
import android.util.Base64;
import com.facebook.stetho.common.Utf8Charset;
import com.facebook.stetho.server.LocalSocketHttpServerConnection;
import com.facebook.stetho.server.SecureHttpRequestHandler;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.annotation.Nullable;
import org.apache.http.Header;
import org.apache.http.HttpException;
import org.apache.http.HttpMessage;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.HttpServerConnection;
import org.apache.http.protocol.HttpContext;

public class WebSocketHandler
  extends SecureHttpRequestHandler
{
  private static final String HEADER_CONNECTION = "Connection";
  private static final String HEADER_CONNECTION_UPGRADE = "Upgrade";
  private static final String HEADER_SEC_WEBSOCKET_ACCEPT = "Sec-WebSocket-Accept";
  private static final String HEADER_SEC_WEBSOCKET_KEY = "Sec-WebSocket-Key";
  private static final String HEADER_SEC_WEBSOCKET_PROTOCOL = "Sec-WebSocket-Protocol";
  private static final String HEADER_SEC_WEBSOCKET_VERSION = "Sec-WebSocket-Version";
  private static final String HEADER_SEC_WEBSOCKET_VERSION_13 = "13";
  private static final String HEADER_UPGRADE = "Upgrade";
  private static final String HEADER_UPGRADE_WEBSOCKET = "websocket";
  private static final String SERVER_KEY_GUID = "258EAFA5-E914-47DA-95CA-C5AB0DC85B11";
  private final SimpleEndpoint mEndpoint;
  
  public WebSocketHandler(Context paramContext, SimpleEndpoint paramSimpleEndpoint)
  {
    super(paramContext);
    mEndpoint = paramSimpleEndpoint;
  }
  
  private void doUpgrade(HttpRequest paramHttpRequest, HttpResponse paramHttpResponse, HttpContext paramHttpContext)
    throws IOException, HttpException
  {
    paramHttpContext = RawSocketUpgradeHelper.fromApacheContext(paramHttpContext);
    paramHttpResponse.setStatusCode(101);
    paramHttpResponse.setReasonPhrase("Switching Protocols");
    paramHttpResponse.addHeader("Upgrade", "websocket");
    paramHttpResponse.addHeader("Connection", "Upgrade");
    paramHttpRequest = getFirstHeaderValue(paramHttpRequest, "Sec-WebSocket-Key");
    if (paramHttpRequest != null) {
      paramHttpResponse.addHeader("Sec-WebSocket-Accept", generateServerKey(paramHttpRequest));
    }
    forceSendResponse(paramHttpContext.getServerConnection(), paramHttpResponse);
    new WebSocketSession(paramHttpContext.getInputStream(), paramHttpContext.getOutputStream(), mEndpoint).handle();
  }
  
  private void forceSendResponse(HttpServerConnection paramHttpServerConnection, HttpResponse paramHttpResponse)
    throws IOException, HttpException
  {
    paramHttpServerConnection.sendResponseHeader(paramHttpResponse);
    paramHttpServerConnection.flush();
  }
  
  private static String generateServerKey(String paramString)
  {
    try
    {
      paramString = paramString + "258EAFA5-E914-47DA-95CA-C5AB0DC85B11";
      MessageDigest localMessageDigest = MessageDigest.getInstance("SHA-1");
      localMessageDigest.update(Utf8Charset.encodeUTF8(paramString));
      paramString = Base64.encodeToString(localMessageDigest.digest(), 2);
      return paramString;
    }
    catch (NoSuchAlgorithmException paramString)
    {
      throw new RuntimeException(paramString);
    }
  }
  
  @Nullable
  private static String getFirstHeaderValue(HttpMessage paramHttpMessage, String paramString)
  {
    paramHttpMessage = paramHttpMessage.getFirstHeader(paramString);
    if (paramHttpMessage != null) {
      return paramHttpMessage.getValue();
    }
    return null;
  }
  
  private static boolean isSupportableUpgradeRequest(HttpRequest paramHttpRequest)
  {
    return ("websocket".equalsIgnoreCase(getFirstHeaderValue(paramHttpRequest, "Upgrade"))) && ("Upgrade".equals(getFirstHeaderValue(paramHttpRequest, "Connection"))) && ("13".equals(getFirstHeaderValue(paramHttpRequest, "Sec-WebSocket-Version")));
  }
  
  /* Error */
  public void handleSecured(HttpRequest paramHttpRequest, HttpResponse paramHttpResponse, HttpContext paramHttpContext)
    throws IOException, HttpException
  {
    // Byte code:
    //   0: aload_1
    //   1: invokestatic 190	com/facebook/stetho/websocket/WebSocketHandler:isSupportableUpgradeRequest	(Lorg/apache/http/HttpRequest;)Z
    //   4: ifne +36 -> 40
    //   7: aload_2
    //   8: sipush 501
    //   11: invokeinterface 62 2 0
    //   16: aload_2
    //   17: ldc -64
    //   19: invokeinterface 68 2 0
    //   24: aload_2
    //   25: new 194	org/apache/http/entity/StringEntity
    //   28: dup
    //   29: ldc -60
    //   31: invokespecial 198	org/apache/http/entity/StringEntity:<init>	(Ljava/lang/String;)V
    //   34: invokeinterface 202 2 0
    //   39: return
    //   40: aload_3
    //   41: ldc -52
    //   43: invokeinterface 210 2 0
    //   48: checkcast 212	org/apache/http/HttpConnection
    //   51: astore 4
    //   53: aload_0
    //   54: aload_1
    //   55: aload_2
    //   56: aload_3
    //   57: invokespecial 214	com/facebook/stetho/websocket/WebSocketHandler:doUpgrade	(Lorg/apache/http/HttpRequest;Lorg/apache/http/HttpResponse;Lorg/apache/http/protocol/HttpContext;)V
    //   60: aload 4
    //   62: invokeinterface 217 1 0
    //   67: new 219	org/apache/http/ConnectionClosedException
    //   70: dup
    //   71: ldc -35
    //   73: invokespecial 222	org/apache/http/ConnectionClosedException:<init>	(Ljava/lang/String;)V
    //   76: athrow
    //   77: astore_1
    //   78: aload 4
    //   80: invokeinterface 217 1 0
    //   85: aload_1
    //   86: athrow
    //   87: astore_1
    //   88: goto -21 -> 67
    //   91: astore_2
    //   92: goto -7 -> 85
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	95	0	this	WebSocketHandler
    //   0	95	1	paramHttpRequest	HttpRequest
    //   0	95	2	paramHttpResponse	HttpResponse
    //   0	95	3	paramHttpContext	HttpContext
    //   51	28	4	localHttpConnection	org.apache.http.HttpConnection
    // Exception table:
    //   from	to	target	type
    //   53	60	77	finally
    //   60	67	87	java/io/IOException
    //   78	85	91	java/io/IOException
  }
  
  private static class RawSocketUpgradeHelper
  {
    private final HttpServerConnection mConn;
    private final InputStream mIn;
    private final OutputStream mOut;
    
    private RawSocketUpgradeHelper(HttpServerConnection paramHttpServerConnection, InputStream paramInputStream, OutputStream paramOutputStream)
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
}

/* Location:
 * Qualified Name:     com.facebook.stetho.websocket.WebSocketHandler
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */