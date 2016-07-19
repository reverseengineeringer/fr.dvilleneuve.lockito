package fr.dvilleneuve.lockito.core.utils;

import android.content.Context;

public final class GeometryUtils_
  extends GeometryUtils
{
  private Context context_;
  
  private GeometryUtils_(Context paramContext)
  {
    context_ = paramContext;
    init_();
  }
  
  public static GeometryUtils_ getInstance_(Context paramContext)
  {
    return new GeometryUtils_(paramContext);
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
 * Qualified Name:     fr.dvilleneuve.lockito.core.utils.GeometryUtils_
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */