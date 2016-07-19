package com.crashlytics.android;

import android.content.Context;
import io.fabric.sdk.android.Fabric;
import io.fabric.sdk.android.Logger;
import io.fabric.sdk.android.services.common.ApiKey;
import io.fabric.sdk.android.services.common.BackgroundPriorityRunnable;
import java.io.File;
import java.io.FilenameFilter;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

class ReportUploader
{
  private static final String CLS_FILE_EXT = ".cls";
  static final Map<String, String> HEADER_INVALID_CLS_FILE = Collections.singletonMap("X-CRASHLYTICS-INVALID-SESSION", "1");
  private static final short[] RETRY_INTERVALS = { 10, 20, 30, 60, 120, 300 };
  private static final FilenameFilter crashFileFilter = new FilenameFilter()
  {
    public boolean accept(File paramAnonymousFile, String paramAnonymousString)
    {
      return (paramAnonymousString.endsWith(".cls")) && (!paramAnonymousString.contains("Session"));
    }
  };
  private final CreateReportSpiCall createReportCall;
  private final Object fileAccessLock = new Object();
  private Thread uploadThread;
  
  public ReportUploader(CreateReportSpiCall paramCreateReportSpiCall)
  {
    if (paramCreateReportSpiCall == null) {
      throw new IllegalArgumentException("createReportCall must not be null.");
    }
    createReportCall = paramCreateReportSpiCall;
  }
  
  List<Report> findReports()
  {
    Fabric.getLogger().d("Fabric", "Checking for crash reports...");
    synchronized (fileAccessLock)
    {
      File[] arrayOfFile = Crashlytics.getInstance().getSdkDirectory().listFiles(crashFileFilter);
      ??? = new LinkedList();
      int j = arrayOfFile.length;
      int i = 0;
      if (i < j)
      {
        File localFile = arrayOfFile[i];
        Fabric.getLogger().d("Fabric", "Found crash report " + localFile.getPath());
        ((List)???).add(new SessionReport(localFile));
        i += 1;
      }
    }
    if (((List)???).isEmpty()) {
      Fabric.getLogger().d("Fabric", "No reports found.");
    }
    return (List<Report>)???;
  }
  
  boolean forceUpload(Report paramReport)
  {
    boolean bool2 = false;
    synchronized (fileAccessLock)
    {
      try
      {
        localObject1 = Crashlytics.getInstance().getContext();
        localObject1 = new CreateReportRequest(new ApiKey().getValue((Context)localObject1), paramReport);
        boolean bool3 = createReportCall.invoke((CreateReportRequest)localObject1);
        Logger localLogger = Fabric.getLogger();
        StringBuilder localStringBuilder = new StringBuilder().append("Crashlytics report upload ");
        if (!bool3) {
          break label131;
        }
        localObject1 = "complete: ";
        localLogger.i("Fabric", (String)localObject1 + paramReport.getFileName());
        bool1 = bool2;
        if (bool3)
        {
          paramReport.remove();
          bool1 = true;
        }
      }
      catch (Exception localException)
      {
        for (;;)
        {
          Object localObject1;
          label131:
          Fabric.getLogger().e("Fabric", "Error occurred sending report " + paramReport, localException);
          boolean bool1 = bool2;
        }
      }
      return bool1;
      localObject1 = "FAILED: ";
    }
  }
  
  boolean isUploading()
  {
    return uploadThread != null;
  }
  
  public void uploadReports()
  {
    uploadReports(0.0F);
  }
  
  public void uploadReports(float paramFloat)
  {
    try
    {
      if (uploadThread == null)
      {
        uploadThread = new Thread(new Worker(paramFloat), "Crashlytics Report Uploader");
        uploadThread.start();
      }
      return;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  private class Worker
    extends BackgroundPriorityRunnable
  {
    private final float delay;
    
    Worker(float paramFloat)
    {
      delay = paramFloat;
    }
    
    private void attemptUploadWithRetry()
    {
      Fabric.getLogger().d("Fabric", "Starting report processing in " + delay + " second(s)...");
      if (delay > 0.0F) {}
      Object localObject2;
      CrashlyticsUncaughtExceptionHandler localCrashlyticsUncaughtExceptionHandler;
      List localList;
      break label194;
      label86:
      Object localObject1;
      for (;;)
      {
        try
        {
          Thread.sleep((delay * 1000.0F));
          localObject2 = Crashlytics.getInstance();
          localCrashlyticsUncaughtExceptionHandler = ((Crashlytics)localObject2).getHandler();
          localList = findReports();
          if (localCrashlyticsUncaughtExceptionHandler.isHandlingException()) {
            return;
          }
        }
        catch (InterruptedException localInterruptedException1)
        {
          Thread.currentThread().interrupt();
          return;
        }
        if ((localInterruptedException1.isEmpty()) || (((Crashlytics)localObject2).canSendWithUserApproval())) {
          break;
        }
        Fabric.getLogger().d("Fabric", "User declined to send. Removing " + localInterruptedException1.size() + " Report(s).");
        localObject1 = localInterruptedException1.iterator();
        while (((Iterator)localObject1).hasNext()) {
          ((Report)((Iterator)localObject1).next()).remove();
        }
      }
      int i = 0;
      for (;;)
      {
        label194:
        if ((((List)localObject1).isEmpty()) || (Crashlytics.getInstance().getHandler().isHandlingException())) {
          break label86;
        }
        Fabric.getLogger().d("Fabric", "Attempting to send " + ((List)localObject1).size() + " report(s)");
        localObject1 = ((List)localObject1).iterator();
        while (((Iterator)localObject1).hasNext())
        {
          localObject2 = (Report)((Iterator)localObject1).next();
          forceUpload((Report)localObject2);
        }
        localObject2 = findReports();
        localObject1 = localObject2;
        if (((List)localObject2).isEmpty()) {
          break;
        }
        long l = ReportUploader.RETRY_INTERVALS[Math.min(i, ReportUploader.RETRY_INTERVALS.length - 1)];
        Fabric.getLogger().d("Fabric", "Report submisson: scheduling delayed retry in " + l + " seconds");
        try
        {
          Thread.sleep(1000L * l);
          i += 1;
          localObject1 = localObject2;
        }
        catch (InterruptedException localInterruptedException2)
        {
          Thread.currentThread().interrupt();
        }
      }
    }
    
    public void onRun()
    {
      try
      {
        attemptUploadWithRetry();
        ReportUploader.access$002(ReportUploader.this, null);
        return;
      }
      catch (Exception localException)
      {
        for (;;)
        {
          Fabric.getLogger().e("Fabric", "An unexpected error occurred while attempting to upload crash reports.", localException);
        }
      }
    }
  }
}

/* Location:
 * Qualified Name:     com.crashlytics.android.ReportUploader
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */