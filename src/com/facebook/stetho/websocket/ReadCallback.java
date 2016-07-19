package com.facebook.stetho.websocket;

abstract interface ReadCallback
{
  public abstract void onCompleteFrame(byte paramByte, byte[] paramArrayOfByte, int paramInt);
}

/* Location:
 * Qualified Name:     com.facebook.stetho.websocket.ReadCallback
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */