package android.support.graphics.drawable;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.graphics.Region.Op;
import android.support.v4.util.ArrayMap;
import java.util.ArrayList;

class VectorDrawableCompat$VPathRenderer
{
  private static final Matrix IDENTITY_MATRIX = new Matrix();
  float mBaseHeight = 0.0F;
  float mBaseWidth = 0.0F;
  private int mChangingConfigurations;
  private Paint mFillPaint;
  private final Matrix mFinalPathMatrix = new Matrix();
  private final Path mPath;
  private PathMeasure mPathMeasure;
  private final Path mRenderPath;
  int mRootAlpha = 255;
  private final VectorDrawableCompat.VGroup mRootGroup;
  String mRootName = null;
  private Paint mStrokePaint;
  final ArrayMap<String, Object> mVGTargetsMap = new ArrayMap();
  float mViewportHeight = 0.0F;
  float mViewportWidth = 0.0F;
  
  public VectorDrawableCompat$VPathRenderer()
  {
    mRootGroup = new VectorDrawableCompat.VGroup();
    mPath = new Path();
    mRenderPath = new Path();
  }
  
  public VectorDrawableCompat$VPathRenderer(VPathRenderer paramVPathRenderer)
  {
    mRootGroup = new VectorDrawableCompat.VGroup(mRootGroup, mVGTargetsMap);
    mPath = new Path(mPath);
    mRenderPath = new Path(mRenderPath);
    mBaseWidth = mBaseWidth;
    mBaseHeight = mBaseHeight;
    mViewportWidth = mViewportWidth;
    mViewportHeight = mViewportHeight;
    mChangingConfigurations = mChangingConfigurations;
    mRootAlpha = mRootAlpha;
    mRootName = mRootName;
    if (mRootName != null) {
      mVGTargetsMap.put(mRootName, this);
    }
  }
  
  private static float cross(float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4)
  {
    return paramFloat1 * paramFloat4 - paramFloat2 * paramFloat3;
  }
  
  private void drawGroupTree(VectorDrawableCompat.VGroup paramVGroup, Matrix paramMatrix, Canvas paramCanvas, int paramInt1, int paramInt2, ColorFilter paramColorFilter)
  {
    VectorDrawableCompat.VGroup.access$700(paramVGroup).set(paramMatrix);
    VectorDrawableCompat.VGroup.access$700(paramVGroup).preConcat(VectorDrawableCompat.VGroup.access$800(paramVGroup));
    int i = 0;
    if (i < mChildren.size())
    {
      paramMatrix = mChildren.get(i);
      if ((paramMatrix instanceof VectorDrawableCompat.VGroup)) {
        drawGroupTree((VectorDrawableCompat.VGroup)paramMatrix, VectorDrawableCompat.VGroup.access$700(paramVGroup), paramCanvas, paramInt1, paramInt2, paramColorFilter);
      }
      for (;;)
      {
        i += 1;
        break;
        if ((paramMatrix instanceof VectorDrawableCompat.VPath)) {
          drawPath(paramVGroup, (VectorDrawableCompat.VPath)paramMatrix, paramCanvas, paramInt1, paramInt2, paramColorFilter);
        }
      }
    }
  }
  
  private void drawPath(VectorDrawableCompat.VGroup paramVGroup, VectorDrawableCompat.VPath paramVPath, Canvas paramCanvas, int paramInt1, int paramInt2, ColorFilter paramColorFilter)
  {
    float f2 = paramInt1 / mViewportWidth;
    float f3 = paramInt2 / mViewportHeight;
    float f1 = Math.min(f2, f3);
    paramVGroup = VectorDrawableCompat.VGroup.access$700(paramVGroup);
    mFinalPathMatrix.set(paramVGroup);
    mFinalPathMatrix.postScale(f2, f3);
    f2 = getMatrixScale(paramVGroup);
    if (f2 == 0.0F) {
      return;
    }
    paramVPath.toPath(mPath);
    Path localPath = mPath;
    mRenderPath.reset();
    if (paramVPath.isClipPath())
    {
      mRenderPath.addPath(localPath, mFinalPathMatrix);
      paramCanvas.clipPath(mRenderPath, Region.Op.REPLACE);
      return;
    }
    paramVGroup = (VectorDrawableCompat.VFullPath)paramVPath;
    float f6;
    float f4;
    if ((mTrimPathStart != 0.0F) || (mTrimPathEnd != 1.0F))
    {
      f6 = mTrimPathStart;
      float f7 = mTrimPathOffset;
      f4 = mTrimPathEnd;
      float f5 = mTrimPathOffset;
      if (mPathMeasure == null) {
        mPathMeasure = new PathMeasure();
      }
      mPathMeasure.setPath(mPath, false);
      f3 = mPathMeasure.getLength();
      f6 = (f6 + f7) % 1.0F * f3;
      f4 = (f4 + f5) % 1.0F * f3;
      localPath.reset();
      if (f6 <= f4) {
        break label506;
      }
      mPathMeasure.getSegment(f6, f3, localPath, true);
      mPathMeasure.getSegment(0.0F, f4, localPath, true);
    }
    for (;;)
    {
      localPath.rLineTo(0.0F, 0.0F);
      mRenderPath.addPath(localPath, mFinalPathMatrix);
      if (mFillColor != 0)
      {
        if (mFillPaint == null)
        {
          mFillPaint = new Paint();
          mFillPaint.setStyle(Paint.Style.FILL);
          mFillPaint.setAntiAlias(true);
        }
        paramVPath = mFillPaint;
        paramVPath.setColor(VectorDrawableCompat.access$900(mFillColor, mFillAlpha));
        paramVPath.setColorFilter(paramColorFilter);
        paramCanvas.drawPath(mRenderPath, paramVPath);
      }
      if (mStrokeColor == 0) {
        break;
      }
      if (mStrokePaint == null)
      {
        mStrokePaint = new Paint();
        mStrokePaint.setStyle(Paint.Style.STROKE);
        mStrokePaint.setAntiAlias(true);
      }
      paramVPath = mStrokePaint;
      if (mStrokeLineJoin != null) {
        paramVPath.setStrokeJoin(mStrokeLineJoin);
      }
      if (mStrokeLineCap != null) {
        paramVPath.setStrokeCap(mStrokeLineCap);
      }
      paramVPath.setStrokeMiter(mStrokeMiterlimit);
      paramVPath.setColor(VectorDrawableCompat.access$900(mStrokeColor, mStrokeAlpha));
      paramVPath.setColorFilter(paramColorFilter);
      paramVPath.setStrokeWidth(mStrokeWidth * (f1 * f2));
      paramCanvas.drawPath(mRenderPath, paramVPath);
      return;
      label506:
      mPathMeasure.getSegment(f6, f4, localPath, true);
    }
  }
  
  private float getMatrixScale(Matrix paramMatrix)
  {
    float[] arrayOfFloat = new float[4];
    float[] tmp7_5 = arrayOfFloat;
    tmp7_5[0] = 0.0F;
    float[] tmp11_7 = tmp7_5;
    tmp11_7[1] = 1.0F;
    float[] tmp15_11 = tmp11_7;
    tmp15_11[2] = 1.0F;
    float[] tmp19_15 = tmp15_11;
    tmp19_15[3] = 0.0F;
    tmp19_15;
    paramMatrix.mapVectors(arrayOfFloat);
    float f1 = (float)Math.hypot(arrayOfFloat[0], arrayOfFloat[1]);
    float f3 = (float)Math.hypot(arrayOfFloat[2], arrayOfFloat[3]);
    float f2 = cross(arrayOfFloat[0], arrayOfFloat[1], arrayOfFloat[2], arrayOfFloat[3]);
    f3 = Math.max(f1, f3);
    f1 = 0.0F;
    if (f3 > 0.0F) {
      f1 = Math.abs(f2) / f3;
    }
    return f1;
  }
  
  public void draw(Canvas paramCanvas, int paramInt1, int paramInt2, ColorFilter paramColorFilter)
  {
    drawGroupTree(mRootGroup, IDENTITY_MATRIX, paramCanvas, paramInt1, paramInt2, paramColorFilter);
  }
  
  public float getAlpha()
  {
    return getRootAlpha() / 255.0F;
  }
  
  public int getRootAlpha()
  {
    return mRootAlpha;
  }
  
  public void setAlpha(float paramFloat)
  {
    setRootAlpha((int)(255.0F * paramFloat));
  }
  
  public void setRootAlpha(int paramInt)
  {
    mRootAlpha = paramInt;
  }
}

/* Location:
 * Qualified Name:     android.support.graphics.drawable.VectorDrawableCompat.VPathRenderer
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */