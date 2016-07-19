package com.google.android.gms.internal;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public final class zzams
{
  private final ThreadLocal<Map<zzaoo<?>, zza<?>>> bdY = new ThreadLocal();
  private final Map<zzaoo<?>, zzank<?>> bdZ = Collections.synchronizedMap(new HashMap());
  private final List<zzanl> bea;
  private final zzans beb;
  private final boolean bec;
  private final boolean bed;
  private final boolean bee;
  private final boolean bef;
  final zzamw beg = new zzamw() {};
  final zzanf beh = new zzanf() {};
  
  public zzams()
  {
    this(zzant.beU, zzamq.bdS, Collections.emptyMap(), false, false, false, true, false, false, zzani.bev, Collections.emptyList());
  }
  
  zzams(zzant paramzzant, zzamr paramzzamr, Map<Type, zzamu<?>> paramMap, boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3, boolean paramBoolean4, boolean paramBoolean5, boolean paramBoolean6, zzani paramzzani, List<zzanl> paramList)
  {
    beb = new zzans(paramMap);
    bec = paramBoolean1;
    bee = paramBoolean3;
    bed = paramBoolean4;
    bef = paramBoolean5;
    paramMap = new ArrayList();
    paramMap.add(zzaon.bgX);
    paramMap.add(zzaoi.bfE);
    paramMap.add(paramzzant);
    paramMap.addAll(paramList);
    paramMap.add(zzaon.bgE);
    paramMap.add(zzaon.bgt);
    paramMap.add(zzaon.bgn);
    paramMap.add(zzaon.bgp);
    paramMap.add(zzaon.bgr);
    paramMap.add(zzaon.zza(Long.TYPE, Long.class, zza(paramzzani)));
    paramMap.add(zzaon.zza(Double.TYPE, Double.class, zzcx(paramBoolean6)));
    paramMap.add(zzaon.zza(Float.TYPE, Float.class, zzcy(paramBoolean6)));
    paramMap.add(zzaon.bgy);
    paramMap.add(zzaon.bgA);
    paramMap.add(zzaon.bgG);
    paramMap.add(zzaon.bgI);
    paramMap.add(zzaon.zza(BigDecimal.class, zzaon.bgC));
    paramMap.add(zzaon.zza(BigInteger.class, zzaon.bgD));
    paramMap.add(zzaon.bgK);
    paramMap.add(zzaon.bgM);
    paramMap.add(zzaon.bgQ);
    paramMap.add(zzaon.bgV);
    paramMap.add(zzaon.bgO);
    paramMap.add(zzaon.bgk);
    paramMap.add(zzaod.bfE);
    paramMap.add(zzaon.bgT);
    paramMap.add(zzaol.bfE);
    paramMap.add(zzaok.bfE);
    paramMap.add(zzaon.bgR);
    paramMap.add(zzaob.bfE);
    paramMap.add(zzaon.bgi);
    paramMap.add(new zzaoc(beb));
    paramMap.add(new zzaoh(beb, paramBoolean2));
    paramMap.add(new zzaoe(beb));
    paramMap.add(zzaon.bgY);
    paramMap.add(new zzaoj(beb, paramzzamr, paramzzant));
    bea = Collections.unmodifiableList(paramMap);
  }
  
  private zzank<Number> zza(zzani paramzzani)
  {
    if (paramzzani == zzani.bev) {
      return zzaon.bgu;
    }
    new zzank()
    {
      public void zza(zzaor paramAnonymouszzaor, Number paramAnonymousNumber)
        throws IOException
      {
        if (paramAnonymousNumber == null)
        {
          paramAnonymouszzaor.r();
          return;
        }
        paramAnonymouszzaor.zztb(paramAnonymousNumber.toString());
      }
      
      public Number zzg(zzaop paramAnonymouszzaop)
        throws IOException
      {
        if (paramAnonymouszzaop.h() == zzaoq.bhH)
        {
          paramAnonymouszzaop.nextNull();
          return null;
        }
        return Long.valueOf(paramAnonymouszzaop.nextLong());
      }
    };
  }
  
  private static void zza(Object paramObject, zzaop paramzzaop)
  {
    if (paramObject != null) {
      try
      {
        if (paramzzaop.h() != zzaoq.bhI) {
          throw new zzamz("JSON document was not fully consumed.");
        }
      }
      catch (zzaos paramObject)
      {
        throw new zzanh((Throwable)paramObject);
      }
      catch (IOException paramObject)
      {
        throw new zzamz((Throwable)paramObject);
      }
    }
  }
  
  private zzank<Number> zzcx(boolean paramBoolean)
  {
    if (paramBoolean) {
      return zzaon.bgw;
    }
    new zzank()
    {
      public void zza(zzaor paramAnonymouszzaor, Number paramAnonymousNumber)
        throws IOException
      {
        if (paramAnonymousNumber == null)
        {
          paramAnonymouszzaor.r();
          return;
        }
        double d = paramAnonymousNumber.doubleValue();
        zzams.zza(zzams.this, d);
        paramAnonymouszzaor.zza(paramAnonymousNumber);
      }
      
      public Double zze(zzaop paramAnonymouszzaop)
        throws IOException
      {
        if (paramAnonymouszzaop.h() == zzaoq.bhH)
        {
          paramAnonymouszzaop.nextNull();
          return null;
        }
        return Double.valueOf(paramAnonymouszzaop.nextDouble());
      }
    };
  }
  
  private zzank<Number> zzcy(boolean paramBoolean)
  {
    if (paramBoolean) {
      return zzaon.bgv;
    }
    new zzank()
    {
      public void zza(zzaor paramAnonymouszzaor, Number paramAnonymousNumber)
        throws IOException
      {
        if (paramAnonymousNumber == null)
        {
          paramAnonymouszzaor.r();
          return;
        }
        float f = paramAnonymousNumber.floatValue();
        zzams.zza(zzams.this, f);
        paramAnonymouszzaor.zza(paramAnonymousNumber);
      }
      
      public Float zzf(zzaop paramAnonymouszzaop)
        throws IOException
      {
        if (paramAnonymouszzaop.h() == zzaoq.bhH)
        {
          paramAnonymouszzaop.nextNull();
          return null;
        }
        return Float.valueOf((float)paramAnonymouszzaop.nextDouble());
      }
    };
  }
  
  private void zzn(double paramDouble)
  {
    if ((Double.isNaN(paramDouble)) || (Double.isInfinite(paramDouble))) {
      throw new IllegalArgumentException(168 + paramDouble + " is not a valid double value as per JSON specification. To override this" + " behavior, use GsonBuilder.serializeSpecialFloatingPointValues() method.");
    }
  }
  
  public String toString()
  {
    return "{serializeNulls:" + bec + "factories:" + bea + ",instanceCreators:" + beb + "}";
  }
  
  public <T> zzank<T> zza(zzanl paramzzanl, zzaoo<T> paramzzaoo)
  {
    int i = 0;
    if (!bea.contains(paramzzanl)) {
      i = 1;
    }
    Iterator localIterator = bea.iterator();
    while (localIterator.hasNext())
    {
      Object localObject = (zzanl)localIterator.next();
      if (i == 0)
      {
        if (localObject == paramzzanl) {
          i = 1;
        }
      }
      else
      {
        localObject = ((zzanl)localObject).zza(this, paramzzaoo);
        if (localObject != null) {
          return (zzank<T>)localObject;
        }
      }
    }
    paramzzanl = String.valueOf(paramzzaoo);
    throw new IllegalArgumentException(String.valueOf(paramzzanl).length() + 22 + "GSON cannot serialize " + paramzzanl);
  }
  
  public <T> zzank<T> zza(zzaoo<T> paramzzaoo)
  {
    Object localObject1 = (zzank)bdZ.get(paramzzaoo);
    if (localObject1 != null) {
      return (zzank<T>)localObject1;
    }
    Object localObject3 = (Map)bdY.get();
    int i = 0;
    if (localObject3 == null)
    {
      localObject3 = new HashMap();
      bdY.set(localObject3);
      i = 1;
    }
    for (;;)
    {
      Object localObject4 = (zza)((Map)localObject3).get(paramzzaoo);
      localObject1 = localObject4;
      if (localObject4 != null) {
        break;
      }
      try
      {
        localObject1 = new zza();
        ((Map)localObject3).put(paramzzaoo, localObject1);
        Iterator localIterator = bea.iterator();
        for (;;)
        {
          if (localIterator.hasNext())
          {
            localObject4 = ((zzanl)localIterator.next()).zza(this, paramzzaoo);
            if (localObject4 != null)
            {
              ((zza)localObject1).zza((zzank)localObject4);
              bdZ.put(paramzzaoo, localObject4);
              ((Map)localObject3).remove(paramzzaoo);
              localObject1 = localObject4;
              if (i == 0) {
                break;
              }
              bdY.remove();
              return (zzank<T>)localObject4;
            }
          }
        }
        localObject1 = String.valueOf(paramzzaoo);
        throw new IllegalArgumentException(String.valueOf(localObject1).length() + 19 + "GSON cannot handle " + (String)localObject1);
      }
      finally
      {
        ((Map)localObject3).remove(paramzzaoo);
        if (i != 0) {
          bdY.remove();
        }
      }
    }
  }
  
  public zzaor zza(Writer paramWriter)
    throws IOException
  {
    if (bee) {
      paramWriter.write(")]}'\n");
    }
    paramWriter = new zzaor(paramWriter);
    if (bef) {
      paramWriter.setIndent("  ");
    }
    paramWriter.zzdc(bec);
    return paramWriter;
  }
  
  public <T> T zza(zzamy paramzzamy, Class<T> paramClass)
    throws zzanh
  {
    paramzzamy = zza(paramzzamy, paramClass);
    return (T)zzany.zzp(paramClass).cast(paramzzamy);
  }
  
  public <T> T zza(zzamy paramzzamy, Type paramType)
    throws zzanh
  {
    if (paramzzamy == null) {
      return null;
    }
    return (T)zza(new zzaof(paramzzamy), paramType);
  }
  
  /* Error */
  public <T> T zza(zzaop paramzzaop, Type paramType)
    throws zzamz, zzanh
  {
    // Byte code:
    //   0: iconst_1
    //   1: istore_3
    //   2: aload_1
    //   3: invokevirtual 484	com/google/android/gms/internal/zzaop:isLenient	()Z
    //   6: istore 4
    //   8: aload_1
    //   9: iconst_1
    //   10: invokevirtual 487	com/google/android/gms/internal/zzaop:setLenient	(Z)V
    //   13: aload_1
    //   14: invokevirtual 289	com/google/android/gms/internal/zzaop:h	()Lcom/google/android/gms/internal/zzaoq;
    //   17: pop
    //   18: iconst_0
    //   19: istore_3
    //   20: aload_0
    //   21: aload_2
    //   22: invokestatic 493	com/google/android/gms/internal/zzaoo:zzl	(Ljava/lang/reflect/Type;)Lcom/google/android/gms/internal/zzaoo;
    //   25: invokevirtual 495	com/google/android/gms/internal/zzams:zza	(Lcom/google/android/gms/internal/zzaoo;)Lcom/google/android/gms/internal/zzank;
    //   28: aload_1
    //   29: invokevirtual 499	com/google/android/gms/internal/zzank:zzb	(Lcom/google/android/gms/internal/zzaop;)Ljava/lang/Object;
    //   32: astore_2
    //   33: aload_1
    //   34: iload 4
    //   36: invokevirtual 487	com/google/android/gms/internal/zzaop:setLenient	(Z)V
    //   39: aload_2
    //   40: areturn
    //   41: astore_2
    //   42: iload_3
    //   43: ifeq +11 -> 54
    //   46: aload_1
    //   47: iload 4
    //   49: invokevirtual 487	com/google/android/gms/internal/zzaop:setLenient	(Z)V
    //   52: aconst_null
    //   53: areturn
    //   54: new 304	com/google/android/gms/internal/zzanh
    //   57: dup
    //   58: aload_2
    //   59: invokespecial 307	com/google/android/gms/internal/zzanh:<init>	(Ljava/lang/Throwable;)V
    //   62: athrow
    //   63: astore_2
    //   64: aload_1
    //   65: iload 4
    //   67: invokevirtual 487	com/google/android/gms/internal/zzaop:setLenient	(Z)V
    //   70: aload_2
    //   71: athrow
    //   72: astore_2
    //   73: new 304	com/google/android/gms/internal/zzanh
    //   76: dup
    //   77: aload_2
    //   78: invokespecial 307	com/google/android/gms/internal/zzanh:<init>	(Ljava/lang/Throwable;)V
    //   81: athrow
    //   82: astore_2
    //   83: new 304	com/google/android/gms/internal/zzanh
    //   86: dup
    //   87: aload_2
    //   88: invokespecial 307	com/google/android/gms/internal/zzanh:<init>	(Ljava/lang/Throwable;)V
    //   91: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	92	0	this	zzams
    //   0	92	1	paramzzaop	zzaop
    //   0	92	2	paramType	Type
    //   1	42	3	i	int
    //   6	60	4	bool	boolean
    // Exception table:
    //   from	to	target	type
    //   13	18	41	java/io/EOFException
    //   20	33	41	java/io/EOFException
    //   13	18	63	finally
    //   20	33	63	finally
    //   54	63	63	finally
    //   73	82	63	finally
    //   83	92	63	finally
    //   13	18	72	java/lang/IllegalStateException
    //   20	33	72	java/lang/IllegalStateException
    //   13	18	82	java/io/IOException
    //   20	33	82	java/io/IOException
  }
  
  public <T> T zza(Reader paramReader, Type paramType)
    throws zzamz, zzanh
  {
    paramReader = new zzaop(paramReader);
    paramType = zza(paramReader, paramType);
    zza(paramType, paramReader);
    return paramType;
  }
  
  public <T> T zza(String paramString, Type paramType)
    throws zzanh
  {
    if (paramString == null) {
      return null;
    }
    return (T)zza(new StringReader(paramString), paramType);
  }
  
  public void zza(zzamy paramzzamy, zzaor paramzzaor)
    throws zzamz
  {
    boolean bool1 = paramzzaor.isLenient();
    paramzzaor.setLenient(true);
    boolean bool2 = paramzzaor.D();
    paramzzaor.zzdb(bed);
    boolean bool3 = paramzzaor.E();
    paramzzaor.zzdc(bec);
    try
    {
      zzanz.zzb(paramzzamy, paramzzaor);
      return;
    }
    catch (IOException paramzzamy)
    {
      throw new zzamz(paramzzamy);
    }
    finally
    {
      paramzzaor.setLenient(bool1);
      paramzzaor.zzdb(bool2);
      paramzzaor.zzdc(bool3);
    }
  }
  
  public void zza(zzamy paramzzamy, Appendable paramAppendable)
    throws zzamz
  {
    try
    {
      zza(paramzzamy, zza(zzanz.zza(paramAppendable)));
      return;
    }
    catch (IOException paramzzamy)
    {
      throw new RuntimeException(paramzzamy);
    }
  }
  
  public void zza(Object paramObject, Type paramType, zzaor paramzzaor)
    throws zzamz
  {
    paramType = zza(zzaoo.zzl(paramType));
    boolean bool1 = paramzzaor.isLenient();
    paramzzaor.setLenient(true);
    boolean bool2 = paramzzaor.D();
    paramzzaor.zzdb(bed);
    boolean bool3 = paramzzaor.E();
    paramzzaor.zzdc(bec);
    try
    {
      paramType.zza(paramzzaor, paramObject);
      return;
    }
    catch (IOException paramObject)
    {
      throw new zzamz((Throwable)paramObject);
    }
    finally
    {
      paramzzaor.setLenient(bool1);
      paramzzaor.zzdb(bool2);
      paramzzaor.zzdc(bool3);
    }
  }
  
  public void zza(Object paramObject, Type paramType, Appendable paramAppendable)
    throws zzamz
  {
    try
    {
      zza(paramObject, paramType, zza(zzanz.zza(paramAppendable)));
      return;
    }
    catch (IOException paramObject)
    {
      throw new zzamz((Throwable)paramObject);
    }
  }
  
  public String zzb(zzamy paramzzamy)
  {
    StringWriter localStringWriter = new StringWriter();
    zza(paramzzamy, localStringWriter);
    return localStringWriter.toString();
  }
  
  public String zzc(Object paramObject, Type paramType)
  {
    StringWriter localStringWriter = new StringWriter();
    zza(paramObject, paramType, localStringWriter);
    return localStringWriter.toString();
  }
  
  public String zzcj(Object paramObject)
  {
    if (paramObject == null) {
      return zzb(zzana.bes);
    }
    return zzc(paramObject, paramObject.getClass());
  }
  
  public <T> T zzf(String paramString, Class<T> paramClass)
    throws zzanh
  {
    paramString = zza(paramString, paramClass);
    return (T)zzany.zzp(paramClass).cast(paramString);
  }
  
  public <T> zzank<T> zzk(Class<T> paramClass)
  {
    return zza(zzaoo.zzr(paramClass));
  }
  
  static class zza<T>
    extends zzank<T>
  {
    private zzank<T> bej;
    
    public void zza(zzank<T> paramzzank)
    {
      if (bej != null) {
        throw new AssertionError();
      }
      bej = paramzzank;
    }
    
    public void zza(zzaor paramzzaor, T paramT)
      throws IOException
    {
      if (bej == null) {
        throw new IllegalStateException();
      }
      bej.zza(paramzzaor, paramT);
    }
    
    public T zzb(zzaop paramzzaop)
      throws IOException
    {
      if (bej == null) {
        throw new IllegalStateException();
      }
      return (T)bej.zzb(paramzzaop);
    }
  }
}

/* Location:
 * Qualified Name:     com.google.android.gms.internal.zzams
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */