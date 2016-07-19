package com.facebook.stetho.dumpapp;

import android.content.Context;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.apache.http.HttpEntity;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.entity.AbstractHttpEntity;

public class StreamingDumpappHandler
  extends DumpappHandler
{
  public StreamingDumpappHandler(Context paramContext, Dumper paramDumper)
  {
    super(paramContext, paramDumper);
  }
  
  private static void writeTo(HttpRequest paramHttpRequest, Dumper paramDumper, InputStream paramInputStream, OutputStream paramOutputStream)
    throws IOException
  {
    paramOutputStream = new StreamFramer(paramOutputStream);
    paramHttpRequest = getArgs(paramHttpRequest);
    try
    {
      paramOutputStream.writeExitCode(paramDumper.dump(paramInputStream, paramOutputStream.getStdout(), paramOutputStream.getStderr(), paramHttpRequest));
      paramOutputStream.close();
      return;
    }
    catch (DumpappOutputBrokenException paramHttpRequest)
    {
      paramHttpRequest = paramHttpRequest;
      paramOutputStream.close();
      return;
    }
    finally
    {
      paramHttpRequest = finally;
      paramOutputStream.close();
      throw paramHttpRequest;
    }
  }
  
  protected HttpEntity getResponseEntity(HttpRequest paramHttpRequest, InputStream paramInputStream, HttpResponse paramHttpResponse)
    throws IOException
  {
    paramHttpRequest = new DumpappHttpEntity(paramHttpRequest, getDumper(), paramInputStream);
    paramHttpRequest.setChunked(true);
    paramHttpRequest.setContentType("application/octet-stream");
    return paramHttpRequest;
  }
  
  private class DumpappHttpEntity
    extends AbstractHttpEntity
  {
    private final Dumper mDumper;
    private final InputStream mInput;
    private final HttpRequest mRequest;
    
    DumpappHttpEntity(HttpRequest paramHttpRequest, Dumper paramDumper, InputStream paramInputStream)
    {
      mRequest = paramHttpRequest;
      mDumper = paramDumper;
      mInput = paramInputStream;
    }
    
    public InputStream getContent()
      throws IOException, IllegalStateException
    {
      throw new UnsupportedOperationException();
    }
    
    public long getContentLength()
    {
      return -1L;
    }
    
    public boolean isRepeatable()
    {
      return false;
    }
    
    public boolean isStreaming()
    {
      return true;
    }
    
    public void writeTo(OutputStream paramOutputStream)
      throws IOException
    {
      StreamingDumpappHandler.writeTo(mRequest, mDumper, mInput, paramOutputStream);
    }
  }
}

/* Location:
 * Qualified Name:     com.facebook.stetho.dumpapp.StreamingDumpappHandler
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */