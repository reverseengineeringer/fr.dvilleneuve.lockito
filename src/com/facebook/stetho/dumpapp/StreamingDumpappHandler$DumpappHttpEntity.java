package com.facebook.stetho.dumpapp;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.apache.http.HttpRequest;
import org.apache.http.entity.AbstractHttpEntity;

class StreamingDumpappHandler$DumpappHttpEntity
  extends AbstractHttpEntity
{
  private final Dumper mDumper;
  private final InputStream mInput;
  private final HttpRequest mRequest;
  
  StreamingDumpappHandler$DumpappHttpEntity(StreamingDumpappHandler paramStreamingDumpappHandler, HttpRequest paramHttpRequest, Dumper paramDumper, InputStream paramInputStream)
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
    StreamingDumpappHandler.access$000(mRequest, mDumper, mInput, paramOutputStream);
  }
}

/* Location:
 * Qualified Name:     com.facebook.stetho.dumpapp.StreamingDumpappHandler.DumpappHttpEntity
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */