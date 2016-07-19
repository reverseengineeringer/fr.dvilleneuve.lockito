package com.facebook.stetho.websocket;

import java.io.IOException;

class WebSocketSession$2
  implements WriteCallback
{
  WebSocketSession$2(WebSocketSession paramWebSocketSession) {}
  
  public void onFailure(IOException paramIOException)
  {
    WebSocketSession.access$000(this$0, paramIOException);
  }
  
  public void onSuccess() {}
}

/* Location:
 * Qualified Name:     com.facebook.stetho.websocket.WebSocketSession.2
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */