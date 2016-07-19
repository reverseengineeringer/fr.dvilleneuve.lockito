package org.androidannotations.api.builder;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;

public abstract class ServiceIntentBuilder<I extends ServiceIntentBuilder<I>>
  extends IntentBuilder<I>
{
  public ServiceIntentBuilder(Context paramContext, Intent paramIntent)
  {
    super(paramContext, paramIntent);
  }
  
  public ServiceIntentBuilder(Context paramContext, Class<?> paramClass)
  {
    super(paramContext, paramClass);
  }
  
  public ComponentName start()
  {
    return context.startService(intent);
  }
  
  public boolean stop()
  {
    return context.stopService(intent);
  }
}

/* Location:
 * Qualified Name:     org.androidannotations.api.builder.ServiceIntentBuilder
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */