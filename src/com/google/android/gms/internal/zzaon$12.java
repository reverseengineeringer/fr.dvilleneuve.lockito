package com.google.android.gms.internal;

import java.io.IOException;
import java.util.BitSet;

final class zzaon$12
  extends zzank<BitSet>
{
  public void zza(zzaor paramzzaor, BitSet paramBitSet)
    throws IOException
  {
    if (paramBitSet == null)
    {
      paramzzaor.r();
      return;
    }
    paramzzaor.n();
    int i = 0;
    if (i < paramBitSet.length())
    {
      if (paramBitSet.get(i)) {}
      for (int j = 1;; j = 0)
      {
        paramzzaor.zzcp(j);
        i += 1;
        break;
      }
    }
    paramzzaor.o();
  }
  
  public BitSet zzx(zzaop paramzzaop)
    throws IOException
  {
    if (paramzzaop.h() == zzaoq.bhH)
    {
      paramzzaop.nextNull();
      return null;
    }
    BitSet localBitSet = new BitSet();
    paramzzaop.beginArray();
    Object localObject = paramzzaop.h();
    int i = 0;
    if (localObject != zzaoq.bhA)
    {
      boolean bool;
      switch (zzaon.26.bfU[localObject.ordinal()])
      {
      default: 
        paramzzaop = String.valueOf(localObject);
        throw new zzanh(String.valueOf(paramzzaop).length() + 27 + "Invalid bitset value type: " + paramzzaop);
      case 1: 
        if (paramzzaop.nextInt() != 0) {
          bool = true;
        }
        break;
      }
      for (;;)
      {
        if (bool) {
          localBitSet.set(i);
        }
        i += 1;
        localObject = paramzzaop.h();
        break;
        bool = false;
        continue;
        bool = paramzzaop.nextBoolean();
        continue;
        localObject = paramzzaop.nextString();
        try
        {
          int j = Integer.parseInt((String)localObject);
          if (j != 0) {
            bool = true;
          } else {
            bool = false;
          }
        }
        catch (NumberFormatException paramzzaop)
        {
          paramzzaop = String.valueOf(localObject);
          if (paramzzaop.length() == 0) {}
        }
      }
      for (paramzzaop = "Error: Expecting: bitset number value (1, 0), Found: ".concat(paramzzaop);; paramzzaop = new String("Error: Expecting: bitset number value (1, 0), Found: ")) {
        throw new zzanh(paramzzaop);
      }
    }
    paramzzaop.endArray();
    return localBitSet;
  }
}

/* Location:
 * Qualified Name:     com.google.android.gms.internal.zzaon.12
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */