package android.support.v4.media.session;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v4.media.RatingCompat;
import android.view.KeyEvent;

class MediaSessionCompat$MediaSessionImplBase$MessageHandler
  extends Handler
{
  private static final int KEYCODE_MEDIA_PAUSE = 127;
  private static final int KEYCODE_MEDIA_PLAY = 126;
  private static final int MSG_ADJUST_VOLUME = 2;
  private static final int MSG_COMMAND = 1;
  private static final int MSG_CUSTOM_ACTION = 20;
  private static final int MSG_FAST_FORWARD = 16;
  private static final int MSG_MEDIA_BUTTON = 21;
  private static final int MSG_NEXT = 14;
  private static final int MSG_PAUSE = 12;
  private static final int MSG_PLAY = 7;
  private static final int MSG_PLAY_MEDIA_ID = 8;
  private static final int MSG_PLAY_SEARCH = 9;
  private static final int MSG_PLAY_URI = 10;
  private static final int MSG_PREPARE = 3;
  private static final int MSG_PREPARE_MEDIA_ID = 4;
  private static final int MSG_PREPARE_SEARCH = 5;
  private static final int MSG_PREPARE_URI = 6;
  private static final int MSG_PREVIOUS = 15;
  private static final int MSG_RATE = 19;
  private static final int MSG_REWIND = 17;
  private static final int MSG_SEEK_TO = 18;
  private static final int MSG_SET_VOLUME = 22;
  private static final int MSG_SKIP_TO_ITEM = 11;
  private static final int MSG_STOP = 13;
  
  public MediaSessionCompat$MediaSessionImplBase$MessageHandler(MediaSessionCompat.MediaSessionImplBase paramMediaSessionImplBase, Looper paramLooper)
  {
    super(paramLooper);
  }
  
  private void onMediaButtonEvent(KeyEvent paramKeyEvent, MediaSessionCompat.Callback paramCallback)
  {
    int k = 1;
    if ((paramKeyEvent == null) || (paramKeyEvent.getAction() != 0)) {}
    label28:
    int i;
    label143:
    int j;
    label157:
    label312:
    label318:
    label324:
    do
    {
      return;
      long l;
      if (MediaSessionCompat.MediaSessionImplBase.access$2900(this$0) == null)
      {
        l = 0L;
        switch (paramKeyEvent.getKeyCode())
        {
        default: 
          return;
        case 79: 
        case 85: 
          if ((MediaSessionCompat.MediaSessionImplBase.access$2900(this$0) != null) && (MediaSessionCompat.MediaSessionImplBase.access$2900(this$0).getState() == 3))
          {
            i = 1;
            if ((0x204 & l) == 0L) {
              break label312;
            }
            j = 1;
            if ((0x202 & l) == 0L) {
              break label318;
            }
          }
          break;
        }
      }
      for (;;)
      {
        if ((i == 0) || (k == 0)) {
          break label324;
        }
        paramCallback.onPause();
        return;
        l = MediaSessionCompat.MediaSessionImplBase.access$2900(this$0).getActions();
        break label28;
        if ((0x4 & l) == 0L) {
          break;
        }
        paramCallback.onPlay();
        return;
        if ((0x2 & l) == 0L) {
          break;
        }
        paramCallback.onPause();
        return;
        if ((0x20 & l) == 0L) {
          break;
        }
        paramCallback.onSkipToNext();
        return;
        if ((0x10 & l) == 0L) {
          break;
        }
        paramCallback.onSkipToPrevious();
        return;
        if ((1L & l) == 0L) {
          break;
        }
        paramCallback.onStop();
        return;
        if ((0x40 & l) == 0L) {
          break;
        }
        paramCallback.onFastForward();
        return;
        if ((0x8 & l) == 0L) {
          break;
        }
        paramCallback.onRewind();
        return;
        i = 0;
        break label143;
        j = 0;
        break label157;
        k = 0;
      }
    } while ((i != 0) || (j == 0));
    paramCallback.onPlay();
  }
  
  public void handleMessage(Message paramMessage)
  {
    MediaSessionCompat.Callback localCallback = MediaSessionCompat.MediaSessionImplBase.access$2800(this$0);
    if (localCallback == null) {}
    Intent localIntent;
    do
    {
      return;
      switch (what)
      {
      default: 
        return;
      case 1: 
        paramMessage = (MediaSessionCompat.MediaSessionImplBase.Command)obj;
        localCallback.onCommand(command, extras, stub);
        return;
      case 21: 
        paramMessage = (KeyEvent)obj;
        localIntent = new Intent("android.intent.action.MEDIA_BUTTON");
        localIntent.putExtra("android.intent.extra.KEY_EVENT", paramMessage);
      }
    } while (localCallback.onMediaButtonEvent(localIntent));
    onMediaButtonEvent(paramMessage, localCallback);
    return;
    localCallback.onPrepare();
    return;
    localCallback.onPrepareFromMediaId((String)obj, paramMessage.getData());
    return;
    localCallback.onPrepareFromSearch((String)obj, paramMessage.getData());
    return;
    localCallback.onPrepareFromUri((Uri)obj, paramMessage.getData());
    return;
    localCallback.onPlay();
    return;
    localCallback.onPlayFromMediaId((String)obj, paramMessage.getData());
    return;
    localCallback.onPlayFromSearch((String)obj, paramMessage.getData());
    return;
    localCallback.onPlayFromUri((Uri)obj, paramMessage.getData());
    return;
    localCallback.onSkipToQueueItem(((Long)obj).longValue());
    return;
    localCallback.onPause();
    return;
    localCallback.onStop();
    return;
    localCallback.onSkipToNext();
    return;
    localCallback.onSkipToPrevious();
    return;
    localCallback.onFastForward();
    return;
    localCallback.onRewind();
    return;
    localCallback.onSeekTo(((Long)obj).longValue());
    return;
    localCallback.onSetRating((RatingCompat)obj);
    return;
    localCallback.onCustomAction((String)obj, paramMessage.getData());
    return;
    MediaSessionCompat.MediaSessionImplBase.access$1800(this$0, ((Integer)obj).intValue(), 0);
    return;
    MediaSessionCompat.MediaSessionImplBase.access$1900(this$0, ((Integer)obj).intValue(), 0);
  }
  
  public void post(int paramInt)
  {
    post(paramInt, null);
  }
  
  public void post(int paramInt, Object paramObject)
  {
    obtainMessage(paramInt, paramObject).sendToTarget();
  }
  
  public void post(int paramInt1, Object paramObject, int paramInt2)
  {
    obtainMessage(paramInt1, paramInt2, 0, paramObject).sendToTarget();
  }
  
  public void post(int paramInt, Object paramObject, Bundle paramBundle)
  {
    paramObject = obtainMessage(paramInt, paramObject);
    ((Message)paramObject).setData(paramBundle);
    ((Message)paramObject).sendToTarget();
  }
}

/* Location:
 * Qualified Name:     android.support.v4.media.session.MediaSessionCompat.MediaSessionImplBase.MessageHandler
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */