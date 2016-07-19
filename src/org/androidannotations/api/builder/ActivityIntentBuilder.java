package org.androidannotations.api.builder;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

public abstract class ActivityIntentBuilder<I extends ActivityIntentBuilder<I>>
  extends IntentBuilder<I>
{
  public ActivityIntentBuilder(Context paramContext, Intent paramIntent)
  {
    super(paramContext, paramIntent);
  }
  
  public ActivityIntentBuilder(Context paramContext, Class<?> paramClass)
  {
    super(paramContext, paramClass);
  }
  
  public void start()
  {
    context.startActivity(intent);
  }
  
  public void startForResult(int paramInt)
  {
    if ((context instanceof Activity))
    {
      ((Activity)context).startActivityForResult(intent, paramInt);
      return;
    }
    context.startActivity(intent);
  }
}

/* Location:
 * Qualified Name:     org.androidannotations.api.builder.ActivityIntentBuilder
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */