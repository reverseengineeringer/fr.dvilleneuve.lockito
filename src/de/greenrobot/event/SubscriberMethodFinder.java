package de.greenrobot.event;

import android.util.Log;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

class SubscriberMethodFinder
{
  private static final int MODIFIERS_IGNORE = 1032;
  private static final Map<String, List<SubscriberMethod>> methodCache = new HashMap();
  private static final Map<Class<?>, Class<?>> skipMethodVerificationForClasses = new ConcurrentHashMap();
  
  static void clearCaches()
  {
    synchronized (methodCache)
    {
      methodCache.clear();
      return;
    }
  }
  
  public static void clearSkipMethodVerifications()
  {
    skipMethodVerificationForClasses.clear();
  }
  
  static void skipMethodVerificationFor(Class<?> paramClass)
  {
    if (!methodCache.isEmpty()) {
      throw new IllegalStateException("This method must be called before registering anything");
    }
    skipMethodVerificationForClasses.put(paramClass, paramClass);
  }
  
  List<SubscriberMethod> findSubscriberMethods(Class<?> arg1, String paramString)
  {
    String str1 = ???.getName() + '.' + paramString;
    synchronized (methodCache)
    {
      localObject2 = (List)methodCache.get(str1);
      if (localObject2 != null) {
        return (List<SubscriberMethod>)localObject2;
      }
    }
    ArrayList localArrayList = new ArrayList();
    Object localObject2 = ???;
    HashSet localHashSet = new HashSet();
    StringBuilder localStringBuilder = new StringBuilder();
    for (;;)
    {
      if (localObject2 != null)
      {
        ??? = ((Class)localObject2).getName();
        if ((!((String)???).startsWith("java.")) && (!((String)???).startsWith("javax.")) && (!((String)???).startsWith("android."))) {}
      }
      else
      {
        if (!localArrayList.isEmpty()) {
          break;
        }
        throw new EventBusException("Subscriber " + ??? + " has no public methods called " + paramString);
      }
      Method[] arrayOfMethod = ((Class)localObject2).getMethods();
      int j = arrayOfMethod.length;
      int i = 0;
      if (i < j)
      {
        Method localMethod = arrayOfMethod[i];
        String str2 = localMethod.getName();
        if (str2.startsWith(paramString))
        {
          int k = localMethod.getModifiers();
          if (((k & 0x1) == 0) || ((k & 0x408) != 0)) {
            break label461;
          }
          Object localObject3 = localMethod.getParameterTypes();
          if (localObject3.length == 1)
          {
            ??? = str2.substring(paramString.length());
            if (((String)???).length() != 0) {
              break label366;
            }
            ??? = ThreadMode.PostThread;
            label289:
            localObject3 = localObject3[0];
            localStringBuilder.setLength(0);
            localStringBuilder.append(str2);
            localStringBuilder.append('>').append(((Class)localObject3).getName());
            if (localHashSet.add(localStringBuilder.toString())) {
              localArrayList.add(new SubscriberMethod(localMethod, (ThreadMode)???, (Class)localObject3));
            }
          }
        }
        for (;;)
        {
          i += 1;
          break;
          label366:
          if (((String)???).equals("MainThread"))
          {
            ??? = ThreadMode.MainThread;
            break label289;
          }
          if (((String)???).equals("BackgroundThread"))
          {
            ??? = ThreadMode.BackgroundThread;
            break label289;
          }
          if (((String)???).equals("Async"))
          {
            ??? = ThreadMode.Async;
            break label289;
          }
          if (!skipMethodVerificationForClasses.containsKey(localObject2))
          {
            throw new EventBusException("Illegal onEvent method, check for typos: " + localMethod);
            label461:
            if (!skipMethodVerificationForClasses.containsKey(localObject2)) {
              Log.d(EventBus.TAG, "Skipping method (not public, static or abstract): " + localObject2 + "." + str2);
            }
          }
        }
      }
      localObject2 = ((Class)localObject2).getSuperclass();
    }
    synchronized (methodCache)
    {
      methodCache.put(str1, localArrayList);
      return localArrayList;
    }
  }
}

/* Location:
 * Qualified Name:     de.greenrobot.event.SubscriberMethodFinder
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */