package com.google.android.gms.common.images;

public final class Size
{
  private final int zzaie;
  private final int zzaif;
  
  public Size(int paramInt1, int paramInt2)
  {
    zzaie = paramInt1;
    zzaif = paramInt2;
  }
  
  public static Size parseSize(String paramString)
    throws NumberFormatException
  {
    if (paramString == null) {
      throw new IllegalArgumentException("string must not be null");
    }
    int j = paramString.indexOf('*');
    int i = j;
    if (j < 0) {
      i = paramString.indexOf('x');
    }
    if (i < 0) {
      throw zzhi(paramString);
    }
    try
    {
      Size localSize = new Size(Integer.parseInt(paramString.substring(0, i)), Integer.parseInt(paramString.substring(i + 1)));
      return localSize;
    }
    catch (NumberFormatException localNumberFormatException)
    {
      throw zzhi(paramString);
    }
  }
  
  private static NumberFormatException zzhi(String paramString)
  {
    throw new NumberFormatException(String.valueOf(paramString).length() + 16 + "Invalid Size: \"" + paramString + "\"");
  }
  
  public boolean equals(Object paramObject)
  {
    boolean bool = true;
    if (paramObject == null) {}
    do
    {
      return false;
      if (this == paramObject) {
        return true;
      }
    } while (!(paramObject instanceof Size));
    paramObject = (Size)paramObject;
    if ((zzaie == zzaie) && (zzaif == zzaif)) {}
    for (;;)
    {
      return bool;
      bool = false;
    }
  }
  
  public int getHeight()
  {
    return zzaif;
  }
  
  public int getWidth()
  {
    return zzaie;
  }
  
  public int hashCode()
  {
    return zzaif ^ (zzaie << 16 | zzaie >>> 16);
  }
  
  public String toString()
  {
    int i = zzaie;
    int j = zzaif;
    return 23 + i + "x" + j;
  }
}

/* Location:
 * Qualified Name:     com.google.android.gms.common.images.Size
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */