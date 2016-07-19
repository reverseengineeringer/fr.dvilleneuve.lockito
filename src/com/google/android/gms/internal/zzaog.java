package com.google.android.gms.internal;

import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public final class zzaog
  extends zzaor
{
  private static final Writer bfM = new Writer()
  {
    public void close()
      throws IOException
    {
      throw new AssertionError();
    }
    
    public void flush()
      throws IOException
    {
      throw new AssertionError();
    }
    
    public void write(char[] paramAnonymousArrayOfChar, int paramAnonymousInt1, int paramAnonymousInt2)
    {
      throw new AssertionError();
    }
  };
  private static final zzane bfN = new zzane("closed");
  private final List<zzamy> bfL = new ArrayList();
  private String bfO;
  private zzamy bfP = zzana.bes;
  
  public zzaog()
  {
    super(bfM);
  }
  
  private zzamy m()
  {
    return (zzamy)bfL.get(bfL.size() - 1);
  }
  
  private void zzd(zzamy paramzzamy)
  {
    if (bfO != null)
    {
      if ((!paramzzamy.zzczp()) || (E())) {
        ((zzanb)m()).zza(bfO, paramzzamy);
      }
      bfO = null;
      return;
    }
    if (bfL.isEmpty())
    {
      bfP = paramzzamy;
      return;
    }
    zzamy localzzamy = m();
    if ((localzzamy instanceof zzamv))
    {
      ((zzamv)localzzamy).zzc(paramzzamy);
      return;
    }
    throw new IllegalStateException();
  }
  
  public void close()
    throws IOException
  {
    if (!bfL.isEmpty()) {
      throw new IOException("Incomplete document");
    }
    bfL.add(bfN);
  }
  
  public void flush()
    throws IOException
  {}
  
  public zzamy l()
  {
    if (!bfL.isEmpty())
    {
      String str = String.valueOf(bfL);
      throw new IllegalStateException(String.valueOf(str).length() + 34 + "Expected one JSON element but was " + str);
    }
    return bfP;
  }
  
  public zzaor n()
    throws IOException
  {
    zzamv localzzamv = new zzamv();
    zzd(localzzamv);
    bfL.add(localzzamv);
    return this;
  }
  
  public zzaor o()
    throws IOException
  {
    if ((bfL.isEmpty()) || (bfO != null)) {
      throw new IllegalStateException();
    }
    if ((m() instanceof zzamv))
    {
      bfL.remove(bfL.size() - 1);
      return this;
    }
    throw new IllegalStateException();
  }
  
  public zzaor p()
    throws IOException
  {
    zzanb localzzanb = new zzanb();
    zzd(localzzanb);
    bfL.add(localzzanb);
    return this;
  }
  
  public zzaor q()
    throws IOException
  {
    if ((bfL.isEmpty()) || (bfO != null)) {
      throw new IllegalStateException();
    }
    if ((m() instanceof zzanb))
    {
      bfL.remove(bfL.size() - 1);
      return this;
    }
    throw new IllegalStateException();
  }
  
  public zzaor r()
    throws IOException
  {
    zzd(zzana.bes);
    return this;
  }
  
  public zzaor zza(Number paramNumber)
    throws IOException
  {
    if (paramNumber == null) {
      return r();
    }
    if (!isLenient())
    {
      double d = paramNumber.doubleValue();
      if ((Double.isNaN(d)) || (Double.isInfinite(d)))
      {
        paramNumber = String.valueOf(paramNumber);
        throw new IllegalArgumentException(String.valueOf(paramNumber).length() + 33 + "JSON forbids NaN and infinities: " + paramNumber);
      }
    }
    zzd(new zzane(paramNumber));
    return this;
  }
  
  public zzaor zzcp(long paramLong)
    throws IOException
  {
    zzd(new zzane(Long.valueOf(paramLong)));
    return this;
  }
  
  public zzaor zzcz(boolean paramBoolean)
    throws IOException
  {
    zzd(new zzane(Boolean.valueOf(paramBoolean)));
    return this;
  }
  
  public zzaor zzta(String paramString)
    throws IOException
  {
    if ((bfL.isEmpty()) || (bfO != null)) {
      throw new IllegalStateException();
    }
    if ((m() instanceof zzanb))
    {
      bfO = paramString;
      return this;
    }
    throw new IllegalStateException();
  }
  
  public zzaor zztb(String paramString)
    throws IOException
  {
    if (paramString == null) {
      return r();
    }
    zzd(new zzane(paramString));
    return this;
  }
}

/* Location:
 * Qualified Name:     com.google.android.gms.internal.zzaog
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */