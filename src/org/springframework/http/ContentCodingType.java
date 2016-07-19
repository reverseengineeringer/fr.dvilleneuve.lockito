package org.springframework.http;

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

public class ContentCodingType
  implements Comparable<ContentCodingType>
{
  public static final ContentCodingType ALL;
  public static final String ALL_VALUE = "*";
  public static final ContentCodingType GZIP = valueOf("gzip");
  public static final String GZIP_VALUE = "gzip";
  public static final ContentCodingType IDENTITY;
  public static final String IDENTITY_VALUE = "identity";
  private static final String PARAM_QUALITY_FACTOR = "q";
  public static final Comparator<ContentCodingType> QUALITY_VALUE_COMPARATOR = new Comparator()
  {
    public int compare(ContentCodingType paramAnonymousContentCodingType1, ContentCodingType paramAnonymousContentCodingType2)
    {
      double d = paramAnonymousContentCodingType1.getQualityValue();
      int i = Double.compare(paramAnonymousContentCodingType2.getQualityValue(), d);
      if (i != 0) {
        return i;
      }
      if ((paramAnonymousContentCodingType1.isWildcardType()) && (!paramAnonymousContentCodingType2.isWildcardType())) {
        return 1;
      }
      if ((paramAnonymousContentCodingType2.isWildcardType()) && (!paramAnonymousContentCodingType1.isWildcardType())) {
        return -1;
      }
      if (!paramAnonymousContentCodingType1.getType().equals(paramAnonymousContentCodingType2.getType())) {
        return 0;
      }
      return 0;
    }
  };
  private static final BitSet TOKEN;
  private static final String WILDCARD_TYPE = "*";
  private final Map<String, String> parameters;
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
    ALL = valueOf("*");
    IDENTITY = valueOf("identity");
  }
  
  public ContentCodingType(String paramString)
  {
    this(paramString, Collections.emptyMap());
  }
  
  public ContentCodingType(String paramString, double paramDouble)
  {
    this(paramString, Collections.singletonMap("q", Double.toString(paramDouble)));
  }
  
  public ContentCodingType(String paramString, Map<String, String> paramMap)
  {
    Assert.hasLength(paramString, "'type' must not be empty");
    checkToken(paramString);
    type = paramString.toLowerCase(Locale.ENGLISH);
    if (!CollectionUtils.isEmpty(paramMap))
    {
      paramString = new LinkedCaseInsensitiveMap(paramMap.size(), Locale.ENGLISH);
      paramMap = paramMap.entrySet().iterator();
      while (paramMap.hasNext())
      {
        Object localObject = (Map.Entry)paramMap.next();
        String str = (String)((Map.Entry)localObject).getKey();
        localObject = (String)((Map.Entry)localObject).getValue();
        checkParameters(str, (String)localObject);
        paramString.put(str, unquote((String)localObject));
      }
      parameters = Collections.unmodifiableMap(paramString);
      return;
    }
    parameters = Collections.emptyMap();
  }
  
  private void appendTo(StringBuilder paramStringBuilder)
  {
    paramStringBuilder.append(type);
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
    if ("q".equals(paramString1))
    {
      paramString1 = unquote(paramString2);
      d = Double.parseDouble(paramString1);
      if ((d >= 0.0D) && (d <= 1.0D))
      {
        bool = true;
        Assert.isTrue(bool, "Invalid quality value \"" + paramString1 + "\": should be between 0.0 and 1.0");
      }
    }
    while (isQuotedString(paramString2)) {
      for (;;)
      {
        double d;
        return;
        boolean bool = false;
      }
    }
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
  
  public static ContentCodingType parseCodingType(String paramString)
  {
    Assert.hasLength(paramString, "'codingType' must not be empty");
    String[] arrayOfString = StringUtils.tokenizeToStringArray(paramString, ";");
    String str = arrayOfString[0].trim();
    paramString = null;
    if (arrayOfString.length > 1)
    {
      LinkedHashMap localLinkedHashMap = new LinkedHashMap(arrayOfString.length - 1);
      int i = 1;
      for (;;)
      {
        paramString = localLinkedHashMap;
        if (i >= arrayOfString.length) {
          break;
        }
        paramString = arrayOfString[i];
        int j = paramString.indexOf('=');
        if (j != -1) {
          localLinkedHashMap.put(paramString.substring(0, j), paramString.substring(j + 1, paramString.length()));
        }
        i += 1;
      }
    }
    return new ContentCodingType(str, paramString);
  }
  
  public static List<ContentCodingType> parseCodingTypes(String paramString)
  {
    if (!StringUtils.hasLength(paramString))
    {
      paramString = Collections.emptyList();
      return paramString;
    }
    String[] arrayOfString = paramString.split(",");
    ArrayList localArrayList = new ArrayList(arrayOfString.length);
    int j = arrayOfString.length;
    int i = 0;
    for (;;)
    {
      paramString = localArrayList;
      if (i >= j) {
        break;
      }
      localArrayList.add(parseCodingType(arrayOfString[i]));
      i += 1;
    }
  }
  
  public static void sortByQualityValue(List<ContentCodingType> paramList)
  {
    Assert.notNull(paramList, "'codingTypes' must not be null");
    if (paramList.size() > 1) {
      Collections.sort(paramList, QUALITY_VALUE_COMPARATOR);
    }
  }
  
  public static String toString(Collection<ContentCodingType> paramCollection)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    paramCollection = paramCollection.iterator();
    while (paramCollection.hasNext())
    {
      ((ContentCodingType)paramCollection.next()).appendTo(localStringBuilder);
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
  
  public static ContentCodingType valueOf(String paramString)
  {
    return parseCodingType(paramString);
  }
  
  public int compareTo(ContentCodingType paramContentCodingType)
  {
    int i = type.compareToIgnoreCase(type);
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
      if (!(paramObject instanceof ContentCodingType)) {
        return false;
      }
      paramObject = (ContentCodingType)paramObject;
    } while ((type.equalsIgnoreCase(type)) && (parameters.equals(parameters)));
    return false;
  }
  
  public String getParameter(String paramString)
  {
    return (String)parameters.get(paramString);
  }
  
  public double getQualityValue()
  {
    String str = getParameter("q");
    if (str != null) {
      return Double.parseDouble(str);
    }
    return 1.0D;
  }
  
  public String getType()
  {
    return type;
  }
  
  public int hashCode()
  {
    return type.hashCode() * 31 + parameters.hashCode();
  }
  
  public boolean includes(ContentCodingType paramContentCodingType)
  {
    if (paramContentCodingType == null) {}
    do
    {
      return false;
      if (isWildcardType()) {
        return true;
      }
    } while (!type.equals(type));
    return true;
  }
  
  public boolean isCompatibleWith(ContentCodingType paramContentCodingType)
  {
    if (paramContentCodingType == null) {}
    do
    {
      return false;
      if ((isWildcardType()) || (paramContentCodingType.isWildcardType())) {
        return true;
      }
    } while (!type.equals(type));
    return true;
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
 * Qualified Name:     org.springframework.http.ContentCodingType
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */