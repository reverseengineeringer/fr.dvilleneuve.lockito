package android.support.v7.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.support.v7.appcompat.R.styleable;
import android.support.v7.text.AllCapsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.AttributeSet;
import android.widget.TextView;

class AppCompatTextHelper
{
  private static final int[] VIEW_ATTRS = { 16842804, 16843119, 16843117, 16843120, 16843118 };
  private TintInfo mDrawableBottomTint;
  private TintInfo mDrawableLeftTint;
  private TintInfo mDrawableRightTint;
  private TintInfo mDrawableTopTint;
  final TextView mView;
  
  AppCompatTextHelper(TextView paramTextView)
  {
    mView = paramTextView;
  }
  
  static AppCompatTextHelper create(TextView paramTextView)
  {
    if (Build.VERSION.SDK_INT >= 17) {
      return new AppCompatTextHelperV17(paramTextView);
    }
    return new AppCompatTextHelper(paramTextView);
  }
  
  protected static TintInfo createTintInfo(Context paramContext, AppCompatDrawableManager paramAppCompatDrawableManager, int paramInt)
  {
    paramContext = paramAppCompatDrawableManager.getTintList(paramContext, paramInt);
    if (paramContext != null)
    {
      paramAppCompatDrawableManager = new TintInfo();
      mHasTintList = true;
      mTintList = paramContext;
      return paramAppCompatDrawableManager;
    }
    return null;
  }
  
  final void applyCompoundDrawableTint(Drawable paramDrawable, TintInfo paramTintInfo)
  {
    if ((paramDrawable != null) && (paramTintInfo != null)) {
      AppCompatDrawableManager.tintDrawable(paramDrawable, paramTintInfo, mView.getDrawableState());
    }
  }
  
  void applyCompoundDrawablesTints()
  {
    if ((mDrawableLeftTint != null) || (mDrawableTopTint != null) || (mDrawableRightTint != null) || (mDrawableBottomTint != null))
    {
      Drawable[] arrayOfDrawable = mView.getCompoundDrawables();
      applyCompoundDrawableTint(arrayOfDrawable[0], mDrawableLeftTint);
      applyCompoundDrawableTint(arrayOfDrawable[1], mDrawableTopTint);
      applyCompoundDrawableTint(arrayOfDrawable[2], mDrawableRightTint);
      applyCompoundDrawableTint(arrayOfDrawable[3], mDrawableBottomTint);
    }
  }
  
  void loadFromAttributes(AttributeSet paramAttributeSet, int paramInt)
  {
    Context localContext = mView.getContext();
    Object localObject = AppCompatDrawableManager.get();
    TintTypedArray localTintTypedArray1 = TintTypedArray.obtainStyledAttributes(localContext, paramAttributeSet, VIEW_ATTRS, paramInt, 0);
    int k = localTintTypedArray1.getResourceId(0, -1);
    if (localTintTypedArray1.hasValue(1)) {
      mDrawableLeftTint = createTintInfo(localContext, (AppCompatDrawableManager)localObject, localTintTypedArray1.getResourceId(1, 0));
    }
    if (localTintTypedArray1.hasValue(2)) {
      mDrawableTopTint = createTintInfo(localContext, (AppCompatDrawableManager)localObject, localTintTypedArray1.getResourceId(2, 0));
    }
    if (localTintTypedArray1.hasValue(3)) {
      mDrawableRightTint = createTintInfo(localContext, (AppCompatDrawableManager)localObject, localTintTypedArray1.getResourceId(3, 0));
    }
    if (localTintTypedArray1.hasValue(4)) {
      mDrawableBottomTint = createTintInfo(localContext, (AppCompatDrawableManager)localObject, localTintTypedArray1.getResourceId(4, 0));
    }
    localTintTypedArray1.recycle();
    boolean bool3 = mView.getTransformationMethod() instanceof PasswordTransformationMethod;
    boolean bool1 = false;
    boolean bool2 = false;
    int i = 0;
    int j = 0;
    localObject = null;
    localTintTypedArray1 = null;
    if (k != -1)
    {
      TintTypedArray localTintTypedArray2 = TintTypedArray.obtainStyledAttributes(localContext, k, R.styleable.TextAppearance);
      bool1 = bool2;
      i = j;
      if (!bool3)
      {
        bool1 = bool2;
        i = j;
        if (localTintTypedArray2.hasValue(R.styleable.TextAppearance_textAllCaps))
        {
          i = 1;
          bool1 = localTintTypedArray2.getBoolean(R.styleable.TextAppearance_textAllCaps, false);
        }
      }
      localObject = localTintTypedArray1;
      if (Build.VERSION.SDK_INT < 23)
      {
        localObject = localTintTypedArray1;
        if (localTintTypedArray2.hasValue(R.styleable.TextAppearance_android_textColor)) {
          localObject = localTintTypedArray2.getColorStateList(R.styleable.TextAppearance_android_textColor);
        }
      }
      localTintTypedArray2.recycle();
    }
    localTintTypedArray1 = TintTypedArray.obtainStyledAttributes(localContext, paramAttributeSet, R.styleable.TextAppearance, paramInt, 0);
    bool2 = bool1;
    paramInt = i;
    if (!bool3)
    {
      bool2 = bool1;
      paramInt = i;
      if (localTintTypedArray1.hasValue(R.styleable.TextAppearance_textAllCaps))
      {
        paramInt = 1;
        bool2 = localTintTypedArray1.getBoolean(R.styleable.TextAppearance_textAllCaps, false);
      }
    }
    paramAttributeSet = (AttributeSet)localObject;
    if (Build.VERSION.SDK_INT < 23)
    {
      paramAttributeSet = (AttributeSet)localObject;
      if (localTintTypedArray1.hasValue(R.styleable.TextAppearance_android_textColor)) {
        paramAttributeSet = localTintTypedArray1.getColorStateList(R.styleable.TextAppearance_android_textColor);
      }
    }
    localTintTypedArray1.recycle();
    if (paramAttributeSet != null) {
      mView.setTextColor(paramAttributeSet);
    }
    if ((!bool3) && (paramInt != 0)) {
      setAllCaps(bool2);
    }
  }
  
  void onSetTextAppearance(Context paramContext, int paramInt)
  {
    paramContext = TintTypedArray.obtainStyledAttributes(paramContext, paramInt, R.styleable.TextAppearance);
    if (paramContext.hasValue(R.styleable.TextAppearance_textAllCaps)) {
      setAllCaps(paramContext.getBoolean(R.styleable.TextAppearance_textAllCaps, false));
    }
    if ((Build.VERSION.SDK_INT < 23) && (paramContext.hasValue(R.styleable.TextAppearance_android_textColor)))
    {
      ColorStateList localColorStateList = paramContext.getColorStateList(R.styleable.TextAppearance_android_textColor);
      if (localColorStateList != null) {
        mView.setTextColor(localColorStateList);
      }
    }
    paramContext.recycle();
  }
  
  void setAllCaps(boolean paramBoolean)
  {
    TextView localTextView = mView;
    if (paramBoolean) {}
    for (AllCapsTransformationMethod localAllCapsTransformationMethod = new AllCapsTransformationMethod(mView.getContext());; localAllCapsTransformationMethod = null)
    {
      localTextView.setTransformationMethod(localAllCapsTransformationMethod);
      return;
    }
  }
}

/* Location:
 * Qualified Name:     android.support.v7.widget.AppCompatTextHelper
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */