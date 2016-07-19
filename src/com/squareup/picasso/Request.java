package com.squareup.picasso;

import android.net.Uri;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class Request
{
  public final boolean centerCrop;
  public final boolean centerInside;
  public final boolean hasRotationPivot;
  public final int resourceId;
  public final float rotationDegrees;
  public final float rotationPivotX;
  public final float rotationPivotY;
  public final int targetHeight;
  public final int targetWidth;
  public final List<Transformation> transformations;
  public final Uri uri;
  
  private Request(Uri paramUri, int paramInt1, List<Transformation> paramList, int paramInt2, int paramInt3, boolean paramBoolean1, boolean paramBoolean2, float paramFloat1, float paramFloat2, float paramFloat3, boolean paramBoolean3)
  {
    uri = paramUri;
    resourceId = paramInt1;
    if (paramList == null) {}
    for (transformations = null;; transformations = Collections.unmodifiableList(paramList))
    {
      targetWidth = paramInt2;
      targetHeight = paramInt3;
      centerCrop = paramBoolean1;
      centerInside = paramBoolean2;
      rotationDegrees = paramFloat1;
      rotationPivotX = paramFloat2;
      rotationPivotY = paramFloat3;
      hasRotationPivot = paramBoolean3;
      return;
    }
  }
  
  public Builder buildUpon()
  {
    return new Builder(this, null);
  }
  
  String getName()
  {
    if (uri != null) {
      return uri.getPath();
    }
    return Integer.toHexString(resourceId);
  }
  
  boolean hasCustomTransformations()
  {
    return transformations != null;
  }
  
  public boolean hasSize()
  {
    return targetWidth != 0;
  }
  
  boolean needsMatrixTransform()
  {
    return (targetWidth != 0) || (rotationDegrees != 0.0F);
  }
  
  boolean needsTransformation()
  {
    return (needsMatrixTransform()) || (hasCustomTransformations());
  }
  
  public static final class Builder
  {
    private boolean centerCrop;
    private boolean centerInside;
    private boolean hasRotationPivot;
    private int resourceId;
    private float rotationDegrees;
    private float rotationPivotX;
    private float rotationPivotY;
    private int targetHeight;
    private int targetWidth;
    private List<Transformation> transformations;
    private Uri uri;
    
    public Builder(int paramInt)
    {
      setResourceId(paramInt);
    }
    
    public Builder(Uri paramUri)
    {
      setUri(paramUri);
    }
    
    Builder(Uri paramUri, int paramInt)
    {
      uri = paramUri;
      resourceId = paramInt;
    }
    
    private Builder(Request paramRequest)
    {
      uri = uri;
      resourceId = resourceId;
      targetWidth = targetWidth;
      targetHeight = targetHeight;
      centerCrop = centerCrop;
      centerInside = centerInside;
      rotationDegrees = rotationDegrees;
      rotationPivotX = rotationPivotX;
      rotationPivotY = rotationPivotY;
      hasRotationPivot = hasRotationPivot;
      if (transformations != null) {
        transformations = new ArrayList(transformations);
      }
    }
    
    public Request build()
    {
      if ((centerInside) && (centerCrop)) {
        throw new IllegalStateException("Center crop and center inside can not be used together.");
      }
      if ((centerCrop) && (targetWidth == 0)) {
        throw new IllegalStateException("Center crop requires calling resize.");
      }
      if ((centerInside) && (targetWidth == 0)) {
        throw new IllegalStateException("Center inside requires calling resize.");
      }
      return new Request(uri, resourceId, transformations, targetWidth, targetHeight, centerCrop, centerInside, rotationDegrees, rotationPivotX, rotationPivotY, hasRotationPivot, null);
    }
    
    public Builder centerCrop()
    {
      if (centerInside) {
        throw new IllegalStateException("Center crop can not be used after calling centerInside");
      }
      centerCrop = true;
      return this;
    }
    
    public Builder centerInside()
    {
      if (centerCrop) {
        throw new IllegalStateException("Center inside can not be used after calling centerCrop");
      }
      centerInside = true;
      return this;
    }
    
    public Builder clearCenterCrop()
    {
      centerCrop = false;
      return this;
    }
    
    public Builder clearCenterInside()
    {
      centerInside = false;
      return this;
    }
    
    public Builder clearResize()
    {
      targetWidth = 0;
      targetHeight = 0;
      centerCrop = false;
      centerInside = false;
      return this;
    }
    
    public Builder clearRotation()
    {
      rotationDegrees = 0.0F;
      rotationPivotX = 0.0F;
      rotationPivotY = 0.0F;
      hasRotationPivot = false;
      return this;
    }
    
    boolean hasImage()
    {
      return (uri != null) || (resourceId != 0);
    }
    
    boolean hasSize()
    {
      return targetWidth != 0;
    }
    
    public Builder resize(int paramInt1, int paramInt2)
    {
      if (paramInt1 <= 0) {
        throw new IllegalArgumentException("Width must be positive number.");
      }
      if (paramInt2 <= 0) {
        throw new IllegalArgumentException("Height must be positive number.");
      }
      targetWidth = paramInt1;
      targetHeight = paramInt2;
      return this;
    }
    
    public Builder rotate(float paramFloat)
    {
      rotationDegrees = paramFloat;
      return this;
    }
    
    public Builder rotate(float paramFloat1, float paramFloat2, float paramFloat3)
    {
      rotationDegrees = paramFloat1;
      rotationPivotX = paramFloat2;
      rotationPivotY = paramFloat3;
      hasRotationPivot = true;
      return this;
    }
    
    public Builder setResourceId(int paramInt)
    {
      if (paramInt == 0) {
        throw new IllegalArgumentException("Image resource ID may not be 0.");
      }
      resourceId = paramInt;
      uri = null;
      return this;
    }
    
    public Builder setUri(Uri paramUri)
    {
      if (paramUri == null) {
        throw new IllegalArgumentException("Image URI may not be null.");
      }
      uri = paramUri;
      resourceId = 0;
      return this;
    }
    
    public Builder transform(Transformation paramTransformation)
    {
      if (paramTransformation == null) {
        throw new IllegalArgumentException("Transformation must not be null.");
      }
      if (transformations == null) {
        transformations = new ArrayList(2);
      }
      transformations.add(paramTransformation);
      return this;
    }
  }
}

/* Location:
 * Qualified Name:     com.squareup.picasso.Request
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */