package org.springframework.http;

import java.net.URI;
import java.nio.charset.Charset;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.EnumSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TimeZone;
import org.springframework.util.Assert;
import org.springframework.util.LinkedCaseInsensitiveMap;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StringUtils;

public class HttpHeaders
  implements MultiValueMap<String, String>
{
  private static final String ACCEPT = "Accept";
  private static final String ACCEPT_CHARSET = "Accept-Charset";
  private static final String ACCEPT_ENCODING = "Accept-Encoding";
  private static final String ACCEPT_LANGUAGE = "Accept-Language";
  private static final String ALLOW = "Allow";
  private static final String AUTHORIZATION = "Authorization";
  private static final String CACHE_CONTROL = "Cache-Control";
  private static final String CONTENT_DISPOSITION = "Content-Disposition";
  private static final String CONTENT_ENCODING = "Content-Encoding";
  private static final String CONTENT_LENGTH = "Content-Length";
  private static final String CONTENT_TYPE = "Content-Type";
  private static final String DATE = "Date";
  private static final String[] DATE_FORMATS = { "EEE, dd MMM yyyy HH:mm:ss zzz", "EEE, dd-MMM-yy HH:mm:ss zzz", "EEE MMM dd HH:mm:ss yyyy" };
  private static final String ETAG = "ETag";
  private static final String EXPIRES = "Expires";
  private static TimeZone GMT = TimeZone.getTimeZone("GMT");
  private static final String IF_MODIFIED_SINCE = "If-Modified-Since";
  private static final String IF_NONE_MATCH = "If-None-Match";
  private static final String LAST_MODIFIED = "Last-Modified";
  private static final String LOCATION = "Location";
  private static final String PRAGMA = "Pragma";
  private static final String USER_AGENT = "User-Agent";
  private final Map<String, List<String>> headers;
  
  public HttpHeaders()
  {
    this(new LinkedCaseInsensitiveMap(8, Locale.ENGLISH), false);
  }
  
  private HttpHeaders(Map<String, List<String>> paramMap, boolean paramBoolean)
  {
    Assert.notNull(paramMap, "'headers' must not be null");
    if (paramBoolean)
    {
      LinkedCaseInsensitiveMap localLinkedCaseInsensitiveMap = new LinkedCaseInsensitiveMap(paramMap.size(), Locale.ENGLISH);
      paramMap = paramMap.entrySet().iterator();
      while (paramMap.hasNext())
      {
        Map.Entry localEntry = (Map.Entry)paramMap.next();
        List localList = Collections.unmodifiableList((List)localEntry.getValue());
        localLinkedCaseInsensitiveMap.put(localEntry.getKey(), localList);
      }
      headers = Collections.unmodifiableMap(localLinkedCaseInsensitiveMap);
      return;
    }
    headers = paramMap;
  }
  
  private long getFirstDate(String paramString)
  {
    String str = getFirst(paramString);
    if (str == null) {
      return -1L;
    }
    String[] arrayOfString = DATE_FORMATS;
    int j = arrayOfString.length;
    int i = 0;
    while (i < j)
    {
      SimpleDateFormat localSimpleDateFormat = new SimpleDateFormat(arrayOfString[i], Locale.US);
      localSimpleDateFormat.setTimeZone(GMT);
      try
      {
        long l = localSimpleDateFormat.parse(str).getTime();
        return l;
      }
      catch (ParseException localParseException)
      {
        i += 1;
      }
    }
    throw new IllegalArgumentException("Cannot parse date value \"" + str + "\" for \"" + paramString + "\" header");
  }
  
  public static HttpHeaders readOnlyHttpHeaders(HttpHeaders paramHttpHeaders)
  {
    return new HttpHeaders(paramHttpHeaders, true);
  }
  
  private void setDate(String paramString, long paramLong)
  {
    SimpleDateFormat localSimpleDateFormat = new SimpleDateFormat(DATE_FORMATS[0], Locale.US);
    localSimpleDateFormat.setTimeZone(GMT);
    set(paramString, localSimpleDateFormat.format(new Date(paramLong)));
  }
  
  public void add(String paramString1, String paramString2)
  {
    List localList = (List)headers.get(paramString1);
    Object localObject = localList;
    if (localList == null)
    {
      localObject = new LinkedList();
      headers.put(paramString1, localObject);
    }
    ((List)localObject).add(paramString2);
  }
  
  public void clear()
  {
    headers.clear();
  }
  
  public boolean containsKey(Object paramObject)
  {
    return headers.containsKey(paramObject);
  }
  
  public boolean containsValue(Object paramObject)
  {
    return headers.containsValue(paramObject);
  }
  
  public Set<Map.Entry<String, List<String>>> entrySet()
  {
    return headers.entrySet();
  }
  
  public boolean equals(Object paramObject)
  {
    if (this == paramObject) {
      return true;
    }
    if (!(paramObject instanceof HttpHeaders)) {
      return false;
    }
    paramObject = (HttpHeaders)paramObject;
    return headers.equals(headers);
  }
  
  public List<String> get(Object paramObject)
  {
    return (List)headers.get(paramObject);
  }
  
  public List<MediaType> getAccept()
  {
    String str = getFirst("Accept");
    if (str != null) {
      return MediaType.parseMediaTypes(str);
    }
    return Collections.emptyList();
  }
  
  public List<Charset> getAcceptCharset()
  {
    ArrayList localArrayList = new ArrayList();
    String str = getFirst("Accept-Charset");
    if (str != null)
    {
      String[] arrayOfString = str.split(",\\s*");
      int j = arrayOfString.length;
      int i = 0;
      if (i < j)
      {
        str = arrayOfString[i];
        int k = str.indexOf(';');
        if (k == -1) {}
        for (;;)
        {
          if (!str.equals("*")) {
            localArrayList.add(Charset.forName(str));
          }
          i += 1;
          break;
          str = str.substring(0, k);
        }
      }
    }
    return localArrayList;
  }
  
  public List<ContentCodingType> getAcceptEncoding()
  {
    String str = getFirst("Accept-Encoding");
    if (str != null) {
      return ContentCodingType.parseCodingTypes(str);
    }
    return Collections.emptyList();
  }
  
  public String getAcceptLanguage()
  {
    return getFirst("Accept-Language");
  }
  
  public Set<HttpMethod> getAllow()
  {
    Object localObject = getFirst("Allow");
    if (localObject != null)
    {
      ArrayList localArrayList = new ArrayList(5);
      localObject = ((String)localObject).split(",\\s*");
      int j = localObject.length;
      int i = 0;
      while (i < j)
      {
        localArrayList.add(HttpMethod.valueOf(localObject[i]));
        i += 1;
      }
      return EnumSet.copyOf(localArrayList);
    }
    return EnumSet.noneOf(HttpMethod.class);
  }
  
  public String getAuthorization()
  {
    return getFirst("Authorization");
  }
  
  public String getCacheControl()
  {
    return getFirst("Cache-Control");
  }
  
  public List<ContentCodingType> getContentEncoding()
  {
    String str = getFirst("Content-Encoding");
    if (str != null) {
      return ContentCodingType.parseCodingTypes(str);
    }
    return Collections.emptyList();
  }
  
  public long getContentLength()
  {
    String str = getFirst("Content-Length");
    if (str != null) {
      return Long.parseLong(str);
    }
    return -1L;
  }
  
  public MediaType getContentType()
  {
    String str = getFirst("Content-Type");
    if (str != null) {
      return MediaType.parseMediaType(str);
    }
    return null;
  }
  
  public long getDate()
  {
    return getFirstDate("Date");
  }
  
  public String getETag()
  {
    return getFirst("ETag");
  }
  
  public long getExpires()
  {
    return getFirstDate("Expires");
  }
  
  public String getFirst(String paramString)
  {
    paramString = (List)headers.get(paramString);
    if (paramString != null) {
      return (String)paramString.get(0);
    }
    return null;
  }
  
  public List<String> getIfNoneMatch()
  {
    ArrayList localArrayList = new ArrayList();
    Object localObject = getFirst("If-None-Match");
    if (localObject != null)
    {
      localObject = ((String)localObject).split(",\\s*");
      int j = localObject.length;
      int i = 0;
      while (i < j)
      {
        localArrayList.add(localObject[i]);
        i += 1;
      }
    }
    return localArrayList;
  }
  
  public long getIfNotModifiedSince()
  {
    return getFirstDate("If-Modified-Since");
  }
  
  public long getLastModified()
  {
    return getFirstDate("Last-Modified");
  }
  
  public URI getLocation()
  {
    String str = getFirst("Location");
    if (str != null) {
      return URI.create(str);
    }
    return null;
  }
  
  public String getPragma()
  {
    return getFirst("Pragma");
  }
  
  public String getUserAgent()
  {
    return getFirst("User-Agent");
  }
  
  public int hashCode()
  {
    return headers.hashCode();
  }
  
  public boolean isEmpty()
  {
    return headers.isEmpty();
  }
  
  public Set<String> keySet()
  {
    return headers.keySet();
  }
  
  public List<String> put(String paramString, List<String> paramList)
  {
    return (List)headers.put(paramString, paramList);
  }
  
  public void putAll(Map<? extends String, ? extends List<String>> paramMap)
  {
    headers.putAll(paramMap);
  }
  
  public List<String> remove(Object paramObject)
  {
    return (List)headers.remove(paramObject);
  }
  
  public void set(String paramString1, String paramString2)
  {
    LinkedList localLinkedList = new LinkedList();
    localLinkedList.add(paramString2);
    headers.put(paramString1, localLinkedList);
  }
  
  public void setAccept(List<MediaType> paramList)
  {
    set("Accept", MediaType.toString(paramList));
  }
  
  public void setAcceptCharset(List<Charset> paramList)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    paramList = paramList.iterator();
    while (paramList.hasNext())
    {
      localStringBuilder.append(((Charset)paramList.next()).name().toLowerCase(Locale.ENGLISH));
      if (paramList.hasNext()) {
        localStringBuilder.append(", ");
      }
    }
    set("Accept-Charset", localStringBuilder.toString());
  }
  
  public void setAcceptEncoding(List<ContentCodingType> paramList)
  {
    set("Accept-Encoding", ContentCodingType.toString(paramList));
  }
  
  public void setAcceptEncoding(ContentCodingType paramContentCodingType)
  {
    setAcceptEncoding(Collections.singletonList(paramContentCodingType));
  }
  
  public void setAcceptLanguage(String paramString)
  {
    set("Accept-Language", paramString);
  }
  
  public void setAll(Map<String, String> paramMap)
  {
    paramMap = paramMap.entrySet().iterator();
    while (paramMap.hasNext())
    {
      Map.Entry localEntry = (Map.Entry)paramMap.next();
      set((String)localEntry.getKey(), (String)localEntry.getValue());
    }
  }
  
  public void setAllow(Set<HttpMethod> paramSet)
  {
    set("Allow", StringUtils.collectionToCommaDelimitedString(paramSet));
  }
  
  public void setAuthorization(HttpAuthentication paramHttpAuthentication)
  {
    set("Authorization", paramHttpAuthentication.getHeaderValue());
  }
  
  public void setCacheControl(String paramString)
  {
    set("Cache-Control", paramString);
  }
  
  public void setContentDispositionFormData(String paramString1, String paramString2)
  {
    Assert.notNull(paramString1, "'name' must not be null");
    StringBuilder localStringBuilder = new StringBuilder("form-data; name=\"");
    localStringBuilder.append(paramString1).append('"');
    if (paramString2 != null)
    {
      localStringBuilder.append("; filename=\"");
      localStringBuilder.append(paramString2).append('"');
    }
    set("Content-Disposition", localStringBuilder.toString());
  }
  
  public void setContentEncoding(List<ContentCodingType> paramList)
  {
    set("Content-Encoding", ContentCodingType.toString(paramList));
  }
  
  public void setContentEncoding(ContentCodingType paramContentCodingType)
  {
    setContentEncoding(Collections.singletonList(paramContentCodingType));
  }
  
  public void setContentLength(long paramLong)
  {
    set("Content-Length", Long.toString(paramLong));
  }
  
  public void setContentType(MediaType paramMediaType)
  {
    boolean bool2 = true;
    if (!paramMediaType.isWildcardType())
    {
      bool1 = true;
      Assert.isTrue(bool1, "'Content-Type' cannot contain wildcard type '*'");
      if (paramMediaType.isWildcardSubtype()) {
        break label50;
      }
    }
    label50:
    for (boolean bool1 = bool2;; bool1 = false)
    {
      Assert.isTrue(bool1, "'Content-Type' cannot contain wildcard subtype '*'");
      set("Content-Type", paramMediaType.toString());
      return;
      bool1 = false;
      break;
    }
  }
  
  public void setDate(long paramLong)
  {
    setDate("Date", paramLong);
  }
  
  public void setETag(String paramString)
  {
    if (paramString != null) {
      if ((!paramString.startsWith("\"")) && (!paramString.startsWith("W/"))) {
        break label54;
      }
    }
    label54:
    for (boolean bool = true;; bool = false)
    {
      Assert.isTrue(bool, "Invalid eTag, does not start with W/ or \"");
      Assert.isTrue(paramString.endsWith("\""), "Invalid eTag, does not end with \"");
      set("ETag", paramString);
      return;
    }
  }
  
  public void setExpires(long paramLong)
  {
    setDate("Expires", paramLong);
  }
  
  public void setIfModifiedSince(long paramLong)
  {
    setDate("If-Modified-Since", paramLong);
  }
  
  public void setIfNoneMatch(String paramString)
  {
    set("If-None-Match", paramString);
  }
  
  public void setIfNoneMatch(List<String> paramList)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    paramList = paramList.iterator();
    while (paramList.hasNext())
    {
      localStringBuilder.append((String)paramList.next());
      if (paramList.hasNext()) {
        localStringBuilder.append(", ");
      }
    }
    set("If-None-Match", localStringBuilder.toString());
  }
  
  public void setLastModified(long paramLong)
  {
    setDate("Last-Modified", paramLong);
  }
  
  public void setLocation(URI paramURI)
  {
    set("Location", paramURI.toASCIIString());
  }
  
  public void setPragma(String paramString)
  {
    set("Pragma", paramString);
  }
  
  public void setUserAgent(String paramString)
  {
    set("User-Agent", paramString);
  }
  
  public int size()
  {
    return headers.size();
  }
  
  public Map<String, String> toSingleValueMap()
  {
    LinkedHashMap localLinkedHashMap = new LinkedHashMap(headers.size());
    Iterator localIterator = headers.entrySet().iterator();
    while (localIterator.hasNext())
    {
      Map.Entry localEntry = (Map.Entry)localIterator.next();
      localLinkedHashMap.put(localEntry.getKey(), ((List)localEntry.getValue()).get(0));
    }
    return localLinkedHashMap;
  }
  
  public String toString()
  {
    return headers.toString();
  }
  
  public Collection<List<String>> values()
  {
    return headers.values();
  }
}

/* Location:
 * Qualified Name:     org.springframework.http.HttpHeaders
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */