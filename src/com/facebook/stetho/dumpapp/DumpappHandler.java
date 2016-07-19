package com.facebook.stetho.dumpapp;

import android.content.Context;
import android.net.Uri;
import com.facebook.stetho.common.Util;
import com.facebook.stetho.server.SecureHttpRequestHandler;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import org.apache.http.HttpEntity;
import org.apache.http.HttpEntityEnclosingRequest;
import org.apache.http.HttpException;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.RequestLine;
import org.apache.http.protocol.HttpContext;

public abstract class DumpappHandler
  extends SecureHttpRequestHandler
{
  private static final String QUERY_PARAM_ARGV = "argv";
  private static final String RESPONSE_HEADER_ALLOW_ORIGIN = "Access-Control-Allow-Origin";
  private final Dumper mDumper;
  
  public DumpappHandler(Context paramContext, Dumper paramDumper)
  {
    super(paramContext);
    mDumper = paramDumper;
  }
  
  private static InputStream bufferInput(HttpRequest paramHttpRequest)
    throws IOException
  {
    ByteArrayOutputStream localByteArrayOutputStream = new ByteArrayOutputStream();
    Util.copy(getCallerInput(paramHttpRequest), localByteArrayOutputStream, new byte['Ā']);
    return new ByteArrayInputStream(localByteArrayOutputStream.toByteArray());
  }
  
  protected static String[] getArgs(HttpRequest paramHttpRequest)
  {
    paramHttpRequest = Uri.parse(paramHttpRequest.getRequestLine().getUri()).getQueryParameters("argv");
    return (String[])paramHttpRequest.toArray(new String[paramHttpRequest.size()]);
  }
  
  private static InputStream getCallerInput(HttpRequest paramHttpRequest)
    throws IOException
  {
    if ((paramHttpRequest instanceof HttpEntityEnclosingRequest))
    {
      paramHttpRequest = ((HttpEntityEnclosingRequest)paramHttpRequest).getEntity();
      if (paramHttpRequest != null) {
        return paramHttpRequest.getContent();
      }
    }
    return new ByteArrayInputStream(new byte[0]);
  }
  
  protected Dumper getDumper()
  {
    return mDumper;
  }
  
  protected abstract HttpEntity getResponseEntity(HttpRequest paramHttpRequest, InputStream paramInputStream, HttpResponse paramHttpResponse)
    throws IOException;
  
  protected void handleSecured(HttpRequest paramHttpRequest, HttpResponse paramHttpResponse, HttpContext paramHttpContext)
    throws HttpException, IOException
  {
    paramHttpResponse.setEntity(getResponseEntity(paramHttpRequest, bufferInput(paramHttpRequest), paramHttpResponse));
    paramHttpResponse.addHeader("Access-Control-Allow-Origin", "*");
    paramHttpResponse.setStatusCode(200);
  }
}

/* Location:
 * Qualified Name:     com.facebook.stetho.dumpapp.DumpappHandler
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */