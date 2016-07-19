package fr.dvilleneuve.lockito.ui.adapter;

import android.content.Context;

public final class SelectFileAdapter_
  extends SelectFileAdapter
{
  private Context context_;
  
  private SelectFileAdapter_(Context paramContext)
  {
    super(paramContext);
    context_ = paramContext;
    init_();
  }
  
  public static SelectFileAdapter_ getInstance_(Context paramContext)
  {
    return new SelectFileAdapter_(paramContext);
  }
  
  private void init_() {}
  
  public void rebind(Context paramContext)
  {
    context_ = paramContext;
    init_();
  }
}

/* Location:
 * Qualified Name:     fr.dvilleneuve.lockito.ui.adapter.SelectFileAdapter_
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */