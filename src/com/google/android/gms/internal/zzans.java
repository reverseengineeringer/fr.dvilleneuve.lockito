package com.google.android.gms.internal;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.EnumSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;

public final class zzans
{
  private final Map<Type, zzamu<?>> ben;
  
  public zzans(Map<Type, zzamu<?>> paramMap)
  {
    ben = paramMap;
  }
  
  private <T> zzanx<T> zzc(final Type paramType, Class<? super T> paramClass)
  {
    if (Collection.class.isAssignableFrom(paramClass))
    {
      if (SortedSet.class.isAssignableFrom(paramClass)) {
        new zzanx()
        {
          public T a()
          {
            return new TreeSet();
          }
        };
      }
      if (EnumSet.class.isAssignableFrom(paramClass)) {
        new zzanx()
        {
          public T a()
          {
            if ((paramType instanceof ParameterizedType))
            {
              localObject = ((ParameterizedType)paramType).getActualTypeArguments()[0];
              if ((localObject instanceof Class)) {
                return EnumSet.noneOf((Class)localObject);
              }
              localObject = String.valueOf(paramType.toString());
              if (((String)localObject).length() != 0) {}
              for (localObject = "Invalid EnumSet type: ".concat((String)localObject);; localObject = new String("Invalid EnumSet type: ")) {
                throw new zzamz((String)localObject);
              }
            }
            Object localObject = String.valueOf(paramType.toString());
            if (((String)localObject).length() != 0) {}
            for (localObject = "Invalid EnumSet type: ".concat((String)localObject);; localObject = new String("Invalid EnumSet type: ")) {
              throw new zzamz((String)localObject);
            }
          }
        };
      }
      if (Set.class.isAssignableFrom(paramClass)) {
        new zzanx()
        {
          public T a()
          {
            return new LinkedHashSet();
          }
        };
      }
      if (Queue.class.isAssignableFrom(paramClass)) {
        new zzanx()
        {
          public T a()
          {
            return new LinkedList();
          }
        };
      }
      new zzanx()
      {
        public T a()
        {
          return new ArrayList();
        }
      };
    }
    if (Map.class.isAssignableFrom(paramClass))
    {
      if (SortedMap.class.isAssignableFrom(paramClass)) {
        new zzanx()
        {
          public T a()
          {
            return new TreeMap();
          }
        };
      }
      if (((paramType instanceof ParameterizedType)) && (!String.class.isAssignableFrom(zzaoo.zzl(((ParameterizedType)paramType).getActualTypeArguments()[0]).s()))) {
        new zzanx()
        {
          public T a()
          {
            return new LinkedHashMap();
          }
        };
      }
      new zzanx()
      {
        public T a()
        {
          return new zzanw();
        }
      };
    }
    return null;
  }
  
  private <T> zzanx<T> zzd(final Type paramType, final Class<? super T> paramClass)
  {
    new zzanx()
    {
      private final zzaoa beQ = zzaoa.f();
      
      public T a()
      {
        try
        {
          Object localObject = beQ.zzf(paramClass);
          return (T)localObject;
        }
        catch (Exception localException)
        {
          String str = String.valueOf(paramType);
          throw new RuntimeException(String.valueOf(str).length() + 116 + "Unable to invoke no-args constructor for " + str + ". " + "Register an InstanceCreator with Gson for this type may fix this problem.", localException);
        }
      }
    };
  }
  
  private <T> zzanx<T> zzl(final Class<? super T> paramClass)
  {
    try
    {
      paramClass = paramClass.getDeclaredConstructor(new Class[0]);
      if (!paramClass.isAccessible()) {
        paramClass.setAccessible(true);
      }
      paramClass = new zzanx()
      {
        public T a()
        {
          try
          {
            Object localObject = paramClass.newInstance(null);
            return (T)localObject;
          }
          catch (InstantiationException localInstantiationException)
          {
            str = String.valueOf(paramClass);
            throw new RuntimeException(String.valueOf(str).length() + 30 + "Failed to invoke " + str + " with no args", localInstantiationException);
          }
          catch (InvocationTargetException localInvocationTargetException)
          {
            String str = String.valueOf(paramClass);
            throw new RuntimeException(String.valueOf(str).length() + 30 + "Failed to invoke " + str + " with no args", localInvocationTargetException.getTargetException());
          }
          catch (IllegalAccessException localIllegalAccessException)
          {
            throw new AssertionError(localIllegalAccessException);
          }
        }
      };
      return paramClass;
    }
    catch (NoSuchMethodException paramClass) {}
    return null;
  }
  
  public String toString()
  {
    return ben.toString();
  }
  
  public <T> zzanx<T> zzb(final zzaoo<T> paramzzaoo)
  {
    final Type localType = paramzzaoo.t();
    Class localClass = paramzzaoo.s();
    paramzzaoo = (zzamu)ben.get(localType);
    if (paramzzaoo != null) {
      paramzzaoo = new zzanx()
      {
        public T a()
        {
          return (T)paramzzaoo.zza(localType);
        }
      };
    }
    zzanx localzzanx;
    do
    {
      do
      {
        return paramzzaoo;
        paramzzaoo = (zzamu)ben.get(localClass);
        if (paramzzaoo != null) {
          new zzanx()
          {
            public T a()
            {
              return (T)paramzzaoo.zza(localType);
            }
          };
        }
        localzzanx = zzl(localClass);
        paramzzaoo = localzzanx;
      } while (localzzanx != null);
      localzzanx = zzc(localType, localClass);
      paramzzaoo = localzzanx;
    } while (localzzanx != null);
    return zzd(localType, localClass);
  }
}

/* Location:
 * Qualified Name:     com.google.android.gms.internal.zzans
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */