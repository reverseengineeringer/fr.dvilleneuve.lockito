package com.google.android.gms.tagmanager;

import com.google.android.gms.internal.zzai.zza;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class zzdl
{
  private static final Object ayO = null;
  private static Long ayP = new Long(0L);
  private static Double ayQ = new Double(0.0D);
  private static zzdk ayR = zzdk.zzbs(0L);
  private static String ayS = new String("");
  private static Boolean ayT = new Boolean(false);
  private static List<Object> ayU = new ArrayList(0);
  private static Map<Object, Object> ayV = new HashMap();
  private static zzai.zza ayW = zzar(ayS);
  
  private static double getDouble(Object paramObject)
  {
    if ((paramObject instanceof Number)) {
      return ((Number)paramObject).doubleValue();
    }
    zzbn.e("getDouble received non-Number");
    return 0.0D;
  }
  
  public static String zzam(Object paramObject)
  {
    if (paramObject == null) {
      return ayS;
    }
    return paramObject.toString();
  }
  
  public static zzdk zzan(Object paramObject)
  {
    if ((paramObject instanceof zzdk)) {
      return (zzdk)paramObject;
    }
    if (zzat(paramObject)) {
      return zzdk.zzbs(zzau(paramObject));
    }
    if (zzas(paramObject)) {
      return zzdk.zza(Double.valueOf(getDouble(paramObject)));
    }
    return zzot(zzam(paramObject));
  }
  
  public static Long zzao(Object paramObject)
  {
    if (zzat(paramObject)) {
      return Long.valueOf(zzau(paramObject));
    }
    return zzou(zzam(paramObject));
  }
  
  public static Double zzap(Object paramObject)
  {
    if (zzas(paramObject)) {
      return Double.valueOf(getDouble(paramObject));
    }
    return zzov(zzam(paramObject));
  }
  
  public static Boolean zzaq(Object paramObject)
  {
    if ((paramObject instanceof Boolean)) {
      return (Boolean)paramObject;
    }
    return zzow(zzam(paramObject));
  }
  
  public static zzai.zza zzar(Object paramObject)
  {
    boolean bool = false;
    zzai.zza localzza1 = new zzai.zza();
    if ((paramObject instanceof zzai.zza)) {
      return (zzai.zza)paramObject;
    }
    if ((paramObject instanceof String))
    {
      type = 1;
      zzwt = ((String)paramObject);
    }
    for (;;)
    {
      zzxd = bool;
      return localzza1;
      Object localObject1;
      Object localObject2;
      if ((paramObject instanceof List))
      {
        type = 2;
        localObject1 = (List)paramObject;
        paramObject = new ArrayList(((List)localObject1).size());
        localObject1 = ((List)localObject1).iterator();
        bool = false;
        if (((Iterator)localObject1).hasNext())
        {
          localObject2 = zzar(((Iterator)localObject1).next());
          if (localObject2 == ayW) {
            return ayW;
          }
          if ((bool) || (zzxd)) {}
          for (bool = true;; bool = false)
          {
            ((List)paramObject).add(localObject2);
            break;
          }
        }
        zzwu = ((zzai.zza[])((List)paramObject).toArray(new zzai.zza[0]));
      }
      else if ((paramObject instanceof Map))
      {
        type = 3;
        localObject2 = ((Map)paramObject).entrySet();
        paramObject = new ArrayList(((Set)localObject2).size());
        localObject1 = new ArrayList(((Set)localObject2).size());
        localObject2 = ((Set)localObject2).iterator();
        bool = false;
        if (((Iterator)localObject2).hasNext())
        {
          Object localObject3 = (Map.Entry)((Iterator)localObject2).next();
          zzai.zza localzza2 = zzar(((Map.Entry)localObject3).getKey());
          localObject3 = zzar(((Map.Entry)localObject3).getValue());
          if ((localzza2 == ayW) || (localObject3 == ayW)) {
            return ayW;
          }
          if ((bool) || (zzxd) || (zzxd)) {}
          for (bool = true;; bool = false)
          {
            ((List)paramObject).add(localzza2);
            ((List)localObject1).add(localObject3);
            break;
          }
        }
        zzwv = ((zzai.zza[])((List)paramObject).toArray(new zzai.zza[0]));
        zzww = ((zzai.zza[])((List)localObject1).toArray(new zzai.zza[0]));
      }
      else if (zzas(paramObject))
      {
        type = 1;
        zzwt = paramObject.toString();
      }
      else if (zzat(paramObject))
      {
        type = 6;
        zzwz = zzau(paramObject);
      }
      else
      {
        if (!(paramObject instanceof Boolean)) {
          break;
        }
        type = 8;
        zzxa = ((Boolean)paramObject).booleanValue();
      }
    }
    if (paramObject == null)
    {
      paramObject = "null";
      paramObject = String.valueOf(paramObject);
      if (((String)paramObject).length() == 0) {
        break label506;
      }
    }
    label506:
    for (paramObject = "Converting to Value from unknown object type: ".concat((String)paramObject);; paramObject = new String("Converting to Value from unknown object type: "))
    {
      zzbn.e((String)paramObject);
      return ayW;
      paramObject = paramObject.getClass().toString();
      break;
    }
  }
  
  private static boolean zzas(Object paramObject)
  {
    return ((paramObject instanceof Double)) || ((paramObject instanceof Float)) || (((paramObject instanceof zzdk)) && (((zzdk)paramObject).zzcdf()));
  }
  
  private static boolean zzat(Object paramObject)
  {
    return ((paramObject instanceof Byte)) || ((paramObject instanceof Short)) || ((paramObject instanceof Integer)) || ((paramObject instanceof Long)) || (((paramObject instanceof zzdk)) && (((zzdk)paramObject).zzcdg()));
  }
  
  private static long zzau(Object paramObject)
  {
    if ((paramObject instanceof Number)) {
      return ((Number)paramObject).longValue();
    }
    zzbn.e("getInt64 received non-Number");
    return 0L;
  }
  
  public static Object zzcdk()
  {
    return null;
  }
  
  public static Long zzcdl()
  {
    return ayP;
  }
  
  public static Double zzcdm()
  {
    return ayQ;
  }
  
  public static Boolean zzcdn()
  {
    return ayT;
  }
  
  public static zzdk zzcdo()
  {
    return ayR;
  }
  
  public static String zzcdp()
  {
    return ayS;
  }
  
  public static zzai.zza zzcdq()
  {
    return ayW;
  }
  
  public static String zzg(zzai.zza paramzza)
  {
    return zzam(zzl(paramzza));
  }
  
  public static zzdk zzh(zzai.zza paramzza)
  {
    return zzan(zzl(paramzza));
  }
  
  public static Long zzi(zzai.zza paramzza)
  {
    return zzao(zzl(paramzza));
  }
  
  public static Double zzj(zzai.zza paramzza)
  {
    return zzap(zzl(paramzza));
  }
  
  public static Boolean zzk(zzai.zza paramzza)
  {
    return zzaq(zzl(paramzza));
  }
  
  public static Object zzl(zzai.zza paramzza)
  {
    int k = 0;
    int j = 0;
    int i = 0;
    if (paramzza == null) {
      return null;
    }
    Object localObject1;
    Object localObject2;
    switch (type)
    {
    default: 
      i = type;
      zzbn.e(46 + "Failed to convert a value of type: " + i);
      return null;
    case 1: 
      return zzwt;
    case 2: 
      localObject1 = new ArrayList(zzwu.length);
      paramzza = zzwu;
      j = paramzza.length;
      while (i < j)
      {
        localObject2 = zzl(paramzza[i]);
        if (localObject2 == null) {
          return null;
        }
        ((ArrayList)localObject1).add(localObject2);
        i += 1;
      }
      return localObject1;
    case 3: 
      if (zzwv.length != zzww.length)
      {
        paramzza = String.valueOf(paramzza.toString());
        if (paramzza.length() != 0) {}
        for (paramzza = "Converting an invalid value to object: ".concat(paramzza);; paramzza = new String("Converting an invalid value to object: "))
        {
          zzbn.e(paramzza);
          return null;
        }
      }
      localObject1 = new HashMap(zzww.length);
      i = k;
      while (i < zzwv.length)
      {
        localObject2 = zzl(zzwv[i]);
        Object localObject3 = zzl(zzww[i]);
        if ((localObject2 == null) || (localObject3 == null)) {
          return null;
        }
        ((Map)localObject1).put(localObject2, localObject3);
        i += 1;
      }
      return localObject1;
    case 4: 
      zzbn.e("Trying to convert a macro reference to object");
      return null;
    case 5: 
      zzbn.e("Trying to convert a function id to object");
      return null;
    case 6: 
      return Long.valueOf(zzwz);
    case 7: 
      localObject1 = new StringBuffer();
      paramzza = zzxb;
      k = paramzza.length;
      i = j;
      while (i < k)
      {
        localObject2 = zzg(paramzza[i]);
        if (localObject2 == ayS) {
          return null;
        }
        ((StringBuffer)localObject1).append((String)localObject2);
        i += 1;
      }
      return ((StringBuffer)localObject1).toString();
    }
    return Boolean.valueOf(zzxa);
  }
  
  public static zzai.zza zzos(String paramString)
  {
    zzai.zza localzza = new zzai.zza();
    type = 5;
    zzwy = paramString;
    return localzza;
  }
  
  private static zzdk zzot(String paramString)
  {
    try
    {
      zzdk localzzdk = zzdk.zzor(paramString);
      return localzzdk;
    }
    catch (NumberFormatException localNumberFormatException)
    {
      zzbn.e(String.valueOf(paramString).length() + 33 + "Failed to convert '" + paramString + "' to a number.");
    }
    return ayR;
  }
  
  private static Long zzou(String paramString)
  {
    paramString = zzot(paramString);
    if (paramString == ayR) {
      return ayP;
    }
    return Long.valueOf(paramString.longValue());
  }
  
  private static Double zzov(String paramString)
  {
    paramString = zzot(paramString);
    if (paramString == ayR) {
      return ayQ;
    }
    return Double.valueOf(paramString.doubleValue());
  }
  
  private static Boolean zzow(String paramString)
  {
    if ("true".equalsIgnoreCase(paramString)) {
      return Boolean.TRUE;
    }
    if ("false".equalsIgnoreCase(paramString)) {
      return Boolean.FALSE;
    }
    return ayT;
  }
}

/* Location:
 * Qualified Name:     com.google.android.gms.tagmanager.zzdl
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */