package com.fasterxml.jackson.core.io;

import com.fasterxml.jackson.core.JsonEncoding;
import com.fasterxml.jackson.core.util.BufferRecycler;
import com.fasterxml.jackson.core.util.BufferRecycler.ByteBufferType;
import com.fasterxml.jackson.core.util.BufferRecycler.CharBufferType;
import com.fasterxml.jackson.core.util.TextBuffer;

public final class IOContext
{
  protected byte[] _base64Buffer = null;
  protected final BufferRecycler _bufferRecycler;
  protected char[] _concatCBuffer = null;
  protected JsonEncoding _encoding;
  protected final boolean _managedResource;
  protected char[] _nameCopyBuffer = null;
  protected byte[] _readIOBuffer = null;
  protected final Object _sourceRef;
  protected char[] _tokenCBuffer = null;
  protected byte[] _writeEncodingBuffer = null;
  
  public IOContext(BufferRecycler paramBufferRecycler, Object paramObject, boolean paramBoolean)
  {
    _bufferRecycler = paramBufferRecycler;
    _sourceRef = paramObject;
    _managedResource = paramBoolean;
  }
  
  private final void _verifyAlloc(Object paramObject)
  {
    if (paramObject != null) {
      throw new IllegalStateException("Trying to call same allocXxx() method second time");
    }
  }
  
  private final void _verifyRelease(Object paramObject1, Object paramObject2)
  {
    if (paramObject1 != paramObject2) {
      throw new IllegalArgumentException("Trying to release buffer not owned by the context");
    }
  }
  
  public byte[] allocBase64Buffer()
  {
    _verifyAlloc(_base64Buffer);
    byte[] arrayOfByte = _bufferRecycler.allocByteBuffer(BufferRecycler.ByteBufferType.BASE64_CODEC_BUFFER);
    _base64Buffer = arrayOfByte;
    return arrayOfByte;
  }
  
  public char[] allocConcatBuffer()
  {
    _verifyAlloc(_concatCBuffer);
    char[] arrayOfChar = _bufferRecycler.allocCharBuffer(BufferRecycler.CharBufferType.CONCAT_BUFFER);
    _concatCBuffer = arrayOfChar;
    return arrayOfChar;
  }
  
  public char[] allocNameCopyBuffer(int paramInt)
  {
    _verifyAlloc(_nameCopyBuffer);
    char[] arrayOfChar = _bufferRecycler.allocCharBuffer(BufferRecycler.CharBufferType.NAME_COPY_BUFFER, paramInt);
    _nameCopyBuffer = arrayOfChar;
    return arrayOfChar;
  }
  
  public byte[] allocReadIOBuffer()
  {
    _verifyAlloc(_readIOBuffer);
    byte[] arrayOfByte = _bufferRecycler.allocByteBuffer(BufferRecycler.ByteBufferType.READ_IO_BUFFER);
    _readIOBuffer = arrayOfByte;
    return arrayOfByte;
  }
  
  public char[] allocTokenBuffer()
  {
    _verifyAlloc(_tokenCBuffer);
    char[] arrayOfChar = _bufferRecycler.allocCharBuffer(BufferRecycler.CharBufferType.TOKEN_BUFFER);
    _tokenCBuffer = arrayOfChar;
    return arrayOfChar;
  }
  
  public byte[] allocWriteEncodingBuffer()
  {
    _verifyAlloc(_writeEncodingBuffer);
    byte[] arrayOfByte = _bufferRecycler.allocByteBuffer(BufferRecycler.ByteBufferType.WRITE_ENCODING_BUFFER);
    _writeEncodingBuffer = arrayOfByte;
    return arrayOfByte;
  }
  
  public TextBuffer constructTextBuffer()
  {
    return new TextBuffer(_bufferRecycler);
  }
  
  public JsonEncoding getEncoding()
  {
    return _encoding;
  }
  
  public Object getSourceReference()
  {
    return _sourceRef;
  }
  
  public boolean isResourceManaged()
  {
    return _managedResource;
  }
  
  public void releaseBase64Buffer(byte[] paramArrayOfByte)
  {
    if (paramArrayOfByte != null)
    {
      _verifyRelease(paramArrayOfByte, _base64Buffer);
      _base64Buffer = null;
      _bufferRecycler.releaseByteBuffer(BufferRecycler.ByteBufferType.BASE64_CODEC_BUFFER, paramArrayOfByte);
    }
  }
  
  public void releaseConcatBuffer(char[] paramArrayOfChar)
  {
    if (paramArrayOfChar != null)
    {
      _verifyRelease(paramArrayOfChar, _concatCBuffer);
      _concatCBuffer = null;
      _bufferRecycler.releaseCharBuffer(BufferRecycler.CharBufferType.CONCAT_BUFFER, paramArrayOfChar);
    }
  }
  
  public void releaseNameCopyBuffer(char[] paramArrayOfChar)
  {
    if (paramArrayOfChar != null)
    {
      _verifyRelease(paramArrayOfChar, _nameCopyBuffer);
      _nameCopyBuffer = null;
      _bufferRecycler.releaseCharBuffer(BufferRecycler.CharBufferType.NAME_COPY_BUFFER, paramArrayOfChar);
    }
  }
  
  public void releaseReadIOBuffer(byte[] paramArrayOfByte)
  {
    if (paramArrayOfByte != null)
    {
      _verifyRelease(paramArrayOfByte, _readIOBuffer);
      _readIOBuffer = null;
      _bufferRecycler.releaseByteBuffer(BufferRecycler.ByteBufferType.READ_IO_BUFFER, paramArrayOfByte);
    }
  }
  
  public void releaseTokenBuffer(char[] paramArrayOfChar)
  {
    if (paramArrayOfChar != null)
    {
      _verifyRelease(paramArrayOfChar, _tokenCBuffer);
      _tokenCBuffer = null;
      _bufferRecycler.releaseCharBuffer(BufferRecycler.CharBufferType.TOKEN_BUFFER, paramArrayOfChar);
    }
  }
  
  public void releaseWriteEncodingBuffer(byte[] paramArrayOfByte)
  {
    if (paramArrayOfByte != null)
    {
      _verifyRelease(paramArrayOfByte, _writeEncodingBuffer);
      _writeEncodingBuffer = null;
      _bufferRecycler.releaseByteBuffer(BufferRecycler.ByteBufferType.WRITE_ENCODING_BUFFER, paramArrayOfByte);
    }
  }
  
  public void setEncoding(JsonEncoding paramJsonEncoding)
  {
    _encoding = paramJsonEncoding;
  }
}

/* Location:
 * Qualified Name:     com.fasterxml.jackson.core.io.IOContext
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */