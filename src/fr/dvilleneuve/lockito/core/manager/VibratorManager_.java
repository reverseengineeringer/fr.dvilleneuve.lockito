package fr.dvilleneuve.lockito.core.manager;

import android.content.Context;
import android.os.Vibrator;

public final class VibratorManager_
  extends VibratorManager
{
  private Context context_;
  
  private VibratorManager_(Context paramContext)
  {
    context_ = paramContext;
    init_();
  }
  
  public static VibratorManager_ getInstance_(Context paramContext)
  {
    return new VibratorManager_(paramContext);
  }
  
  private void init_()
  {
    vibrator = ((Vibrator)context_.getSystemService("vibrator"));
  }
  
  public void rebind(Context paramContext)
  {
    context_ = paramContext;
    init_();
  }
}

/* Location:
 * Qualified Name:     fr.dvilleneuve.lockito.core.manager.VibratorManager_
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */