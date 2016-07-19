package io.fabric.sdk.android.services.network;

import java.security.cert.X509Certificate;
import javax.net.ssl.X509TrustManager;

final class HttpRequest$1
  implements X509TrustManager
{
  public void checkClientTrusted(X509Certificate[] paramArrayOfX509Certificate, String paramString) {}
  
  public void checkServerTrusted(X509Certificate[] paramArrayOfX509Certificate, String paramString) {}
  
  public X509Certificate[] getAcceptedIssuers()
  {
    return new X509Certificate[0];
  }
}

/* Location:
 * Qualified Name:     io.fabric.sdk.android.services.network.HttpRequest.1
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */