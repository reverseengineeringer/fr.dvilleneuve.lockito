package com.fasterxml.jackson.databind.util;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

public class ByteBufferBackedInputStream
  extends InputStream
{
  protected final ByteBuffer _buffer;
  
  public ByteBufferBackedInputStream(ByteBuffer paramByteBuffer)
  {
    _buffer = paramByteBuffer;
  }
  
  public int available()
  {
    return _buffer.remaining();
  }
  
  public int read()
    throws IOException
  {
    if (_buffer.hasRemaining()) {
      return _buffer.get() & 0xFF;
    }
    return -1;
  }
  
  public int read(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
    throws IOException
  {
    if (!_buffer.hasRemaining()) {
      return -1;
    }
    paramInt2 = Math.min(paramInt2, _buffer.remaining());
    _buffer.get(paramArrayOfByte, paramInt1, paramInt2);
    return paramInt2;
  }
}

/* Location:
 * Qualified Name:     com.fasterxml.jackson.databind.util.ByteBufferBackedInputStream
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */