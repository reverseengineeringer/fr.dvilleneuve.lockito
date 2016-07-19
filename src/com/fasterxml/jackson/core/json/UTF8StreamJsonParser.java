package com.fasterxml.jackson.core.json;

import com.fasterxml.jackson.core.Base64Variant;
import com.fasterxml.jackson.core.JsonLocation;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser.Feature;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.core.SerializableString;
import com.fasterxml.jackson.core.base.ParserBase;
import com.fasterxml.jackson.core.io.CharTypes;
import com.fasterxml.jackson.core.io.IOContext;
import com.fasterxml.jackson.core.sym.BytesToNameCanonicalizer;
import com.fasterxml.jackson.core.sym.Name;
import com.fasterxml.jackson.core.util.ByteArrayBuilder;
import com.fasterxml.jackson.core.util.TextBuffer;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Arrays;

public class UTF8StreamJsonParser
  extends ParserBase
{
  static final byte BYTE_LF = 10;
  protected static final int[] _icLatin1 = CharTypes.getInputCodeLatin1();
  private static final int[] _icUTF8 = ;
  private static final int[] _icWS = CharTypes.getInputCodeWS();
  protected boolean _bufferRecyclable;
  protected byte[] _inputBuffer;
  protected InputStream _inputStream;
  protected ObjectCodec _objectCodec;
  private int _quad1;
  protected int[] _quadBuffer = new int[16];
  protected final BytesToNameCanonicalizer _symbols;
  protected boolean _tokenIncomplete = false;
  
  public UTF8StreamJsonParser(IOContext paramIOContext, int paramInt1, InputStream paramInputStream, ObjectCodec paramObjectCodec, BytesToNameCanonicalizer paramBytesToNameCanonicalizer, byte[] paramArrayOfByte, int paramInt2, int paramInt3, boolean paramBoolean)
  {
    super(paramIOContext, paramInt1);
    _inputStream = paramInputStream;
    _objectCodec = paramObjectCodec;
    _symbols = paramBytesToNameCanonicalizer;
    _inputBuffer = paramArrayOfByte;
    _inputPtr = paramInt2;
    _inputEnd = paramInt3;
    _currInputRowStart = paramInt2;
    _currInputProcessed = (-paramInt2);
    _bufferRecyclable = paramBoolean;
  }
  
  private final int _decodeUtf8_2(int paramInt)
    throws IOException
  {
    if (_inputPtr >= _inputEnd) {
      loadMoreGuaranteed();
    }
    byte[] arrayOfByte = _inputBuffer;
    int i = _inputPtr;
    _inputPtr = (i + 1);
    i = arrayOfByte[i];
    if ((i & 0xC0) != 128) {
      _reportInvalidOther(i & 0xFF, _inputPtr);
    }
    return i & 0x3F | (paramInt & 0x1F) << 6;
  }
  
  private final int _decodeUtf8_3(int paramInt)
    throws IOException
  {
    if (_inputPtr >= _inputEnd) {
      loadMoreGuaranteed();
    }
    byte[] arrayOfByte = _inputBuffer;
    int i = _inputPtr;
    _inputPtr = (i + 1);
    i = arrayOfByte[i];
    if ((i & 0xC0) != 128) {
      _reportInvalidOther(i & 0xFF, _inputPtr);
    }
    if (_inputPtr >= _inputEnd) {
      loadMoreGuaranteed();
    }
    arrayOfByte = _inputBuffer;
    int j = _inputPtr;
    _inputPtr = (j + 1);
    j = arrayOfByte[j];
    if ((j & 0xC0) != 128) {
      _reportInvalidOther(j & 0xFF, _inputPtr);
    }
    return ((paramInt & 0xF) << 6 | i & 0x3F) << 6 | j & 0x3F;
  }
  
  private final int _decodeUtf8_3fast(int paramInt)
    throws IOException
  {
    byte[] arrayOfByte = _inputBuffer;
    int i = _inputPtr;
    _inputPtr = (i + 1);
    i = arrayOfByte[i];
    if ((i & 0xC0) != 128) {
      _reportInvalidOther(i & 0xFF, _inputPtr);
    }
    arrayOfByte = _inputBuffer;
    int j = _inputPtr;
    _inputPtr = (j + 1);
    j = arrayOfByte[j];
    if ((j & 0xC0) != 128) {
      _reportInvalidOther(j & 0xFF, _inputPtr);
    }
    return ((paramInt & 0xF) << 6 | i & 0x3F) << 6 | j & 0x3F;
  }
  
  private final int _decodeUtf8_4(int paramInt)
    throws IOException
  {
    if (_inputPtr >= _inputEnd) {
      loadMoreGuaranteed();
    }
    byte[] arrayOfByte = _inputBuffer;
    int i = _inputPtr;
    _inputPtr = (i + 1);
    i = arrayOfByte[i];
    if ((i & 0xC0) != 128) {
      _reportInvalidOther(i & 0xFF, _inputPtr);
    }
    if (_inputPtr >= _inputEnd) {
      loadMoreGuaranteed();
    }
    arrayOfByte = _inputBuffer;
    int j = _inputPtr;
    _inputPtr = (j + 1);
    j = arrayOfByte[j];
    if ((j & 0xC0) != 128) {
      _reportInvalidOther(j & 0xFF, _inputPtr);
    }
    if (_inputPtr >= _inputEnd) {
      loadMoreGuaranteed();
    }
    arrayOfByte = _inputBuffer;
    int k = _inputPtr;
    _inputPtr = (k + 1);
    k = arrayOfByte[k];
    if ((k & 0xC0) != 128) {
      _reportInvalidOther(k & 0xFF, _inputPtr);
    }
    return (((i & 0x3F | (paramInt & 0x7) << 6) << 6 | j & 0x3F) << 6 | k & 0x3F) - 65536;
  }
  
  private final void _finishString2(char[] paramArrayOfChar, int paramInt)
    throws IOException
  {
    int[] arrayOfInt = _icUTF8;
    byte[] arrayOfByte = _inputBuffer;
    char[] arrayOfChar = paramArrayOfChar;
    int i;
    int j;
    int k;
    for (;;)
    {
      i = _inputPtr;
      j = i;
      if (i >= _inputEnd)
      {
        loadMoreGuaranteed();
        j = _inputPtr;
      }
      paramArrayOfChar = arrayOfChar;
      i = paramInt;
      if (paramInt >= arrayOfChar.length)
      {
        paramArrayOfChar = _textBuffer.finishCurrentSegment();
        i = 0;
      }
      k = Math.min(_inputEnd, paramArrayOfChar.length - i + j);
      while (j < k)
      {
        paramInt = j + 1;
        j = arrayOfByte[j] & 0xFF;
        if (arrayOfInt[j] != 0)
        {
          _inputPtr = paramInt;
          if (j != 34) {
            break label160;
          }
          _textBuffer.setCurrentLength(i);
          return;
        }
        paramArrayOfChar[i] = ((char)j);
        j = paramInt;
        i += 1;
      }
      _inputPtr = j;
      arrayOfChar = paramArrayOfChar;
      paramInt = i;
    }
    label160:
    switch (arrayOfInt[j])
    {
    default: 
      if (j < 32)
      {
        _throwUnquotedSpace(j, "string value");
        paramInt = j;
        label214:
        if (i < paramArrayOfChar.length) {
          break label379;
        }
        paramArrayOfChar = _textBuffer.finishCurrentSegment();
        i = 0;
      }
      break;
    }
    label379:
    for (;;)
    {
      j = i + 1;
      paramArrayOfChar[i] = ((char)paramInt);
      arrayOfChar = paramArrayOfChar;
      paramInt = j;
      break;
      paramInt = _decodeEscaped();
      break label214;
      paramInt = _decodeUtf8_2(j);
      break label214;
      if (_inputEnd - _inputPtr >= 2)
      {
        paramInt = _decodeUtf8_3fast(j);
        break label214;
      }
      paramInt = _decodeUtf8_3(j);
      break label214;
      k = _decodeUtf8_4(j);
      j = i + 1;
      paramArrayOfChar[i] = ((char)(0xD800 | k >> 10));
      paramInt = j;
      arrayOfChar = paramArrayOfChar;
      if (j >= paramArrayOfChar.length)
      {
        arrayOfChar = _textBuffer.finishCurrentSegment();
        paramInt = 0;
      }
      i = paramInt;
      paramInt = k & 0x3FF | 0xDC00;
      paramArrayOfChar = arrayOfChar;
      break label214;
      _reportInvalidChar(j);
      paramInt = j;
      break label214;
    }
  }
  
  private final boolean _isNextTokenNameMaybe(int paramInt, SerializableString paramSerializableString)
    throws IOException, JsonParseException
  {
    String str = _parseName(paramInt).getName();
    _parsingContext.setCurrentName(str);
    boolean bool = str.equals(paramSerializableString.getValue());
    _currToken = JsonToken.FIELD_NAME;
    paramInt = _skipWS();
    if (paramInt != 58) {
      _reportUnexpectedChar(paramInt, "was expecting a colon to separate field name and value");
    }
    paramInt = _skipWS();
    if (paramInt == 34)
    {
      _tokenIncomplete = true;
      _nextToken = JsonToken.VALUE_STRING;
      return bool;
    }
    switch (paramInt)
    {
    default: 
      paramSerializableString = _handleUnexpectedValue(paramInt);
    }
    for (;;)
    {
      _nextToken = paramSerializableString;
      return bool;
      paramSerializableString = JsonToken.START_ARRAY;
      continue;
      paramSerializableString = JsonToken.START_OBJECT;
      continue;
      _reportUnexpectedChar(paramInt, "expected a value");
      _matchToken("true", 1);
      paramSerializableString = JsonToken.VALUE_TRUE;
      continue;
      _matchToken("false", 1);
      paramSerializableString = JsonToken.VALUE_FALSE;
      continue;
      _matchToken("null", 1);
      paramSerializableString = JsonToken.VALUE_NULL;
      continue;
      paramSerializableString = _parseNumber(paramInt);
    }
  }
  
  private final void _isNextTokenNameYes()
    throws IOException, JsonParseException
  {
    if ((_inputPtr < _inputEnd - 1) && (_inputBuffer[_inputPtr] == 58))
    {
      byte[] arrayOfByte = _inputBuffer;
      i = _inputPtr + 1;
      _inputPtr = i;
      i = arrayOfByte[i];
      _inputPtr += 1;
      if (i == 34)
      {
        _tokenIncomplete = true;
        _nextToken = JsonToken.VALUE_STRING;
        return;
      }
      if (i == 123)
      {
        _nextToken = JsonToken.START_OBJECT;
        return;
      }
      if (i == 91)
      {
        _nextToken = JsonToken.START_ARRAY;
        return;
      }
      int j = i & 0xFF;
      if (j > 32)
      {
        i = j;
        if (j != 47) {}
      }
      else
      {
        _inputPtr -= 1;
      }
    }
    for (int i = _skipWS();; i = _skipColon()) {
      switch (i)
      {
      default: 
        _nextToken = _handleUnexpectedValue(i);
        return;
      }
    }
    _tokenIncomplete = true;
    _nextToken = JsonToken.VALUE_STRING;
    return;
    _nextToken = JsonToken.START_ARRAY;
    return;
    _nextToken = JsonToken.START_OBJECT;
    return;
    _reportUnexpectedChar(i, "expected a value");
    _matchToken("true", 1);
    _nextToken = JsonToken.VALUE_TRUE;
    return;
    _matchToken("false", 1);
    _nextToken = JsonToken.VALUE_FALSE;
    return;
    _matchToken("null", 1);
    _nextToken = JsonToken.VALUE_NULL;
    return;
    _nextToken = _parseNumber(i);
  }
  
  private final JsonToken _nextAfterName()
  {
    _nameCopied = false;
    JsonToken localJsonToken = _nextToken;
    _nextToken = null;
    if (localJsonToken == JsonToken.START_ARRAY) {
      _parsingContext = _parsingContext.createChildArrayContext(_tokenInputRow, _tokenInputCol);
    }
    for (;;)
    {
      _currToken = localJsonToken;
      return localJsonToken;
      if (localJsonToken == JsonToken.START_OBJECT) {
        _parsingContext = _parsingContext.createChildObjectContext(_tokenInputRow, _tokenInputCol);
      }
    }
  }
  
  private final JsonToken _nextTokenNotInObject(int paramInt)
    throws IOException, JsonParseException
  {
    if (paramInt == 34)
    {
      _tokenIncomplete = true;
      localJsonToken = JsonToken.VALUE_STRING;
      _currToken = localJsonToken;
      return localJsonToken;
    }
    switch (paramInt)
    {
    default: 
      localJsonToken = _handleUnexpectedValue(paramInt);
      _currToken = localJsonToken;
      return localJsonToken;
    case 91: 
      _parsingContext = _parsingContext.createChildArrayContext(_tokenInputRow, _tokenInputCol);
      localJsonToken = JsonToken.START_ARRAY;
      _currToken = localJsonToken;
      return localJsonToken;
    case 123: 
      _parsingContext = _parsingContext.createChildObjectContext(_tokenInputRow, _tokenInputCol);
      localJsonToken = JsonToken.START_OBJECT;
      _currToken = localJsonToken;
      return localJsonToken;
    case 93: 
    case 125: 
      _reportUnexpectedChar(paramInt, "expected a value");
    case 116: 
      _matchToken("true", 1);
      localJsonToken = JsonToken.VALUE_TRUE;
      _currToken = localJsonToken;
      return localJsonToken;
    case 102: 
      _matchToken("false", 1);
      localJsonToken = JsonToken.VALUE_FALSE;
      _currToken = localJsonToken;
      return localJsonToken;
    case 110: 
      _matchToken("null", 1);
      localJsonToken = JsonToken.VALUE_NULL;
      _currToken = localJsonToken;
      return localJsonToken;
    }
    JsonToken localJsonToken = _parseNumber(paramInt);
    _currToken = localJsonToken;
    return localJsonToken;
  }
  
  private final JsonToken _parseFloat(char[] paramArrayOfChar, int paramInt1, int paramInt2, boolean paramBoolean, int paramInt3)
    throws IOException, JsonParseException
  {
    int i = 0;
    int m = 0;
    int j = 0;
    int k;
    label49:
    Object localObject;
    if (paramInt2 == 46)
    {
      k = paramInt1 + 1;
      paramArrayOfChar[paramInt1] = ((char)paramInt2);
      paramInt1 = k;
      if ((_inputPtr >= _inputEnd) && (!loadMore()))
      {
        j = 1;
        if (i == 0) {
          reportUnexpectedNumberChar(paramInt2, "Decimal point not followed by a digit");
        }
        k = i;
        i = paramInt2;
        paramInt2 = j;
        localObject = paramArrayOfChar;
      }
    }
    for (;;)
    {
      m = 0;
      label204:
      label264:
      label351:
      int n;
      int i1;
      int i2;
      if ((i == 101) || (i == 69))
      {
        j = paramInt1;
        paramArrayOfChar = (char[])localObject;
        if (paramInt1 >= localObject.length)
        {
          paramArrayOfChar = _textBuffer.finishCurrentSegment();
          j = 0;
        }
        paramInt1 = j + 1;
        paramArrayOfChar[j] = ((char)i);
        if (_inputPtr >= _inputEnd) {
          loadMoreGuaranteed();
        }
        localObject = _inputBuffer;
        i = _inputPtr;
        _inputPtr = (i + 1);
        i = localObject[i] & 0xFF;
        if ((i == 45) || (i == 43)) {
          if (paramInt1 >= paramArrayOfChar.length)
          {
            paramArrayOfChar = _textBuffer.finishCurrentSegment();
            paramInt1 = 0;
            paramArrayOfChar[paramInt1] = ((char)i);
            if (_inputPtr >= _inputEnd) {
              loadMoreGuaranteed();
            }
            localObject = _inputBuffer;
            i = _inputPtr;
            _inputPtr = (i + 1);
            i = localObject[i] & 0xFF;
            paramInt1 += 1;
            j = m;
            if ((i <= 57) && (i >= 48))
            {
              j += 1;
              m = paramInt1;
              localObject = paramArrayOfChar;
              if (paramInt1 >= paramArrayOfChar.length)
              {
                localObject = _textBuffer.finishCurrentSegment();
                m = 0;
              }
              paramInt1 = m + 1;
              localObject[m] = ((char)i);
              if ((_inputPtr >= _inputEnd) && (!loadMore()))
              {
                m = j;
                paramInt2 = 1;
                j = paramInt1;
                paramInt1 = m;
                m = paramInt1;
                n = paramInt2;
                i1 = i;
                i2 = j;
                if (paramInt1 == 0)
                {
                  reportUnexpectedNumberChar(i, "Exponent indicator not followed by a digit");
                  i2 = j;
                  i1 = i;
                  n = paramInt2;
                  m = paramInt1;
                }
                label392:
                if (n == 0)
                {
                  _inputPtr -= 1;
                  if (_parsingContext.inRoot()) {
                    _verifyRootSpace(i1);
                  }
                }
                _textBuffer.setCurrentLength(i2);
                return resetFloat(paramBoolean, paramInt3, k, m);
                localObject = _inputBuffer;
                paramInt2 = _inputPtr;
                _inputPtr = (paramInt2 + 1);
                paramInt2 = localObject[paramInt2] & 0xFF;
                if (paramInt2 < 48) {
                  break label602;
                }
                if (paramInt2 > 57) {
                  break label49;
                }
                i += 1;
                if (paramInt1 < paramArrayOfChar.length) {
                  break label599;
                }
                paramArrayOfChar = _textBuffer.finishCurrentSegment();
                paramInt1 = 0;
              }
            }
          }
        }
      }
      label599:
      for (;;)
      {
        k = paramInt1 + 1;
        paramArrayOfChar[paramInt1] = ((char)paramInt2);
        paramInt1 = k;
        break;
        paramArrayOfChar = _inputBuffer;
        i = _inputPtr;
        _inputPtr = (i + 1);
        i = paramArrayOfChar[i] & 0xFF;
        paramArrayOfChar = (char[])localObject;
        break label264;
        m = paramInt1;
        paramInt1 = j;
        j = m;
        break label351;
        break label204;
        j = m;
        break label264;
        m = 0;
        n = paramInt2;
        i1 = i;
        i2 = paramInt1;
        break label392;
      }
      label602:
      break label49;
      k = 0;
      i = paramInt2;
      localObject = paramArrayOfChar;
      paramInt2 = m;
    }
  }
  
  private final JsonToken _parserNumber2(char[] paramArrayOfChar, int paramInt1, boolean paramBoolean, int paramInt2)
    throws IOException, JsonParseException
  {
    if ((_inputPtr >= _inputEnd) && (!loadMore()))
    {
      _textBuffer.setCurrentLength(paramInt1);
      return resetInt(paramBoolean, paramInt2);
    }
    byte[] arrayOfByte = _inputBuffer;
    int i = _inputPtr;
    _inputPtr = (i + 1);
    int j = arrayOfByte[i] & 0xFF;
    if ((j > 57) || (j < 48))
    {
      if ((j == 46) || (j == 101) || (j == 69)) {
        return _parseFloat(paramArrayOfChar, paramInt1, j, paramBoolean, paramInt2);
      }
    }
    else
    {
      if (paramInt1 < paramArrayOfChar.length) {
        break label215;
      }
      paramArrayOfChar = _textBuffer.finishCurrentSegment();
      paramInt1 = 0;
    }
    label215:
    for (;;)
    {
      i = paramInt1 + 1;
      paramArrayOfChar[paramInt1] = ((char)j);
      paramInt2 += 1;
      paramInt1 = i;
      break;
      _inputPtr -= 1;
      _textBuffer.setCurrentLength(paramInt1);
      if (_parsingContext.inRoot())
      {
        paramArrayOfChar = _inputBuffer;
        paramInt1 = _inputPtr;
        _inputPtr = (paramInt1 + 1);
        _verifyRootSpace(paramArrayOfChar[paramInt1] & 0xFF);
      }
      return resetInt(paramBoolean, paramInt2);
    }
  }
  
  private final void _skipCComment()
    throws IOException
  {
    int[] arrayOfInt = CharTypes.getInputCodeComment();
    for (;;)
    {
      int i;
      int j;
      if ((_inputPtr < _inputEnd) || (loadMore()))
      {
        byte[] arrayOfByte = _inputBuffer;
        i = _inputPtr;
        _inputPtr = (i + 1);
        i = arrayOfByte[i] & 0xFF;
        j = arrayOfInt[i];
        if (j == 0) {}
      }
      else
      {
        switch (j)
        {
        default: 
          _reportInvalidChar(i);
          break;
        case 42: 
          if ((_inputPtr >= _inputEnd) && (!loadMore()))
          {
            _reportInvalidEOF(" in a comment");
            return;
          }
          if (_inputBuffer[_inputPtr] == 47)
          {
            _inputPtr += 1;
            return;
          }
          break;
        case 10: 
          _currInputRow += 1;
          _currInputRowStart = _inputPtr;
          break;
        case 13: 
          _skipCR();
          break;
        case 2: 
          _skipUtf8_2(i);
          break;
        case 3: 
          _skipUtf8_3(i);
          break;
        case 4: 
          _skipUtf8_4(i);
        }
      }
    }
  }
  
  private final int _skipColon()
    throws IOException
  {
    if (_inputPtr >= _inputEnd) {
      loadMoreGuaranteed();
    }
    byte[] arrayOfByte = _inputBuffer;
    int i = _inputPtr;
    _inputPtr = (i + 1);
    i = arrayOfByte[i];
    if (i == 58)
    {
      if (_inputPtr < _inputEnd)
      {
        i = _inputBuffer[_inputPtr] & 0xFF;
        if ((i > 32) && (i != 47))
        {
          _inputPtr += 1;
          return i;
        }
      }
    }
    else
    {
      i &= 0xFF;
      switch (i)
      {
      default: 
        if (i < 32) {
          _throwInvalidSpace(i);
        }
        if (i != 58) {
          _reportUnexpectedChar(i, "was expecting a colon to separate field name and value");
        }
        break;
      }
    }
    for (;;)
    {
      if ((_inputPtr >= _inputEnd) && (!loadMore())) {
        break label371;
      }
      arrayOfByte = _inputBuffer;
      i = _inputPtr;
      _inputPtr = (i + 1);
      int j = arrayOfByte[i] & 0xFF;
      if (j > 32)
      {
        i = j;
        if (j != 47) {
          break;
        }
        _skipComment();
        continue;
        _skipCR();
        for (;;)
        {
          if (_inputPtr >= _inputEnd) {
            loadMoreGuaranteed();
          }
          arrayOfByte = _inputBuffer;
          i = _inputPtr;
          _inputPtr = (i + 1);
          i = arrayOfByte[i] & 0xFF;
          break;
          _currInputRow += 1;
          _currInputRowStart = _inputPtr;
          continue;
          _skipComment();
        }
      }
      if (j != 32) {
        if (j == 10)
        {
          _currInputRow += 1;
          _currInputRowStart = _inputPtr;
        }
        else if (j == 13)
        {
          _skipCR();
        }
        else if (j != 9)
        {
          _throwInvalidSpace(j);
        }
      }
    }
    label371:
    throw _constructError("Unexpected end-of-input within/between " + _parsingContext.getTypeDesc() + " entries");
  }
  
  private final void _skipComment()
    throws IOException
  {
    if (!isEnabled(JsonParser.Feature.ALLOW_COMMENTS)) {
      _reportUnexpectedChar(47, "maybe a (non-standard) comment? (not recognized as one since Feature 'ALLOW_COMMENTS' not enabled for parser)");
    }
    if ((_inputPtr >= _inputEnd) && (!loadMore())) {
      _reportInvalidEOF(" in a comment");
    }
    byte[] arrayOfByte = _inputBuffer;
    int i = _inputPtr;
    _inputPtr = (i + 1);
    i = arrayOfByte[i] & 0xFF;
    if (i == 47)
    {
      _skipLine();
      return;
    }
    if (i == 42)
    {
      _skipCComment();
      return;
    }
    _reportUnexpectedChar(i, "was expecting either '*' or '/' for a comment");
  }
  
  private final void _skipLine()
    throws IOException
  {
    int[] arrayOfInt = CharTypes.getInputCodeComment();
    for (;;)
    {
      int i;
      int j;
      if ((_inputPtr < _inputEnd) || (loadMore()))
      {
        byte[] arrayOfByte = _inputBuffer;
        i = _inputPtr;
        _inputPtr = (i + 1);
        i = arrayOfByte[i] & 0xFF;
        j = arrayOfInt[i];
        if (j == 0) {}
      }
      else
      {
        switch (j)
        {
        case 42: 
        default: 
          if (j < 0) {
            _reportInvalidChar(i);
          }
          break;
        case 10: 
          _currInputRow += 1;
          _currInputRowStart = _inputPtr;
          return;
        case 13: 
          _skipCR();
          return;
        case 2: 
          _skipUtf8_2(i);
          break;
        case 3: 
          _skipUtf8_3(i);
          break;
        case 4: 
          _skipUtf8_4(i);
        }
      }
    }
  }
  
  private final void _skipUtf8_2(int paramInt)
    throws IOException
  {
    if (_inputPtr >= _inputEnd) {
      loadMoreGuaranteed();
    }
    byte[] arrayOfByte = _inputBuffer;
    paramInt = _inputPtr;
    _inputPtr = (paramInt + 1);
    paramInt = arrayOfByte[paramInt];
    if ((paramInt & 0xC0) != 128) {
      _reportInvalidOther(paramInt & 0xFF, _inputPtr);
    }
  }
  
  private final void _skipUtf8_3(int paramInt)
    throws IOException
  {
    if (_inputPtr >= _inputEnd) {
      loadMoreGuaranteed();
    }
    byte[] arrayOfByte = _inputBuffer;
    paramInt = _inputPtr;
    _inputPtr = (paramInt + 1);
    paramInt = arrayOfByte[paramInt];
    if ((paramInt & 0xC0) != 128) {
      _reportInvalidOther(paramInt & 0xFF, _inputPtr);
    }
    if (_inputPtr >= _inputEnd) {
      loadMoreGuaranteed();
    }
    arrayOfByte = _inputBuffer;
    paramInt = _inputPtr;
    _inputPtr = (paramInt + 1);
    paramInt = arrayOfByte[paramInt];
    if ((paramInt & 0xC0) != 128) {
      _reportInvalidOther(paramInt & 0xFF, _inputPtr);
    }
  }
  
  private final void _skipUtf8_4(int paramInt)
    throws IOException
  {
    if (_inputPtr >= _inputEnd) {
      loadMoreGuaranteed();
    }
    byte[] arrayOfByte = _inputBuffer;
    paramInt = _inputPtr;
    _inputPtr = (paramInt + 1);
    paramInt = arrayOfByte[paramInt];
    if ((paramInt & 0xC0) != 128) {
      _reportInvalidOther(paramInt & 0xFF, _inputPtr);
    }
    if (_inputPtr >= _inputEnd) {
      loadMoreGuaranteed();
    }
    arrayOfByte = _inputBuffer;
    paramInt = _inputPtr;
    _inputPtr = (paramInt + 1);
    paramInt = arrayOfByte[paramInt];
    if ((paramInt & 0xC0) != 128) {
      _reportInvalidOther(paramInt & 0xFF, _inputPtr);
    }
    if (_inputPtr >= _inputEnd) {
      loadMoreGuaranteed();
    }
    arrayOfByte = _inputBuffer;
    paramInt = _inputPtr;
    _inputPtr = (paramInt + 1);
    paramInt = arrayOfByte[paramInt];
    if ((paramInt & 0xC0) != 128) {
      _reportInvalidOther(paramInt & 0xFF, _inputPtr);
    }
  }
  
  private final int _skipWS()
    throws IOException
  {
    int[] arrayOfInt = _icWS;
    while ((_inputPtr < _inputEnd) || (loadMore()))
    {
      byte[] arrayOfByte = _inputBuffer;
      int i = _inputPtr;
      _inputPtr = (i + 1);
      i = arrayOfByte[i] & 0xFF;
      switch (arrayOfInt[i])
      {
      case 1: 
      default: 
        if (i < 32) {
          _throwInvalidSpace(i);
        }
        _reportInvalidChar(i);
        break;
      case 2: 
        _skipUtf8_2(i);
        break;
      case 3: 
        _skipUtf8_3(i);
        break;
      case 4: 
        _skipUtf8_4(i);
        break;
      case 10: 
        _currInputRow += 1;
        _currInputRowStart = _inputPtr;
        break;
      case 13: 
        _skipCR();
        break;
      case 47: 
        _skipComment();
        break;
      case 35: 
        if (_skipYAMLComment()) {}
        break;
      case 0: 
        return i;
      }
    }
    throw _constructError("Unexpected end-of-input within/between " + _parsingContext.getTypeDesc() + " entries");
  }
  
  private final int _skipWSOrEnd()
    throws IOException
  {
    int[] arrayOfInt = _icWS;
    while ((_inputPtr < _inputEnd) || (loadMore()))
    {
      byte[] arrayOfByte = _inputBuffer;
      int i = _inputPtr;
      _inputPtr = (i + 1);
      i = arrayOfByte[i] & 0xFF;
      switch (arrayOfInt[i])
      {
      case 1: 
      default: 
        _reportInvalidChar(i);
        break;
      case 2: 
        _skipUtf8_2(i);
        break;
      case 3: 
        _skipUtf8_3(i);
        break;
      case 4: 
        _skipUtf8_4(i);
        break;
      case 10: 
        _currInputRow += 1;
        _currInputRowStart = _inputPtr;
        break;
      case 13: 
        _skipCR();
        break;
      case 47: 
        _skipComment();
        break;
      case 35: 
        if (_skipYAMLComment()) {}
        break;
      case 0: 
        return i;
      }
    }
    _handleEOF();
    return -1;
  }
  
  private final boolean _skipYAMLComment()
    throws IOException
  {
    if (!isEnabled(JsonParser.Feature.ALLOW_YAML_COMMENTS)) {
      return false;
    }
    _skipLine();
    return true;
  }
  
  private final int _verifyNoLeadingZeroes()
    throws IOException, JsonParseException
  {
    if ((_inputPtr >= _inputEnd) && (!loadMore())) {}
    int j;
    do
    {
      for (int i = 48; (_inputPtr >= _inputEnd) && (!loadMore()); i = j) {
        do
        {
          return i;
          j = _inputBuffer[_inputPtr] & 0xFF;
          if ((j < 48) || (j > 57)) {
            return 48;
          }
          if (!isEnabled(JsonParser.Feature.ALLOW_NUMERIC_LEADING_ZEROS)) {
            reportInvalidNumber("Leading zeroes not allowed");
          }
          _inputPtr += 1;
          i = j;
        } while (j != 48);
      }
      j = _inputBuffer[_inputPtr] & 0xFF;
      if ((j < 48) || (j > 57)) {
        return 48;
      }
      _inputPtr += 1;
      i = j;
    } while (j == 48);
    return j;
  }
  
  private final void _verifyRootSpace(int paramInt)
    throws IOException
  {
    _inputPtr += 1;
    switch (paramInt)
    {
    default: 
      _reportMissingRootWS(paramInt);
    case 9: 
    case 32: 
      return;
    case 13: 
      _skipCR();
      return;
    }
    _currInputRow += 1;
    _currInputRowStart = _inputPtr;
  }
  
  private final Name addName(int[] paramArrayOfInt, int paramInt1, int paramInt2)
    throws JsonParseException
  {
    int i3 = (paramInt1 << 2) - 4 + paramInt2;
    int i2;
    Object localObject1;
    int m;
    int i;
    label49:
    int j;
    int k;
    int i1;
    int n;
    label120:
    Object localObject2;
    if (paramInt2 < 4)
    {
      i2 = paramArrayOfInt[(paramInt1 - 1)];
      paramArrayOfInt[(paramInt1 - 1)] = (i2 << (4 - paramInt2 << 3));
      localObject1 = _textBuffer.emptyAndGetCurrentSegment();
      m = 0;
      i = 0;
      if (i >= i3) {
        break label521;
      }
      j = paramArrayOfInt[(i >> 2)] >> (3 - (i & 0x3) << 3) & 0xFF;
      k = i + 1;
      i1 = j;
      n = k;
      if (j <= 127) {
        break label559;
      }
      if ((j & 0xE0) != 192) {
        break label456;
      }
      i = j & 0x1F;
      j = 1;
      if (k + j > i3) {
        _reportInvalidEOF(" in field name");
      }
      i1 = paramArrayOfInt[(k >> 2)] >> (3 - (k & 0x3) << 3);
      n = k + 1;
      if ((i1 & 0xC0) != 128) {
        _reportInvalidOther(i1);
      }
      i1 = i << 6 | i1 & 0x3F;
      k = i1;
      i = n;
      if (j > 1)
      {
        i = paramArrayOfInt[(n >> 2)] >> (3 - (n & 0x3) << 3);
        n += 1;
        if ((i & 0xC0) != 128) {
          _reportInvalidOther(i);
        }
        i1 = i1 << 6 | i & 0x3F;
        k = i1;
        i = n;
        if (j > 2)
        {
          k = paramArrayOfInt[(n >> 2)] >> (3 - (n & 0x3) << 3);
          i = n + 1;
          if ((k & 0xC0) != 128) {
            _reportInvalidOther(k & 0xFF);
          }
          k = i1 << 6 | k & 0x3F;
        }
      }
      i1 = k;
      n = i;
      if (j <= 2) {
        break label559;
      }
      j = k - 65536;
      localObject2 = localObject1;
      if (m >= localObject1.length) {
        localObject2 = _textBuffer.expandCurrentSegment();
      }
      localObject2[m] = ((char)(55296 + (j >> 10)));
      k = m + 1;
      localObject1 = localObject2;
      j = j & 0x3FF | 0xDC00;
    }
    for (;;)
    {
      localObject2 = localObject1;
      if (k >= localObject1.length) {
        localObject2 = _textBuffer.expandCurrentSegment();
      }
      m = k + 1;
      localObject2[k] = ((char)j);
      localObject1 = localObject2;
      break label49;
      i2 = 0;
      break;
      label456:
      if ((j & 0xF0) == 224)
      {
        i = j & 0xF;
        j = 2;
        break label120;
      }
      if ((j & 0xF8) == 240)
      {
        i = j & 0x7;
        j = 3;
        break label120;
      }
      _reportInvalidInitial(j);
      i = 1;
      j = 1;
      break label120;
      label521:
      localObject1 = new String((char[])localObject1, 0, m);
      if (paramInt2 < 4) {
        paramArrayOfInt[(paramInt1 - 1)] = i2;
      }
      return _symbols.addName((String)localObject1, paramArrayOfInt, paramInt1);
      label559:
      j = i1;
      i = n;
      k = m;
    }
  }
  
  private final Name findName(int paramInt1, int paramInt2)
    throws JsonParseException
  {
    Name localName = _symbols.findName(paramInt1);
    if (localName != null) {
      return localName;
    }
    _quadBuffer[0] = paramInt1;
    return addName(_quadBuffer, 1, paramInt2);
  }
  
  private final Name findName(int paramInt1, int paramInt2, int paramInt3)
    throws JsonParseException
  {
    Name localName = _symbols.findName(paramInt1, paramInt2);
    if (localName != null) {
      return localName;
    }
    _quadBuffer[0] = paramInt1;
    _quadBuffer[1] = paramInt2;
    return addName(_quadBuffer, 2, paramInt3);
  }
  
  private final Name findName(int[] paramArrayOfInt, int paramInt1, int paramInt2, int paramInt3)
    throws JsonParseException
  {
    int[] arrayOfInt = paramArrayOfInt;
    if (paramInt1 >= paramArrayOfInt.length)
    {
      arrayOfInt = growArrayBy(paramArrayOfInt, paramArrayOfInt.length);
      _quadBuffer = arrayOfInt;
    }
    int i = paramInt1 + 1;
    arrayOfInt[paramInt1] = paramInt2;
    Name localName = _symbols.findName(arrayOfInt, i);
    paramArrayOfInt = localName;
    if (localName == null) {
      paramArrayOfInt = addName(arrayOfInt, i, paramInt3);
    }
    return paramArrayOfInt;
  }
  
  public static int[] growArrayBy(int[] paramArrayOfInt, int paramInt)
  {
    if (paramArrayOfInt == null) {
      return new int[paramInt];
    }
    return Arrays.copyOf(paramArrayOfInt, paramArrayOfInt.length + paramInt);
  }
  
  private int nextByte()
    throws IOException
  {
    if (_inputPtr >= _inputEnd) {
      loadMoreGuaranteed();
    }
    byte[] arrayOfByte = _inputBuffer;
    int i = _inputPtr;
    _inputPtr = (i + 1);
    return arrayOfByte[i] & 0xFF;
  }
  
  private final Name parseName(int paramInt1, int paramInt2, int paramInt3)
    throws IOException
  {
    return parseEscapedName(_quadBuffer, 0, paramInt1, paramInt2, paramInt3);
  }
  
  private final Name parseName(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
    throws IOException
  {
    _quadBuffer[0] = paramInt1;
    return parseEscapedName(_quadBuffer, 1, paramInt2, paramInt3, paramInt4);
  }
  
  protected void _closeInput()
    throws IOException
  {
    if (_inputStream != null)
    {
      if ((_ioContext.isResourceManaged()) || (isEnabled(JsonParser.Feature.AUTO_CLOSE_SOURCE))) {
        _inputStream.close();
      }
      _inputStream = null;
    }
  }
  
  protected final byte[] _decodeBase64(Base64Variant paramBase64Variant)
    throws IOException
  {
    ByteArrayBuilder localByteArrayBuilder = _getByteArrayBuilder();
    for (;;)
    {
      if (_inputPtr >= _inputEnd) {
        loadMoreGuaranteed();
      }
      byte[] arrayOfByte = _inputBuffer;
      int i = _inputPtr;
      _inputPtr = (i + 1);
      int k = arrayOfByte[i] & 0xFF;
      if (k > 32)
      {
        int j = paramBase64Variant.decodeBase64Char(k);
        i = j;
        if (j < 0)
        {
          if (k == 34) {
            return localByteArrayBuilder.toByteArray();
          }
          i = _decodeBase64Escape(paramBase64Variant, k, 0);
          if (i < 0) {}
        }
        else
        {
          if (_inputPtr >= _inputEnd) {
            loadMoreGuaranteed();
          }
          arrayOfByte = _inputBuffer;
          j = _inputPtr;
          _inputPtr = (j + 1);
          int m = arrayOfByte[j] & 0xFF;
          k = paramBase64Variant.decodeBase64Char(m);
          j = k;
          if (k < 0) {
            j = _decodeBase64Escape(paramBase64Variant, m, 1);
          }
          m = j | i << 6;
          if (_inputPtr >= _inputEnd) {
            loadMoreGuaranteed();
          }
          arrayOfByte = _inputBuffer;
          i = _inputPtr;
          _inputPtr = (i + 1);
          int n = arrayOfByte[i] & 0xFF;
          j = paramBase64Variant.decodeBase64Char(n);
          k = j;
          if (j < 0)
          {
            i = j;
            if (j != -2)
            {
              if ((n == 34) && (!paramBase64Variant.usesPadding()))
              {
                localByteArrayBuilder.append(m >> 4);
                return localByteArrayBuilder.toByteArray();
              }
              i = _decodeBase64Escape(paramBase64Variant, n, 2);
            }
            k = i;
            if (i == -2)
            {
              if (_inputPtr >= _inputEnd) {
                loadMoreGuaranteed();
              }
              arrayOfByte = _inputBuffer;
              i = _inputPtr;
              _inputPtr = (i + 1);
              i = arrayOfByte[i] & 0xFF;
              if (!paramBase64Variant.usesPaddingChar(i)) {
                throw reportInvalidBase64Char(paramBase64Variant, i, 3, "expected padding character '" + paramBase64Variant.getPaddingChar() + "'");
              }
              localByteArrayBuilder.append(m >> 4);
              continue;
            }
          }
          m = m << 6 | k;
          if (_inputPtr >= _inputEnd) {
            loadMoreGuaranteed();
          }
          arrayOfByte = _inputBuffer;
          i = _inputPtr;
          _inputPtr = (i + 1);
          n = arrayOfByte[i] & 0xFF;
          j = paramBase64Variant.decodeBase64Char(n);
          k = j;
          if (j < 0)
          {
            i = j;
            if (j != -2)
            {
              if ((n == 34) && (!paramBase64Variant.usesPadding()))
              {
                localByteArrayBuilder.appendTwoBytes(m >> 2);
                return localByteArrayBuilder.toByteArray();
              }
              i = _decodeBase64Escape(paramBase64Variant, n, 3);
            }
            k = i;
            if (i == -2)
            {
              localByteArrayBuilder.appendTwoBytes(m >> 2);
              continue;
            }
          }
          localByteArrayBuilder.appendThreeBytes(k | m << 6);
        }
      }
    }
  }
  
  protected int _decodeCharForError(int paramInt)
    throws IOException
  {
    int j = paramInt;
    int i;
    if (paramInt < 0)
    {
      if ((paramInt & 0xE0) != 192) {
        break label145;
      }
      paramInt &= 0x1F;
      i = 1;
    }
    for (;;)
    {
      j = nextByte();
      if ((j & 0xC0) != 128) {
        _reportInvalidOther(j & 0xFF);
      }
      paramInt = paramInt << 6 | j & 0x3F;
      j = paramInt;
      if (i > 1)
      {
        j = nextByte();
        if ((j & 0xC0) != 128) {
          _reportInvalidOther(j & 0xFF);
        }
        paramInt = paramInt << 6 | j & 0x3F;
        j = paramInt;
        if (i > 2)
        {
          i = nextByte();
          if ((i & 0xC0) != 128) {
            _reportInvalidOther(i & 0xFF);
          }
          j = paramInt << 6 | i & 0x3F;
        }
      }
      return j;
      label145:
      if ((paramInt & 0xF0) == 224)
      {
        paramInt &= 0xF;
        i = 2;
      }
      else if ((paramInt & 0xF8) == 240)
      {
        paramInt &= 0x7;
        i = 3;
      }
      else
      {
        _reportInvalidInitial(paramInt & 0xFF);
        i = 1;
      }
    }
  }
  
  protected char _decodeEscaped()
    throws IOException
  {
    int i = 0;
    if ((_inputPtr >= _inputEnd) && (!loadMore())) {
      _reportInvalidEOF(" in character escape sequence");
    }
    byte[] arrayOfByte = _inputBuffer;
    int j = _inputPtr;
    _inputPtr = (j + 1);
    j = arrayOfByte[j];
    switch (j)
    {
    default: 
      return _handleUnrecognizedCharacterEscape((char)_decodeCharForError(j));
    case 98: 
      return '\b';
    case 116: 
      return '\t';
    case 110: 
      return '\n';
    case 102: 
      return '\f';
    case 114: 
      return '\r';
    case 34: 
    case 47: 
    case 92: 
      return (char)j;
    }
    j = 0;
    while (i < 4)
    {
      if ((_inputPtr >= _inputEnd) && (!loadMore())) {
        _reportInvalidEOF(" in character escape sequence");
      }
      arrayOfByte = _inputBuffer;
      int k = _inputPtr;
      _inputPtr = (k + 1);
      k = arrayOfByte[k];
      int m = CharTypes.charToHex(k);
      if (m < 0) {
        _reportUnexpectedChar(k, "expected a hex-digit for character escape sequence");
      }
      j = j << 4 | m;
      i += 1;
    }
    return (char)j;
  }
  
  protected void _finishString()
    throws IOException
  {
    int j = _inputPtr;
    int i = j;
    if (j >= _inputEnd)
    {
      loadMoreGuaranteed();
      i = _inputPtr;
    }
    char[] arrayOfChar = _textBuffer.emptyAndGetCurrentSegment();
    int[] arrayOfInt = _icUTF8;
    int k = Math.min(_inputEnd, arrayOfChar.length + i);
    byte[] arrayOfByte = _inputBuffer;
    j = 0;
    while (i < k)
    {
      int m = arrayOfByte[i] & 0xFF;
      if (arrayOfInt[m] != 0)
      {
        if (m != 34) {
          break;
        }
        _inputPtr = (i + 1);
        _textBuffer.setCurrentLength(j);
        return;
      }
      arrayOfChar[j] = ((char)m);
      j += 1;
      i += 1;
    }
    _inputPtr = i;
    _finishString2(arrayOfChar, j);
  }
  
  protected final String _getText2(JsonToken paramJsonToken)
  {
    if (paramJsonToken == null) {
      return null;
    }
    switch (paramJsonToken.id())
    {
    default: 
      return paramJsonToken.asString();
    case 5: 
      return _parsingContext.getCurrentName();
    }
    return _textBuffer.contentsAsString();
  }
  
  protected JsonToken _handleApos()
    throws IOException
  {
    Object localObject2 = _textBuffer.emptyAndGetCurrentSegment();
    int[] arrayOfInt = _icUTF8;
    byte[] arrayOfByte = _inputBuffer;
    int i = 0;
    if (_inputPtr >= _inputEnd) {
      loadMoreGuaranteed();
    }
    Object localObject1 = localObject2;
    int j = i;
    if (i >= localObject2.length)
    {
      localObject1 = _textBuffer.finishCurrentSegment();
      j = 0;
    }
    int k = _inputEnd;
    i = _inputPtr + (localObject1.length - j);
    if (i < k) {
      k = i;
    }
    for (;;)
    {
      localObject2 = localObject1;
      i = j;
      if (_inputPtr >= k) {
        break;
      }
      i = _inputPtr;
      _inputPtr = (i + 1);
      i = arrayOfByte[i] & 0xFF;
      if ((i == 39) || (arrayOfInt[i] != 0))
      {
        if (i == 39)
        {
          _textBuffer.setCurrentLength(j);
          return JsonToken.VALUE_STRING;
        }
      }
      else
      {
        localObject1[j] = ((char)i);
        j += 1;
        continue;
      }
      switch (arrayOfInt[i])
      {
      default: 
        if (i < 32) {
          _throwUnquotedSpace(i, "string value");
        }
        _reportInvalidChar(i);
      }
      for (;;)
      {
        if (j >= localObject1.length)
        {
          localObject1 = _textBuffer.finishCurrentSegment();
          j = 0;
          label232:
          k = j + 1;
          localObject1[j] = ((char)i);
          localObject2 = localObject1;
          i = k;
          break;
          if (i != 39)
          {
            i = _decodeEscaped();
            continue;
            i = _decodeUtf8_2(i);
            continue;
            if (_inputEnd - _inputPtr >= 2)
            {
              i = _decodeUtf8_3fast(i);
            }
            else
            {
              i = _decodeUtf8_3(i);
              continue;
              k = _decodeUtf8_4(i);
              i = j + 1;
              localObject1[j] = ((char)(0xD800 | k >> 10));
              if (i < localObject1.length) {
                break label360;
              }
              localObject1 = _textBuffer.finishCurrentSegment();
            }
          }
        }
      }
      label360:
      for (j = 0;; j = i)
      {
        i = 0xDC00 | k & 0x3FF;
        break;
        break label232;
      }
    }
  }
  
  protected JsonToken _handleInvalidNumberStart(int paramInt, boolean paramBoolean)
    throws IOException
  {
    for (;;)
    {
      i = paramInt;
      if (paramInt != 73) {
        break label180;
      }
      if ((_inputPtr >= _inputEnd) && (!loadMore())) {
        _reportInvalidEOFInValue();
      }
      Object localObject = _inputBuffer;
      paramInt = _inputPtr;
      _inputPtr = (paramInt + 1);
      paramInt = localObject[paramInt];
      if (paramInt == 78) {
        if (paramBoolean)
        {
          localObject = "-INF";
          _matchToken((String)localObject, 3);
          if (!isEnabled(JsonParser.Feature.ALLOW_NON_NUMERIC_NUMBERS)) {
            break label143;
          }
          if (!paramBoolean) {
            break label136;
          }
        }
      }
      label136:
      for (double d = Double.NEGATIVE_INFINITY;; d = Double.POSITIVE_INFINITY)
      {
        return resetAsNaN((String)localObject, d);
        localObject = "+INF";
        break;
        if (paramInt != 110) {
          break label177;
        }
        if (paramBoolean)
        {
          localObject = "-Infinity";
          break;
        }
        localObject = "+Infinity";
        break;
      }
      label143:
      _reportError("Non-standard token '" + (String)localObject + "': enable JsonParser.Feature.ALLOW_NON_NUMERIC_NUMBERS to allow");
    }
    label177:
    int i = paramInt;
    label180:
    reportUnexpectedNumberChar(i, "expected digit (0-9) to follow minus sign, for valid numeric value");
    return null;
  }
  
  protected Name _handleOddName(int paramInt)
    throws IOException
  {
    if ((paramInt == 39) && (isEnabled(JsonParser.Feature.ALLOW_SINGLE_QUOTES))) {
      return _parseAposName();
    }
    if (!isEnabled(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES)) {
      _reportUnexpectedChar(paramInt, "was expecting double-quote to start field name");
    }
    int[] arrayOfInt = CharTypes.getInputCodeUtf8JsNames();
    if (arrayOfInt[paramInt] != 0) {
      _reportUnexpectedChar(paramInt, "was expecting either valid name character (for unquoted name) or double-quote (for quoted) to start field name");
    }
    Object localObject1 = _quadBuffer;
    int k = 0;
    int j = 0;
    int i = paramInt;
    paramInt = 0;
    for (;;)
    {
      if (k < 4) {
        j = i | j << 8;
      }
      int m;
      for (i = k + 1;; i = k)
      {
        if ((_inputPtr >= _inputEnd) && (!loadMore())) {
          _reportInvalidEOF(" in field name");
        }
        m = _inputBuffer[_inputPtr] & 0xFF;
        if (arrayOfInt[m] == 0) {
          break;
        }
        k = paramInt;
        Object localObject2 = localObject1;
        if (i > 0)
        {
          localObject2 = localObject1;
          if (paramInt >= localObject1.length)
          {
            localObject2 = growArrayBy((int[])localObject1, localObject1.length);
            _quadBuffer = ((int[])localObject2);
          }
          localObject2[paramInt] = j;
          k = paramInt + 1;
        }
        localObject1 = _symbols.findName((int[])localObject2, k);
        if (localObject1 != null) {
          break label286;
        }
        return addName((int[])localObject2, k, i);
        localObject2 = localObject1;
        if (paramInt >= localObject1.length)
        {
          localObject2 = growArrayBy((int[])localObject1, localObject1.length);
          _quadBuffer = ((int[])localObject2);
        }
        localObject2[paramInt] = j;
        localObject1 = localObject2;
        k = 1;
        j = i;
        paramInt += 1;
      }
      _inputPtr += 1;
      k = i;
      i = m;
    }
    label286:
    return (Name)localObject1;
  }
  
  protected JsonToken _handleUnexpectedValue(int paramInt)
    throws IOException
  {
    switch (paramInt)
    {
    default: 
    case 39: 
    case 78: 
    case 73: 
      for (;;)
      {
        if (Character.isJavaIdentifierStart(paramInt)) {
          _reportInvalidToken("" + (char)paramInt, "('true', 'false' or 'null')");
        }
        _reportUnexpectedChar(paramInt, "expected a valid value (number, String, array, object, 'true', 'false' or 'null')");
        return null;
        if (isEnabled(JsonParser.Feature.ALLOW_SINGLE_QUOTES))
        {
          return _handleApos();
          _matchToken("NaN", 1);
          if (isEnabled(JsonParser.Feature.ALLOW_NON_NUMERIC_NUMBERS)) {
            return resetAsNaN("NaN", NaN.0D);
          }
          _reportError("Non-standard token 'NaN': enable JsonParser.Feature.ALLOW_NON_NUMERIC_NUMBERS to allow");
          continue;
          _matchToken("Infinity", 1);
          if (isEnabled(JsonParser.Feature.ALLOW_NON_NUMERIC_NUMBERS)) {
            return resetAsNaN("Infinity", Double.POSITIVE_INFINITY);
          }
          _reportError("Non-standard token 'Infinity': enable JsonParser.Feature.ALLOW_NON_NUMERIC_NUMBERS to allow");
        }
      }
    }
    if ((_inputPtr >= _inputEnd) && (!loadMore())) {
      _reportInvalidEOFInValue();
    }
    byte[] arrayOfByte = _inputBuffer;
    paramInt = _inputPtr;
    _inputPtr = (paramInt + 1);
    return _handleInvalidNumberStart(arrayOfByte[paramInt] & 0xFF, false);
  }
  
  protected final boolean _loadToHaveAtLeast(int paramInt)
    throws IOException
  {
    if (_inputStream == null) {
      return false;
    }
    int i = _inputEnd - _inputPtr;
    if ((i > 0) && (_inputPtr > 0))
    {
      _currInputProcessed += _inputPtr;
      _currInputRowStart -= _inputPtr;
      System.arraycopy(_inputBuffer, _inputPtr, _inputBuffer, 0, i);
      _inputEnd = i;
      label79:
      _inputPtr = 0;
    }
    for (;;)
    {
      if (_inputEnd >= paramInt) {
        break label186;
      }
      int j = _inputStream.read(_inputBuffer, _inputEnd, _inputBuffer.length - _inputEnd);
      if (j < 1)
      {
        _closeInput();
        if (j != 0) {
          break;
        }
        throw new IOException("InputStream.read() returned 0 characters when trying to read " + i + " bytes");
        _inputEnd = 0;
        break label79;
      }
      _inputEnd = (j + _inputEnd);
    }
    label186:
    return true;
  }
  
  protected void _matchToken(String paramString, int paramInt)
    throws IOException
  {
    int j = paramString.length();
    int i;
    do
    {
      if (((_inputPtr >= _inputEnd) && (!loadMore())) || (_inputBuffer[_inputPtr] != paramString.charAt(paramInt))) {
        _reportInvalidToken(paramString.substring(0, paramInt));
      }
      _inputPtr += 1;
      i = paramInt + 1;
      paramInt = i;
    } while (i < j);
    if ((_inputPtr >= _inputEnd) && (!loadMore())) {}
    do
    {
      return;
      paramInt = _inputBuffer[_inputPtr] & 0xFF;
    } while ((paramInt < 48) || (paramInt == 93) || (paramInt == 125) || (!Character.isJavaIdentifierPart((char)_decodeCharForError(paramInt))));
    _reportInvalidToken(paramString.substring(0, i));
  }
  
  protected Name _parseAposName()
    throws IOException
  {
    if ((_inputPtr >= _inputEnd) && (!loadMore())) {
      _reportInvalidEOF(": was expecting closing ''' for name");
    }
    Object localObject1 = _inputBuffer;
    int i = _inputPtr;
    _inputPtr = (i + 1);
    int m = localObject1[i] & 0xFF;
    if (m == 39) {
      return BytesToNameCanonicalizer.getEmptyName();
    }
    localObject1 = _quadBuffer;
    int[] arrayOfInt = _icLatin1;
    int j = 0;
    int k = 0;
    i = 0;
    Object localObject2;
    int n;
    if (m == 39)
    {
      if (j <= 0) {
        break label557;
      }
      localObject2 = localObject1;
      if (i >= localObject1.length)
      {
        localObject2 = growArrayBy((int[])localObject1, localObject1.length);
        _quadBuffer = ((int[])localObject2);
      }
      localObject2[i] = k;
      localObject1 = localObject2;
      i += 1;
      localObject2 = _symbols.findName((int[])localObject1, i);
      if (localObject2 == null) {
        return addName((int[])localObject1, i, j);
      }
    }
    else
    {
      n = m;
      if (m == 34) {
        break label566;
      }
      n = m;
      if (arrayOfInt[m] == 0) {
        break label566;
      }
      if (m != 92)
      {
        _throwUnquotedSpace(m, "name");
        label197:
        n = m;
        if (m <= 127) {
          break label566;
        }
        if (j < 4) {
          break label563;
        }
        localObject2 = localObject1;
        if (i >= localObject1.length)
        {
          localObject2 = growArrayBy((int[])localObject1, localObject1.length);
          _quadBuffer = ((int[])localObject2);
        }
        localObject2[i] = k;
        j = 0;
        i += 1;
        k = 0;
        localObject1 = localObject2;
        label257:
        if (m >= 2048) {
          break label407;
        }
        k = k << 8 | m >> 6 | 0xC0;
        j += 1;
        label284:
        n = k;
        k = j;
        j = m & 0x3F | 0x80;
        m = k;
      }
    }
    for (;;)
    {
      if (m < 4)
      {
        k = j | n << 8;
        m += 1;
        j = i;
        i = m;
        label327:
        if ((_inputPtr >= _inputEnd) && (!loadMore())) {
          _reportInvalidEOF(" in field name");
        }
        localObject2 = _inputBuffer;
        m = _inputPtr;
        _inputPtr = (m + 1);
        n = localObject2[m] & 0xFF;
        m = i;
        i = j;
        j = m;
        m = n;
        break;
        m = _decodeEscaped();
        break label197;
        label407:
        k = k << 8 | m >> 12 | 0xE0;
        j += 1;
        if (j < 4) {
          break label560;
        }
        localObject2 = localObject1;
        if (i >= localObject1.length)
        {
          localObject2 = growArrayBy((int[])localObject1, localObject1.length);
          _quadBuffer = ((int[])localObject2);
        }
        localObject2[i] = k;
        i += 1;
        localObject1 = localObject2;
        j = 0;
        k = 0;
      }
      label557:
      label560:
      for (;;)
      {
        k = k << 8 | m >> 6 & 0x3F | 0x80;
        j += 1;
        break label284;
        localObject2 = localObject1;
        if (i >= localObject1.length)
        {
          localObject2 = growArrayBy((int[])localObject1, localObject1.length);
          _quadBuffer = ((int[])localObject2);
        }
        localObject2[i] = n;
        localObject1 = localObject2;
        k = 1;
        m = i + 1;
        i = k;
        k = j;
        j = m;
        break label327;
        return (Name)localObject2;
        break;
      }
      label563:
      break label257;
      label566:
      m = j;
      j = n;
      n = k;
    }
  }
  
  protected Name _parseName(int paramInt)
    throws IOException
  {
    if (paramInt != 34) {
      return _handleOddName(paramInt);
    }
    if (_inputPtr + 9 > _inputEnd) {
      return slowParseName();
    }
    byte[] arrayOfByte = _inputBuffer;
    int[] arrayOfInt = _icLatin1;
    paramInt = _inputPtr;
    _inputPtr = (paramInt + 1);
    paramInt = arrayOfByte[paramInt] & 0xFF;
    if (arrayOfInt[paramInt] == 0)
    {
      int i = _inputPtr;
      _inputPtr = (i + 1);
      i = arrayOfByte[i] & 0xFF;
      if (arrayOfInt[i] == 0)
      {
        paramInt = paramInt << 8 | i;
        i = _inputPtr;
        _inputPtr = (i + 1);
        i = arrayOfByte[i] & 0xFF;
        if (arrayOfInt[i] == 0)
        {
          paramInt = paramInt << 8 | i;
          i = _inputPtr;
          _inputPtr = (i + 1);
          i = arrayOfByte[i] & 0xFF;
          if (arrayOfInt[i] == 0)
          {
            paramInt = paramInt << 8 | i;
            i = _inputPtr;
            _inputPtr = (i + 1);
            i = arrayOfByte[i] & 0xFF;
            if (arrayOfInt[i] == 0)
            {
              _quad1 = paramInt;
              return parseMediumName(i, arrayOfInt);
            }
            if (i == 34) {
              return findName(paramInt, 4);
            }
            return parseName(paramInt, i, 4);
          }
          if (i == 34) {
            return findName(paramInt, 3);
          }
          return parseName(paramInt, i, 3);
        }
        if (i == 34) {
          return findName(paramInt, 2);
        }
        return parseName(paramInt, i, 2);
      }
      if (i == 34) {
        return findName(paramInt, 1);
      }
      return parseName(paramInt, i, 1);
    }
    if (paramInt == 34) {
      return BytesToNameCanonicalizer.getEmptyName();
    }
    return parseName(0, paramInt, 0);
  }
  
  protected JsonToken _parseNumber(int paramInt)
    throws IOException, JsonParseException
  {
    int k = 1;
    Object localObject2 = _textBuffer.emptyAndGetCurrentSegment();
    if (paramInt == 45) {}
    Object localObject1;
    for (boolean bool = true;; bool = false)
    {
      if (!bool) {
        break label355;
      }
      localObject2[0] = 45;
      if (_inputPtr >= _inputEnd) {
        loadMoreGuaranteed();
      }
      localObject1 = _inputBuffer;
      paramInt = _inputPtr;
      _inputPtr = (paramInt + 1);
      paramInt = localObject1[paramInt] & 0xFF;
      if ((paramInt >= 48) && (paramInt <= 57)) {
        break;
      }
      return _handleInvalidNumberStart(paramInt, true);
    }
    label352:
    label355:
    for (int i = 1;; i = 0)
    {
      int j = paramInt;
      if (paramInt == 48) {
        j = _verifyNoLeadingZeroes();
      }
      int m = i + 1;
      localObject2[i] = ((char)j);
      int n = _inputPtr + localObject2.length;
      i = n;
      localObject1 = localObject2;
      paramInt = m;
      j = k;
      if (n > _inputEnd)
      {
        i = _inputEnd;
        j = k;
        paramInt = m;
        localObject1 = localObject2;
      }
      if (_inputPtr >= i) {
        return _parserNumber2((char[])localObject1, paramInt, bool, j);
      }
      localObject2 = _inputBuffer;
      k = _inputPtr;
      _inputPtr = (k + 1);
      m = localObject2[k] & 0xFF;
      if ((m < 48) || (m > 57))
      {
        if ((m == 46) || (m == 101) || (m == 69)) {
          return _parseFloat((char[])localObject1, paramInt, m, bool, j);
        }
      }
      else
      {
        j += 1;
        if (paramInt < localObject1.length) {
          break label352;
        }
        localObject1 = _textBuffer.finishCurrentSegment();
        paramInt = 0;
      }
      for (;;)
      {
        k = paramInt + 1;
        localObject1[paramInt] = ((char)m);
        paramInt = k;
        break;
        _inputPtr -= 1;
        _textBuffer.setCurrentLength(paramInt);
        if (_parsingContext.inRoot()) {
          _verifyRootSpace(m);
        }
        return resetInt(bool, j);
      }
    }
  }
  
  protected int _readBinary(Base64Variant paramBase64Variant, OutputStream paramOutputStream, byte[] paramArrayOfByte)
    throws IOException, JsonParseException
  {
    int i1 = paramArrayOfByte.length;
    int j = 0;
    int i = 0;
    byte[] arrayOfByte;
    int k;
    int n;
    int m;
    label95:
    do
    {
      do
      {
        if (_inputPtr >= _inputEnd) {
          loadMoreGuaranteed();
        }
        arrayOfByte = _inputBuffer;
        k = _inputPtr;
        _inputPtr = (k + 1);
        n = arrayOfByte[k] & 0xFF;
      } while (n <= 32);
      k = paramBase64Variant.decodeBase64Char(n);
      m = k;
      if (k >= 0) {
        break;
      }
      if (n == 34)
      {
        k = i;
        i = j;
        _tokenIncomplete = false;
        j = i;
        if (k > 0)
        {
          j = i + k;
          paramOutputStream.write(paramArrayOfByte, 0, k);
        }
        return j;
      }
      m = _decodeBase64Escape(paramBase64Variant, n, 0);
    } while (m < 0);
    if (i > i1 - 3)
    {
      j += i;
      paramOutputStream.write(paramArrayOfByte, 0, i);
      k = 0;
    }
    for (i = j;; i = j)
    {
      if (_inputPtr >= _inputEnd) {
        loadMoreGuaranteed();
      }
      arrayOfByte = _inputBuffer;
      j = _inputPtr;
      _inputPtr = (j + 1);
      int i2 = arrayOfByte[j] & 0xFF;
      n = paramBase64Variant.decodeBase64Char(i2);
      j = n;
      if (n < 0) {
        j = _decodeBase64Escape(paramBase64Variant, i2, 1);
      }
      i2 = m << 6 | j;
      if (_inputPtr >= _inputEnd) {
        loadMoreGuaranteed();
      }
      arrayOfByte = _inputBuffer;
      j = _inputPtr;
      _inputPtr = (j + 1);
      int i3 = arrayOfByte[j] & 0xFF;
      m = paramBase64Variant.decodeBase64Char(i3);
      n = m;
      if (m < 0)
      {
        j = m;
        if (m != -2)
        {
          if ((i3 == 34) && (!paramBase64Variant.usesPadding()))
          {
            j = k + 1;
            paramArrayOfByte[k] = ((byte)(i2 >> 4));
            k = j;
            break label95;
          }
          j = _decodeBase64Escape(paramBase64Variant, i3, 2);
        }
        n = j;
        if (j == -2)
        {
          if (_inputPtr >= _inputEnd) {
            loadMoreGuaranteed();
          }
          arrayOfByte = _inputBuffer;
          j = _inputPtr;
          _inputPtr = (j + 1);
          j = arrayOfByte[j] & 0xFF;
          if (!paramBase64Variant.usesPaddingChar(j)) {
            throw reportInvalidBase64Char(paramBase64Variant, j, 3, "expected padding character '" + paramBase64Variant.getPaddingChar() + "'");
          }
          m = k + 1;
          paramArrayOfByte[k] = ((byte)(i2 >> 4));
          j = i;
          i = m;
          break;
        }
      }
      i2 = i2 << 6 | n;
      if (_inputPtr >= _inputEnd) {
        loadMoreGuaranteed();
      }
      arrayOfByte = _inputBuffer;
      j = _inputPtr;
      _inputPtr = (j + 1);
      i3 = arrayOfByte[j] & 0xFF;
      m = paramBase64Variant.decodeBase64Char(i3);
      n = m;
      if (m < 0)
      {
        j = m;
        if (m != -2)
        {
          if ((i3 == 34) && (!paramBase64Variant.usesPadding()))
          {
            j = i2 >> 2;
            m = k + 1;
            paramArrayOfByte[k] = ((byte)(j >> 8));
            k = m + 1;
            paramArrayOfByte[m] = ((byte)j);
            break label95;
          }
          j = _decodeBase64Escape(paramBase64Variant, i3, 3);
        }
        n = j;
        if (j == -2)
        {
          j = i2 >> 2;
          m = k + 1;
          paramArrayOfByte[k] = ((byte)(j >> 8));
          k = m + 1;
          paramArrayOfByte[m] = ((byte)j);
          j = i;
          i = k;
          break;
        }
      }
      j = i2 << 6 | n;
      m = k + 1;
      paramArrayOfByte[k] = ((byte)(j >> 16));
      n = m + 1;
      paramArrayOfByte[m] = ((byte)(j >> 8));
      k = n + 1;
      paramArrayOfByte[n] = ((byte)j);
      j = i;
      i = k;
      break;
      k = i;
    }
  }
  
  protected void _releaseBuffers()
    throws IOException
  {
    super._releaseBuffers();
    _symbols.release();
    if (_bufferRecyclable)
    {
      byte[] arrayOfByte = _inputBuffer;
      if (arrayOfByte != null)
      {
        _inputBuffer = null;
        _ioContext.releaseReadIOBuffer(arrayOfByte);
      }
    }
  }
  
  protected void _reportInvalidChar(int paramInt)
    throws JsonParseException
  {
    if (paramInt < 32) {
      _throwInvalidSpace(paramInt);
    }
    _reportInvalidInitial(paramInt);
  }
  
  protected void _reportInvalidInitial(int paramInt)
    throws JsonParseException
  {
    _reportError("Invalid UTF-8 start byte 0x" + Integer.toHexString(paramInt));
  }
  
  protected void _reportInvalidOther(int paramInt)
    throws JsonParseException
  {
    _reportError("Invalid UTF-8 middle byte 0x" + Integer.toHexString(paramInt));
  }
  
  protected void _reportInvalidOther(int paramInt1, int paramInt2)
    throws JsonParseException
  {
    _inputPtr = paramInt2;
    _reportInvalidOther(paramInt1);
  }
  
  protected void _reportInvalidToken(String paramString)
    throws IOException
  {
    _reportInvalidToken(paramString, "'null', 'true', 'false' or NaN");
  }
  
  protected void _reportInvalidToken(String paramString1, String paramString2)
    throws IOException
  {
    paramString1 = new StringBuilder(paramString1);
    for (;;)
    {
      if ((_inputPtr >= _inputEnd) && (!loadMore())) {}
      char c;
      do
      {
        _reportError("Unrecognized token '" + paramString1.toString() + "': was expecting " + paramString2);
        return;
        byte[] arrayOfByte = _inputBuffer;
        int i = _inputPtr;
        _inputPtr = (i + 1);
        c = (char)_decodeCharForError(arrayOfByte[i]);
      } while (!Character.isJavaIdentifierPart(c));
      paramString1.append(c);
    }
  }
  
  protected final void _skipCR()
    throws IOException
  {
    if (((_inputPtr < _inputEnd) || (loadMore())) && (_inputBuffer[_inputPtr] == 10)) {
      _inputPtr += 1;
    }
    _currInputRow += 1;
    _currInputRowStart = _inputPtr;
  }
  
  protected void _skipString()
    throws IOException
  {
    _tokenIncomplete = false;
    int[] arrayOfInt = _icUTF8;
    byte[] arrayOfByte = _inputBuffer;
    int k = _inputPtr;
    int m = _inputEnd;
    int j = m;
    int i = k;
    if (k >= m)
    {
      loadMoreGuaranteed();
      i = _inputPtr;
      j = _inputEnd;
    }
    for (;;)
    {
      if (i < j)
      {
        k = i + 1;
        i = arrayOfByte[i] & 0xFF;
        if (arrayOfInt[i] == 0) {
          break label188;
        }
        _inputPtr = k;
        if (i != 34) {}
      }
      else
      {
        _inputPtr = i;
        break;
      }
      switch (arrayOfInt[i])
      {
      default: 
        if (i < 32) {
          _throwUnquotedSpace(i, "string value");
        }
        break;
      case 1: 
        _decodeEscaped();
        break;
      case 2: 
        _skipUtf8_2(i);
        break;
      case 3: 
        _skipUtf8_3(i);
        break;
      case 4: 
        _skipUtf8_4(i);
        break;
        _reportInvalidChar(i);
        break;
      }
      label188:
      i = k;
    }
  }
  
  public byte[] getBinaryValue(Base64Variant paramBase64Variant)
    throws IOException, JsonParseException
  {
    if ((_currToken != JsonToken.VALUE_STRING) && ((_currToken != JsonToken.VALUE_EMBEDDED_OBJECT) || (_binaryValue == null))) {
      _reportError("Current token (" + _currToken + ") not VALUE_STRING or VALUE_EMBEDDED_OBJECT, can not access as binary");
    }
    if (_tokenIncomplete) {}
    for (;;)
    {
      try
      {
        _binaryValue = _decodeBase64(paramBase64Variant);
        _tokenIncomplete = false;
        return _binaryValue;
      }
      catch (IllegalArgumentException localIllegalArgumentException)
      {
        throw _constructError("Failed to decode VALUE_STRING as base64 (" + paramBase64Variant + "): " + localIllegalArgumentException.getMessage());
      }
      if (_binaryValue == null)
      {
        ByteArrayBuilder localByteArrayBuilder = _getByteArrayBuilder();
        _decodeBase64(getText(), localByteArrayBuilder, paramBase64Variant);
        _binaryValue = localByteArrayBuilder.toByteArray();
      }
    }
  }
  
  public ObjectCodec getCodec()
  {
    return _objectCodec;
  }
  
  public JsonLocation getCurrentLocation()
  {
    int i = _inputPtr;
    int j = _currInputRowStart;
    return new JsonLocation(_ioContext.getSourceReference(), _currInputProcessed + _inputPtr, -1L, _currInputRow, i - j + 1);
  }
  
  public Object getInputSource()
  {
    return _inputStream;
  }
  
  public String getText()
    throws IOException, JsonParseException
  {
    if (_currToken == JsonToken.VALUE_STRING)
    {
      if (_tokenIncomplete)
      {
        _tokenIncomplete = false;
        _finishString();
      }
      return _textBuffer.contentsAsString();
    }
    return _getText2(_currToken);
  }
  
  public char[] getTextCharacters()
    throws IOException, JsonParseException
  {
    if (_currToken != null)
    {
      switch (_currToken.id())
      {
      default: 
        return _currToken.asCharArray();
      case 5: 
        String str;
        int i;
        if (!_nameCopied)
        {
          str = _parsingContext.getCurrentName();
          i = str.length();
          if (_nameCopyBuffer != null) {
            break label112;
          }
          _nameCopyBuffer = _ioContext.allocNameCopyBuffer(i);
        }
        for (;;)
        {
          str.getChars(0, i, _nameCopyBuffer, 0);
          _nameCopied = true;
          return _nameCopyBuffer;
          if (_nameCopyBuffer.length < i) {
            _nameCopyBuffer = new char[i];
          }
        }
      case 6: 
        label112:
        if (_tokenIncomplete)
        {
          _tokenIncomplete = false;
          _finishString();
        }
        break;
      }
      return _textBuffer.getTextBuffer();
    }
    return null;
  }
  
  public int getTextLength()
    throws IOException, JsonParseException
  {
    int i = 0;
    if (_currToken != null) {}
    switch (_currToken.id())
    {
    default: 
      i = _currToken.asCharArray().length;
      return i;
    case 5: 
      return _parsingContext.getCurrentName().length();
    case 6: 
      if (_tokenIncomplete)
      {
        _tokenIncomplete = false;
        _finishString();
      }
      break;
    }
    return _textBuffer.size();
  }
  
  public int getTextOffset()
    throws IOException, JsonParseException
  {
    if (_currToken != null) {}
    switch (_currToken.id())
    {
    case 5: 
    default: 
      return 0;
    case 6: 
      if (_tokenIncomplete)
      {
        _tokenIncomplete = false;
        _finishString();
      }
      break;
    }
    return _textBuffer.getTextOffset();
  }
  
  public JsonLocation getTokenLocation()
  {
    return new JsonLocation(_ioContext.getSourceReference(), getTokenCharacterOffset(), -1L, getTokenLineNr(), getTokenColumnNr());
  }
  
  public String getValueAsString()
    throws IOException, JsonParseException
  {
    if (_currToken == JsonToken.VALUE_STRING)
    {
      if (_tokenIncomplete)
      {
        _tokenIncomplete = false;
        _finishString();
      }
      return _textBuffer.contentsAsString();
    }
    return super.getValueAsString(null);
  }
  
  public String getValueAsString(String paramString)
    throws IOException, JsonParseException
  {
    if (_currToken == JsonToken.VALUE_STRING)
    {
      if (_tokenIncomplete)
      {
        _tokenIncomplete = false;
        _finishString();
      }
      return _textBuffer.contentsAsString();
    }
    return super.getValueAsString(paramString);
  }
  
  protected final boolean loadMore()
    throws IOException
  {
    boolean bool2 = false;
    _currInputProcessed += _inputEnd;
    _currInputRowStart -= _inputEnd;
    boolean bool1 = bool2;
    int i;
    if (_inputStream != null)
    {
      i = _inputStream.read(_inputBuffer, 0, _inputBuffer.length);
      if (i <= 0) {
        break label74;
      }
      _inputPtr = 0;
      _inputEnd = i;
      bool1 = true;
    }
    label74:
    do
    {
      return bool1;
      _closeInput();
      bool1 = bool2;
    } while (i != 0);
    throw new IOException("InputStream.read() returned 0 characters when trying to read " + _inputBuffer.length + " bytes");
  }
  
  public Boolean nextBooleanValue()
    throws IOException, JsonParseException
  {
    Boolean localBoolean = null;
    if (_currToken == JsonToken.FIELD_NAME)
    {
      _nameCopied = false;
      JsonToken localJsonToken = _nextToken;
      _nextToken = null;
      _currToken = localJsonToken;
      if (localJsonToken == JsonToken.VALUE_TRUE) {
        localBoolean = Boolean.TRUE;
      }
      do
      {
        return localBoolean;
        if (localJsonToken == JsonToken.VALUE_FALSE) {
          return Boolean.FALSE;
        }
        if (localJsonToken == JsonToken.START_ARRAY)
        {
          _parsingContext = _parsingContext.createChildArrayContext(_tokenInputRow, _tokenInputCol);
          return null;
        }
      } while (localJsonToken != JsonToken.START_OBJECT);
      _parsingContext = _parsingContext.createChildObjectContext(_tokenInputRow, _tokenInputCol);
      return null;
    }
    switch (nextToken().id())
    {
    default: 
      return null;
    case 9: 
      return Boolean.TRUE;
    }
    return Boolean.FALSE;
  }
  
  public boolean nextFieldName(SerializableString paramSerializableString)
    throws IOException, JsonParseException
  {
    int j = 0;
    _numTypesValid = 0;
    if (_currToken == JsonToken.FIELD_NAME)
    {
      _nextAfterName();
      return false;
    }
    if (_tokenIncomplete) {
      _skipString();
    }
    int k = _skipWSOrEnd();
    if (k < 0)
    {
      close();
      _currToken = null;
      return false;
    }
    _tokenInputTotal = (_currInputProcessed + _inputPtr - 1L);
    _tokenInputRow = _currInputRow;
    _tokenInputCol = (_inputPtr - _currInputRowStart - 1);
    _binaryValue = null;
    if (k == 93)
    {
      if (!_parsingContext.inArray()) {
        _reportMismatchedEndMarker(k, '}');
      }
      _parsingContext = _parsingContext.getParent();
      _currToken = JsonToken.END_ARRAY;
      return false;
    }
    if (k == 125)
    {
      if (!_parsingContext.inObject()) {
        _reportMismatchedEndMarker(k, ']');
      }
      _parsingContext = _parsingContext.getParent();
      _currToken = JsonToken.END_OBJECT;
      return false;
    }
    int i = k;
    if (_parsingContext.expectComma())
    {
      if (k != 44) {
        _reportUnexpectedChar(k, "was expecting comma to separate " + _parsingContext.getTypeDesc() + " entries");
      }
      i = _skipWS();
    }
    if (!_parsingContext.inObject())
    {
      _nextTokenNotInObject(i);
      return false;
    }
    byte[] arrayOfByte;
    int m;
    int n;
    if (i == 34)
    {
      arrayOfByte = paramSerializableString.asQuotedUTF8();
      k = arrayOfByte.length;
      if (_inputPtr + k < _inputEnd)
      {
        m = _inputPtr + k;
        if (_inputBuffer[m] == 34) {
          n = _inputPtr;
        }
      }
    }
    for (;;)
    {
      if (j == k)
      {
        _inputPtr = (m + 1);
        _parsingContext.setCurrentName(paramSerializableString.getValue());
        _currToken = JsonToken.FIELD_NAME;
        _isNextTokenNameYes();
        return true;
      }
      if (arrayOfByte[j] != _inputBuffer[(n + j)]) {
        return _isNextTokenNameMaybe(i, paramSerializableString);
      }
      j += 1;
    }
  }
  
  public int nextIntValue(int paramInt)
    throws IOException, JsonParseException
  {
    JsonToken localJsonToken;
    int i;
    if (_currToken == JsonToken.FIELD_NAME)
    {
      _nameCopied = false;
      localJsonToken = _nextToken;
      _nextToken = null;
      _currToken = localJsonToken;
      if (localJsonToken == JsonToken.VALUE_NUMBER_INT) {
        i = getIntValue();
      }
    }
    do
    {
      do
      {
        return i;
        if (localJsonToken == JsonToken.START_ARRAY)
        {
          _parsingContext = _parsingContext.createChildArrayContext(_tokenInputRow, _tokenInputCol);
          return paramInt;
        }
        i = paramInt;
      } while (localJsonToken != JsonToken.START_OBJECT);
      _parsingContext = _parsingContext.createChildObjectContext(_tokenInputRow, _tokenInputCol);
      return paramInt;
      i = paramInt;
    } while (nextToken() != JsonToken.VALUE_NUMBER_INT);
    return getIntValue();
  }
  
  public long nextLongValue(long paramLong)
    throws IOException, JsonParseException
  {
    JsonToken localJsonToken;
    long l;
    if (_currToken == JsonToken.FIELD_NAME)
    {
      _nameCopied = false;
      localJsonToken = _nextToken;
      _nextToken = null;
      _currToken = localJsonToken;
      if (localJsonToken == JsonToken.VALUE_NUMBER_INT) {
        l = getLongValue();
      }
    }
    do
    {
      do
      {
        return l;
        if (localJsonToken == JsonToken.START_ARRAY)
        {
          _parsingContext = _parsingContext.createChildArrayContext(_tokenInputRow, _tokenInputCol);
          return paramLong;
        }
        l = paramLong;
      } while (localJsonToken != JsonToken.START_OBJECT);
      _parsingContext = _parsingContext.createChildObjectContext(_tokenInputRow, _tokenInputCol);
      return paramLong;
      l = paramLong;
    } while (nextToken() != JsonToken.VALUE_NUMBER_INT);
    return getLongValue();
  }
  
  public String nextTextValue()
    throws IOException, JsonParseException
  {
    String str = null;
    if (_currToken == JsonToken.FIELD_NAME)
    {
      _nameCopied = false;
      localJsonToken = _nextToken;
      _nextToken = null;
      _currToken = localJsonToken;
      if (localJsonToken == JsonToken.VALUE_STRING)
      {
        if (_tokenIncomplete)
        {
          _tokenIncomplete = false;
          _finishString();
        }
        str = _textBuffer.contentsAsString();
      }
    }
    while (nextToken() != JsonToken.VALUE_STRING)
    {
      JsonToken localJsonToken;
      do
      {
        return str;
        if (localJsonToken == JsonToken.START_ARRAY)
        {
          _parsingContext = _parsingContext.createChildArrayContext(_tokenInputRow, _tokenInputCol);
          return null;
        }
      } while (localJsonToken != JsonToken.START_OBJECT);
      _parsingContext = _parsingContext.createChildObjectContext(_tokenInputRow, _tokenInputCol);
      return null;
    }
    return getText();
  }
  
  public JsonToken nextToken()
    throws IOException, JsonParseException
  {
    _numTypesValid = 0;
    if (_currToken == JsonToken.FIELD_NAME) {
      return _nextAfterName();
    }
    if (_tokenIncomplete) {
      _skipString();
    }
    int j = _skipWSOrEnd();
    if (j < 0)
    {
      close();
      _currToken = null;
      return null;
    }
    _tokenInputTotal = (_currInputProcessed + _inputPtr - 1L);
    _tokenInputRow = _currInputRow;
    _tokenInputCol = (_inputPtr - _currInputRowStart - 1);
    _binaryValue = null;
    if (j == 93)
    {
      if (!_parsingContext.inArray()) {
        _reportMismatchedEndMarker(j, '}');
      }
      _parsingContext = _parsingContext.getParent();
      localObject = JsonToken.END_ARRAY;
      _currToken = ((JsonToken)localObject);
      return (JsonToken)localObject;
    }
    if (j == 125)
    {
      if (!_parsingContext.inObject()) {
        _reportMismatchedEndMarker(j, ']');
      }
      _parsingContext = _parsingContext.getParent();
      localObject = JsonToken.END_OBJECT;
      _currToken = ((JsonToken)localObject);
      return (JsonToken)localObject;
    }
    int i = j;
    if (_parsingContext.expectComma())
    {
      if (j != 44) {
        _reportUnexpectedChar(j, "was expecting comma to separate " + _parsingContext.getTypeDesc() + " entries");
      }
      i = _skipWS();
    }
    if (!_parsingContext.inObject()) {
      return _nextTokenNotInObject(i);
    }
    Object localObject = _parseName(i);
    _parsingContext.setCurrentName(((Name)localObject).getName());
    _currToken = JsonToken.FIELD_NAME;
    if ((_inputPtr < _inputEnd) && (_inputBuffer[_inputPtr] == 58)) {
      _inputPtr += 1;
    }
    for (;;)
    {
      i = _skipWS();
      if (i != 34) {
        break;
      }
      _tokenIncomplete = true;
      _nextToken = JsonToken.VALUE_STRING;
      return _currToken;
      i = _skipWS();
      if (i != 58) {
        _reportUnexpectedChar(i, "was expecting a colon to separate field name and value");
      }
    }
    switch (i)
    {
    default: 
      localObject = _handleUnexpectedValue(i);
    }
    for (;;)
    {
      _nextToken = ((JsonToken)localObject);
      return _currToken;
      localObject = JsonToken.START_ARRAY;
      continue;
      localObject = JsonToken.START_OBJECT;
      continue;
      _reportUnexpectedChar(i, "expected a value");
      _matchToken("true", 1);
      localObject = JsonToken.VALUE_TRUE;
      continue;
      _matchToken("false", 1);
      localObject = JsonToken.VALUE_FALSE;
      continue;
      _matchToken("null", 1);
      localObject = JsonToken.VALUE_NULL;
      continue;
      localObject = _parseNumber(i);
    }
  }
  
  protected Name parseEscapedName(int[] paramArrayOfInt, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
    throws IOException
  {
    Object localObject2 = _icLatin1;
    int i = paramInt3;
    Object localObject1;
    if (localObject2[paramInt3] != 0)
    {
      if (paramInt3 == 34)
      {
        localObject1 = paramArrayOfInt;
        paramInt3 = paramInt1;
        if (paramInt4 > 0)
        {
          localObject1 = paramArrayOfInt;
          if (paramInt1 >= paramArrayOfInt.length)
          {
            localObject1 = growArrayBy(paramArrayOfInt, paramArrayOfInt.length);
            _quadBuffer = ((int[])localObject1);
          }
          localObject1[paramInt1] = paramInt2;
          paramInt3 = paramInt1 + 1;
        }
        localObject2 = _symbols.findName((int[])localObject1, paramInt3);
        paramArrayOfInt = (int[])localObject2;
        if (localObject2 == null) {
          paramArrayOfInt = addName((int[])localObject1, paramInt3, paramInt4);
        }
        return paramArrayOfInt;
      }
      if (paramInt3 != 92)
      {
        _throwUnquotedSpace(paramInt3, "name");
        label118:
        i = paramInt3;
        if (paramInt3 <= 127) {
          break label485;
        }
        if (paramInt4 < 4) {
          break label482;
        }
        localObject1 = paramArrayOfInt;
        if (paramInt1 >= paramArrayOfInt.length)
        {
          localObject1 = growArrayBy(paramArrayOfInt, paramArrayOfInt.length);
          _quadBuffer = ((int[])localObject1);
        }
        i = paramInt1 + 1;
        localObject1[paramInt1] = paramInt2;
        paramInt4 = 0;
        paramInt2 = 0;
        paramArrayOfInt = (int[])localObject1;
        paramInt1 = i;
        label179:
        if (paramInt3 >= 2048) {
          break label333;
        }
        i = paramInt3 >> 6 | 0xC0 | paramInt2 << 8;
        paramInt4 += 1;
        paramInt2 = paramInt1;
        paramInt1 = i;
        int j = paramInt3 & 0x3F | 0x80;
        i = paramInt4;
        paramInt3 = paramInt2;
        paramInt4 = paramInt1;
        paramInt2 = j;
        paramInt1 = paramInt3;
        paramInt3 = paramInt4;
      }
    }
    for (;;)
    {
      if (i < 4)
      {
        paramInt4 = i + 1;
        paramInt2 |= paramInt3 << 8;
        label265:
        if ((_inputPtr >= _inputEnd) && (!loadMore())) {
          _reportInvalidEOF(" in field name");
        }
        localObject1 = _inputBuffer;
        paramInt3 = _inputPtr;
        _inputPtr = (paramInt3 + 1);
        paramInt3 = localObject1[paramInt3] & 0xFF;
        break;
        paramInt3 = _decodeEscaped();
        break label118;
        label333:
        i = paramInt3 >> 12 | 0xE0 | paramInt2 << 8;
        paramInt2 = paramInt4 + 1;
        if (paramInt2 < 4) {
          break label475;
        }
        localObject1 = paramArrayOfInt;
        if (paramInt1 >= paramArrayOfInt.length)
        {
          localObject1 = growArrayBy(paramArrayOfInt, paramArrayOfInt.length);
          _quadBuffer = ((int[])localObject1);
        }
        localObject1[paramInt1] = i;
        paramInt1 += 1;
        paramArrayOfInt = (int[])localObject1;
        paramInt2 = 0;
      }
      label475:
      for (paramInt4 = 0;; paramInt4 = i)
      {
        i = paramInt4 << 8 | paramInt3 >> 6 & 0x3F | 0x80;
        paramInt4 = paramInt2 + 1;
        paramInt2 = paramInt1;
        paramInt1 = i;
        break;
        localObject1 = paramArrayOfInt;
        if (paramInt1 >= paramArrayOfInt.length)
        {
          localObject1 = growArrayBy(paramArrayOfInt, paramArrayOfInt.length);
          _quadBuffer = ((int[])localObject1);
        }
        localObject1[paramInt1] = paramInt3;
        paramInt4 = 1;
        paramInt1 += 1;
        paramArrayOfInt = (int[])localObject1;
        break label265;
      }
      label482:
      break label179;
      label485:
      paramInt3 = paramInt2;
      paramInt2 = i;
      i = paramInt4;
    }
  }
  
  protected Name parseLongName(int paramInt)
    throws IOException
  {
    int[] arrayOfInt = _icLatin1;
    int j = 2;
    int i = paramInt;
    paramInt = j;
    for (;;)
    {
      if (_inputEnd - _inputPtr < 4) {
        return parseEscapedName(_quadBuffer, paramInt, 0, i, 0);
      }
      byte[] arrayOfByte = _inputBuffer;
      j = _inputPtr;
      _inputPtr = (j + 1);
      j = arrayOfByte[j] & 0xFF;
      if (arrayOfInt[j] != 0)
      {
        if (j == 34) {
          return findName(_quadBuffer, paramInt, i, 1);
        }
        return parseEscapedName(_quadBuffer, paramInt, i, j, 1);
      }
      i = i << 8 | j;
      arrayOfByte = _inputBuffer;
      j = _inputPtr;
      _inputPtr = (j + 1);
      j = arrayOfByte[j] & 0xFF;
      if (arrayOfInt[j] != 0)
      {
        if (j == 34) {
          return findName(_quadBuffer, paramInt, i, 2);
        }
        return parseEscapedName(_quadBuffer, paramInt, i, j, 2);
      }
      i = i << 8 | j;
      arrayOfByte = _inputBuffer;
      j = _inputPtr;
      _inputPtr = (j + 1);
      j = arrayOfByte[j] & 0xFF;
      if (arrayOfInt[j] != 0)
      {
        if (j == 34) {
          return findName(_quadBuffer, paramInt, i, 3);
        }
        return parseEscapedName(_quadBuffer, paramInt, i, j, 3);
      }
      j = i << 8 | j;
      arrayOfByte = _inputBuffer;
      i = _inputPtr;
      _inputPtr = (i + 1);
      i = arrayOfByte[i] & 0xFF;
      if (arrayOfInt[i] != 0)
      {
        if (i == 34) {
          return findName(_quadBuffer, paramInt, j, 4);
        }
        return parseEscapedName(_quadBuffer, paramInt, j, i, 4);
      }
      if (paramInt >= _quadBuffer.length) {
        _quadBuffer = growArrayBy(_quadBuffer, paramInt);
      }
      _quadBuffer[paramInt] = j;
      paramInt += 1;
    }
  }
  
  protected Name parseMediumName(int paramInt, int[] paramArrayOfInt)
    throws IOException
  {
    byte[] arrayOfByte = _inputBuffer;
    int i = _inputPtr;
    _inputPtr = (i + 1);
    i = arrayOfByte[i] & 0xFF;
    if (paramArrayOfInt[i] != 0)
    {
      if (i == 34) {
        return findName(_quad1, paramInt, 1);
      }
      return parseName(_quad1, paramInt, i, 1);
    }
    paramInt = i | paramInt << 8;
    arrayOfByte = _inputBuffer;
    i = _inputPtr;
    _inputPtr = (i + 1);
    i = arrayOfByte[i] & 0xFF;
    if (paramArrayOfInt[i] != 0)
    {
      if (i == 34) {
        return findName(_quad1, paramInt, 2);
      }
      return parseName(_quad1, paramInt, i, 2);
    }
    paramInt = paramInt << 8 | i;
    arrayOfByte = _inputBuffer;
    i = _inputPtr;
    _inputPtr = (i + 1);
    i = arrayOfByte[i] & 0xFF;
    if (paramArrayOfInt[i] != 0)
    {
      if (i == 34) {
        return findName(_quad1, paramInt, 3);
      }
      return parseName(_quad1, paramInt, i, 3);
    }
    paramInt = paramInt << 8 | i;
    arrayOfByte = _inputBuffer;
    i = _inputPtr;
    _inputPtr = (i + 1);
    i = arrayOfByte[i] & 0xFF;
    if (paramArrayOfInt[i] != 0)
    {
      if (i == 34) {
        return findName(_quad1, paramInt, 4);
      }
      return parseName(_quad1, paramInt, i, 4);
    }
    _quadBuffer[0] = _quad1;
    _quadBuffer[1] = paramInt;
    return parseLongName(i);
  }
  
  public int readBinaryValue(Base64Variant paramBase64Variant, OutputStream paramOutputStream)
    throws IOException, JsonParseException
  {
    if ((!_tokenIncomplete) || (_currToken != JsonToken.VALUE_STRING))
    {
      paramBase64Variant = getBinaryValue(paramBase64Variant);
      paramOutputStream.write(paramBase64Variant);
      return paramBase64Variant.length;
    }
    byte[] arrayOfByte = _ioContext.allocBase64Buffer();
    try
    {
      int i = _readBinary(paramBase64Variant, paramOutputStream, arrayOfByte);
      return i;
    }
    finally
    {
      _ioContext.releaseBase64Buffer(arrayOfByte);
    }
  }
  
  public int releaseBuffered(OutputStream paramOutputStream)
    throws IOException
  {
    int i = _inputEnd - _inputPtr;
    if (i < 1) {
      return 0;
    }
    int j = _inputPtr;
    paramOutputStream.write(_inputBuffer, j, i);
    return i;
  }
  
  public void setCodec(ObjectCodec paramObjectCodec)
  {
    _objectCodec = paramObjectCodec;
  }
  
  protected Name slowParseName()
    throws IOException
  {
    if ((_inputPtr >= _inputEnd) && (!loadMore())) {
      _reportInvalidEOF(": was expecting closing '\"' for name");
    }
    byte[] arrayOfByte = _inputBuffer;
    int i = _inputPtr;
    _inputPtr = (i + 1);
    i = arrayOfByte[i] & 0xFF;
    if (i == 34) {
      return BytesToNameCanonicalizer.getEmptyName();
    }
    return parseEscapedName(_quadBuffer, 0, 0, i, 0);
  }
}

/* Location:
 * Qualified Name:     com.fasterxml.jackson.core.json.UTF8StreamJsonParser
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */