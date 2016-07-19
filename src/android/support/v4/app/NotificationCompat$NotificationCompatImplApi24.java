package android.support.v4.app;

import android.app.Notification;

class NotificationCompat$NotificationCompatImplApi24
  extends NotificationCompat.NotificationCompatImplApi21
{
  public Notification build(NotificationCompat.Builder paramBuilder, NotificationCompat.BuilderExtender paramBuilderExtender)
  {
    NotificationCompatApi24.Builder localBuilder = new NotificationCompatApi24.Builder(mContext, mNotification, mContentTitle, mContentText, mContentInfo, mTickerView, mNumber, mContentIntent, mFullScreenIntent, mLargeIcon, mProgressMax, mProgress, mProgressIndeterminate, mShowWhen, mUseChronometer, mPriority, mSubText, mLocalOnly, mCategory, mPeople, mExtras, mColor, mVisibility, mPublicVersion, mGroupKey, mGroupSummary, mSortKey, mRemoteInputHistory, mContentView, mBigContentView, mHeadsUpContentView);
    NotificationCompat.access$000(localBuilder, mActions);
    NotificationCompat.access$200(localBuilder, mStyle);
    paramBuilderExtender = paramBuilderExtender.build(paramBuilder, localBuilder);
    if (mStyle != null) {
      mStyle.addCompatExtras(getExtras(paramBuilderExtender));
    }
    return paramBuilderExtender;
  }
}

/* Location:
 * Qualified Name:     android.support.v4.app.NotificationCompat.NotificationCompatImplApi24
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */