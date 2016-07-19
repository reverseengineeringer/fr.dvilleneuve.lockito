package org.springframework.http.converter.feed;

import com.google.code.rome.android.repackaged.com.sun.syndication.feed.rss.Channel;
import org.springframework.http.MediaType;

public class RssChannelHttpMessageConverter
  extends AbstractWireFeedHttpMessageConverter<Channel>
{
  public RssChannelHttpMessageConverter()
  {
    super(MediaType.APPLICATION_RSS_XML);
  }
  
  protected boolean supports(Class<?> paramClass)
  {
    return Channel.class.isAssignableFrom(paramClass);
  }
}

/* Location:
 * Qualified Name:     org.springframework.http.converter.feed.RssChannelHttpMessageConverter
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */