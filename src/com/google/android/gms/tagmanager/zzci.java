package com.google.android.gms.tagmanager;

import android.net.Uri;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

class zzci
{
  private static zzci axj;
  private volatile String auZ;
  private volatile zza axk;
  private volatile String axl;
  private volatile String axm;
  
  zzci()
  {
    clear();
  }
  
  static zzci zzcce()
  {
    try
    {
      if (axj == null) {
        axj = new zzci();
      }
      zzci localzzci = axj;
      return localzzci;
    }
    finally {}
  }
  
  private String zzoj(String paramString)
  {
    return paramString.split("&")[0].split("=")[1];
  }
  
  private String zzu(Uri paramUri)
  {
    return paramUri.getQuery().replace("&gtm_debug=x", "");
  }
  
  void clear()
  {
    axk = zza.axn;
    axl = null;
    auZ = null;
    axm = null;
  }
  
  String getContainerId()
  {
    return auZ;
  }
  
  zza zzccf()
  {
    return axk;
  }
  
  String zzccg()
  {
    return axl;
  }
  
  boolean zzt(Uri paramUri)
  {
    boolean bool = true;
    String str2;
    try
    {
      str2 = URLDecoder.decode(paramUri.toString(), "UTF-8");
      if (!str2.matches("^tagmanager.c.\\S+:\\/\\/preview\\/p\\?id=\\S+&gtm_auth=\\S+&gtm_preview=\\d+(&gtm_debug=x)?$")) {
        break label177;
      }
      str1 = String.valueOf(str2);
      if (str1.length() == 0) {
        break label149;
      }
      str1 = "Container preview url: ".concat(str1);
    }
    catch (UnsupportedEncodingException paramUri)
    {
      for (;;)
      {
        label122:
        bool = false;
        continue;
        label149:
        String str1 = new String("Container preview url: ");
      }
    }
    finally {}
    zzbn.v(str1);
    if (str2.matches(".*?&gtm_debug=x$"))
    {
      axk = zza.axp;
      axm = zzu(paramUri);
      if ((axk == zza.axo) || (axk == zza.axp))
      {
        paramUri = String.valueOf("/r?");
        str1 = String.valueOf(axm);
        if (str1.length() == 0) {
          break label301;
        }
        paramUri = paramUri.concat(str1);
        axl = paramUri;
      }
      auZ = zzoj(axm);
    }
    for (;;)
    {
      return bool;
      axk = zza.axo;
      break;
      label177:
      if (str2.matches("^tagmanager.c.\\S+:\\/\\/preview\\/p\\?id=\\S+&gtm_preview=$"))
      {
        if (zzoj(paramUri.getQuery()).equals(auZ))
        {
          paramUri = String.valueOf(auZ);
          if (paramUri.length() != 0) {}
          for (paramUri = "Exit preview mode for container: ".concat(paramUri);; paramUri = new String("Exit preview mode for container: "))
          {
            zzbn.v(paramUri);
            axk = zza.axn;
            axl = null;
            break;
          }
        }
      }
      else
      {
        paramUri = String.valueOf(str2);
        if (paramUri.length() != 0) {}
        for (paramUri = "Invalid preview uri: ".concat(paramUri);; paramUri = new String("Invalid preview uri: "))
        {
          zzbn.zzcy(paramUri);
          bool = false;
          break;
        }
        label301:
        paramUri = new String(paramUri);
        break label122;
      }
      bool = false;
    }
  }
  
  static enum zza
  {
    private zza() {}
  }
}

/* Location:
 * Qualified Name:     com.google.android.gms.tagmanager.zzci
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */