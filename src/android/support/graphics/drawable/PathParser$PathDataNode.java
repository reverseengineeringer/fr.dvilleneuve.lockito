package android.support.graphics.drawable;

import android.graphics.Path;
import android.util.Log;

public class PathParser$PathDataNode
{
  float[] params;
  char type;
  
  private PathParser$PathDataNode(char paramChar, float[] paramArrayOfFloat)
  {
    type = paramChar;
    params = paramArrayOfFloat;
  }
  
  private PathParser$PathDataNode(PathDataNode paramPathDataNode)
  {
    type = type;
    params = PathParser.access$300(params, 0, params.length);
  }
  
  private static void addCommand(Path paramPath, float[] paramArrayOfFloat1, char paramChar1, char paramChar2, float[] paramArrayOfFloat2)
  {
    char c1 = '\002';
    float f1 = paramArrayOfFloat1[0];
    float f2 = paramArrayOfFloat1[1];
    float f4 = paramArrayOfFloat1[2];
    float f6 = paramArrayOfFloat1[3];
    float f3 = paramArrayOfFloat1[4];
    float f5 = paramArrayOfFloat1[5];
    int i;
    float f7;
    float f8;
    float f9;
    switch (paramChar2)
    {
    default: 
      char c2 = '\000';
      i = paramChar1;
      paramChar1 = c2;
      f7 = f5;
      f5 = f3;
      f8 = f6;
      f9 = f4;
      if (paramChar1 < paramArrayOfFloat2.length) {
        switch (paramChar2)
        {
        default: 
          f6 = f7;
          f4 = f8;
          f3 = f9;
        }
      }
      break;
    case 'Z': 
    case 'z': 
    case 'L': 
    case 'M': 
    case 'T': 
    case 'l': 
    case 'm': 
    case 't': 
    case 'H': 
    case 'V': 
    case 'h': 
    case 'v': 
    case 'C': 
    case 'c': 
    case 'Q': 
    case 'S': 
    case 'q': 
    case 's': 
    case 'A': 
    case 'a': 
      for (;;)
      {
        label229:
        i = paramChar2;
        paramChar1 += c1;
        f9 = f3;
        f8 = f4;
        f7 = f6;
        break label229;
        paramPath.close();
        f1 = f3;
        f2 = f5;
        f4 = f3;
        f6 = f5;
        paramPath.moveTo(f1, f2);
        break;
        c1 = '\002';
        break;
        c1 = '\001';
        break;
        c1 = '\006';
        break;
        c1 = '\004';
        break;
        c1 = '\007';
        break;
        f1 += paramArrayOfFloat2[(paramChar1 + '\000')];
        f2 += paramArrayOfFloat2[(paramChar1 + '\001')];
        if (paramChar1 > 0)
        {
          paramPath.rLineTo(paramArrayOfFloat2[(paramChar1 + '\000')], paramArrayOfFloat2[(paramChar1 + '\001')]);
          f3 = f9;
          f4 = f8;
          f6 = f7;
        }
        else
        {
          paramPath.rMoveTo(paramArrayOfFloat2[(paramChar1 + '\000')], paramArrayOfFloat2[(paramChar1 + '\001')]);
          f5 = f1;
          f6 = f2;
          f3 = f9;
          f4 = f8;
          continue;
          f1 = paramArrayOfFloat2[(paramChar1 + '\000')];
          f2 = paramArrayOfFloat2[(paramChar1 + '\001')];
          if (paramChar1 > 0)
          {
            paramPath.lineTo(paramArrayOfFloat2[(paramChar1 + '\000')], paramArrayOfFloat2[(paramChar1 + '\001')]);
            f3 = f9;
            f4 = f8;
            f6 = f7;
          }
          else
          {
            paramPath.moveTo(paramArrayOfFloat2[(paramChar1 + '\000')], paramArrayOfFloat2[(paramChar1 + '\001')]);
            f5 = f1;
            f6 = f2;
            f3 = f9;
            f4 = f8;
            continue;
            paramPath.rLineTo(paramArrayOfFloat2[(paramChar1 + '\000')], paramArrayOfFloat2[(paramChar1 + '\001')]);
            f1 += paramArrayOfFloat2[(paramChar1 + '\000')];
            f2 += paramArrayOfFloat2[(paramChar1 + '\001')];
            f3 = f9;
            f4 = f8;
            f6 = f7;
            continue;
            paramPath.lineTo(paramArrayOfFloat2[(paramChar1 + '\000')], paramArrayOfFloat2[(paramChar1 + '\001')]);
            f1 = paramArrayOfFloat2[(paramChar1 + '\000')];
            f2 = paramArrayOfFloat2[(paramChar1 + '\001')];
            f3 = f9;
            f4 = f8;
            f6 = f7;
            continue;
            paramPath.rLineTo(paramArrayOfFloat2[(paramChar1 + '\000')], 0.0F);
            f1 += paramArrayOfFloat2[(paramChar1 + '\000')];
            f3 = f9;
            f4 = f8;
            f6 = f7;
            continue;
            paramPath.lineTo(paramArrayOfFloat2[(paramChar1 + '\000')], f2);
            f1 = paramArrayOfFloat2[(paramChar1 + '\000')];
            f3 = f9;
            f4 = f8;
            f6 = f7;
            continue;
            paramPath.rLineTo(0.0F, paramArrayOfFloat2[(paramChar1 + '\000')]);
            f2 += paramArrayOfFloat2[(paramChar1 + '\000')];
            f3 = f9;
            f4 = f8;
            f6 = f7;
            continue;
            paramPath.lineTo(f1, paramArrayOfFloat2[(paramChar1 + '\000')]);
            f2 = paramArrayOfFloat2[(paramChar1 + '\000')];
            f3 = f9;
            f4 = f8;
            f6 = f7;
            continue;
            paramPath.rCubicTo(paramArrayOfFloat2[(paramChar1 + '\000')], paramArrayOfFloat2[(paramChar1 + '\001')], paramArrayOfFloat2[(paramChar1 + '\002')], paramArrayOfFloat2[(paramChar1 + '\003')], paramArrayOfFloat2[(paramChar1 + '\004')], paramArrayOfFloat2[(paramChar1 + '\005')]);
            f3 = f1 + paramArrayOfFloat2[(paramChar1 + '\002')];
            f4 = f2 + paramArrayOfFloat2[(paramChar1 + '\003')];
            f1 += paramArrayOfFloat2[(paramChar1 + '\004')];
            f2 += paramArrayOfFloat2[(paramChar1 + '\005')];
            f6 = f7;
            continue;
            paramPath.cubicTo(paramArrayOfFloat2[(paramChar1 + '\000')], paramArrayOfFloat2[(paramChar1 + '\001')], paramArrayOfFloat2[(paramChar1 + '\002')], paramArrayOfFloat2[(paramChar1 + '\003')], paramArrayOfFloat2[(paramChar1 + '\004')], paramArrayOfFloat2[(paramChar1 + '\005')]);
            f1 = paramArrayOfFloat2[(paramChar1 + '\004')];
            f2 = paramArrayOfFloat2[(paramChar1 + '\005')];
            f3 = paramArrayOfFloat2[(paramChar1 + '\002')];
            f4 = paramArrayOfFloat2[(paramChar1 + '\003')];
            f6 = f7;
            continue;
            f3 = 0.0F;
            f4 = 0.0F;
            if ((i == 99) || (i == 115) || (i == 67) || (i == 83))
            {
              f3 = f1 - f9;
              f4 = f2 - f8;
            }
            paramPath.rCubicTo(f3, f4, paramArrayOfFloat2[(paramChar1 + '\000')], paramArrayOfFloat2[(paramChar1 + '\001')], paramArrayOfFloat2[(paramChar1 + '\002')], paramArrayOfFloat2[(paramChar1 + '\003')]);
            f3 = f1 + paramArrayOfFloat2[(paramChar1 + '\000')];
            f4 = f2 + paramArrayOfFloat2[(paramChar1 + '\001')];
            f1 += paramArrayOfFloat2[(paramChar1 + '\002')];
            f2 += paramArrayOfFloat2[(paramChar1 + '\003')];
            f6 = f7;
            continue;
            f4 = f1;
            f3 = f2;
            if ((i == 99) || (i == 115) || (i == 67) || (i == 83))
            {
              f4 = 2.0F * f1 - f9;
              f3 = 2.0F * f2 - f8;
            }
            paramPath.cubicTo(f4, f3, paramArrayOfFloat2[(paramChar1 + '\000')], paramArrayOfFloat2[(paramChar1 + '\001')], paramArrayOfFloat2[(paramChar1 + '\002')], paramArrayOfFloat2[(paramChar1 + '\003')]);
            f3 = paramArrayOfFloat2[(paramChar1 + '\000')];
            f4 = paramArrayOfFloat2[(paramChar1 + '\001')];
            f1 = paramArrayOfFloat2[(paramChar1 + '\002')];
            f2 = paramArrayOfFloat2[(paramChar1 + '\003')];
            f6 = f7;
            continue;
            paramPath.rQuadTo(paramArrayOfFloat2[(paramChar1 + '\000')], paramArrayOfFloat2[(paramChar1 + '\001')], paramArrayOfFloat2[(paramChar1 + '\002')], paramArrayOfFloat2[(paramChar1 + '\003')]);
            f3 = f1 + paramArrayOfFloat2[(paramChar1 + '\000')];
            f4 = f2 + paramArrayOfFloat2[(paramChar1 + '\001')];
            f1 += paramArrayOfFloat2[(paramChar1 + '\002')];
            f2 += paramArrayOfFloat2[(paramChar1 + '\003')];
            f6 = f7;
            continue;
            paramPath.quadTo(paramArrayOfFloat2[(paramChar1 + '\000')], paramArrayOfFloat2[(paramChar1 + '\001')], paramArrayOfFloat2[(paramChar1 + '\002')], paramArrayOfFloat2[(paramChar1 + '\003')]);
            f3 = paramArrayOfFloat2[(paramChar1 + '\000')];
            f4 = paramArrayOfFloat2[(paramChar1 + '\001')];
            f1 = paramArrayOfFloat2[(paramChar1 + '\002')];
            f2 = paramArrayOfFloat2[(paramChar1 + '\003')];
            f6 = f7;
            continue;
            f4 = 0.0F;
            f3 = 0.0F;
            if ((i == 113) || (i == 116) || (i == 81) || (i == 84))
            {
              f4 = f1 - f9;
              f3 = f2 - f8;
            }
            paramPath.rQuadTo(f4, f3, paramArrayOfFloat2[(paramChar1 + '\000')], paramArrayOfFloat2[(paramChar1 + '\001')]);
            f4 = f1 + f4;
            f6 = f2 + f3;
            f1 += paramArrayOfFloat2[(paramChar1 + '\000')];
            f2 += paramArrayOfFloat2[(paramChar1 + '\001')];
            f3 = f4;
            f4 = f6;
            f6 = f7;
            continue;
            f4 = f1;
            f3 = f2;
            if ((i == 113) || (i == 116) || (i == 81) || (i == 84))
            {
              f4 = 2.0F * f1 - f9;
              f3 = 2.0F * f2 - f8;
            }
            paramPath.quadTo(f4, f3, paramArrayOfFloat2[(paramChar1 + '\000')], paramArrayOfFloat2[(paramChar1 + '\001')]);
            f1 = f3;
            f8 = paramArrayOfFloat2[(paramChar1 + '\000')];
            f2 = paramArrayOfFloat2[(paramChar1 + '\001')];
            f3 = f4;
            f4 = f1;
            f6 = f7;
            f1 = f8;
          }
        }
      }
      f3 = paramArrayOfFloat2[(paramChar1 + '\005')];
      f4 = paramArrayOfFloat2[(paramChar1 + '\006')];
      f6 = paramArrayOfFloat2[(paramChar1 + '\000')];
      f8 = paramArrayOfFloat2[(paramChar1 + '\001')];
      f9 = paramArrayOfFloat2[(paramChar1 + '\002')];
      boolean bool1;
      if (paramArrayOfFloat2[(paramChar1 + '\003')] != 0.0F)
      {
        bool1 = true;
        label1771:
        if (paramArrayOfFloat2[(paramChar1 + '\004')] == 0.0F) {
          break label1857;
        }
      }
      label1857:
      for (boolean bool2 = true;; bool2 = false)
      {
        drawArc(paramPath, f1, f2, f3 + f1, f4 + f2, f6, f8, f9, bool1, bool2);
        f1 += paramArrayOfFloat2[(paramChar1 + '\005')];
        f2 += paramArrayOfFloat2[(paramChar1 + '\006')];
        f3 = f1;
        f4 = f2;
        f6 = f7;
        break;
        bool1 = false;
        break label1771;
      }
      f3 = paramArrayOfFloat2[(paramChar1 + '\005')];
      f4 = paramArrayOfFloat2[(paramChar1 + '\006')];
      f6 = paramArrayOfFloat2[(paramChar1 + '\000')];
      f8 = paramArrayOfFloat2[(paramChar1 + '\001')];
      f9 = paramArrayOfFloat2[(paramChar1 + '\002')];
      if (paramArrayOfFloat2[(paramChar1 + '\003')] != 0.0F)
      {
        bool1 = true;
        label1918:
        if (paramArrayOfFloat2[(paramChar1 + '\004')] == 0.0F) {
          break label1992;
        }
      }
      label1992:
      for (bool2 = true;; bool2 = false)
      {
        drawArc(paramPath, f1, f2, f3, f4, f6, f8, f9, bool1, bool2);
        f1 = paramArrayOfFloat2[(paramChar1 + '\005')];
        f2 = paramArrayOfFloat2[(paramChar1 + '\006')];
        f3 = f1;
        f4 = f2;
        f6 = f7;
        break;
        bool1 = false;
        break label1918;
      }
    }
    paramArrayOfFloat1[0] = f1;
    paramArrayOfFloat1[1] = f2;
    paramArrayOfFloat1[2] = f9;
    paramArrayOfFloat1[3] = f8;
    paramArrayOfFloat1[4] = f5;
    paramArrayOfFloat1[5] = f7;
  }
  
  private static void arcToBezier(Path paramPath, double paramDouble1, double paramDouble2, double paramDouble3, double paramDouble4, double paramDouble5, double paramDouble6, double paramDouble7, double paramDouble8, double paramDouble9)
  {
    int j = (int)Math.ceil(Math.abs(4.0D * paramDouble9 / 3.141592653589793D));
    double d1 = paramDouble8;
    double d6 = Math.cos(paramDouble7);
    double d7 = Math.sin(paramDouble7);
    paramDouble7 = Math.cos(d1);
    paramDouble8 = Math.sin(d1);
    double d2 = -paramDouble3 * d6 * paramDouble8 - paramDouble4 * d7 * paramDouble7;
    double d3 = -paramDouble3 * d7 * paramDouble8 + paramDouble4 * d6 * paramDouble7;
    double d8 = paramDouble9 / j;
    int i = 0;
    paramDouble8 = paramDouble6;
    paramDouble7 = paramDouble5;
    paramDouble9 = d1;
    paramDouble6 = d3;
    paramDouble5 = d2;
    while (i < j)
    {
      double d5 = paramDouble9 + d8;
      d3 = Math.sin(d5);
      double d9 = Math.cos(d5);
      double d4 = paramDouble3 * d6 * d9 + paramDouble1 - paramDouble4 * d7 * d3;
      d2 = paramDouble3 * d7 * d9 + paramDouble2 + paramDouble4 * d6 * d3;
      d1 = -paramDouble3 * d6 * d3 - paramDouble4 * d7 * d9;
      d3 = -paramDouble3 * d7 * d3 + paramDouble4 * d6 * d9;
      d9 = Math.tan((d5 - paramDouble9) / 2.0D);
      paramDouble9 = Math.sin(d5 - paramDouble9) * (Math.sqrt(4.0D + 3.0D * d9 * d9) - 1.0D) / 3.0D;
      paramPath.cubicTo((float)(paramDouble7 + paramDouble9 * paramDouble5), (float)(paramDouble8 + paramDouble9 * paramDouble6), (float)(d4 - paramDouble9 * d1), (float)(d2 - paramDouble9 * d3), (float)d4, (float)d2);
      paramDouble9 = d5;
      paramDouble7 = d4;
      paramDouble8 = d2;
      paramDouble5 = d1;
      paramDouble6 = d3;
      i += 1;
    }
  }
  
  private static void drawArc(Path paramPath, float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4, float paramFloat5, float paramFloat6, float paramFloat7, boolean paramBoolean1, boolean paramBoolean2)
  {
    double d5 = Math.toRadians(paramFloat7);
    double d6 = Math.cos(d5);
    double d7 = Math.sin(d5);
    double d8 = (paramFloat1 * d6 + paramFloat2 * d7) / paramFloat5;
    double d9 = (-paramFloat1 * d7 + paramFloat2 * d6) / paramFloat6;
    double d1 = (paramFloat3 * d6 + paramFloat4 * d7) / paramFloat5;
    double d4 = (-paramFloat3 * d7 + paramFloat4 * d6) / paramFloat6;
    double d11 = d8 - d1;
    double d10 = d9 - d4;
    double d3 = (d8 + d1) / 2.0D;
    double d2 = (d9 + d4) / 2.0D;
    double d12 = d11 * d11 + d10 * d10;
    if (d12 == 0.0D)
    {
      Log.w("PathParser", " Points are coincident");
      return;
    }
    double d13 = 1.0D / d12 - 0.25D;
    if (d13 < 0.0D)
    {
      Log.w("PathParser", "Points are too far apart " + d12);
      float f = (float)(Math.sqrt(d12) / 1.99999D);
      drawArc(paramPath, paramFloat1, paramFloat2, paramFloat3, paramFloat4, paramFloat5 * f, paramFloat6 * f, paramFloat7, paramBoolean1, paramBoolean2);
      return;
    }
    d12 = Math.sqrt(d13);
    d11 = d12 * d11;
    d10 = d12 * d10;
    if (paramBoolean1 == paramBoolean2)
    {
      d3 -= d10;
      d2 += d11;
      d8 = Math.atan2(d9 - d2, d8 - d3);
      d4 = Math.atan2(d4 - d2, d1 - d3) - d8;
      if (d4 < 0.0D) {
        break label427;
      }
      paramBoolean1 = true;
      label325:
      d1 = d4;
      if (paramBoolean2 != paramBoolean1) {
        if (d4 <= 0.0D) {
          break label433;
        }
      }
    }
    label427:
    label433:
    for (d1 = d4 - 6.283185307179586D;; d1 = d4 + 6.283185307179586D)
    {
      d3 *= paramFloat5;
      d2 *= paramFloat6;
      arcToBezier(paramPath, d3 * d6 - d2 * d7, d3 * d7 + d2 * d6, paramFloat5, paramFloat6, paramFloat1, paramFloat2, d5, d8, d1);
      return;
      d3 += d10;
      d2 -= d11;
      break;
      paramBoolean1 = false;
      break label325;
    }
  }
  
  public static void nodesToPath(PathDataNode[] paramArrayOfPathDataNode, Path paramPath)
  {
    float[] arrayOfFloat = new float[6];
    char c = 'm';
    int i = 0;
    while (i < paramArrayOfPathDataNode.length)
    {
      addCommand(paramPath, arrayOfFloat, c, type, params);
      c = type;
      i += 1;
    }
  }
  
  public void interpolatePathDataNode(PathDataNode paramPathDataNode1, PathDataNode paramPathDataNode2, float paramFloat)
  {
    int i = 0;
    while (i < params.length)
    {
      params[i] = (params[i] * (1.0F - paramFloat) + params[i] * paramFloat);
      i += 1;
    }
  }
}

/* Location:
 * Qualified Name:     android.support.graphics.drawable.PathParser.PathDataNode
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */