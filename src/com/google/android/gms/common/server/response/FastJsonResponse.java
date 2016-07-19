package com.google.android.gms.common.server.response;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.zzab;
import com.google.android.gms.common.server.converter.ConverterWrapper;
import com.google.android.gms.common.util.zzc;
import com.google.android.gms.common.util.zzp;
import com.google.android.gms.common.util.zzq;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public abstract class FastJsonResponse
{
  private void zza(StringBuilder paramStringBuilder, Field paramField, Object paramObject)
  {
    if (paramField.zzatp() == 11)
    {
      paramStringBuilder.append(((FastJsonResponse)paramField.zzaty().cast(paramObject)).toString());
      return;
    }
    if (paramField.zzatp() == 7)
    {
      paramStringBuilder.append("\"");
      paramStringBuilder.append(zzp.zzib((String)paramObject));
      paramStringBuilder.append("\"");
      return;
    }
    paramStringBuilder.append(paramObject);
  }
  
  private void zza(StringBuilder paramStringBuilder, Field paramField, ArrayList<Object> paramArrayList)
  {
    paramStringBuilder.append("[");
    int i = 0;
    int j = paramArrayList.size();
    while (i < j)
    {
      if (i > 0) {
        paramStringBuilder.append(",");
      }
      Object localObject = paramArrayList.get(i);
      if (localObject != null) {
        zza(paramStringBuilder, paramField, localObject);
      }
      i += 1;
    }
    paramStringBuilder.append("]");
  }
  
  public String toString()
  {
    Map localMap = zzatr();
    StringBuilder localStringBuilder = new StringBuilder(100);
    Iterator localIterator = localMap.keySet().iterator();
    while (localIterator.hasNext())
    {
      String str = (String)localIterator.next();
      Field localField = (Field)localMap.get(str);
      if (zza(localField))
      {
        Object localObject = zza(localField, zzb(localField));
        if (localStringBuilder.length() == 0) {
          localStringBuilder.append("{");
        }
        for (;;)
        {
          localStringBuilder.append("\"").append(str).append("\":");
          if (localObject != null) {
            break label139;
          }
          localStringBuilder.append("null");
          break;
          localStringBuilder.append(",");
        }
        label139:
        switch (localField.zzatq())
        {
        default: 
          if (localField.zzatu()) {
            zza(localStringBuilder, localField, (ArrayList)localObject);
          }
          break;
        case 8: 
          localStringBuilder.append("\"").append(zzc.zzp((byte[])localObject)).append("\"");
          break;
        case 9: 
          localStringBuilder.append("\"").append(zzc.zzq((byte[])localObject)).append("\"");
          break;
        case 10: 
          zzq.zza(localStringBuilder, (HashMap)localObject);
          continue;
          zza(localStringBuilder, localField, localObject);
        }
      }
    }
    if (localStringBuilder.length() > 0) {
      localStringBuilder.append("}");
    }
    for (;;)
    {
      return localStringBuilder.toString();
      localStringBuilder.append("{}");
    }
  }
  
  protected <O, I> I zza(Field<I, O> paramField, Object paramObject)
  {
    Object localObject = paramObject;
    if (Field.zzc(paramField) != null) {
      localObject = paramField.convertBack(paramObject);
    }
    return (I)localObject;
  }
  
  protected boolean zza(Field paramField)
  {
    if (paramField.zzatq() == 11)
    {
      if (paramField.zzatv()) {
        return zzhw(paramField.zzatw());
      }
      return zzhv(paramField.zzatw());
    }
    return zzhu(paramField.zzatw());
  }
  
  public abstract Map<String, Field<?, ?>> zzatr();
  
  public HashMap<String, Object> zzats()
  {
    return null;
  }
  
  public HashMap<String, Object> zzatt()
  {
    return null;
  }
  
  protected Object zzb(Field paramField)
  {
    String str = paramField.zzatw();
    if (paramField.zzaty() != null)
    {
      boolean bool;
      if (zzht(paramField.zzatw()) == null)
      {
        bool = true;
        zzab.zza(bool, "Concrete field shouldn't be value object: %s", new Object[] { paramField.zzatw() });
        if (!paramField.zzatv()) {
          break label73;
        }
      }
      label73:
      for (paramField = zzatt();; paramField = zzats())
      {
        if (paramField == null) {
          break label81;
        }
        return paramField.get(str);
        bool = false;
        break;
      }
      try
      {
        label81:
        char c = Character.toUpperCase(str.charAt(0));
        paramField = String.valueOf(str.substring(1));
        paramField = String.valueOf(paramField).length() + 4 + "get" + c + paramField;
        paramField = getClass().getMethod(paramField, new Class[0]).invoke(this, new Object[0]);
        return paramField;
      }
      catch (Exception paramField)
      {
        throw new RuntimeException(paramField);
      }
    }
    return zzht(paramField.zzatw());
  }
  
  protected abstract Object zzht(String paramString);
  
  protected abstract boolean zzhu(String paramString);
  
  protected boolean zzhv(String paramString)
  {
    throw new UnsupportedOperationException("Concrete types not supported");
  }
  
  protected boolean zzhw(String paramString)
  {
    throw new UnsupportedOperationException("Concrete type arrays not supported");
  }
  
  public static class Field<I, O>
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
    
    Field(int paramInt1, int paramInt2, boolean paramBoolean1, int paramInt3, boolean paramBoolean2, String paramString1, int paramInt4, String paramString2, ConverterWrapper paramConverterWrapper)
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
    
    protected Field(int paramInt1, boolean paramBoolean1, int paramInt2, boolean paramBoolean2, String paramString, int paramInt3, Class<? extends FastJsonResponse> paramClass, FastJsonResponse.zza<I, O> paramzza)
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
  
  public static abstract interface zza<I, O>
  {
    public abstract I convertBack(O paramO);
    
    public abstract int zzatp();
    
    public abstract int zzatq();
  }
}

/* Location:
 * Qualified Name:     com.google.android.gms.common.server.response.FastJsonResponse
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */