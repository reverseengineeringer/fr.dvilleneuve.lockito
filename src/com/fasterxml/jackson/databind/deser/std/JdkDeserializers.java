package com.fasterxml.jackson.databind.deser.std;

import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.URI;
import java.net.URL;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.util.Currency;
import java.util.HashSet;
import java.util.Locale;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.regex.Pattern;

public class JdkDeserializers
{
  private static final HashSet<String> _classNames;
  
  static
  {
    int i = 0;
    _classNames = new HashSet();
    Class[] arrayOfClass = new Class[14];
    arrayOfClass[0] = UUID.class;
    arrayOfClass[1] = URL.class;
    arrayOfClass[2] = URI.class;
    arrayOfClass[3] = File.class;
    arrayOfClass[4] = Currency.class;
    arrayOfClass[5] = Pattern.class;
    arrayOfClass[6] = Locale.class;
    arrayOfClass[7] = InetAddress.class;
    arrayOfClass[8] = InetSocketAddress.class;
    arrayOfClass[9] = Charset.class;
    arrayOfClass[10] = AtomicBoolean.class;
    arrayOfClass[11] = Class.class;
    arrayOfClass[12] = StackTraceElement.class;
    arrayOfClass[13] = ByteBuffer.class;
    int j = arrayOfClass.length;
    while (i < j)
    {
      Class localClass = arrayOfClass[i];
      _classNames.add(localClass.getName());
      i += 1;
    }
  }
  
  public static JsonDeserializer<?> find(Class<?> paramClass, String paramString)
  {
    if (!_classNames.contains(paramString)) {
      return null;
    }
    if (paramClass == URI.class) {
      return URIDeserializer.instance;
    }
    if (paramClass == URL.class) {
      return URLDeserializer.instance;
    }
    if (paramClass == File.class) {
      return FileDeserializer.instance;
    }
    if (paramClass == UUID.class) {
      return UUIDDeserializer.instance;
    }
    if (paramClass == Currency.class) {
      return CurrencyDeserializer.instance;
    }
    if (paramClass == Pattern.class) {
      return PatternDeserializer.instance;
    }
    if (paramClass == Locale.class) {
      return LocaleDeserializer.instance;
    }
    if (paramClass == InetAddress.class) {
      return InetAddressDeserializer.instance;
    }
    if (paramClass == InetSocketAddress.class) {
      return InetSocketAddressDeserializer.instance;
    }
    if (paramClass == Charset.class) {
      return new CharsetDeserializer();
    }
    if (paramClass == Class.class) {
      return ClassDeserializer.instance;
    }
    if (paramClass == StackTraceElement.class) {
      return StackTraceElementDeserializer.instance;
    }
    if (paramClass == AtomicBoolean.class) {
      return AtomicBooleanDeserializer.instance;
    }
    if (paramClass == ByteBuffer.class) {
      return new ByteBufferDeserializer();
    }
    throw new IllegalArgumentException("Internal error: can't find deserializer for " + paramString);
  }
  
  public static class CurrencyDeserializer
    extends FromStringDeserializer<Currency>
  {
    public static final CurrencyDeserializer instance = new CurrencyDeserializer();
    
    public CurrencyDeserializer()
    {
      super();
    }
    
    protected Currency _deserialize(String paramString, DeserializationContext paramDeserializationContext)
      throws IllegalArgumentException
    {
      return Currency.getInstance(paramString);
    }
  }
  
  public static class FileDeserializer
    extends FromStringDeserializer<File>
  {
    public static final FileDeserializer instance = new FileDeserializer();
    
    public FileDeserializer()
    {
      super();
    }
    
    protected File _deserialize(String paramString, DeserializationContext paramDeserializationContext)
    {
      return new File(paramString);
    }
  }
  
  protected static class LocaleDeserializer
    extends FromStringDeserializer<Locale>
  {
    public static final LocaleDeserializer instance = new LocaleDeserializer();
    
    public LocaleDeserializer()
    {
      super();
    }
    
    protected Locale _deserialize(String paramString, DeserializationContext paramDeserializationContext)
      throws IOException
    {
      int i = paramString.indexOf('_');
      if (i < 0) {
        return new Locale(paramString);
      }
      paramDeserializationContext = paramString.substring(0, i);
      paramString = paramString.substring(i + 1);
      i = paramString.indexOf('_');
      if (i < 0) {
        return new Locale(paramDeserializationContext, paramString);
      }
      return new Locale(paramDeserializationContext, paramString.substring(0, i), paramString.substring(i + 1));
    }
  }
  
  public static class PatternDeserializer
    extends FromStringDeserializer<Pattern>
  {
    public static final PatternDeserializer instance = new PatternDeserializer();
    
    public PatternDeserializer()
    {
      super();
    }
    
    protected Pattern _deserialize(String paramString, DeserializationContext paramDeserializationContext)
      throws IllegalArgumentException
    {
      return Pattern.compile(paramString);
    }
  }
  
  public static class URIDeserializer
    extends FromStringDeserializer<URI>
  {
    public static final URIDeserializer instance = new URIDeserializer();
    
    public URIDeserializer()
    {
      super();
    }
    
    protected URI _deserialize(String paramString, DeserializationContext paramDeserializationContext)
      throws IllegalArgumentException
    {
      return URI.create(paramString);
    }
  }
  
  public static class URLDeserializer
    extends FromStringDeserializer<URL>
  {
    public static final URLDeserializer instance = new URLDeserializer();
    
    public URLDeserializer()
    {
      super();
    }
    
    protected URL _deserialize(String paramString, DeserializationContext paramDeserializationContext)
      throws IOException
    {
      return new URL(paramString);
    }
  }
}

/* Location:
 * Qualified Name:     com.fasterxml.jackson.databind.deser.std.JdkDeserializers
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */