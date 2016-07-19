package com.crashlytics.android;

import android.content.Context;
import io.fabric.sdk.android.Fabric;
import io.fabric.sdk.android.Logger;
import io.fabric.sdk.android.services.common.CommonUtils;
import io.fabric.sdk.android.services.common.QueueFile;
import io.fabric.sdk.android.services.common.QueueFile.ElementReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Locale;
import java.util.UUID;

class LogFileManager
{
  static final int MAX_LOG_SIZE = 65536;
  private final Context context;
  private final File filesDir;
  private QueueFile logFile;
  
  public LogFileManager(Context paramContext, File paramFile)
  {
    this(paramContext, paramFile, null);
  }
  
  LogFileManager(Context paramContext, File paramFile, QueueFile paramQueueFile)
  {
    context = paramContext;
    filesDir = paramFile;
    logFile = paramQueueFile;
  }
  
  private boolean initLogFile()
  {
    if (!CommonUtils.getBooleanResourceValue(context, "com.crashlytics.CollectCustomLogs", true))
    {
      Fabric.getLogger().d("Fabric", "Preferences requested no custom logs. Aborting log file creation.");
      return false;
    }
    CommonUtils.closeOrLog(logFile, "Could not close log file: " + logFile);
    localObject3 = null;
    try
    {
      Object localObject1 = "crashlytics-userlog-" + UUID.randomUUID().toString() + ".temp";
      localObject1 = new File(filesDir, (String)localObject1);
      Fabric.getLogger().e("Fabric", "Could not create log file: " + localObject3, localException1);
    }
    catch (Exception localException1)
    {
      try
      {
        logFile = new QueueFile((File)localObject1);
        ((File)localObject1).delete();
        return true;
      }
      catch (Exception localException2)
      {
        for (;;)
        {
          localObject3 = localException1;
          Object localObject2 = localException2;
        }
      }
      localException1 = localException1;
    }
    return false;
  }
  
  void closeLogFile()
  {
    CommonUtils.closeOrLog(logFile, "There was a problem closing the Crashlytics log file.");
    logFile = null;
  }
  
  void doWriteToLog(int paramInt, long paramLong, String paramString)
  {
    if (logFile == null) {}
    for (;;)
    {
      return;
      String str = paramString;
      if (paramString == null) {
        str = "null";
      }
      try
      {
        int i = paramInt / 4;
        paramString = str;
        if (str.length() > i) {
          paramString = "..." + str.substring(str.length() - i);
        }
        paramString = paramString.replaceAll("\r", " ").replaceAll("\n", " ");
        paramString = String.format(Locale.US, "%d %s%n", new Object[] { Long.valueOf(paramLong), paramString }).getBytes("UTF-8");
        logFile.add(paramString);
        while ((!logFile.isEmpty()) && (logFile.usedBytes() > paramInt)) {
          logFile.remove();
        }
        return;
      }
      catch (IOException paramString)
      {
        Fabric.getLogger().e("Fabric", "There was a problem writing to the Crashlytics log.", paramString);
      }
    }
  }
  
  ByteString getByteStringForLog()
  {
    if (logFile == null) {
      return null;
    }
    final int[] arrayOfInt = new int[1];
    arrayOfInt[0] = 0;
    final byte[] arrayOfByte = new byte[logFile.usedBytes()];
    try
    {
      logFile.forEach(new QueueFile.ElementReader()
      {
        public void read(InputStream paramAnonymousInputStream, int paramAnonymousInt)
          throws IOException
        {
          try
          {
            paramAnonymousInputStream.read(arrayOfByte, arrayOfInt[0], paramAnonymousInt);
            int[] arrayOfInt = arrayOfInt;
            arrayOfInt[0] += paramAnonymousInt;
            return;
          }
          finally
          {
            paramAnonymousInputStream.close();
          }
        }
      });
      return ByteString.copyFrom(arrayOfByte, 0, arrayOfInt[0]);
    }
    catch (IOException localIOException)
    {
      for (;;)
      {
        Fabric.getLogger().e("Fabric", "A problem occurred while reading the Crashlytics log file.", localIOException);
      }
    }
  }
  
  public void writeToLog(long paramLong, String paramString)
  {
    if (logFile == null) {
      initLogFile();
    }
    doWriteToLog(65536, paramLong, paramString);
  }
}

/* Location:
 * Qualified Name:     com.crashlytics.android.LogFileManager
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */