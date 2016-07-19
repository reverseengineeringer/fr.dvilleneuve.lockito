package android.support.v4.media.session;

import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;
import android.media.AudioManager;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.RemoteCallbackList;
import android.os.RemoteException;
import android.os.ResultReceiver;
import android.os.SystemClock;
import android.support.v4.media.MediaMetadataCompat;
import android.support.v4.media.MediaMetadataCompat.Builder;
import android.support.v4.media.RatingCompat;
import android.support.v4.media.VolumeProviderCompat;
import android.support.v4.media.VolumeProviderCompat.Callback;
import android.util.Log;
import android.view.KeyEvent;
import java.util.List;

class MediaSessionCompat$MediaSessionImplBase
  implements MediaSessionCompat.MediaSessionImpl
{
  private final AudioManager mAudioManager;
  private volatile MediaSessionCompat.Callback mCallback;
  private final Context mContext;
  private final RemoteCallbackList<IMediaControllerCallback> mControllerCallbacks = new RemoteCallbackList();
  private boolean mDestroyed = false;
  private Bundle mExtras;
  private int mFlags;
  private MessageHandler mHandler;
  private boolean mIsActive = false;
  private boolean mIsMbrRegistered = false;
  private boolean mIsRccRegistered = false;
  private int mLocalStream;
  private final Object mLock = new Object();
  private final ComponentName mMediaButtonReceiverComponentName;
  private final PendingIntent mMediaButtonReceiverIntent;
  private MediaMetadataCompat mMetadata;
  private final String mPackageName;
  private List<MediaSessionCompat.QueueItem> mQueue;
  private CharSequence mQueueTitle;
  private int mRatingType;
  private final Object mRccObj;
  private PendingIntent mSessionActivity;
  private PlaybackStateCompat mState;
  private final MediaSessionStub mStub;
  private final String mTag;
  private final MediaSessionCompat.Token mToken;
  private VolumeProviderCompat.Callback mVolumeCallback = new VolumeProviderCompat.Callback()
  {
    public void onVolumeChanged(VolumeProviderCompat paramAnonymousVolumeProviderCompat)
    {
      if (mVolumeProvider != paramAnonymousVolumeProviderCompat) {
        return;
      }
      paramAnonymousVolumeProviderCompat = new ParcelableVolumeInfo(mVolumeType, mLocalStream, paramAnonymousVolumeProviderCompat.getVolumeControl(), paramAnonymousVolumeProviderCompat.getMaxVolume(), paramAnonymousVolumeProviderCompat.getCurrentVolume());
      MediaSessionCompat.MediaSessionImplBase.this.sendVolumeInfoChanged(paramAnonymousVolumeProviderCompat);
    }
  };
  private VolumeProviderCompat mVolumeProvider;
  private int mVolumeType;
  
  public MediaSessionCompat$MediaSessionImplBase(Context paramContext, String paramString, ComponentName paramComponentName, PendingIntent paramPendingIntent)
  {
    Object localObject = paramComponentName;
    List localList;
    if (paramComponentName == null)
    {
      localObject = new Intent("android.intent.action.MEDIA_BUTTON");
      ((Intent)localObject).setPackage(paramContext.getPackageName());
      localList = paramContext.getPackageManager().queryBroadcastReceivers((Intent)localObject, 0);
      if (localList.size() != 1) {
        break label200;
      }
      paramComponentName = (ResolveInfo)localList.get(0);
      localObject = new ComponentName(activityInfo.packageName, activityInfo.name);
    }
    for (;;)
    {
      paramComponentName = paramPendingIntent;
      if (localObject != null)
      {
        paramComponentName = paramPendingIntent;
        if (paramPendingIntent == null)
        {
          paramComponentName = new Intent("android.intent.action.MEDIA_BUTTON");
          paramComponentName.setComponent((ComponentName)localObject);
          paramComponentName = PendingIntent.getBroadcast(paramContext, 0, paramComponentName, 0);
        }
      }
      if (localObject != null) {
        break;
      }
      throw new IllegalArgumentException("MediaButtonReceiver component may not be null.");
      label200:
      localObject = paramComponentName;
      if (localList.size() > 1)
      {
        Log.w("MediaSessionCompat", "More than one BroadcastReceiver that handles android.intent.action.MEDIA_BUTTON was found, using null. Provide a specific ComponentName to use as this session's media button receiver");
        localObject = paramComponentName;
      }
    }
    mContext = paramContext;
    mPackageName = paramContext.getPackageName();
    mAudioManager = ((AudioManager)paramContext.getSystemService("audio"));
    mTag = paramString;
    mMediaButtonReceiverComponentName = ((ComponentName)localObject);
    mMediaButtonReceiverIntent = paramComponentName;
    mStub = new MediaSessionStub();
    mToken = new MediaSessionCompat.Token(mStub);
    mRatingType = 0;
    mVolumeType = 1;
    mLocalStream = 3;
    if (Build.VERSION.SDK_INT >= 14)
    {
      mRccObj = MediaSessionCompatApi14.createRemoteControlClient(paramComponentName);
      return;
    }
    mRccObj = null;
  }
  
  private void adjustVolume(int paramInt1, int paramInt2)
  {
    if (mVolumeType == 2)
    {
      if (mVolumeProvider != null) {
        mVolumeProvider.onAdjustVolume(paramInt1);
      }
      return;
    }
    mAudioManager.adjustStreamVolume(mLocalStream, paramInt1, paramInt2);
  }
  
  private MediaMetadataCompat cloneMetadataIfNeeded(MediaMetadataCompat paramMediaMetadataCompat)
  {
    if (paramMediaMetadataCompat == null) {
      localObject = null;
    }
    do
    {
      return (MediaMetadataCompat)localObject;
      if (paramMediaMetadataCompat.containsKey("android.media.metadata.ART")) {
        break;
      }
      localObject = paramMediaMetadataCompat;
    } while (!paramMediaMetadataCompat.containsKey("android.media.metadata.ALBUM_ART"));
    Object localObject = new MediaMetadataCompat.Builder(paramMediaMetadataCompat);
    Bitmap localBitmap = paramMediaMetadataCompat.getBitmap("android.media.metadata.ART");
    if (localBitmap != null) {
      ((MediaMetadataCompat.Builder)localObject).putBitmap("android.media.metadata.ART", localBitmap.copy(localBitmap.getConfig(), false));
    }
    paramMediaMetadataCompat = paramMediaMetadataCompat.getBitmap("android.media.metadata.ALBUM_ART");
    if (paramMediaMetadataCompat != null) {
      ((MediaMetadataCompat.Builder)localObject).putBitmap("android.media.metadata.ALBUM_ART", paramMediaMetadataCompat.copy(paramMediaMetadataCompat.getConfig(), false));
    }
    return ((MediaMetadataCompat.Builder)localObject).build();
  }
  
  private PlaybackStateCompat getStateWithUpdatedPosition()
  {
    long l2 = -1L;
    for (;;)
    {
      synchronized (mLock)
      {
        PlaybackStateCompat localPlaybackStateCompat = mState;
        l1 = l2;
        if (mMetadata != null)
        {
          l1 = l2;
          if (mMetadata.containsKey("android.media.metadata.DURATION")) {
            l1 = mMetadata.getLong("android.media.metadata.DURATION");
          }
        }
        Object localObject2 = null;
        ??? = localObject2;
        if (localPlaybackStateCompat != null) {
          if ((localPlaybackStateCompat.getState() != 3) && (localPlaybackStateCompat.getState() != 4))
          {
            ??? = localObject2;
            if (localPlaybackStateCompat.getState() != 5) {}
          }
          else
          {
            l2 = localPlaybackStateCompat.getLastPositionUpdateTime();
            long l3 = SystemClock.elapsedRealtime();
            ??? = localObject2;
            if (l2 > 0L)
            {
              l2 = (localPlaybackStateCompat.getPlaybackSpeed() * (float)(l3 - l2)) + localPlaybackStateCompat.getPosition();
              if ((l1 < 0L) || (l2 <= l1)) {
                break label205;
              }
              ??? = new PlaybackStateCompat.Builder(localPlaybackStateCompat);
              ((PlaybackStateCompat.Builder)???).setState(localPlaybackStateCompat.getState(), l1, localPlaybackStateCompat.getPlaybackSpeed(), l3);
              ??? = ((PlaybackStateCompat.Builder)???).build();
            }
          }
        }
        if (??? != null) {
          break;
        }
        return localPlaybackStateCompat;
      }
      label205:
      long l1 = l2;
      if (l2 < 0L) {
        l1 = 0L;
      }
    }
    return (PlaybackStateCompat)???;
  }
  
  private void postToHandler(int paramInt)
  {
    postToHandler(paramInt, null);
  }
  
  private void postToHandler(int paramInt, Object paramObject)
  {
    postToHandler(paramInt, paramObject, null);
  }
  
  private void postToHandler(int paramInt, Object paramObject, Bundle paramBundle)
  {
    synchronized (mLock)
    {
      if (mHandler != null) {
        mHandler.post(paramInt, paramObject, paramBundle);
      }
      return;
    }
  }
  
  private void sendEvent(String paramString, Bundle paramBundle)
  {
    int i = mControllerCallbacks.beginBroadcast() - 1;
    for (;;)
    {
      IMediaControllerCallback localIMediaControllerCallback;
      if (i >= 0) {
        localIMediaControllerCallback = (IMediaControllerCallback)mControllerCallbacks.getBroadcastItem(i);
      }
      try
      {
        localIMediaControllerCallback.onEvent(paramString, paramBundle);
        i -= 1;
        continue;
        mControllerCallbacks.finishBroadcast();
        return;
      }
      catch (RemoteException localRemoteException)
      {
        for (;;) {}
      }
    }
  }
  
  private void sendMetadata(MediaMetadataCompat paramMediaMetadataCompat)
  {
    int i = mControllerCallbacks.beginBroadcast() - 1;
    for (;;)
    {
      IMediaControllerCallback localIMediaControllerCallback;
      if (i >= 0) {
        localIMediaControllerCallback = (IMediaControllerCallback)mControllerCallbacks.getBroadcastItem(i);
      }
      try
      {
        localIMediaControllerCallback.onMetadataChanged(paramMediaMetadataCompat);
        i -= 1;
        continue;
        mControllerCallbacks.finishBroadcast();
        return;
      }
      catch (RemoteException localRemoteException)
      {
        for (;;) {}
      }
    }
  }
  
  private void sendQueue(List<MediaSessionCompat.QueueItem> paramList)
  {
    int i = mControllerCallbacks.beginBroadcast() - 1;
    for (;;)
    {
      IMediaControllerCallback localIMediaControllerCallback;
      if (i >= 0) {
        localIMediaControllerCallback = (IMediaControllerCallback)mControllerCallbacks.getBroadcastItem(i);
      }
      try
      {
        localIMediaControllerCallback.onQueueChanged(paramList);
        i -= 1;
        continue;
        mControllerCallbacks.finishBroadcast();
        return;
      }
      catch (RemoteException localRemoteException)
      {
        for (;;) {}
      }
    }
  }
  
  private void sendQueueTitle(CharSequence paramCharSequence)
  {
    int i = mControllerCallbacks.beginBroadcast() - 1;
    for (;;)
    {
      IMediaControllerCallback localIMediaControllerCallback;
      if (i >= 0) {
        localIMediaControllerCallback = (IMediaControllerCallback)mControllerCallbacks.getBroadcastItem(i);
      }
      try
      {
        localIMediaControllerCallback.onQueueTitleChanged(paramCharSequence);
        i -= 1;
        continue;
        mControllerCallbacks.finishBroadcast();
        return;
      }
      catch (RemoteException localRemoteException)
      {
        for (;;) {}
      }
    }
  }
  
  private void sendSessionDestroyed()
  {
    int i = mControllerCallbacks.beginBroadcast() - 1;
    for (;;)
    {
      IMediaControllerCallback localIMediaControllerCallback;
      if (i >= 0) {
        localIMediaControllerCallback = (IMediaControllerCallback)mControllerCallbacks.getBroadcastItem(i);
      }
      try
      {
        localIMediaControllerCallback.onSessionDestroyed();
        i -= 1;
        continue;
        mControllerCallbacks.finishBroadcast();
        mControllerCallbacks.kill();
        return;
      }
      catch (RemoteException localRemoteException)
      {
        for (;;) {}
      }
    }
  }
  
  private void sendState(PlaybackStateCompat paramPlaybackStateCompat)
  {
    int i = mControllerCallbacks.beginBroadcast() - 1;
    for (;;)
    {
      IMediaControllerCallback localIMediaControllerCallback;
      if (i >= 0) {
        localIMediaControllerCallback = (IMediaControllerCallback)mControllerCallbacks.getBroadcastItem(i);
      }
      try
      {
        localIMediaControllerCallback.onPlaybackStateChanged(paramPlaybackStateCompat);
        i -= 1;
        continue;
        mControllerCallbacks.finishBroadcast();
        return;
      }
      catch (RemoteException localRemoteException)
      {
        for (;;) {}
      }
    }
  }
  
  private void sendVolumeInfoChanged(ParcelableVolumeInfo paramParcelableVolumeInfo)
  {
    int i = mControllerCallbacks.beginBroadcast() - 1;
    for (;;)
    {
      IMediaControllerCallback localIMediaControllerCallback;
      if (i >= 0) {
        localIMediaControllerCallback = (IMediaControllerCallback)mControllerCallbacks.getBroadcastItem(i);
      }
      try
      {
        localIMediaControllerCallback.onVolumeInfoChanged(paramParcelableVolumeInfo);
        i -= 1;
        continue;
        mControllerCallbacks.finishBroadcast();
        return;
      }
      catch (RemoteException localRemoteException)
      {
        for (;;) {}
      }
    }
  }
  
  private void setVolumeTo(int paramInt1, int paramInt2)
  {
    if (mVolumeType == 2)
    {
      if (mVolumeProvider != null) {
        mVolumeProvider.onSetVolumeTo(paramInt1);
      }
      return;
    }
    mAudioManager.setStreamVolume(mLocalStream, paramInt1, paramInt2);
  }
  
  private boolean update()
  {
    boolean bool2 = false;
    label61:
    boolean bool1;
    if (mIsActive)
    {
      if (Build.VERSION.SDK_INT >= 8)
      {
        if ((mIsMbrRegistered) || ((mFlags & 0x1) == 0)) {
          break label121;
        }
        if (Build.VERSION.SDK_INT >= 18)
        {
          MediaSessionCompatApi18.registerMediaButtonEventReceiver(mContext, mMediaButtonReceiverIntent, mMediaButtonReceiverComponentName);
          mIsMbrRegistered = true;
        }
      }
      else
      {
        bool1 = bool2;
        if (Build.VERSION.SDK_INT >= 14)
        {
          if ((mIsRccRegistered) || ((mFlags & 0x2) == 0)) {
            break label182;
          }
          MediaSessionCompatApi14.registerRemoteControlClient(mContext, mRccObj);
          mIsRccRegistered = true;
          bool1 = true;
        }
      }
      label121:
      label182:
      do
      {
        do
        {
          return bool1;
          MediaSessionCompatApi8.registerMediaButtonEventReceiver(mContext, mMediaButtonReceiverComponentName);
          break;
          if ((!mIsMbrRegistered) || ((mFlags & 0x1) != 0)) {
            break label61;
          }
          if (Build.VERSION.SDK_INT >= 18) {
            MediaSessionCompatApi18.unregisterMediaButtonEventReceiver(mContext, mMediaButtonReceiverIntent, mMediaButtonReceiverComponentName);
          }
          for (;;)
          {
            mIsMbrRegistered = false;
            break;
            MediaSessionCompatApi8.unregisterMediaButtonEventReceiver(mContext, mMediaButtonReceiverComponentName);
          }
          bool1 = bool2;
        } while (!mIsRccRegistered);
        bool1 = bool2;
      } while ((mFlags & 0x2) != 0);
      MediaSessionCompatApi14.setState(mRccObj, 0);
      MediaSessionCompatApi14.unregisterRemoteControlClient(mContext, mRccObj);
      mIsRccRegistered = false;
      return false;
    }
    if (mIsMbrRegistered)
    {
      if (Build.VERSION.SDK_INT < 18) {
        break label298;
      }
      MediaSessionCompatApi18.unregisterMediaButtonEventReceiver(mContext, mMediaButtonReceiverIntent, mMediaButtonReceiverComponentName);
    }
    for (;;)
    {
      mIsMbrRegistered = false;
      bool1 = bool2;
      if (!mIsRccRegistered) {
        break;
      }
      MediaSessionCompatApi14.setState(mRccObj, 0);
      MediaSessionCompatApi14.unregisterRemoteControlClient(mContext, mRccObj);
      mIsRccRegistered = false;
      return false;
      label298:
      MediaSessionCompatApi8.unregisterMediaButtonEventReceiver(mContext, mMediaButtonReceiverComponentName);
    }
  }
  
  public String getCallingPackage()
  {
    return null;
  }
  
  public Object getMediaSession()
  {
    return null;
  }
  
  public Object getRemoteControlClient()
  {
    return mRccObj;
  }
  
  public MediaSessionCompat.Token getSessionToken()
  {
    return mToken;
  }
  
  public boolean isActive()
  {
    return mIsActive;
  }
  
  public void release()
  {
    mIsActive = false;
    mDestroyed = true;
    update();
    sendSessionDestroyed();
  }
  
  public void sendSessionEvent(String paramString, Bundle paramBundle)
  {
    sendEvent(paramString, paramBundle);
  }
  
  public void setActive(boolean paramBoolean)
  {
    if (paramBoolean == mIsActive) {}
    do
    {
      return;
      mIsActive = paramBoolean;
    } while (!update());
    setMetadata(mMetadata);
    setPlaybackState(mState);
  }
  
  public void setCallback(MediaSessionCompat.Callback paramCallback, Handler arg2)
  {
    mCallback = paramCallback;
    if (paramCallback == null)
    {
      if (Build.VERSION.SDK_INT >= 18) {
        MediaSessionCompatApi18.setOnPlaybackPositionUpdateListener(mRccObj, null);
      }
      if (Build.VERSION.SDK_INT >= 19) {
        MediaSessionCompatApi19.setOnMetadataUpdateListener(mRccObj, null);
      }
    }
    for (;;)
    {
      return;
      paramCallback = ???;
      if (??? == null) {
        paramCallback = new Handler();
      }
      synchronized (mLock)
      {
        mHandler = new MessageHandler(paramCallback.getLooper());
        paramCallback = new MediaSessionCompatApi19.Callback()
        {
          public void onSeekTo(long paramAnonymousLong)
          {
            MediaSessionCompat.MediaSessionImplBase.this.postToHandler(18, Long.valueOf(paramAnonymousLong));
          }
          
          public void onSetRating(Object paramAnonymousObject)
          {
            MediaSessionCompat.MediaSessionImplBase.this.postToHandler(19, RatingCompat.fromRating(paramAnonymousObject));
          }
        };
        if (Build.VERSION.SDK_INT >= 18)
        {
          ??? = MediaSessionCompatApi18.createPlaybackPositionUpdateListener(paramCallback);
          MediaSessionCompatApi18.setOnPlaybackPositionUpdateListener(mRccObj, ???);
        }
        if (Build.VERSION.SDK_INT < 19) {
          continue;
        }
        paramCallback = MediaSessionCompatApi19.createMetadataUpdateListener(paramCallback);
        MediaSessionCompatApi19.setOnMetadataUpdateListener(mRccObj, paramCallback);
        return;
      }
    }
  }
  
  public void setExtras(Bundle paramBundle)
  {
    mExtras = paramBundle;
  }
  
  public void setFlags(int paramInt)
  {
    synchronized (mLock)
    {
      mFlags = paramInt;
      update();
      return;
    }
  }
  
  public void setMediaButtonReceiver(PendingIntent paramPendingIntent) {}
  
  public void setMetadata(MediaMetadataCompat arg1)
  {
    Object localObject3 = null;
    Object localObject2 = null;
    MediaMetadataCompat localMediaMetadataCompat = ???;
    if (Build.VERSION.SDK_INT >= 14)
    {
      localMediaMetadataCompat = ???;
      if (??? != null) {
        localMediaMetadataCompat = cloneMetadataIfNeeded(???);
      }
    }
    label115:
    do
    {
      synchronized (mLock)
      {
        mMetadata = localMediaMetadataCompat;
        sendMetadata(localMediaMetadataCompat);
        if (!mIsActive) {
          return;
        }
      }
      if (Build.VERSION.SDK_INT >= 19)
      {
        localObject3 = mRccObj;
        if (localObject1 == null)
        {
          ??? = (MediaMetadataCompat)localObject2;
          if (mState != null) {
            break label115;
          }
        }
        for (long l = 0L;; l = mState.getActions())
        {
          MediaSessionCompatApi19.setMetadata(localObject3, ???, l);
          return;
          ??? = ((MediaMetadataCompat)localObject1).getBundle();
          break;
        }
      }
    } while (Build.VERSION.SDK_INT < 14);
    localObject2 = mRccObj;
    if (localObject1 == null) {}
    for (??? = (MediaMetadataCompat)localObject3;; ??? = ((MediaMetadataCompat)localObject1).getBundle())
    {
      MediaSessionCompatApi14.setMetadata(localObject2, ???);
      return;
    }
  }
  
  public void setPlaybackState(PlaybackStateCompat paramPlaybackStateCompat)
  {
    do
    {
      do
      {
        synchronized (mLock)
        {
          mState = paramPlaybackStateCompat;
          sendState(paramPlaybackStateCompat);
          if (!mIsActive) {
            return;
          }
        }
        if (paramPlaybackStateCompat != null) {
          break;
        }
      } while (Build.VERSION.SDK_INT < 14);
      MediaSessionCompatApi14.setState(mRccObj, 0);
      MediaSessionCompatApi14.setTransportControlFlags(mRccObj, 0L);
      return;
      if (Build.VERSION.SDK_INT >= 18) {
        MediaSessionCompatApi18.setState(mRccObj, paramPlaybackStateCompat.getState(), paramPlaybackStateCompat.getPosition(), paramPlaybackStateCompat.getPlaybackSpeed(), paramPlaybackStateCompat.getLastPositionUpdateTime());
      }
      while (Build.VERSION.SDK_INT >= 19)
      {
        MediaSessionCompatApi19.setTransportControlFlags(mRccObj, paramPlaybackStateCompat.getActions());
        return;
        if (Build.VERSION.SDK_INT >= 14) {
          MediaSessionCompatApi14.setState(mRccObj, paramPlaybackStateCompat.getState());
        }
      }
      if (Build.VERSION.SDK_INT >= 18)
      {
        MediaSessionCompatApi18.setTransportControlFlags(mRccObj, paramPlaybackStateCompat.getActions());
        return;
      }
    } while (Build.VERSION.SDK_INT < 14);
    MediaSessionCompatApi14.setTransportControlFlags(mRccObj, paramPlaybackStateCompat.getActions());
  }
  
  public void setPlaybackToLocal(int paramInt)
  {
    if (mVolumeProvider != null) {
      mVolumeProvider.setCallback(null);
    }
    mVolumeType = 1;
    sendVolumeInfoChanged(new ParcelableVolumeInfo(mVolumeType, mLocalStream, 2, mAudioManager.getStreamMaxVolume(mLocalStream), mAudioManager.getStreamVolume(mLocalStream)));
  }
  
  public void setPlaybackToRemote(VolumeProviderCompat paramVolumeProviderCompat)
  {
    if (paramVolumeProviderCompat == null) {
      throw new IllegalArgumentException("volumeProvider may not be null");
    }
    if (mVolumeProvider != null) {
      mVolumeProvider.setCallback(null);
    }
    mVolumeType = 2;
    mVolumeProvider = paramVolumeProviderCompat;
    sendVolumeInfoChanged(new ParcelableVolumeInfo(mVolumeType, mLocalStream, mVolumeProvider.getVolumeControl(), mVolumeProvider.getMaxVolume(), mVolumeProvider.getCurrentVolume()));
    paramVolumeProviderCompat.setCallback(mVolumeCallback);
  }
  
  public void setQueue(List<MediaSessionCompat.QueueItem> paramList)
  {
    mQueue = paramList;
    sendQueue(paramList);
  }
  
  public void setQueueTitle(CharSequence paramCharSequence)
  {
    mQueueTitle = paramCharSequence;
    sendQueueTitle(paramCharSequence);
  }
  
  public void setRatingType(int paramInt)
  {
    mRatingType = paramInt;
  }
  
  public void setSessionActivity(PendingIntent paramPendingIntent)
  {
    synchronized (mLock)
    {
      mSessionActivity = paramPendingIntent;
      return;
    }
  }
  
  private static final class Command
  {
    public final String command;
    public final Bundle extras;
    public final ResultReceiver stub;
    
    public Command(String paramString, Bundle paramBundle, ResultReceiver paramResultReceiver)
    {
      command = paramString;
      extras = paramBundle;
      stub = paramResultReceiver;
    }
  }
  
  class MediaSessionStub
    extends IMediaSession.Stub
  {
    MediaSessionStub() {}
    
    public void adjustVolume(int paramInt1, int paramInt2, String paramString)
    {
      MediaSessionCompat.MediaSessionImplBase.this.adjustVolume(paramInt1, paramInt2);
    }
    
    public void fastForward()
      throws RemoteException
    {
      MediaSessionCompat.MediaSessionImplBase.this.postToHandler(16);
    }
    
    public Bundle getExtras()
    {
      synchronized (mLock)
      {
        Bundle localBundle = mExtras;
        return localBundle;
      }
    }
    
    public long getFlags()
    {
      synchronized (mLock)
      {
        long l = mFlags;
        return l;
      }
    }
    
    public PendingIntent getLaunchPendingIntent()
    {
      synchronized (mLock)
      {
        PendingIntent localPendingIntent = mSessionActivity;
        return localPendingIntent;
      }
    }
    
    public MediaMetadataCompat getMetadata()
    {
      return mMetadata;
    }
    
    public String getPackageName()
    {
      return mPackageName;
    }
    
    public PlaybackStateCompat getPlaybackState()
    {
      return MediaSessionCompat.MediaSessionImplBase.this.getStateWithUpdatedPosition();
    }
    
    public List<MediaSessionCompat.QueueItem> getQueue()
    {
      synchronized (mLock)
      {
        List localList = mQueue;
        return localList;
      }
    }
    
    public CharSequence getQueueTitle()
    {
      return mQueueTitle;
    }
    
    public int getRatingType()
    {
      return mRatingType;
    }
    
    public String getTag()
    {
      return mTag;
    }
    
    public ParcelableVolumeInfo getVolumeAttributes()
    {
      synchronized (mLock)
      {
        int m = mVolumeType;
        int n = mLocalStream;
        VolumeProviderCompat localVolumeProviderCompat = mVolumeProvider;
        if (m == 2)
        {
          i = localVolumeProviderCompat.getVolumeControl();
          j = localVolumeProviderCompat.getMaxVolume();
          k = localVolumeProviderCompat.getCurrentVolume();
          return new ParcelableVolumeInfo(m, n, i, j, k);
        }
        int i = 2;
        int j = mAudioManager.getStreamMaxVolume(n);
        int k = mAudioManager.getStreamVolume(n);
      }
    }
    
    public boolean isTransportControlEnabled()
    {
      return (mFlags & 0x2) != 0;
    }
    
    public void next()
      throws RemoteException
    {
      MediaSessionCompat.MediaSessionImplBase.this.postToHandler(14);
    }
    
    public void pause()
      throws RemoteException
    {
      MediaSessionCompat.MediaSessionImplBase.this.postToHandler(12);
    }
    
    public void play()
      throws RemoteException
    {
      MediaSessionCompat.MediaSessionImplBase.this.postToHandler(7);
    }
    
    public void playFromMediaId(String paramString, Bundle paramBundle)
      throws RemoteException
    {
      MediaSessionCompat.MediaSessionImplBase.this.postToHandler(8, paramString, paramBundle);
    }
    
    public void playFromSearch(String paramString, Bundle paramBundle)
      throws RemoteException
    {
      MediaSessionCompat.MediaSessionImplBase.this.postToHandler(9, paramString, paramBundle);
    }
    
    public void playFromUri(Uri paramUri, Bundle paramBundle)
      throws RemoteException
    {
      MediaSessionCompat.MediaSessionImplBase.this.postToHandler(10, paramUri, paramBundle);
    }
    
    public void prepare()
      throws RemoteException
    {
      MediaSessionCompat.MediaSessionImplBase.this.postToHandler(3);
    }
    
    public void prepareFromMediaId(String paramString, Bundle paramBundle)
      throws RemoteException
    {
      MediaSessionCompat.MediaSessionImplBase.this.postToHandler(4, paramString, paramBundle);
    }
    
    public void prepareFromSearch(String paramString, Bundle paramBundle)
      throws RemoteException
    {
      MediaSessionCompat.MediaSessionImplBase.this.postToHandler(5, paramString, paramBundle);
    }
    
    public void prepareFromUri(Uri paramUri, Bundle paramBundle)
      throws RemoteException
    {
      MediaSessionCompat.MediaSessionImplBase.this.postToHandler(6, paramUri, paramBundle);
    }
    
    public void previous()
      throws RemoteException
    {
      MediaSessionCompat.MediaSessionImplBase.this.postToHandler(15);
    }
    
    public void rate(RatingCompat paramRatingCompat)
      throws RemoteException
    {
      MediaSessionCompat.MediaSessionImplBase.this.postToHandler(19, paramRatingCompat);
    }
    
    public void registerCallbackListener(IMediaControllerCallback paramIMediaControllerCallback)
    {
      if (mDestroyed) {}
      try
      {
        paramIMediaControllerCallback.onSessionDestroyed();
        return;
      }
      catch (Exception paramIMediaControllerCallback) {}
      mControllerCallbacks.register(paramIMediaControllerCallback);
      return;
    }
    
    public void rewind()
      throws RemoteException
    {
      MediaSessionCompat.MediaSessionImplBase.this.postToHandler(17);
    }
    
    public void seekTo(long paramLong)
      throws RemoteException
    {
      MediaSessionCompat.MediaSessionImplBase.this.postToHandler(18, Long.valueOf(paramLong));
    }
    
    public void sendCommand(String paramString, Bundle paramBundle, MediaSessionCompat.ResultReceiverWrapper paramResultReceiverWrapper)
    {
      MediaSessionCompat.MediaSessionImplBase.this.postToHandler(1, new MediaSessionCompat.MediaSessionImplBase.Command(paramString, paramBundle, MediaSessionCompat.ResultReceiverWrapper.access$900(paramResultReceiverWrapper)));
    }
    
    public void sendCustomAction(String paramString, Bundle paramBundle)
      throws RemoteException
    {
      MediaSessionCompat.MediaSessionImplBase.this.postToHandler(20, paramString, paramBundle);
    }
    
    public boolean sendMediaButton(KeyEvent paramKeyEvent)
    {
      if ((mFlags & 0x1) != 0) {}
      for (boolean bool = true;; bool = false)
      {
        if (bool) {
          MediaSessionCompat.MediaSessionImplBase.this.postToHandler(21, paramKeyEvent);
        }
        return bool;
      }
    }
    
    public void setVolumeTo(int paramInt1, int paramInt2, String paramString)
    {
      MediaSessionCompat.MediaSessionImplBase.this.setVolumeTo(paramInt1, paramInt2);
    }
    
    public void skipToQueueItem(long paramLong)
    {
      MediaSessionCompat.MediaSessionImplBase.this.postToHandler(11, Long.valueOf(paramLong));
    }
    
    public void stop()
      throws RemoteException
    {
      MediaSessionCompat.MediaSessionImplBase.this.postToHandler(13);
    }
    
    public void unregisterCallbackListener(IMediaControllerCallback paramIMediaControllerCallback)
    {
      mControllerCallbacks.unregister(paramIMediaControllerCallback);
    }
  }
  
  private class MessageHandler
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
    
    public MessageHandler(Looper paramLooper)
    {
      super();
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
        if (mState == null)
        {
          l = 0L;
          switch (paramKeyEvent.getKeyCode())
          {
          default: 
            return;
          case 79: 
          case 85: 
            if ((mState != null) && (mState.getState() == 3))
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
          l = mState.getActions();
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
      MediaSessionCompat.Callback localCallback = mCallback;
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
      MediaSessionCompat.MediaSessionImplBase.this.adjustVolume(((Integer)obj).intValue(), 0);
      return;
      MediaSessionCompat.MediaSessionImplBase.this.setVolumeTo(((Integer)obj).intValue(), 0);
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
}

/* Location:
 * Qualified Name:     android.support.v4.media.session.MediaSessionCompat.MediaSessionImplBase
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */