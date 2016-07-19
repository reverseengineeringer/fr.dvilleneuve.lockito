package android.support.v4.app;

import android.app.Activity;
import android.content.Intent;
import android.content.IntentSender;
import android.content.IntentSender.SendIntentException;

class ActivityCompatEclair
{
  public static void startIntentSenderForResult(Activity paramActivity, IntentSender paramIntentSender, int paramInt1, Intent paramIntent, int paramInt2, int paramInt3, int paramInt4)
    throws IntentSender.SendIntentException
  {
    paramActivity.startIntentSenderForResult(paramIntentSender, paramInt1, paramIntent, paramInt2, paramInt3, paramInt4);
  }
}

/* Location:
 * Qualified Name:     android.support.v4.app.ActivityCompatEclair
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */