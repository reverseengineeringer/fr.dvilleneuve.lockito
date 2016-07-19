package io.fabric.sdk.android.services.settings;

public class AnalyticsSettingsData
{
  public final String analyticsURL;
  public final int flushIntervalSeconds;
  public final int maxByteSizePerFile;
  public final int maxFileCountPerSend;
  public final int maxPendingSendFileCount;
  
  public AnalyticsSettingsData(String paramString, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    analyticsURL = paramString;
    flushIntervalSeconds = paramInt1;
    maxByteSizePerFile = paramInt2;
    maxFileCountPerSend = paramInt3;
    maxPendingSendFileCount = paramInt4;
  }
}

/* Location:
 * Qualified Name:     io.fabric.sdk.android.services.settings.AnalyticsSettingsData
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */