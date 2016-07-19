package com.facebook.stetho.dumpapp;

import java.io.IOException;
import java.io.OutputStream;

class StreamFramer$FramingOutputStream
  extends OutputStream
{
  private boolean mIsClosed;
  private final OutputStream mOut;
  private final byte mPrefix;
  
  StreamFramer$FramingOutputStream(StreamFramer paramStreamFramer, OutputStream paramOutputStream, byte paramByte)
  {
    mOut = paramOutputStream;
    mPrefix = paramByte;
    mIsClosed = false;
  }
  
  public void close()
    throws IOException
  {
    mIsClosed = true;
  }
  
  public void write(int paramInt)
    throws IOException
  {
    byte[] arrayOfByte = new byte[1];
    arrayOfByte[0] = ((byte)paramInt);
    write(arrayOfByte, 0, arrayOfByte.length);
  }
  
  public void write(byte[] paramArrayOfByte)
    throws IOException
  {
    write(paramArrayOfByte, 0, paramArrayOfByte.length);
  }
  
  public void write(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
    throws IOException
  {
    if (mIsClosed) {
      throw new IOException("Stream is closed");
    }
    if (paramInt2 > 0) {
      try
      {
        synchronized (this$0)
        {
          StreamFramer.access$000(this$0, mPrefix, paramInt2);
          mOut.write(paramArrayOfByte, paramInt1, paramInt2);
          mOut.flush();
          return;
        }
        return;
      }
      catch (IOException paramArrayOfByte)
      {
        throw new DumpappOutputBrokenException(paramArrayOfByte);
      }
    }
  }
}

/* Location:
 * Qualified Name:     com.facebook.stetho.dumpapp.StreamFramer.FramingOutputStream
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */