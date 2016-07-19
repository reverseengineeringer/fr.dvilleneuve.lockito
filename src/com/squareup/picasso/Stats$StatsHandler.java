package com.squareup.picasso;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

class Stats$StatsHandler
  extends Handler
{
  public Stats$StatsHandler(Stats paramStats, Looper paramLooper)
  {
    super(paramLooper);
  }
  
  public void handleMessage(final Message paramMessage)
  {
    for (;;)
    {
      synchronized (this$0)
      {
        switch (what)
        {
        case 0: 
          new Handler(Looper.getMainLooper()).post(new Runnable()
          {
            public void run()
            {
              throw new AssertionError("Unhandled stats message." + paramMessagewhat);
            }
          });
          return;
        case 1: 
          this$0.performCacheHit();
        }
      }
      this$0.performCacheMiss();
      continue;
      this$0.performBitmapDecoded(arg1);
      continue;
      this$0.performBitmapTransformed(arg1);
    }
  }
}

/* Location:
 * Qualified Name:     com.squareup.picasso.Stats.StatsHandler
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */