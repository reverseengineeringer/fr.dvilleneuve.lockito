package com.google.android.gms.internal;

import java.io.IOException;
import java.io.Writer;

final class zzanz$zza
  extends Writer
{
  private final Appendable bfx;
  private final zza bfy = new zza();
  
  private zzanz$zza(Appendable paramAppendable)
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

/* Location:
 * Qualified Name:     com.google.android.gms.internal.zzanz.zza
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */