package android.support.v4.content;

import android.content.Context;
import java.io.File;

public class ContextCompatApi24
{
  public static Context createDeviceProtectedStorageContext(Context paramContext)
  {
    return paramContext.createDeviceProtectedStorageContext();
  }
  
  public static File getDataDir(Context paramContext)
  {
    return paramContext.getDataDir();
  }
  
  public static boolean isDeviceProtectedStorage(Context paramContext)
  {
    return paramContext.isDeviceProtectedStorage();
  }
}

/* Location:
 * Qualified Name:     android.support.v4.content.ContextCompatApi24
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */