package com.facebook.stetho;

import com.facebook.stetho.common.LogUtil;
import java.io.IOException;
import org.apache.http.HttpException;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.entity.StringEntity;
import org.apache.http.protocol.HttpContext;
import org.apache.http.protocol.HttpRequestHandler;

class Stetho$Initializer$LoggingCatchAllHandler
  implements HttpRequestHandler
{
  public void handle(HttpRequest paramHttpRequest, HttpResponse paramHttpResponse, HttpContext paramHttpContext)
    throws HttpException, IOException
  {
    LogUtil.w("Unsupported request received: " + paramHttpRequest.getRequestLine());
    paramHttpResponse.setStatusCode(404);
    paramHttpResponse.setReasonPhrase("Not Found");
    paramHttpResponse.setEntity(new StringEntity("Endpoint not implemented\n", "UTF-8"));
  }
}

/* Location:
 * Qualified Name:     com.facebook.stetho.Stetho.Initializer.LoggingCatchAllHandler
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */