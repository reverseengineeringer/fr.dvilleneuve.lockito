package com.google.android.gms.internal;

import com.google.android.gms.analytics.ecommerce.Product;
import com.google.android.gms.analytics.ecommerce.ProductAction;
import com.google.android.gms.analytics.ecommerce.Promotion;
import com.google.android.gms.analytics.zzg;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public final class zzme
  extends zzg<zzme>
{
  private ProductAction zzcsr;
  private final Map<String, List<Product>> zzcss = new HashMap();
  private final List<Promotion> zzcst = new ArrayList();
  private final List<Product> zzcsu = new ArrayList();
  
  public String toString()
  {
    HashMap localHashMap = new HashMap();
    if (!zzcsu.isEmpty()) {
      localHashMap.put("products", zzcsu);
    }
    if (!zzcst.isEmpty()) {
      localHashMap.put("promotions", zzcst);
    }
    if (!zzcss.isEmpty()) {
      localHashMap.put("impressions", zzcss);
    }
    localHashMap.put("productAction", zzcsr);
    return zzk(localHashMap);
  }
  
  public void zza(Product paramProduct, String paramString)
  {
    if (paramProduct == null) {
      return;
    }
    String str = paramString;
    if (paramString == null) {
      str = "";
    }
    if (!zzcss.containsKey(str)) {
      zzcss.put(str, new ArrayList());
    }
    ((List)zzcss.get(str)).add(paramProduct);
  }
  
  public void zza(zzme paramzzme)
  {
    zzcsu.addAll(zzcsu);
    zzcst.addAll(zzcst);
    Iterator localIterator = zzcss.entrySet().iterator();
    while (localIterator.hasNext())
    {
      Object localObject = (Map.Entry)localIterator.next();
      String str = (String)((Map.Entry)localObject).getKey();
      localObject = ((List)((Map.Entry)localObject).getValue()).iterator();
      while (((Iterator)localObject).hasNext()) {
        paramzzme.zza((Product)((Iterator)localObject).next(), str);
      }
    }
    if (zzcsr != null) {
      zzcsr = zzcsr;
    }
  }
  
  public ProductAction zzxs()
  {
    return zzcsr;
  }
  
  public List<Product> zzxt()
  {
    return Collections.unmodifiableList(zzcsu);
  }
  
  public Map<String, List<Product>> zzxu()
  {
    return zzcss;
  }
  
  public List<Promotion> zzxv()
  {
    return Collections.unmodifiableList(zzcst);
  }
}

/* Location:
 * Qualified Name:     com.google.android.gms.internal.zzme
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */