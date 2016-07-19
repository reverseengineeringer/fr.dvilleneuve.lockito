package com.google.android.gms.internal;

import java.math.BigInteger;

public final class zzane
  extends zzamy
{
  private static final Class<?>[] beu = { Integer.TYPE, Long.TYPE, Short.TYPE, Float.TYPE, Double.TYPE, Byte.TYPE, Boolean.TYPE, Character.TYPE, Integer.class, Long.class, Short.class, Float.class, Double.class, Byte.class, Boolean.class, Character.class };
  private Object aQx;
  
  public zzane(Boolean paramBoolean)
  {
    setValue(paramBoolean);
  }
  
  public zzane(Number paramNumber)
  {
    setValue(paramNumber);
  }
  
  public zzane(String paramString)
  {
    setValue(paramString);
  }
  
  private static boolean zza(zzane paramzzane)
  {
    if ((aQx instanceof Number))
    {
      paramzzane = (Number)aQx;
      return ((paramzzane instanceof BigInteger)) || ((paramzzane instanceof Long)) || ((paramzzane instanceof Integer)) || ((paramzzane instanceof Short)) || ((paramzzane instanceof Byte));
    }
    return false;
  }
  
  private static boolean zzck(Object paramObject)
  {
    if ((paramObject instanceof String)) {
      return true;
    }
    paramObject = paramObject.getClass();
    Class[] arrayOfClass = beu;
    int j = arrayOfClass.length;
    int i = 0;
    for (;;)
    {
      if (i >= j) {
        break label45;
      }
      if (arrayOfClass[i].isAssignableFrom((Class)paramObject)) {
        break;
      }
      i += 1;
    }
    label45:
    return false;
  }
  
  public boolean equals(Object paramObject)
  {
    boolean bool2 = false;
    if (this == paramObject) {}
    do
    {
      do
      {
        return true;
        if ((paramObject == null) || (getClass() != paramObject.getClass())) {
          return false;
        }
        paramObject = (zzane)paramObject;
        if (aQx != null) {
          break;
        }
      } while (aQx == null);
      return false;
      if ((!zza(this)) || (!zza((zzane)paramObject))) {
        break;
      }
    } while (zzczg().longValue() == ((zzane)paramObject).zzczg().longValue());
    return false;
    if (((aQx instanceof Number)) && ((aQx instanceof Number)))
    {
      double d1 = zzczg().doubleValue();
      double d2 = ((zzane)paramObject).zzczg().doubleValue();
      boolean bool1;
      if (d1 != d2)
      {
        bool1 = bool2;
        if (Double.isNaN(d1))
        {
          bool1 = bool2;
          if (!Double.isNaN(d2)) {}
        }
      }
      else
      {
        bool1 = true;
      }
      return bool1;
    }
    return aQx.equals(aQx);
  }
  
  public int hashCode()
  {
    if (aQx == null) {
      return 31;
    }
    long l;
    if (zza(this))
    {
      l = zzczg().longValue();
      return (int)(l ^ l >>> 32);
    }
    if ((aQx instanceof Number))
    {
      l = Double.doubleToLongBits(zzczg().doubleValue());
      return (int)(l ^ l >>> 32);
    }
    return aQx.hashCode();
  }
  
  void setValue(Object paramObject)
  {
    if ((paramObject instanceof Character))
    {
      aQx = String.valueOf(((Character)paramObject).charValue());
      return;
    }
    if (((paramObject instanceof Number)) || (zzck(paramObject))) {}
    for (boolean bool = true;; bool = false)
    {
      zzanq.zzbn(bool);
      aQx = paramObject;
      return;
    }
  }
  
  public Number zzczg()
  {
    if ((aQx instanceof String)) {
      return new zzanv((String)aQx);
    }
    return (Number)aQx;
  }
  
  public String zzczh()
  {
    if (zzczv()) {
      return zzczg().toString();
    }
    if (zzczu()) {
      return zzczt().toString();
    }
    return (String)aQx;
  }
  
  public double zzczi()
  {
    if (zzczv()) {
      return zzczg().doubleValue();
    }
    return Double.parseDouble(zzczh());
  }
  
  public long zzczj()
  {
    if (zzczv()) {
      return zzczg().longValue();
    }
    return Long.parseLong(zzczh());
  }
  
  public int zzczk()
  {
    if (zzczv()) {
      return zzczg().intValue();
    }
    return Integer.parseInt(zzczh());
  }
  
  public boolean zzczl()
  {
    if (zzczu()) {
      return zzczt().booleanValue();
    }
    return Boolean.parseBoolean(zzczh());
  }
  
  Boolean zzczt()
  {
    return (Boolean)aQx;
  }
  
  public boolean zzczu()
  {
    return aQx instanceof Boolean;
  }
  
  public boolean zzczv()
  {
    return aQx instanceof Number;
  }
  
  public boolean zzczw()
  {
    return aQx instanceof String;
  }
}

/* Location:
 * Qualified Name:     com.google.android.gms.internal.zzane
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */