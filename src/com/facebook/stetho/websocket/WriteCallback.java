package com.facebook.stetho.websocket;

import java.io.IOException;

abstract interface WriteCallback
{
  public abstract void onFailure(IOException paramIOException);
  
  public abstract void onSuccess();
}

/* Location:
 * Qualified Name:     com.facebook.stetho.websocket.WriteCallback
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */