package com.google.android.gms.internal;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.EnumSet;

class zzans$8
  implements zzanx<T>
{
  zzans$8(zzans paramzzans, Type paramType) {}
  
  public T a()
  {
    if ((beO instanceof ParameterizedType))
    {
      localObject = ((ParameterizedType)beO).getActualTypeArguments()[0];
      if ((localObject instanceof Class)) {
        return EnumSet.noneOf((Class)localObject);
      }
      localObject = String.valueOf(beO.toString());
      if (((String)localObject).length() != 0) {}
      for (localObject = "Invalid EnumSet type: ".concat((String)localObject);; localObject = new String("Invalid EnumSet type: ")) {
        throw new zzamz((String)localObject);
      }
    }
    Object localObject = String.valueOf(beO.toString());
    if (((String)localObject).length() != 0) {}
    for (localObject = "Invalid EnumSet type: ".concat((String)localObject);; localObject = new String("Invalid EnumSet type: ")) {
      throw new zzamz((String)localObject);
    }
  }
}

/* Location:
 * Qualified Name:     com.google.android.gms.internal.zzans.8
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */