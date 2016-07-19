package android.support.v4.media;

import android.os.Bundle;
import android.os.IBinder;
import android.support.v4.util.ArrayMap;
import android.support.v4.util.Pair;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

class MediaBrowserServiceCompat$MediaBrowserServiceImplApi21$1
  implements Runnable
{
  MediaBrowserServiceCompat$MediaBrowserServiceImplApi21$1(MediaBrowserServiceCompat.MediaBrowserServiceImplApi21 paramMediaBrowserServiceImplApi21, String paramString, Bundle paramBundle) {}
  
  public void run()
  {
    Iterator localIterator = MediaBrowserServiceCompat.access$200(this$1.this$0).keySet().iterator();
    while (localIterator.hasNext())
    {
      Object localObject1 = (IBinder)localIterator.next();
      localObject1 = (MediaBrowserServiceCompat.ConnectionRecord)MediaBrowserServiceCompat.access$200(this$1.this$0).get(localObject1);
      Object localObject2 = (List)subscriptions.get(val$parentId);
      if (localObject2 != null)
      {
        localObject2 = ((List)localObject2).iterator();
        while (((Iterator)localObject2).hasNext())
        {
          Pair localPair = (Pair)((Iterator)localObject2).next();
          if (MediaBrowserCompatUtils.hasDuplicatedItems(val$options, (Bundle)second)) {
            MediaBrowserServiceCompat.access$300(this$1.this$0, val$parentId, (MediaBrowserServiceCompat.ConnectionRecord)localObject1, (Bundle)second);
          }
        }
      }
    }
  }
}

/* Location:
 * Qualified Name:     android.support.v4.media.MediaBrowserServiceCompat.MediaBrowserServiceImplApi21.1
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */