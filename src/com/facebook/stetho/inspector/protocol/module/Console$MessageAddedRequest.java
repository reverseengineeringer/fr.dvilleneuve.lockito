package com.facebook.stetho.inspector.protocol.module;

import android.annotation.SuppressLint;
import com.facebook.stetho.json.annotation.JsonProperty;

@SuppressLint({"UsingDefaultJsonDeserializer", "EmptyJsonPropertyUse"})
public class Console$MessageAddedRequest
{
  @JsonProperty(required=true)
  public Console.ConsoleMessage message;
}

/* Location:
 * Qualified Name:     com.facebook.stetho.inspector.protocol.module.Console.MessageAddedRequest
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */