package com.facebook.stetho.inspector;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.Uri;
import android.net.Uri.Builder;
import com.facebook.stetho.common.ProcessUtil;
import com.facebook.stetho.server.SecureHttpRequestHandler;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import javax.annotation.Nullable;
import org.apache.http.HttpEntity;
import org.apache.http.HttpException;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.RequestLine;
import org.apache.http.entity.StringEntity;
import org.apache.http.protocol.HttpContext;
import org.apache.http.protocol.HttpRequestHandlerRegistry;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ChromeDiscoveryHandler
  extends SecureHttpRequestHandler
{
  private static final String PAGE_ID = "1";
  private static final String PATH_ACTIVATE = "/json/activate/1";
  private static final String PATH_PAGE_LIST = "/json";
  private static final String PATH_VERSION = "/json/version";
  private static final String PROTOCOL_VERSION = "1.1";
  private static final String USER_AGENT = "Stetho";
  private static final String WEBKIT_REV = "@188492";
  private static final String WEBKIT_VERSION = "537.36 (@188492)";
  private final Context mContext;
  private final String mInspectorPath;
  @Nullable
  private StringEntity mPageListResponse;
  @Nullable
  private StringEntity mVersionResponse;
  
  public ChromeDiscoveryHandler(Context paramContext, String paramString)
  {
    super(paramContext);
    mContext = paramContext;
    mInspectorPath = paramString;
  }
  
  private static StringEntity createStringEntity(String paramString1, String paramString2)
    throws UnsupportedEncodingException
  {
    paramString2 = new StringEntity(paramString2, "UTF-8");
    paramString2.setContentType(paramString1);
    return paramString2;
  }
  
  private CharSequence getAppLabel()
  {
    return mContext.getPackageManager().getApplicationLabel(mContext.getApplicationInfo());
  }
  
  private String getAppLabelAndVersion()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    PackageManager localPackageManager = mContext.getPackageManager();
    localStringBuilder.append(getAppLabel());
    localStringBuilder.append('/');
    try
    {
      localStringBuilder.append(getPackageInfomContext.getPackageName(), 0).versionName);
      return localStringBuilder.toString();
    }
    catch (PackageManager.NameNotFoundException localNameNotFoundException)
    {
      throw new RuntimeException(localNameNotFoundException);
    }
  }
  
  private void handleActivate(HttpResponse paramHttpResponse)
    throws UnsupportedEncodingException
  {
    setSuccessfulResponse(paramHttpResponse, createStringEntity("text/plain", "Target activation ignored"));
  }
  
  private void handlePageList(HttpResponse paramHttpResponse)
    throws JSONException, UnsupportedEncodingException
  {
    if (mPageListResponse == null)
    {
      JSONArray localJSONArray = new JSONArray();
      JSONObject localJSONObject = new JSONObject();
      localJSONObject.put("type", "app");
      localJSONObject.put("title", makeTitle());
      localJSONObject.put("id", "1");
      localJSONObject.put("description", "");
      localJSONObject.put("webSocketDebuggerUrl", "ws://" + mInspectorPath);
      localJSONObject.put("devtoolsFrontendUrl", new Uri.Builder().scheme("http").authority("chrome-devtools-frontend.appspot.com").appendEncodedPath("serve_rev").appendEncodedPath("@188492").appendEncodedPath("devtools.html").appendQueryParameter("ws", mInspectorPath).build().toString());
      localJSONArray.put(localJSONObject);
      mPageListResponse = createStringEntity("application/json", localJSONArray.toString());
    }
    setSuccessfulResponse(paramHttpResponse, mPageListResponse);
  }
  
  private void handleVersion(HttpResponse paramHttpResponse)
    throws JSONException, UnsupportedEncodingException
  {
    if (mVersionResponse == null)
    {
      JSONObject localJSONObject = new JSONObject();
      localJSONObject.put("WebKit-Version", "537.36 (@188492)");
      localJSONObject.put("User-Agent", "Stetho");
      localJSONObject.put("Protocol-Version", "1.1");
      localJSONObject.put("Browser", getAppLabelAndVersion());
      localJSONObject.put("Android-Package", mContext.getPackageName());
      mVersionResponse = createStringEntity("application/json", localJSONObject.toString());
    }
    setSuccessfulResponse(paramHttpResponse, mVersionResponse);
  }
  
  private String makeTitle()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(getAppLabel());
    localStringBuilder.append(" (powered by Stetho)");
    String str = ProcessUtil.getProcessName();
    int i = str.indexOf(':');
    if (i >= 0) {
      localStringBuilder.append(str.substring(i));
    }
    return localStringBuilder.toString();
  }
  
  private static void setSuccessfulResponse(HttpResponse paramHttpResponse, HttpEntity paramHttpEntity)
  {
    paramHttpResponse.setStatusCode(200);
    paramHttpResponse.setReasonPhrase("OK");
    paramHttpResponse.setEntity(paramHttpEntity);
  }
  
  protected void handleSecured(HttpRequest paramHttpRequest, HttpResponse paramHttpResponse, HttpContext paramHttpContext)
    throws HttpException, IOException
  {
    paramHttpRequest = Uri.parse(paramHttpRequest.getRequestLine().getUri());
    paramHttpContext = paramHttpRequest.getPath();
    try
    {
      if ("/json/version".equals(paramHttpContext))
      {
        handleVersion(paramHttpResponse);
        return;
      }
      if ("/json".equals(paramHttpContext))
      {
        handlePageList(paramHttpResponse);
        return;
      }
    }
    catch (JSONException paramHttpRequest)
    {
      paramHttpResponse.setStatusCode(500);
      paramHttpResponse.setReasonPhrase("Internal Server Error");
      paramHttpResponse.setEntity(new StringEntity(paramHttpRequest.toString(), "UTF-8"));
      return;
    }
    if ("/json/activate/1".equals(paramHttpContext))
    {
      handleActivate(paramHttpResponse);
      return;
    }
    paramHttpResponse.setStatusCode(501);
    paramHttpResponse.setReasonPhrase("Not Implemented");
    paramHttpResponse.setEntity(new StringEntity("No support for " + paramHttpRequest.getPath()));
  }
  
  public void register(HttpRequestHandlerRegistry paramHttpRequestHandlerRegistry)
  {
    paramHttpRequestHandlerRegistry.register("/json", this);
    paramHttpRequestHandlerRegistry.register("/json/version", this);
    paramHttpRequestHandlerRegistry.register("/json/activate/1*", this);
  }
}

/* Location:
 * Qualified Name:     com.facebook.stetho.inspector.ChromeDiscoveryHandler
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */