package com.google.android.gms.internal;

final class zzaon$25
  implements zzanl
{
  zzaon$25(Class paramClass, zzank paramzzank) {}
  
  public String toString()
  {
    String str1 = String.valueOf(bhh.getName());
    String str2 = String.valueOf(bhb);
    return String.valueOf(str1).length() + 32 + String.valueOf(str2).length() + "Factory[typeHierarchy=" + str1 + ",adapter=" + str2 + "]";
  }
  
  public <T> zzank<T> zza(zzams paramzzams, zzaoo<T> paramzzaoo)
  {
    if (bhh.isAssignableFrom(paramzzaoo.s())) {
      return bhb;
    }
    return null;
  }
}

/* Location:
 * Qualified Name:     com.google.android.gms.internal.zzaon.25
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */