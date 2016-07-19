package org.springframework.http.converter.feed;

import android.os.Build.VERSION;
import com.google.code.rome.android.repackaged.com.sun.syndication.feed.WireFeed;
import com.google.code.rome.android.repackaged.com.sun.syndication.io.FeedException;
import com.google.code.rome.android.repackaged.com.sun.syndication.io.WireFeedInput;
import com.google.code.rome.android.repackaged.com.sun.syndication.io.WireFeedOutput;
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

public abstract class AbstractWireFeedHttpMessageConverter<T extends WireFeed>
  extends AbstractHttpMessageConverter<T>
{
  public static final Charset DEFAULT_CHARSET = Charset.forName("UTF-8");
  
  protected AbstractWireFeedHttpMessageConverter(MediaType paramMediaType)
  {
    super(paramMediaType);
    if (Build.VERSION.SDK_INT < 8) {
      Thread.currentThread().setContextClassLoader(getClass().getClassLoader());
    }
  }
  
  protected T readInternal(Class<? extends T> paramClass, HttpInputMessage paramHttpInputMessage)
    throws IOException, HttpMessageNotReadableException
  {
    WireFeedInput localWireFeedInput = new WireFeedInput();
    paramClass = paramHttpInputMessage.getHeaders().getContentType();
    if ((paramClass != null) && (paramClass.getCharSet() != null)) {}
    for (paramClass = paramClass.getCharSet();; paramClass = DEFAULT_CHARSET) {
      try
      {
        paramClass = localWireFeedInput.build(new InputStreamReader(paramHttpInputMessage.getBody(), paramClass));
        return paramClass;
      }
      catch (FeedException paramClass)
      {
        throw new HttpMessageNotReadableException("Could not read WireFeed: " + paramClass.getMessage(), paramClass);
      }
    }
  }
  
  protected void writeInternal(T paramT, HttpOutputMessage paramHttpOutputMessage)
    throws IOException, HttpMessageNotWritableException
  {
    Object localObject2 = paramT.getEncoding();
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
    localObject2 = new WireFeedOutput();
    try
    {
      ((WireFeedOutput)localObject2).output(paramT, new OutputStreamWriter(paramHttpOutputMessage.getBody(), (String)localObject1));
      return;
    }
    catch (FeedException paramT)
    {
      throw new HttpMessageNotWritableException("Could not write WiredFeed: " + paramT.getMessage(), paramT);
    }
  }
}

/* Location:
 * Qualified Name:     org.springframework.http.converter.feed.AbstractWireFeedHttpMessageConverter
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */