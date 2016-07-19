package org.springframework.http.converter;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.SortedMap;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.util.FileCopyUtils;

public class StringHttpMessageConverter
  extends AbstractHttpMessageConverter<String>
{
  private final List<Charset> availableCharsets;
  private final Charset defaultCharset;
  private boolean writeAcceptCharset = true;
  
  public StringHttpMessageConverter()
  {
    this(Charset.forName("ISO-8859-1"));
  }
  
  public StringHttpMessageConverter(Charset paramCharset)
  {
    this(paramCharset, new ArrayList(Charset.availableCharsets().values()));
  }
  
  public StringHttpMessageConverter(Charset paramCharset, List<Charset> paramList)
  {
    super(new MediaType[] { new MediaType("text", "plain", paramCharset), MediaType.ALL });
    defaultCharset = paramCharset;
    availableCharsets = paramList;
  }
  
  private Charset getContentTypeCharset(MediaType paramMediaType)
  {
    if ((paramMediaType != null) && (paramMediaType.getCharSet() != null)) {
      return paramMediaType.getCharSet();
    }
    return getDefaultCharset();
  }
  
  protected List<Charset> getAcceptedCharsets()
  {
    return availableCharsets;
  }
  
  protected Long getContentLength(String paramString, MediaType paramMediaType)
  {
    paramMediaType = getContentTypeCharset(paramMediaType);
    try
    {
      long l = paramString.getBytes(paramMediaType.displayName()).length;
      return Long.valueOf(l);
    }
    catch (UnsupportedEncodingException paramString)
    {
      throw new InternalError(paramString.getMessage());
    }
  }
  
  public Charset getDefaultCharset()
  {
    return defaultCharset;
  }
  
  protected String readInternal(Class<? extends String> paramClass, HttpInputMessage paramHttpInputMessage)
    throws IOException
  {
    paramClass = getContentTypeCharset(paramHttpInputMessage.getHeaders().getContentType());
    return FileCopyUtils.copyToString(new InputStreamReader(paramHttpInputMessage.getBody(), paramClass));
  }
  
  public void setWriteAcceptCharset(boolean paramBoolean)
  {
    writeAcceptCharset = paramBoolean;
  }
  
  public boolean supports(Class<?> paramClass)
  {
    return String.class.equals(paramClass);
  }
  
  protected void writeInternal(String paramString, HttpOutputMessage paramHttpOutputMessage)
    throws IOException
  {
    if (writeAcceptCharset) {
      paramHttpOutputMessage.getHeaders().setAcceptCharset(getAcceptedCharsets());
    }
    Charset localCharset = getContentTypeCharset(paramHttpOutputMessage.getHeaders().getContentType());
    FileCopyUtils.copy(paramString, new OutputStreamWriter(paramHttpOutputMessage.getBody(), localCharset));
  }
}

/* Location:
 * Qualified Name:     org.springframework.http.converter.StringHttpMessageConverter
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */