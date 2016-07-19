package com.google.android.gms.internal;

import java.io.EOFException;
import java.io.IOException;
import java.io.Writer;

public final class zzanz
{
  public static Writer zza(Appendable paramAppendable)
  {
    if ((paramAppendable instanceof Writer)) {
      return (Writer)paramAppendable;
    }
    return new zza(paramAppendable, null);
  }
  
  public static void zzb(zzamy paramzzamy, zzaor paramzzaor)
    throws IOException
  {
    zzaon.bgW.zza(paramzzaor, paramzzamy);
  }
  
  public static zzamy zzh(zzaop paramzzaop)
    throws zzanc
  {
    int i = 1;
    try
    {
      paramzzaop.h();
      i = 0;
      paramzzaop = (zzamy)zzaon.bgW.zzb(paramzzaop);
      return paramzzaop;
    }
    catch (EOFException paramzzaop)
    {
      if (i != 0) {
        return zzana.bes;
      }
      throw new zzanh(paramzzaop);
    }
    catch (zzaos paramzzaop)
    {
      throw new zzanh(paramzzaop);
    }
    catch (IOException paramzzaop)
    {
      throw new zzamz(paramzzaop);
    }
    catch (NumberFormatException paramzzaop)
    {
      throw new zzanh(paramzzaop);
    }
  }
  
  private static final class zza
    extends Writer
  {
    private final Appendable bfx;
    private final zza bfy = new zza();
    
    private zza(Appendable paramAppendable)
    {
      bfx = paramAppendable;
    }
    
    public void close() {}
    
    public void flush() {}
    
    public void write(int paramInt)
      throws IOException
    {
      bfx.append((char)paramInt);
    }
    
    public void write(char[] paramArrayOfChar, int paramInt1, int paramInt2)
      throws IOException
    {
      bfy.bfz = paramArrayOfChar;
      bfx.append(bfy, paramInt1, paramInt1 + paramInt2);
    }
    
    static class zza
      implements CharSequence
    {
      char[] bfz;
      
      public char charAt(int paramInt)
      {
        return bfz[paramInt];
      }
      
      public int length()
      {
        return bfz.length;
      }
      
      public CharSequence subSequence(int paramInt1, int paramInt2)
      {
        return new String(bfz, paramInt1, paramInt2 - paramInt1);
      }
    }
  }
}

/* Location:
 * Qualified Name:     com.google.android.gms.internal.zzanz
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */