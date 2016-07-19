package com.facebook.stetho.common.android;

import android.app.Activity;
import javax.annotation.Nullable;

public abstract interface FragmentActivityAccessor<FRAGMENT_ACTIVITY extends Activity, FRAGMENT_MANAGER>
{
  @Nullable
  public abstract FRAGMENT_MANAGER getFragmentManager(FRAGMENT_ACTIVITY paramFRAGMENT_ACTIVITY);
}

/* Location:
 * Qualified Name:     com.facebook.stetho.common.android.FragmentActivityAccessor
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */