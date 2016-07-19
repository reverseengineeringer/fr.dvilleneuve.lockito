package com.google.android.gms.internal;

import java.io.IOException;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.InetAddress;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.sql.Timestamp;
import java.util.BitSet;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.UUID;

public final class zzaon
{
  public static final zzanl bgA = zza(Character.TYPE, Character.class, bgz);
  public static final zzank<String> bgB = new zzank()
  {
    public void zza(zzaor paramAnonymouszzaor, String paramAnonymousString)
      throws IOException
    {
      paramAnonymouszzaor.zztb(paramAnonymousString);
    }
    
    public String zzq(zzaop paramAnonymouszzaop)
      throws IOException
    {
      zzaoq localzzaoq = paramAnonymouszzaop.h();
      if (localzzaoq == zzaoq.bhH)
      {
        paramAnonymouszzaop.nextNull();
        return null;
      }
      if (localzzaoq == zzaoq.bhG) {
        return Boolean.toString(paramAnonymouszzaop.nextBoolean());
      }
      return paramAnonymouszzaop.nextString();
    }
  };
  public static final zzank<BigDecimal> bgC = new zzank()
  {
    public void zza(zzaor paramAnonymouszzaor, BigDecimal paramAnonymousBigDecimal)
      throws IOException
    {
      paramAnonymouszzaor.zza(paramAnonymousBigDecimal);
    }
    
    public BigDecimal zzr(zzaop paramAnonymouszzaop)
      throws IOException
    {
      if (paramAnonymouszzaop.h() == zzaoq.bhH)
      {
        paramAnonymouszzaop.nextNull();
        return null;
      }
      try
      {
        paramAnonymouszzaop = new BigDecimal(paramAnonymouszzaop.nextString());
        return paramAnonymouszzaop;
      }
      catch (NumberFormatException paramAnonymouszzaop)
      {
        throw new zzanh(paramAnonymouszzaop);
      }
    }
  };
  public static final zzank<BigInteger> bgD = new zzank()
  {
    public void zza(zzaor paramAnonymouszzaor, BigInteger paramAnonymousBigInteger)
      throws IOException
    {
      paramAnonymouszzaor.zza(paramAnonymousBigInteger);
    }
    
    public BigInteger zzs(zzaop paramAnonymouszzaop)
      throws IOException
    {
      if (paramAnonymouszzaop.h() == zzaoq.bhH)
      {
        paramAnonymouszzaop.nextNull();
        return null;
      }
      try
      {
        paramAnonymouszzaop = new BigInteger(paramAnonymouszzaop.nextString());
        return paramAnonymouszzaop;
      }
      catch (NumberFormatException paramAnonymouszzaop)
      {
        throw new zzanh(paramAnonymouszzaop);
      }
    }
  };
  public static final zzanl bgE = zza(String.class, bgB);
  public static final zzank<StringBuilder> bgF = new zzank()
  {
    public void zza(zzaor paramAnonymouszzaor, StringBuilder paramAnonymousStringBuilder)
      throws IOException
    {
      if (paramAnonymousStringBuilder == null) {}
      for (paramAnonymousStringBuilder = null;; paramAnonymousStringBuilder = paramAnonymousStringBuilder.toString())
      {
        paramAnonymouszzaor.zztb(paramAnonymousStringBuilder);
        return;
      }
    }
    
    public StringBuilder zzt(zzaop paramAnonymouszzaop)
      throws IOException
    {
      if (paramAnonymouszzaop.h() == zzaoq.bhH)
      {
        paramAnonymouszzaop.nextNull();
        return null;
      }
      return new StringBuilder(paramAnonymouszzaop.nextString());
    }
  };
  public static final zzanl bgG = zza(StringBuilder.class, bgF);
  public static final zzank<StringBuffer> bgH = new zzank()
  {
    public void zza(zzaor paramAnonymouszzaor, StringBuffer paramAnonymousStringBuffer)
      throws IOException
    {
      if (paramAnonymousStringBuffer == null) {}
      for (paramAnonymousStringBuffer = null;; paramAnonymousStringBuffer = paramAnonymousStringBuffer.toString())
      {
        paramAnonymouszzaor.zztb(paramAnonymousStringBuffer);
        return;
      }
    }
    
    public StringBuffer zzu(zzaop paramAnonymouszzaop)
      throws IOException
    {
      if (paramAnonymouszzaop.h() == zzaoq.bhH)
      {
        paramAnonymouszzaop.nextNull();
        return null;
      }
      return new StringBuffer(paramAnonymouszzaop.nextString());
    }
  };
  public static final zzanl bgI = zza(StringBuffer.class, bgH);
  public static final zzank<URL> bgJ = new zzank()
  {
    public void zza(zzaor paramAnonymouszzaor, URL paramAnonymousURL)
      throws IOException
    {
      if (paramAnonymousURL == null) {}
      for (paramAnonymousURL = null;; paramAnonymousURL = paramAnonymousURL.toExternalForm())
      {
        paramAnonymouszzaor.zztb(paramAnonymousURL);
        return;
      }
    }
    
    public URL zzv(zzaop paramAnonymouszzaop)
      throws IOException
    {
      if (paramAnonymouszzaop.h() == zzaoq.bhH) {
        paramAnonymouszzaop.nextNull();
      }
      do
      {
        return null;
        paramAnonymouszzaop = paramAnonymouszzaop.nextString();
      } while ("null".equals(paramAnonymouszzaop));
      return new URL(paramAnonymouszzaop);
    }
  };
  public static final zzanl bgK = zza(URL.class, bgJ);
  public static final zzank<URI> bgL = new zzank()
  {
    public void zza(zzaor paramAnonymouszzaor, URI paramAnonymousURI)
      throws IOException
    {
      if (paramAnonymousURI == null) {}
      for (paramAnonymousURI = null;; paramAnonymousURI = paramAnonymousURI.toASCIIString())
      {
        paramAnonymouszzaor.zztb(paramAnonymousURI);
        return;
      }
    }
    
    public URI zzw(zzaop paramAnonymouszzaop)
      throws IOException
    {
      if (paramAnonymouszzaop.h() == zzaoq.bhH) {
        paramAnonymouszzaop.nextNull();
      }
      for (;;)
      {
        return null;
        try
        {
          paramAnonymouszzaop = paramAnonymouszzaop.nextString();
          if ("null".equals(paramAnonymouszzaop)) {
            continue;
          }
          paramAnonymouszzaop = new URI(paramAnonymouszzaop);
          return paramAnonymouszzaop;
        }
        catch (URISyntaxException paramAnonymouszzaop)
        {
          throw new zzamz(paramAnonymouszzaop);
        }
      }
    }
  };
  public static final zzanl bgM = zza(URI.class, bgL);
  public static final zzank<InetAddress> bgN = new zzank()
  {
    public void zza(zzaor paramAnonymouszzaor, InetAddress paramAnonymousInetAddress)
      throws IOException
    {
      if (paramAnonymousInetAddress == null) {}
      for (paramAnonymousInetAddress = null;; paramAnonymousInetAddress = paramAnonymousInetAddress.getHostAddress())
      {
        paramAnonymouszzaor.zztb(paramAnonymousInetAddress);
        return;
      }
    }
    
    public InetAddress zzy(zzaop paramAnonymouszzaop)
      throws IOException
    {
      if (paramAnonymouszzaop.h() == zzaoq.bhH)
      {
        paramAnonymouszzaop.nextNull();
        return null;
      }
      return InetAddress.getByName(paramAnonymouszzaop.nextString());
    }
  };
  public static final zzanl bgO = zzb(InetAddress.class, bgN);
  public static final zzank<UUID> bgP = new zzank()
  {
    public void zza(zzaor paramAnonymouszzaor, UUID paramAnonymousUUID)
      throws IOException
    {
      if (paramAnonymousUUID == null) {}
      for (paramAnonymousUUID = null;; paramAnonymousUUID = paramAnonymousUUID.toString())
      {
        paramAnonymouszzaor.zztb(paramAnonymousUUID);
        return;
      }
    }
    
    public UUID zzz(zzaop paramAnonymouszzaop)
      throws IOException
    {
      if (paramAnonymouszzaop.h() == zzaoq.bhH)
      {
        paramAnonymouszzaop.nextNull();
        return null;
      }
      return UUID.fromString(paramAnonymouszzaop.nextString());
    }
  };
  public static final zzanl bgQ = zza(UUID.class, bgP);
  public static final zzanl bgR = new zzanl()
  {
    public <T> zzank<T> zza(zzams paramAnonymouszzams, zzaoo<T> paramAnonymouszzaoo)
    {
      if (paramAnonymouszzaoo.s() != Timestamp.class) {
        return null;
      }
      new zzank()
      {
        public void zza(zzaor paramAnonymous2zzaor, Timestamp paramAnonymous2Timestamp)
          throws IOException
        {
          bgZ.zza(paramAnonymous2zzaor, paramAnonymous2Timestamp);
        }
        
        public Timestamp zzaa(zzaop paramAnonymous2zzaop)
          throws IOException
        {
          paramAnonymous2zzaop = (Date)bgZ.zzb(paramAnonymous2zzaop);
          if (paramAnonymous2zzaop != null) {
            return new Timestamp(paramAnonymous2zzaop.getTime());
          }
          return null;
        }
      };
    }
  };
  public static final zzank<Calendar> bgS = new zzank()
  {
    public void zza(zzaor paramAnonymouszzaor, Calendar paramAnonymousCalendar)
      throws IOException
    {
      if (paramAnonymousCalendar == null)
      {
        paramAnonymouszzaor.r();
        return;
      }
      paramAnonymouszzaor.p();
      paramAnonymouszzaor.zzta("year");
      paramAnonymouszzaor.zzcp(paramAnonymousCalendar.get(1));
      paramAnonymouszzaor.zzta("month");
      paramAnonymouszzaor.zzcp(paramAnonymousCalendar.get(2));
      paramAnonymouszzaor.zzta("dayOfMonth");
      paramAnonymouszzaor.zzcp(paramAnonymousCalendar.get(5));
      paramAnonymouszzaor.zzta("hourOfDay");
      paramAnonymouszzaor.zzcp(paramAnonymousCalendar.get(11));
      paramAnonymouszzaor.zzta("minute");
      paramAnonymouszzaor.zzcp(paramAnonymousCalendar.get(12));
      paramAnonymouszzaor.zzta("second");
      paramAnonymouszzaor.zzcp(paramAnonymousCalendar.get(13));
      paramAnonymouszzaor.q();
    }
    
    public Calendar zzab(zzaop paramAnonymouszzaop)
      throws IOException
    {
      int j = 0;
      if (paramAnonymouszzaop.h() == zzaoq.bhH)
      {
        paramAnonymouszzaop.nextNull();
        return null;
      }
      paramAnonymouszzaop.beginObject();
      int k = 0;
      int m = 0;
      int n = 0;
      int i1 = 0;
      int i2 = 0;
      while (paramAnonymouszzaop.h() != zzaoq.bhC)
      {
        String str = paramAnonymouszzaop.nextName();
        int i = paramAnonymouszzaop.nextInt();
        if ("year".equals(str)) {
          i2 = i;
        } else if ("month".equals(str)) {
          i1 = i;
        } else if ("dayOfMonth".equals(str)) {
          n = i;
        } else if ("hourOfDay".equals(str)) {
          m = i;
        } else if ("minute".equals(str)) {
          k = i;
        } else if ("second".equals(str)) {
          j = i;
        }
      }
      paramAnonymouszzaop.endObject();
      return new GregorianCalendar(i2, i1, n, m, k, j);
    }
  };
  public static final zzanl bgT = zzb(Calendar.class, GregorianCalendar.class, bgS);
  public static final zzank<Locale> bgU = new zzank()
  {
    public void zza(zzaor paramAnonymouszzaor, Locale paramAnonymousLocale)
      throws IOException
    {
      if (paramAnonymousLocale == null) {}
      for (paramAnonymousLocale = null;; paramAnonymousLocale = paramAnonymousLocale.toString())
      {
        paramAnonymouszzaor.zztb(paramAnonymousLocale);
        return;
      }
    }
    
    public Locale zzac(zzaop paramAnonymouszzaop)
      throws IOException
    {
      if (paramAnonymouszzaop.h() == zzaoq.bhH)
      {
        paramAnonymouszzaop.nextNull();
        return null;
      }
      Object localObject = new StringTokenizer(paramAnonymouszzaop.nextString(), "_");
      if (((StringTokenizer)localObject).hasMoreElements()) {}
      for (paramAnonymouszzaop = ((StringTokenizer)localObject).nextToken();; paramAnonymouszzaop = null)
      {
        if (((StringTokenizer)localObject).hasMoreElements()) {}
        for (String str = ((StringTokenizer)localObject).nextToken();; str = null)
        {
          if (((StringTokenizer)localObject).hasMoreElements()) {}
          for (localObject = ((StringTokenizer)localObject).nextToken();; localObject = null)
          {
            if ((str == null) && (localObject == null)) {
              return new Locale(paramAnonymouszzaop);
            }
            if (localObject == null) {
              return new Locale(paramAnonymouszzaop, str);
            }
            return new Locale(paramAnonymouszzaop, str, (String)localObject);
          }
        }
      }
    }
  };
  public static final zzanl bgV = zza(Locale.class, bgU);
  public static final zzank<zzamy> bgW = new zzank()
  {
    public void zza(zzaor paramAnonymouszzaor, zzamy paramAnonymouszzamy)
      throws IOException
    {
      if ((paramAnonymouszzamy == null) || (paramAnonymouszzamy.zzczp()))
      {
        paramAnonymouszzaor.r();
        return;
      }
      if (paramAnonymouszzamy.zzczo())
      {
        paramAnonymouszzamy = paramAnonymouszzamy.zzczs();
        if (paramAnonymouszzamy.zzczv())
        {
          paramAnonymouszzaor.zza(paramAnonymouszzamy.zzczg());
          return;
        }
        if (paramAnonymouszzamy.zzczu())
        {
          paramAnonymouszzaor.zzcz(paramAnonymouszzamy.zzczl());
          return;
        }
        paramAnonymouszzaor.zztb(paramAnonymouszzamy.zzczh());
        return;
      }
      if (paramAnonymouszzamy.zzczm())
      {
        paramAnonymouszzaor.n();
        paramAnonymouszzamy = paramAnonymouszzamy.zzczr().iterator();
        while (paramAnonymouszzamy.hasNext()) {
          zza(paramAnonymouszzaor, (zzamy)paramAnonymouszzamy.next());
        }
        paramAnonymouszzaor.o();
        return;
      }
      if (paramAnonymouszzamy.zzczn())
      {
        paramAnonymouszzaor.p();
        paramAnonymouszzamy = paramAnonymouszzamy.zzczq().entrySet().iterator();
        while (paramAnonymouszzamy.hasNext())
        {
          Map.Entry localEntry = (Map.Entry)paramAnonymouszzamy.next();
          paramAnonymouszzaor.zzta((String)localEntry.getKey());
          zza(paramAnonymouszzaor, (zzamy)localEntry.getValue());
        }
        paramAnonymouszzaor.q();
        return;
      }
      paramAnonymouszzaor = String.valueOf(paramAnonymouszzamy.getClass());
      throw new IllegalArgumentException(String.valueOf(paramAnonymouszzaor).length() + 15 + "Couldn't write " + paramAnonymouszzaor);
    }
    
    public zzamy zzad(zzaop paramAnonymouszzaop)
      throws IOException
    {
      switch (zzaon.26.bfU[paramAnonymouszzaop.h().ordinal()])
      {
      default: 
        throw new IllegalArgumentException();
      case 3: 
        return new zzane(paramAnonymouszzaop.nextString());
      case 1: 
        return new zzane(new zzanv(paramAnonymouszzaop.nextString()));
      case 2: 
        return new zzane(Boolean.valueOf(paramAnonymouszzaop.nextBoolean()));
      case 4: 
        paramAnonymouszzaop.nextNull();
        return zzana.bes;
      case 5: 
        localObject = new zzamv();
        paramAnonymouszzaop.beginArray();
        while (paramAnonymouszzaop.hasNext()) {
          ((zzamv)localObject).zzc((zzamy)zzb(paramAnonymouszzaop));
        }
        paramAnonymouszzaop.endArray();
        return (zzamy)localObject;
      }
      Object localObject = new zzanb();
      paramAnonymouszzaop.beginObject();
      while (paramAnonymouszzaop.hasNext()) {
        ((zzanb)localObject).zza(paramAnonymouszzaop.nextName(), (zzamy)zzb(paramAnonymouszzaop));
      }
      paramAnonymouszzaop.endObject();
      return (zzamy)localObject;
    }
  };
  public static final zzanl bgX = zzb(zzamy.class, bgW);
  public static final zzanl bgY = new zzanl()
  {
    public <T> zzank<T> zza(zzams paramAnonymouszzams, zzaoo<T> paramAnonymouszzaoo)
    {
      paramAnonymouszzaoo = paramAnonymouszzaoo.s();
      if ((!Enum.class.isAssignableFrom(paramAnonymouszzaoo)) || (paramAnonymouszzaoo == Enum.class)) {
        return null;
      }
      paramAnonymouszzams = paramAnonymouszzaoo;
      if (!paramAnonymouszzaoo.isEnum()) {
        paramAnonymouszzams = paramAnonymouszzaoo.getSuperclass();
      }
      return new zzaon.zza(paramAnonymouszzams);
    }
  };
  public static final zzank<Class> bgh = new zzank()
  {
    public void zza(zzaor paramAnonymouszzaor, Class paramAnonymousClass)
      throws IOException
    {
      if (paramAnonymousClass == null)
      {
        paramAnonymouszzaor.r();
        return;
      }
      paramAnonymouszzaor = String.valueOf(paramAnonymousClass.getName());
      throw new UnsupportedOperationException(String.valueOf(paramAnonymouszzaor).length() + 76 + "Attempted to serialize java.lang.Class: " + paramAnonymouszzaor + ". Forgot to register a type adapter?");
    }
    
    public Class zzo(zzaop paramAnonymouszzaop)
      throws IOException
    {
      if (paramAnonymouszzaop.h() == zzaoq.bhH)
      {
        paramAnonymouszzaop.nextNull();
        return null;
      }
      throw new UnsupportedOperationException("Attempted to deserialize a java.lang.Class. Forgot to register a type adapter?");
    }
  };
  public static final zzanl bgi = zza(Class.class, bgh);
  public static final zzank<BitSet> bgj = new zzank()
  {
    public void zza(zzaor paramAnonymouszzaor, BitSet paramAnonymousBitSet)
      throws IOException
    {
      if (paramAnonymousBitSet == null)
      {
        paramAnonymouszzaor.r();
        return;
      }
      paramAnonymouszzaor.n();
      int i = 0;
      if (i < paramAnonymousBitSet.length())
      {
        if (paramAnonymousBitSet.get(i)) {}
        for (int j = 1;; j = 0)
        {
          paramAnonymouszzaor.zzcp(j);
          i += 1;
          break;
        }
      }
      paramAnonymouszzaor.o();
    }
    
    public BitSet zzx(zzaop paramAnonymouszzaop)
      throws IOException
    {
      if (paramAnonymouszzaop.h() == zzaoq.bhH)
      {
        paramAnonymouszzaop.nextNull();
        return null;
      }
      BitSet localBitSet = new BitSet();
      paramAnonymouszzaop.beginArray();
      Object localObject = paramAnonymouszzaop.h();
      int i = 0;
      if (localObject != zzaoq.bhA)
      {
        boolean bool;
        switch (zzaon.26.bfU[localObject.ordinal()])
        {
        default: 
          paramAnonymouszzaop = String.valueOf(localObject);
          throw new zzanh(String.valueOf(paramAnonymouszzaop).length() + 27 + "Invalid bitset value type: " + paramAnonymouszzaop);
        case 1: 
          if (paramAnonymouszzaop.nextInt() != 0) {
            bool = true;
          }
          break;
        }
        for (;;)
        {
          if (bool) {
            localBitSet.set(i);
          }
          i += 1;
          localObject = paramAnonymouszzaop.h();
          break;
          bool = false;
          continue;
          bool = paramAnonymouszzaop.nextBoolean();
          continue;
          localObject = paramAnonymouszzaop.nextString();
          try
          {
            int j = Integer.parseInt((String)localObject);
            if (j != 0) {
              bool = true;
            } else {
              bool = false;
            }
          }
          catch (NumberFormatException paramAnonymouszzaop)
          {
            paramAnonymouszzaop = String.valueOf(localObject);
            if (paramAnonymouszzaop.length() == 0) {}
          }
        }
        for (paramAnonymouszzaop = "Error: Expecting: bitset number value (1, 0), Found: ".concat(paramAnonymouszzaop);; paramAnonymouszzaop = new String("Error: Expecting: bitset number value (1, 0), Found: ")) {
          throw new zzanh(paramAnonymouszzaop);
        }
      }
      paramAnonymouszzaop.endArray();
      return localBitSet;
    }
  };
  public static final zzanl bgk = zza(BitSet.class, bgj);
  public static final zzank<Boolean> bgl = new zzank()
  {
    public void zza(zzaor paramAnonymouszzaor, Boolean paramAnonymousBoolean)
      throws IOException
    {
      if (paramAnonymousBoolean == null)
      {
        paramAnonymouszzaor.r();
        return;
      }
      paramAnonymouszzaor.zzcz(paramAnonymousBoolean.booleanValue());
    }
    
    public Boolean zzae(zzaop paramAnonymouszzaop)
      throws IOException
    {
      if (paramAnonymouszzaop.h() == zzaoq.bhH)
      {
        paramAnonymouszzaop.nextNull();
        return null;
      }
      if (paramAnonymouszzaop.h() == zzaoq.bhE) {
        return Boolean.valueOf(Boolean.parseBoolean(paramAnonymouszzaop.nextString()));
      }
      return Boolean.valueOf(paramAnonymouszzaop.nextBoolean());
    }
  };
  public static final zzank<Boolean> bgm = new zzank()
  {
    public void zza(zzaor paramAnonymouszzaor, Boolean paramAnonymousBoolean)
      throws IOException
    {
      if (paramAnonymousBoolean == null) {}
      for (paramAnonymousBoolean = "null";; paramAnonymousBoolean = paramAnonymousBoolean.toString())
      {
        paramAnonymouszzaor.zztb(paramAnonymousBoolean);
        return;
      }
    }
    
    public Boolean zzae(zzaop paramAnonymouszzaop)
      throws IOException
    {
      if (paramAnonymouszzaop.h() == zzaoq.bhH)
      {
        paramAnonymouszzaop.nextNull();
        return null;
      }
      return Boolean.valueOf(paramAnonymouszzaop.nextString());
    }
  };
  public static final zzanl bgn = zza(Boolean.TYPE, Boolean.class, bgl);
  public static final zzank<Number> bgo = new zzank()
  {
    public void zza(zzaor paramAnonymouszzaor, Number paramAnonymousNumber)
      throws IOException
    {
      paramAnonymouszzaor.zza(paramAnonymousNumber);
    }
    
    public Number zzg(zzaop paramAnonymouszzaop)
      throws IOException
    {
      if (paramAnonymouszzaop.h() == zzaoq.bhH)
      {
        paramAnonymouszzaop.nextNull();
        return null;
      }
      try
      {
        byte b = (byte)paramAnonymouszzaop.nextInt();
        return Byte.valueOf(b);
      }
      catch (NumberFormatException paramAnonymouszzaop)
      {
        throw new zzanh(paramAnonymouszzaop);
      }
    }
  };
  public static final zzanl bgp = zza(Byte.TYPE, Byte.class, bgo);
  public static final zzank<Number> bgq = new zzank()
  {
    public void zza(zzaor paramAnonymouszzaor, Number paramAnonymousNumber)
      throws IOException
    {
      paramAnonymouszzaor.zza(paramAnonymousNumber);
    }
    
    public Number zzg(zzaop paramAnonymouszzaop)
      throws IOException
    {
      if (paramAnonymouszzaop.h() == zzaoq.bhH)
      {
        paramAnonymouszzaop.nextNull();
        return null;
      }
      try
      {
        short s = (short)paramAnonymouszzaop.nextInt();
        return Short.valueOf(s);
      }
      catch (NumberFormatException paramAnonymouszzaop)
      {
        throw new zzanh(paramAnonymouszzaop);
      }
    }
  };
  public static final zzanl bgr = zza(Short.TYPE, Short.class, bgq);
  public static final zzank<Number> bgs = new zzank()
  {
    public void zza(zzaor paramAnonymouszzaor, Number paramAnonymousNumber)
      throws IOException
    {
      paramAnonymouszzaor.zza(paramAnonymousNumber);
    }
    
    public Number zzg(zzaop paramAnonymouszzaop)
      throws IOException
    {
      if (paramAnonymouszzaop.h() == zzaoq.bhH)
      {
        paramAnonymouszzaop.nextNull();
        return null;
      }
      try
      {
        int i = paramAnonymouszzaop.nextInt();
        return Integer.valueOf(i);
      }
      catch (NumberFormatException paramAnonymouszzaop)
      {
        throw new zzanh(paramAnonymouszzaop);
      }
    }
  };
  public static final zzanl bgt = zza(Integer.TYPE, Integer.class, bgs);
  public static final zzank<Number> bgu = new zzank()
  {
    public void zza(zzaor paramAnonymouszzaor, Number paramAnonymousNumber)
      throws IOException
    {
      paramAnonymouszzaor.zza(paramAnonymousNumber);
    }
    
    public Number zzg(zzaop paramAnonymouszzaop)
      throws IOException
    {
      if (paramAnonymouszzaop.h() == zzaoq.bhH)
      {
        paramAnonymouszzaop.nextNull();
        return null;
      }
      try
      {
        long l = paramAnonymouszzaop.nextLong();
        return Long.valueOf(l);
      }
      catch (NumberFormatException paramAnonymouszzaop)
      {
        throw new zzanh(paramAnonymouszzaop);
      }
    }
  };
  public static final zzank<Number> bgv = new zzank()
  {
    public void zza(zzaor paramAnonymouszzaor, Number paramAnonymousNumber)
      throws IOException
    {
      paramAnonymouszzaor.zza(paramAnonymousNumber);
    }
    
    public Number zzg(zzaop paramAnonymouszzaop)
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
  public static final zzank<Number> bgw = new zzank()
  {
    public void zza(zzaor paramAnonymouszzaor, Number paramAnonymousNumber)
      throws IOException
    {
      paramAnonymouszzaor.zza(paramAnonymousNumber);
    }
    
    public Number zzg(zzaop paramAnonymouszzaop)
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
  public static final zzank<Number> bgx = new zzank()
  {
    public void zza(zzaor paramAnonymouszzaor, Number paramAnonymousNumber)
      throws IOException
    {
      paramAnonymouszzaor.zza(paramAnonymousNumber);
    }
    
    public Number zzg(zzaop paramAnonymouszzaop)
      throws IOException
    {
      zzaoq localzzaoq = paramAnonymouszzaop.h();
      switch (zzaon.26.bfU[localzzaoq.ordinal()])
      {
      case 2: 
      case 3: 
      default: 
        paramAnonymouszzaop = String.valueOf(localzzaoq);
        throw new zzanh(String.valueOf(paramAnonymouszzaop).length() + 23 + "Expecting number, got: " + paramAnonymouszzaop);
      case 4: 
        paramAnonymouszzaop.nextNull();
        return null;
      }
      return new zzanv(paramAnonymouszzaop.nextString());
    }
  };
  public static final zzanl bgy = zza(Number.class, bgx);
  public static final zzank<Character> bgz = new zzank()
  {
    public void zza(zzaor paramAnonymouszzaor, Character paramAnonymousCharacter)
      throws IOException
    {
      if (paramAnonymousCharacter == null) {}
      for (paramAnonymousCharacter = null;; paramAnonymousCharacter = String.valueOf(paramAnonymousCharacter))
      {
        paramAnonymouszzaor.zztb(paramAnonymousCharacter);
        return;
      }
    }
    
    public Character zzp(zzaop paramAnonymouszzaop)
      throws IOException
    {
      if (paramAnonymouszzaop.h() == zzaoq.bhH)
      {
        paramAnonymouszzaop.nextNull();
        return null;
      }
      paramAnonymouszzaop = paramAnonymouszzaop.nextString();
      if (paramAnonymouszzaop.length() != 1)
      {
        paramAnonymouszzaop = String.valueOf(paramAnonymouszzaop);
        if (paramAnonymouszzaop.length() != 0) {}
        for (paramAnonymouszzaop = "Expecting character, got: ".concat(paramAnonymouszzaop);; paramAnonymouszzaop = new String("Expecting character, got: ")) {
          throw new zzanh(paramAnonymouszzaop);
        }
      }
      return Character.valueOf(paramAnonymouszzaop.charAt(0));
    }
  };
  
  public static <TT> zzanl zza(zzaoo<TT> paramzzaoo, final zzank<TT> paramzzank)
  {
    new zzanl()
    {
      public <T> zzank<T> zza(zzams paramAnonymouszzams, zzaoo<T> paramAnonymouszzaoo)
      {
        if (paramAnonymouszzaoo.equals(bfd)) {
          return paramzzank;
        }
        return null;
      }
    };
  }
  
  public static <TT> zzanl zza(Class<TT> paramClass, final zzank<TT> paramzzank)
  {
    new zzanl()
    {
      public String toString()
      {
        String str1 = String.valueOf(bhc.getName());
        String str2 = String.valueOf(paramzzank);
        return String.valueOf(str1).length() + 23 + String.valueOf(str2).length() + "Factory[type=" + str1 + ",adapter=" + str2 + "]";
      }
      
      public <T> zzank<T> zza(zzams paramAnonymouszzams, zzaoo<T> paramAnonymouszzaoo)
      {
        if (paramAnonymouszzaoo.s() == bhc) {
          return paramzzank;
        }
        return null;
      }
    };
  }
  
  public static <TT> zzanl zza(Class<TT> paramClass1, final Class<TT> paramClass2, final zzank<? super TT> paramzzank)
  {
    new zzanl()
    {
      public String toString()
      {
        String str1 = String.valueOf(paramClass2.getName());
        String str2 = String.valueOf(bhd.getName());
        String str3 = String.valueOf(paramzzank);
        return String.valueOf(str1).length() + 24 + String.valueOf(str2).length() + String.valueOf(str3).length() + "Factory[type=" + str1 + "+" + str2 + ",adapter=" + str3 + "]";
      }
      
      public <T> zzank<T> zza(zzams paramAnonymouszzams, zzaoo<T> paramAnonymouszzaoo)
      {
        paramAnonymouszzams = paramAnonymouszzaoo.s();
        if ((paramAnonymouszzams == bhd) || (paramAnonymouszzams == paramClass2)) {
          return paramzzank;
        }
        return null;
      }
    };
  }
  
  public static <TT> zzanl zzb(Class<TT> paramClass, final zzank<TT> paramzzank)
  {
    new zzanl()
    {
      public String toString()
      {
        String str1 = String.valueOf(bhh.getName());
        String str2 = String.valueOf(paramzzank);
        return String.valueOf(str1).length() + 32 + String.valueOf(str2).length() + "Factory[typeHierarchy=" + str1 + ",adapter=" + str2 + "]";
      }
      
      public <T> zzank<T> zza(zzams paramAnonymouszzams, zzaoo<T> paramAnonymouszzaoo)
      {
        if (bhh.isAssignableFrom(paramAnonymouszzaoo.s())) {
          return paramzzank;
        }
        return null;
      }
    };
  }
  
  public static <TT> zzanl zzb(Class<TT> paramClass, final Class<? extends TT> paramClass1, final zzank<? super TT> paramzzank)
  {
    new zzanl()
    {
      public String toString()
      {
        String str1 = String.valueOf(bhf.getName());
        String str2 = String.valueOf(paramClass1.getName());
        String str3 = String.valueOf(paramzzank);
        return String.valueOf(str1).length() + 24 + String.valueOf(str2).length() + String.valueOf(str3).length() + "Factory[type=" + str1 + "+" + str2 + ",adapter=" + str3 + "]";
      }
      
      public <T> zzank<T> zza(zzams paramAnonymouszzams, zzaoo<T> paramAnonymouszzaoo)
      {
        paramAnonymouszzams = paramAnonymouszzaoo.s();
        if ((paramAnonymouszzams == bhf) || (paramAnonymouszzams == paramClass1)) {
          return paramzzank;
        }
        return null;
      }
    };
  }
  
  private static final class zza<T extends Enum<T>>
    extends zzank<T>
  {
    private final Map<String, T> bhi = new HashMap();
    private final Map<T, String> bhj = new HashMap();
    
    public zza(Class<T> paramClass)
    {
      try
      {
        Enum[] arrayOfEnum = (Enum[])paramClass.getEnumConstants();
        int k = arrayOfEnum.length;
        int i = 0;
        while (i < k)
        {
          Enum localEnum = arrayOfEnum[i];
          Object localObject1 = localEnum.name();
          Object localObject2 = (zzann)paramClass.getField((String)localObject1).getAnnotation(zzann.class);
          if (localObject2 != null)
          {
            String str = ((zzann)localObject2).value();
            localObject2 = ((zzann)localObject2).zzczy();
            int m = localObject2.length;
            int j = 0;
            for (;;)
            {
              localObject1 = str;
              if (j >= m) {
                break;
              }
              localObject1 = localObject2[j];
              bhi.put(localObject1, localEnum);
              j += 1;
            }
          }
          bhi.put(localObject1, localEnum);
          bhj.put(localEnum, localObject1);
          i += 1;
        }
        return;
      }
      catch (NoSuchFieldException paramClass)
      {
        throw new AssertionError();
      }
    }
    
    public void zza(zzaor paramzzaor, T paramT)
      throws IOException
    {
      if (paramT == null) {}
      for (paramT = null;; paramT = (String)bhj.get(paramT))
      {
        paramzzaor.zztb(paramT);
        return;
      }
    }
    
    public T zzaf(zzaop paramzzaop)
      throws IOException
    {
      if (paramzzaop.h() == zzaoq.bhH)
      {
        paramzzaop.nextNull();
        return null;
      }
      return (Enum)bhi.get(paramzzaop.nextString());
    }
  }
}

/* Location:
 * Qualified Name:     com.google.android.gms.internal.zzaon
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */