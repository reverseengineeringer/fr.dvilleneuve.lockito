package com.google.android.gms.internal;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

class zzaeb
  implements zzaec
{
  private HttpURLConnection aCA;
  
  private InputStream zzd(HttpURLConnection paramHttpURLConnection)
    throws IOException
  {
    int i = paramHttpURLConnection.getResponseCode();
    if (i == 200) {
      return paramHttpURLConnection.getInputStream();
    }
    paramHttpURLConnection = 25 + "Bad response: " + i;
    if (i == 404) {
      throw new FileNotFoundException(paramHttpURLConnection);
    }
    throw new IOException(paramHttpURLConnection);
  }
  
  private void zze(HttpURLConnection paramHttpURLConnection)
  {
    if (paramHttpURLConnection != null) {
      paramHttpURLConnection.disconnect();
    }
  }
  
  public void close()
  {
    zze(aCA);
  }
  
  public InputStream zzpt(String paramString)
    throws IOException
  {
    aCA = zzpu(paramString);
    return zzd(aCA);
  }
  
  HttpURLConnection zzpu(String paramString)
    throws IOException
  {
    paramString = (HttpURLConnection)new URL(paramString).openConnection();
    paramString.setReadTimeout(20000);
    paramString.setConnectTimeout(20000);
    return paramString;
  }
}

/* Location:
 * Qualified Name:     com.google.android.gms.internal.zzaeb
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */