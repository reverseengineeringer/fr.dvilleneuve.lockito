package fr.dvilleneuve.lockito.core.helper;

import android.content.Context;
import fr.dvilleneuve.lockito.core.manager.PreferenceManager_;

public final class StringFormatHelper_
  extends StringFormatHelper
{
  private Context context_;
  
  private StringFormatHelper_(Context paramContext)
  {
    context_ = paramContext;
    init_();
  }
  
  public static StringFormatHelper_ getInstance_(Context paramContext)
  {
    return new StringFormatHelper_(paramContext);
  }
  
  private void init_()
  {
    context = context_;
    preferenceManager = PreferenceManager_.getInstance_(context_);
  }
  
  public void rebind(Context paramContext)
  {
    context_ = paramContext;
    init_();
  }
}

/* Location:
 * Qualified Name:     fr.dvilleneuve.lockito.core.helper.StringFormatHelper_
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */