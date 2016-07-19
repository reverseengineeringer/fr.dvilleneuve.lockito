package android.support.v4.view;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;

class PointerIconCompat$Api24PointerIconCompatImpl
  extends PointerIconCompat.BasePointerIconCompatImpl
{
  public Object create(Bitmap paramBitmap, float paramFloat1, float paramFloat2)
  {
    return PointerIconCompatApi24.create(paramBitmap, paramFloat1, paramFloat2);
  }
  
  public Object getSystemIcon(Context paramContext, int paramInt)
  {
    return PointerIconCompatApi24.getSystemIcon(paramContext, paramInt);
  }
  
  public Object load(Resources paramResources, int paramInt)
  {
    return PointerIconCompatApi24.load(paramResources, paramInt);
  }
}

/* Location:
 * Qualified Name:     android.support.v4.view.PointerIconCompat.Api24PointerIconCompatImpl
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */