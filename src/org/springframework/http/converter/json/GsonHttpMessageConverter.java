package org.springframework.http.converter.json;

import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSyntaxException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.nio.charset.Charset;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.AbstractHttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.util.Assert;

public class GsonHttpMessageConverter
  extends AbstractHttpMessageConverter<Object>
{
  public static final Charset DEFAULT_CHARSET = Charset.forName("UTF-8");
  private Gson gson;
  private boolean prefixJson = false;
  private Type type = null;
  
  public GsonHttpMessageConverter()
  {
    this(new Gson());
  }
  
  public GsonHttpMessageConverter(Gson paramGson)
  {
    super(new MediaType("application", "json", DEFAULT_CHARSET));
    setGson(paramGson);
  }
  
  public GsonHttpMessageConverter(boolean paramBoolean) {}
  
  private Charset getCharset(HttpHeaders paramHttpHeaders)
  {
    if ((paramHttpHeaders != null) && (paramHttpHeaders.getContentType() != null) && (paramHttpHeaders.getContentType().getCharSet() != null)) {
      return paramHttpHeaders.getContentType().getCharSet();
    }
    return DEFAULT_CHARSET;
  }
  
  public boolean canRead(Class<?> paramClass, MediaType paramMediaType)
  {
    return canRead(paramMediaType);
  }
  
  public boolean canWrite(Class<?> paramClass, MediaType paramMediaType)
  {
    return canWrite(paramMediaType);
  }
  
  public Type getType()
  {
    return type;
  }
  
  protected Object readInternal(Class<?> paramClass, HttpInputMessage paramHttpInputMessage)
    throws IOException, HttpMessageNotReadableException
  {
    paramHttpInputMessage = new InputStreamReader(paramHttpInputMessage.getBody(), getCharset(paramHttpInputMessage.getHeaders()));
    try
    {
      Type localType = getType();
      if (localType != null) {
        return gson.fromJson(paramHttpInputMessage, localType);
      }
      paramClass = gson.fromJson(paramHttpInputMessage, paramClass);
      return paramClass;
    }
    catch (JsonSyntaxException paramClass)
    {
      throw new HttpMessageNotReadableException("Could not read JSON: " + paramClass.getMessage(), paramClass);
    }
    catch (JsonIOException paramClass)
    {
      throw new HttpMessageNotReadableException("Could not read JSON: " + paramClass.getMessage(), paramClass);
    }
    catch (JsonParseException paramClass)
    {
      throw new HttpMessageNotReadableException("Could not read JSON: " + paramClass.getMessage(), paramClass);
    }
  }
  
  public void setGson(Gson paramGson)
  {
    Assert.notNull(paramGson, "'gson' must not be null");
    gson = paramGson;
  }
  
  public void setPrefixJson(boolean paramBoolean)
  {
    prefixJson = paramBoolean;
  }
  
  public void setType(Type paramType)
  {
    type = paramType;
  }
  
  protected boolean supports(Class<?> paramClass)
  {
    throw new UnsupportedOperationException();
  }
  
  /* Error */
  protected void writeInternal(Object paramObject, org.springframework.http.HttpOutputMessage paramHttpOutputMessage)
    throws IOException, org.springframework.http.converter.HttpMessageNotWritableException
  {
    // Byte code:
    //   0: new 175	java/io/OutputStreamWriter
    //   3: dup
    //   4: aload_2
    //   5: invokeinterface 180 1 0
    //   10: aload_0
    //   11: aload_2
    //   12: invokeinterface 181 1 0
    //   17: invokespecial 115	org/springframework/http/converter/json/GsonHttpMessageConverter:getCharset	(Lorg/springframework/http/HttpHeaders;)Ljava/nio/charset/Charset;
    //   20: invokespecial 184	java/io/OutputStreamWriter:<init>	(Ljava/io/OutputStream;Ljava/nio/charset/Charset;)V
    //   23: astore_2
    //   24: aload_0
    //   25: getfield 50	org/springframework/http/converter/json/GsonHttpMessageConverter:prefixJson	Z
    //   28: ifeq +10 -> 38
    //   31: aload_2
    //   32: ldc -70
    //   34: invokevirtual 189	java/io/OutputStreamWriter:append	(Ljava/lang/CharSequence;)Ljava/io/Writer;
    //   37: pop
    //   38: aload_0
    //   39: invokevirtual 120	org/springframework/http/converter/json/GsonHttpMessageConverter:getType	()Ljava/lang/reflect/Type;
    //   42: astore_3
    //   43: aload_3
    //   44: ifnull +18 -> 62
    //   47: aload_0
    //   48: getfield 122	org/springframework/http/converter/json/GsonHttpMessageConverter:gson	Lcom/google/gson/Gson;
    //   51: aload_1
    //   52: aload_3
    //   53: aload_2
    //   54: invokevirtual 193	com/google/gson/Gson:toJson	(Ljava/lang/Object;Ljava/lang/reflect/Type;Ljava/lang/Appendable;)V
    //   57: aload_2
    //   58: invokevirtual 196	java/io/OutputStreamWriter:close	()V
    //   61: return
    //   62: aload_0
    //   63: getfield 122	org/springframework/http/converter/json/GsonHttpMessageConverter:gson	Lcom/google/gson/Gson;
    //   66: aload_1
    //   67: aload_2
    //   68: invokevirtual 199	com/google/gson/Gson:toJson	(Ljava/lang/Object;Ljava/lang/Appendable;)V
    //   71: goto -14 -> 57
    //   74: astore_1
    //   75: new 173	org/springframework/http/converter/HttpMessageNotWritableException
    //   78: dup
    //   79: new 131	java/lang/StringBuilder
    //   82: dup
    //   83: invokespecial 132	java/lang/StringBuilder:<init>	()V
    //   86: ldc -55
    //   88: invokevirtual 138	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   91: aload_1
    //   92: invokevirtual 149	com/google/gson/JsonIOException:getMessage	()Ljava/lang/String;
    //   95: invokevirtual 138	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   98: invokevirtual 145	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   101: aload_1
    //   102: invokespecial 202	org/springframework/http/converter/HttpMessageNotWritableException:<init>	(Ljava/lang/String;Ljava/lang/Throwable;)V
    //   105: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	106	0	this	GsonHttpMessageConverter
    //   0	106	1	paramObject	Object
    //   0	106	2	paramHttpOutputMessage	org.springframework.http.HttpOutputMessage
    //   42	11	3	localType	Type
    // Exception table:
    //   from	to	target	type
    //   24	38	74	com/google/gson/JsonIOException
    //   38	43	74	com/google/gson/JsonIOException
    //   47	57	74	com/google/gson/JsonIOException
    //   57	61	74	com/google/gson/JsonIOException
    //   62	71	74	com/google/gson/JsonIOException
  }
}

/* Location:
 * Qualified Name:     org.springframework.http.converter.json.GsonHttpMessageConverter
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */