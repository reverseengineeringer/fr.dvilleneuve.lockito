package android.support.graphics.drawable;

import android.content.res.Resources;
import android.content.res.Resources.Theme;
import android.content.res.TypedArray;
import android.graphics.Paint.Cap;
import android.graphics.Paint.Join;
import android.util.AttributeSet;
import org.xmlpull.v1.XmlPullParser;

class VectorDrawableCompat$VFullPath
  extends VectorDrawableCompat.VPath
{
  float mFillAlpha = 1.0F;
  int mFillColor = 0;
  int mFillRule;
  float mStrokeAlpha = 1.0F;
  int mStrokeColor = 0;
  Paint.Cap mStrokeLineCap = Paint.Cap.BUTT;
  Paint.Join mStrokeLineJoin = Paint.Join.MITER;
  float mStrokeMiterlimit = 4.0F;
  float mStrokeWidth = 0.0F;
  private int[] mThemeAttrs;
  float mTrimPathEnd = 1.0F;
  float mTrimPathOffset = 0.0F;
  float mTrimPathStart = 0.0F;
  
  public VectorDrawableCompat$VFullPath() {}
  
  public VectorDrawableCompat$VFullPath(VFullPath paramVFullPath)
  {
    super(paramVFullPath);
    mThemeAttrs = mThemeAttrs;
    mStrokeColor = mStrokeColor;
    mStrokeWidth = mStrokeWidth;
    mStrokeAlpha = mStrokeAlpha;
    mFillColor = mFillColor;
    mFillRule = mFillRule;
    mFillAlpha = mFillAlpha;
    mTrimPathStart = mTrimPathStart;
    mTrimPathEnd = mTrimPathEnd;
    mTrimPathOffset = mTrimPathOffset;
    mStrokeLineCap = mStrokeLineCap;
    mStrokeLineJoin = mStrokeLineJoin;
    mStrokeMiterlimit = mStrokeMiterlimit;
  }
  
  private Paint.Cap getStrokeLineCap(int paramInt, Paint.Cap paramCap)
  {
    switch (paramInt)
    {
    default: 
      return paramCap;
    case 0: 
      return Paint.Cap.BUTT;
    case 1: 
      return Paint.Cap.ROUND;
    }
    return Paint.Cap.SQUARE;
  }
  
  private Paint.Join getStrokeLineJoin(int paramInt, Paint.Join paramJoin)
  {
    switch (paramInt)
    {
    default: 
      return paramJoin;
    case 0: 
      return Paint.Join.MITER;
    case 1: 
      return Paint.Join.ROUND;
    }
    return Paint.Join.BEVEL;
  }
  
  private void updateStateFromTypedArray(TypedArray paramTypedArray, XmlPullParser paramXmlPullParser)
  {
    mThemeAttrs = null;
    if (!TypedArrayUtils.hasAttribute(paramXmlPullParser, "pathData")) {
      return;
    }
    String str = paramTypedArray.getString(0);
    if (str != null) {
      mPathName = str;
    }
    str = paramTypedArray.getString(2);
    if (str != null) {
      mNodes = PathParser.createNodesFromPathData(str);
    }
    mFillColor = TypedArrayUtils.getNamedColor(paramTypedArray, paramXmlPullParser, "fillColor", 1, mFillColor);
    mFillAlpha = TypedArrayUtils.getNamedFloat(paramTypedArray, paramXmlPullParser, "fillAlpha", 12, mFillAlpha);
    mStrokeLineCap = getStrokeLineCap(TypedArrayUtils.getNamedInt(paramTypedArray, paramXmlPullParser, "strokeLineCap", 8, -1), mStrokeLineCap);
    mStrokeLineJoin = getStrokeLineJoin(TypedArrayUtils.getNamedInt(paramTypedArray, paramXmlPullParser, "strokeLineJoin", 9, -1), mStrokeLineJoin);
    mStrokeMiterlimit = TypedArrayUtils.getNamedFloat(paramTypedArray, paramXmlPullParser, "strokeMiterLimit", 10, mStrokeMiterlimit);
    mStrokeColor = TypedArrayUtils.getNamedColor(paramTypedArray, paramXmlPullParser, "strokeColor", 3, mStrokeColor);
    mStrokeAlpha = TypedArrayUtils.getNamedFloat(paramTypedArray, paramXmlPullParser, "strokeAlpha", 11, mStrokeAlpha);
    mStrokeWidth = TypedArrayUtils.getNamedFloat(paramTypedArray, paramXmlPullParser, "strokeWidth", 4, mStrokeWidth);
    mTrimPathEnd = TypedArrayUtils.getNamedFloat(paramTypedArray, paramXmlPullParser, "trimPathEnd", 6, mTrimPathEnd);
    mTrimPathOffset = TypedArrayUtils.getNamedFloat(paramTypedArray, paramXmlPullParser, "trimPathOffset", 7, mTrimPathOffset);
    mTrimPathStart = TypedArrayUtils.getNamedFloat(paramTypedArray, paramXmlPullParser, "trimPathStart", 5, mTrimPathStart);
  }
  
  public void applyTheme(Resources.Theme paramTheme)
  {
    if (mThemeAttrs == null) {}
  }
  
  public boolean canApplyTheme()
  {
    return mThemeAttrs != null;
  }
  
  float getFillAlpha()
  {
    return mFillAlpha;
  }
  
  int getFillColor()
  {
    return mFillColor;
  }
  
  float getStrokeAlpha()
  {
    return mStrokeAlpha;
  }
  
  int getStrokeColor()
  {
    return mStrokeColor;
  }
  
  float getStrokeWidth()
  {
    return mStrokeWidth;
  }
  
  float getTrimPathEnd()
  {
    return mTrimPathEnd;
  }
  
  float getTrimPathOffset()
  {
    return mTrimPathOffset;
  }
  
  float getTrimPathStart()
  {
    return mTrimPathStart;
  }
  
  public void inflate(Resources paramResources, AttributeSet paramAttributeSet, Resources.Theme paramTheme, XmlPullParser paramXmlPullParser)
  {
    paramResources = VectorDrawableCommon.obtainAttributes(paramResources, paramTheme, paramAttributeSet, AndroidResources.styleable_VectorDrawablePath);
    updateStateFromTypedArray(paramResources, paramXmlPullParser);
    paramResources.recycle();
  }
  
  void setFillAlpha(float paramFloat)
  {
    mFillAlpha = paramFloat;
  }
  
  void setFillColor(int paramInt)
  {
    mFillColor = paramInt;
  }
  
  void setStrokeAlpha(float paramFloat)
  {
    mStrokeAlpha = paramFloat;
  }
  
  void setStrokeColor(int paramInt)
  {
    mStrokeColor = paramInt;
  }
  
  void setStrokeWidth(float paramFloat)
  {
    mStrokeWidth = paramFloat;
  }
  
  void setTrimPathEnd(float paramFloat)
  {
    mTrimPathEnd = paramFloat;
  }
  
  void setTrimPathOffset(float paramFloat)
  {
    mTrimPathOffset = paramFloat;
  }
  
  void setTrimPathStart(float paramFloat)
  {
    mTrimPathStart = paramFloat;
  }
}

/* Location:
 * Qualified Name:     android.support.graphics.drawable.VectorDrawableCompat.VFullPath
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */