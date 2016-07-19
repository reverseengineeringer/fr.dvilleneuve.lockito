package com.facebook.stetho.inspector.network;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.concurrent.Callable;

class ResponseBodyFileManager$AsyncPrettyPrintingCallable
  implements Callable<String>
{
  private final AsyncPrettyPrinter mAsyncPrettyPrinter;
  private final InputStream mInputStream;
  
  public ResponseBodyFileManager$AsyncPrettyPrintingCallable(ResponseBodyFileManager paramResponseBodyFileManager, InputStream paramInputStream, AsyncPrettyPrinter paramAsyncPrettyPrinter)
  {
    mInputStream = paramInputStream;
    mAsyncPrettyPrinter = paramAsyncPrettyPrinter;
  }
  
  private String prettyPrintContent(InputStream paramInputStream, AsyncPrettyPrinter paramAsyncPrettyPrinter)
    throws IOException
  {
    ByteArrayOutputStream localByteArrayOutputStream = new ByteArrayOutputStream();
    PrintWriter localPrintWriter = new PrintWriter(localByteArrayOutputStream);
    paramAsyncPrettyPrinter.printTo(localPrintWriter, paramInputStream);
    localPrintWriter.flush();
    return localByteArrayOutputStream.toString("UTF-8");
  }
  
  public String call()
    throws IOException
  {
    return prettyPrintContent(mInputStream, mAsyncPrettyPrinter);
  }
}

/* Location:
 * Qualified Name:     com.facebook.stetho.inspector.network.ResponseBodyFileManager.AsyncPrettyPrintingCallable
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */