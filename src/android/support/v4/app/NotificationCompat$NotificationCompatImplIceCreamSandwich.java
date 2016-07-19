package android.support.v4.app;

import android.app.Notification;

class NotificationCompat$NotificationCompatImplIceCreamSandwich
  extends NotificationCompat.NotificationCompatImplBase
{
  public Notification build(NotificationCompat.Builder paramBuilder, NotificationCompat.BuilderExtender paramBuilderExtender)
  {
    paramBuilderExtender = paramBuilderExtender.build(paramBuilder, new NotificationCompatIceCreamSandwich.Builder(mContext, mNotification, mContentTitle, mContentText, mContentInfo, mTickerView, mNumber, mContentIntent, mFullScreenIntent, mLargeIcon, mProgressMax, mProgress, mProgressIndeterminate));
    if (mContentView != null) {
      contentView = mContentView;
    }
    return paramBuilderExtender;
  }
}

/* Location:
 * Qualified Name:     android.support.v4.app.NotificationCompat.NotificationCompatImplIceCreamSandwich
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */