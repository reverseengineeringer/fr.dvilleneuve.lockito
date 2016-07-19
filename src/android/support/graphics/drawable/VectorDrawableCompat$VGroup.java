package android.support.graphics.drawable;

import android.content.res.Resources;
import android.content.res.Resources.Theme;
import android.content.res.TypedArray;
import android.graphics.Matrix;
import android.support.v4.util.ArrayMap;
import android.util.AttributeSet;
import java.util.ArrayList;
import org.xmlpull.v1.XmlPullParser;

class VectorDrawableCompat$VGroup
{
  private int mChangingConfigurations;
  final ArrayList<Object> mChildren = new ArrayList();
  private String mGroupName = null;
  private final Matrix mLocalMatrix = new Matrix();
  private float mPivotX = 0.0F;
  private float mPivotY = 0.0F;
  private float mRotate = 0.0F;
  private float mScaleX = 1.0F;
  private float mScaleY = 1.0F;
  private final Matrix mStackedMatrix = new Matrix();
  private int[] mThemeAttrs;
  private float mTranslateX = 0.0F;
  private float mTranslateY = 0.0F;
  
  public VectorDrawableCompat$VGroup() {}
  
  public VectorDrawableCompat$VGroup(VGroup paramVGroup, ArrayMap<String, Object> paramArrayMap)
  {
    mRotate = mRotate;
    mPivotX = mPivotX;
    mPivotY = mPivotY;
    mScaleX = mScaleX;
    mScaleY = mScaleY;
    mTranslateX = mTranslateX;
    mTranslateY = mTranslateY;
    mThemeAttrs = mThemeAttrs;
    mGroupName = mGroupName;
    mChangingConfigurations = mChangingConfigurations;
    if (mGroupName != null) {
      paramArrayMap.put(mGroupName, this);
    }
    mLocalMatrix.set(mLocalMatrix);
    ArrayList localArrayList = mChildren;
    int i = 0;
    while (i < localArrayList.size())
    {
      paramVGroup = localArrayList.get(i);
      if ((paramVGroup instanceof VGroup))
      {
        paramVGroup = (VGroup)paramVGroup;
        mChildren.add(new VGroup(paramVGroup, paramArrayMap));
        i += 1;
      }
      else
      {
        if ((paramVGroup instanceof VectorDrawableCompat.VFullPath)) {}
        for (paramVGroup = new VectorDrawableCompat.VFullPath((VectorDrawableCompat.VFullPath)paramVGroup);; paramVGroup = new VectorDrawableCompat.VClipPath((VectorDrawableCompat.VClipPath)paramVGroup))
        {
          mChildren.add(paramVGroup);
          if (mPathName == null) {
            break;
          }
          paramArrayMap.put(mPathName, paramVGroup);
          break;
          if (!(paramVGroup instanceof VectorDrawableCompat.VClipPath)) {
            break label315;
          }
        }
        label315:
        throw new IllegalStateException("Unknown object in the tree!");
      }
    }
  }
  
  private void updateLocalMatrix()
  {
    mLocalMatrix.reset();
    mLocalMatrix.postTranslate(-mPivotX, -mPivotY);
    mLocalMatrix.postScale(mScaleX, mScaleY);
    mLocalMatrix.postRotate(mRotate, 0.0F, 0.0F);
    mLocalMatrix.postTranslate(mTranslateX + mPivotX, mTranslateY + mPivotY);
  }
  
  private void updateStateFromTypedArray(TypedArray paramTypedArray, XmlPullParser paramXmlPullParser)
  {
    mThemeAttrs = null;
    mRotate = TypedArrayUtils.getNamedFloat(paramTypedArray, paramXmlPullParser, "rotation", 5, mRotate);
    mPivotX = paramTypedArray.getFloat(1, mPivotX);
    mPivotY = paramTypedArray.getFloat(2, mPivotY);
    mScaleX = TypedArrayUtils.getNamedFloat(paramTypedArray, paramXmlPullParser, "scaleX", 3, mScaleX);
    mScaleY = TypedArrayUtils.getNamedFloat(paramTypedArray, paramXmlPullParser, "scaleY", 4, mScaleY);
    mTranslateX = TypedArrayUtils.getNamedFloat(paramTypedArray, paramXmlPullParser, "translateX", 6, mTranslateX);
    mTranslateY = TypedArrayUtils.getNamedFloat(paramTypedArray, paramXmlPullParser, "translateY", 7, mTranslateY);
    paramTypedArray = paramTypedArray.getString(0);
    if (paramTypedArray != null) {
      mGroupName = paramTypedArray;
    }
    updateLocalMatrix();
  }
  
  public String getGroupName()
  {
    return mGroupName;
  }
  
  public Matrix getLocalMatrix()
  {
    return mLocalMatrix;
  }
  
  public float getPivotX()
  {
    return mPivotX;
  }
  
  public float getPivotY()
  {
    return mPivotY;
  }
  
  public float getRotation()
  {
    return mRotate;
  }
  
  public float getScaleX()
  {
    return mScaleX;
  }
  
  public float getScaleY()
  {
    return mScaleY;
  }
  
  public float getTranslateX()
  {
    return mTranslateX;
  }
  
  public float getTranslateY()
  {
    return mTranslateY;
  }
  
  public void inflate(Resources paramResources, AttributeSet paramAttributeSet, Resources.Theme paramTheme, XmlPullParser paramXmlPullParser)
  {
    paramResources = VectorDrawableCommon.obtainAttributes(paramResources, paramTheme, paramAttributeSet, AndroidResources.styleable_VectorDrawableGroup);
    updateStateFromTypedArray(paramResources, paramXmlPullParser);
    paramResources.recycle();
  }
  
  public void setPivotX(float paramFloat)
  {
    if (paramFloat != mPivotX)
    {
      mPivotX = paramFloat;
      updateLocalMatrix();
    }
  }
  
  public void setPivotY(float paramFloat)
  {
    if (paramFloat != mPivotY)
    {
      mPivotY = paramFloat;
      updateLocalMatrix();
    }
  }
  
  public void setRotation(float paramFloat)
  {
    if (paramFloat != mRotate)
    {
      mRotate = paramFloat;
      updateLocalMatrix();
    }
  }
  
  public void setScaleX(float paramFloat)
  {
    if (paramFloat != mScaleX)
    {
      mScaleX = paramFloat;
      updateLocalMatrix();
    }
  }
  
  public void setScaleY(float paramFloat)
  {
    if (paramFloat != mScaleY)
    {
      mScaleY = paramFloat;
      updateLocalMatrix();
    }
  }
  
  public void setTranslateX(float paramFloat)
  {
    if (paramFloat != mTranslateX)
    {
      mTranslateX = paramFloat;
      updateLocalMatrix();
    }
  }
  
  public void setTranslateY(float paramFloat)
  {
    if (paramFloat != mTranslateY)
    {
      mTranslateY = paramFloat;
      updateLocalMatrix();
    }
  }
}

/* Location:
 * Qualified Name:     android.support.graphics.drawable.VectorDrawableCompat.VGroup
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */