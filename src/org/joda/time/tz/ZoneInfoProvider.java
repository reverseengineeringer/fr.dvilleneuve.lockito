package org.joda.time.tz;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.SoftReference;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import org.joda.time.DateTimeZone;

public class ZoneInfoProvider
  implements Provider
{
  private final File iFileDir;
  private final ClassLoader iLoader;
  private final String iResourcePath;
  private final Map<String, Object> iZoneInfoMap;
  
  public ZoneInfoProvider(File paramFile)
    throws IOException
  {
    if (paramFile == null) {
      throw new IllegalArgumentException("No file directory provided");
    }
    if (!paramFile.exists()) {
      throw new IOException("File directory doesn't exist: " + paramFile);
    }
    if (!paramFile.isDirectory()) {
      throw new IOException("File doesn't refer to a directory: " + paramFile);
    }
    iFileDir = paramFile;
    iResourcePath = null;
    iLoader = null;
    iZoneInfoMap = loadZoneInfoMap(openResource("ZoneInfoMap"));
  }
  
  public ZoneInfoProvider(String paramString)
    throws IOException
  {
    this(paramString, null, false);
  }
  
  public ZoneInfoProvider(String paramString, ClassLoader paramClassLoader)
    throws IOException
  {
    this(paramString, paramClassLoader, true);
  }
  
  private ZoneInfoProvider(String paramString, ClassLoader paramClassLoader, boolean paramBoolean)
    throws IOException
  {
    if (paramString == null) {
      throw new IllegalArgumentException("No resource path provided");
    }
    String str = paramString;
    if (!paramString.endsWith("/")) {
      str = paramString + '/';
    }
    iFileDir = null;
    iResourcePath = str;
    paramString = paramClassLoader;
    if (paramClassLoader == null)
    {
      paramString = paramClassLoader;
      if (!paramBoolean) {
        paramString = getClass().getClassLoader();
      }
    }
    iLoader = paramString;
    iZoneInfoMap = loadZoneInfoMap(openResource("ZoneInfoMap"));
  }
  
  /* Error */
  private DateTimeZone loadZoneData(String paramString)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore_3
    //   2: aconst_null
    //   3: astore_2
    //   4: aload_0
    //   5: aload_1
    //   6: invokespecial 69	org/joda/time/tz/ZoneInfoProvider:openResource	(Ljava/lang/String;)Ljava/io/InputStream;
    //   9: astore 4
    //   11: aload 4
    //   13: astore_2
    //   14: aload 4
    //   16: astore_3
    //   17: aload 4
    //   19: aload_1
    //   20: invokestatic 112	org/joda/time/tz/DateTimeZoneBuilder:readFrom	(Ljava/io/InputStream;Ljava/lang/String;)Lorg/joda/time/DateTimeZone;
    //   23: astore 5
    //   25: aload 4
    //   27: astore_2
    //   28: aload 4
    //   30: astore_3
    //   31: aload_0
    //   32: getfield 75	org/joda/time/tz/ZoneInfoProvider:iZoneInfoMap	Ljava/util/Map;
    //   35: aload_1
    //   36: new 114	java/lang/ref/SoftReference
    //   39: dup
    //   40: aload 5
    //   42: invokespecial 117	java/lang/ref/SoftReference:<init>	(Ljava/lang/Object;)V
    //   45: invokeinterface 123 3 0
    //   50: pop
    //   51: aload 5
    //   53: astore_1
    //   54: aload 4
    //   56: ifnull +11 -> 67
    //   59: aload 4
    //   61: invokevirtual 128	java/io/InputStream:close	()V
    //   64: aload 5
    //   66: astore_1
    //   67: aload_1
    //   68: areturn
    //   69: astore 4
    //   71: aload_2
    //   72: astore_3
    //   73: aload_0
    //   74: aload 4
    //   76: invokevirtual 132	org/joda/time/tz/ZoneInfoProvider:uncaughtException	(Ljava/lang/Exception;)V
    //   79: aload_2
    //   80: astore_3
    //   81: aload_0
    //   82: getfield 75	org/joda/time/tz/ZoneInfoProvider:iZoneInfoMap	Ljava/util/Map;
    //   85: aload_1
    //   86: invokeinterface 136 2 0
    //   91: pop
    //   92: aconst_null
    //   93: astore_1
    //   94: aload_2
    //   95: ifnull -28 -> 67
    //   98: aload_2
    //   99: invokevirtual 128	java/io/InputStream:close	()V
    //   102: aconst_null
    //   103: areturn
    //   104: astore_1
    //   105: aconst_null
    //   106: areturn
    //   107: astore_1
    //   108: aload_3
    //   109: ifnull +7 -> 116
    //   112: aload_3
    //   113: invokevirtual 128	java/io/InputStream:close	()V
    //   116: aload_1
    //   117: athrow
    //   118: astore_1
    //   119: aload 5
    //   121: areturn
    //   122: astore_2
    //   123: goto -7 -> 116
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	126	0	this	ZoneInfoProvider
    //   0	126	1	paramString	String
    //   3	96	2	localObject1	Object
    //   122	1	2	localIOException1	IOException
    //   1	112	3	localObject2	Object
    //   9	51	4	localInputStream	InputStream
    //   69	6	4	localIOException2	IOException
    //   23	97	5	localDateTimeZone	DateTimeZone
    // Exception table:
    //   from	to	target	type
    //   4	11	69	java/io/IOException
    //   17	25	69	java/io/IOException
    //   31	51	69	java/io/IOException
    //   98	102	104	java/io/IOException
    //   4	11	107	finally
    //   17	25	107	finally
    //   31	51	107	finally
    //   73	79	107	finally
    //   81	92	107	finally
    //   59	64	118	java/io/IOException
    //   112	116	122	java/io/IOException
  }
  
  /* Error */
  private static Map<String, Object> loadZoneInfoMap(InputStream paramInputStream)
    throws IOException
  {
    // Byte code:
    //   0: new 138	java/util/concurrent/ConcurrentHashMap
    //   3: dup
    //   4: invokespecial 139	java/util/concurrent/ConcurrentHashMap:<init>	()V
    //   7: astore_1
    //   8: new 141	java/io/DataInputStream
    //   11: dup
    //   12: aload_0
    //   13: invokespecial 144	java/io/DataInputStream:<init>	(Ljava/io/InputStream;)V
    //   16: astore_0
    //   17: aload_0
    //   18: aload_1
    //   19: invokestatic 148	org/joda/time/tz/ZoneInfoProvider:readZoneInfoMap	(Ljava/io/DataInputStream;Ljava/util/Map;)V
    //   22: aload_0
    //   23: invokevirtual 149	java/io/DataInputStream:close	()V
    //   26: aload_1
    //   27: ldc -105
    //   29: new 114	java/lang/ref/SoftReference
    //   32: dup
    //   33: getstatic 156	org/joda/time/DateTimeZone:UTC	Lorg/joda/time/DateTimeZone;
    //   36: invokespecial 117	java/lang/ref/SoftReference:<init>	(Ljava/lang/Object;)V
    //   39: invokeinterface 123 3 0
    //   44: pop
    //   45: aload_1
    //   46: areturn
    //   47: astore_1
    //   48: aload_0
    //   49: invokevirtual 149	java/io/DataInputStream:close	()V
    //   52: aload_1
    //   53: athrow
    //   54: astore_0
    //   55: goto -29 -> 26
    //   58: astore_0
    //   59: goto -7 -> 52
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	62	0	paramInputStream	InputStream
    //   7	39	1	localConcurrentHashMap	java.util.concurrent.ConcurrentHashMap
    //   47	6	1	localObject	Object
    // Exception table:
    //   from	to	target	type
    //   17	22	47	finally
    //   22	26	54	java/io/IOException
    //   48	52	58	java/io/IOException
  }
  
  private InputStream openResource(String paramString)
    throws IOException
  {
    if (iFileDir != null) {
      localObject = new FileInputStream(new File(iFileDir, paramString));
    }
    String str;
    do
    {
      return (InputStream)localObject;
      str = iResourcePath.concat(paramString);
      if (iLoader == null) {
        break;
      }
      paramString = iLoader.getResourceAsStream(str);
      localObject = paramString;
    } while (paramString != null);
    Object localObject = new StringBuilder(40).append("Resource not found: \"").append(str).append("\" ClassLoader: ");
    if (iLoader != null) {}
    for (paramString = iLoader.toString();; paramString = "system")
    {
      throw new IOException(paramString);
      paramString = ClassLoader.getSystemResourceAsStream(str);
      break;
    }
  }
  
  private static void readZoneInfoMap(DataInputStream paramDataInputStream, Map<String, Object> paramMap)
    throws IOException
  {
    int j = paramDataInputStream.readUnsignedShort();
    String[] arrayOfString = new String[j];
    int i = 0;
    while (i < j)
    {
      arrayOfString[i] = paramDataInputStream.readUTF().intern();
      i += 1;
    }
    j = paramDataInputStream.readUnsignedShort();
    i = 0;
    while (i < j) {
      try
      {
        paramMap.put(arrayOfString[paramDataInputStream.readUnsignedShort()], arrayOfString[paramDataInputStream.readUnsignedShort()]);
        i += 1;
      }
      catch (ArrayIndexOutOfBoundsException paramDataInputStream)
      {
        throw new IOException("Corrupt zone info map");
      }
    }
  }
  
  public Set<String> getAvailableIDs()
  {
    return new TreeSet(iZoneInfoMap.keySet());
  }
  
  public DateTimeZone getZone(String paramString)
  {
    Object localObject1 = null;
    if (paramString == null) {}
    Object localObject2;
    do
    {
      do
      {
        return (DateTimeZone)localObject1;
        localObject2 = iZoneInfoMap.get(paramString);
      } while (localObject2 == null);
      if (paramString.equals(localObject2)) {
        return loadZoneData(paramString);
      }
      if (!(localObject2 instanceof SoftReference)) {
        break;
      }
      localObject2 = (DateTimeZone)((SoftReference)localObject2).get();
      localObject1 = localObject2;
    } while (localObject2 != null);
    return loadZoneData(paramString);
    return getZone((String)localObject2);
  }
  
  protected void uncaughtException(Exception paramException)
  {
    paramException.printStackTrace();
  }
}

/* Location:
 * Qualified Name:     org.joda.time.tz.ZoneInfoProvider
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */