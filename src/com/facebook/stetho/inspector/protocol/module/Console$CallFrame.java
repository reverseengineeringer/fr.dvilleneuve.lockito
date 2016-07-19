package com.facebook.stetho.inspector.protocol.module;

import android.annotation.SuppressLint;
import com.facebook.stetho.json.annotation.JsonProperty;

@SuppressLint({"UsingDefaultJsonDeserializer", "EmptyJsonPropertyUse"})
public class Console$CallFrame
{
  @JsonProperty(required=true)
  public int columnNumber;
  @JsonProperty(required=true)
  public String functionName;
  @JsonProperty(required=true)
  public int lineNumber;
  @JsonProperty(required=true)
  public String url;
  
  public Console$CallFrame() {}
  
  public Console$CallFrame(String paramString1, String paramString2, int paramInt1, int paramInt2)
  {
    functionName = paramString1;
    url = paramString2;
    lineNumber = paramInt1;
    columnNumber = paramInt2;
  }
}

/* Location:
 * Qualified Name:     com.facebook.stetho.inspector.protocol.module.Console.CallFrame
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */