package com.google.android.gms.internal;

import java.io.Closeable;
import java.io.EOFException;
import java.io.IOException;
import java.io.Reader;

public class zzaop
  implements Closeable
{
  private static final char[] bhm = ")]}'\n".toCharArray();
  private int aYn = 0;
  private boolean bhn = false;
  private final char[] bho = new char['Ѐ'];
  private int bhp = 0;
  private int bhq = 0;
  private int bhr = 0;
  private long bhs;
  private int bht;
  private String bhu;
  private int[] bhv = new int[32];
  private int bhw = 0;
  private String[] bhx;
  private int[] bhy;
  private final Reader in;
  private int pos = 0;
  
  static
  {
    zzanu.bff = new zzanu()
    {
      public void zzi(zzaop paramAnonymouszzaop)
        throws IOException
      {
        if ((paramAnonymouszzaop instanceof zzaof))
        {
          ((zzaof)paramAnonymouszzaop).k();
          return;
        }
        int j = zzaop.zzag(paramAnonymouszzaop);
        int i = j;
        if (j == 0) {
          i = zzaop.zzah(paramAnonymouszzaop);
        }
        if (i == 13)
        {
          zzaop.zza(paramAnonymouszzaop, 9);
          return;
        }
        if (i == 12)
        {
          zzaop.zza(paramAnonymouszzaop, 8);
          return;
        }
        if (i == 14)
        {
          zzaop.zza(paramAnonymouszzaop, 10);
          return;
        }
        String str = String.valueOf(paramAnonymouszzaop.h());
        i = zzaop.zzai(paramAnonymouszzaop);
        j = zzaop.zzaj(paramAnonymouszzaop);
        paramAnonymouszzaop = paramAnonymouszzaop.getPath();
        throw new IllegalStateException(String.valueOf(str).length() + 70 + String.valueOf(paramAnonymouszzaop).length() + "Expected a name but was " + str + " " + " at line " + i + " column " + j + " path " + paramAnonymouszzaop);
      }
    };
  }
  
  public zzaop(Reader paramReader)
  {
    int[] arrayOfInt = bhv;
    int i = bhw;
    bhw = (i + 1);
    arrayOfInt[i] = 6;
    bhx = new String[32];
    bhy = new int[32];
    if (paramReader == null) {
      throw new NullPointerException("in == null");
    }
    in = paramReader;
  }
  
  private void A()
    throws IOException
  {
    int i;
    do
    {
      if ((pos < aYn) || (zzaed(1)))
      {
        char[] arrayOfChar = bho;
        i = pos;
        pos = (i + 1);
        i = arrayOfChar[i];
        if (i == 10)
        {
          bhp += 1;
          bhq = pos;
        }
      }
      else
      {
        return;
      }
    } while (i != 13);
  }
  
  private char B()
    throws IOException
  {
    if ((pos == aYn) && (!zzaed(1))) {
      throw zztd("Unterminated escape sequence");
    }
    Object localObject = bho;
    int i = pos;
    pos = (i + 1);
    char c = localObject[i];
    switch (c)
    {
    default: 
      return c;
    case 'u': 
      if ((pos + 4 > aYn) && (!zzaed(4))) {
        throw zztd("Unterminated escape sequence");
      }
      int j = pos;
      c = '\000';
      i = j;
      if (i < j + 4)
      {
        int k = bho[i];
        int m = (char)(c << '\004');
        if ((k >= 48) && (k <= 57)) {
          c = (char)(m + (k - 48));
        }
        for (;;)
        {
          i += 1;
          break;
          if ((k >= 97) && (k <= 102))
          {
            c = (char)(m + (k - 97 + 10));
          }
          else
          {
            if ((k < 65) || (k > 70)) {
              break label267;
            }
            c = (char)(m + (k - 65 + 10));
          }
        }
        localObject = String.valueOf(new String(bho, pos, 4));
        if (((String)localObject).length() != 0) {}
        for (localObject = "\\u".concat((String)localObject);; localObject = new String("\\u")) {
          throw new NumberFormatException((String)localObject);
        }
      }
      pos += 4;
      return c;
    case 't': 
      return '\t';
    case 'b': 
      return '\b';
    case 'n': 
      return '\n';
    case 'r': 
      return '\r';
    case 'f': 
      label267:
      return '\f';
    }
    bhp += 1;
    bhq = pos;
    return c;
  }
  
  private void C()
    throws IOException
  {
    zzda(true);
    pos -= 1;
    if ((pos + bhm.length > aYn) && (!zzaed(bhm.length))) {
      return;
    }
    int i = 0;
    for (;;)
    {
      if (i >= bhm.length) {
        break label80;
      }
      if (bho[(pos + i)] != bhm[i]) {
        break;
      }
      i += 1;
    }
    label80:
    pos += bhm.length;
  }
  
  private int getColumnNumber()
  {
    return pos - bhq + 1;
  }
  
  private int getLineNumber()
  {
    return bhp + 1;
  }
  
  private int u()
    throws IOException
  {
    int i = bhv[(bhw - 1)];
    if (i == 1)
    {
      bhv[(bhw - 1)] = 2;
      switch (zzda(true))
      {
      default: 
        pos -= 1;
        if (bhw == 1) {
          z();
        }
        i = v();
        if (i == 0) {
          break;
        }
      }
    }
    int j;
    do
    {
      return i;
      if (i == 2)
      {
        switch (zzda(true))
        {
        case 44: 
        default: 
          throw zztd("Unterminated array");
        case 93: 
          bhr = 4;
          return 4;
        }
        z();
        break;
      }
      if ((i == 3) || (i == 5))
      {
        bhv[(bhw - 1)] = 4;
        if (i == 5) {
          switch (zzda(true))
          {
          default: 
            throw zztd("Unterminated object");
          case 125: 
            bhr = 2;
            return 2;
          case 59: 
            z();
          }
        }
        j = zzda(true);
        switch (j)
        {
        default: 
          z();
          pos -= 1;
          if (zze((char)j))
          {
            bhr = 14;
            return 14;
          }
          break;
        case 34: 
          bhr = 13;
          return 13;
        case 39: 
          z();
          bhr = 12;
          return 12;
        case 125: 
          if (i != 5)
          {
            bhr = 2;
            return 2;
          }
          throw zztd("Expected name");
        }
        throw zztd("Expected name");
      }
      if (i == 4)
      {
        bhv[(bhw - 1)] = 5;
        switch (zzda(true))
        {
        case 58: 
        case 59: 
        case 60: 
        default: 
          throw zztd("Expected ':'");
        }
        z();
        if (((pos >= aYn) && (!zzaed(1))) || (bho[pos] != '>')) {
          break;
        }
        pos += 1;
        break;
      }
      if (i == 6)
      {
        if (bhn) {
          C();
        }
        bhv[(bhw - 1)] = 7;
        break;
      }
      if (i == 7)
      {
        if (zzda(false) == -1)
        {
          bhr = 17;
          return 17;
        }
        z();
        pos -= 1;
        break;
      }
      if (i != 8) {
        break;
      }
      throw new IllegalStateException("JsonReader is closed");
      if (i == 1)
      {
        bhr = 4;
        return 4;
      }
      if ((i == 1) || (i == 2))
      {
        z();
        pos -= 1;
        bhr = 7;
        return 7;
      }
      throw zztd("Unexpected value");
      z();
      bhr = 8;
      return 8;
      if (bhw == 1) {
        z();
      }
      bhr = 9;
      return 9;
      bhr = 3;
      return 3;
      bhr = 1;
      return 1;
      j = w();
      i = j;
    } while (j != 0);
    if (!zze(bho[pos])) {
      throw zztd("Expected value");
    }
    z();
    bhr = 10;
    return 10;
  }
  
  private int v()
    throws IOException
  {
    int i = bho[pos];
    String str2;
    String str1;
    int k;
    int j;
    if ((i == 116) || (i == 84))
    {
      str2 = "true";
      str1 = "TRUE";
      i = 5;
      k = str2.length();
      j = 1;
    }
    for (;;)
    {
      if (j >= k) {
        break label168;
      }
      if ((pos + j >= aYn) && (!zzaed(j + 1)))
      {
        return 0;
        if ((i == 102) || (i == 70))
        {
          str2 = "false";
          str1 = "FALSE";
          i = 6;
          break;
        }
        if ((i == 110) || (i == 78))
        {
          str2 = "null";
          str1 = "NULL";
          i = 7;
          break;
        }
        return 0;
      }
      int m = bho[(pos + j)];
      if ((m != str2.charAt(j)) && (m != str1.charAt(j))) {
        return 0;
      }
      j += 1;
    }
    label168:
    if (((pos + k < aYn) || (zzaed(k + 1))) && (zze(bho[(pos + k)]))) {
      return 0;
    }
    pos += k;
    bhr = i;
    return i;
  }
  
  private int w()
    throws IOException
  {
    char[] arrayOfChar = bho;
    int i2 = pos;
    int n = aYn;
    long l1 = 0L;
    int i = 0;
    int j = 1;
    int k = 0;
    int m = 0;
    int i3 = n;
    int i1 = i3;
    n = i2;
    if (i2 + m == i3)
    {
      if (m == arrayOfChar.length) {
        return 0;
      }
      if (zzaed(m + 1)) {}
    }
    label101:
    char c;
    for (;;)
    {
      if ((k == 2) && (j != 0) && ((l1 != Long.MIN_VALUE) || (i != 0))) {
        if (i != 0)
        {
          bhs = l1;
          pos += m;
          bhr = 15;
          return 15;
          n = pos;
          i1 = aYn;
          c = arrayOfChar[(n + m)];
          switch (c)
          {
          default: 
            if ((c < '0') || (c > '9'))
            {
              if (!zze(c)) {
                continue;
              }
              return 0;
            }
            break;
          case '-': 
            if (k == 0)
            {
              i = 1;
              k = 1;
            }
            break;
          }
        }
      }
    }
    for (;;)
    {
      int i4 = m + 1;
      m = k;
      i3 = i1;
      i2 = n;
      k = i;
      i = m;
      m = i4;
      break;
      if (k == 5)
      {
        i2 = 6;
        k = i;
        i = i2;
      }
      else
      {
        return 0;
        if (k == 5)
        {
          i2 = 6;
          k = i;
          i = i2;
        }
        else
        {
          return 0;
          if ((k == 2) || (k == 4))
          {
            i2 = 5;
            k = i;
            i = i2;
          }
          else
          {
            return 0;
            if (k == 2)
            {
              i2 = 3;
              k = i;
              i = i2;
            }
            else
            {
              return 0;
              if ((k == 1) || (k == 0))
              {
                l1 = -(c - '0');
                i2 = 2;
                k = i;
                i = i2;
              }
              else
              {
                if (k == 2)
                {
                  if (l1 == 0L) {
                    return 0;
                  }
                  long l2 = 10L * l1 - (c - '0');
                  if ((l1 > -922337203685477580L) || ((l1 == -922337203685477580L) && (l2 < l1))) {}
                  for (i3 = 1;; i3 = 0)
                  {
                    i2 = i;
                    l1 = l2;
                    j = i3 & j;
                    i = k;
                    k = i2;
                    break;
                  }
                }
                if (k == 3)
                {
                  i2 = 4;
                  k = i;
                  i = i2;
                }
                else
                {
                  if ((k == 5) || (k == 6))
                  {
                    i2 = 7;
                    k = i;
                    i = i2;
                    continue;
                    l1 = -l1;
                    break label101;
                    if ((k == 2) || (k == 4) || (k == 7))
                    {
                      bht = m;
                      bhr = 16;
                      return 16;
                    }
                    return 0;
                  }
                  i2 = i;
                  i = k;
                  k = i2;
                }
              }
            }
          }
        }
      }
    }
  }
  
  private String x()
    throws IOException
  {
    Object localObject1 = null;
    int i = 0;
    for (;;)
    {
      Object localObject2;
      int j;
      if (pos + i < aYn)
      {
        localObject2 = localObject1;
        j = i;
        switch (bho[(pos + i)])
        {
        default: 
          i += 1;
          break;
        case '#': 
        case '/': 
        case ';': 
        case '=': 
        case '\\': 
          z();
          j = i;
          localObject2 = localObject1;
        case '\t': 
        case '\n': 
        case '\f': 
        case '\r': 
        case ' ': 
        case ',': 
        case ':': 
        case '[': 
        case ']': 
        case '{': 
        case '}': 
          label188:
          if (localObject2 != null) {}
          break;
        }
      }
      else
      {
        for (localObject1 = new String(bho, pos, j);; localObject1 = ((StringBuilder)localObject2).toString())
        {
          pos = (j + pos);
          return (String)localObject1;
          if (i < bho.length)
          {
            localObject2 = localObject1;
            j = i;
            if (!zzaed(i + 1)) {
              break label188;
            }
            break;
          }
          localObject2 = localObject1;
          if (localObject1 == null) {
            localObject2 = new StringBuilder();
          }
          ((StringBuilder)localObject2).append(bho, pos, i);
          pos = (i + pos);
          if (zzaed(1)) {
            break label327;
          }
          j = 0;
          break label188;
          ((StringBuilder)localObject2).append(bho, pos, j);
        }
        label327:
        i = 0;
        localObject1 = localObject2;
      }
    }
  }
  
  private void y()
    throws IOException
  {
    do
    {
      int i = 0;
      while (pos + i < aYn) {
        switch (bho[(pos + i)])
        {
        default: 
          i += 1;
          break;
        case '#': 
        case '/': 
        case ';': 
        case '=': 
        case '\\': 
          z();
        case '\t': 
        case '\n': 
        case '\f': 
        case '\r': 
        case ' ': 
        case ',': 
        case ':': 
        case '[': 
        case ']': 
        case '{': 
        case '}': 
          pos = (i + pos);
          return;
        }
      }
      pos = (i + pos);
    } while (zzaed(1));
  }
  
  private void z()
    throws IOException
  {
    if (!bhn) {
      throw zztd("Use JsonReader.setLenient(true) to accept malformed JSON");
    }
  }
  
  private void zzaec(int paramInt)
  {
    if (bhw == bhv.length)
    {
      arrayOfInt1 = new int[bhw * 2];
      int[] arrayOfInt2 = new int[bhw * 2];
      String[] arrayOfString = new String[bhw * 2];
      System.arraycopy(bhv, 0, arrayOfInt1, 0, bhw);
      System.arraycopy(bhy, 0, arrayOfInt2, 0, bhw);
      System.arraycopy(bhx, 0, arrayOfString, 0, bhw);
      bhv = arrayOfInt1;
      bhy = arrayOfInt2;
      bhx = arrayOfString;
    }
    int[] arrayOfInt1 = bhv;
    int i = bhw;
    bhw = (i + 1);
    arrayOfInt1[i] = paramInt;
  }
  
  private boolean zzaed(int paramInt)
    throws IOException
  {
    boolean bool2 = false;
    char[] arrayOfChar = bho;
    bhq -= pos;
    if (aYn != pos)
    {
      aYn -= pos;
      System.arraycopy(arrayOfChar, pos, arrayOfChar, 0, aYn);
    }
    for (;;)
    {
      pos = 0;
      int i;
      do
      {
        i = in.read(arrayOfChar, aYn, arrayOfChar.length - aYn);
        bool1 = bool2;
        if (i == -1) {
          break;
        }
        aYn = (i + aYn);
        i = paramInt;
        if (bhp == 0)
        {
          i = paramInt;
          if (bhq == 0)
          {
            i = paramInt;
            if (aYn > 0)
            {
              i = paramInt;
              if (arrayOfChar[0] == 65279)
              {
                pos += 1;
                bhq += 1;
                i = paramInt + 1;
              }
            }
          }
        }
        paramInt = i;
      } while (aYn < i);
      boolean bool1 = true;
      return bool1;
      aYn = 0;
    }
  }
  
  private int zzda(boolean paramBoolean)
    throws IOException
  {
    Object localObject = bho;
    int i = pos;
    int j = aYn;
    for (;;)
    {
      int k = j;
      int m = i;
      if (i == j)
      {
        pos = i;
        if (!zzaed(1))
        {
          if (paramBoolean)
          {
            localObject = String.valueOf("End of input at line ");
            i = getLineNumber();
            j = getColumnNumber();
            throw new EOFException(String.valueOf(localObject).length() + 30 + (String)localObject + i + " column " + j);
          }
        }
        else
        {
          m = pos;
          k = aYn;
        }
      }
      else
      {
        i = m + 1;
        j = localObject[m];
        if (j == 10)
        {
          bhp += 1;
          bhq = i;
          j = k;
          continue;
        }
        if ((j == 32) || (j == 13)) {
          break label384;
        }
        if (j == 9)
        {
          j = k;
          continue;
        }
        if (j == 47)
        {
          pos = i;
          if (i == k)
          {
            pos -= 1;
            boolean bool = zzaed(2);
            pos += 1;
            if (!bool) {
              return j;
            }
          }
          z();
          switch (localObject[pos])
          {
          default: 
            return j;
          case '*': 
            pos += 1;
            if (!zztc("*/")) {
              throw zztd("Unterminated comment");
            }
            i = pos + 2;
            j = aYn;
            break;
          case '/': 
            pos += 1;
            A();
            i = pos;
            j = aYn;
            break;
          }
        }
        if (j == 35)
        {
          pos = i;
          z();
          A();
          i = pos;
          j = aYn;
          continue;
        }
        pos = i;
        return j;
      }
      return -1;
      label384:
      j = k;
    }
  }
  
  private boolean zze(char paramChar)
    throws IOException
  {
    switch (paramChar)
    {
    default: 
      return true;
    case '#': 
    case '/': 
    case ';': 
    case '=': 
    case '\\': 
      z();
    }
    return false;
  }
  
  private String zzf(char paramChar)
    throws IOException
  {
    char[] arrayOfChar = bho;
    StringBuilder localStringBuilder = new StringBuilder();
    do
    {
      int k = pos;
      int j = aYn;
      int i = k;
      if (i < j)
      {
        int i1 = i + 1;
        char c = arrayOfChar[i];
        if (c == paramChar)
        {
          pos = i1;
          localStringBuilder.append(arrayOfChar, k, i1 - k - 1);
          return localStringBuilder.toString();
        }
        int n;
        int m;
        if (c == '\\')
        {
          pos = i1;
          localStringBuilder.append(arrayOfChar, k, i1 - k - 1);
          localStringBuilder.append(B());
          n = pos;
          m = aYn;
          i = n;
        }
        for (;;)
        {
          k = n;
          j = m;
          break;
          n = k;
          m = j;
          i = i1;
          if (c == '\n')
          {
            bhp += 1;
            bhq = i1;
            n = k;
            m = j;
            i = i1;
          }
        }
      }
      localStringBuilder.append(arrayOfChar, k, i - k);
      pos = i;
    } while (zzaed(1));
    throw zztd("Unterminated string");
  }
  
  private void zzg(char paramChar)
    throws IOException
  {
    char[] arrayOfChar = bho;
    do
    {
      int i = pos;
      int j = aYn;
      if (i < j)
      {
        int m = i + 1;
        char c = arrayOfChar[i];
        if (c == paramChar)
        {
          pos = m;
          return;
        }
        int k;
        if (c == '\\')
        {
          pos = m;
          B();
          i = pos;
          k = aYn;
        }
        for (;;)
        {
          j = k;
          break;
          k = j;
          i = m;
          if (c == '\n')
          {
            bhp += 1;
            bhq = m;
            k = j;
            i = m;
          }
        }
      }
      pos = i;
    } while (zzaed(1));
    throw zztd("Unterminated string");
  }
  
  private boolean zztc(String paramString)
    throws IOException
  {
    boolean bool2 = false;
    for (;;)
    {
      if (pos + paramString.length() > aYn)
      {
        bool1 = bool2;
        if (!zzaed(paramString.length())) {
          return bool1;
        }
      }
      if (bho[pos] != '\n') {
        break;
      }
      bhp += 1;
      bhq = (pos + 1);
      pos += 1;
    }
    int i = 0;
    for (;;)
    {
      if (i >= paramString.length()) {
        break label116;
      }
      if (bho[(pos + i)] != paramString.charAt(i)) {
        break;
      }
      i += 1;
    }
    label116:
    boolean bool1 = true;
    return bool1;
  }
  
  private IOException zztd(String paramString)
    throws IOException
  {
    int i = getLineNumber();
    int j = getColumnNumber();
    String str = getPath();
    throw new zzaos(String.valueOf(paramString).length() + 45 + String.valueOf(str).length() + paramString + " at line " + i + " column " + j + " path " + str);
  }
  
  public void beginArray()
    throws IOException
  {
    int j = bhr;
    int i = j;
    if (j == 0) {
      i = u();
    }
    if (i == 3)
    {
      zzaec(1);
      bhy[(bhw - 1)] = 0;
      bhr = 0;
      return;
    }
    String str1 = String.valueOf(h());
    i = getLineNumber();
    j = getColumnNumber();
    String str2 = getPath();
    throw new IllegalStateException(String.valueOf(str1).length() + 74 + String.valueOf(str2).length() + "Expected BEGIN_ARRAY but was " + str1 + " at line " + i + " column " + j + " path " + str2);
  }
  
  public void beginObject()
    throws IOException
  {
    int j = bhr;
    int i = j;
    if (j == 0) {
      i = u();
    }
    if (i == 1)
    {
      zzaec(3);
      bhr = 0;
      return;
    }
    String str1 = String.valueOf(h());
    i = getLineNumber();
    j = getColumnNumber();
    String str2 = getPath();
    throw new IllegalStateException(String.valueOf(str1).length() + 75 + String.valueOf(str2).length() + "Expected BEGIN_OBJECT but was " + str1 + " at line " + i + " column " + j + " path " + str2);
  }
  
  public void close()
    throws IOException
  {
    bhr = 0;
    bhv[0] = 8;
    bhw = 1;
    in.close();
  }
  
  public void endArray()
    throws IOException
  {
    int j = bhr;
    int i = j;
    if (j == 0) {
      i = u();
    }
    if (i == 4)
    {
      bhw -= 1;
      localObject = bhy;
      i = bhw - 1;
      localObject[i] += 1;
      bhr = 0;
      return;
    }
    Object localObject = String.valueOf(h());
    i = getLineNumber();
    j = getColumnNumber();
    String str = getPath();
    throw new IllegalStateException(String.valueOf(localObject).length() + 72 + String.valueOf(str).length() + "Expected END_ARRAY but was " + (String)localObject + " at line " + i + " column " + j + " path " + str);
  }
  
  public void endObject()
    throws IOException
  {
    int j = bhr;
    int i = j;
    if (j == 0) {
      i = u();
    }
    if (i == 2)
    {
      bhw -= 1;
      bhx[bhw] = null;
      localObject = bhy;
      i = bhw - 1;
      localObject[i] += 1;
      bhr = 0;
      return;
    }
    Object localObject = String.valueOf(h());
    i = getLineNumber();
    j = getColumnNumber();
    String str = getPath();
    throw new IllegalStateException(String.valueOf(localObject).length() + 73 + String.valueOf(str).length() + "Expected END_OBJECT but was " + (String)localObject + " at line " + i + " column " + j + " path " + str);
  }
  
  public String getPath()
  {
    StringBuilder localStringBuilder = new StringBuilder().append('$');
    int i = 0;
    int j = bhw;
    if (i < j)
    {
      switch (bhv[i])
      {
      }
      for (;;)
      {
        i += 1;
        break;
        localStringBuilder.append('[').append(bhy[i]).append(']');
        continue;
        localStringBuilder.append('.');
        if (bhx[i] != null) {
          localStringBuilder.append(bhx[i]);
        }
      }
    }
    return localStringBuilder.toString();
  }
  
  public zzaoq h()
    throws IOException
  {
    int j = bhr;
    int i = j;
    if (j == 0) {
      i = u();
    }
    switch (i)
    {
    default: 
      throw new AssertionError();
    case 1: 
      return zzaoq.bhB;
    case 2: 
      return zzaoq.bhC;
    case 3: 
      return zzaoq.bhz;
    case 4: 
      return zzaoq.bhA;
    case 12: 
    case 13: 
    case 14: 
      return zzaoq.bhD;
    case 5: 
    case 6: 
      return zzaoq.bhG;
    case 7: 
      return zzaoq.bhH;
    case 8: 
    case 9: 
    case 10: 
    case 11: 
      return zzaoq.bhE;
    case 15: 
    case 16: 
      return zzaoq.bhF;
    }
    return zzaoq.bhI;
  }
  
  public boolean hasNext()
    throws IOException
  {
    int j = bhr;
    int i = j;
    if (j == 0) {
      i = u();
    }
    return (i != 2) && (i != 4);
  }
  
  public final boolean isLenient()
  {
    return bhn;
  }
  
  public boolean nextBoolean()
    throws IOException
  {
    int j = bhr;
    int i = j;
    if (j == 0) {
      i = u();
    }
    if (i == 5)
    {
      bhr = 0;
      localObject = bhy;
      i = bhw - 1;
      localObject[i] += 1;
      return true;
    }
    if (i == 6)
    {
      bhr = 0;
      localObject = bhy;
      i = bhw - 1;
      localObject[i] += 1;
      return false;
    }
    Object localObject = String.valueOf(h());
    i = getLineNumber();
    j = getColumnNumber();
    String str = getPath();
    throw new IllegalStateException(String.valueOf(localObject).length() + 72 + String.valueOf(str).length() + "Expected a boolean but was " + (String)localObject + " at line " + i + " column " + j + " path " + str);
  }
  
  public double nextDouble()
    throws IOException
  {
    int j = bhr;
    int i = j;
    if (j == 0) {
      i = u();
    }
    if (i == 15)
    {
      bhr = 0;
      localObject = bhy;
      i = bhw - 1;
      localObject[i] += 1;
      return bhs;
    }
    if (i == 16)
    {
      bhu = new String(bho, pos, bht);
      pos += bht;
    }
    double d;
    do
    {
      for (;;)
      {
        bhr = 11;
        d = Double.parseDouble(bhu);
        if ((bhn) || ((!Double.isNaN(d)) && (!Double.isInfinite(d)))) {
          break label407;
        }
        i = getLineNumber();
        j = getColumnNumber();
        localObject = getPath();
        throw new zzaos(String.valueOf(localObject).length() + 102 + "JSON forbids NaN and infinities: " + d + " at line " + i + " column " + j + " path " + (String)localObject);
        if ((i == 8) || (i == 9))
        {
          if (i == 8) {}
          for (char c = '\'';; c = '"')
          {
            bhu = zzf(c);
            break;
          }
        }
        if (i != 10) {
          break;
        }
        bhu = x();
      }
    } while (i == 11);
    Object localObject = String.valueOf(h());
    i = getLineNumber();
    j = getColumnNumber();
    String str = getPath();
    throw new IllegalStateException(String.valueOf(localObject).length() + 71 + String.valueOf(str).length() + "Expected a double but was " + (String)localObject + " at line " + i + " column " + j + " path " + str);
    label407:
    bhu = null;
    bhr = 0;
    localObject = bhy;
    i = bhw - 1;
    localObject[i] += 1;
    return d;
  }
  
  public int nextInt()
    throws IOException
  {
    int j = bhr;
    int i = j;
    if (j == 0) {
      i = u();
    }
    Object localObject1;
    if (i == 15)
    {
      i = (int)bhs;
      if (bhs != i)
      {
        long l = bhs;
        i = getLineNumber();
        j = getColumnNumber();
        localObject1 = getPath();
        throw new NumberFormatException(String.valueOf(localObject1).length() + 89 + "Expected an int but was " + l + " at line " + i + " column " + j + " path " + (String)localObject1);
      }
      bhr = 0;
      localObject1 = bhy;
      j = bhw - 1;
      localObject1[j] += 1;
      return i;
    }
    String str;
    if (i == 16)
    {
      bhu = new String(bho, pos, bht);
      pos += bht;
      bhr = 11;
      double d = Double.parseDouble(bhu);
      i = (int)d;
      if (i != d)
      {
        localObject1 = bhu;
        i = getLineNumber();
        j = getColumnNumber();
        str = getPath();
        throw new NumberFormatException(String.valueOf(localObject1).length() + 69 + String.valueOf(str).length() + "Expected an int but was " + (String)localObject1 + " at line " + i + " column " + j + " path " + str);
      }
    }
    else
    {
      if ((i == 8) || (i == 9))
      {
        if (i == 8) {}
        for (char c = '\'';; c = '"')
        {
          bhu = zzf(c);
          try
          {
            i = Integer.parseInt(bhu);
            bhr = 0;
            localObject1 = bhy;
            j = bhw - 1;
            localObject1[j] += 1;
            return i;
          }
          catch (NumberFormatException localNumberFormatException) {}
          break;
        }
      }
      localObject2 = String.valueOf(h());
      i = getLineNumber();
      j = getColumnNumber();
      str = getPath();
      throw new IllegalStateException(String.valueOf(localObject2).length() + 69 + String.valueOf(str).length() + "Expected an int but was " + (String)localObject2 + " at line " + i + " column " + j + " path " + str);
    }
    bhu = null;
    bhr = 0;
    Object localObject2 = bhy;
    j = bhw - 1;
    localObject2[j] += 1;
    return i;
  }
  
  public long nextLong()
    throws IOException
  {
    int j = bhr;
    int i = j;
    if (j == 0) {
      i = u();
    }
    Object localObject1;
    if (i == 15)
    {
      bhr = 0;
      localObject1 = bhy;
      i = bhw - 1;
      localObject1[i] += 1;
      return bhs;
    }
    long l;
    String str;
    if (i == 16)
    {
      bhu = new String(bho, pos, bht);
      pos += bht;
      bhr = 11;
      double d = Double.parseDouble(bhu);
      l = d;
      if (l != d)
      {
        localObject1 = bhu;
        i = getLineNumber();
        j = getColumnNumber();
        str = getPath();
        throw new NumberFormatException(String.valueOf(localObject1).length() + 69 + String.valueOf(str).length() + "Expected a long but was " + (String)localObject1 + " at line " + i + " column " + j + " path " + str);
      }
    }
    else
    {
      if ((i == 8) || (i == 9))
      {
        if (i == 8) {}
        for (char c = '\'';; c = '"')
        {
          bhu = zzf(c);
          try
          {
            l = Long.parseLong(bhu);
            bhr = 0;
            localObject1 = bhy;
            i = bhw - 1;
            localObject1[i] += 1;
            return l;
          }
          catch (NumberFormatException localNumberFormatException) {}
          break;
        }
      }
      localObject2 = String.valueOf(h());
      i = getLineNumber();
      j = getColumnNumber();
      str = getPath();
      throw new IllegalStateException(String.valueOf(localObject2).length() + 69 + String.valueOf(str).length() + "Expected a long but was " + (String)localObject2 + " at line " + i + " column " + j + " path " + str);
    }
    bhu = null;
    bhr = 0;
    Object localObject2 = bhy;
    i = bhw - 1;
    localObject2[i] += 1;
    return l;
  }
  
  public String nextName()
    throws IOException
  {
    int j = bhr;
    int i = j;
    if (j == 0) {
      i = u();
    }
    if (i == 14) {
      str1 = x();
    }
    for (;;)
    {
      bhr = 0;
      bhx[(bhw - 1)] = str1;
      return str1;
      if (i == 12)
      {
        str1 = zzf('\'');
      }
      else
      {
        if (i != 13) {
          break;
        }
        str1 = zzf('"');
      }
    }
    String str1 = String.valueOf(h());
    i = getLineNumber();
    j = getColumnNumber();
    String str2 = getPath();
    throw new IllegalStateException(String.valueOf(str1).length() + 69 + String.valueOf(str2).length() + "Expected a name but was " + str1 + " at line " + i + " column " + j + " path " + str2);
  }
  
  public void nextNull()
    throws IOException
  {
    int j = bhr;
    int i = j;
    if (j == 0) {
      i = u();
    }
    if (i == 7)
    {
      bhr = 0;
      localObject = bhy;
      i = bhw - 1;
      localObject[i] += 1;
      return;
    }
    Object localObject = String.valueOf(h());
    i = getLineNumber();
    j = getColumnNumber();
    String str = getPath();
    throw new IllegalStateException(String.valueOf(localObject).length() + 67 + String.valueOf(str).length() + "Expected null but was " + (String)localObject + " at line " + i + " column " + j + " path " + str);
  }
  
  public String nextString()
    throws IOException
  {
    int j = bhr;
    int i = j;
    if (j == 0) {
      i = u();
    }
    if (i == 10) {
      str = x();
    }
    for (;;)
    {
      bhr = 0;
      localObject = bhy;
      i = bhw - 1;
      localObject[i] += 1;
      return str;
      if (i == 8)
      {
        str = zzf('\'');
      }
      else if (i == 9)
      {
        str = zzf('"');
      }
      else if (i == 11)
      {
        str = bhu;
        bhu = null;
      }
      else if (i == 15)
      {
        str = Long.toString(bhs);
      }
      else
      {
        if (i != 16) {
          break;
        }
        str = new String(bho, pos, bht);
        pos += bht;
      }
    }
    String str = String.valueOf(h());
    i = getLineNumber();
    j = getColumnNumber();
    Object localObject = getPath();
    throw new IllegalStateException(String.valueOf(str).length() + 71 + String.valueOf(localObject).length() + "Expected a string but was " + str + " at line " + i + " column " + j + " path " + (String)localObject);
  }
  
  public final void setLenient(boolean paramBoolean)
  {
    bhn = paramBoolean;
  }
  
  public void skipValue()
    throws IOException
  {
    int j = 0;
    int i = bhr;
    int k = i;
    if (i == 0) {
      k = u();
    }
    if (k == 3)
    {
      zzaec(1);
      i = j + 1;
    }
    for (;;)
    {
      bhr = 0;
      j = i;
      if (i != 0) {
        break;
      }
      int[] arrayOfInt = bhy;
      i = bhw - 1;
      arrayOfInt[i] += 1;
      bhx[(bhw - 1)] = "null";
      return;
      if (k == 1)
      {
        zzaec(3);
        i = j + 1;
      }
      else if (k == 4)
      {
        bhw -= 1;
        i = j - 1;
      }
      else if (k == 2)
      {
        bhw -= 1;
        i = j - 1;
      }
      else if ((k == 14) || (k == 10))
      {
        y();
        i = j;
      }
      else if ((k == 8) || (k == 12))
      {
        zzg('\'');
        i = j;
      }
      else if ((k == 9) || (k == 13))
      {
        zzg('"');
        i = j;
      }
      else
      {
        i = j;
        if (k == 16)
        {
          pos += bht;
          i = j;
        }
      }
    }
  }
  
  public String toString()
  {
    String str = String.valueOf(getClass().getSimpleName());
    int i = getLineNumber();
    int j = getColumnNumber();
    return String.valueOf(str).length() + 39 + str + " at line " + i + " column " + j;
  }
}

/* Location:
 * Qualified Name:     com.google.android.gms.internal.zzaop
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */