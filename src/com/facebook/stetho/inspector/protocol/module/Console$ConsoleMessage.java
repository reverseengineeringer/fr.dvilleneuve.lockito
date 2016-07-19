package com.facebook.stetho.inspector.protocol.module;

import android.annotation.SuppressLint;
import com.facebook.stetho.json.annotation.JsonProperty;

@SuppressLint({"UsingDefaultJsonDeserializer", "EmptyJsonPropertyUse"})
public class Console$ConsoleMessage
{
  @JsonProperty(required=true)
  public Console.MessageLevel level;
  @JsonProperty(required=true)
  public Console.MessageSource source;
  @JsonProperty(required=true)
  public String text;
}

/* Location:
 * Qualified Name:     com.facebook.stetho.inspector.protocol.module.Console.ConsoleMessage
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */