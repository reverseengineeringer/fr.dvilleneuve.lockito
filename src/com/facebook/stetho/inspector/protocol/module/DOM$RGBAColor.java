package com.facebook.stetho.inspector.protocol.module;

import android.graphics.Color;
import com.facebook.stetho.json.annotation.JsonProperty;

class DOM$RGBAColor
{
  @JsonProperty
  public Double a;
  @JsonProperty(required=true)
  public int b;
  @JsonProperty(required=true)
  public int g;
  @JsonProperty(required=true)
  public int r;
  
  public int getColor()
  {
    int i;
    if (a == null)
    {
      i = -1;
      return Color.argb(i, r, g, b);
    }
    long l = Math.round(a.doubleValue() * 255.0D);
    if (l < 0L) {
      i = 0;
    }
    for (;;)
    {
      break;
      if (l >= 255L) {
        i = -1;
      } else {
        i = (byte)(int)l;
      }
    }
  }
}

/* Location:
 * Qualified Name:     com.facebook.stetho.inspector.protocol.module.DOM.RGBAColor
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */