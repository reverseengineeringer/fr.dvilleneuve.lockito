package io.fabric.sdk.android.services.network;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

final class HttpRequest$2
  implements HostnameVerifier
{
  public boolean verify(String paramString, SSLSession paramSSLSession)
  {
    return true;
  }
}

/* Location:
 * Qualified Name:     io.fabric.sdk.android.services.network.HttpRequest.2
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */