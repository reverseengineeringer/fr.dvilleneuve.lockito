package org.springframework.http.converter.feed;

import com.google.code.rome.android.repackaged.com.sun.syndication.feed.atom.Feed;
import org.springframework.http.MediaType;

public class AtomFeedHttpMessageConverter
  extends AbstractWireFeedHttpMessageConverter<Feed>
{
  public AtomFeedHttpMessageConverter()
  {
    super(MediaType.APPLICATION_ATOM_XML);
  }
  
  protected boolean supports(Class<?> paramClass)
  {
    return Feed.class.isAssignableFrom(paramClass);
  }
}

/* Location:
 * Qualified Name:     org.springframework.http.converter.feed.AtomFeedHttpMessageConverter
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */