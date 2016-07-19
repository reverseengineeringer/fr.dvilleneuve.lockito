package com.fasterxml.jackson.core.io;

import java.io.IOException;
import java.io.InputStream;

public final class MergedStream
  extends InputStream
{
  byte[] _buffer;
  protected final IOContext _context;
  final int _end;
  final InputStream _in;
  int _ptr;
  
  public MergedStream(IOContext paramIOContext, InputStream paramInputStream, byte[] paramArrayOfByte, int paramInt1, int paramInt2)
  {
    _context = paramIOContext;
    _in = paramInputStream;
    _buffer = paramArrayOfByte;
    _ptr = paramInt1;
    _end = paramInt2;
  }
  
  private void freeMergedBuffer()
  {
    byte[] arrayOfByte = _buffer;
    if (arrayOfByte != null)
    {
      _buffer = null;
      if (_context != null) {
        _context.releaseReadIOBuffer(arrayOfByte);
      }
    }
  }
  
  public int available()
    throws IOException
  {
    if (_buffer != null) {
      return _end - _ptr;
    }
    return _in.available();
  }
  
  public void close()
    throws IOException
  {
    freeMergedBuffer();
    _in.close();
  }
  
  public void mark(int paramInt)
  {
    if (_buffer == null) {
      _in.mark(paramInt);
    }
  }
  
  public boolean markSupported()
  {
    return (_buffer == null) && (_in.markSupported());
  }
  
  public int read()
    throws IOException
  {
    if (_buffer != null)
    {
      byte[] arrayOfByte = _buffer;
      int i = _ptr;
      _ptr = (i + 1);
      i = arrayOfByte[i];
      if (_ptr >= _end) {
        freeMergedBuffer();
      }
      return i & 0xFF;
    }
    return _in.read();
  }
  
  public int read(byte[] paramArrayOfByte)
    throws IOException
  {
    return read(paramArrayOfByte, 0, paramArrayOfByte.length);
  }
  
  public int read(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
    throws IOException
  {
    if (_buffer != null)
    {
      int j = _end - _ptr;
      int i = paramInt2;
      if (paramInt2 > j) {
        i = j;
      }
      System.arraycopy(_buffer, _ptr, paramArrayOfByte, paramInt1, i);
      _ptr += i;
      if (_ptr >= _end) {
        freeMergedBuffer();
      }
      return i;
    }
    return _in.read(paramArrayOfByte, paramInt1, paramInt2);
  }
  
  public void reset()
    throws IOException
  {
    if (_buffer == null) {
      _in.reset();
    }
  }
  
  public long skip(long paramLong)
    throws IOException
  {
    long l1;
    if (_buffer != null)
    {
      int i = _end - _ptr;
      if (i > paramLong)
      {
        _ptr += (int)paramLong;
        return paramLong;
      }
      freeMergedBuffer();
      l1 = i + 0L;
      paramLong -= i;
    }
    for (;;)
    {
      long l2 = l1;
      if (paramLong > 0L) {
        l2 = l1 + _in.skip(paramLong);
      }
      return l2;
      l1 = 0L;
    }
  }
}

/* Location:
 * Qualified Name:     com.fasterxml.jackson.core.io.MergedStream
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */