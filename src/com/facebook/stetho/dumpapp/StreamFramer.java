package com.facebook.stetho.dumpapp;

import java.io.BufferedOutputStream;
import java.io.Closeable;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;

public class StreamFramer
  implements Closeable
{
  private static final byte EXIT_FRAME_PREFIX = 120;
  private static final byte STDERR_FRAME_PREFIX = 50;
  private static final byte STDOUT_FRAME_PREFIX = 49;
  private final DataOutputStream mMultiplexedOutputStream;
  private final PrintStream mStderr;
  private final PrintStream mStdout;
  
  public StreamFramer(OutputStream paramOutputStream)
    throws IOException
  {
    mMultiplexedOutputStream = new DataOutputStream(paramOutputStream);
    mStdout = new PrintStream(new BufferedOutputStream(new FramingOutputStream(mMultiplexedOutputStream, (byte)49)));
    mStderr = new PrintStream(new FramingOutputStream(mMultiplexedOutputStream, (byte)50));
  }
  
  private void writeIntFrame(byte paramByte, int paramInt)
    throws IOException
  {
    mMultiplexedOutputStream.write(paramByte);
    mMultiplexedOutputStream.writeInt(paramInt);
  }
  
  public void close()
    throws IOException
  {
    try
    {
      mMultiplexedOutputStream.close();
      return;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  public PrintStream getStderr()
  {
    return mStderr;
  }
  
  public PrintStream getStdout()
  {
    return mStdout;
  }
  
  public void writeExitCode(int paramInt)
    throws IOException
  {
    try
    {
      mStdout.flush();
      mStderr.flush();
      writeIntFrame((byte)120, paramInt);
      return;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  private class FramingOutputStream
    extends OutputStream
  {
    private boolean mIsClosed;
    private final OutputStream mOut;
    private final byte mPrefix;
    
    FramingOutputStream(OutputStream paramOutputStream, byte paramByte)
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
          synchronized (StreamFramer.this)
          {
            StreamFramer.this.writeIntFrame(mPrefix, paramInt2);
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
}

/* Location:
 * Qualified Name:     com.facebook.stetho.dumpapp.StreamFramer
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */