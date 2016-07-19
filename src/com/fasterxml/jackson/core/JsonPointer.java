package com.fasterxml.jackson.core;

import com.fasterxml.jackson.core.io.NumberInput;

public class JsonPointer
{
  protected static final JsonPointer EMPTY = new JsonPointer();
  protected final String _asString;
  protected final int _matchingElementIndex;
  protected final String _matchingPropertyName;
  protected final JsonPointer _nextSegment;
  
  protected JsonPointer()
  {
    _nextSegment = null;
    _matchingPropertyName = "";
    _matchingElementIndex = -1;
    _asString = "";
  }
  
  protected JsonPointer(String paramString1, String paramString2, JsonPointer paramJsonPointer)
  {
    _asString = paramString1;
    _nextSegment = paramJsonPointer;
    _matchingPropertyName = paramString2;
    _matchingElementIndex = _parseInt(paramString2);
  }
  
  private static void _appendEscape(StringBuilder paramStringBuilder, char paramChar)
  {
    if (paramChar == '0') {
      paramChar = '~';
    }
    for (;;)
    {
      paramStringBuilder.append(paramChar);
      return;
      if (paramChar == '1') {
        paramChar = '/';
      } else {
        paramStringBuilder.append('~');
      }
    }
  }
  
  private static final int _parseInt(String paramString)
  {
    int j = paramString.length();
    if (j == 0) {
      return -1;
    }
    for (int i = 0;; i = i + 1 + 1)
    {
      if (i >= j) {
        break label45;
      }
      int k = paramString.charAt(i);
      if ((k > 57) || (k < 48)) {
        break;
      }
    }
    label45:
    return NumberInput.parseInt(paramString);
  }
  
  protected static JsonPointer _parseQuotedTail(String paramString, int paramInt)
  {
    int i = paramString.length();
    StringBuilder localStringBuilder = new StringBuilder(Math.max(16, i));
    if (paramInt > 2) {
      localStringBuilder.append(paramString, 1, paramInt - 1);
    }
    _appendEscape(localStringBuilder, paramString.charAt(paramInt));
    paramInt += 1;
    while (paramInt < i)
    {
      char c = paramString.charAt(paramInt);
      if (c == '/') {
        return new JsonPointer(paramString, localStringBuilder.toString(), _parseTail(paramString.substring(paramInt)));
      }
      paramInt += 1;
      if ((c == '~') && (paramInt < i))
      {
        _appendEscape(localStringBuilder, paramString.charAt(paramInt));
        paramInt += 1;
      }
      else
      {
        localStringBuilder.append(c);
      }
    }
    return new JsonPointer(paramString, localStringBuilder.toString(), EMPTY);
  }
  
  protected static JsonPointer _parseTail(String paramString)
  {
    int j = paramString.length();
    int i = 1;
    while (i < j)
    {
      int k = paramString.charAt(i);
      if (k == 47) {
        return new JsonPointer(paramString, paramString.substring(1, i), _parseTail(paramString.substring(i)));
      }
      i += 1;
      if ((k == 126) && (i < j)) {
        return _parseQuotedTail(paramString, i);
      }
    }
    return new JsonPointer(paramString, paramString.substring(1), EMPTY);
  }
  
  public static JsonPointer compile(String paramString)
    throws IllegalArgumentException
  {
    if ((paramString == null) || (paramString.length() == 0)) {
      return EMPTY;
    }
    if (paramString.charAt(0) != '/') {
      throw new IllegalArgumentException("Invalid input: JSON Pointer expression must start with '/': \"" + paramString + "\"");
    }
    return _parseTail(paramString);
  }
  
  public static JsonPointer valueOf(String paramString)
  {
    return compile(paramString);
  }
  
  public boolean equals(Object paramObject)
  {
    boolean bool2 = false;
    boolean bool1;
    if (paramObject == this) {
      bool1 = true;
    }
    do
    {
      do
      {
        return bool1;
        bool1 = bool2;
      } while (paramObject == null);
      bool1 = bool2;
    } while (!(paramObject instanceof JsonPointer));
    return _asString.equals(_asString);
  }
  
  public int getMatchingIndex()
  {
    return _matchingElementIndex;
  }
  
  public String getMatchingProperty()
  {
    return _matchingPropertyName;
  }
  
  public int hashCode()
  {
    return _asString.hashCode();
  }
  
  public JsonPointer matchElement(int paramInt)
  {
    if ((paramInt != _matchingElementIndex) || (paramInt < 0)) {
      return null;
    }
    return _nextSegment;
  }
  
  public JsonPointer matchProperty(String paramString)
  {
    if ((_nextSegment == null) || (!_matchingPropertyName.equals(paramString))) {
      return null;
    }
    return _nextSegment;
  }
  
  public boolean matches()
  {
    return _nextSegment == null;
  }
  
  public boolean mayMatchElement()
  {
    return _matchingElementIndex >= 0;
  }
  
  public boolean mayMatchProperty()
  {
    return _matchingPropertyName != null;
  }
  
  public JsonPointer tail()
  {
    return _nextSegment;
  }
  
  public String toString()
  {
    return _asString;
  }
}

/* Location:
 * Qualified Name:     com.fasterxml.jackson.core.JsonPointer
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */