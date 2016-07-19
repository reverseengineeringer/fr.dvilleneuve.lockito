package com.facebook.stetho.inspector.network;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.zip.InflaterOutputStream;
import javax.annotation.Nullable;

public class RequestBodyHelper
{
  private ByteArrayOutputStream mDeflatedOutput;
  private CountingOutputStream mDeflatingOutput;
  private final NetworkEventReporter mEventReporter;
  private final String mRequestId;
  
  public RequestBodyHelper(NetworkEventReporter paramNetworkEventReporter, String paramString)
  {
    mEventReporter = paramNetworkEventReporter;
    mRequestId = paramString;
  }
  
  private void throwIfNoBody()
  {
    if (!hasBody()) {
      throw new IllegalStateException("No body found; has createBodySink been called?");
    }
  }
  
  public OutputStream createBodySink(@Nullable String paramString)
    throws IOException
  {
    ByteArrayOutputStream localByteArrayOutputStream = new ByteArrayOutputStream();
    if ("gzip".equals(paramString)) {
      paramString = GunzippingOutputStream.create(localByteArrayOutputStream);
    }
    for (;;)
    {
      mDeflatingOutput = new CountingOutputStream(paramString);
      mDeflatedOutput = localByteArrayOutputStream;
      return mDeflatingOutput;
      if ("deflate".equals(paramString)) {
        paramString = new InflaterOutputStream(localByteArrayOutputStream);
      } else {
        paramString = localByteArrayOutputStream;
      }
    }
  }
  
  public byte[] getDisplayBody()
  {
    throwIfNoBody();
    return mDeflatedOutput.toByteArray();
  }
  
  public boolean hasBody()
  {
    return mDeflatedOutput != null;
  }
  
  public void reportDataSent()
  {
    throwIfNoBody();
    mEventReporter.dataSent(mRequestId, mDeflatedOutput.size(), (int)mDeflatingOutput.getCount());
  }
}

/* Location:
 * Qualified Name:     com.facebook.stetho.inspector.network.RequestBodyHelper
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */