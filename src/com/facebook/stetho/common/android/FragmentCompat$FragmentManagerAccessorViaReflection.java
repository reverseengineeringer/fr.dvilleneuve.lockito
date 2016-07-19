package com.facebook.stetho.common.android;

import com.facebook.stetho.common.ReflectionUtil;
import java.lang.reflect.Field;
import java.util.List;
import javax.annotation.Nullable;

class FragmentCompat$FragmentManagerAccessorViaReflection<FRAGMENT_MANAGER, FRAGMENT>
  implements FragmentManagerAccessor<FRAGMENT_MANAGER, FRAGMENT>
{
  @Nullable
  private Field mFieldMAdded;
  
  @Nullable
  public List<FRAGMENT> getAddedFragments(FRAGMENT_MANAGER paramFRAGMENT_MANAGER)
  {
    if (mFieldMAdded == null)
    {
      Field localField = ReflectionUtil.tryGetDeclaredField(paramFRAGMENT_MANAGER.getClass(), "mAdded");
      if (localField != null)
      {
        localField.setAccessible(true);
        mFieldMAdded = localField;
      }
    }
    if (mFieldMAdded != null) {
      return (List)ReflectionUtil.getFieldValue(mFieldMAdded, paramFRAGMENT_MANAGER);
    }
    return null;
  }
}

/* Location:
 * Qualified Name:     com.facebook.stetho.common.android.FragmentCompat.FragmentManagerAccessorViaReflection
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */