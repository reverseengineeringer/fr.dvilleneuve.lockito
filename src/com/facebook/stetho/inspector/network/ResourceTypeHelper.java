package com.facebook.stetho.inspector.network;

import com.facebook.stetho.inspector.protocol.module.Page.ResourceType;

public class ResourceTypeHelper
{
  private final MimeMatcher<Page.ResourceType> mMimeMatcher = new MimeMatcher();
  
  public ResourceTypeHelper()
  {
    mMimeMatcher.addRule("text/css", Page.ResourceType.STYLESHEET);
    mMimeMatcher.addRule("image/*", Page.ResourceType.IMAGE);
    mMimeMatcher.addRule("application/x-javascript", Page.ResourceType.SCRIPT);
    mMimeMatcher.addRule("text/javascript", Page.ResourceType.XHR);
    mMimeMatcher.addRule("application/json", Page.ResourceType.XHR);
    mMimeMatcher.addRule("text/*", Page.ResourceType.DOCUMENT);
    mMimeMatcher.addRule("*", Page.ResourceType.OTHER);
  }
  
  public Page.ResourceType determineResourceType(String paramString)
  {
    paramString = stripContentExtras(paramString);
    return (Page.ResourceType)mMimeMatcher.match(paramString);
  }
  
  public String stripContentExtras(String paramString)
  {
    int i = paramString.indexOf(';');
    String str = paramString;
    if (i >= 0) {
      str = paramString.substring(0, i);
    }
    return str;
  }
}

/* Location:
 * Qualified Name:     com.facebook.stetho.inspector.network.ResourceTypeHelper
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */