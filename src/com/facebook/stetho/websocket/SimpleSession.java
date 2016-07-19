package com.facebook.stetho.websocket;

public abstract interface SimpleSession
{
  public abstract void close(int paramInt, String paramString);
  
  public abstract boolean isOpen();
  
  public abstract void sendBinary(byte[] paramArrayOfByte);
  
  public abstract void sendText(String paramString);
}

/* Location:
 * Qualified Name:     com.facebook.stetho.websocket.SimpleSession
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */