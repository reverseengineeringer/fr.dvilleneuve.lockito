package com.facebook.stetho;

import android.content.Context;
import com.facebook.stetho.common.LogUtil;
import com.facebook.stetho.dumpapp.Dumper;
import com.facebook.stetho.dumpapp.DumperPlugin;
import com.facebook.stetho.dumpapp.RawDumpappHandler;
import com.facebook.stetho.dumpapp.StreamingDumpappHandler;
import com.facebook.stetho.inspector.ChromeDevtoolsServer;
import com.facebook.stetho.inspector.ChromeDiscoveryHandler;
import com.facebook.stetho.inspector.protocol.ChromeDevtoolsDomain;
import com.facebook.stetho.server.RegistryInitializer;
import com.facebook.stetho.websocket.WebSocketHandler;
import java.io.IOException;
import javax.annotation.Nullable;
import org.apache.http.HttpException;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.entity.StringEntity;
import org.apache.http.protocol.HttpContext;
import org.apache.http.protocol.HttpRequestHandler;
import org.apache.http.protocol.HttpRequestHandlerRegistry;

public abstract class Stetho$Initializer
  implements RegistryInitializer
{
  private final Context mContext;
  
  protected Stetho$Initializer(Context paramContext)
  {
    mContext = paramContext.getApplicationContext();
  }
  
  protected void addCustomEntries(HttpRequestHandlerRegistry paramHttpRequestHandlerRegistry) {}
  
  @Nullable
  protected abstract Iterable<DumperPlugin> getDumperPlugins();
  
  @Nullable
  protected abstract Iterable<ChromeDevtoolsDomain> getInspectorModules();
  
  public final HttpRequestHandlerRegistry getRegistry()
  {
    HttpRequestHandlerRegistry localHttpRequestHandlerRegistry = new HttpRequestHandlerRegistry();
    Object localObject = getDumperPlugins();
    if (localObject != null)
    {
      localObject = new Dumper((Iterable)localObject);
      localHttpRequestHandlerRegistry.register("/dumpapp", new StreamingDumpappHandler(mContext, (Dumper)localObject));
      localHttpRequestHandlerRegistry.register("/dumpapp-raw", new RawDumpappHandler(mContext, (Dumper)localObject));
    }
    localObject = getInspectorModules();
    if (localObject != null)
    {
      new ChromeDiscoveryHandler(mContext, "/inspector").register(localHttpRequestHandlerRegistry);
      localHttpRequestHandlerRegistry.register("/inspector", new WebSocketHandler(mContext, new ChromeDevtoolsServer((Iterable)localObject)));
    }
    addCustomEntries(localHttpRequestHandlerRegistry);
    localHttpRequestHandlerRegistry.register("/*", new LoggingCatchAllHandler(null));
    return localHttpRequestHandlerRegistry;
  }
  
  private static class LoggingCatchAllHandler
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
}

/* Location:
 * Qualified Name:     com.facebook.stetho.Stetho.Initializer
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */