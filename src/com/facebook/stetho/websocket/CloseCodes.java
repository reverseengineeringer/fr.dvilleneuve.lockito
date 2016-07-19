package com.facebook.stetho.websocket;

public abstract interface CloseCodes
{
  public static final int CLOSED_ABNORMALLY = 1006;
  public static final int NORMAL_CLOSURE = 1000;
  public static final int PROTOCOL_ERROR = 1002;
  public static final int UNEXPECTED_CONDITION = 1011;
}

/* Location:
 * Qualified Name:     com.facebook.stetho.websocket.CloseCodes
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */