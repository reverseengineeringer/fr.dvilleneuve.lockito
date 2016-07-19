package org.springframework.http.converter.xml;

import java.io.IOException;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.AbstractHttpMessageConverter;

public abstract class AbstractXmlHttpMessageConverter<T>
  extends AbstractHttpMessageConverter<T>
{
  private final TransformerFactory transformerFactory = TransformerFactory.newInstance();
  
  protected AbstractXmlHttpMessageConverter()
  {
    super(new MediaType[] { MediaType.APPLICATION_XML, MediaType.TEXT_XML, new MediaType("application", "*+xml") });
  }
  
  protected abstract T readFromSource(Class<? extends T> paramClass, HttpHeaders paramHttpHeaders, Source paramSource)
    throws IOException;
  
  public final T readInternal(Class<? extends T> paramClass, HttpInputMessage paramHttpInputMessage)
    throws IOException
  {
    return (T)readFromSource(paramClass, paramHttpInputMessage.getHeaders(), new StreamSource(paramHttpInputMessage.getBody()));
  }
  
  protected void transform(Source paramSource, Result paramResult)
    throws TransformerException
  {
    transformerFactory.newTransformer().transform(paramSource, paramResult);
  }
  
  protected final void writeInternal(T paramT, HttpOutputMessage paramHttpOutputMessage)
    throws IOException
  {
    writeToResult(paramT, paramHttpOutputMessage.getHeaders(), new StreamResult(paramHttpOutputMessage.getBody()));
  }
  
  protected abstract void writeToResult(T paramT, HttpHeaders paramHttpHeaders, Result paramResult)
    throws IOException;
}

/* Location:
 * Qualified Name:     org.springframework.http.converter.xml.AbstractXmlHttpMessageConverter
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */