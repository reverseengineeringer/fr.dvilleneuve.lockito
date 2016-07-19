package com.google.android.gms.tagmanager;

class zzdk
  extends Number
  implements Comparable<zzdk>
{
  private double ayL;
  private long ayM;
  private boolean ayN;
  
  private zzdk(double paramDouble)
  {
    ayL = paramDouble;
    ayN = false;
  }
  
  private zzdk(long paramLong)
  {
    ayM = paramLong;
    ayN = true;
  }
  
  public static zzdk zza(Double paramDouble)
  {
    return new zzdk(paramDouble.doubleValue());
  }
  
  public static zzdk zzbs(long paramLong)
  {
    return new zzdk(paramLong);
  }
  
  public static zzdk zzor(String paramString)
    throws NumberFormatException
  {
    try
    {
      zzdk localzzdk1 = new zzdk(Long.parseLong(paramString));
      return localzzdk1;
    }
    catch (NumberFormatException localNumberFormatException1)
    {
      try
      {
        zzdk localzzdk2 = new zzdk(Double.parseDouble(paramString));
        return localzzdk2;
      }
      catch (NumberFormatException localNumberFormatException2)
      {
        throw new NumberFormatException(String.valueOf(paramString).concat(" is not a valid TypedNumber"));
      }
    }
  }
  
  public byte byteValue()
  {
    return (byte)(int)longValue();
  }
  
  public double doubleValue()
  {
    if (zzcdg()) {
      return ayM;
    }
    return ayL;
  }
  
  public boolean equals(Object paramObject)
  {
    return ((paramObject instanceof zzdk)) && (zza((zzdk)paramObject) == 0);
  }
  
  public float floatValue()
  {
    return (float)doubleValue();
  }
  
  public int hashCode()
  {
    return new Long(longValue()).hashCode();
  }
  
  public int intValue()
  {
    return zzcdi();
  }
  
  public long longValue()
  {
    return zzcdh();
  }
  
  public short shortValue()
  {
    return zzcdj();
  }
  
  public String toString()
  {
    if (zzcdg()) {
      return Long.toString(ayM);
    }
    return Double.toString(ayL);
  }
  
  public int zza(zzdk paramzzdk)
  {
    if ((zzcdg()) && (paramzzdk.zzcdg())) {
      return new Long(ayM).compareTo(Long.valueOf(ayM));
    }
    return Double.compare(doubleValue(), paramzzdk.doubleValue());
  }
  
  public boolean zzcdf()
  {
    return !zzcdg();
  }
  
  public boolean zzcdg()
  {
    return ayN;
  }
  
  public long zzcdh()
  {
    if (zzcdg()) {
      return ayM;
    }
    return ayL;
  }
  
  public int zzcdi()
  {
    return (int)longValue();
  }
  
  public short zzcdj()
  {
    return (short)(int)longValue();
  }
}

/* Location:
 * Qualified Name:     com.google.android.gms.tagmanager.zzdk
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */