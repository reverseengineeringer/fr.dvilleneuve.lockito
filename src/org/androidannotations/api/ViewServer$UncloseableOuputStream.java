package org.androidannotations.api;

import java.io.IOException;
import java.io.OutputStream;

class ViewServer$UncloseableOuputStream
  extends OutputStream
{
  private final OutputStream mStream;
  
  ViewServer$UncloseableOuputStream(OutputStream paramOutputStream)
  {
    mStream = paramOutputStream;
  }
  
  public void close()
    throws IOException
  {}
  
  public boolean equals(Object paramObject)
  {
    return mStream.equals(paramObject);
  }
  
  public void flush()
    throws IOException
  {
    mStream.flush();
  }
  
  public int hashCode()
  {
    return mStream.hashCode();
  }
  
  public String toString()
  {
    return mStream.toString();
  }
  
  public void write(int paramInt)
    throws IOException
  {
    mStream.write(paramInt);
  }
  
  public void write(byte[] paramArrayOfByte)
    throws IOException
  {
    mStream.write(paramArrayOfByte);
  }
  
  public void write(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
    throws IOException
  {
    mStream.write(paramArrayOfByte, paramInt1, paramInt2);
  }
}

/* Location:
 * Qualified Name:     org.androidannotations.api.ViewServer.UncloseableOuputStream
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */