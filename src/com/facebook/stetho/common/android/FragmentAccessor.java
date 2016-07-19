package com.facebook.stetho.common.android;

import android.content.res.Resources;
import android.view.View;
import javax.annotation.Nullable;

public abstract interface FragmentAccessor<FRAGMENT, FRAGMENT_MANAGER>
{
  public static final int NO_ID = 0;
  
  @Nullable
  public abstract FRAGMENT_MANAGER getChildFragmentManager(FRAGMENT paramFRAGMENT);
  
  @Nullable
  public abstract FRAGMENT_MANAGER getFragmentManager(FRAGMENT paramFRAGMENT);
  
  public abstract int getId(FRAGMENT paramFRAGMENT);
  
  public abstract Resources getResources(FRAGMENT paramFRAGMENT);
  
  @Nullable
  public abstract String getTag(FRAGMENT paramFRAGMENT);
  
  @Nullable
  public abstract View getView(FRAGMENT paramFRAGMENT);
}

/* Location:
 * Qualified Name:     com.facebook.stetho.common.android.FragmentAccessor
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */