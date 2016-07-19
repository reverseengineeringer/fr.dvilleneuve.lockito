package io.fabric.sdk.android.services.settings;

public class SettingsRequest
{
  public final String apiKey;
  public final String buildVersion;
  public final String deviceId;
  public final String displayVersion;
  public final String iconHash;
  public final String instanceId;
  public final int source;
  
  public SettingsRequest(String paramString1, String paramString2, String paramString3, String paramString4, String paramString5, int paramInt, String paramString6)
  {
    apiKey = paramString1;
    deviceId = paramString2;
    instanceId = paramString3;
    displayVersion = paramString4;
    buildVersion = paramString5;
    source = paramInt;
    iconHash = paramString6;
  }
}

/* Location:
 * Qualified Name:     io.fabric.sdk.android.services.settings.SettingsRequest
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */