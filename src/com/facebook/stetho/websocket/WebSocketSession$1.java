package com.facebook.stetho.websocket;

import java.io.IOException;

class WebSocketSession$1
  implements ReadCallback
{
  WebSocketSession$1(WebSocketSession paramWebSocketSession) {}
  
  private void handleBinaryFrame(byte[] paramArrayOfByte, int paramInt)
  {
    WebSocketSession.access$400(this$0).onMessage(this$0, paramArrayOfByte, paramInt);
  }
  
  private void handleClose(byte[] paramArrayOfByte, int paramInt)
  {
    int i;
    if (paramInt >= 2)
    {
      i = (paramArrayOfByte[0] & 0xFF) << 8 | paramArrayOfByte[1] & 0xFF;
      if (paramInt > 2)
      {
        paramArrayOfByte = new String(paramArrayOfByte, 2, paramInt - 2);
        paramInt = i;
      }
    }
    for (;;)
    {
      if (!WebSocketSession.access$100(this$0)) {
        WebSocketSession.access$200(this$0, 1000, "Received close frame");
      }
      this$0.markAndSignalClosed(paramInt, paramArrayOfByte);
      return;
      paramArrayOfByte = null;
      paramInt = i;
      continue;
      paramInt = 1006;
      paramArrayOfByte = "Unparseable close frame";
    }
  }
  
  private void handlePing(byte[] paramArrayOfByte, int paramInt)
  {
    WebSocketSession.access$300(this$0, FrameHelper.createPongFrame(paramArrayOfByte, paramInt));
  }
  
  private void handlePong(byte[] paramArrayOfByte, int paramInt) {}
  
  private void handleTextFrame(byte[] paramArrayOfByte, int paramInt)
  {
    WebSocketSession.access$400(this$0).onMessage(this$0, new String(paramArrayOfByte, 0, paramInt));
  }
  
  public void onCompleteFrame(byte paramByte, byte[] paramArrayOfByte, int paramInt)
  {
    switch (paramByte)
    {
    case 3: 
    case 4: 
    case 5: 
    case 6: 
    case 7: 
    default: 
      WebSocketSession.access$000(this$0, new IOException("Unsupported frame opcode=" + paramByte));
      return;
    case 8: 
      handleClose(paramArrayOfByte, paramInt);
      return;
    case 9: 
      handlePing(paramArrayOfByte, paramInt);
      return;
    case 10: 
      handlePong(paramArrayOfByte, paramInt);
      return;
    case 1: 
      handleTextFrame(paramArrayOfByte, paramInt);
      return;
    }
    handleBinaryFrame(paramArrayOfByte, paramInt);
  }
}

/* Location:
 * Qualified Name:     com.facebook.stetho.websocket.WebSocketSession.1
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */