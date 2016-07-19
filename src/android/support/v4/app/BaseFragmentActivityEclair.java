package android.support.v4.app;

import android.content.Intent;
import android.content.IntentSender;
import android.content.IntentSender.SendIntentException;
import android.support.annotation.Nullable;

abstract class BaseFragmentActivityEclair
  extends BaseFragmentActivityDonut
{
  boolean mStartedIntentSenderFromFragment;
  
  static void checkForValidRequestCode(int paramInt)
  {
    if ((0xFFFF0000 & paramInt) != 0) {
      throw new IllegalArgumentException("Can only use lower 16 bits for requestCode");
    }
  }
  
  void onBackPressedNotHandled()
  {
    super.onBackPressed();
  }
  
  public void startIntentSenderForResult(IntentSender paramIntentSender, int paramInt1, @Nullable Intent paramIntent, int paramInt2, int paramInt3, int paramInt4)
    throws IntentSender.SendIntentException
  {
    if ((!mStartedIntentSenderFromFragment) && (paramInt1 != -1)) {
      checkForValidRequestCode(paramInt1);
    }
    super.startIntentSenderForResult(paramIntentSender, paramInt1, paramIntent, paramInt2, paramInt3, paramInt4);
  }
}

/* Location:
 * Qualified Name:     android.support.v4.app.BaseFragmentActivityEclair
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */