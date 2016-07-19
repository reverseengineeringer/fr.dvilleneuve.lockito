package android.support.graphics.drawable;

import android.content.res.Resources;
import android.content.res.Resources.Theme;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import org.xmlpull.v1.XmlPullParser;

class VectorDrawableCompat$VClipPath
  extends VectorDrawableCompat.VPath
{
  public VectorDrawableCompat$VClipPath() {}
  
  public VectorDrawableCompat$VClipPath(VClipPath paramVClipPath)
  {
    super(paramVClipPath);
  }
  
  private void updateStateFromTypedArray(TypedArray paramTypedArray)
  {
    String str = paramTypedArray.getString(0);
    if (str != null) {
      mPathName = str;
    }
    paramTypedArray = paramTypedArray.getString(1);
    if (paramTypedArray != null) {
      mNodes = PathParser.createNodesFromPathData(paramTypedArray);
    }
  }
  
  public void inflate(Resources paramResources, AttributeSet paramAttributeSet, Resources.Theme paramTheme, XmlPullParser paramXmlPullParser)
  {
    if (!TypedArrayUtils.hasAttribute(paramXmlPullParser, "pathData")) {
      return;
    }
    paramResources = VectorDrawableCommon.obtainAttributes(paramResources, paramTheme, paramAttributeSet, AndroidResources.styleable_VectorDrawableClipPath);
    updateStateFromTypedArray(paramResources);
    paramResources.recycle();
  }
  
  public boolean isClipPath()
  {
    return true;
  }
}

/* Location:
 * Qualified Name:     android.support.graphics.drawable.VectorDrawableCompat.VClipPath
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */