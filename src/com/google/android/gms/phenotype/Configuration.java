package com.google.android.gms.phenotype;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.zzaa;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

public class Configuration
  extends AbstractSafeParcelable
  implements Comparable<Configuration>
{
  public static final Parcelable.Creator<Configuration> CREATOR = new zza();
  public final int arY;
  public final Flag[] arZ;
  public final String[] asa;
  public final Map<String, Flag> asb;
  final int mVersionCode;
  
  Configuration(int paramInt1, int paramInt2, Flag[] paramArrayOfFlag, String[] paramArrayOfString)
  {
    mVersionCode = paramInt1;
    arY = paramInt2;
    arZ = paramArrayOfFlag;
    asb = new TreeMap();
    paramInt2 = paramArrayOfFlag.length;
    paramInt1 = 0;
    while (paramInt1 < paramInt2)
    {
      Flag localFlag = paramArrayOfFlag[paramInt1];
      asb.put(name, localFlag);
      paramInt1 += 1;
    }
    asa = paramArrayOfString;
    if (asa != null) {
      Arrays.sort(asa);
    }
  }
  
  public boolean equals(Object paramObject)
  {
    boolean bool2 = false;
    boolean bool1 = bool2;
    if (paramObject != null)
    {
      bool1 = bool2;
      if ((paramObject instanceof Configuration))
      {
        paramObject = (Configuration)paramObject;
        bool1 = bool2;
        if (mVersionCode == mVersionCode)
        {
          bool1 = bool2;
          if (arY == arY)
          {
            bool1 = bool2;
            if (zzaa.equal(asb, asb))
            {
              bool1 = bool2;
              if (Arrays.equals(asa, asa)) {
                bool1 = true;
              }
            }
          }
        }
      }
    }
    return bool1;
  }
  
  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder("Configuration(");
    localStringBuilder.append(mVersionCode);
    localStringBuilder.append(", ");
    localStringBuilder.append(arY);
    localStringBuilder.append(", ");
    localStringBuilder.append("(");
    Object localObject = asb.values().iterator();
    while (((Iterator)localObject).hasNext())
    {
      localStringBuilder.append((Flag)((Iterator)localObject).next());
      localStringBuilder.append(", ");
    }
    localStringBuilder.append(")");
    localStringBuilder.append(", ");
    localStringBuilder.append("(");
    if (asa != null)
    {
      localObject = asa;
      int j = localObject.length;
      int i = 0;
      while (i < j)
      {
        localStringBuilder.append(localObject[i]);
        localStringBuilder.append(", ");
        i += 1;
      }
    }
    localStringBuilder.append("null");
    localStringBuilder.append(")");
    localStringBuilder.append(")");
    return localStringBuilder.toString();
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    zza.zza(this, paramParcel, paramInt);
  }
  
  public int zza(Configuration paramConfiguration)
  {
    return arY - arY;
  }
}

/* Location:
 * Qualified Name:     com.google.android.gms.phenotype.Configuration
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */