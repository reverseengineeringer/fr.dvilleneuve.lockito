package com.google.android.gms.common.server.response;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.zzab;
import com.google.android.gms.common.server.converter.ConverterWrapper;
import java.util.ArrayList;
import java.util.Map;

public class FastJsonResponse$Field<I, O>
  extends AbstractSafeParcelable
{
  public static final zza CREATOR = new zza();
  private final int mVersionCode;
  protected final String zA;
  private FieldMappingDictionary zB;
  private FastJsonResponse.zza<I, O> zC;
  protected final int zt;
  protected final boolean zu;
  protected final int zv;
  protected final boolean zw;
  protected final String zx;
  protected final int zy;
  protected final Class<? extends FastJsonResponse> zz;
  
  FastJsonResponse$Field(int paramInt1, int paramInt2, boolean paramBoolean1, int paramInt3, boolean paramBoolean2, String paramString1, int paramInt4, String paramString2, ConverterWrapper paramConverterWrapper)
  {
    mVersionCode = paramInt1;
    zt = paramInt2;
    zu = paramBoolean1;
    zv = paramInt3;
    zw = paramBoolean2;
    zx = paramString1;
    zy = paramInt4;
    if (paramString2 == null) {
      zz = null;
    }
    for (zA = null; paramConverterWrapper == null; zA = paramString2)
    {
      zC = null;
      return;
      zz = SafeParcelResponse.class;
    }
    zC = paramConverterWrapper.zzatn();
  }
  
  protected FastJsonResponse$Field(int paramInt1, boolean paramBoolean1, int paramInt2, boolean paramBoolean2, String paramString, int paramInt3, Class<? extends FastJsonResponse> paramClass, FastJsonResponse.zza<I, O> paramzza)
  {
    mVersionCode = 1;
    zt = paramInt1;
    zu = paramBoolean1;
    zv = paramInt2;
    zw = paramBoolean2;
    zx = paramString;
    zy = paramInt3;
    zz = paramClass;
    if (paramClass == null) {}
    for (zA = null;; zA = paramClass.getCanonicalName())
    {
      zC = paramzza;
      return;
    }
  }
  
  public static Field zza(String paramString, int paramInt, FastJsonResponse.zza<?, ?> paramzza, boolean paramBoolean)
  {
    return new Field(paramzza.zzatp(), paramBoolean, paramzza.zzatq(), false, paramString, paramInt, null, paramzza);
  }
  
  public static <T extends FastJsonResponse> Field<T, T> zza(String paramString, int paramInt, Class<T> paramClass)
  {
    return new Field(11, false, 11, false, paramString, paramInt, paramClass, null);
  }
  
  public static <T extends FastJsonResponse> Field<ArrayList<T>, ArrayList<T>> zzb(String paramString, int paramInt, Class<T> paramClass)
  {
    return new Field(11, true, 11, true, paramString, paramInt, paramClass, null);
  }
  
  public static Field<Integer, Integer> zzj(String paramString, int paramInt)
  {
    return new Field(0, false, 0, false, paramString, paramInt, null, null);
  }
  
  public static Field<Boolean, Boolean> zzk(String paramString, int paramInt)
  {
    return new Field(6, false, 6, false, paramString, paramInt, null, null);
  }
  
  public static Field<String, String> zzl(String paramString, int paramInt)
  {
    return new Field(7, false, 7, false, paramString, paramInt, null, null);
  }
  
  public I convertBack(O paramO)
  {
    return (I)zC.convertBack(paramO);
  }
  
  public int getVersionCode()
  {
    return mVersionCode;
  }
  
  public String toString()
  {
    StringBuilder localStringBuilder1 = new StringBuilder();
    localStringBuilder1.append("Field\n");
    localStringBuilder1.append("            versionCode=").append(mVersionCode).append('\n');
    localStringBuilder1.append("                 typeIn=").append(zt).append('\n');
    localStringBuilder1.append("            typeInArray=").append(zu).append('\n');
    localStringBuilder1.append("                typeOut=").append(zv).append('\n');
    localStringBuilder1.append("           typeOutArray=").append(zw).append('\n');
    localStringBuilder1.append("        outputFieldName=").append(zx).append('\n');
    localStringBuilder1.append("      safeParcelFieldId=").append(zy).append('\n');
    localStringBuilder1.append("       concreteTypeName=").append(zzatz()).append('\n');
    if (zzaty() != null) {
      localStringBuilder1.append("     concreteType.class=").append(zzaty().getCanonicalName()).append('\n');
    }
    StringBuilder localStringBuilder2 = localStringBuilder1.append("          converterName=");
    if (zC == null) {}
    for (String str = "null";; str = zC.getClass().getCanonicalName())
    {
      localStringBuilder2.append(str).append('\n');
      return localStringBuilder1.toString();
    }
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    zza localzza = CREATOR;
    zza.zza(this, paramParcel, paramInt);
  }
  
  public void zza(FieldMappingDictionary paramFieldMappingDictionary)
  {
    zB = paramFieldMappingDictionary;
  }
  
  public int zzatp()
  {
    return zt;
  }
  
  public int zzatq()
  {
    return zv;
  }
  
  public boolean zzatu()
  {
    return zu;
  }
  
  public boolean zzatv()
  {
    return zw;
  }
  
  public String zzatw()
  {
    return zx;
  }
  
  public int zzatx()
  {
    return zy;
  }
  
  public Class<? extends FastJsonResponse> zzaty()
  {
    return zz;
  }
  
  String zzatz()
  {
    if (zA == null) {
      return null;
    }
    return zA;
  }
  
  public boolean zzaua()
  {
    return zC != null;
  }
  
  ConverterWrapper zzaub()
  {
    if (zC == null) {
      return null;
    }
    return ConverterWrapper.zza(zC);
  }
  
  public Map<String, Field<?, ?>> zzauc()
  {
    zzab.zzaa(zA);
    zzab.zzaa(zB);
    return zB.zzhx(zA);
  }
}

/* Location:
 * Qualified Name:     com.google.android.gms.common.server.response.FastJsonResponse.Field
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */