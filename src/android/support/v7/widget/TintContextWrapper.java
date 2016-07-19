package android.support.v7.widget;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.Resources;
import android.content.res.Resources.Theme;
import android.os.Build.VERSION;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatDelegate;
import java.lang.ref.WeakReference;
import java.util.ArrayList;

public class TintContextWrapper
  extends ContextWrapper
{
  private static final ArrayList<WeakReference<TintContextWrapper>> sCache = new ArrayList();
  private Resources mResources;
  private final Resources.Theme mTheme;
  
  private TintContextWrapper(@NonNull Context paramContext)
  {
    super(paramContext);
    if (VectorEnabledTintResources.shouldBeUsed())
    {
      mTheme = getResources().newTheme();
      mTheme.setTo(paramContext.getTheme());
      return;
    }
    mTheme = null;
  }
  
  private static boolean shouldWrap(@NonNull Context paramContext)
  {
    if (((paramContext instanceof TintContextWrapper)) || ((paramContext.getResources() instanceof TintResources)) || ((paramContext.getResources() instanceof VectorEnabledTintResources))) {}
    while ((AppCompatDelegate.isCompatVectorFromResourcesEnabled()) && (Build.VERSION.SDK_INT > 20)) {
      return false;
    }
    return true;
  }
  
  public static Context wrap(@NonNull Context paramContext)
  {
    if (shouldWrap(paramContext))
    {
      int i = 0;
      int j = sCache.size();
      while (i < j)
      {
        Object localObject = (WeakReference)sCache.get(i);
        if (localObject != null) {}
        for (localObject = (TintContextWrapper)((WeakReference)localObject).get(); (localObject != null) && (((TintContextWrapper)localObject).getBaseContext() == paramContext); localObject = null) {
          return (Context)localObject;
        }
        i += 1;
      }
      paramContext = new TintContextWrapper(paramContext);
      sCache.add(new WeakReference(paramContext));
      return paramContext;
    }
    return paramContext;
  }
  
  public Resources getResources()
  {
    if (mResources == null) {
      if (mTheme != null) {
        break label37;
      }
    }
    label37:
    for (Object localObject = new TintResources(this, super.getResources());; localObject = new VectorEnabledTintResources(this, super.getResources()))
    {
      mResources = ((Resources)localObject);
      return mResources;
    }
  }
  
  public Resources.Theme getTheme()
  {
    if (mTheme == null) {
      return super.getTheme();
    }
    return mTheme;
  }
  
  public void setTheme(int paramInt)
  {
    if (mTheme == null)
    {
      super.setTheme(paramInt);
      return;
    }
    mTheme.applyStyle(paramInt, true);
  }
}

/* Location:
 * Qualified Name:     android.support.v7.widget.TintContextWrapper
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */