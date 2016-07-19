package org.springframework.http.converter.xml;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.TransformerException;
import javax.xml.transform.dom.DOMResult;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.sax.SAXSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConversionException;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.xml.sax.InputSource;

public class SourceHttpMessageConverter<T extends Source>
  extends AbstractXmlHttpMessageConverter<T>
{
  private ByteArrayInputStream transformToByteArrayInputStream(Source paramSource)
    throws TransformerException
  {
    ByteArrayOutputStream localByteArrayOutputStream = new ByteArrayOutputStream();
    transform(paramSource, new StreamResult(localByteArrayOutputStream));
    return new ByteArrayInputStream(localByteArrayOutputStream.toByteArray());
  }
  
  protected Long getContentLength(T paramT, MediaType paramMediaType)
  {
    paramMediaType = null;
    if ((paramT instanceof DOMSource)) {}
    try
    {
      paramMediaType = new CountingOutputStream(null);
      transform(paramT, new StreamResult(paramMediaType));
      long l = count;
      paramMediaType = Long.valueOf(l);
      return paramMediaType;
    }
    catch (TransformerException paramT) {}
    return null;
  }
  
  protected T readFromSource(Class<? extends T> paramClass, HttpHeaders paramHttpHeaders, Source paramSource)
    throws IOException
  {
    try
    {
      if (DOMSource.class.equals(paramClass))
      {
        paramHttpHeaders = new DOMResult();
        transform(paramSource, paramHttpHeaders);
        return new DOMSource(paramHttpHeaders.getNode());
      }
      if (SAXSource.class.equals(paramClass))
      {
        paramHttpHeaders = new SAXSource(new InputSource(transformToByteArrayInputStream(paramSource)));
        return paramHttpHeaders;
      }
    }
    catch (TransformerException paramHttpHeaders)
    {
      throw new HttpMessageNotReadableException("Could not transform from [" + paramSource + "] to [" + paramClass + "]", paramHttpHeaders);
    }
    if ((StreamSource.class.equals(paramClass)) || (Source.class.equals(paramClass))) {
      return new StreamSource(transformToByteArrayInputStream(paramSource));
    }
    throw new HttpMessageConversionException("Could not read class [" + paramClass + "]. Only DOMSource, SAXSource, and StreamSource are supported.");
  }
  
  public boolean supports(Class<?> paramClass)
  {
    return (DOMSource.class.equals(paramClass)) || (SAXSource.class.equals(paramClass)) || (StreamSource.class.equals(paramClass)) || (Source.class.equals(paramClass));
  }
  
  protected void writeToResult(T paramT, HttpHeaders paramHttpHeaders, Result paramResult)
    throws IOException
  {
    try
    {
      transform(paramT, paramResult);
      return;
    }
    catch (TransformerException paramHttpHeaders)
    {
      throw new HttpMessageNotWritableException("Could not transform [" + paramT + "] to [" + paramResult + "]", paramHttpHeaders);
    }
  }
  
  private static class CountingOutputStream
    extends OutputStream
  {
    private long count = 0L;
    
    public void write(int paramInt)
      throws IOException
    {
      count += 1L;
    }
    
    public void write(byte[] paramArrayOfByte)
      throws IOException
    {
      count += paramArrayOfByte.length;
    }
    
    public void write(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
      throws IOException
    {
      count += paramInt2;
    }
  }
}

/* Location:
 * Qualified Name:     org.springframework.http.converter.xml.SourceHttpMessageConverter
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */