package fr.dvilleneuve.lockito.core.helper;

import android.content.Context;

public final class AdHelper_
  extends AdHelper
{
  private Context context_;
  
  private AdHelper_(Context paramContext)
  {
    context_ = paramContext;
    init_();
  }
  
  public static AdHelper_ getInstance_(Context paramContext)
  {
    return new AdHelper_(paramContext);
  }
  
  private void init_() {}
  
  public void rebind(Context paramContext)
  {
    context_ = paramContext;
    init_();
  }
}

/* Location:
 * Qualified Name:     fr.dvilleneuve.lockito.core.helper.AdHelper_
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */