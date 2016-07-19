package io.fabric.sdk.android.services.settings;

import io.fabric.sdk.android.services.common.CurrentTimeProvider;
import org.json.JSONException;
import org.json.JSONObject;

class DefaultSettingsJsonTransform
  implements SettingsJsonTransform
{
  private AnalyticsSettingsData buildAnalyticsSessionDataFrom(JSONObject paramJSONObject)
  {
    return new AnalyticsSettingsData(paramJSONObject.optString("url", "https://e.crashlytics.com/spi/v2/events"), paramJSONObject.optInt("flush_interval_secs", 600), paramJSONObject.optInt("max_byte_size_per_file", 8000), paramJSONObject.optInt("max_file_count_per_send", 1), paramJSONObject.optInt("max_pending_send_file_count", 100));
  }
  
  private AppSettingsData buildAppDataFrom(JSONObject paramJSONObject)
    throws JSONException
  {
    String str1 = paramJSONObject.getString("identifier");
    String str2 = paramJSONObject.getString("status");
    String str3 = paramJSONObject.getString("url");
    String str4 = paramJSONObject.getString("reports_url");
    boolean bool = paramJSONObject.optBoolean("update_required", false);
    Object localObject2 = null;
    Object localObject1 = localObject2;
    if (paramJSONObject.has("icon"))
    {
      localObject1 = localObject2;
      if (paramJSONObject.getJSONObject("icon").has("hash")) {
        localObject1 = buildIconDataFrom(paramJSONObject.getJSONObject("icon"));
      }
    }
    return new AppSettingsData(str1, str2, str3, str4, bool, (AppIconSettingsData)localObject1);
  }
  
  private BetaSettingsData buildBetaSettingsDataFrom(JSONObject paramJSONObject)
    throws JSONException
  {
    return new BetaSettingsData(paramJSONObject.optString("update_endpoint", SettingsJsonConstants.BETA_UPDATE_ENDPOINT_DEFAULT), paramJSONObject.optInt("update_suspend_duration", 3600));
  }
  
  private FeaturesSettingsData buildFeaturesSessionDataFrom(JSONObject paramJSONObject)
  {
    return new FeaturesSettingsData(paramJSONObject.optBoolean("prompt_enabled", false), paramJSONObject.optBoolean("collect_logged_exceptions", true), paramJSONObject.optBoolean("collect_reports", true), paramJSONObject.optBoolean("collect_analytics", false));
  }
  
  private AppIconSettingsData buildIconDataFrom(JSONObject paramJSONObject)
    throws JSONException
  {
    return new AppIconSettingsData(paramJSONObject.getString("hash"), paramJSONObject.getInt("width"), paramJSONObject.getInt("height"));
  }
  
  private PromptSettingsData buildPromptDataFrom(JSONObject paramJSONObject)
    throws JSONException
  {
    return new PromptSettingsData(paramJSONObject.optString("title", "Send Crash Report?"), paramJSONObject.optString("message", "Looks like we crashed! Please help us fix the problem by sending a crash report."), paramJSONObject.optString("send_button_title", "Send"), paramJSONObject.optBoolean("show_cancel_button", true), paramJSONObject.optString("cancel_button_title", "Don't Send"), paramJSONObject.optBoolean("show_always_send_button", true), paramJSONObject.optString("always_send_button_title", "Always Send"));
  }
  
  private SessionSettingsData buildSessionDataFrom(JSONObject paramJSONObject)
    throws JSONException
  {
    return new SessionSettingsData(paramJSONObject.optInt("log_buffer_size", 64000), paramJSONObject.optInt("max_chained_exception_depth", 8), paramJSONObject.optInt("max_custom_exception_events", 64), paramJSONObject.optInt("max_custom_key_value_pairs", 64), paramJSONObject.optInt("identifier_mask", 255), paramJSONObject.optBoolean("send_session_without_crash", false));
  }
  
  private long getExpiresAtFrom(CurrentTimeProvider paramCurrentTimeProvider, long paramLong, JSONObject paramJSONObject)
    throws JSONException
  {
    if (paramJSONObject.has("expires_at")) {
      return paramJSONObject.getLong("expires_at");
    }
    return paramCurrentTimeProvider.getCurrentTimeMillis() + 1000L * paramLong;
  }
  
  private JSONObject toAnalyticsJson(AnalyticsSettingsData paramAnalyticsSettingsData)
    throws JSONException
  {
    return new JSONObject().put("url", analyticsURL).put("flush_interval_secs", flushIntervalSeconds).put("max_byte_size_per_file", maxByteSizePerFile).put("max_file_count_per_send", maxFileCountPerSend).put("max_pending_send_file_count", maxPendingSendFileCount);
  }
  
  private JSONObject toAppJson(AppSettingsData paramAppSettingsData)
    throws JSONException
  {
    JSONObject localJSONObject = new JSONObject().put("identifier", identifier).put("status", status).put("url", url).put("reports_url", reportsUrl).put("update_required", updateRequired);
    if (icon != null) {
      localJSONObject.put("icon", toIconJson(icon));
    }
    return localJSONObject;
  }
  
  private JSONObject toBetaJson(BetaSettingsData paramBetaSettingsData)
    throws JSONException
  {
    return new JSONObject().put("update_endpoint", updateUrl).put("update_suspend_duration", updateSuspendDurationSeconds);
  }
  
  private JSONObject toFeaturesJson(FeaturesSettingsData paramFeaturesSettingsData)
    throws JSONException
  {
    return new JSONObject().put("collect_logged_exceptions", collectLoggedException).put("collect_reports", collectReports).put("collect_analytics", collectAnalytics);
  }
  
  private JSONObject toIconJson(AppIconSettingsData paramAppIconSettingsData)
    throws JSONException
  {
    return new JSONObject().put("hash", hash).put("width", width).put("height", height);
  }
  
  private JSONObject toPromptJson(PromptSettingsData paramPromptSettingsData)
    throws JSONException
  {
    return new JSONObject().put("title", title).put("message", message).put("send_button_title", sendButtonTitle).put("show_cancel_button", showCancelButton).put("cancel_button_title", cancelButtonTitle).put("show_always_send_button", showAlwaysSendButton).put("always_send_button_title", alwaysSendButtonTitle);
  }
  
  private JSONObject toSessionJson(SessionSettingsData paramSessionSettingsData)
    throws JSONException
  {
    return new JSONObject().put("log_buffer_size", logBufferSize).put("max_chained_exception_depth", maxChainedExceptionDepth).put("max_custom_exception_events", maxCustomExceptionEvents).put("max_custom_key_value_pairs", maxCustomKeyValuePairs).put("identifier_mask", identifierMask).put("send_session_without_crash", sendSessionWithoutCrash);
  }
  
  public SettingsData buildFromJson(CurrentTimeProvider paramCurrentTimeProvider, JSONObject paramJSONObject)
    throws JSONException
  {
    int i = paramJSONObject.optInt("settings_version", 0);
    int j = paramJSONObject.optInt("cache_duration", 3600);
    AppSettingsData localAppSettingsData = buildAppDataFrom(paramJSONObject.getJSONObject("app"));
    SessionSettingsData localSessionSettingsData = buildSessionDataFrom(paramJSONObject.getJSONObject("session"));
    PromptSettingsData localPromptSettingsData = buildPromptDataFrom(paramJSONObject.getJSONObject("prompt"));
    FeaturesSettingsData localFeaturesSettingsData = buildFeaturesSessionDataFrom(paramJSONObject.getJSONObject("features"));
    AnalyticsSettingsData localAnalyticsSettingsData = buildAnalyticsSessionDataFrom(paramJSONObject.getJSONObject("analytics"));
    BetaSettingsData localBetaSettingsData = buildBetaSettingsDataFrom(paramJSONObject.getJSONObject("beta"));
    return new SettingsData(getExpiresAtFrom(paramCurrentTimeProvider, j, paramJSONObject), localAppSettingsData, localSessionSettingsData, localPromptSettingsData, localFeaturesSettingsData, localAnalyticsSettingsData, localBetaSettingsData, i, j);
  }
  
  public JSONObject sanitizeTraceInfo(JSONObject paramJSONObject)
    throws JSONException
  {
    paramJSONObject = new JSONObject(paramJSONObject.toString());
    paramJSONObject.getJSONObject("features").remove("collect_analytics");
    paramJSONObject.remove("analytics");
    return paramJSONObject;
  }
  
  public JSONObject toJson(SettingsData paramSettingsData)
    throws JSONException
  {
    return new JSONObject().put("expires_at", expiresAtMillis).put("cache_duration", cacheDuration).put("settings_version", settingsVersion).put("features", toFeaturesJson(featuresData)).put("analytics", toAnalyticsJson(analyticsSettingsData)).put("beta", toBetaJson(betaSettingsData)).put("app", toAppJson(appData)).put("session", toSessionJson(sessionData)).put("prompt", toPromptJson(promptData));
  }
}

/* Location:
 * Qualified Name:     io.fabric.sdk.android.services.settings.DefaultSettingsJsonTransform
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */