package com.facebook.stetho.inspector.network;

import com.facebook.stetho.common.ExceptionUtil;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

class DownloadingAsyncPrettyPrinterFactory$1
  implements AsyncPrettyPrinter
{
  DownloadingAsyncPrettyPrinterFactory$1(DownloadingAsyncPrettyPrinterFactory paramDownloadingAsyncPrettyPrinterFactory, Future paramFuture, DownloadingAsyncPrettyPrinterFactory.MatchResult paramMatchResult) {}
  
  public PrettyPrinterDisplayType getPrettifiedType()
  {
    return val$result.getDisplayType();
  }
  
  public void printTo(PrintWriter paramPrintWriter, InputStream paramInputStream)
    throws IOException
  {
    try
    {
      String str = (String)val$response.get();
      throw localInterruptedException;
    }
    catch (ExecutionException localExecutionException)
    {
      try
      {
        this$0.doPrint(paramPrintWriter, paramInputStream, str);
        return;
      }
      catch (ExecutionException paramPrintWriter)
      {
        throw ExceptionUtil.propagate(paramPrintWriter.getCause());
      }
      localExecutionException = localExecutionException;
      if (IOException.class.isInstance(localExecutionException.getCause()))
      {
        DownloadingAsyncPrettyPrinterFactory.access$000(paramPrintWriter, paramInputStream, "Cannot successfully download schema: " + localExecutionException.getMessage());
        return;
      }
    }
    catch (InterruptedException localInterruptedException)
    {
      DownloadingAsyncPrettyPrinterFactory.access$000(paramPrintWriter, paramInputStream, "Encountered spurious interrupt while downloading schema for pretty printing: " + localInterruptedException.getMessage());
      return;
    }
  }
}

/* Location:
 * Qualified Name:     com.facebook.stetho.inspector.network.DownloadingAsyncPrettyPrinterFactory.1
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */