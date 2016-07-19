package com.fasterxml.jackson.core.base;

import com.fasterxml.jackson.core.Base64Variant;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonParser.Feature;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.JsonStreamContext;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.core.io.NumberInput;
import com.fasterxml.jackson.core.util.ByteArrayBuilder;
import com.fasterxml.jackson.core.util.VersionUtil;
import java.io.IOException;

public abstract class ParserMinimalBase
  extends JsonParser
{
  protected static final int INT_BACKSLASH = 92;
  protected static final int INT_COLON = 58;
  protected static final int INT_COMMA = 44;
  protected static final int INT_CR = 13;
  protected static final int INT_LBRACKET = 91;
  protected static final int INT_LCURLY = 123;
  protected static final int INT_LF = 10;
  protected static final int INT_QUOTE = 34;
  protected static final int INT_RBRACKET = 93;
  protected static final int INT_RCURLY = 125;
  protected static final int INT_SLASH = 47;
  protected static final int INT_SPACE = 32;
  protected static final int INT_TAB = 9;
  protected JsonToken _currToken;
  protected JsonToken _lastClearedToken;
  
  protected ParserMinimalBase() {}
  
  protected ParserMinimalBase(int paramInt)
  {
    super(paramInt);
  }
  
  protected static final String _getCharDesc(int paramInt)
  {
    char c = (char)paramInt;
    if (Character.isISOControl(c)) {
      return "(CTRL-CHAR, code " + paramInt + ")";
    }
    if (paramInt > 255) {
      return "'" + c + "' (code " + paramInt + " / 0x" + Integer.toHexString(paramInt) + ")";
    }
    return "'" + c + "' (code " + paramInt + ")";
  }
  
  protected final JsonParseException _constructError(String paramString, Throwable paramThrowable)
  {
    return new JsonParseException(paramString, getCurrentLocation(), paramThrowable);
  }
  
  protected void _decodeBase64(String paramString, ByteArrayBuilder paramByteArrayBuilder, Base64Variant paramBase64Variant)
    throws IOException, JsonParseException
  {
    try
    {
      paramBase64Variant.decode(paramString, paramByteArrayBuilder);
      return;
    }
    catch (IllegalArgumentException paramString)
    {
      _reportError(paramString.getMessage());
    }
  }
  
  protected abstract void _handleEOF()
    throws JsonParseException;
  
  protected char _handleUnrecognizedCharacterEscape(char paramChar)
    throws JsonProcessingException
  {
    if (isEnabled(JsonParser.Feature.ALLOW_BACKSLASH_ESCAPING_ANY_CHARACTER)) {}
    while ((paramChar == '\'') && (isEnabled(JsonParser.Feature.ALLOW_SINGLE_QUOTES))) {
      return paramChar;
    }
    _reportError("Unrecognized character escape " + _getCharDesc(paramChar));
    return paramChar;
  }
  
  protected boolean _hasTextualNull(String paramString)
  {
    return "null".equals(paramString);
  }
  
  @Deprecated
  protected void _reportBase64EOF()
    throws JsonParseException
  {
    throw _constructError("Unexpected end-of-String in base64 content");
  }
  
  protected final void _reportError(String paramString)
    throws JsonParseException
  {
    throw _constructError(paramString);
  }
  
  @Deprecated
  protected void _reportInvalidBase64(Base64Variant paramBase64Variant, char paramChar, int paramInt, String paramString)
    throws JsonParseException
  {
    if (paramChar <= ' ') {
      paramBase64Variant = "Illegal white space character (code 0x" + Integer.toHexString(paramChar) + ") as character #" + (paramInt + 1) + " of 4-char base64 unit: can only used between units";
    }
    for (;;)
    {
      Object localObject = paramBase64Variant;
      if (paramString != null) {
        localObject = paramBase64Variant + ": " + paramString;
      }
      throw _constructError((String)localObject);
      if (paramBase64Variant.usesPaddingChar(paramChar)) {
        paramBase64Variant = "Unexpected padding character ('" + paramBase64Variant.getPaddingChar() + "') as character #" + (paramInt + 1) + " of 4-char base64 unit: padding only legal as 3rd or 4th character";
      } else if ((!Character.isDefined(paramChar)) || (Character.isISOControl(paramChar))) {
        paramBase64Variant = "Illegal character (code 0x" + Integer.toHexString(paramChar) + ") in base64 content";
      } else {
        paramBase64Variant = "Illegal character '" + paramChar + "' (code 0x" + Integer.toHexString(paramChar) + ") in base64 content";
      }
    }
  }
  
  protected void _reportInvalidEOF()
    throws JsonParseException
  {
    _reportInvalidEOF(" in " + _currToken);
  }
  
  protected void _reportInvalidEOF(String paramString)
    throws JsonParseException
  {
    _reportError("Unexpected end-of-input" + paramString);
  }
  
  protected void _reportInvalidEOFInValue()
    throws JsonParseException
  {
    _reportInvalidEOF(" in a value");
  }
  
  protected void _reportMissingRootWS(int paramInt)
    throws JsonParseException
  {
    _reportUnexpectedChar(paramInt, "Expected space separating root-level values");
  }
  
  protected void _reportUnexpectedChar(int paramInt, String paramString)
    throws JsonParseException
  {
    if (paramInt < 0) {
      _reportInvalidEOF();
    }
    String str2 = "Unexpected character (" + _getCharDesc(paramInt) + ")";
    String str1 = str2;
    if (paramString != null) {
      str1 = str2 + ": " + paramString;
    }
    _reportError(str1);
  }
  
  protected final void _throwInternal() {}
  
  protected void _throwInvalidSpace(int paramInt)
    throws JsonParseException
  {
    paramInt = (char)paramInt;
    _reportError("Illegal character (" + _getCharDesc(paramInt) + "): only regular white space (\\r, \\n, \\t) is allowed between tokens");
  }
  
  protected void _throwUnquotedSpace(int paramInt, String paramString)
    throws JsonParseException
  {
    if ((!isEnabled(JsonParser.Feature.ALLOW_UNQUOTED_CONTROL_CHARS)) || (paramInt >= 32))
    {
      paramInt = (char)paramInt;
      _reportError("Illegal unquoted character (" + _getCharDesc(paramInt) + "): has to be escaped using backslash to be included in " + paramString);
    }
  }
  
  protected final void _wrapError(String paramString, Throwable paramThrowable)
    throws JsonParseException
  {
    throw _constructError(paramString, paramThrowable);
  }
  
  public void clearCurrentToken()
  {
    if (_currToken != null)
    {
      _lastClearedToken = _currToken;
      _currToken = null;
    }
  }
  
  public abstract void close()
    throws IOException;
  
  public abstract byte[] getBinaryValue(Base64Variant paramBase64Variant)
    throws IOException, JsonParseException;
  
  public abstract String getCurrentName()
    throws IOException, JsonParseException;
  
  public JsonToken getCurrentToken()
  {
    return _currToken;
  }
  
  public final int getCurrentTokenId()
  {
    JsonToken localJsonToken = _currToken;
    if (localJsonToken == null) {
      return 0;
    }
    return localJsonToken.id();
  }
  
  public JsonToken getLastClearedToken()
  {
    return _lastClearedToken;
  }
  
  public abstract JsonStreamContext getParsingContext();
  
  public abstract String getText()
    throws IOException, JsonParseException;
  
  public abstract char[] getTextCharacters()
    throws IOException, JsonParseException;
  
  public abstract int getTextLength()
    throws IOException, JsonParseException;
  
  public abstract int getTextOffset()
    throws IOException, JsonParseException;
  
  public boolean getValueAsBoolean(boolean paramBoolean)
    throws IOException, JsonParseException
  {
    boolean bool2 = true;
    Object localObject = _currToken;
    boolean bool1;
    if (localObject != null)
    {
      bool1 = bool2;
      switch (((JsonToken)localObject).id())
      {
      }
    }
    do
    {
      bool1 = paramBoolean;
      do
      {
        do
        {
          return bool1;
          localObject = getText().trim();
          bool1 = bool2;
        } while ("true".equals(localObject));
        if ("false".equals(localObject)) {
          return false;
        }
        if (!_hasTextualNull((String)localObject)) {
          break;
        }
        return false;
        bool1 = bool2;
      } while (getIntValue() != 0);
      return false;
      return false;
      localObject = getEmbeddedObject();
    } while (!(localObject instanceof Boolean));
    return ((Boolean)localObject).booleanValue();
  }
  
  public double getValueAsDouble(double paramDouble)
    throws IOException, JsonParseException
  {
    Object localObject = _currToken;
    if (localObject != null) {
      switch (((JsonToken)localObject).id())
      {
      }
    }
    do
    {
      return paramDouble;
      localObject = getText();
      if (_hasTextualNull((String)localObject)) {
        return 0.0D;
      }
      return NumberInput.parseAsDouble((String)localObject, paramDouble);
      return getDoubleValue();
      return 1.0D;
      return 0.0D;
      localObject = getEmbeddedObject();
    } while (!(localObject instanceof Number));
    return ((Number)localObject).doubleValue();
  }
  
  public int getValueAsInt(int paramInt)
    throws IOException, JsonParseException
  {
    Object localObject = _currToken;
    if (localObject != null) {
      switch (((JsonToken)localObject).id())
      {
      }
    }
    do
    {
      return paramInt;
      localObject = getText();
      if (_hasTextualNull((String)localObject)) {
        return 0;
      }
      return NumberInput.parseAsInt((String)localObject, paramInt);
      return getIntValue();
      return 1;
      return 0;
      return 0;
      localObject = getEmbeddedObject();
    } while (!(localObject instanceof Number));
    return ((Number)localObject).intValue();
  }
  
  public long getValueAsLong(long paramLong)
    throws IOException, JsonParseException
  {
    Object localObject = _currToken;
    if (localObject != null) {
      switch (((JsonToken)localObject).id())
      {
      }
    }
    do
    {
      return paramLong;
      localObject = getText();
      if (_hasTextualNull((String)localObject)) {
        return 0L;
      }
      return NumberInput.parseAsLong((String)localObject, paramLong);
      return getLongValue();
      return 1L;
      return 0L;
      localObject = getEmbeddedObject();
    } while (!(localObject instanceof Number));
    return ((Number)localObject).longValue();
  }
  
  public String getValueAsString(String paramString)
    throws IOException, JsonParseException
  {
    if ((_currToken != JsonToken.VALUE_STRING) && ((_currToken == null) || (_currToken == JsonToken.VALUE_NULL) || (!_currToken.isScalarValue()))) {
      return paramString;
    }
    return getText();
  }
  
  public boolean hasCurrentToken()
  {
    return _currToken != null;
  }
  
  public abstract boolean hasTextCharacters();
  
  public abstract boolean isClosed();
  
  public abstract JsonToken nextToken()
    throws IOException, JsonParseException;
  
  public JsonToken nextValue()
    throws IOException, JsonParseException
  {
    JsonToken localJsonToken2 = nextToken();
    JsonToken localJsonToken1 = localJsonToken2;
    if (localJsonToken2 == JsonToken.FIELD_NAME) {
      localJsonToken1 = nextToken();
    }
    return localJsonToken1;
  }
  
  public abstract void overrideCurrentName(String paramString);
  
  public JsonParser skipChildren()
    throws IOException, JsonParseException
  {
    if ((_currToken != JsonToken.START_OBJECT) && (_currToken != JsonToken.START_ARRAY)) {
      return this;
    }
    int i = 1;
    int j;
    do
    {
      JsonToken localJsonToken;
      do
      {
        for (;;)
        {
          localJsonToken = nextToken();
          if (localJsonToken == null)
          {
            _handleEOF();
            return this;
          }
          if (!localJsonToken.isStructStart()) {
            break;
          }
          i += 1;
        }
      } while (!localJsonToken.isStructEnd());
      j = i - 1;
      i = j;
    } while (j != 0);
    return this;
  }
  
  public Version version()
  {
    return VersionUtil.versionFor(getClass());
  }
}

/* Location:
 * Qualified Name:     com.fasterxml.jackson.core.base.ParserMinimalBase
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */