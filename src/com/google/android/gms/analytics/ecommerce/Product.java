package com.google.android.gms.analytics.ecommerce;

import com.google.android.gms.analytics.zzc;
import com.google.android.gms.analytics.zzg;
import com.google.android.gms.common.internal.zzab;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class Product
{
  Map<String, String> zzcwd = new HashMap();
  
  void put(String paramString1, String paramString2)
  {
    zzab.zzb(paramString1, "Name should be non-null");
    zzcwd.put(paramString1, paramString2);
  }
  
  public Product setBrand(String paramString)
  {
    put("br", paramString);
    return this;
  }
  
  public Product setCategory(String paramString)
  {
    put("ca", paramString);
    return this;
  }
  
  public Product setCouponCode(String paramString)
  {
    put("cc", paramString);
    return this;
  }
  
  public Product setCustomDimension(int paramInt, String paramString)
  {
    put(zzc.zzbn(paramInt), paramString);
    return this;
  }
  
  public Product setCustomMetric(int paramInt1, int paramInt2)
  {
    put(zzc.zzbo(paramInt1), Integer.toString(paramInt2));
    return this;
  }
  
  public Product setId(String paramString)
  {
    put("id", paramString);
    return this;
  }
  
  public Product setName(String paramString)
  {
    put("nm", paramString);
    return this;
  }
  
  public Product setPosition(int paramInt)
  {
    put("ps", Integer.toString(paramInt));
    return this;
  }
  
  public Product setPrice(double paramDouble)
  {
    put("pr", Double.toString(paramDouble));
    return this;
  }
  
  public Product setQuantity(int paramInt)
  {
    put("qt", Integer.toString(paramInt));
    return this;
  }
  
  public Product setVariant(String paramString)
  {
    put("va", paramString);
    return this;
  }
  
  public String toString()
  {
    return zzg.zzao(zzcwd);
  }
  
  public Map<String, String> zzef(String paramString)
  {
    HashMap localHashMap = new HashMap();
    Iterator localIterator = zzcwd.entrySet().iterator();
    if (localIterator.hasNext())
    {
      Map.Entry localEntry = (Map.Entry)localIterator.next();
      String str1 = String.valueOf(paramString);
      String str2 = String.valueOf((String)localEntry.getKey());
      if (str2.length() != 0) {}
      for (str1 = str1.concat(str2);; str1 = new String(str1))
      {
        localHashMap.put(str1, (String)localEntry.getValue());
        break;
      }
    }
    return localHashMap;
  }
}

/* Location:
 * Qualified Name:     com.google.android.gms.analytics.ecommerce.Product
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */