package com.facebook.stetho.websocket;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

class ReadHandler
{
  private final BufferedInputStream mBufferedInput;
  private final ByteArrayOutputStream mCurrentPayload = new ByteArrayOutputStream();
  private final SimpleEndpoint mEndpoint;
  
  public ReadHandler(InputStream paramInputStream, SimpleEndpoint paramSimpleEndpoint)
  {
    mBufferedInput = new BufferedInputStream(paramInputStream, 1024);
    mEndpoint = paramSimpleEndpoint;
  }
  
  public void readLoop(ReadCallback paramReadCallback)
    throws IOException
  {
    Frame localFrame = new Frame();
    do
    {
      localFrame.readFrom(mBufferedInput);
      mCurrentPayload.write(payloadData, 0, (int)payloadLen);
      if (fin)
      {
        byte[] arrayOfByte = mCurrentPayload.toByteArray();
        paramReadCallback.onCompleteFrame(opcode, arrayOfByte, arrayOfByte.length);
        mCurrentPayload.reset();
      }
    } while (opcode != 8);
  }
}

/* Location:
 * Qualified Name:     com.facebook.stetho.websocket.ReadHandler
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */