package com.facebook.stetho.inspector.elements;

import com.facebook.stetho.common.ThreadBound;
import com.facebook.stetho.common.UncheckedCallable;
import com.facebook.stetho.common.Util;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.Nullable;

public abstract class Descriptor
  implements NodeDescriptor
{
  private Host mHost;
  
  protected static Map<String, String> parseSetAttributesAsTextArg(String paramString)
  {
    String str1 = "";
    String str2 = "";
    StringBuilder localStringBuilder = new StringBuilder();
    HashMap localHashMap = new HashMap();
    int i = 0;
    int j = 0;
    int k = paramString.length();
    if (j < k)
    {
      char c = paramString.charAt(j);
      if (c == '=')
      {
        str2 = localStringBuilder.toString();
        localStringBuilder.setLength(0);
      }
      for (;;)
      {
        j += 1;
        break;
        if (c == '"')
        {
          if (i != 0)
          {
            str1 = localStringBuilder.toString();
            localStringBuilder.setLength(0);
          }
          if (i == 0) {}
          for (i = 1;; i = 0) {
            break;
          }
        }
        if ((c == ' ') && (i == 0)) {
          localHashMap.put(str2, str1);
        } else {
          localStringBuilder.append(c);
        }
      }
    }
    if ((!str2.isEmpty()) && (!str1.isEmpty())) {
      localHashMap.put(str2, str1);
    }
    return localHashMap;
  }
  
  public final boolean checkThreadAccess()
  {
    return getHost().checkThreadAccess();
  }
  
  protected final Host getHost()
  {
    return mHost;
  }
  
  final void initialize(Host paramHost)
  {
    Util.throwIfNull(paramHost);
    Util.throwIfNotNull(mHost);
    mHost = paramHost;
  }
  
  final boolean isInitialized()
  {
    return mHost != null;
  }
  
  public final <V> V postAndWait(UncheckedCallable<V> paramUncheckedCallable)
  {
    return (V)getHost().postAndWait(paramUncheckedCallable);
  }
  
  public final void postAndWait(Runnable paramRunnable)
  {
    getHost().postAndWait(paramRunnable);
  }
  
  public final void postDelayed(Runnable paramRunnable, long paramLong)
  {
    getHost().postDelayed(paramRunnable, paramLong);
  }
  
  public final void removeCallbacks(Runnable paramRunnable)
  {
    getHost().removeCallbacks(paramRunnable);
  }
  
  public final void verifyThreadAccess()
  {
    getHost().verifyThreadAccess();
  }
  
  public static abstract interface Host
    extends ThreadBound
  {
    @Nullable
    public abstract Descriptor getDescriptor(@Nullable Object paramObject);
    
    public abstract void onAttributeModified(Object paramObject, String paramString1, String paramString2);
    
    public abstract void onAttributeRemoved(Object paramObject, String paramString);
  }
}

/* Location:
 * Qualified Name:     com.facebook.stetho.inspector.elements.Descriptor
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */