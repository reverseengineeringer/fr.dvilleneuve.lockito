package com.facebook.stetho.common.android;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.res.Resources;
import android.os.Build.VERSION;
import android.view.View;
import javax.annotation.Nullable;

@TargetApi(11)
final class FragmentCompatFramework
  extends FragmentCompat<Fragment, DialogFragment, FragmentManager, Activity>
{
  private static final DialogFragmentAccessorFramework sDialogFragmentAccessor;
  private static final FragmentAccessorFrameworkHoneycomb sFragmentAccessor;
  private static final FragmentActivityAccessorFramework sFragmentActivityAccessor;
  private static final FragmentCompat.FragmentManagerAccessorViaReflection<FragmentManager, Fragment> sFragmentManagerAccessor = new FragmentCompat.FragmentManagerAccessorViaReflection();
  
  static
  {
    sFragmentActivityAccessor = new FragmentActivityAccessorFramework(null);
    if (Build.VERSION.SDK_INT >= 17) {}
    for (sFragmentAccessor = new FragmentAccessorFrameworkJellyBean(null);; sFragmentAccessor = new FragmentAccessorFrameworkHoneycomb(null))
    {
      sDialogFragmentAccessor = new DialogFragmentAccessorFramework(sFragmentAccessor);
      return;
    }
  }
  
  public DialogFragmentAccessorFramework forDialogFragment()
  {
    return sDialogFragmentAccessor;
  }
  
  public FragmentAccessorFrameworkHoneycomb forFragment()
  {
    return sFragmentAccessor;
  }
  
  public FragmentActivityAccessorFramework forFragmentActivity()
  {
    return sFragmentActivityAccessor;
  }
  
  public FragmentCompat.FragmentManagerAccessorViaReflection<FragmentManager, Fragment> forFragmentManager()
  {
    return sFragmentManagerAccessor;
  }
  
  public Class<DialogFragment> getDialogFragmentClass()
  {
    return DialogFragment.class;
  }
  
  public Class<Activity> getFragmentActivityClass()
  {
    return Activity.class;
  }
  
  public Class<Fragment> getFragmentClass()
  {
    return Fragment.class;
  }
  
  private static class DialogFragmentAccessorFramework
    implements DialogFragmentAccessor<DialogFragment, Fragment, FragmentManager>
  {
    private final FragmentAccessor<Fragment, FragmentManager> mFragmentAccessor;
    
    public DialogFragmentAccessorFramework(FragmentAccessor<Fragment, FragmentManager> paramFragmentAccessor)
    {
      mFragmentAccessor = paramFragmentAccessor;
    }
    
    @Nullable
    public FragmentManager getChildFragmentManager(Fragment paramFragment)
    {
      return (FragmentManager)mFragmentAccessor.getChildFragmentManager(paramFragment);
    }
    
    public Dialog getDialog(DialogFragment paramDialogFragment)
    {
      return paramDialogFragment.getDialog();
    }
    
    @Nullable
    public FragmentManager getFragmentManager(Fragment paramFragment)
    {
      return (FragmentManager)mFragmentAccessor.getFragmentManager(paramFragment);
    }
    
    public int getId(Fragment paramFragment)
    {
      return mFragmentAccessor.getId(paramFragment);
    }
    
    public Resources getResources(Fragment paramFragment)
    {
      return mFragmentAccessor.getResources(paramFragment);
    }
    
    @Nullable
    public String getTag(Fragment paramFragment)
    {
      return mFragmentAccessor.getTag(paramFragment);
    }
    
    @Nullable
    public View getView(Fragment paramFragment)
    {
      return mFragmentAccessor.getView(paramFragment);
    }
  }
  
  private static class FragmentAccessorFrameworkHoneycomb
    implements FragmentAccessor<Fragment, FragmentManager>
  {
    @Nullable
    public FragmentManager getChildFragmentManager(Fragment paramFragment)
    {
      return null;
    }
    
    @Nullable
    public FragmentManager getFragmentManager(Fragment paramFragment)
    {
      return paramFragment.getFragmentManager();
    }
    
    public int getId(Fragment paramFragment)
    {
      return paramFragment.getId();
    }
    
    public Resources getResources(Fragment paramFragment)
    {
      return paramFragment.getResources();
    }
    
    @Nullable
    public String getTag(Fragment paramFragment)
    {
      return paramFragment.getTag();
    }
    
    @Nullable
    public View getView(Fragment paramFragment)
    {
      return paramFragment.getView();
    }
  }
  
  @TargetApi(17)
  private static class FragmentAccessorFrameworkJellyBean
    extends FragmentCompatFramework.FragmentAccessorFrameworkHoneycomb
  {
    private FragmentAccessorFrameworkJellyBean()
    {
      super();
    }
    
    @Nullable
    public FragmentManager getChildFragmentManager(Fragment paramFragment)
    {
      return paramFragment.getChildFragmentManager();
    }
  }
  
  private static class FragmentActivityAccessorFramework
    implements FragmentActivityAccessor<Activity, FragmentManager>
  {
    @Nullable
    public FragmentManager getFragmentManager(Activity paramActivity)
    {
      return paramActivity.getFragmentManager();
    }
  }
}

/* Location:
 * Qualified Name:     com.facebook.stetho.common.android.FragmentCompatFramework
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */