package com.google.android.gms.tagmanager;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build.VERSION;
import android.text.TextUtils;
import com.google.android.gms.common.util.zze;
import com.google.android.gms.common.util.zzh;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

class zzw
  implements DataLayer.zzc
{
  private static final String avO = String.format("CREATE TABLE IF NOT EXISTS %s ( '%s' INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, '%s' STRING NOT NULL, '%s' BLOB NOT NULL, '%s' INTEGER NOT NULL);", new Object[] { "datalayer", "ID", "key", "value", "expires" });
  private final Executor avP;
  private zza avQ;
  private int avR;
  private final Context mContext;
  private zze zzaoa;
  
  public zzw(Context paramContext)
  {
    this(paramContext, zzh.zzavi(), "google_tagmanager.db", 2000, Executors.newSingleThreadExecutor());
  }
  
  zzw(Context paramContext, zze paramzze, String paramString, int paramInt, Executor paramExecutor)
  {
    mContext = paramContext;
    zzaoa = paramzze;
    avR = paramInt;
    avP = paramExecutor;
    avQ = new zza(mContext, paramString);
  }
  
  private List<DataLayer.zza> zzag(List<zzb> paramList)
  {
    ArrayList localArrayList = new ArrayList();
    paramList = paramList.iterator();
    while (paramList.hasNext())
    {
      zzb localzzb = (zzb)paramList.next();
      localArrayList.add(new DataLayer.zza(zzaxn, zzak(avX)));
    }
    return localArrayList;
  }
  
  private List<zzb> zzah(List<DataLayer.zza> paramList)
  {
    ArrayList localArrayList = new ArrayList();
    paramList = paramList.iterator();
    while (paramList.hasNext())
    {
      DataLayer.zza localzza = (DataLayer.zza)paramList.next();
      localArrayList.add(new zzb(zzaxn, zzaj(zzcnr)));
    }
    return localArrayList;
  }
  
  /* Error */
  private byte[] zzaj(Object paramObject)
  {
    // Byte code:
    //   0: new 162	java/io/ByteArrayOutputStream
    //   3: dup
    //   4: invokespecial 163	java/io/ByteArrayOutputStream:<init>	()V
    //   7: astore_3
    //   8: new 165	java/io/ObjectOutputStream
    //   11: dup
    //   12: aload_3
    //   13: invokespecial 168	java/io/ObjectOutputStream:<init>	(Ljava/io/OutputStream;)V
    //   16: astore_2
    //   17: aload_2
    //   18: aload_1
    //   19: invokevirtual 172	java/io/ObjectOutputStream:writeObject	(Ljava/lang/Object;)V
    //   22: aload_3
    //   23: invokevirtual 176	java/io/ByteArrayOutputStream:toByteArray	()[B
    //   26: astore_1
    //   27: aload_2
    //   28: ifnull +7 -> 35
    //   31: aload_2
    //   32: invokevirtual 179	java/io/ObjectOutputStream:close	()V
    //   35: aload_3
    //   36: invokevirtual 180	java/io/ByteArrayOutputStream:close	()V
    //   39: aload_1
    //   40: areturn
    //   41: astore_1
    //   42: aconst_null
    //   43: astore_2
    //   44: aload_2
    //   45: ifnull +7 -> 52
    //   48: aload_2
    //   49: invokevirtual 179	java/io/ObjectOutputStream:close	()V
    //   52: aload_3
    //   53: invokevirtual 180	java/io/ByteArrayOutputStream:close	()V
    //   56: aconst_null
    //   57: areturn
    //   58: astore_1
    //   59: aconst_null
    //   60: areturn
    //   61: astore_1
    //   62: aconst_null
    //   63: astore_2
    //   64: aload_2
    //   65: ifnull +7 -> 72
    //   68: aload_2
    //   69: invokevirtual 179	java/io/ObjectOutputStream:close	()V
    //   72: aload_3
    //   73: invokevirtual 180	java/io/ByteArrayOutputStream:close	()V
    //   76: aload_1
    //   77: athrow
    //   78: astore_2
    //   79: goto -3 -> 76
    //   82: astore_1
    //   83: goto -19 -> 64
    //   86: astore_1
    //   87: goto -43 -> 44
    //   90: astore_2
    //   91: aload_1
    //   92: areturn
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	93	0	this	zzw
    //   0	93	1	paramObject	Object
    //   16	53	2	localObjectOutputStream	java.io.ObjectOutputStream
    //   78	1	2	localIOException1	java.io.IOException
    //   90	1	2	localIOException2	java.io.IOException
    //   7	66	3	localByteArrayOutputStream	java.io.ByteArrayOutputStream
    // Exception table:
    //   from	to	target	type
    //   8	17	41	java/io/IOException
    //   48	52	58	java/io/IOException
    //   52	56	58	java/io/IOException
    //   8	17	61	finally
    //   68	72	78	java/io/IOException
    //   72	76	78	java/io/IOException
    //   17	27	82	finally
    //   17	27	86	java/io/IOException
    //   31	35	90	java/io/IOException
    //   35	39	90	java/io/IOException
  }
  
  /* Error */
  private Object zzak(byte[] paramArrayOfByte)
  {
    // Byte code:
    //   0: new 184	java/io/ByteArrayInputStream
    //   3: dup
    //   4: aload_1
    //   5: invokespecial 187	java/io/ByteArrayInputStream:<init>	([B)V
    //   8: astore_3
    //   9: new 189	java/io/ObjectInputStream
    //   12: dup
    //   13: aload_3
    //   14: invokespecial 192	java/io/ObjectInputStream:<init>	(Ljava/io/InputStream;)V
    //   17: astore_1
    //   18: aload_1
    //   19: invokevirtual 195	java/io/ObjectInputStream:readObject	()Ljava/lang/Object;
    //   22: astore_2
    //   23: aload_1
    //   24: ifnull +7 -> 31
    //   27: aload_1
    //   28: invokevirtual 196	java/io/ObjectInputStream:close	()V
    //   31: aload_3
    //   32: invokevirtual 197	java/io/ByteArrayInputStream:close	()V
    //   35: aload_2
    //   36: areturn
    //   37: astore_1
    //   38: aconst_null
    //   39: astore_1
    //   40: aload_1
    //   41: ifnull +7 -> 48
    //   44: aload_1
    //   45: invokevirtual 196	java/io/ObjectInputStream:close	()V
    //   48: aload_3
    //   49: invokevirtual 197	java/io/ByteArrayInputStream:close	()V
    //   52: aconst_null
    //   53: areturn
    //   54: astore_1
    //   55: aconst_null
    //   56: areturn
    //   57: astore_1
    //   58: aconst_null
    //   59: astore_1
    //   60: aload_1
    //   61: ifnull +7 -> 68
    //   64: aload_1
    //   65: invokevirtual 196	java/io/ObjectInputStream:close	()V
    //   68: aload_3
    //   69: invokevirtual 197	java/io/ByteArrayInputStream:close	()V
    //   72: aconst_null
    //   73: areturn
    //   74: astore_1
    //   75: aconst_null
    //   76: areturn
    //   77: astore_2
    //   78: aconst_null
    //   79: astore_1
    //   80: aload_1
    //   81: ifnull +7 -> 88
    //   84: aload_1
    //   85: invokevirtual 196	java/io/ObjectInputStream:close	()V
    //   88: aload_3
    //   89: invokevirtual 197	java/io/ByteArrayInputStream:close	()V
    //   92: aload_2
    //   93: athrow
    //   94: astore_1
    //   95: goto -3 -> 92
    //   98: astore_2
    //   99: goto -19 -> 80
    //   102: astore_2
    //   103: goto -43 -> 60
    //   106: astore_2
    //   107: goto -67 -> 40
    //   110: astore_1
    //   111: aload_2
    //   112: areturn
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	113	0	this	zzw
    //   0	113	1	paramArrayOfByte	byte[]
    //   22	14	2	localObject1	Object
    //   77	16	2	localObject2	Object
    //   98	1	2	localObject3	Object
    //   102	1	2	localClassNotFoundException	ClassNotFoundException
    //   106	6	2	localIOException	java.io.IOException
    //   8	81	3	localByteArrayInputStream	java.io.ByteArrayInputStream
    // Exception table:
    //   from	to	target	type
    //   9	18	37	java/io/IOException
    //   44	48	54	java/io/IOException
    //   48	52	54	java/io/IOException
    //   9	18	57	java/lang/ClassNotFoundException
    //   64	68	74	java/io/IOException
    //   68	72	74	java/io/IOException
    //   9	18	77	finally
    //   84	88	94	java/io/IOException
    //   88	92	94	java/io/IOException
    //   18	23	98	finally
    //   18	23	102	java/lang/ClassNotFoundException
    //   18	23	106	java/io/IOException
    //   27	31	110	java/io/IOException
    //   31	35	110	java/io/IOException
  }
  
  /* Error */
  private void zzb(List<zzb> paramList, long paramLong)
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield 78	com/google/android/gms/tagmanager/zzw:zzaoa	Lcom/google/android/gms/common/util/zze;
    //   6: invokeinterface 204 1 0
    //   11: lstore 4
    //   13: aload_0
    //   14: lload 4
    //   16: invokespecial 208	com/google/android/gms/tagmanager/zzw:zzbr	(J)V
    //   19: aload_0
    //   20: aload_1
    //   21: invokeinterface 212 1 0
    //   26: invokespecial 216	com/google/android/gms/tagmanager/zzw:zzxx	(I)V
    //   29: aload_0
    //   30: aload_1
    //   31: lload 4
    //   33: lload_2
    //   34: ladd
    //   35: invokespecial 219	com/google/android/gms/tagmanager/zzw:zzc	(Ljava/util/List;J)V
    //   38: aload_0
    //   39: invokespecial 222	com/google/android/gms/tagmanager/zzw:zzcba	()V
    //   42: aload_0
    //   43: monitorexit
    //   44: return
    //   45: astore_1
    //   46: aload_0
    //   47: invokespecial 222	com/google/android/gms/tagmanager/zzw:zzcba	()V
    //   50: aload_1
    //   51: athrow
    //   52: astore_1
    //   53: aload_0
    //   54: monitorexit
    //   55: aload_1
    //   56: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	57	0	this	zzw
    //   0	57	1	paramList	List<zzb>
    //   0	57	2	paramLong	long
    //   11	21	4	l	long
    // Exception table:
    //   from	to	target	type
    //   2	38	45	finally
    //   38	42	52	finally
    //   46	52	52	finally
  }
  
  private void zzbr(long paramLong)
  {
    SQLiteDatabase localSQLiteDatabase = zzoa("Error opening database for deleteOlderThan.");
    if (localSQLiteDatabase == null) {
      return;
    }
    try
    {
      int i = localSQLiteDatabase.delete("datalayer", "expires <= ?", new String[] { Long.toString(paramLong) });
      zzbn.v(33 + "Deleted " + i + " expired items");
      return;
    }
    catch (SQLiteException localSQLiteException)
    {
      zzbn.zzcy("Error deleting old entries.");
    }
  }
  
  private void zzc(List<zzb> paramList, long paramLong)
  {
    SQLiteDatabase localSQLiteDatabase = zzoa("Error opening database for writeEntryToDatabase.");
    if (localSQLiteDatabase == null) {}
    for (;;)
    {
      return;
      paramList = paramList.iterator();
      while (paramList.hasNext())
      {
        zzb localzzb = (zzb)paramList.next();
        ContentValues localContentValues = new ContentValues();
        localContentValues.put("expires", Long.valueOf(paramLong));
        localContentValues.put("key", zzaxn);
        localContentValues.put("value", avX);
        localSQLiteDatabase.insert("datalayer", null, localContentValues);
      }
    }
  }
  
  private List<DataLayer.zza> zzcax()
  {
    try
    {
      zzbr(zzaoa.currentTimeMillis());
      List localList = zzag(zzcay());
      return localList;
    }
    finally
    {
      zzcba();
    }
  }
  
  private List<zzb> zzcay()
  {
    Object localObject = zzoa("Error opening database for loadSerialized.");
    ArrayList localArrayList = new ArrayList();
    if (localObject == null) {
      return localArrayList;
    }
    localObject = ((SQLiteDatabase)localObject).query("datalayer", new String[] { "key", "value" }, null, null, null, null, "ID", null);
    try
    {
      if (((Cursor)localObject).moveToNext()) {
        localArrayList.add(new zzb(((Cursor)localObject).getString(0), ((Cursor)localObject).getBlob(1)));
      }
      return localList;
    }
    finally
    {
      ((Cursor)localObject).close();
    }
  }
  
  private int zzcaz()
  {
    Object localObject3 = null;
    Object localObject1 = null;
    int i = 0;
    int j = 0;
    Object localObject5 = zzoa("Error opening database for getNumStoredEntries.");
    if (localObject5 == null) {}
    for (;;)
    {
      return j;
      try
      {
        localObject5 = ((SQLiteDatabase)localObject5).rawQuery("SELECT COUNT(*) from datalayer", null);
        localObject1 = localObject5;
        localObject3 = localObject5;
        if (((Cursor)localObject5).moveToFirst())
        {
          localObject1 = localObject5;
          localObject3 = localObject5;
          long l = ((Cursor)localObject5).getLong(0);
          i = (int)l;
        }
        j = i;
        return i;
      }
      catch (SQLiteException localSQLiteException)
      {
        localObject4 = localObject1;
        zzbn.zzcy("Error getting numStoredEntries");
        return 0;
      }
      finally
      {
        Object localObject4;
        if (localObject4 != null) {
          ((Cursor)localObject4).close();
        }
      }
    }
  }
  
  private void zzcba()
  {
    try
    {
      avQ.close();
      return;
    }
    catch (SQLiteException localSQLiteException) {}
  }
  
  private void zzf(String[] paramArrayOfString)
  {
    if ((paramArrayOfString == null) || (paramArrayOfString.length == 0)) {}
    SQLiteDatabase localSQLiteDatabase;
    do
    {
      return;
      localSQLiteDatabase = zzoa("Error opening database for deleteEntries.");
    } while (localSQLiteDatabase == null);
    String str = String.format("%s in (%s)", new Object[] { "ID", TextUtils.join(",", Collections.nCopies(paramArrayOfString.length, "?")) });
    try
    {
      localSQLiteDatabase.delete("datalayer", str, paramArrayOfString);
      return;
    }
    catch (SQLiteException localSQLiteException)
    {
      paramArrayOfString = String.valueOf(Arrays.toString(paramArrayOfString));
      if (paramArrayOfString.length() == 0) {}
    }
    for (paramArrayOfString = "Error deleting entries ".concat(paramArrayOfString);; paramArrayOfString = new String("Error deleting entries "))
    {
      zzbn.zzcy(paramArrayOfString);
      return;
    }
  }
  
  private void zznz(String paramString)
  {
    SQLiteDatabase localSQLiteDatabase = zzoa("Error opening database for clearKeysWithPrefix.");
    if (localSQLiteDatabase == null) {
      return;
    }
    try
    {
      int i = localSQLiteDatabase.delete("datalayer", "key = ? OR key LIKE ?", new String[] { paramString, String.valueOf(paramString).concat(".%") });
      zzbn.v(25 + "Cleared " + i + " items");
      return;
    }
    catch (SQLiteException localSQLiteException)
    {
      String str = String.valueOf(localSQLiteException);
      zzbn.zzcy(String.valueOf(paramString).length() + 44 + String.valueOf(str).length() + "Error deleting entries with key prefix: " + paramString + " (" + str + ").");
      return;
    }
    finally
    {
      zzcba();
    }
  }
  
  private SQLiteDatabase zzoa(String paramString)
  {
    try
    {
      SQLiteDatabase localSQLiteDatabase = avQ.getWritableDatabase();
      return localSQLiteDatabase;
    }
    catch (SQLiteException localSQLiteException)
    {
      zzbn.zzcy(paramString);
    }
    return null;
  }
  
  private void zzxx(int paramInt)
  {
    paramInt = zzcaz() - avR + paramInt;
    if (paramInt > 0)
    {
      List localList = zzxy(paramInt);
      paramInt = localList.size();
      zzbn.zzcx(64 + "DataLayer store full, deleting " + paramInt + " entries to make room.");
      zzf((String[])localList.toArray(new String[0]));
    }
  }
  
  /* Error */
  private List<String> zzxy(int paramInt)
  {
    // Byte code:
    //   0: new 105	java/util/ArrayList
    //   3: dup
    //   4: invokespecial 106	java/util/ArrayList:<init>	()V
    //   7: astore 6
    //   9: iload_1
    //   10: ifgt +12 -> 22
    //   13: ldc_w 426
    //   16: invokestatic 273	com/google/android/gms/tagmanager/zzbn:zzcy	(Ljava/lang/String;)V
    //   19: aload 6
    //   21: areturn
    //   22: aload_0
    //   23: ldc_w 428
    //   26: invokespecial 231	com/google/android/gms/tagmanager/zzw:zzoa	(Ljava/lang/String;)Landroid/database/sqlite/SQLiteDatabase;
    //   29: astore_3
    //   30: aload_3
    //   31: ifnonnull +6 -> 37
    //   34: aload 6
    //   36: areturn
    //   37: ldc_w 430
    //   40: iconst_1
    //   41: anewarray 4	java/lang/Object
    //   44: dup
    //   45: iconst_0
    //   46: ldc 38
    //   48: aastore
    //   49: invokestatic 50	java/lang/String:format	(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
    //   52: astore 4
    //   54: iload_1
    //   55: invokestatic 434	java/lang/Integer:toString	(I)Ljava/lang/String;
    //   58: astore 5
    //   60: aload_3
    //   61: ldc 36
    //   63: iconst_1
    //   64: anewarray 46	java/lang/String
    //   67: dup
    //   68: iconst_0
    //   69: ldc 38
    //   71: aastore
    //   72: aconst_null
    //   73: aconst_null
    //   74: aconst_null
    //   75: aconst_null
    //   76: aload 4
    //   78: aload 5
    //   80: invokevirtual 307	android/database/sqlite/SQLiteDatabase:query	(Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   83: astore 4
    //   85: aload 4
    //   87: astore_3
    //   88: aload 4
    //   90: invokeinterface 334 1 0
    //   95: ifeq +40 -> 135
    //   98: aload 4
    //   100: astore_3
    //   101: aload 6
    //   103: aload 4
    //   105: iconst_0
    //   106: invokeinterface 338 2 0
    //   111: invokestatic 436	java/lang/String:valueOf	(J)Ljava/lang/String;
    //   114: invokeinterface 142 2 0
    //   119: pop
    //   120: aload 4
    //   122: astore_3
    //   123: aload 4
    //   125: invokeinterface 312 1 0
    //   130: istore_2
    //   131: iload_2
    //   132: ifne -34 -> 98
    //   135: aload 4
    //   137: ifnull +10 -> 147
    //   140: aload 4
    //   142: invokeinterface 321 1 0
    //   147: aload 6
    //   149: areturn
    //   150: astore 5
    //   152: aconst_null
    //   153: astore 4
    //   155: aload 4
    //   157: astore_3
    //   158: aload 5
    //   160: invokevirtual 439	android/database/sqlite/SQLiteException:getMessage	()Ljava/lang/String;
    //   163: invokestatic 372	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   166: astore 5
    //   168: aload 4
    //   170: astore_3
    //   171: aload 5
    //   173: invokevirtual 375	java/lang/String:length	()I
    //   176: ifeq +39 -> 215
    //   179: aload 4
    //   181: astore_3
    //   182: ldc_w 441
    //   185: aload 5
    //   187: invokevirtual 381	java/lang/String:concat	(Ljava/lang/String;)Ljava/lang/String;
    //   190: astore 5
    //   192: aload 4
    //   194: astore_3
    //   195: aload 5
    //   197: invokestatic 273	com/google/android/gms/tagmanager/zzbn:zzcy	(Ljava/lang/String;)V
    //   200: aload 4
    //   202: ifnull -55 -> 147
    //   205: aload 4
    //   207: invokeinterface 321 1 0
    //   212: goto -65 -> 147
    //   215: aload 4
    //   217: astore_3
    //   218: new 46	java/lang/String
    //   221: dup
    //   222: ldc_w 441
    //   225: invokespecial 383	java/lang/String:<init>	(Ljava/lang/String;)V
    //   228: astore 5
    //   230: goto -38 -> 192
    //   233: astore 5
    //   235: aload_3
    //   236: astore 4
    //   238: aload 5
    //   240: astore_3
    //   241: aload 4
    //   243: ifnull +10 -> 253
    //   246: aload 4
    //   248: invokeinterface 321 1 0
    //   253: aload_3
    //   254: athrow
    //   255: astore_3
    //   256: aconst_null
    //   257: astore 4
    //   259: goto -18 -> 241
    //   262: astore 5
    //   264: goto -109 -> 155
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	267	0	this	zzw
    //   0	267	1	paramInt	int
    //   130	2	2	bool	boolean
    //   29	225	3	localObject1	Object
    //   255	1	3	localObject2	Object
    //   52	206	4	localObject3	Object
    //   58	21	5	str1	String
    //   150	9	5	localSQLiteException1	SQLiteException
    //   166	63	5	str2	String
    //   233	6	5	localObject4	Object
    //   262	1	5	localSQLiteException2	SQLiteException
    //   7	141	6	localArrayList	ArrayList
    // Exception table:
    //   from	to	target	type
    //   37	85	150	android/database/sqlite/SQLiteException
    //   88	98	233	finally
    //   101	120	233	finally
    //   123	131	233	finally
    //   158	168	233	finally
    //   171	179	233	finally
    //   182	192	233	finally
    //   195	200	233	finally
    //   218	230	233	finally
    //   37	85	255	finally
    //   88	98	262	android/database/sqlite/SQLiteException
    //   101	120	262	android/database/sqlite/SQLiteException
    //   123	131	262	android/database/sqlite/SQLiteException
  }
  
  public void zza(final DataLayer.zzc.zza paramzza)
  {
    avP.execute(new Runnable()
    {
      public void run()
      {
        paramzza.zzaf(zzw.zza(zzw.this));
      }
    });
  }
  
  public void zza(final List<DataLayer.zza> paramList, final long paramLong)
  {
    paramList = zzah(paramList);
    avP.execute(new Runnable()
    {
      public void run()
      {
        zzw.zza(zzw.this, paramList, paramLong);
      }
    });
  }
  
  public void zzny(final String paramString)
  {
    avP.execute(new Runnable()
    {
      public void run()
      {
        zzw.zza(zzw.this, paramString);
      }
    });
  }
  
  class zza
    extends SQLiteOpenHelper
  {
    zza(Context paramContext, String paramString)
    {
      super(paramString, null, 1);
    }
    
    /* Error */
    private boolean zza(String paramString, SQLiteDatabase paramSQLiteDatabase)
    {
      // Byte code:
      //   0: aconst_null
      //   1: astore 4
      //   3: aload_2
      //   4: ldc 22
      //   6: iconst_1
      //   7: anewarray 24	java/lang/String
      //   10: dup
      //   11: iconst_0
      //   12: ldc 26
      //   14: aastore
      //   15: ldc 28
      //   17: iconst_1
      //   18: anewarray 24	java/lang/String
      //   21: dup
      //   22: iconst_0
      //   23: aload_1
      //   24: aastore
      //   25: aconst_null
      //   26: aconst_null
      //   27: aconst_null
      //   28: invokevirtual 34	android/database/sqlite/SQLiteDatabase:query	(Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
      //   31: astore_2
      //   32: aload_2
      //   33: invokeinterface 40 1 0
      //   38: istore_3
      //   39: aload_2
      //   40: ifnull +9 -> 49
      //   43: aload_2
      //   44: invokeinterface 44 1 0
      //   49: iload_3
      //   50: ireturn
      //   51: astore_2
      //   52: aconst_null
      //   53: astore_2
      //   54: aload_1
      //   55: invokestatic 48	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
      //   58: astore_1
      //   59: aload_1
      //   60: invokevirtual 52	java/lang/String:length	()I
      //   63: ifeq +26 -> 89
      //   66: ldc 54
      //   68: aload_1
      //   69: invokevirtual 58	java/lang/String:concat	(Ljava/lang/String;)Ljava/lang/String;
      //   72: astore_1
      //   73: aload_1
      //   74: invokestatic 64	com/google/android/gms/tagmanager/zzbn:zzcy	(Ljava/lang/String;)V
      //   77: aload_2
      //   78: ifnull +9 -> 87
      //   81: aload_2
      //   82: invokeinterface 44 1 0
      //   87: iconst_0
      //   88: ireturn
      //   89: new 24	java/lang/String
      //   92: dup
      //   93: ldc 54
      //   95: invokespecial 66	java/lang/String:<init>	(Ljava/lang/String;)V
      //   98: astore_1
      //   99: goto -26 -> 73
      //   102: astore_1
      //   103: aload_2
      //   104: ifnull +9 -> 113
      //   107: aload_2
      //   108: invokeinterface 44 1 0
      //   113: aload_1
      //   114: athrow
      //   115: astore_1
      //   116: aload 4
      //   118: astore_2
      //   119: goto -16 -> 103
      //   122: astore_1
      //   123: goto -20 -> 103
      //   126: astore 4
      //   128: goto -74 -> 54
      // Local variable table:
      //   start	length	slot	name	signature
      //   0	131	0	this	zza
      //   0	131	1	paramString	String
      //   0	131	2	paramSQLiteDatabase	SQLiteDatabase
      //   38	12	3	bool	boolean
      //   1	116	4	localObject	Object
      //   126	1	4	localSQLiteException	SQLiteException
      // Exception table:
      //   from	to	target	type
      //   3	32	51	android/database/sqlite/SQLiteException
      //   54	73	102	finally
      //   73	77	102	finally
      //   89	99	102	finally
      //   3	32	115	finally
      //   32	39	122	finally
      //   32	39	126	android/database/sqlite/SQLiteException
    }
    
    private void zzc(SQLiteDatabase paramSQLiteDatabase)
    {
      paramSQLiteDatabase = paramSQLiteDatabase.rawQuery("SELECT * FROM datalayer WHERE 0", null);
      HashSet localHashSet = new HashSet();
      try
      {
        String[] arrayOfString = paramSQLiteDatabase.getColumnNames();
        int i = 0;
        while (i < arrayOfString.length)
        {
          localHashSet.add(arrayOfString[i]);
          i += 1;
        }
        paramSQLiteDatabase.close();
        if ((!localHashSet.remove("key")) || (!localHashSet.remove("value")) || (!localHashSet.remove("ID")) || (!localHashSet.remove("expires"))) {
          throw new SQLiteException("Database column missing");
        }
      }
      finally
      {
        paramSQLiteDatabase.close();
      }
      if (!((Set)localObject).isEmpty()) {
        throw new SQLiteException("Database has extra columns");
      }
    }
    
    public SQLiteDatabase getWritableDatabase()
    {
      Object localObject1 = null;
      try
      {
        localObject2 = super.getWritableDatabase();
        localObject1 = localObject2;
      }
      catch (SQLiteException localSQLiteException)
      {
        for (;;)
        {
          Object localObject2;
          zzw.zzb(zzw.this).getDatabasePath("google_tagmanager.db").delete();
        }
      }
      localObject2 = localObject1;
      if (localObject1 == null) {
        localObject2 = super.getWritableDatabase();
      }
      return (SQLiteDatabase)localObject2;
    }
    
    public void onCreate(SQLiteDatabase paramSQLiteDatabase)
    {
      zzam.zzet(paramSQLiteDatabase.getPath());
    }
    
    public void onOpen(SQLiteDatabase paramSQLiteDatabase)
    {
      Cursor localCursor;
      if (Build.VERSION.SDK_INT < 15) {
        localCursor = paramSQLiteDatabase.rawQuery("PRAGMA journal_mode=memory", null);
      }
      try
      {
        localCursor.moveToFirst();
        localCursor.close();
        if (!zza("datalayer", paramSQLiteDatabase))
        {
          paramSQLiteDatabase.execSQL(zzw.zzcbb());
          return;
        }
      }
      finally
      {
        localCursor.close();
      }
      zzc(paramSQLiteDatabase);
    }
    
    public void onUpgrade(SQLiteDatabase paramSQLiteDatabase, int paramInt1, int paramInt2) {}
  }
  
  private static class zzb
  {
    final byte[] avX;
    final String zzaxn;
    
    zzb(String paramString, byte[] paramArrayOfByte)
    {
      zzaxn = paramString;
      avX = paramArrayOfByte;
    }
    
    public String toString()
    {
      String str = zzaxn;
      int i = Arrays.hashCode(avX);
      return String.valueOf(str).length() + 54 + "KeyAndSerialized: key = " + str + " serialized hash = " + i;
    }
  }
}

/* Location:
 * Qualified Name:     com.google.android.gms.tagmanager.zzw
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */