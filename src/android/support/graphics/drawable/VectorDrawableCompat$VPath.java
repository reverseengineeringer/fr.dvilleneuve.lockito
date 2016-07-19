package android.support.graphics.drawable;

import android.content.res.Resources.Theme;
import android.graphics.Path;
import android.util.Log;

class VectorDrawableCompat$VPath
{
  int mChangingConfigurations;
  protected PathParser.PathDataNode[] mNodes = null;
  String mPathName;
  
  public VectorDrawableCompat$VPath() {}
  
  public VectorDrawableCompat$VPath(VPath paramVPath)
  {
    mPathName = mPathName;
    mChangingConfigurations = mChangingConfigurations;
    mNodes = PathParser.deepCopyNodes(mNodes);
  }
  
  public String NodesToString(PathParser.PathDataNode[] paramArrayOfPathDataNode)
  {
    String str = " ";
    int i = 0;
    while (i < paramArrayOfPathDataNode.length)
    {
      str = str + type + ":";
      float[] arrayOfFloat = params;
      int j = 0;
      while (j < arrayOfFloat.length)
      {
        str = str + arrayOfFloat[j] + ",";
        j += 1;
      }
      i += 1;
    }
    return str;
  }
  
  public void applyTheme(Resources.Theme paramTheme) {}
  
  public boolean canApplyTheme()
  {
    return false;
  }
  
  public PathParser.PathDataNode[] getPathData()
  {
    return mNodes;
  }
  
  public String getPathName()
  {
    return mPathName;
  }
  
  public boolean isClipPath()
  {
    return false;
  }
  
  public void printVPath(int paramInt)
  {
    String str = "";
    int i = 0;
    while (i < paramInt)
    {
      str = str + "    ";
      i += 1;
    }
    Log.v("VectorDrawableCompat", str + "current path is :" + mPathName + " pathData is " + NodesToString(mNodes));
  }
  
  public void setPathData(PathParser.PathDataNode[] paramArrayOfPathDataNode)
  {
    if (!PathParser.canMorph(mNodes, paramArrayOfPathDataNode))
    {
      mNodes = PathParser.deepCopyNodes(paramArrayOfPathDataNode);
      return;
    }
    PathParser.updateNodes(mNodes, paramArrayOfPathDataNode);
  }
  
  public void toPath(Path paramPath)
  {
    paramPath.reset();
    if (mNodes != null) {
      PathParser.PathDataNode.nodesToPath(mNodes, paramPath);
    }
  }
}

/* Location:
 * Qualified Name:     android.support.graphics.drawable.VectorDrawableCompat.VPath
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */