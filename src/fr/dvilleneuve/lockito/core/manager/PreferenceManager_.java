package fr.dvilleneuve.lockito.core.manager;

import android.content.Context;

public final class PreferenceManager_
  extends PreferenceManager
{
  private Context context_;
  
  private PreferenceManager_(Context paramContext)
  {
    context_ = paramContext;
    init_();
  }
  
  public static PreferenceManager_ getInstance_(Context paramContext)
  {
    return new PreferenceManager_(paramContext);
  }
  
  private void init_()
  {
    context = context_;
    init();
  }
  
  public void rebind(Context paramContext)
  {
    context_ = paramContext;
    init_();
  }
}

/* Location:
 * Qualified Name:     fr.dvilleneuve.lockito.core.manager.PreferenceManager_
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */