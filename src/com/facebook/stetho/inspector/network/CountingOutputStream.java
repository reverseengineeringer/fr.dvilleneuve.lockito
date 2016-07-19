package com.facebook.stetho.inspector.network;

import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.OutputStream;

class CountingOutputStream
  extends FilterOutputStream
{
  private long mCount;
  
  public CountingOutputStream(OutputStream paramOutputStream)
  {
    super(paramOutputStream);
  }
  
  public long getCount()
  {
    return mCount;
  }
  
  public void write(int paramInt)
    throws IOException
  {
    out.write(paramInt);
    mCount += 1L;
  }
  
  public void write(byte[] paramArrayOfByte)
    throws IOException
  {
    write(paramArrayOfByte, 0, paramArrayOfByte.length);
  }
  
  public void write(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
    throws IOException
  {
    out.write(paramArrayOfByte, paramInt1, paramInt2);
    mCount += paramInt2;
  }
}

/* Location:
 * Qualified Name:     com.facebook.stetho.inspector.network.CountingOutputStream
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */