package com.google.android.gms.internal;

final class zzaon$24
  implements zzanl
{
  zzaon$24(Class paramClass1, Class paramClass2, zzank paramzzank) {}
  
  public String toString()
  {
    String str1 = String.valueOf(bhf.getName());
    String str2 = String.valueOf(bhg.getName());
    String str3 = String.valueOf(bhb);
    return String.valueOf(str1).length() + 24 + String.valueOf(str2).length() + String.valueOf(str3).length() + "Factory[type=" + str1 + "+" + str2 + ",adapter=" + str3 + "]";
  }
  
  public <T> zzank<T> zza(zzams paramzzams, zzaoo<T> paramzzaoo)
  {
    paramzzams = paramzzaoo.s();
    if ((paramzzams == bhf) || (paramzzams == bhg)) {
      return bhb;
    }
    return null;
  }
}

/* Location:
 * Qualified Name:     com.google.android.gms.internal.zzaon.24
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */