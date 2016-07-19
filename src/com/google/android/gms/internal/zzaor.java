package com.google.android.gms.internal;

import java.io.Closeable;
import java.io.Flushable;
import java.io.IOException;
import java.io.Writer;

public class zzaor
  implements Closeable, Flushable
{
  private static final String[] bhK = new String[''];
  private static final String[] bhL;
  private boolean bec;
  private boolean bed;
  private String bhM;
  private String bhN;
  private boolean bhn;
  private int[] bhv = new int[32];
  private int bhw = 0;
  private final Writer out;
  private String separator;
  
  static
  {
    int i = 0;
    while (i <= 31)
    {
      bhK[i] = String.format("\\u%04x", new Object[] { Integer.valueOf(i) });
      i += 1;
    }
    bhK[34] = "\\\"";
    bhK[92] = "\\\\";
    bhK[9] = "\\t";
    bhK[8] = "\\b";
    bhK[10] = "\\n";
    bhK[13] = "\\r";
    bhK[12] = "\\f";
    bhL = (String[])bhK.clone();
    bhL[60] = "\\u003c";
    bhL[62] = "\\u003e";
    bhL[38] = "\\u0026";
    bhL[61] = "\\u003d";
    bhL[39] = "\\u0027";
  }
  
  public zzaor(Writer paramWriter)
  {
    zzaec(6);
    separator = ":";
    bec = true;
    if (paramWriter == null) {
      throw new NullPointerException("out == null");
    }
    out = paramWriter;
  }
  
  private int F()
  {
    if (bhw == 0) {
      throw new IllegalStateException("JsonWriter is closed.");
    }
    return bhv[(bhw - 1)];
  }
  
  private void G()
    throws IOException
  {
    if (bhN != null)
    {
      I();
      zzte(bhN);
      bhN = null;
    }
  }
  
  private void H()
    throws IOException
  {
    if (bhM == null) {}
    for (;;)
    {
      return;
      out.write("\n");
      int i = 1;
      int j = bhw;
      while (i < j)
      {
        out.write(bhM);
        i += 1;
      }
    }
  }
  
  private void I()
    throws IOException
  {
    int i = F();
    if (i == 5) {
      out.write(44);
    }
    while (i == 3)
    {
      H();
      zzaee(4);
      return;
    }
    throw new IllegalStateException("Nesting problem.");
  }
  
  private void zzaec(int paramInt)
  {
    if (bhw == bhv.length)
    {
      arrayOfInt = new int[bhw * 2];
      System.arraycopy(bhv, 0, arrayOfInt, 0, bhw);
      bhv = arrayOfInt;
    }
    int[] arrayOfInt = bhv;
    int i = bhw;
    bhw = (i + 1);
    arrayOfInt[i] = paramInt;
  }
  
  private void zzaee(int paramInt)
  {
    bhv[(bhw - 1)] = paramInt;
  }
  
  private zzaor zzc(int paramInt1, int paramInt2, String paramString)
    throws IOException
  {
    int i = F();
    if ((i != paramInt2) && (i != paramInt1)) {
      throw new IllegalStateException("Nesting problem.");
    }
    if (bhN != null)
    {
      paramString = String.valueOf(bhN);
      if (paramString.length() != 0) {}
      for (paramString = "Dangling name: ".concat(paramString);; paramString = new String("Dangling name: ")) {
        throw new IllegalStateException(paramString);
      }
    }
    bhw -= 1;
    if (i == paramInt2) {
      H();
    }
    out.write(paramString);
    return this;
  }
  
  private void zzdd(boolean paramBoolean)
    throws IOException
  {
    switch (F())
    {
    case 3: 
    case 5: 
    default: 
      throw new IllegalStateException("Nesting problem.");
    case 7: 
      if (!bhn) {
        throw new IllegalStateException("JSON must have only one top-level value.");
      }
    case 6: 
      if ((!bhn) && (!paramBoolean)) {
        throw new IllegalStateException("JSON must start with an array or an object.");
      }
      zzaee(7);
      return;
    case 1: 
      zzaee(2);
      H();
      return;
    case 2: 
      out.append(',');
      H();
      return;
    }
    out.append(separator);
    zzaee(5);
  }
  
  private zzaor zzq(int paramInt, String paramString)
    throws IOException
  {
    zzdd(true);
    zzaec(paramInt);
    out.write(paramString);
    return this;
  }
  
  private void zzte(String paramString)
    throws IOException
  {
    int j = 0;
    if (bed) {}
    int m;
    int i;
    int n;
    int k;
    for (String[] arrayOfString = bhL;; arrayOfString = bhK)
    {
      out.write("\"");
      m = paramString.length();
      i = 0;
      for (;;)
      {
        if (i >= m) {
          break label153;
        }
        n = paramString.charAt(i);
        if (n >= 128) {
          break;
        }
        String str2 = arrayOfString[n];
        str1 = str2;
        if (str2 != null) {
          break label101;
        }
        k = j;
        i += 1;
        j = k;
      }
    }
    if (n == 8232) {}
    for (String str1 = "\\u2028";; str1 = "\\u2029")
    {
      label101:
      if (j < i) {
        out.write(paramString, j, i - j);
      }
      out.write(str1);
      k = i + 1;
      break;
      k = j;
      if (n != 8233) {
        break;
      }
    }
    label153:
    if (j < m) {
      out.write(paramString, j, m - j);
    }
    out.write("\"");
  }
  
  public final boolean D()
  {
    return bed;
  }
  
  public final boolean E()
  {
    return bec;
  }
  
  public void close()
    throws IOException
  {
    out.close();
    int i = bhw;
    if ((i > 1) || ((i == 1) && (bhv[(i - 1)] != 7))) {
      throw new IOException("Incomplete document");
    }
    bhw = 0;
  }
  
  public void flush()
    throws IOException
  {
    if (bhw == 0) {
      throw new IllegalStateException("JsonWriter is closed.");
    }
    out.flush();
  }
  
  public boolean isLenient()
  {
    return bhn;
  }
  
  public zzaor n()
    throws IOException
  {
    G();
    return zzq(1, "[");
  }
  
  public zzaor o()
    throws IOException
  {
    return zzc(1, 2, "]");
  }
  
  public zzaor p()
    throws IOException
  {
    G();
    return zzq(3, "{");
  }
  
  public zzaor q()
    throws IOException
  {
    return zzc(3, 5, "}");
  }
  
  public zzaor r()
    throws IOException
  {
    if (bhN != null)
    {
      if (bec) {
        G();
      }
    }
    else
    {
      zzdd(false);
      out.write("null");
      return this;
    }
    bhN = null;
    return this;
  }
  
  public final void setIndent(String paramString)
  {
    if (paramString.length() == 0)
    {
      bhM = null;
      separator = ":";
      return;
    }
    bhM = paramString;
    separator = ": ";
  }
  
  public final void setLenient(boolean paramBoolean)
  {
    bhn = paramBoolean;
  }
  
  public zzaor zza(Number paramNumber)
    throws IOException
  {
    if (paramNumber == null) {
      return r();
    }
    G();
    String str = paramNumber.toString();
    if ((!bhn) && ((str.equals("-Infinity")) || (str.equals("Infinity")) || (str.equals("NaN"))))
    {
      paramNumber = String.valueOf(paramNumber);
      throw new IllegalArgumentException(String.valueOf(paramNumber).length() + 39 + "Numeric values must be finite, but was " + paramNumber);
    }
    zzdd(false);
    out.append(str);
    return this;
  }
  
  public zzaor zzcp(long paramLong)
    throws IOException
  {
    G();
    zzdd(false);
    out.write(Long.toString(paramLong));
    return this;
  }
  
  public zzaor zzcz(boolean paramBoolean)
    throws IOException
  {
    G();
    zzdd(false);
    Writer localWriter = out;
    if (paramBoolean) {}
    for (String str = "true";; str = "false")
    {
      localWriter.write(str);
      return this;
    }
  }
  
  public final void zzdb(boolean paramBoolean)
  {
    bed = paramBoolean;
  }
  
  public final void zzdc(boolean paramBoolean)
  {
    bec = paramBoolean;
  }
  
  public zzaor zzta(String paramString)
    throws IOException
  {
    if (paramString == null) {
      throw new NullPointerException("name == null");
    }
    if (bhN != null) {
      throw new IllegalStateException();
    }
    if (bhw == 0) {
      throw new IllegalStateException("JsonWriter is closed.");
    }
    bhN = paramString;
    return this;
  }
  
  public zzaor zztb(String paramString)
    throws IOException
  {
    if (paramString == null) {
      return r();
    }
    G();
    zzdd(false);
    zzte(paramString);
    return this;
  }
}

/* Location:
 * Qualified Name:     com.google.android.gms.internal.zzaor
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */