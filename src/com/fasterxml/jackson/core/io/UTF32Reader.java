package com.fasterxml.jackson.core.io;

import java.io.CharConversionException;
import java.io.IOException;
import java.io.InputStream;

public class UTF32Reader
  extends BaseReader
{
  protected final boolean _bigEndian;
  protected int _byteCount = 0;
  protected int _charCount = 0;
  protected final boolean _managedBuffers;
  protected char _surrogate = '\000';
  
  public UTF32Reader(IOContext paramIOContext, InputStream paramInputStream, byte[] paramArrayOfByte, int paramInt1, int paramInt2, boolean paramBoolean)
  {
    super(paramIOContext, paramInputStream, paramArrayOfByte, paramInt1, paramInt2);
    _bigEndian = paramBoolean;
    paramBoolean = bool;
    if (paramInputStream != null) {
      paramBoolean = true;
    }
    _managedBuffers = paramBoolean;
  }
  
  private boolean loadMore(int paramInt)
    throws IOException
  {
    _byteCount += _length - paramInt;
    if (paramInt > 0)
    {
      if (_ptr > 0)
      {
        int i = 0;
        while (i < paramInt)
        {
          _buffer[i] = _buffer[(_ptr + i)];
          i += 1;
        }
        _ptr = 0;
      }
      _length = paramInt;
      if (_length >= 4) {
        break label227;
      }
      if (_in != null) {
        break label198;
      }
    }
    label198:
    for (paramInt = -1;; paramInt = _in.read(_buffer, _length, _buffer.length - _length))
    {
      if (paramInt < 1)
      {
        if (paramInt < 0)
        {
          if (_managedBuffers) {
            freeBuffers();
          }
          reportUnexpectedEOF(_length, 4);
        }
        reportStrangeStream();
      }
      _length = (paramInt + _length);
      break;
      _ptr = 0;
      if (_in == null) {
        paramInt = -1;
      }
      while (paramInt < 1)
      {
        _length = 0;
        if (paramInt < 0)
        {
          if (_managedBuffers) {
            freeBuffers();
          }
          return false;
          paramInt = _in.read(_buffer);
        }
        else
        {
          reportStrangeStream();
        }
      }
      _length = paramInt;
      break;
    }
    label227:
    return true;
  }
  
  private void reportInvalid(int paramInt1, int paramInt2, String paramString)
    throws IOException
  {
    int i = _byteCount;
    int j = _ptr;
    int k = _charCount;
    throw new CharConversionException("Invalid UTF-32 character 0x" + Integer.toHexString(paramInt1) + paramString + " at char #" + (k + paramInt2) + ", byte #" + (i + j - 1) + ")");
  }
  
  private void reportUnexpectedEOF(int paramInt1, int paramInt2)
    throws IOException
  {
    int i = _byteCount;
    int j = _charCount;
    throw new CharConversionException("Unexpected EOF in the middle of a 4-byte UTF-32 char: got " + paramInt1 + ", needed " + paramInt2 + ", at char #" + j + ", byte #" + (i + paramInt1) + ")");
  }
  
  public int read(char[] paramArrayOfChar, int paramInt1, int paramInt2)
    throws IOException
  {
    int i;
    if (_buffer == null) {
      i = -1;
    }
    do
    {
      return i;
      i = paramInt2;
    } while (paramInt2 < 1);
    if ((paramInt1 < 0) || (paramInt1 + paramInt2 > paramArrayOfChar.length)) {
      reportBounds(paramArrayOfChar, paramInt1, paramInt2);
    }
    int m = paramInt2 + paramInt1;
    if (_surrogate != 0)
    {
      paramInt2 = paramInt1 + 1;
      paramArrayOfChar[paramInt1] = _surrogate;
      _surrogate = '\000';
    }
    for (;;)
    {
      int j;
      int k;
      int n;
      if (paramInt2 < m)
      {
        i = _ptr;
        if (_bigEndian)
        {
          j = _buffer[i];
          k = _buffer[(i + 1)];
          n = _buffer[(i + 2)];
          i = _buffer[(i + 3)] & 0xFF | j << 24 | (k & 0xFF) << 16 | (n & 0xFF) << 8;
          label159:
          _ptr += 4;
          if (i <= 65535) {
            break label395;
          }
          if (i > 1114111) {
            reportInvalid(i, paramInt2 - paramInt1, "(above " + Integer.toHexString(1114111) + ") ");
          }
          j = i - 65536;
          i = paramInt2 + 1;
          paramArrayOfChar[paramInt2] = ((char)(55296 + (j >> 10)));
          k = j & 0x3FF | 0xDC00;
          j = k;
          paramInt2 = i;
          if (i < m) {
            break label399;
          }
          _surrogate = ((char)k);
          paramInt2 = i;
        }
      }
      for (;;)
      {
        paramInt1 = paramInt2 - paramInt1;
        _charCount += paramInt1;
        return paramInt1;
        paramInt2 = _length - _ptr;
        if ((paramInt2 >= 4) || (loadMore(paramInt2))) {
          break label433;
        }
        return -1;
        j = _buffer[i];
        k = _buffer[(i + 1)];
        n = _buffer[(i + 2)];
        i = _buffer[(i + 3)] << 24 | j & 0xFF | (k & 0xFF) << 8 | (n & 0xFF) << 16;
        break label159;
        label395:
        j = i;
        label399:
        i = paramInt2 + 1;
        paramArrayOfChar[paramInt2] = ((char)j);
        paramInt2 = i;
        if (_ptr < _length) {
          break;
        }
        paramInt2 = i;
      }
      label433:
      paramInt2 = paramInt1;
    }
  }
}

/* Location:
 * Qualified Name:     com.fasterxml.jackson.core.io.UTF32Reader
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */