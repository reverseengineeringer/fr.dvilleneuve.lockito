package com.facebook.stetho.server;

import android.annotation.SuppressLint;
import com.facebook.stetho.common.LogUtil;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;
import org.apache.http.ConnectionClosedException;
import org.apache.http.HttpException;
import org.apache.http.HttpServerConnection;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpService;

class LocalSocketHttpServer$WorkerThread
  extends Thread
{
  private final HttpServerConnection conn;
  private final HttpService httpservice;
  
  public LocalSocketHttpServer$WorkerThread(HttpService paramHttpService, HttpServerConnection paramHttpServerConnection)
  {
    super("StethoWorker" + LocalSocketHttpServer.access$000().incrementAndGet());
    httpservice = paramHttpService;
    conn = paramHttpServerConnection;
  }
  
  @SuppressLint({"LogMethodNoExceptionInCatch"})
  public void run()
  {
    BasicHttpContext localBasicHttpContext = new BasicHttpContext(null);
    try
    {
      if ((!Thread.interrupted()) && (conn.isOpen())) {
        httpservice.handleRequest(conn, localBasicHttpContext);
      }
      return;
    }
    catch (ConnectionClosedException localConnectionClosedException)
    {
      localConnectionClosedException = localConnectionClosedException;
      LogUtil.w("Client closed connection: %s", new Object[] { localConnectionClosedException });
      try
      {
        conn.close();
        return;
      }
      catch (IOException localIOException1)
      {
        return;
      }
    }
    catch (IOException localIOException2)
    {
      LogUtil.w("I/O error: %s", new Object[] { localIOException2 });
      try
      {
        conn.close();
        return;
      }
      catch (IOException localIOException3)
      {
        return;
      }
    }
    catch (HttpException localHttpException)
    {
      LogUtil.w("Unrecoverable HTTP protocol violation: %s", new Object[] { localHttpException });
      try
      {
        conn.close();
        return;
      }
      catch (IOException localIOException4)
      {
        return;
      }
    }
    finally
    {
      try
      {
        conn.close();
        throw ((Throwable)localObject);
      }
      catch (IOException localIOException6)
      {
        for (;;) {}
      }
    }
  }
}

/* Location:
 * Qualified Name:     com.facebook.stetho.server.LocalSocketHttpServer.WorkerThread
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */