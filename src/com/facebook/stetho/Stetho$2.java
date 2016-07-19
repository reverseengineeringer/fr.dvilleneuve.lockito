package com.facebook.stetho;

import com.facebook.stetho.common.LogUtil;
import com.facebook.stetho.server.LocalSocketHttpServer;
import java.io.IOException;

final class Stetho$2
  extends Thread
{
  Stetho$2(String paramString, Stetho.Initializer paramInitializer)
  {
    super(paramString);
  }
  
  public void run()
  {
    LocalSocketHttpServer localLocalSocketHttpServer = new LocalSocketHttpServer(val$initializer);
    try
    {
      localLocalSocketHttpServer.run();
      return;
    }
    catch (IOException localIOException)
    {
      LogUtil.e(localIOException, "Could not start Stetho");
    }
  }
}

/* Location:
 * Qualified Name:     com.facebook.stetho.Stetho.2
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */