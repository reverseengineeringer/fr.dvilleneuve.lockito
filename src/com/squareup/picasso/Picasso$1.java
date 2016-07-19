package com.squareup.picasso;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import java.util.Iterator;
import java.util.List;

final class Picasso$1
  extends Handler
{
  Picasso$1(Looper paramLooper)
  {
    super(paramLooper);
  }
  
  public void handleMessage(Message paramMessage)
  {
    switch (what)
    {
    default: 
      throw new AssertionError("Unknown handler message received: " + what);
    case 8: 
      paramMessage = ((List)obj).iterator();
    }
    while (paramMessage.hasNext())
    {
      BitmapHunter localBitmapHunter = (BitmapHunter)paramMessage.next();
      picasso.complete(localBitmapHunter);
      continue;
      paramMessage = (Action)obj;
      Picasso.access$000(picasso, paramMessage.getTarget());
    }
  }
}

/* Location:
 * Qualified Name:     com.squareup.picasso.Picasso.1
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */