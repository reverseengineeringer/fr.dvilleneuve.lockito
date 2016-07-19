package org.springframework.http.converter.feed;

import android.os.Build.VERSION;
import com.google.code.rome.android.repackaged.com.sun.syndication.feed.synd.SyndFeed;
import com.google.code.rome.android.repackaged.com.sun.syndication.io.FeedException;
import com.google.code.rome.android.repackaged.com.sun.syndication.io.SyndFeedInput;
import com.google.code.rome.android.repackaged.com.sun.syndication.io.SyndFeedOutput;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.AbstractHttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.util.StringUtils;

public class SyndFeedHttpMessageConverter
  extends AbstractHttpMessageConverter<SyndFeed>
{
  public static final Charset DEFAULT_CHARSET = Charset.forName("UTF-8");
  
  public SyndFeedHttpMessageConverter()
  {
    super(new MediaType[] { MediaType.APPLICATION_RSS_XML, MediaType.APPLICATION_ATOM_XML });
    if (Build.VERSION.SDK_INT < 8) {
      Thread.currentThread().setContextClassLoader(getClass().getClassLoader());
    }
  }
  
  protected SyndFeed readInternal(Class<? extends SyndFeed> paramClass, HttpInputMessage paramHttpInputMessage)
    throws IOException, HttpMessageNotReadableException
  {
    SyndFeedInput localSyndFeedInput = new SyndFeedInput();
    paramClass = paramHttpInputMessage.getHeaders().getContentType();
    if ((paramClass != null) && (paramClass.getCharSet() != null)) {}
    for (paramClass = paramClass.getCharSet();; paramClass = DEFAULT_CHARSET) {
      try
      {
        paramClass = localSyndFeedInput.build(new InputStreamReader(paramHttpInputMessage.getBody(), paramClass));
        return paramClass;
      }
      catch (FeedException paramClass)
      {
        throw new HttpMessageNotReadableException("Could not read SyndFeed: " + paramClass.getMessage(), paramClass);
      }
    }
  }
  
  protected boolean supports(Class<?> paramClass)
  {
    return SyndFeed.class.isAssignableFrom(paramClass);
  }
  
  protected void writeInternal(SyndFeed paramSyndFeed, HttpOutputMessage paramHttpOutputMessage)
    throws IOException, HttpMessageNotWritableException
  {
    Object localObject2 = paramSyndFeed.getEncoding();
    Object localObject1 = localObject2;
    if (!StringUtils.hasLength((String)localObject2)) {
      localObject1 = DEFAULT_CHARSET.name();
    }
    localObject2 = paramHttpOutputMessage.getHeaders().getContentType();
    if (localObject2 != null)
    {
      Charset localCharset = Charset.forName((String)localObject1);
      localObject2 = new MediaType(((MediaType)localObject2).getType(), ((MediaType)localObject2).getSubtype(), localCharset);
      paramHttpOutputMessage.getHeaders().setContentType((MediaType)localObject2);
    }
    localObject2 = new SyndFeedOutput();
    try
    {
      ((SyndFeedOutput)localObject2).output(paramSyndFeed, new OutputStreamWriter(paramHttpOutputMessage.getBody(), (String)localObject1));
      return;
    }
    catch (FeedException paramSyndFeed)
    {
      throw new HttpMessageNotWritableException("Could not write SyndFeed: " + paramSyndFeed.getMessage(), paramSyndFeed);
    }
  }
}

/* Location:
 * Qualified Name:     org.springframework.http.converter.feed.SyndFeedHttpMessageConverter
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */