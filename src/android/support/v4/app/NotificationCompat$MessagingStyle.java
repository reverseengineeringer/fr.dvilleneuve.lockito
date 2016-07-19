package android.support.v4.app;

import android.app.Notification;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import java.util.ArrayList;
import java.util.List;

public class NotificationCompat$MessagingStyle
  extends NotificationCompat.Style
{
  public static final int MAXIMUM_RETAINED_MESSAGES = 25;
  CharSequence mConversationTitle;
  List<Message> mMessages = new ArrayList();
  CharSequence mUserDisplayName;
  
  NotificationCompat$MessagingStyle() {}
  
  public NotificationCompat$MessagingStyle(CharSequence paramCharSequence)
  {
    mUserDisplayName = paramCharSequence;
  }
  
  public static MessagingStyle extractMessagingStyleFromNotification(Notification paramNotification)
  {
    paramNotification = NotificationCompat.access$300().getExtras(paramNotification);
    if (!paramNotification.containsKey("android.selfDisplayName")) {
      return null;
    }
    try
    {
      MessagingStyle localMessagingStyle = new MessagingStyle();
      localMessagingStyle.restoreFromCompatExtras(paramNotification);
      return localMessagingStyle;
    }
    catch (ClassCastException paramNotification) {}
    return null;
  }
  
  public void addCompatExtras(Bundle paramBundle)
  {
    super.addCompatExtras(paramBundle);
    if (mUserDisplayName != null) {
      paramBundle.putCharSequence("android.selfDisplayName", mUserDisplayName);
    }
    if (mConversationTitle != null) {
      paramBundle.putCharSequence("android.conversationTitle", mConversationTitle);
    }
    if (!mMessages.isEmpty()) {
      paramBundle.putParcelableArray("android.messages", Message.getBundleArrayForMessages(mMessages));
    }
  }
  
  public MessagingStyle addMessage(Message paramMessage)
  {
    mMessages.add(paramMessage);
    if (mMessages.size() > 25) {
      mMessages.remove(0);
    }
    return this;
  }
  
  public MessagingStyle addMessage(CharSequence paramCharSequence1, long paramLong, CharSequence paramCharSequence2)
  {
    mMessages.add(new Message(paramCharSequence1, paramLong, paramCharSequence2));
    if (mMessages.size() > 25) {
      mMessages.remove(0);
    }
    return this;
  }
  
  public CharSequence getConversationTitle()
  {
    return mConversationTitle;
  }
  
  public List<Message> getMessages()
  {
    return mMessages;
  }
  
  public CharSequence getUserDisplayName()
  {
    return mUserDisplayName;
  }
  
  protected void restoreFromCompatExtras(Bundle paramBundle)
  {
    mMessages.clear();
    mUserDisplayName = paramBundle.getString("android.selfDisplayName");
    mConversationTitle = paramBundle.getString("android.conversationTitle");
    paramBundle = paramBundle.getParcelableArray("android.messages");
    if (paramBundle != null) {
      mMessages = Message.getMessagesFromBundleArray(paramBundle);
    }
  }
  
  public MessagingStyle setConversationTitle(CharSequence paramCharSequence)
  {
    mConversationTitle = paramCharSequence;
    return this;
  }
  
  public static final class Message
  {
    static final String KEY_DATA_MIME_TYPE = "type";
    static final String KEY_DATA_URI = "uri";
    static final String KEY_SENDER = "sender";
    static final String KEY_TEXT = "text";
    static final String KEY_TIMESTAMP = "time";
    private String mDataMimeType;
    private Uri mDataUri;
    private final CharSequence mSender;
    private final CharSequence mText;
    private final long mTimestamp;
    
    public Message(CharSequence paramCharSequence1, long paramLong, CharSequence paramCharSequence2)
    {
      mText = paramCharSequence1;
      mTimestamp = paramLong;
      mSender = paramCharSequence2;
    }
    
    static Bundle[] getBundleArrayForMessages(List<Message> paramList)
    {
      Bundle[] arrayOfBundle = new Bundle[paramList.size()];
      int j = paramList.size();
      int i = 0;
      while (i < j)
      {
        arrayOfBundle[i] = ((Message)paramList.get(i)).toBundle();
        i += 1;
      }
      return arrayOfBundle;
    }
    
    static Message getMessageFromBundle(Bundle paramBundle)
    {
      try
      {
        if ((paramBundle.containsKey("text")) && (paramBundle.containsKey("time")))
        {
          Message localMessage2 = new Message(paramBundle.getCharSequence("text"), paramBundle.getLong("time"), paramBundle.getCharSequence("sender"));
          localMessage1 = localMessage2;
          if (!paramBundle.containsKey("type")) {
            return localMessage1;
          }
          localMessage1 = localMessage2;
          if (!paramBundle.containsKey("uri")) {
            return localMessage1;
          }
          localMessage2.setData(paramBundle.getString("type"), (Uri)paramBundle.getParcelable("uri"));
          return localMessage2;
        }
      }
      catch (ClassCastException paramBundle)
      {
        return null;
      }
      Message localMessage1 = null;
      return localMessage1;
    }
    
    static List<Message> getMessagesFromBundleArray(Parcelable[] paramArrayOfParcelable)
    {
      ArrayList localArrayList = new ArrayList(paramArrayOfParcelable.length);
      int i = 0;
      while (i < paramArrayOfParcelable.length)
      {
        if ((paramArrayOfParcelable[i] instanceof Bundle))
        {
          Message localMessage = getMessageFromBundle((Bundle)paramArrayOfParcelable[i]);
          if (localMessage != null) {
            localArrayList.add(localMessage);
          }
        }
        i += 1;
      }
      return localArrayList;
    }
    
    private Bundle toBundle()
    {
      Bundle localBundle = new Bundle();
      if (mText != null) {
        localBundle.putCharSequence("text", mText);
      }
      localBundle.putLong("time", mTimestamp);
      if (mSender != null) {
        localBundle.putCharSequence("sender", mSender);
      }
      if (mDataMimeType != null) {
        localBundle.putString("type", mDataMimeType);
      }
      if (mDataUri != null) {
        localBundle.putParcelable("uri", mDataUri);
      }
      return localBundle;
    }
    
    public String getDataMimeType()
    {
      return mDataMimeType;
    }
    
    public Uri getDataUri()
    {
      return mDataUri;
    }
    
    public CharSequence getSender()
    {
      return mSender;
    }
    
    public CharSequence getText()
    {
      return mText;
    }
    
    public long getTimestamp()
    {
      return mTimestamp;
    }
    
    public Message setData(String paramString, Uri paramUri)
    {
      mDataMimeType = paramString;
      mDataUri = paramUri;
      return this;
    }
  }
}

/* Location:
 * Qualified Name:     android.support.v4.app.NotificationCompat.MessagingStyle
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */