package com.google.android.gms.ads.internal.client;

import com.google.android.gms.internal.zzir;
import java.util.Random;

@zzir
public class zzn
  extends zzy.zza
{
  private Object zzail = new Object();
  private final Random zzavn = new Random();
  private long zzavo;
  
  public zzn()
  {
    zziy();
  }
  
  public long getValue()
  {
    return zzavo;
  }
  
  public void zziy()
  {
    Object localObject1 = zzail;
    int i = 3;
    long l1 = 0L;
    for (;;)
    {
      int j = i - 1;
      if (j > 0) {}
      try
      {
        long l2 = zzavn.nextInt() + 2147483648L;
        l1 = l2;
        i = j;
        if (l2 == zzavo) {
          continue;
        }
        l1 = l2;
        i = j;
        if (l2 == 0L) {
          continue;
        }
        l1 = l2;
        zzavo = l1;
        return;
      }
      finally {}
    }
  }
}

/* Location:
 * Qualified Name:     com.google.android.gms.ads.internal.client.zzn
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */