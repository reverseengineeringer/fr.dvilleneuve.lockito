package com.facebook.stetho.inspector.network;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;

final class DownloadingAsyncPrettyPrinterFactory$2
  implements AsyncPrettyPrinter
{
  DownloadingAsyncPrettyPrinterFactory$2(String paramString1, String paramString2) {}
  
  public PrettyPrinterDisplayType getPrettifiedType()
  {
    return PrettyPrinterDisplayType.TEXT;
  }
  
  public void printTo(PrintWriter paramPrintWriter, InputStream paramInputStream)
    throws IOException
  {
    DownloadingAsyncPrettyPrinterFactory.access$000(paramPrintWriter, paramInputStream, "[Failed to parse header: " + val$headerName + " : " + val$headerValue + " ]");
  }
}

/* Location:
 * Qualified Name:     com.facebook.stetho.inspector.network.DownloadingAsyncPrettyPrinterFactory.2
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */