package org.springframework.http;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeSet;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;
import org.springframework.util.LinkedCaseInsensitiveMap;
import org.springframework.util.StringUtils;

public class MediaType
  implements Comparable<MediaType>
{
  public static final MediaType ALL;
  public static final String ALL_VALUE = "*/*";
  public static final MediaType APPLICATION_ATOM_XML;
  public static final String APPLICATION_ATOM_XML_VALUE = "application/atom+xml";
  public static final MediaType APPLICATION_FORM_URLENCODED;
  public static final String APPLICATION_FORM_URLENCODED_VALUE = "application/x-www-form-urlencoded";
  public static final MediaType APPLICATION_JSON;
  public static final String APPLICATION_JSON_VALUE = "application/json";
  public static final MediaType APPLICATION_OCTET_STREAM;
  public static final String APPLICATION_OCTET_STREAM_VALUE = "application/octet-stream";
  public static final MediaType APPLICATION_RSS_XML;
  public static final String APPLICATION_RSS_XML_VALUE = "application/rss+xml";
  public static final MediaType APPLICATION_WILDCARD_XML;
  public static final String APPLICATION_WILDCARD_XML_VALUE = "application/*+xml";
  public static final MediaType APPLICATION_XHTML_XML;
  public static final String APPLICATION_XHTML_XML_VALUE = "application/xhtml+xml";
  public static final MediaType APPLICATION_XML;
  public static final String APPLICATION_XML_VALUE = "application/xml";
  public static final MediaType IMAGE_GIF;
  public static final String IMAGE_GIF_VALUE = "image/gif";
  public static final MediaType IMAGE_JPEG;
  public static final String IMAGE_JPEG_VALUE = "image/jpeg";
  public static final MediaType IMAGE_PNG;
  public static final String IMAGE_PNG_VALUE = "image/png";
  public static final MediaType MULTIPART_FORM_DATA;
  public static final String MULTIPART_FORM_DATA_VALUE = "multipart/form-data";
  private static final String PARAM_CHARSET = "charset";
  private static final String PARAM_QUALITY_FACTOR = "q";
  public static final Comparator<MediaType> QUALITY_VALUE_COMPARATOR = new Comparator()
  {
    public int compare(MediaType paramAnonymousMediaType1, MediaType paramAnonymousMediaType2)
    {
      int j = -1;
      double d = paramAnonymousMediaType1.getQualityValue();
      int i = Double.compare(paramAnonymousMediaType2.getQualityValue(), d);
      if (i != 0) {}
      int k;
      int m;
      do
      {
        do
        {
          do
          {
            return i;
            if ((paramAnonymousMediaType1.isWildcardType()) && (!paramAnonymousMediaType2.isWildcardType())) {
              return 1;
            }
            if (!paramAnonymousMediaType2.isWildcardType()) {
              break;
            }
            i = j;
          } while (!paramAnonymousMediaType1.isWildcardType());
          if (!paramAnonymousMediaType1.getType().equals(paramAnonymousMediaType2.getType())) {
            return 0;
          }
          if ((paramAnonymousMediaType1.isWildcardSubtype()) && (!paramAnonymousMediaType2.isWildcardSubtype())) {
            return 1;
          }
          if (!paramAnonymousMediaType2.isWildcardSubtype()) {
            break;
          }
          i = j;
        } while (!paramAnonymousMediaType1.isWildcardSubtype());
        if (!paramAnonymousMediaType1.getSubtype().equals(paramAnonymousMediaType2.getSubtype())) {
          return 0;
        }
        k = parameters.size();
        m = parameters.size();
        i = j;
      } while (m < k);
      if (m == k) {
        return 0;
      }
      return 1;
    }
  };
  public static final Comparator<MediaType> SPECIFICITY_COMPARATOR;
  public static final MediaType TEXT_HTML;
  public static final String TEXT_HTML_VALUE = "text/html";
  public static final MediaType TEXT_PLAIN;
  public static final String TEXT_PLAIN_VALUE = "text/plain";
  public static final MediaType TEXT_XML;
  public static final String TEXT_XML_VALUE = "text/xml";
  private static final BitSet TOKEN;
  private static final String WILDCARD_TYPE = "*";
  private final Map<String, String> parameters;
  private final String subtype;
  private final String type;
  
  static
  {
    BitSet localBitSet1 = new BitSet(128);
    int i = 0;
    while (i <= 31)
    {
      localBitSet1.set(i);
      i += 1;
    }
    localBitSet1.set(127);
    BitSet localBitSet2 = new BitSet(128);
    localBitSet2.set(40);
    localBitSet2.set(41);
    localBitSet2.set(60);
    localBitSet2.set(62);
    localBitSet2.set(64);
    localBitSet2.set(44);
    localBitSet2.set(59);
    localBitSet2.set(58);
    localBitSet2.set(92);
    localBitSet2.set(34);
    localBitSet2.set(47);
    localBitSet2.set(91);
    localBitSet2.set(93);
    localBitSet2.set(63);
    localBitSet2.set(61);
    localBitSet2.set(123);
    localBitSet2.set(125);
    localBitSet2.set(32);
    localBitSet2.set(9);
    TOKEN = new BitSet(128);
    TOKEN.set(0, 128);
    TOKEN.andNot(localBitSet1);
    TOKEN.andNot(localBitSet2);
    ALL = valueOf("*/*");
    APPLICATION_ATOM_XML = valueOf("application/atom+xml");
    APPLICATION_RSS_XML = valueOf("application/rss+xml");
    APPLICATION_FORM_URLENCODED = valueOf("application/x-www-form-urlencoded");
    APPLICATION_JSON = valueOf("application/json");
    APPLICATION_OCTET_STREAM = valueOf("application/octet-stream");
    APPLICATION_XHTML_XML = valueOf("application/xhtml+xml");
    APPLICATION_XML = valueOf("application/xml");
    APPLICATION_WILDCARD_XML = valueOf("application/*+xml");
    IMAGE_GIF = valueOf("image/gif");
    IMAGE_JPEG = valueOf("image/jpeg");
    IMAGE_PNG = valueOf("image/png");
    MULTIPART_FORM_DATA = valueOf("multipart/form-data");
    TEXT_HTML = valueOf("text/html");
    TEXT_PLAIN = valueOf("text/plain");
    TEXT_XML = valueOf("text/xml");
    SPECIFICITY_COMPARATOR = new Comparator()
    {
      public int compare(MediaType paramAnonymousMediaType1, MediaType paramAnonymousMediaType2)
      {
        int j = -1;
        int i;
        if ((paramAnonymousMediaType1.isWildcardType()) && (!paramAnonymousMediaType2.isWildcardType())) {
          i = 1;
        }
        int k;
        int m;
        do
        {
          do
          {
            do
            {
              return i;
              if (!paramAnonymousMediaType2.isWildcardType()) {
                break;
              }
              i = j;
            } while (!paramAnonymousMediaType1.isWildcardType());
            if (!paramAnonymousMediaType1.getType().equals(paramAnonymousMediaType2.getType())) {
              return 0;
            }
            if ((paramAnonymousMediaType1.isWildcardSubtype()) && (!paramAnonymousMediaType2.isWildcardSubtype())) {
              return 1;
            }
            if (!paramAnonymousMediaType2.isWildcardSubtype()) {
              break;
            }
            i = j;
          } while (!paramAnonymousMediaType1.isWildcardSubtype());
          if (!paramAnonymousMediaType1.getSubtype().equals(paramAnonymousMediaType2.getSubtype())) {
            return 0;
          }
          double d = paramAnonymousMediaType1.getQualityValue();
          i = Double.compare(paramAnonymousMediaType2.getQualityValue(), d);
          if (i != 0) {
            return i;
          }
          k = parameters.size();
          m = parameters.size();
          i = j;
        } while (m < k);
        if (m == k) {
          return 0;
        }
        return 1;
      }
    };
  }
  
  public MediaType(String paramString)
  {
    this(paramString, "*");
  }
  
  public MediaType(String paramString1, String paramString2)
  {
    this(paramString1, paramString2, Collections.emptyMap());
  }
  
  public MediaType(String paramString1, String paramString2, double paramDouble)
  {
    this(paramString1, paramString2, Collections.singletonMap("q", Double.toString(paramDouble)));
  }
  
  public MediaType(String paramString1, String paramString2, Charset paramCharset)
  {
    this(paramString1, paramString2, Collections.singletonMap("charset", paramCharset.displayName()));
  }
  
  public MediaType(String paramString1, String paramString2, Map<String, String> paramMap)
  {
    Assert.hasLength(paramString1, "'type' must not be empty");
    Assert.hasLength(paramString2, "'subtype' must not be empty");
    checkToken(paramString1);
    checkToken(paramString2);
    type = paramString1.toLowerCase(Locale.ENGLISH);
    subtype = paramString2.toLowerCase(Locale.ENGLISH);
    if (!CollectionUtils.isEmpty(paramMap))
    {
      paramString1 = new LinkedCaseInsensitiveMap(paramMap.size(), Locale.ENGLISH);
      paramString2 = paramMap.entrySet().iterator();
      while (paramString2.hasNext())
      {
        Object localObject = (Map.Entry)paramString2.next();
        paramMap = (String)((Map.Entry)localObject).getKey();
        localObject = (String)((Map.Entry)localObject).getValue();
        checkParameters(paramMap, (String)localObject);
        paramString1.put(paramMap, localObject);
      }
      parameters = Collections.unmodifiableMap(paramString1);
      return;
    }
    parameters = Collections.emptyMap();
  }
  
  public MediaType(MediaType paramMediaType, Map<String, String> paramMap)
  {
    this(paramMediaType.getType(), paramMediaType.getSubtype(), paramMap);
  }
  
  private void appendTo(StringBuilder paramStringBuilder)
  {
    paramStringBuilder.append(type);
    paramStringBuilder.append('/');
    paramStringBuilder.append(subtype);
    appendTo(parameters, paramStringBuilder);
  }
  
  private void appendTo(Map<String, String> paramMap, StringBuilder paramStringBuilder)
  {
    paramMap = paramMap.entrySet().iterator();
    while (paramMap.hasNext())
    {
      Map.Entry localEntry = (Map.Entry)paramMap.next();
      paramStringBuilder.append(';');
      paramStringBuilder.append((String)localEntry.getKey());
      paramStringBuilder.append('=');
      paramStringBuilder.append((String)localEntry.getValue());
    }
  }
  
  private void checkParameters(String paramString1, String paramString2)
  {
    Assert.hasLength(paramString1, "parameter attribute must not be empty");
    Assert.hasLength(paramString2, "parameter value must not be empty");
    checkToken(paramString1);
    boolean bool;
    if ("q".equals(paramString1))
    {
      paramString1 = unquote(paramString2);
      double d = Double.parseDouble(paramString1);
      if ((d >= 0.0D) && (d <= 1.0D))
      {
        bool = true;
        Assert.isTrue(bool, "Invalid quality value \"" + paramString1 + "\": should be between 0.0 and 1.0");
      }
    }
    do
    {
      return;
      bool = false;
      break;
      if ("charset".equals(paramString1))
      {
        Charset.forName(unquote(paramString2));
        return;
      }
    } while (isQuotedString(paramString2));
    checkToken(paramString2);
  }
  
  private void checkToken(String paramString)
  {
    int i = 0;
    while (i < paramString.length())
    {
      char c = paramString.charAt(i);
      if (!TOKEN.get(c)) {
        throw new IllegalArgumentException("Invalid token character '" + c + "' in token \"" + paramString + "\"");
      }
      i += 1;
    }
  }
  
  private boolean isQuotedString(String paramString)
  {
    return (paramString.length() > 1) && (paramString.startsWith("\"")) && (paramString.endsWith("\""));
  }
  
  public static MediaType parseMediaType(String paramString)
  {
    Assert.hasLength(paramString, "'mediaType' must not be empty");
    String[] arrayOfString = StringUtils.tokenizeToStringArray(paramString, ";");
    String str1 = arrayOfString[0].trim();
    Object localObject = str1;
    if ("*".equals(str1)) {
      localObject = "*/*";
    }
    int i = ((String)localObject).indexOf('/');
    if (i == -1) {
      throw new IllegalArgumentException("\"" + paramString + "\" does not contain '/'");
    }
    if (i == ((String)localObject).length() - 1) {
      throw new IllegalArgumentException("\"" + paramString + "\" does not contain subtype after '/'");
    }
    str1 = ((String)localObject).substring(0, i);
    String str2 = ((String)localObject).substring(i + 1, ((String)localObject).length());
    if (("*".equals(str1)) && (!"*".equals(str2))) {
      throw new IllegalArgumentException("A wildcard type is legal only in '*/*' (all media types).");
    }
    paramString = null;
    if (arrayOfString.length > 1)
    {
      localObject = new LinkedHashMap(arrayOfString.length - 1);
      i = 1;
      for (;;)
      {
        paramString = (String)localObject;
        if (i >= arrayOfString.length) {
          break;
        }
        paramString = arrayOfString[i];
        int j = paramString.indexOf('=');
        if (j != -1) {
          ((Map)localObject).put(paramString.substring(0, j), paramString.substring(j + 1, paramString.length()));
        }
        i += 1;
      }
    }
    return new MediaType(str1, str2, paramString);
  }
  
  public static List<MediaType> parseMediaTypes(String paramString)
  {
    if (!StringUtils.hasLength(paramString))
    {
      paramString = Collections.emptyList();
      return paramString;
    }
    String[] arrayOfString = paramString.split(",\\s*");
    ArrayList localArrayList = new ArrayList(arrayOfString.length);
    int j = arrayOfString.length;
    int i = 0;
    for (;;)
    {
      paramString = localArrayList;
      if (i >= j) {
        break;
      }
      localArrayList.add(parseMediaType(arrayOfString[i]));
      i += 1;
    }
  }
  
  public static void sortByQualityValue(List<MediaType> paramList)
  {
    Assert.notNull(paramList, "'mediaTypes' must not be null");
    if (paramList.size() > 1) {
      Collections.sort(paramList, QUALITY_VALUE_COMPARATOR);
    }
  }
  
  public static void sortBySpecificity(List<MediaType> paramList)
  {
    Assert.notNull(paramList, "'mediaTypes' must not be null");
    if (paramList.size() > 1) {
      Collections.sort(paramList, SPECIFICITY_COMPARATOR);
    }
  }
  
  public static String toString(Collection<MediaType> paramCollection)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    paramCollection = paramCollection.iterator();
    while (paramCollection.hasNext())
    {
      ((MediaType)paramCollection.next()).appendTo(localStringBuilder);
      if (paramCollection.hasNext()) {
        localStringBuilder.append(", ");
      }
    }
    return localStringBuilder.toString();
  }
  
  private String unquote(String paramString)
  {
    String str;
    if (paramString == null) {
      str = null;
    }
    do
    {
      return str;
      str = paramString;
    } while (!isQuotedString(paramString));
    return paramString.substring(1, paramString.length() - 1);
  }
  
  public static MediaType valueOf(String paramString)
  {
    return parseMediaType(paramString);
  }
  
  public int compareTo(MediaType paramMediaType)
  {
    int i = type.compareToIgnoreCase(type);
    if (i != 0) {
      return i;
    }
    i = subtype.compareToIgnoreCase(subtype);
    if (i != 0) {
      return i;
    }
    i = parameters.size() - parameters.size();
    if (i != 0) {
      return i;
    }
    Object localObject2 = new TreeSet(String.CASE_INSENSITIVE_ORDER);
    ((TreeSet)localObject2).addAll(parameters.keySet());
    Object localObject1 = new TreeSet(String.CASE_INSENSITIVE_ORDER);
    ((TreeSet)localObject1).addAll(parameters.keySet());
    Iterator localIterator1 = ((TreeSet)localObject2).iterator();
    Iterator localIterator2 = ((TreeSet)localObject1).iterator();
    while (localIterator1.hasNext())
    {
      localObject2 = (String)localIterator1.next();
      localObject1 = (String)localIterator2.next();
      i = ((String)localObject2).compareToIgnoreCase((String)localObject1);
      if (i != 0) {
        return i;
      }
      String str = (String)parameters.get(localObject2);
      localObject2 = (String)parameters.get(localObject1);
      localObject1 = localObject2;
      if (localObject2 == null) {
        localObject1 = "";
      }
      i = str.compareTo((String)localObject1);
      if (i != 0) {
        return i;
      }
    }
    return 0;
  }
  
  public boolean equals(Object paramObject)
  {
    if (this == paramObject) {}
    do
    {
      return true;
      if (!(paramObject instanceof MediaType)) {
        return false;
      }
      paramObject = (MediaType)paramObject;
    } while ((type.equalsIgnoreCase(type)) && (subtype.equalsIgnoreCase(subtype)) && (parameters.equals(parameters)));
    return false;
  }
  
  public Charset getCharSet()
  {
    String str = getParameter("charset");
    if (str != null) {
      return Charset.forName(unquote(str));
    }
    return null;
  }
  
  public String getParameter(String paramString)
  {
    return (String)parameters.get(paramString);
  }
  
  public Map<String, String> getParameters()
  {
    return parameters;
  }
  
  public double getQualityValue()
  {
    String str = getParameter("q");
    if (str != null) {
      return Double.parseDouble(unquote(str));
    }
    return 1.0D;
  }
  
  public String getSubtype()
  {
    return subtype;
  }
  
  public String getType()
  {
    return type;
  }
  
  public int hashCode()
  {
    return (type.hashCode() * 31 + subtype.hashCode()) * 31 + parameters.hashCode();
  }
  
  public boolean includes(MediaType paramMediaType)
  {
    if (paramMediaType == null) {}
    int i;
    int j;
    String str;
    do
    {
      do
      {
        do
        {
          return false;
          if (isWildcardType()) {
            return true;
          }
        } while (!type.equals(type));
        if ((subtype.equals(subtype)) || (isWildcardSubtype())) {
          return true;
        }
        i = subtype.indexOf('+');
        j = subtype.indexOf('+');
      } while ((i == -1) || (j == -1));
      str = subtype.substring(0, i);
    } while ((!subtype.substring(i + 1).equals(subtype.substring(j + 1))) || (!"*".equals(str)));
    return true;
  }
  
  public boolean isCompatibleWith(MediaType paramMediaType)
  {
    if (paramMediaType == null) {}
    int i;
    int j;
    String str1;
    String str2;
    do
    {
      do
      {
        do
        {
          return false;
          if ((isWildcardType()) || (paramMediaType.isWildcardType())) {
            return true;
          }
        } while (!type.equals(type));
        if ((subtype.equals(subtype)) || (isWildcardSubtype()) || (paramMediaType.isWildcardSubtype())) {
          return true;
        }
        i = subtype.indexOf('+');
        j = subtype.indexOf('+');
      } while ((i == -1) || (j == -1));
      str1 = subtype.substring(0, i);
      str2 = subtype.substring(0, j);
    } while ((!subtype.substring(i + 1).equals(subtype.substring(j + 1))) || ((!"*".equals(str1)) && (!"*".equals(str2))));
    return true;
  }
  
  public boolean isConcrete()
  {
    return (!isWildcardType()) && (!isWildcardSubtype());
  }
  
  public boolean isWildcardSubtype()
  {
    return "*".equals(subtype);
  }
  
  public boolean isWildcardType()
  {
    return "*".equals(type);
  }
  
  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    appendTo(localStringBuilder);
    return localStringBuilder.toString();
  }
}

/* Location:
 * Qualified Name:     org.springframework.http.MediaType
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */