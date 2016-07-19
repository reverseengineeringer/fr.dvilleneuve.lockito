package android.support.v4.app;

import android.content.Intent;
import android.content.IntentSender;
import android.content.IntentSender.SendIntentException;
import android.os.Bundle;
import android.support.annotation.Nullable;

abstract class BaseFragmentActivityJB
  extends BaseFragmentActivityHoneycomb
{
  boolean mStartedActivityFromFragment;
  
  public void startActivityForResult(Intent paramIntent, int paramInt, @Nullable Bundle paramBundle)
  {
    if ((!mStartedActivityFromFragment) && (paramInt != -1)) {
      checkForValidRequestCode(paramInt);
    }
    super.startActivityForResult(paramIntent, paramInt, paramBundle);
  }
  
  public void startIntentSenderForResult(IntentSender paramIntentSender, int paramInt1, @Nullable Intent paramIntent, int paramInt2, int paramInt3, int paramInt4, Bundle paramBundle)
    throws IntentSender.SendIntentException
  {
    if ((!mStartedIntentSenderFromFragment) && (paramInt1 != -1)) {
      checkForValidRequestCode(paramInt1);
    }
    super.startIntentSenderForResult(paramIntentSender, paramInt1, paramIntent, paramInt2, paramInt3, paramInt4, paramBundle);
  }
}

/* Location:
 * Qualified Name:     android.support.v4.app.BaseFragmentActivityJB
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */