package com.facebook.stetho.websocket;

import com.facebook.stetho.common.LogUtil;
import java.io.IOException;
import java.io.InputStream;
import javax.annotation.concurrent.NotThreadSafe;

@NotThreadSafe
class CompositeInputStream
  extends InputStream
{
  private int mCurrentIndex;
  private final InputStream[] mStreams;
  
  public CompositeInputStream(InputStream[] paramArrayOfInputStream)
  {
    if ((paramArrayOfInputStream == null) || (paramArrayOfInputStream.length < 2)) {
      throw new IllegalArgumentException("Streams must be non-null and have more than 1 entry");
    }
    mStreams = paramArrayOfInputStream;
    mCurrentIndex = 0;
  }
  
  private void closeAll(int paramInt)
    throws IOException
  {
    Object localObject1 = null;
    int i = 0;
    for (;;)
    {
      if (i >= mStreams.length) {
        return;
      }
      try
      {
        mStreams[i].close();
        localObject2 = localObject1;
      }
      catch (IOException localIOException)
      {
        for (;;)
        {
          if (i != paramInt)
          {
            localObject2 = localObject1;
            if (localObject1 != null) {}
          }
          else
          {
            localObject2 = localIOException;
          }
          Object localObject3 = localObject2;
          Object localObject2 = localObject3;
          if (localObject1 != null)
          {
            localObject2 = localObject3;
            if (localObject1 != localObject3)
            {
              LogUtil.w((Throwable)localObject1, "Suppressing exception");
              localObject2 = localObject3;
            }
          }
        }
      }
      i += 1;
      localObject1 = localObject2;
    }
  }
  
  private boolean tryMoveToNextStream()
  {
    if (mCurrentIndex + 1 < mStreams.length)
    {
      mCurrentIndex += 1;
      return true;
    }
    return false;
  }
  
  public int available()
    throws IOException
  {
    return mStreams[mCurrentIndex].available();
  }
  
  public void close()
    throws IOException
  {
    closeAll(mCurrentIndex);
  }
  
  public void mark(int paramInt)
  {
    throw new UnsupportedOperationException();
  }
  
  public boolean markSupported()
  {
    return false;
  }
  
  public int read()
    throws IOException
  {
    int i;
    do
    {
      i = mStreams[mCurrentIndex].read();
    } while ((i == -1) && (tryMoveToNextStream()));
    return i;
  }
  
  public int read(byte[] paramArrayOfByte)
    throws IOException
  {
    return read(paramArrayOfByte, 0, paramArrayOfByte.length);
  }
  
  public int read(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
    throws IOException
  {
    int i;
    do
    {
      i = mStreams[mCurrentIndex].read(paramArrayOfByte, paramInt1, paramInt2);
    } while ((i == -1) && (tryMoveToNextStream()));
    return i;
  }
  
  public void reset()
    throws IOException
  {
    throw new UnsupportedOperationException();
  }
  
  public long skip(long paramLong)
    throws IOException
  {
    int i = read(new byte[(int)paramLong]);
    if (i >= 0) {
      return i;
    }
    return -1L;
  }
}

/* Location:
 * Qualified Name:     com.facebook.stetho.websocket.CompositeInputStream
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */