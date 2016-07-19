package org.springframework.http.converter.xml;

import org.springframework.http.converter.FormHttpMessageConverter;

public class XmlAwareFormHttpMessageConverter
  extends FormHttpMessageConverter
{
  public XmlAwareFormHttpMessageConverter()
  {
    addPartConverter(new SourceHttpMessageConverter());
  }
}

/* Location:
 * Qualified Name:     org.springframework.http.converter.xml.XmlAwareFormHttpMessageConverter
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */