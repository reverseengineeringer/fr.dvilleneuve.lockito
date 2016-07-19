package com.facebook.stetho.inspector.network;

import com.facebook.stetho.common.Util;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.concurrent.Callable;
import java.util.zip.GZIPInputStream;

class GunzippingOutputStream$GunzippingCallable
  implements Callable<Void>
{
  private final InputStream mIn;
  private final OutputStream mOut;
  
  public GunzippingOutputStream$GunzippingCallable(InputStream paramInputStream, OutputStream paramOutputStream)
  {
    mIn = paramInputStream;
    mOut = paramOutputStream;
  }
  
  public Void call()
    throws IOException
  {
    GZIPInputStream localGZIPInputStream = new GZIPInputStream(mIn);
    try
    {
      Util.copy(localGZIPInputStream, mOut, new byte['Ѐ']);
      return null;
    }
    finally
    {
      localGZIPInputStream.close();
      mOut.close();
    }
  }
}

/* Location:
 * Qualified Name:     com.facebook.stetho.inspector.network.GunzippingOutputStream.GunzippingCallable
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */