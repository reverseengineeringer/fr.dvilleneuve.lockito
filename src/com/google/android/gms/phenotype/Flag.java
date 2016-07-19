package com.google.android.gms.phenotype;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.zzaa;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.Comparator;

public class Flag
  extends AbstractSafeParcelable
  implements Comparable<Flag>
{
  public static final Parcelable.Creator<Flag> CREATOR = new zzb();
  private static final Charset UTF_8 = Charset.forName("UTF-8");
  public static final zza asg = new zza();
  final boolean abu;
  final double abw;
  final long asc;
  final byte[] asd;
  public final int ase;
  public final int asf;
  final int mVersionCode;
  public final String name;
  final String zr;
  
  Flag(int paramInt1, String paramString1, long paramLong, boolean paramBoolean, double paramDouble, String paramString2, byte[] paramArrayOfByte, int paramInt2, int paramInt3)
  {
    mVersionCode = paramInt1;
    name = paramString1;
    asc = paramLong;
    abu = paramBoolean;
    abw = paramDouble;
    zr = paramString2;
    asd = paramArrayOfByte;
    ase = paramInt2;
    asf = paramInt3;
  }
  
  private static int compare(byte paramByte1, byte paramByte2)
  {
    return paramByte1 - paramByte2;
  }
  
  private static int compare(int paramInt1, int paramInt2)
  {
    if (paramInt1 < paramInt2) {
      return -1;
    }
    if (paramInt1 == paramInt2) {
      return 0;
    }
    return 1;
  }
  
  private static int compare(long paramLong1, long paramLong2)
  {
    if (paramLong1 < paramLong2) {
      return -1;
    }
    if (paramLong1 == paramLong2) {
      return 0;
    }
    return 1;
  }
  
  private static int compare(String paramString1, String paramString2)
  {
    if (paramString1 == paramString2) {
      return 0;
    }
    if (paramString1 == null) {
      return -1;
    }
    if (paramString2 == null) {
      return 1;
    }
    return paramString1.compareTo(paramString2);
  }
  
  private static int compare(boolean paramBoolean1, boolean paramBoolean2)
  {
    if (paramBoolean1 == paramBoolean2) {
      return 0;
    }
    if (paramBoolean1) {
      return 1;
    }
    return -1;
  }
  
  public boolean equals(Object paramObject)
  {
    boolean bool = true;
    if ((paramObject != null) && ((paramObject instanceof Flag)))
    {
      paramObject = (Flag)paramObject;
      if ((mVersionCode != mVersionCode) || (!zzaa.equal(name, name)) || (ase != ase) || (asf != asf)) {
        bool = false;
      }
      do
      {
        do
        {
          do
          {
            return bool;
            switch (ase)
            {
            default: 
              int i = ase;
              throw new AssertionError(31 + "Invalid enum value: " + i);
            }
          } while (asc == asc);
          return false;
        } while (abu == abu);
        return false;
      } while (abw == abw);
      return false;
      return zzaa.equal(zr, zr);
      return Arrays.equals(asd, asd);
    }
    return false;
  }
  
  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder("Flag(");
    localStringBuilder.append(mVersionCode);
    localStringBuilder.append(", ");
    localStringBuilder.append(name);
    localStringBuilder.append(", ");
    switch (ase)
    {
    default: 
      int i = ase;
      throw new AssertionError(31 + "Invalid enum value: " + i);
    case 1: 
      localStringBuilder.append(asc);
    }
    for (;;)
    {
      localStringBuilder.append(", ");
      localStringBuilder.append(ase);
      localStringBuilder.append(", ");
      localStringBuilder.append(asf);
      localStringBuilder.append(")");
      return localStringBuilder.toString();
      localStringBuilder.append(abu);
      continue;
      localStringBuilder.append(abw);
      continue;
      localStringBuilder.append("'");
      localStringBuilder.append(zr);
      localStringBuilder.append("'");
      continue;
      if (asd == null)
      {
        localStringBuilder.append("null");
      }
      else
      {
        localStringBuilder.append("'");
        localStringBuilder.append(new String(asd, UTF_8));
        localStringBuilder.append("'");
      }
    }
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    zzb.zza(this, paramParcel, paramInt);
  }
  
  public int zza(Flag paramFlag)
  {
    int j = 0;
    int i = 0;
    int k = name.compareTo(name);
    if (k != 0) {
      i = k;
    }
    do
    {
      return i;
      k = compare(ase, ase);
      if (k != 0) {
        return k;
      }
      switch (ase)
      {
      default: 
        i = ase;
        throw new AssertionError(31 + "Invalid enum value: " + i);
      case 1: 
        return compare(asc, asc);
      case 2: 
        return compare(abu, abu);
      case 3: 
        return Double.compare(abw, abw);
      case 4: 
        return compare(zr, zr);
      }
    } while (asd == asd);
    if (asd == null) {
      return -1;
    }
    i = j;
    if (asd == null) {
      return 1;
    }
    do
    {
      i += 1;
      if (i >= Math.min(asd.length, asd.length)) {
        break;
      }
      j = compare(asd[i], asd[i]);
    } while (j == 0);
    return j;
    return compare(asd.length, asd.length);
  }
  
  public static class zza
    implements Comparator<Flag>
  {
    public int zza(Flag paramFlag1, Flag paramFlag2)
    {
      if (asf == asf) {
        return name.compareTo(name);
      }
      return asf - asf;
    }
  }
}

/* Location:
 * Qualified Name:     com.google.android.gms.phenotype.Flag
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */