package com.google.android.gms.internal;

class zzanz$zza$zza
  implements CharSequence
{
  char[] bfz;
  
  public char charAt(int paramInt)
  {
    return bfz[paramInt];
  }
  
  public int length()
  {
    return bfz.length;
  }
  
  public CharSequence subSequence(int paramInt1, int paramInt2)
  {
    return new String(bfz, paramInt1, paramInt2 - paramInt1);
  }
}

/* Location:
 * Qualified Name:     com.google.android.gms.internal.zzanz.zza.zza
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */