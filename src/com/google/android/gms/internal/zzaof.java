package com.google.android.gms.internal;

import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

public final class zzaof
  extends zzaop
{
  private static final Reader bfJ = new Reader()
  {
    public void close()
      throws IOException
    {
      throw new AssertionError();
    }
    
    public int read(char[] paramAnonymousArrayOfChar, int paramAnonymousInt1, int paramAnonymousInt2)
      throws IOException
    {
      throw new AssertionError();
    }
  };
  private static final Object bfK = new Object();
  private final List<Object> bfL = new ArrayList();
  
  public zzaof(zzamy paramzzamy)
  {
    super(bfJ);
    bfL.add(paramzzamy);
  }
  
  private Object i()
  {
    return bfL.get(bfL.size() - 1);
  }
  
  private Object j()
  {
    return bfL.remove(bfL.size() - 1);
  }
  
  private void zza(zzaoq paramzzaoq)
    throws IOException
  {
    if (h() != paramzzaoq)
    {
      paramzzaoq = String.valueOf(paramzzaoq);
      String str = String.valueOf(h());
      throw new IllegalStateException(String.valueOf(paramzzaoq).length() + 18 + String.valueOf(str).length() + "Expected " + paramzzaoq + " but was " + str);
    }
  }
  
  public void beginArray()
    throws IOException
  {
    zza(zzaoq.bhz);
    zzamv localzzamv = (zzamv)i();
    bfL.add(localzzamv.iterator());
  }
  
  public void beginObject()
    throws IOException
  {
    zza(zzaoq.bhB);
    zzanb localzzanb = (zzanb)i();
    bfL.add(localzzanb.entrySet().iterator());
  }
  
  public void close()
    throws IOException
  {
    bfL.clear();
    bfL.add(bfK);
  }
  
  public void endArray()
    throws IOException
  {
    zza(zzaoq.bhA);
    j();
    j();
  }
  
  public void endObject()
    throws IOException
  {
    zza(zzaoq.bhC);
    j();
    j();
  }
  
  public zzaoq h()
    throws IOException
  {
    if (bfL.isEmpty()) {
      return zzaoq.bhI;
    }
    Object localObject = i();
    if ((localObject instanceof Iterator))
    {
      boolean bool = bfL.get(bfL.size() - 2) instanceof zzanb;
      localObject = (Iterator)localObject;
      if (((Iterator)localObject).hasNext())
      {
        if (bool) {
          return zzaoq.bhD;
        }
        bfL.add(((Iterator)localObject).next());
        return h();
      }
      if (bool) {
        return zzaoq.bhC;
      }
      return zzaoq.bhA;
    }
    if ((localObject instanceof zzanb)) {
      return zzaoq.bhB;
    }
    if ((localObject instanceof zzamv)) {
      return zzaoq.bhz;
    }
    if ((localObject instanceof zzane))
    {
      localObject = (zzane)localObject;
      if (((zzane)localObject).zzczw()) {
        return zzaoq.bhE;
      }
      if (((zzane)localObject).zzczu()) {
        return zzaoq.bhG;
      }
      if (((zzane)localObject).zzczv()) {
        return zzaoq.bhF;
      }
      throw new AssertionError();
    }
    if ((localObject instanceof zzana)) {
      return zzaoq.bhH;
    }
    if (localObject == bfK) {
      throw new IllegalStateException("JsonReader is closed");
    }
    throw new AssertionError();
  }
  
  public boolean hasNext()
    throws IOException
  {
    zzaoq localzzaoq = h();
    return (localzzaoq != zzaoq.bhC) && (localzzaoq != zzaoq.bhA);
  }
  
  public void k()
    throws IOException
  {
    zza(zzaoq.bhD);
    Map.Entry localEntry = (Map.Entry)((Iterator)i()).next();
    bfL.add(localEntry.getValue());
    bfL.add(new zzane((String)localEntry.getKey()));
  }
  
  public boolean nextBoolean()
    throws IOException
  {
    zza(zzaoq.bhG);
    return ((zzane)j()).zzczl();
  }
  
  public double nextDouble()
    throws IOException
  {
    Object localObject = h();
    if ((localObject != zzaoq.bhF) && (localObject != zzaoq.bhE))
    {
      String str = String.valueOf(zzaoq.bhF);
      localObject = String.valueOf(localObject);
      throw new IllegalStateException(String.valueOf(str).length() + 18 + String.valueOf(localObject).length() + "Expected " + str + " but was " + (String)localObject);
    }
    double d = ((zzane)i()).zzczi();
    if ((!isLenient()) && ((Double.isNaN(d)) || (Double.isInfinite(d)))) {
      throw new NumberFormatException(57 + "JSON forbids NaN and infinities: " + d);
    }
    j();
    return d;
  }
  
  public int nextInt()
    throws IOException
  {
    Object localObject = h();
    if ((localObject != zzaoq.bhF) && (localObject != zzaoq.bhE))
    {
      String str = String.valueOf(zzaoq.bhF);
      localObject = String.valueOf(localObject);
      throw new IllegalStateException(String.valueOf(str).length() + 18 + String.valueOf(localObject).length() + "Expected " + str + " but was " + (String)localObject);
    }
    int i = ((zzane)i()).zzczk();
    j();
    return i;
  }
  
  public long nextLong()
    throws IOException
  {
    Object localObject = h();
    if ((localObject != zzaoq.bhF) && (localObject != zzaoq.bhE))
    {
      String str = String.valueOf(zzaoq.bhF);
      localObject = String.valueOf(localObject);
      throw new IllegalStateException(String.valueOf(str).length() + 18 + String.valueOf(localObject).length() + "Expected " + str + " but was " + (String)localObject);
    }
    long l = ((zzane)i()).zzczj();
    j();
    return l;
  }
  
  public String nextName()
    throws IOException
  {
    zza(zzaoq.bhD);
    Map.Entry localEntry = (Map.Entry)((Iterator)i()).next();
    bfL.add(localEntry.getValue());
    return (String)localEntry.getKey();
  }
  
  public void nextNull()
    throws IOException
  {
    zza(zzaoq.bhH);
    j();
  }
  
  public String nextString()
    throws IOException
  {
    Object localObject = h();
    if ((localObject != zzaoq.bhE) && (localObject != zzaoq.bhF))
    {
      String str = String.valueOf(zzaoq.bhE);
      localObject = String.valueOf(localObject);
      throw new IllegalStateException(String.valueOf(str).length() + 18 + String.valueOf(localObject).length() + "Expected " + str + " but was " + (String)localObject);
    }
    return ((zzane)j()).zzczh();
  }
  
  public void skipValue()
    throws IOException
  {
    if (h() == zzaoq.bhD)
    {
      nextName();
      return;
    }
    j();
  }
  
  public String toString()
  {
    return getClass().getSimpleName();
  }
}

/* Location:
 * Qualified Name:     com.google.android.gms.internal.zzaof
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */