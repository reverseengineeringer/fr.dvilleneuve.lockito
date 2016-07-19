package android.support.v4.os;

import android.os.Build.VERSION;

public class BuildCompat
{
  public static boolean isAtLeastN()
  {
    return Build.VERSION.SDK_INT >= 24;
  }
}

/* Location:
 * Qualified Name:     android.support.v4.os.BuildCompat
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */