package fr.dvilleneuve.lockito.core.manager;

import android.content.Context;

public final class BusManager_
  extends BusManager
{
  private Context context_;
  
  private BusManager_(Context paramContext)
  {
    context_ = paramContext;
    init_();
  }
  
  public static BusManager_ getInstance_(Context paramContext)
  {
    return new BusManager_(paramContext);
  }
  
  private void init_() {}
  
  public void rebind(Context paramContext)
  {
    context_ = paramContext;
    init_();
  }
}

/* Location:
 * Qualified Name:     fr.dvilleneuve.lockito.core.manager.BusManager_
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */