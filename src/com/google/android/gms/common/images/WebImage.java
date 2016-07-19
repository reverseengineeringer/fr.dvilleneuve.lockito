package com.google.android.gms.common.images;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.zzaa;
import java.util.Locale;
import org.json.JSONException;
import org.json.JSONObject;

public final class WebImage
  extends AbstractSafeParcelable
{
  public static final Parcelable.Creator<WebImage> CREATOR = new zzb();
  private final int mVersionCode;
  private final Uri wD;
  private final int zzaie;
  private final int zzaif;
  
  WebImage(int paramInt1, Uri paramUri, int paramInt2, int paramInt3)
  {
    mVersionCode = paramInt1;
    wD = paramUri;
    zzaie = paramInt2;
    zzaif = paramInt3;
  }
  
  public WebImage(Uri paramUri)
    throws IllegalArgumentException
  {
    this(paramUri, 0, 0);
  }
  
  public WebImage(Uri paramUri, int paramInt1, int paramInt2)
    throws IllegalArgumentException
  {
    this(1, paramUri, paramInt1, paramInt2);
    if (paramUri == null) {
      throw new IllegalArgumentException("url cannot be null");
    }
    if ((paramInt1 < 0) || (paramInt2 < 0)) {
      throw new IllegalArgumentException("width and height must not be negative");
    }
  }
  
  public WebImage(JSONObject paramJSONObject)
    throws IllegalArgumentException
  {
    this(zzn(paramJSONObject), paramJSONObject.optInt("width", 0), paramJSONObject.optInt("height", 0));
  }
  
  private static Uri zzn(JSONObject paramJSONObject)
  {
    Uri localUri = null;
    if (paramJSONObject.has("url")) {}
    try
    {
      localUri = Uri.parse(paramJSONObject.getString("url"));
      return localUri;
    }
    catch (JSONException paramJSONObject) {}
    return null;
  }
  
  public boolean equals(Object paramObject)
  {
    if (this == paramObject) {}
    do
    {
      return true;
      if ((paramObject == null) || (!(paramObject instanceof WebImage))) {
        return false;
      }
      paramObject = (WebImage)paramObject;
    } while ((zzaa.equal(wD, wD)) && (zzaie == zzaie) && (zzaif == zzaif));
    return false;
  }
  
  public int getHeight()
  {
    return zzaif;
  }
  
  public Uri getUrl()
  {
    return wD;
  }
  
  int getVersionCode()
  {
    return mVersionCode;
  }
  
  public int getWidth()
  {
    return zzaie;
  }
  
  public int hashCode()
  {
    return zzaa.hashCode(new Object[] { wD, Integer.valueOf(zzaie), Integer.valueOf(zzaif) });
  }
  
  public JSONObject toJson()
  {
    JSONObject localJSONObject = new JSONObject();
    try
    {
      localJSONObject.put("url", wD.toString());
      localJSONObject.put("width", zzaie);
      localJSONObject.put("height", zzaif);
      return localJSONObject;
    }
    catch (JSONException localJSONException) {}
    return localJSONObject;
  }
  
  public String toString()
  {
    return String.format(Locale.US, "Image %dx%d %s", new Object[] { Integer.valueOf(zzaie), Integer.valueOf(zzaif), wD.toString() });
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    zzb.zza(this, paramParcel, paramInt);
  }
}

/* Location:
 * Qualified Name:     com.google.android.gms.common.images.WebImage
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */