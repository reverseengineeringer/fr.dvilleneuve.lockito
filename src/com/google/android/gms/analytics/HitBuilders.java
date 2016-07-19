package com.google.android.gms.analytics;

import android.text.TextUtils;
import com.google.android.gms.analytics.ecommerce.Product;
import com.google.android.gms.analytics.ecommerce.ProductAction;
import com.google.android.gms.analytics.ecommerce.Promotion;
import com.google.android.gms.analytics.internal.zzae;
import com.google.android.gms.analytics.internal.zzao;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class HitBuilders
{
  @Deprecated
  public static class AppViewBuilder
    extends HitBuilders.HitBuilder<AppViewBuilder>
  {
    public AppViewBuilder()
    {
      set("&t", "screenview");
    }
  }
  
  public static class EventBuilder
    extends HitBuilders.HitBuilder<EventBuilder>
  {
    public EventBuilder()
    {
      set("&t", "event");
    }
    
    public EventBuilder(String paramString1, String paramString2)
    {
      this();
      setCategory(paramString1);
      setAction(paramString2);
    }
    
    public EventBuilder setAction(String paramString)
    {
      set("&ea", paramString);
      return this;
    }
    
    public EventBuilder setCategory(String paramString)
    {
      set("&ec", paramString);
      return this;
    }
    
    public EventBuilder setLabel(String paramString)
    {
      set("&el", paramString);
      return this;
    }
    
    public EventBuilder setValue(long paramLong)
    {
      set("&ev", Long.toString(paramLong));
      return this;
    }
  }
  
  public static class ExceptionBuilder
    extends HitBuilders.HitBuilder<ExceptionBuilder>
  {
    public ExceptionBuilder()
    {
      set("&t", "exception");
    }
    
    public ExceptionBuilder setDescription(String paramString)
    {
      set("&exd", paramString);
      return this;
    }
    
    public ExceptionBuilder setFatal(boolean paramBoolean)
    {
      set("&exf", zzao.zzat(paramBoolean));
      return this;
    }
  }
  
  protected static class HitBuilder<T extends HitBuilder>
  {
    private Map<String, String> zzcsq = new HashMap();
    ProductAction zzcsr;
    Map<String, List<Product>> zzcss = new HashMap();
    List<Promotion> zzcst = new ArrayList();
    List<Product> zzcsu = new ArrayList();
    
    private T zzo(String paramString1, String paramString2)
    {
      if (paramString1 != null)
      {
        if (paramString2 != null) {
          zzcsq.put(paramString1, paramString2);
        }
        return this;
      }
      zzae.zzcy("HitBuilder.setIfNonNull() called with a null paramName.");
      return this;
    }
    
    public T addImpression(Product paramProduct, String paramString)
    {
      if (paramProduct == null)
      {
        zzae.zzcy("product should be non-null");
        return this;
      }
      String str = paramString;
      if (paramString == null) {
        str = "";
      }
      if (!zzcss.containsKey(str)) {
        zzcss.put(str, new ArrayList());
      }
      ((List)zzcss.get(str)).add(paramProduct);
      return this;
    }
    
    public T addProduct(Product paramProduct)
    {
      if (paramProduct == null)
      {
        zzae.zzcy("product should be non-null");
        return this;
      }
      zzcsu.add(paramProduct);
      return this;
    }
    
    public T addPromotion(Promotion paramPromotion)
    {
      if (paramPromotion == null)
      {
        zzae.zzcy("promotion should be non-null");
        return this;
      }
      zzcst.add(paramPromotion);
      return this;
    }
    
    public Map<String, String> build()
    {
      HashMap localHashMap = new HashMap(zzcsq);
      if (zzcsr != null) {
        localHashMap.putAll(zzcsr.build());
      }
      Object localObject = zzcst.iterator();
      int i = 1;
      while (((Iterator)localObject).hasNext())
      {
        localHashMap.putAll(((Promotion)((Iterator)localObject).next()).zzef(zzc.zzbi(i)));
        i += 1;
      }
      localObject = zzcsu.iterator();
      i = 1;
      while (((Iterator)localObject).hasNext())
      {
        localHashMap.putAll(((Product)((Iterator)localObject).next()).zzef(zzc.zzbg(i)));
        i += 1;
      }
      Iterator localIterator1 = zzcss.entrySet().iterator();
      i = 1;
      if (localIterator1.hasNext())
      {
        Map.Entry localEntry = (Map.Entry)localIterator1.next();
        localObject = (List)localEntry.getValue();
        String str1 = zzc.zzbl(i);
        Iterator localIterator2 = ((List)localObject).iterator();
        int j = 1;
        if (localIterator2.hasNext())
        {
          Product localProduct = (Product)localIterator2.next();
          localObject = String.valueOf(str1);
          String str2 = String.valueOf(zzc.zzbk(j));
          if (str2.length() != 0) {}
          for (localObject = ((String)localObject).concat(str2);; localObject = new String((String)localObject))
          {
            localHashMap.putAll(localProduct.zzef((String)localObject));
            j += 1;
            break;
          }
        }
        if (!TextUtils.isEmpty((CharSequence)localEntry.getKey()))
        {
          localObject = String.valueOf(str1);
          str1 = String.valueOf("nm");
          if (str1.length() == 0) {
            break label357;
          }
        }
        label357:
        for (localObject = ((String)localObject).concat(str1);; localObject = new String((String)localObject))
        {
          localHashMap.put(localObject, (String)localEntry.getKey());
          i += 1;
          break;
        }
      }
      return localHashMap;
    }
    
    protected String get(String paramString)
    {
      return (String)zzcsq.get(paramString);
    }
    
    public final T set(String paramString1, String paramString2)
    {
      if (paramString1 != null)
      {
        zzcsq.put(paramString1, paramString2);
        return this;
      }
      zzae.zzcy("HitBuilder.set() called with a null paramName.");
      return this;
    }
    
    public final T setAll(Map<String, String> paramMap)
    {
      if (paramMap == null) {
        return this;
      }
      zzcsq.putAll(new HashMap(paramMap));
      return this;
    }
    
    public T setCampaignParamsFromUrl(String paramString)
    {
      paramString = zzao.zzfa(paramString);
      if (TextUtils.isEmpty(paramString)) {
        return this;
      }
      paramString = zzao.zzey(paramString);
      zzo("&cc", (String)paramString.get("utm_content"));
      zzo("&cm", (String)paramString.get("utm_medium"));
      zzo("&cn", (String)paramString.get("utm_campaign"));
      zzo("&cs", (String)paramString.get("utm_source"));
      zzo("&ck", (String)paramString.get("utm_term"));
      zzo("&ci", (String)paramString.get("utm_id"));
      zzo("&anid", (String)paramString.get("anid"));
      zzo("&gclid", (String)paramString.get("gclid"));
      zzo("&dclid", (String)paramString.get("dclid"));
      zzo("&aclid", (String)paramString.get("aclid"));
      zzo("&gmob_t", (String)paramString.get("gmob_t"));
      return this;
    }
    
    public T setCustomDimension(int paramInt, String paramString)
    {
      set(zzc.zzbc(paramInt), paramString);
      return this;
    }
    
    public T setCustomMetric(int paramInt, float paramFloat)
    {
      set(zzc.zzbe(paramInt), Float.toString(paramFloat));
      return this;
    }
    
    protected T setHitType(String paramString)
    {
      set("&t", paramString);
      return this;
    }
    
    public T setNewSession()
    {
      set("&sc", "start");
      return this;
    }
    
    public T setNonInteraction(boolean paramBoolean)
    {
      set("&ni", zzao.zzat(paramBoolean));
      return this;
    }
    
    public T setProductAction(ProductAction paramProductAction)
    {
      zzcsr = paramProductAction;
      return this;
    }
    
    public T setPromotionAction(String paramString)
    {
      zzcsq.put("&promoa", paramString);
      return this;
    }
  }
  
  @Deprecated
  public static class ItemBuilder
    extends HitBuilders.HitBuilder<ItemBuilder>
  {
    public ItemBuilder()
    {
      set("&t", "item");
    }
    
    public ItemBuilder setCategory(String paramString)
    {
      set("&iv", paramString);
      return this;
    }
    
    public ItemBuilder setCurrencyCode(String paramString)
    {
      set("&cu", paramString);
      return this;
    }
    
    public ItemBuilder setName(String paramString)
    {
      set("&in", paramString);
      return this;
    }
    
    public ItemBuilder setPrice(double paramDouble)
    {
      set("&ip", Double.toString(paramDouble));
      return this;
    }
    
    public ItemBuilder setQuantity(long paramLong)
    {
      set("&iq", Long.toString(paramLong));
      return this;
    }
    
    public ItemBuilder setSku(String paramString)
    {
      set("&ic", paramString);
      return this;
    }
    
    public ItemBuilder setTransactionId(String paramString)
    {
      set("&ti", paramString);
      return this;
    }
  }
  
  public static class ScreenViewBuilder
    extends HitBuilders.HitBuilder<ScreenViewBuilder>
  {
    public ScreenViewBuilder()
    {
      set("&t", "screenview");
    }
  }
  
  public static class SocialBuilder
    extends HitBuilders.HitBuilder<SocialBuilder>
  {
    public SocialBuilder()
    {
      set("&t", "social");
    }
    
    public SocialBuilder setAction(String paramString)
    {
      set("&sa", paramString);
      return this;
    }
    
    public SocialBuilder setNetwork(String paramString)
    {
      set("&sn", paramString);
      return this;
    }
    
    public SocialBuilder setTarget(String paramString)
    {
      set("&st", paramString);
      return this;
    }
  }
  
  public static class TimingBuilder
    extends HitBuilders.HitBuilder<TimingBuilder>
  {
    public TimingBuilder()
    {
      set("&t", "timing");
    }
    
    public TimingBuilder(String paramString1, String paramString2, long paramLong)
    {
      this();
      setVariable(paramString2);
      setValue(paramLong);
      setCategory(paramString1);
    }
    
    public TimingBuilder setCategory(String paramString)
    {
      set("&utc", paramString);
      return this;
    }
    
    public TimingBuilder setLabel(String paramString)
    {
      set("&utl", paramString);
      return this;
    }
    
    public TimingBuilder setValue(long paramLong)
    {
      set("&utt", Long.toString(paramLong));
      return this;
    }
    
    public TimingBuilder setVariable(String paramString)
    {
      set("&utv", paramString);
      return this;
    }
  }
  
  @Deprecated
  public static class TransactionBuilder
    extends HitBuilders.HitBuilder<TransactionBuilder>
  {
    public TransactionBuilder()
    {
      set("&t", "transaction");
    }
    
    public TransactionBuilder setAffiliation(String paramString)
    {
      set("&ta", paramString);
      return this;
    }
    
    public TransactionBuilder setCurrencyCode(String paramString)
    {
      set("&cu", paramString);
      return this;
    }
    
    public TransactionBuilder setRevenue(double paramDouble)
    {
      set("&tr", Double.toString(paramDouble));
      return this;
    }
    
    public TransactionBuilder setShipping(double paramDouble)
    {
      set("&ts", Double.toString(paramDouble));
      return this;
    }
    
    public TransactionBuilder setTax(double paramDouble)
    {
      set("&tt", Double.toString(paramDouble));
      return this;
    }
    
    public TransactionBuilder setTransactionId(String paramString)
    {
      set("&ti", paramString);
      return this;
    }
  }
}

/* Location:
 * Qualified Name:     com.google.android.gms.analytics.HitBuilders
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */