package com.facebook.stetho.inspector.protocol.module;

import com.facebook.stetho.json.annotation.JsonProperty;

class HeapProfiler$ProfileHeader
{
  @JsonProperty(required=true)
  public String title;
  @JsonProperty(required=true)
  public int uid;
}

/* Location:
 * Qualified Name:     com.facebook.stetho.inspector.protocol.module.HeapProfiler.ProfileHeader
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */