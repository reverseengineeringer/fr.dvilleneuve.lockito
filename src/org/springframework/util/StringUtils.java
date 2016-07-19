package org.springframework.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Properties;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeSet;

public abstract class StringUtils
{
  private static final String CURRENT_PATH = ".";
  private static final char EXTENSION_SEPARATOR = '.';
  private static final String FOLDER_SEPARATOR = "/";
  private static final String TOP_PATH = "..";
  private static final String WINDOWS_FOLDER_SEPARATOR = "\\";
  
  public static String[] addStringToArray(String[] paramArrayOfString, String paramString)
  {
    if (ObjectUtils.isEmpty(paramArrayOfString)) {
      return new String[] { paramString };
    }
    String[] arrayOfString = new String[paramArrayOfString.length + 1];
    System.arraycopy(paramArrayOfString, 0, arrayOfString, 0, paramArrayOfString.length);
    arrayOfString[paramArrayOfString.length] = paramString;
    return arrayOfString;
  }
  
  public static String applyRelativePath(String paramString1, String paramString2)
  {
    int i = paramString1.lastIndexOf("/");
    String str = paramString2;
    if (i != -1)
    {
      str = paramString1.substring(0, i);
      paramString1 = str;
      if (!paramString2.startsWith("/")) {
        paramString1 = str + "/";
      }
      str = paramString1 + paramString2;
    }
    return str;
  }
  
  public static String arrayToCommaDelimitedString(Object[] paramArrayOfObject)
  {
    return arrayToDelimitedString(paramArrayOfObject, ",");
  }
  
  public static String arrayToDelimitedString(Object[] paramArrayOfObject, String paramString)
  {
    if (ObjectUtils.isEmpty(paramArrayOfObject)) {
      return "";
    }
    if (paramArrayOfObject.length == 1) {
      return ObjectUtils.nullSafeToString(paramArrayOfObject[0]);
    }
    StringBuilder localStringBuilder = new StringBuilder();
    int i = 0;
    while (i < paramArrayOfObject.length)
    {
      if (i > 0) {
        localStringBuilder.append(paramString);
      }
      localStringBuilder.append(paramArrayOfObject[i]);
      i += 1;
    }
    return localStringBuilder.toString();
  }
  
  public static String capitalize(String paramString)
  {
    return changeFirstCharacterCase(paramString, true);
  }
  
  private static String changeFirstCharacterCase(String paramString, boolean paramBoolean)
  {
    if ((paramString == null) || (paramString.length() == 0)) {
      return paramString;
    }
    StringBuilder localStringBuilder = new StringBuilder(paramString.length());
    if (paramBoolean) {
      localStringBuilder.append(Character.toUpperCase(paramString.charAt(0)));
    }
    for (;;)
    {
      localStringBuilder.append(paramString.substring(1));
      return localStringBuilder.toString();
      localStringBuilder.append(Character.toLowerCase(paramString.charAt(0)));
    }
  }
  
  public static String cleanPath(String paramString)
  {
    if (paramString == null) {
      return null;
    }
    Object localObject2 = replace(paramString, "\\", "/");
    int i = ((String)localObject2).indexOf(":");
    Object localObject1 = "";
    paramString = (String)localObject2;
    if (i != -1)
    {
      localObject1 = ((String)localObject2).substring(0, i + 1);
      paramString = ((String)localObject2).substring(i + 1);
    }
    String str = paramString;
    localObject2 = localObject1;
    if (paramString.startsWith("/"))
    {
      localObject2 = (String)localObject1 + "/";
      str = paramString.substring(1);
    }
    paramString = delimitedListToStringArray(str, "/");
    localObject1 = new LinkedList();
    i = 0;
    int j = paramString.length - 1;
    if (j >= 0)
    {
      str = paramString[j];
      if (".".equals(str)) {}
      for (;;)
      {
        j -= 1;
        break;
        if ("..".equals(str)) {
          i += 1;
        } else if (i > 0) {
          i -= 1;
        } else {
          ((List)localObject1).add(0, str);
        }
      }
    }
    j = 0;
    while (j < i)
    {
      ((List)localObject1).add(0, "..");
      j += 1;
    }
    return (String)localObject2 + collectionToDelimitedString((Collection)localObject1, "/");
  }
  
  public static String collectionToCommaDelimitedString(Collection<?> paramCollection)
  {
    return collectionToDelimitedString(paramCollection, ",");
  }
  
  public static String collectionToDelimitedString(Collection<?> paramCollection, String paramString)
  {
    return collectionToDelimitedString(paramCollection, paramString, "", "");
  }
  
  public static String collectionToDelimitedString(Collection<?> paramCollection, String paramString1, String paramString2, String paramString3)
  {
    if (CollectionUtils.isEmpty(paramCollection)) {
      return "";
    }
    StringBuilder localStringBuilder = new StringBuilder();
    paramCollection = paramCollection.iterator();
    while (paramCollection.hasNext())
    {
      localStringBuilder.append(paramString2).append(paramCollection.next()).append(paramString3);
      if (paramCollection.hasNext()) {
        localStringBuilder.append(paramString1);
      }
    }
    return localStringBuilder.toString();
  }
  
  public static Set<String> commaDelimitedListToSet(String paramString)
  {
    TreeSet localTreeSet = new TreeSet();
    paramString = commaDelimitedListToStringArray(paramString);
    int j = paramString.length;
    int i = 0;
    while (i < j)
    {
      localTreeSet.add(paramString[i]);
      i += 1;
    }
    return localTreeSet;
  }
  
  public static String[] commaDelimitedListToStringArray(String paramString)
  {
    return delimitedListToStringArray(paramString, ",");
  }
  
  public static String[] concatenateStringArrays(String[] paramArrayOfString1, String[] paramArrayOfString2)
  {
    if (ObjectUtils.isEmpty(paramArrayOfString1)) {
      return paramArrayOfString2;
    }
    if (ObjectUtils.isEmpty(paramArrayOfString2)) {
      return paramArrayOfString1;
    }
    String[] arrayOfString = new String[paramArrayOfString1.length + paramArrayOfString2.length];
    System.arraycopy(paramArrayOfString1, 0, arrayOfString, 0, paramArrayOfString1.length);
    System.arraycopy(paramArrayOfString2, 0, arrayOfString, paramArrayOfString1.length, paramArrayOfString2.length);
    return arrayOfString;
  }
  
  public static boolean containsWhitespace(CharSequence paramCharSequence)
  {
    if (!hasLength(paramCharSequence)) {}
    for (;;)
    {
      return false;
      int j = paramCharSequence.length();
      int i = 0;
      while (i < j)
      {
        if (Character.isWhitespace(paramCharSequence.charAt(i))) {
          return true;
        }
        i += 1;
      }
    }
  }
  
  public static boolean containsWhitespace(String paramString)
  {
    return containsWhitespace(paramString);
  }
  
  public static int countOccurrencesOf(String paramString1, String paramString2)
  {
    if ((paramString1 == null) || (paramString2 == null) || (paramString1.length() == 0) || (paramString2.length() == 0))
    {
      j = 0;
      return j;
    }
    int i = 0;
    int k;
    for (int j = 0;; j = k + paramString2.length())
    {
      k = paramString1.indexOf(paramString2, j);
      j = i;
      if (k == -1) {
        break;
      }
      i += 1;
    }
  }
  
  public static String delete(String paramString1, String paramString2)
  {
    return replace(paramString1, paramString2, "");
  }
  
  public static String deleteAny(String paramString1, String paramString2)
  {
    if ((!hasLength(paramString1)) || (!hasLength(paramString2))) {
      return paramString1;
    }
    StringBuilder localStringBuilder = new StringBuilder();
    int i = 0;
    while (i < paramString1.length())
    {
      char c = paramString1.charAt(i);
      if (paramString2.indexOf(c) == -1) {
        localStringBuilder.append(c);
      }
      i += 1;
    }
    return localStringBuilder.toString();
  }
  
  public static String[] delimitedListToStringArray(String paramString1, String paramString2)
  {
    return delimitedListToStringArray(paramString1, paramString2, null);
  }
  
  public static String[] delimitedListToStringArray(String paramString1, String paramString2, String paramString3)
  {
    if (paramString1 == null) {
      return new String[0];
    }
    if (paramString2 == null) {
      return new String[] { paramString1 };
    }
    ArrayList localArrayList = new ArrayList();
    if ("".equals(paramString2))
    {
      i = 0;
      while (i < paramString1.length())
      {
        localArrayList.add(deleteAny(paramString1.substring(i, i + 1), paramString3));
        i += 1;
      }
    }
    int j;
    for (int i = 0;; i = j + paramString2.length())
    {
      j = paramString1.indexOf(paramString2, i);
      if (j == -1) {
        break;
      }
      localArrayList.add(deleteAny(paramString1.substring(i, j), paramString3));
    }
    if ((paramString1.length() > 0) && (i <= paramString1.length())) {
      localArrayList.add(deleteAny(paramString1.substring(i), paramString3));
    }
    return toStringArray(localArrayList);
  }
  
  public static boolean endsWithIgnoreCase(String paramString1, String paramString2)
  {
    if ((paramString1 == null) || (paramString2 == null)) {}
    do
    {
      return false;
      if (paramString1.endsWith(paramString2)) {
        return true;
      }
    } while (paramString1.length() < paramString2.length());
    return paramString1.substring(paramString1.length() - paramString2.length()).toLowerCase().equals(paramString2.toLowerCase());
  }
  
  public static String getFilename(String paramString)
  {
    String str;
    if (paramString == null) {
      str = null;
    }
    int i;
    do
    {
      return str;
      i = paramString.lastIndexOf("/");
      str = paramString;
    } while (i == -1);
    return paramString.substring(i + 1);
  }
  
  public static String getFilenameExtension(String paramString)
  {
    if (paramString == null) {}
    int i;
    do
    {
      return null;
      i = paramString.lastIndexOf('.');
    } while ((i == -1) || (paramString.lastIndexOf("/") > i));
    return paramString.substring(i + 1);
  }
  
  public static boolean hasLength(CharSequence paramCharSequence)
  {
    return (paramCharSequence != null) && (paramCharSequence.length() > 0);
  }
  
  public static boolean hasLength(String paramString)
  {
    return hasLength(paramString);
  }
  
  public static boolean hasText(CharSequence paramCharSequence)
  {
    if (!hasLength(paramCharSequence)) {}
    for (;;)
    {
      return false;
      int j = paramCharSequence.length();
      int i = 0;
      while (i < j)
      {
        if (!Character.isWhitespace(paramCharSequence.charAt(i))) {
          return true;
        }
        i += 1;
      }
    }
  }
  
  public static boolean hasText(String paramString)
  {
    return hasText(paramString);
  }
  
  public static String[] mergeStringArrays(String[] paramArrayOfString1, String[] paramArrayOfString2)
  {
    if (ObjectUtils.isEmpty(paramArrayOfString1)) {
      return paramArrayOfString2;
    }
    if (ObjectUtils.isEmpty(paramArrayOfString2)) {
      return paramArrayOfString1;
    }
    ArrayList localArrayList = new ArrayList();
    localArrayList.addAll(Arrays.asList(paramArrayOfString1));
    int j = paramArrayOfString2.length;
    int i = 0;
    while (i < j)
    {
      paramArrayOfString1 = paramArrayOfString2[i];
      if (!localArrayList.contains(paramArrayOfString1)) {
        localArrayList.add(paramArrayOfString1);
      }
      i += 1;
    }
    return toStringArray(localArrayList);
  }
  
  public static Locale parseLocaleString(String paramString)
  {
    String[] arrayOfString = tokenizeToStringArray(paramString, "_ ", false, false);
    String str2;
    if (arrayOfString.length > 0)
    {
      str2 = arrayOfString[0];
      if (arrayOfString.length <= 1) {
        break label113;
      }
    }
    label113:
    for (String str3 = arrayOfString[1];; str3 = "")
    {
      validateLocalePart(str2);
      validateLocalePart(str3);
      String str1 = "";
      if (arrayOfString.length >= 2)
      {
        paramString = trimLeadingWhitespace(paramString.substring(paramString.indexOf(str3) + str3.length()));
        str1 = paramString;
        if (paramString.startsWith("_")) {
          str1 = trimLeadingCharacter(paramString, '_');
        }
      }
      if (str2.length() <= 0) {
        break label119;
      }
      return new Locale(str2, str3, str1);
      str2 = "";
      break;
    }
    label119:
    return null;
  }
  
  public static boolean pathEquals(String paramString1, String paramString2)
  {
    return cleanPath(paramString1).equals(cleanPath(paramString2));
  }
  
  public static String quote(String paramString)
  {
    if (paramString != null) {
      return "'" + paramString + "'";
    }
    return null;
  }
  
  public static Object quoteIfString(Object paramObject)
  {
    Object localObject = paramObject;
    if ((paramObject instanceof String)) {
      localObject = quote((String)paramObject);
    }
    return localObject;
  }
  
  public static String[] removeDuplicateStrings(String[] paramArrayOfString)
  {
    if (ObjectUtils.isEmpty(paramArrayOfString)) {
      return paramArrayOfString;
    }
    TreeSet localTreeSet = new TreeSet();
    int j = paramArrayOfString.length;
    int i = 0;
    while (i < j)
    {
      localTreeSet.add(paramArrayOfString[i]);
      i += 1;
    }
    return toStringArray(localTreeSet);
  }
  
  public static String replace(String paramString1, String paramString2, String paramString3)
  {
    if ((!hasLength(paramString1)) || (!hasLength(paramString2)) || (paramString3 == null)) {
      return paramString1;
    }
    StringBuilder localStringBuilder = new StringBuilder();
    int j = 0;
    int i = paramString1.indexOf(paramString2);
    int k = paramString2.length();
    while (i >= 0)
    {
      localStringBuilder.append(paramString1.substring(j, i));
      localStringBuilder.append(paramString3);
      j = i + k;
      i = paramString1.indexOf(paramString2, j);
    }
    localStringBuilder.append(paramString1.substring(j));
    return localStringBuilder.toString();
  }
  
  public static String[] sortStringArray(String[] paramArrayOfString)
  {
    if (ObjectUtils.isEmpty(paramArrayOfString)) {
      return new String[0];
    }
    Arrays.sort(paramArrayOfString);
    return paramArrayOfString;
  }
  
  public static String[] split(String paramString1, String paramString2)
  {
    if ((!hasLength(paramString1)) || (!hasLength(paramString2))) {}
    int i;
    do
    {
      return null;
      i = paramString1.indexOf(paramString2);
    } while (i < 0);
    return new String[] { paramString1.substring(0, i), paramString1.substring(paramString2.length() + i) };
  }
  
  public static Properties splitArrayElementsIntoProperties(String[] paramArrayOfString, String paramString)
  {
    return splitArrayElementsIntoProperties(paramArrayOfString, paramString, null);
  }
  
  public static Properties splitArrayElementsIntoProperties(String[] paramArrayOfString, String paramString1, String paramString2)
  {
    if (ObjectUtils.isEmpty(paramArrayOfString)) {
      localObject = null;
    }
    Properties localProperties;
    int j;
    int i;
    do
    {
      return (Properties)localObject;
      localProperties = new Properties();
      j = paramArrayOfString.length;
      i = 0;
      localObject = localProperties;
    } while (i >= j);
    String str = paramArrayOfString[i];
    Object localObject = str;
    if (paramString2 != null) {
      localObject = deleteAny(str, paramString2);
    }
    localObject = split((String)localObject, paramString1);
    if (localObject == null) {}
    for (;;)
    {
      i += 1;
      break;
      localProperties.setProperty(localObject[0].trim(), localObject[1].trim());
    }
  }
  
  public static boolean startsWithIgnoreCase(String paramString1, String paramString2)
  {
    if ((paramString1 == null) || (paramString2 == null)) {}
    do
    {
      return false;
      if (paramString1.startsWith(paramString2)) {
        return true;
      }
    } while (paramString1.length() < paramString2.length());
    return paramString1.substring(0, paramString2.length()).toLowerCase().equals(paramString2.toLowerCase());
  }
  
  public static String stripFilenameExtension(String paramString)
  {
    String str;
    if (paramString == null) {
      str = null;
    }
    int i;
    do
    {
      do
      {
        return str;
        i = paramString.lastIndexOf('.');
        str = paramString;
      } while (i == -1);
      str = paramString;
    } while (paramString.lastIndexOf("/") > i);
    return paramString.substring(0, i);
  }
  
  public static boolean substringMatch(CharSequence paramCharSequence1, int paramInt, CharSequence paramCharSequence2)
  {
    int i = 0;
    while (i < paramCharSequence2.length())
    {
      int j = paramInt + i;
      if ((j >= paramCharSequence1.length()) || (paramCharSequence1.charAt(j) != paramCharSequence2.charAt(i))) {
        return false;
      }
      i += 1;
    }
    return true;
  }
  
  public static String toLanguageTag(Locale paramLocale)
  {
    StringBuilder localStringBuilder = new StringBuilder().append(paramLocale.getLanguage());
    if (hasText(paramLocale.getCountry())) {}
    for (paramLocale = "-" + paramLocale.getCountry();; paramLocale = "") {
      return paramLocale;
    }
  }
  
  public static String[] toStringArray(Collection<String> paramCollection)
  {
    if (paramCollection == null) {
      return null;
    }
    return (String[])paramCollection.toArray(new String[paramCollection.size()]);
  }
  
  public static String[] toStringArray(Enumeration<String> paramEnumeration)
  {
    if (paramEnumeration == null) {
      return null;
    }
    paramEnumeration = Collections.list(paramEnumeration);
    return (String[])paramEnumeration.toArray(new String[paramEnumeration.size()]);
  }
  
  public static String[] tokenizeToStringArray(String paramString1, String paramString2)
  {
    return tokenizeToStringArray(paramString1, paramString2, true, true);
  }
  
  public static String[] tokenizeToStringArray(String paramString1, String paramString2, boolean paramBoolean1, boolean paramBoolean2)
  {
    if (paramString1 == null) {
      return null;
    }
    StringTokenizer localStringTokenizer = new StringTokenizer(paramString1, paramString2);
    ArrayList localArrayList = new ArrayList();
    while (localStringTokenizer.hasMoreTokens())
    {
      paramString2 = localStringTokenizer.nextToken();
      paramString1 = paramString2;
      if (paramBoolean1) {
        paramString1 = paramString2.trim();
      }
      if ((!paramBoolean2) || (paramString1.length() > 0)) {
        localArrayList.add(paramString1);
      }
    }
    return toStringArray(localArrayList);
  }
  
  public static String trimAllWhitespace(String paramString)
  {
    if (!hasLength(paramString)) {
      return paramString;
    }
    paramString = new StringBuilder(paramString);
    int i = 0;
    while (paramString.length() > i) {
      if (Character.isWhitespace(paramString.charAt(i))) {
        paramString.deleteCharAt(i);
      } else {
        i += 1;
      }
    }
    return paramString.toString();
  }
  
  public static String[] trimArrayElements(String[] paramArrayOfString)
  {
    if (ObjectUtils.isEmpty(paramArrayOfString)) {
      localObject = new String[0];
    }
    String[] arrayOfString;
    int i;
    do
    {
      return (String[])localObject;
      arrayOfString = new String[paramArrayOfString.length];
      i = 0;
      localObject = arrayOfString;
    } while (i >= paramArrayOfString.length);
    Object localObject = paramArrayOfString[i];
    if (localObject != null) {}
    for (localObject = ((String)localObject).trim();; localObject = null)
    {
      arrayOfString[i] = localObject;
      i += 1;
      break;
    }
  }
  
  public static String trimLeadingCharacter(String paramString, char paramChar)
  {
    if (!hasLength(paramString)) {
      return paramString;
    }
    paramString = new StringBuilder(paramString);
    while ((paramString.length() > 0) && (paramString.charAt(0) == paramChar)) {
      paramString.deleteCharAt(0);
    }
    return paramString.toString();
  }
  
  public static String trimLeadingWhitespace(String paramString)
  {
    if (!hasLength(paramString)) {
      return paramString;
    }
    paramString = new StringBuilder(paramString);
    while ((paramString.length() > 0) && (Character.isWhitespace(paramString.charAt(0)))) {
      paramString.deleteCharAt(0);
    }
    return paramString.toString();
  }
  
  public static String trimTrailingCharacter(String paramString, char paramChar)
  {
    if (!hasLength(paramString)) {
      return paramString;
    }
    paramString = new StringBuilder(paramString);
    while ((paramString.length() > 0) && (paramString.charAt(paramString.length() - 1) == paramChar)) {
      paramString.deleteCharAt(paramString.length() - 1);
    }
    return paramString.toString();
  }
  
  public static String trimTrailingWhitespace(String paramString)
  {
    if (!hasLength(paramString)) {
      return paramString;
    }
    paramString = new StringBuilder(paramString);
    while ((paramString.length() > 0) && (Character.isWhitespace(paramString.charAt(paramString.length() - 1)))) {
      paramString.deleteCharAt(paramString.length() - 1);
    }
    return paramString.toString();
  }
  
  public static String trimWhitespace(String paramString)
  {
    if (!hasLength(paramString)) {
      return paramString;
    }
    paramString = new StringBuilder(paramString);
    while ((paramString.length() > 0) && (Character.isWhitespace(paramString.charAt(0)))) {
      paramString.deleteCharAt(0);
    }
    while ((paramString.length() > 0) && (Character.isWhitespace(paramString.charAt(paramString.length() - 1)))) {
      paramString.deleteCharAt(paramString.length() - 1);
    }
    return paramString.toString();
  }
  
  public static String uncapitalize(String paramString)
  {
    return changeFirstCharacterCase(paramString, false);
  }
  
  public static String unqualify(String paramString)
  {
    return unqualify(paramString, '.');
  }
  
  public static String unqualify(String paramString, char paramChar)
  {
    return paramString.substring(paramString.lastIndexOf(paramChar) + 1);
  }
  
  private static void validateLocalePart(String paramString)
  {
    int i = 0;
    while (i < paramString.length())
    {
      char c = paramString.charAt(i);
      if ((c != '_') && (c != ' ') && (!Character.isLetterOrDigit(c))) {
        throw new IllegalArgumentException("Locale part \"" + paramString + "\" contains invalid characters");
      }
      i += 1;
    }
  }
}

/* Location:
 * Qualified Name:     org.springframework.util.StringUtils
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */