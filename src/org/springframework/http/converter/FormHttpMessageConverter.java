package org.springframework.http.converter;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.Set;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.util.Assert;
import org.springframework.util.FileCopyUtils;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StringUtils;

public class FormHttpMessageConverter
  implements HttpMessageConverter<MultiValueMap<String, ?>>
{
  private static final byte[] BOUNDARY_CHARS = { 45, 95, 49, 50, 51, 52, 53, 54, 55, 56, 57, 48, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90 };
  private Charset charset = Charset.forName("UTF-8");
  private List<HttpMessageConverter<?>> partConverters = new ArrayList();
  private final Random rnd = new Random();
  private List<MediaType> supportedMediaTypes = new ArrayList();
  
  public FormHttpMessageConverter()
  {
    supportedMediaTypes.add(MediaType.APPLICATION_FORM_URLENCODED);
    supportedMediaTypes.add(MediaType.MULTIPART_FORM_DATA);
    partConverters.add(new ByteArrayHttpMessageConverter());
    StringHttpMessageConverter localStringHttpMessageConverter = new StringHttpMessageConverter();
    localStringHttpMessageConverter.setWriteAcceptCharset(false);
    partConverters.add(localStringHttpMessageConverter);
    partConverters.add(new ResourceHttpMessageConverter());
  }
  
  private HttpEntity<?> getEntity(Object paramObject)
  {
    if ((paramObject instanceof HttpEntity)) {
      return (HttpEntity)paramObject;
    }
    return new HttpEntity(paramObject);
  }
  
  private boolean isMultipart(MultiValueMap<String, ?> paramMultiValueMap, MediaType paramMediaType)
  {
    if (paramMediaType != null) {
      return MediaType.MULTIPART_FORM_DATA.equals(paramMediaType);
    }
    Object localObject;
    do
    {
      paramMediaType = paramMultiValueMap.keySet().iterator();
      Iterator localIterator;
      while (!localIterator.hasNext())
      {
        if (!paramMediaType.hasNext()) {
          break;
        }
        localIterator = ((List)paramMultiValueMap.get((String)paramMediaType.next())).iterator();
      }
      localObject = localIterator.next();
    } while ((localObject == null) || ((localObject instanceof String)));
    return true;
    return false;
  }
  
  private void writeBoundary(byte[] paramArrayOfByte, OutputStream paramOutputStream)
    throws IOException
  {
    paramOutputStream.write(45);
    paramOutputStream.write(45);
    paramOutputStream.write(paramArrayOfByte);
    writeNewLine(paramOutputStream);
  }
  
  private void writeEnd(byte[] paramArrayOfByte, OutputStream paramOutputStream)
    throws IOException
  {
    paramOutputStream.write(45);
    paramOutputStream.write(45);
    paramOutputStream.write(paramArrayOfByte);
    paramOutputStream.write(45);
    paramOutputStream.write(45);
    writeNewLine(paramOutputStream);
  }
  
  private void writeForm(MultiValueMap<String, String> paramMultiValueMap, MediaType paramMediaType, HttpOutputMessage paramHttpOutputMessage)
    throws IOException
  {
    StringBuilder localStringBuilder;
    Iterator localIterator1;
    if (paramMediaType != null)
    {
      paramHttpOutputMessage.getHeaders().setContentType(paramMediaType);
      if (paramMediaType.getCharSet() != null)
      {
        paramMediaType = paramMediaType.getCharSet();
        localStringBuilder = new StringBuilder();
        localIterator1 = paramMultiValueMap.keySet().iterator();
      }
    }
    for (;;)
    {
      if (!localIterator1.hasNext()) {
        break label223;
      }
      String str1 = (String)localIterator1.next();
      Iterator localIterator2 = ((List)paramMultiValueMap.get(str1)).iterator();
      for (;;)
      {
        if (localIterator2.hasNext())
        {
          String str2 = (String)localIterator2.next();
          localStringBuilder.append(URLEncoder.encode(str1, paramMediaType.name()));
          if (str2 != null)
          {
            localStringBuilder.append('=');
            localStringBuilder.append(URLEncoder.encode(str2, paramMediaType.name()));
            if (localIterator2.hasNext())
            {
              localStringBuilder.append('&');
              continue;
              paramMediaType = charset;
              break;
              paramHttpOutputMessage.getHeaders().setContentType(MediaType.APPLICATION_FORM_URLENCODED);
              paramMediaType = charset;
              break;
            }
          }
        }
      }
      if (localIterator1.hasNext()) {
        localStringBuilder.append('&');
      }
    }
    label223:
    paramMultiValueMap = localStringBuilder.toString().getBytes(paramMediaType.name());
    paramHttpOutputMessage.getHeaders().setContentLength(paramMultiValueMap.length);
    FileCopyUtils.copy(paramMultiValueMap, paramHttpOutputMessage.getBody());
  }
  
  private void writeMultipart(MultiValueMap<String, Object> paramMultiValueMap, HttpOutputMessage paramHttpOutputMessage)
    throws IOException
  {
    byte[] arrayOfByte = generateMultipartBoundary();
    Object localObject = Collections.singletonMap("boundary", new String(arrayOfByte, "US-ASCII"));
    localObject = new MediaType(MediaType.MULTIPART_FORM_DATA, (Map)localObject);
    paramHttpOutputMessage.getHeaders().setContentType((MediaType)localObject);
    writeParts(paramHttpOutputMessage.getBody(), paramMultiValueMap, arrayOfByte);
    writeEnd(arrayOfByte, paramHttpOutputMessage.getBody());
  }
  
  private void writeNewLine(OutputStream paramOutputStream)
    throws IOException
  {
    paramOutputStream.write(13);
    paramOutputStream.write(10);
  }
  
  private void writePart(String paramString, HttpEntity<?> paramHttpEntity, OutputStream paramOutputStream)
    throws IOException
  {
    Object localObject = paramHttpEntity.getBody();
    Class localClass = localObject.getClass();
    paramHttpEntity = paramHttpEntity.getHeaders();
    MediaType localMediaType = paramHttpEntity.getContentType();
    Iterator localIterator = partConverters.iterator();
    while (localIterator.hasNext())
    {
      HttpMessageConverter localHttpMessageConverter = (HttpMessageConverter)localIterator.next();
      if (localHttpMessageConverter.canWrite(localClass, localMediaType))
      {
        paramOutputStream = new MultipartHttpOutputMessage(paramOutputStream);
        paramOutputStream.getHeaders().setContentDispositionFormData(paramString, getFilename(localObject));
        if (!paramHttpEntity.isEmpty()) {
          paramOutputStream.getHeaders().putAll(paramHttpEntity);
        }
        localHttpMessageConverter.write(localObject, localMediaType, paramOutputStream);
        return;
      }
    }
    throw new HttpMessageNotWritableException("Could not write request: no suitable HttpMessageConverter found for request type [" + localClass.getName() + "]");
  }
  
  private void writeParts(OutputStream paramOutputStream, MultiValueMap<String, Object> paramMultiValueMap, byte[] paramArrayOfByte)
    throws IOException
  {
    paramMultiValueMap = paramMultiValueMap.entrySet().iterator();
    while (paramMultiValueMap.hasNext())
    {
      Object localObject1 = (Map.Entry)paramMultiValueMap.next();
      String str = (String)((Map.Entry)localObject1).getKey();
      localObject1 = ((List)((Map.Entry)localObject1).getValue()).iterator();
      while (((Iterator)localObject1).hasNext())
      {
        Object localObject2 = ((Iterator)localObject1).next();
        writeBoundary(paramArrayOfByte, paramOutputStream);
        writePart(str, getEntity(localObject2), paramOutputStream);
        writeNewLine(paramOutputStream);
      }
    }
  }
  
  public final void addPartConverter(HttpMessageConverter<?> paramHttpMessageConverter)
  {
    Assert.notNull(paramHttpMessageConverter, "'partConverter' must not be NULL");
    partConverters.add(paramHttpMessageConverter);
  }
  
  public boolean canRead(Class<?> paramClass, MediaType paramMediaType)
  {
    if (!MultiValueMap.class.isAssignableFrom(paramClass)) {}
    MediaType localMediaType;
    do
    {
      while (!paramClass.hasNext())
      {
        return false;
        if (paramMediaType == null) {
          return true;
        }
        paramClass = getSupportedMediaTypes().iterator();
      }
      localMediaType = (MediaType)paramClass.next();
    } while ((localMediaType.equals(MediaType.MULTIPART_FORM_DATA)) || (!localMediaType.includes(paramMediaType)));
    return true;
  }
  
  public boolean canWrite(Class<?> paramClass, MediaType paramMediaType)
  {
    if (!MultiValueMap.class.isAssignableFrom(paramClass)) {}
    do
    {
      while (!paramClass.hasNext())
      {
        return false;
        if ((paramMediaType == null) || (MediaType.ALL.equals(paramMediaType))) {
          return true;
        }
        paramClass = getSupportedMediaTypes().iterator();
      }
    } while (!((MediaType)paramClass.next()).isCompatibleWith(paramMediaType));
    return true;
  }
  
  protected byte[] generateMultipartBoundary()
  {
    byte[] arrayOfByte = new byte[rnd.nextInt(11) + 30];
    int i = 0;
    while (i < arrayOfByte.length)
    {
      arrayOfByte[i] = BOUNDARY_CHARS[rnd.nextInt(BOUNDARY_CHARS.length)];
      i += 1;
    }
    return arrayOfByte;
  }
  
  protected String getFilename(Object paramObject)
  {
    if ((paramObject instanceof Resource)) {
      return ((Resource)paramObject).getFilename();
    }
    return null;
  }
  
  public List<MediaType> getSupportedMediaTypes()
  {
    return Collections.unmodifiableList(supportedMediaTypes);
  }
  
  public MultiValueMap<String, String> read(Class<? extends MultiValueMap<String, ?>> paramClass, HttpInputMessage paramHttpInputMessage)
    throws IOException, HttpMessageNotReadableException
  {
    paramClass = paramHttpInputMessage.getHeaders().getContentType();
    LinkedMultiValueMap localLinkedMultiValueMap;
    int i;
    label63:
    String str;
    int k;
    if (paramClass.getCharSet() != null)
    {
      paramClass = paramClass.getCharSet();
      paramHttpInputMessage = StringUtils.tokenizeToStringArray(FileCopyUtils.copyToString(new InputStreamReader(paramHttpInputMessage.getBody(), paramClass)), "&");
      localLinkedMultiValueMap = new LinkedMultiValueMap(paramHttpInputMessage.length);
      int j = paramHttpInputMessage.length;
      i = 0;
      if (i >= j) {
        break label162;
      }
      str = paramHttpInputMessage[i];
      k = str.indexOf('=');
      if (k != -1) {
        break label121;
      }
      localLinkedMultiValueMap.add(URLDecoder.decode(str, paramClass.name()), null);
    }
    for (;;)
    {
      i += 1;
      break label63;
      paramClass = charset;
      break;
      label121:
      localLinkedMultiValueMap.add(URLDecoder.decode(str.substring(0, k), paramClass.name()), URLDecoder.decode(str.substring(k + 1), paramClass.name()));
    }
    label162:
    return localLinkedMultiValueMap;
  }
  
  public void setCharset(Charset paramCharset)
  {
    charset = paramCharset;
  }
  
  public final void setPartConverters(List<HttpMessageConverter<?>> paramList)
  {
    Assert.notEmpty(paramList, "'partConverters' must not be empty");
    partConverters = paramList;
  }
  
  public void setSupportedMediaTypes(List<MediaType> paramList)
  {
    supportedMediaTypes = paramList;
  }
  
  public void write(MultiValueMap<String, ?> paramMultiValueMap, MediaType paramMediaType, HttpOutputMessage paramHttpOutputMessage)
    throws IOException, HttpMessageNotWritableException
  {
    if (!isMultipart(paramMultiValueMap, paramMediaType))
    {
      writeForm(paramMultiValueMap, paramMediaType, paramHttpOutputMessage);
      return;
    }
    writeMultipart(paramMultiValueMap, paramHttpOutputMessage);
  }
  
  private class MultipartHttpOutputMessage
    implements HttpOutputMessage
  {
    private final HttpHeaders headers = new HttpHeaders();
    private boolean headersWritten = false;
    private final OutputStream os;
    
    public MultipartHttpOutputMessage(OutputStream paramOutputStream)
    {
      os = paramOutputStream;
    }
    
    private void writeHeaders()
      throws IOException
    {
      if (!headersWritten)
      {
        Iterator localIterator = headers.entrySet().iterator();
        while (localIterator.hasNext())
        {
          Object localObject = (Map.Entry)localIterator.next();
          byte[] arrayOfByte1 = getAsciiBytes((String)((Map.Entry)localObject).getKey());
          localObject = ((List)((Map.Entry)localObject).getValue()).iterator();
          while (((Iterator)localObject).hasNext())
          {
            byte[] arrayOfByte2 = getAsciiBytes((String)((Iterator)localObject).next());
            os.write(arrayOfByte1);
            os.write(58);
            os.write(32);
            os.write(arrayOfByte2);
            FormHttpMessageConverter.this.writeNewLine(os);
          }
        }
        FormHttpMessageConverter.this.writeNewLine(os);
        headersWritten = true;
      }
    }
    
    protected byte[] getAsciiBytes(String paramString)
    {
      try
      {
        paramString = paramString.getBytes("US-ASCII");
        return paramString;
      }
      catch (UnsupportedEncodingException paramString)
      {
        throw new IllegalStateException(paramString);
      }
    }
    
    public OutputStream getBody()
      throws IOException
    {
      writeHeaders();
      return os;
    }
    
    public HttpHeaders getHeaders()
    {
      if (headersWritten) {
        return HttpHeaders.readOnlyHttpHeaders(headers);
      }
      return headers;
    }
  }
}

/* Location:
 * Qualified Name:     org.springframework.http.converter.FormHttpMessageConverter
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */