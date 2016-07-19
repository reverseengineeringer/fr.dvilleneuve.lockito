package com.facebook.stetho.inspector.protocol.module;

import com.facebook.stetho.json.annotation.JsonProperty;

class Profiler$ProfileHeader
{
  @JsonProperty(required=true)
  String title;
  @JsonProperty(required=true)
  String typeId;
  @JsonProperty(required=true)
  int uid;
}

/* Location:
 * Qualified Name:     com.facebook.stetho.inspector.protocol.module.Profiler.ProfileHeader
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */