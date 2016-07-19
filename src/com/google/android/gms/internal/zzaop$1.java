package com.google.android.gms.internal;

import java.io.IOException;

final class zzaop$1
  extends zzanu
{
  public void zzi(zzaop paramzzaop)
    throws IOException
  {
    if ((paramzzaop instanceof zzaof))
    {
      ((zzaof)paramzzaop).k();
      return;
    }
    int j = zzaop.zzag(paramzzaop);
    int i = j;
    if (j == 0) {
      i = zzaop.zzah(paramzzaop);
    }
    if (i == 13)
    {
      zzaop.zza(paramzzaop, 9);
      return;
    }
    if (i == 12)
    {
      zzaop.zza(paramzzaop, 8);
      return;
    }
    if (i == 14)
    {
      zzaop.zza(paramzzaop, 10);
      return;
    }
    String str = String.valueOf(paramzzaop.h());
    i = zzaop.zzai(paramzzaop);
    j = zzaop.zzaj(paramzzaop);
    paramzzaop = paramzzaop.getPath();
    throw new IllegalStateException(String.valueOf(str).length() + 70 + String.valueOf(paramzzaop).length() + "Expected a name but was " + str + " " + " at line " + i + " column " + j + " path " + paramzzaop);
  }
}

/* Location:
 * Qualified Name:     com.google.android.gms.internal.zzaop.1
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */