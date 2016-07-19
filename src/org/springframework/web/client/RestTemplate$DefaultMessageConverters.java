package org.springframework.web.client;

import java.util.List;
import org.springframework.http.converter.ByteArrayHttpMessageConverter;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.ResourceHttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.feed.SyndFeedHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.converter.json.MappingJacksonHttpMessageConverter;
import org.springframework.http.converter.xml.SimpleXmlHttpMessageConverter;
import org.springframework.http.converter.xml.SourceHttpMessageConverter;
import org.springframework.http.converter.xml.XmlAwareFormHttpMessageConverter;
import org.springframework.util.ClassUtils;

class RestTemplate$DefaultMessageConverters
{
  private static final boolean jackson2Present;
  private static final boolean jacksonPresent;
  private static final boolean javaxXmlTransformPresent;
  private static final boolean romePresent;
  private static final boolean simpleXmlPresent;
  
  static
  {
    boolean bool2 = true;
    javaxXmlTransformPresent = ClassUtils.isPresent("javax.xml.transform.Source", RestTemplate.class.getClassLoader());
    simpleXmlPresent = ClassUtils.isPresent("org.simpleframework.xml.Serializer", RestTemplate.class.getClassLoader());
    if ((ClassUtils.isPresent("org.codehaus.jackson.map.ObjectMapper", RestTemplate.class.getClassLoader())) && (ClassUtils.isPresent("org.codehaus.jackson.JsonGenerator", RestTemplate.class.getClassLoader())))
    {
      bool1 = true;
      jacksonPresent = bool1;
      if ((!ClassUtils.isPresent("com.fasterxml.jackson.databind.ObjectMapper", RestTemplate.class.getClassLoader())) || (!ClassUtils.isPresent("com.fasterxml.jackson.core.JsonGenerator", RestTemplate.class.getClassLoader()))) {
        break label111;
      }
    }
    label111:
    for (boolean bool1 = bool2;; bool1 = false)
    {
      jackson2Present = bool1;
      romePresent = ClassUtils.isPresent("com.google.code.rome.android.repackaged.com.sun.syndication.feed.synd.SyndFeed", RestTemplate.class.getClassLoader());
      return;
      bool1 = false;
      break;
    }
  }
  
  public static void init(List<HttpMessageConverter<?>> paramList)
  {
    paramList.add(new ByteArrayHttpMessageConverter());
    paramList.add(new StringHttpMessageConverter());
    paramList.add(new ResourceHttpMessageConverter());
    if (javaxXmlTransformPresent)
    {
      paramList.add(new SourceHttpMessageConverter());
      paramList.add(new XmlAwareFormHttpMessageConverter());
      if (simpleXmlPresent) {
        paramList.add(new SimpleXmlHttpMessageConverter());
      }
      if (!jackson2Present) {
        break label154;
      }
      paramList.add(new MappingJackson2HttpMessageConverter());
    }
    for (;;)
    {
      if (romePresent) {
        paramList.add(new SyndFeedHttpMessageConverter());
      }
      return;
      paramList.add(new FormHttpMessageConverter());
      break;
      label154:
      if (jacksonPresent) {
        paramList.add(new MappingJacksonHttpMessageConverter());
      }
    }
  }
}

/* Location:
 * Qualified Name:     org.springframework.web.client.RestTemplate.DefaultMessageConverters
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */