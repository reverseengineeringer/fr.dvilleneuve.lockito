package com.fasterxml.jackson.core.json;

import com.fasterxml.jackson.core.Base64Variant;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser.Feature;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.core.base.ParserBase;
import com.fasterxml.jackson.core.io.CharTypes;
import com.fasterxml.jackson.core.io.IOContext;
import com.fasterxml.jackson.core.sym.CharsToNameCanonicalizer;
import com.fasterxml.jackson.core.util.ByteArrayBuilder;
import com.fasterxml.jackson.core.util.TextBuffer;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;

public final class ReaderBasedJsonParser
  extends ParserBase
{
  protected static final int[] _icLatin1 = ;
  private static final int[] _icWS = CharTypes.getInputCodeWS();
  protected final int _hashSeed;
  protected char[] _inputBuffer;
  protected ObjectCodec _objectCodec;
  protected Reader _reader;
  protected final CharsToNameCanonicalizer _symbols;
  protected boolean _tokenIncomplete = false;
  
  public ReaderBasedJsonParser(IOContext paramIOContext, int paramInt, Reader paramReader, ObjectCodec paramObjectCodec, CharsToNameCanonicalizer paramCharsToNameCanonicalizer)
  {
    super(paramIOContext, paramInt);
    _reader = paramReader;
    _inputBuffer = paramIOContext.allocTokenBuffer();
    _objectCodec = paramObjectCodec;
    _symbols = paramCharsToNameCanonicalizer;
    _hashSeed = paramCharsToNameCanonicalizer.hashSeed();
  }
  
  private String _handleOddName2(int paramInt1, int paramInt2, int[] paramArrayOfInt)
    throws IOException
  {
    _textBuffer.resetWithShared(_inputBuffer, paramInt1, _inputPtr - paramInt1);
    char[] arrayOfChar = _textBuffer.getCurrentSegment();
    paramInt1 = _textBuffer.getCurrentSegmentSize();
    int k = paramArrayOfInt.length;
    for (;;)
    {
      if ((_inputPtr >= _inputEnd) && (!loadMore())) {}
      int j;
      for (;;)
      {
        _textBuffer.setCurrentLength(paramInt1);
        paramArrayOfInt = _textBuffer;
        arrayOfChar = paramArrayOfInt.getTextBuffer();
        paramInt1 = paramArrayOfInt.getTextOffset();
        j = paramArrayOfInt.size();
        return _symbols.findSymbol(arrayOfChar, paramInt1, j, paramInt2);
        int i = _inputBuffer[_inputPtr];
        if (i <= k)
        {
          if (paramArrayOfInt[i] != 0) {}
        }
        else {
          while (Character.isJavaIdentifierPart(i))
          {
            _inputPtr += 1;
            paramInt2 = paramInt2 * 33 + i;
            j = paramInt1 + 1;
            arrayOfChar[paramInt1] = i;
            if (j < arrayOfChar.length) {
              break label188;
            }
            arrayOfChar = _textBuffer.finishCurrentSegment();
            paramInt1 = 0;
            break;
          }
        }
      }
      label188:
      paramInt1 = j;
    }
  }
  
  private JsonToken _nextAfterName()
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
  
  private String _parseName2(int paramInt1, int paramInt2, int paramInt3)
    throws IOException
  {
    _textBuffer.resetWithShared(_inputBuffer, paramInt1, _inputPtr - paramInt1);
    Object localObject = _textBuffer.getCurrentSegment();
    paramInt1 = _textBuffer.getCurrentSegmentSize();
    for (;;)
    {
      if ((_inputPtr >= _inputEnd) && (!loadMore())) {
        _reportInvalidEOF(": was expecting closing '" + (char)paramInt3 + "' for name");
      }
      char[] arrayOfChar = _inputBuffer;
      int k = _inputPtr;
      _inputPtr = (k + 1);
      int j = arrayOfChar[k];
      if (j <= 92) {
        if (j != 92) {}
      }
      for (int i = _decodeEscaped();; i = j)
      {
        paramInt2 = paramInt2 * 33 + j;
        k = paramInt1 + 1;
        localObject[paramInt1] = i;
        if (k < localObject.length) {
          break label250;
        }
        localObject = _textBuffer.finishCurrentSegment();
        paramInt1 = 0;
        break;
        if (j <= paramInt3)
        {
          if (j == paramInt3)
          {
            _textBuffer.setCurrentLength(paramInt1);
            localObject = _textBuffer;
            arrayOfChar = ((TextBuffer)localObject).getTextBuffer();
            paramInt1 = ((TextBuffer)localObject).getTextOffset();
            paramInt3 = ((TextBuffer)localObject).size();
            return _symbols.findSymbol(arrayOfChar, paramInt1, paramInt3, paramInt2);
          }
          if (j < 32) {
            _throwUnquotedSpace(j, "name");
          }
        }
      }
      label250:
      paramInt1 = k;
    }
  }
  
  private JsonToken _parseNumber2(boolean paramBoolean)
    throws IOException
  {
    int i3 = 0;
    Object localObject2 = _textBuffer.emptyAndGetCurrentSegment();
    if (paramBoolean) {
      localObject2[0] = 45;
    }
    for (int k = 1;; k = 0)
    {
      Object localObject1;
      int m;
      int i;
      label80:
      int n;
      int i1;
      if (_inputPtr < _inputEnd)
      {
        localObject1 = _inputBuffer;
        m = _inputPtr;
        _inputPtr = (m + 1);
        i = localObject1[m];
        int j = i;
        if (i == 48) {
          j = _verifyNoLeadingZeroes();
        }
        m = 0;
        i = j;
        if ((i < 48) || (i > 57)) {
          break label878;
        }
        m += 1;
        n = k;
        localObject1 = localObject2;
        if (k >= localObject2.length)
        {
          localObject1 = _textBuffer.finishCurrentSegment();
          n = 0;
        }
        k = n + 1;
        localObject1[n] = i;
        if ((_inputPtr < _inputEnd) || (loadMore())) {
          break label640;
        }
        n = 1;
        i = 0;
        i1 = m;
        m = k;
      }
      for (k = n;; k = n)
      {
        if (i1 == 0) {
          reportInvalidNumber("Missing integer part (next char " + _getCharDesc(i) + ")");
        }
        label251:
        int i2;
        if (i == 46)
        {
          localObject1[m] = i;
          m += 1;
          n = 0;
          if ((_inputPtr >= _inputEnd) && (!loadMore()))
          {
            k = 1;
            if (n == 0) {
              reportUnexpectedNumberChar(i, "Decimal point not followed by a digit");
            }
            i2 = n;
            n = m;
            m = k;
            localObject2 = localObject1;
            k = n;
          }
        }
        for (;;)
        {
          label372:
          label404:
          label447:
          label456:
          label547:
          int i4;
          if ((i == 101) || (i == 69))
          {
            n = k;
            localObject1 = localObject2;
            if (k >= localObject2.length)
            {
              localObject1 = _textBuffer.finishCurrentSegment();
              n = 0;
            }
            k = n + 1;
            localObject1[n] = i;
            if (_inputPtr < _inputEnd)
            {
              localObject2 = _inputBuffer;
              n = _inputPtr;
              _inputPtr = (n + 1);
              i = localObject2[n];
              if ((i != 45) && (i != 43)) {
                break label834;
              }
              if (k < localObject1.length) {
                break label831;
              }
              localObject1 = _textBuffer.finishCurrentSegment();
              k = 0;
              localObject1[k] = i;
              if (_inputPtr >= _inputEnd) {
                break label769;
              }
              localObject2 = _inputBuffer;
              n = _inputPtr;
              _inputPtr = (n + 1);
              i = localObject2[n];
              n = 0;
              k += 1;
              if ((i > 57) || (i < 48)) {
                break label812;
              }
              n += 1;
              i3 = k;
              localObject2 = localObject1;
              if (k >= localObject1.length)
              {
                localObject2 = _textBuffer.finishCurrentSegment();
                i3 = 0;
              }
              k = i3 + 1;
              localObject2[i3] = i;
              if ((_inputPtr < _inputEnd) || (loadMore())) {
                break label779;
              }
              m = n;
              i3 = 1;
              n = k;
              k = i3;
              if (m == 0) {
                reportUnexpectedNumberChar(i, "Exponent indicator not followed by a digit");
              }
              int i5 = i;
              i4 = n;
              i3 = m;
              n = i5;
              m = k;
              label578:
              if (m == 0)
              {
                _inputPtr -= 1;
                if (_parsingContext.inRoot()) {
                  _verifyRootSpace(n);
                }
              }
              _textBuffer.setCurrentLength(i4);
              return reset(paramBoolean, i1, i2, i3);
              i = getNextChar("No digit following minus sign");
              break;
              label640:
              localObject2 = _inputBuffer;
              n = _inputPtr;
              _inputPtr = (n + 1);
              i = localObject2[n];
              localObject2 = localObject1;
              break label80;
              localObject2 = _inputBuffer;
              i2 = _inputPtr;
              _inputPtr = (i2 + 1);
              i = localObject2[i2];
              if (i < 48) {
                break label853;
              }
              if (i > 57) {
                break label251;
              }
              n += 1;
              if (m < localObject1.length) {
                break label850;
              }
              localObject1 = _textBuffer.finishCurrentSegment();
              m = 0;
            }
          }
          label769:
          label779:
          label812:
          label831:
          label834:
          label850:
          for (;;)
          {
            i2 = m + 1;
            localObject1[m] = i;
            m = i2;
            break;
            i = getNextChar("expected a digit for number exponent");
            break label372;
            i = getNextChar("expected a digit for number exponent");
            break label447;
            localObject1 = _inputBuffer;
            i3 = _inputPtr;
            _inputPtr = (i3 + 1);
            i = localObject1[i3];
            localObject1 = localObject2;
            break label456;
            i3 = n;
            n = k;
            k = m;
            m = i3;
            break label547;
            break label404;
            n = 0;
            break label456;
            n = i;
            i4 = k;
            break label578;
          }
          label853:
          break label251;
          i2 = 0;
          n = k;
          k = m;
          localObject2 = localObject1;
          m = n;
        }
        label878:
        n = 0;
        i1 = m;
        localObject1 = localObject2;
        m = k;
      }
    }
  }
  
  private void _skipCComment()
    throws IOException
  {
    for (;;)
    {
      int i;
      if ((_inputPtr < _inputEnd) || (loadMore()))
      {
        char[] arrayOfChar = _inputBuffer;
        i = _inputPtr;
        _inputPtr = (i + 1);
        i = arrayOfChar[i];
        if (i > 42) {
          continue;
        }
        if (i != 42) {
          break label101;
        }
        if ((_inputPtr < _inputEnd) || (loadMore())) {}
      }
      else
      {
        _reportInvalidEOF(" in a comment");
        return;
      }
      if (_inputBuffer[_inputPtr] == '/')
      {
        _inputPtr += 1;
        return;
        label101:
        if (i < 32) {
          if (i == 10)
          {
            _currInputRow += 1;
            _currInputRowStart = _inputPtr;
          }
          else if (i == 13)
          {
            _skipCR();
          }
          else if (i != 9)
          {
            _throwInvalidSpace(i);
          }
        }
      }
    }
  }
  
  private void _skipComment()
    throws IOException
  {
    if (!isEnabled(JsonParser.Feature.ALLOW_COMMENTS)) {
      _reportUnexpectedChar(47, "maybe a (non-standard) comment? (not recognized as one since Feature 'ALLOW_COMMENTS' not enabled for parser)");
    }
    if ((_inputPtr >= _inputEnd) && (!loadMore())) {
      _reportInvalidEOF(" in a comment");
    }
    char[] arrayOfChar = _inputBuffer;
    int i = _inputPtr;
    _inputPtr = (i + 1);
    i = arrayOfChar[i];
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
  
  private void _skipLine()
    throws IOException
  {
    for (;;)
    {
      int i;
      if ((_inputPtr < _inputEnd) || (loadMore()))
      {
        char[] arrayOfChar = _inputBuffer;
        i = _inputPtr;
        _inputPtr = (i + 1);
        i = arrayOfChar[i];
        if (i >= 32) {
          continue;
        }
        if (i == 10)
        {
          _currInputRow += 1;
          _currInputRowStart = _inputPtr;
        }
      }
      else
      {
        return;
      }
      if (i == 13)
      {
        _skipCR();
        return;
      }
      if (i != 9) {
        _throwInvalidSpace(i);
      }
    }
  }
  
  private int _skipWS()
    throws IOException
  {
    int[] arrayOfInt = _icWS;
    while ((_inputPtr < _inputEnd) || (loadMore()))
    {
      char[] arrayOfChar = _inputBuffer;
      int i = _inputPtr;
      _inputPtr = (i + 1);
      i = arrayOfChar[i];
      if (i >= 64) {
        return i;
      }
      switch (arrayOfInt[i])
      {
      case 1: 
      case 0: 
      default: 
        break;
      case -1: 
        _throwInvalidSpace(i);
        return i;
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
        if (!_skipYAMLComment()) {
          return i;
        }
        break;
      }
    }
    throw _constructError("Unexpected end-of-input within/between " + _parsingContext.getTypeDesc() + " entries");
  }
  
  private int _skipWSOrEnd()
    throws IOException
  {
    int[] arrayOfInt = _icWS;
    while ((_inputPtr < _inputEnd) || (loadMore()))
    {
      char[] arrayOfChar = _inputBuffer;
      int i = _inputPtr;
      _inputPtr = (i + 1);
      i = arrayOfChar[i];
      if (i >= 64) {
        return i;
      }
      switch (arrayOfInt[i])
      {
      case 1: 
      case 0: 
      default: 
        break;
      case -1: 
        _throwInvalidSpace(i);
        return i;
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
        if (!_skipYAMLComment()) {
          return i;
        }
        break;
      }
    }
    _handleEOF();
    return -1;
  }
  
  private boolean _skipYAMLComment()
    throws IOException
  {
    if (!isEnabled(JsonParser.Feature.ALLOW_YAML_COMMENTS)) {
      return false;
    }
    _skipLine();
    return true;
  }
  
  private char _verifyNoLeadingZeroes()
    throws IOException
  {
    if ((_inputPtr >= _inputEnd) && (!loadMore())) {}
    char c2;
    do
    {
      for (char c1 = '0'; (_inputPtr >= _inputEnd) && (!loadMore()); c1 = c2) {
        do
        {
          return c1;
          c2 = _inputBuffer[_inputPtr];
          if ((c2 < '0') || (c2 > '9')) {
            return '0';
          }
          if (!isEnabled(JsonParser.Feature.ALLOW_NUMERIC_LEADING_ZEROS)) {
            reportInvalidNumber("Leading zeroes not allowed");
          }
          _inputPtr += 1;
          c1 = c2;
        } while (c2 != '0');
      }
      c2 = _inputBuffer[_inputPtr];
      if ((c2 < '0') || (c2 > '9')) {
        return '0';
      }
      _inputPtr += 1;
      c1 = c2;
    } while (c2 == '0');
    return c2;
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
  
  protected void _closeInput()
    throws IOException
  {
    if (_reader != null)
    {
      if ((_ioContext.isResourceManaged()) || (isEnabled(JsonParser.Feature.AUTO_CLOSE_SOURCE))) {
        _reader.close();
      }
      _reader = null;
    }
  }
  
  protected byte[] _decodeBase64(Base64Variant paramBase64Variant)
    throws IOException
  {
    ByteArrayBuilder localByteArrayBuilder = _getByteArrayBuilder();
    for (;;)
    {
      if (_inputPtr >= _inputEnd) {
        loadMoreGuaranteed();
      }
      char[] arrayOfChar = _inputBuffer;
      int i = _inputPtr;
      _inputPtr = (i + 1);
      char c = arrayOfChar[i];
      if (c > ' ')
      {
        int j = paramBase64Variant.decodeBase64Char(c);
        i = j;
        if (j < 0)
        {
          if (c == '"') {
            return localByteArrayBuilder.toByteArray();
          }
          i = _decodeBase64Escape(paramBase64Variant, c, 0);
          if (i < 0) {}
        }
        else
        {
          if (_inputPtr >= _inputEnd) {
            loadMoreGuaranteed();
          }
          arrayOfChar = _inputBuffer;
          j = _inputPtr;
          _inputPtr = (j + 1);
          c = arrayOfChar[j];
          int k = paramBase64Variant.decodeBase64Char(c);
          j = k;
          if (k < 0) {
            j = _decodeBase64Escape(paramBase64Variant, c, 1);
          }
          int m = j | i << 6;
          if (_inputPtr >= _inputEnd) {
            loadMoreGuaranteed();
          }
          arrayOfChar = _inputBuffer;
          i = _inputPtr;
          _inputPtr = (i + 1);
          c = arrayOfChar[i];
          j = paramBase64Variant.decodeBase64Char(c);
          k = j;
          if (j < 0)
          {
            i = j;
            if (j != -2)
            {
              if ((c == '"') && (!paramBase64Variant.usesPadding()))
              {
                localByteArrayBuilder.append(m >> 4);
                return localByteArrayBuilder.toByteArray();
              }
              i = _decodeBase64Escape(paramBase64Variant, c, 2);
            }
            k = i;
            if (i == -2)
            {
              if (_inputPtr >= _inputEnd) {
                loadMoreGuaranteed();
              }
              arrayOfChar = _inputBuffer;
              i = _inputPtr;
              _inputPtr = (i + 1);
              c = arrayOfChar[i];
              if (!paramBase64Variant.usesPaddingChar(c)) {
                throw reportInvalidBase64Char(paramBase64Variant, c, 3, "expected padding character '" + paramBase64Variant.getPaddingChar() + "'");
              }
              localByteArrayBuilder.append(m >> 4);
              continue;
            }
          }
          m = m << 6 | k;
          if (_inputPtr >= _inputEnd) {
            loadMoreGuaranteed();
          }
          arrayOfChar = _inputBuffer;
          i = _inputPtr;
          _inputPtr = (i + 1);
          c = arrayOfChar[i];
          j = paramBase64Variant.decodeBase64Char(c);
          k = j;
          if (j < 0)
          {
            i = j;
            if (j != -2)
            {
              if ((c == '"') && (!paramBase64Variant.usesPadding()))
              {
                localByteArrayBuilder.appendTwoBytes(m >> 2);
                return localByteArrayBuilder.toByteArray();
              }
              i = _decodeBase64Escape(paramBase64Variant, c, 3);
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
  
  protected char _decodeEscaped()
    throws IOException
  {
    int j = 0;
    if ((_inputPtr >= _inputEnd) && (!loadMore())) {
      _reportInvalidEOF(" in character escape sequence");
    }
    char[] arrayOfChar = _inputBuffer;
    int i = _inputPtr;
    _inputPtr = (i + 1);
    char c2 = arrayOfChar[i];
    char c1 = c2;
    switch (c2)
    {
    default: 
      c1 = _handleUnrecognizedCharacterEscape(c2);
    case '"': 
    case '/': 
    case '\\': 
      return c1;
    case 'b': 
      return '\b';
    case 't': 
      return '\t';
    case 'n': 
      return '\n';
    case 'f': 
      return '\f';
    case 'r': 
      return '\r';
    }
    i = 0;
    while (i < 4)
    {
      if ((_inputPtr >= _inputEnd) && (!loadMore())) {
        _reportInvalidEOF(" in character escape sequence");
      }
      arrayOfChar = _inputBuffer;
      int k = _inputPtr;
      _inputPtr = (k + 1);
      k = arrayOfChar[k];
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
    int i = _inputPtr;
    int k = _inputEnd;
    int j = i;
    if (i < k)
    {
      int[] arrayOfInt = _icLatin1;
      int m = arrayOfInt.length;
      do
      {
        int n = _inputBuffer[i];
        if ((n < m) && (arrayOfInt[n] != 0))
        {
          j = i;
          if (n != 34) {
            break;
          }
          _textBuffer.resetWithShared(_inputBuffer, _inputPtr, i - _inputPtr);
          _inputPtr = (i + 1);
          return;
        }
        j = i + 1;
        i = j;
      } while (j < k);
    }
    _textBuffer.resetWithCopy(_inputBuffer, _inputPtr, j - _inputPtr);
    _inputPtr = j;
    _finishString2();
  }
  
  protected void _finishString2()
    throws IOException
  {
    char[] arrayOfChar1 = _textBuffer.getCurrentSegment();
    int k = _textBuffer.getCurrentSegmentSize();
    if ((_inputPtr >= _inputEnd) && (!loadMore())) {
      _reportInvalidEOF(": was expecting closing quote for a string value");
    }
    char[] arrayOfChar2 = _inputBuffer;
    int m = _inputPtr;
    _inputPtr = (m + 1);
    int j = arrayOfChar2[m];
    int i = j;
    if (j <= 92)
    {
      if (j == 92) {
        i = _decodeEscaped();
      }
    }
    else
    {
      label87:
      if (k < arrayOfChar1.length) {
        break label165;
      }
      arrayOfChar1 = _textBuffer.finishCurrentSegment();
      k = 0;
    }
    label165:
    for (;;)
    {
      m = k + 1;
      arrayOfChar1[k] = i;
      k = m;
      break;
      i = j;
      if (j > 34) {
        break label87;
      }
      if (j == 34)
      {
        _textBuffer.setCurrentLength(k);
        return;
      }
      i = j;
      if (j >= 32) {
        break label87;
      }
      _throwUnquotedSpace(j, "string value");
      i = j;
      break label87;
    }
  }
  
  protected String _getText2(JsonToken paramJsonToken)
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
    char[] arrayOfChar1 = _textBuffer.emptyAndGetCurrentSegment();
    int k = _textBuffer.getCurrentSegmentSize();
    if ((_inputPtr >= _inputEnd) && (!loadMore())) {
      _reportInvalidEOF(": was expecting closing quote for a string value");
    }
    char[] arrayOfChar2 = _inputBuffer;
    int m = _inputPtr;
    _inputPtr = (m + 1);
    int j = arrayOfChar2[m];
    int i = j;
    if (j <= 92)
    {
      if (j == 92) {
        i = _decodeEscaped();
      }
    }
    else
    {
      label87:
      if (k < arrayOfChar1.length) {
        break label168;
      }
      arrayOfChar1 = _textBuffer.finishCurrentSegment();
      k = 0;
    }
    label168:
    for (;;)
    {
      m = k + 1;
      arrayOfChar1[k] = i;
      k = m;
      break;
      i = j;
      if (j > 39) {
        break label87;
      }
      if (j == 39)
      {
        _textBuffer.setCurrentLength(k);
        return JsonToken.VALUE_STRING;
      }
      i = j;
      if (j >= 32) {
        break label87;
      }
      _throwUnquotedSpace(j, "string value");
      i = j;
      break label87;
    }
  }
  
  protected JsonToken _handleInvalidNumberStart(int paramInt, boolean paramBoolean)
    throws IOException
  {
    double d = Double.NEGATIVE_INFINITY;
    int i = paramInt;
    Object localObject;
    if (paramInt == 73)
    {
      if ((_inputPtr >= _inputEnd) && (!loadMore())) {
        _reportInvalidEOFInValue();
      }
      localObject = _inputBuffer;
      paramInt = _inputPtr;
      _inputPtr = (paramInt + 1);
      paramInt = localObject[paramInt];
      if (paramInt != 78) {
        break label162;
      }
      if (paramBoolean)
      {
        localObject = "-INF";
        _matchToken((String)localObject, 3);
        if (!isEnabled(JsonParser.Feature.ALLOW_NON_NUMERIC_NUMBERS)) {
          break label117;
        }
        if (!paramBoolean) {
          break label110;
        }
      }
      for (;;)
      {
        return resetAsNaN((String)localObject, d);
        localObject = "+INF";
        break;
        label110:
        d = Double.POSITIVE_INFINITY;
      }
      label117:
      _reportError("Non-standard token '" + (String)localObject + "': enable JsonParser.Feature.ALLOW_NON_NUMERIC_NUMBERS to allow");
      i = paramInt;
    }
    for (;;)
    {
      reportUnexpectedNumberChar(i, "expected digit (0-9) to follow minus sign, for valid numeric value");
      return null;
      label162:
      i = paramInt;
      if (paramInt == 110)
      {
        if (paramBoolean)
        {
          localObject = "-Infinity";
          _matchToken((String)localObject, 3);
          if (!isEnabled(JsonParser.Feature.ALLOW_NON_NUMERIC_NUMBERS)) {
            break label224;
          }
          if (!paramBoolean) {
            break label217;
          }
        }
        for (;;)
        {
          return resetAsNaN((String)localObject, d);
          localObject = "+Infinity";
          break;
          label217:
          d = Double.POSITIVE_INFINITY;
        }
        label224:
        _reportError("Non-standard token '" + (String)localObject + "': enable JsonParser.Feature.ALLOW_NON_NUMERIC_NUMBERS to allow");
        i = paramInt;
      }
    }
  }
  
  protected String _handleOddName(int paramInt)
    throws IOException
  {
    if ((paramInt == 39) && (isEnabled(JsonParser.Feature.ALLOW_SINGLE_QUOTES))) {
      return _parseAposName();
    }
    if (!isEnabled(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES)) {
      _reportUnexpectedChar(paramInt, "was expecting double-quote to start field name");
    }
    int[] arrayOfInt = CharTypes.getInputCodeLatin1JsNames();
    int m = arrayOfInt.length;
    boolean bool;
    int k;
    int n;
    int j;
    if (paramInt < m) {
      if (arrayOfInt[paramInt] == 0)
      {
        bool = true;
        if (!bool) {
          _reportUnexpectedChar(paramInt, "was expecting either valid name character (for unquoted name) or double-quote (for quoted) to start field name");
        }
        k = _inputPtr;
        i = _hashSeed;
        n = _inputEnd;
        j = i;
        paramInt = k;
        if (k >= n) {
          break label239;
        }
        paramInt = k;
      }
    }
    label212:
    do
    {
      j = _inputBuffer[paramInt];
      if (j < m)
      {
        if (arrayOfInt[j] == 0) {
          break label212;
        }
        j = _inputPtr - 1;
        _inputPtr = paramInt;
        return _symbols.findSymbol(_inputBuffer, j, paramInt - j, i);
        bool = false;
        break;
        bool = Character.isJavaIdentifierPart((char)paramInt);
        break;
      }
      if (!Character.isJavaIdentifierPart((char)j))
      {
        j = _inputPtr - 1;
        _inputPtr = paramInt;
        return _symbols.findSymbol(_inputBuffer, j, paramInt - j, i);
      }
      j = i * 33 + j;
      k = paramInt + 1;
      i = j;
      paramInt = k;
    } while (k < n);
    paramInt = k;
    label239:
    int i = _inputPtr;
    _inputPtr = paramInt;
    return _handleOddName2(i - 1, j, arrayOfInt);
  }
  
  protected JsonToken _handleOddValue(int paramInt)
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
    char[] arrayOfChar = _inputBuffer;
    paramInt = _inputPtr;
    _inputPtr = (paramInt + 1);
    return _handleInvalidNumberStart(arrayOfChar[paramInt], false);
  }
  
  protected void _matchToken(String paramString, int paramInt)
    throws IOException
  {
    int j = paramString.length();
    int i;
    do
    {
      if ((_inputPtr >= _inputEnd) && (!loadMore())) {
        _reportInvalidToken(paramString.substring(0, paramInt));
      }
      if (_inputBuffer[_inputPtr] != paramString.charAt(paramInt)) {
        _reportInvalidToken(paramString.substring(0, paramInt));
      }
      _inputPtr += 1;
      i = paramInt + 1;
      paramInt = i;
    } while (i < j);
    if ((_inputPtr >= _inputEnd) && (!loadMore())) {}
    char c;
    do
    {
      return;
      c = _inputBuffer[_inputPtr];
    } while ((c < '0') || (c == ']') || (c == '}') || (!Character.isJavaIdentifierPart(c)));
    _reportInvalidToken(paramString.substring(0, i));
  }
  
  protected String _parseAposName()
    throws IOException
  {
    int m = _inputPtr;
    int k = _hashSeed;
    int n = _inputEnd;
    int i = k;
    int j = m;
    if (m < n)
    {
      int[] arrayOfInt = _icLatin1;
      int i1 = arrayOfInt.length;
      j = m;
      i = k;
      k = _inputBuffer[j];
      if (k == 39)
      {
        k = _inputPtr;
        _inputPtr = (j + 1);
        return _symbols.findSymbol(_inputBuffer, k, j - k, i);
      }
      if ((k >= i1) || (arrayOfInt[k] == 0)) {
        break label118;
      }
    }
    for (;;)
    {
      k = _inputPtr;
      _inputPtr = j;
      return _parseName2(k, i, 39);
      label118:
      m = i * 33 + k;
      k = j + 1;
      i = m;
      j = k;
      if (k < n) {
        break;
      }
      i = m;
      j = k;
    }
  }
  
  protected String _parseName(int paramInt)
    throws IOException
  {
    if (paramInt != 34) {
      return _handleOddName(paramInt);
    }
    paramInt = _inputPtr;
    int i = _hashSeed;
    int m = _inputEnd;
    int k = i;
    int j = paramInt;
    if (paramInt < m)
    {
      int[] arrayOfInt = _icLatin1;
      int n = arrayOfInt.length;
      do
      {
        int i1 = _inputBuffer[paramInt];
        if ((i1 < n) && (arrayOfInt[i1] != 0))
        {
          k = i;
          j = paramInt;
          if (i1 != 34) {
            break;
          }
          j = _inputPtr;
          _inputPtr = (paramInt + 1);
          return _symbols.findSymbol(_inputBuffer, j, paramInt - j, i);
        }
        k = i * 33 + i1;
        j = paramInt + 1;
        i = k;
        paramInt = j;
      } while (j < m);
    }
    paramInt = _inputPtr;
    _inputPtr = j;
    return _parseName2(paramInt, k, 34);
  }
  
  protected JsonToken _parseNumber(int paramInt)
    throws IOException
  {
    int k = 1;
    int n = 0;
    int i4 = 0;
    boolean bool;
    int i;
    int i3;
    int i5;
    if (paramInt == 45)
    {
      bool = true;
      i = _inputPtr;
      i3 = i - 1;
      i5 = _inputEnd;
      if (!bool) {
        break label123;
      }
      if (i < _inputEnd) {
        break label75;
      }
    }
    label57:
    label75:
    char[] arrayOfChar;
    int j;
    int m;
    for (;;)
    {
      if (bool)
      {
        paramInt = i3 + 1;
        _inputPtr = paramInt;
        return _parseNumber2(bool);
        bool = false;
        break;
        arrayOfChar = _inputBuffer;
        j = i + 1;
        m = arrayOfChar[i];
        if (m <= 57)
        {
          paramInt = j;
          i = m;
          if (m >= 48) {}
        }
        else
        {
          _inputPtr = j;
          return _handleInvalidNumberStart(m, true);
          label123:
          j = i;
          i = paramInt;
          paramInt = j;
        }
        if (i != 48)
        {
          i = paramInt;
          label137:
          if (i < _inputEnd)
          {
            arrayOfChar = _inputBuffer;
            paramInt = i + 1;
            i = arrayOfChar[i];
            if ((i < 48) || (i > 57))
            {
              if (i != 46) {
                break label463;
              }
              j = 0;
              i = paramInt;
              paramInt = j;
              label184:
              if (i >= i5) {
                break label362;
              }
              arrayOfChar = _inputBuffer;
              j = i + 1;
              i = arrayOfChar[i];
              if ((i >= 48) && (i <= 57)) {
                break label355;
              }
              if (paramInt == 0) {
                reportUnexpectedNumberChar(i, "Decimal point not followed by a digit");
              }
              m = paramInt;
              paramInt = j;
            }
          }
        }
      }
    }
    for (;;)
    {
      int i2;
      int i1;
      if (i != 101)
      {
        i2 = paramInt;
        i1 = i;
        if (i != 69) {}
      }
      else
      {
        if (paramInt >= i5) {
          break;
        }
        arrayOfChar = _inputBuffer;
        j = paramInt + 1;
        i = arrayOfChar[paramInt];
        if ((i != 45) && (i != 43)) {
          break label455;
        }
        if (j >= i5) {
          break;
        }
        arrayOfChar = _inputBuffer;
        paramInt = j + 1;
        i = arrayOfChar[j];
        j = i4;
      }
      for (;;)
      {
        if ((i <= 57) && (i >= 48))
        {
          j += 1;
          if (paramInt >= i5) {
            break;
          }
          i = _inputBuffer[paramInt];
          paramInt += 1;
          continue;
          k += 1;
          i = paramInt;
          break label137;
          break;
          label355:
          paramInt += 1;
          i = j;
          break label184;
          label362:
          break;
        }
        n = j;
        i2 = paramInt;
        i1 = i;
        if (j == 0)
        {
          reportUnexpectedNumberChar(i, "Exponent indicator not followed by a digit");
          i1 = i;
          i2 = paramInt;
          n = j;
        }
        paramInt = i2 - 1;
        _inputPtr = paramInt;
        if (_parsingContext.inRoot()) {
          _verifyRootSpace(i1);
        }
        _textBuffer.resetWithShared(_inputBuffer, i3, paramInt - i3);
        return reset(bool, k, m, n);
        paramInt = i3;
        break label57;
        label455:
        paramInt = j;
        j = i4;
      }
      label463:
      m = 0;
    }
  }
  
  protected int _readBinary(Base64Variant paramBase64Variant, OutputStream paramOutputStream, byte[] paramArrayOfByte)
    throws IOException, JsonParseException
  {
    int i1 = paramArrayOfByte.length;
    int j = 0;
    int i = 0;
    char[] arrayOfChar;
    int k;
    char c;
    int m;
    label91:
    do
    {
      do
      {
        if (_inputPtr >= _inputEnd) {
          loadMoreGuaranteed();
        }
        arrayOfChar = _inputBuffer;
        k = _inputPtr;
        _inputPtr = (k + 1);
        c = arrayOfChar[k];
      } while (c <= ' ');
      k = paramBase64Variant.decodeBase64Char(c);
      m = k;
      if (k >= 0) {
        break;
      }
      if (c == '"')
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
      m = _decodeBase64Escape(paramBase64Variant, c, 0);
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
      arrayOfChar = _inputBuffer;
      j = _inputPtr;
      _inputPtr = (j + 1);
      c = arrayOfChar[j];
      int n = paramBase64Variant.decodeBase64Char(c);
      j = n;
      if (n < 0) {
        j = _decodeBase64Escape(paramBase64Variant, c, 1);
      }
      int i2 = m << 6 | j;
      if (_inputPtr >= _inputEnd) {
        loadMoreGuaranteed();
      }
      arrayOfChar = _inputBuffer;
      j = _inputPtr;
      _inputPtr = (j + 1);
      c = arrayOfChar[j];
      m = paramBase64Variant.decodeBase64Char(c);
      n = m;
      if (m < 0)
      {
        j = m;
        if (m != -2)
        {
          if ((c == '"') && (!paramBase64Variant.usesPadding()))
          {
            j = k + 1;
            paramArrayOfByte[k] = ((byte)(i2 >> 4));
            k = j;
            break label91;
          }
          j = _decodeBase64Escape(paramBase64Variant, c, 2);
        }
        n = j;
        if (j == -2)
        {
          if (_inputPtr >= _inputEnd) {
            loadMoreGuaranteed();
          }
          arrayOfChar = _inputBuffer;
          j = _inputPtr;
          _inputPtr = (j + 1);
          c = arrayOfChar[j];
          if (!paramBase64Variant.usesPaddingChar(c)) {
            throw reportInvalidBase64Char(paramBase64Variant, c, 3, "expected padding character '" + paramBase64Variant.getPaddingChar() + "'");
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
      arrayOfChar = _inputBuffer;
      j = _inputPtr;
      _inputPtr = (j + 1);
      c = arrayOfChar[j];
      m = paramBase64Variant.decodeBase64Char(c);
      n = m;
      if (m < 0)
      {
        j = m;
        if (m != -2)
        {
          if ((c == '"') && (!paramBase64Variant.usesPadding()))
          {
            j = i2 >> 2;
            m = k + 1;
            paramArrayOfByte[k] = ((byte)(j >> 8));
            k = m + 1;
            paramArrayOfByte[m] = ((byte)j);
            break label91;
          }
          j = _decodeBase64Escape(paramBase64Variant, c, 3);
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
    char[] arrayOfChar = _inputBuffer;
    if (arrayOfChar != null)
    {
      _inputBuffer = null;
      _ioContext.releaseTokenBuffer(arrayOfChar);
    }
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
        c = _inputBuffer[_inputPtr];
      } while (!Character.isJavaIdentifierPart(c));
      _inputPtr += 1;
      paramString1.append(c);
    }
  }
  
  protected void _skipCR()
    throws IOException
  {
    if (((_inputPtr < _inputEnd) || (loadMore())) && (_inputBuffer[_inputPtr] == '\n')) {
      _inputPtr += 1;
    }
    _currInputRow += 1;
    _currInputRowStart = _inputPtr;
  }
  
  protected void _skipString()
    throws IOException
  {
    _tokenIncomplete = false;
    int i = _inputPtr;
    int j = _inputEnd;
    char[] arrayOfChar = _inputBuffer;
    for (;;)
    {
      int k = j;
      int m = i;
      if (i >= j)
      {
        _inputPtr = i;
        if (!loadMore()) {
          _reportInvalidEOF(": was expecting closing quote for a string value");
        }
        m = _inputPtr;
        k = _inputEnd;
      }
      i = m + 1;
      j = arrayOfChar[m];
      if (j <= 92)
      {
        if (j == 92)
        {
          _inputPtr = i;
          _decodeEscaped();
          i = _inputPtr;
          j = _inputEnd;
          continue;
        }
        if (j <= 34)
        {
          if (j == 34)
          {
            _inputPtr = i;
            return;
          }
          if (j < 32)
          {
            _inputPtr = i;
            _throwUnquotedSpace(j, "string value");
          }
        }
      }
      j = k;
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
  
  public Object getInputSource()
  {
    return _reader;
  }
  
  protected char getNextChar(String paramString)
    throws IOException, JsonParseException
  {
    if ((_inputPtr >= _inputEnd) && (!loadMore())) {
      _reportInvalidEOF(paramString);
    }
    paramString = _inputBuffer;
    int i = _inputPtr;
    _inputPtr = (i + 1);
    return paramString[i];
  }
  
  public String getText()
    throws IOException, JsonParseException
  {
    JsonToken localJsonToken = _currToken;
    if (localJsonToken == JsonToken.VALUE_STRING)
    {
      if (_tokenIncomplete)
      {
        _tokenIncomplete = false;
        _finishString();
      }
      return _textBuffer.contentsAsString();
    }
    return _getText2(localJsonToken);
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
  
  protected boolean loadMore()
    throws IOException
  {
    boolean bool2 = false;
    _currInputProcessed += _inputEnd;
    _currInputRowStart -= _inputEnd;
    boolean bool1 = bool2;
    int i;
    if (_reader != null)
    {
      i = _reader.read(_inputBuffer, 0, _inputBuffer.length);
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
    throw new IOException("Reader returned 0 characters when trying to read " + _inputEnd);
  }
  
  public Boolean nextBooleanValue()
    throws IOException, JsonParseException
  {
    Object localObject2 = null;
    JsonToken localJsonToken;
    Object localObject1;
    if (_currToken == JsonToken.FIELD_NAME)
    {
      _nameCopied = false;
      localJsonToken = _nextToken;
      _nextToken = null;
      _currToken = localJsonToken;
      if (localJsonToken == JsonToken.VALUE_TRUE) {
        localObject1 = Boolean.TRUE;
      }
    }
    int i;
    do
    {
      do
      {
        do
        {
          return (Boolean)localObject1;
          if (localJsonToken == JsonToken.VALUE_FALSE) {
            return Boolean.FALSE;
          }
          if (localJsonToken == JsonToken.START_ARRAY)
          {
            _parsingContext = _parsingContext.createChildArrayContext(_tokenInputRow, _tokenInputCol);
            return null;
          }
          localObject1 = localObject2;
        } while (localJsonToken != JsonToken.START_OBJECT);
        _parsingContext = _parsingContext.createChildObjectContext(_tokenInputRow, _tokenInputCol);
        return null;
        localJsonToken = nextToken();
        localObject1 = localObject2;
      } while (localJsonToken == null);
      i = localJsonToken.id();
      if (i == 9) {
        return Boolean.TRUE;
      }
      localObject1 = localObject2;
    } while (i != 10);
    return Boolean.FALSE;
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
    Object localObject;
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
    boolean bool = _parsingContext.inObject();
    j = i;
    if (bool)
    {
      localObject = _parseName(i);
      _parsingContext.setCurrentName((String)localObject);
      _currToken = JsonToken.FIELD_NAME;
      i = _skipWS();
      if (i != 58) {
        _reportUnexpectedChar(i, "was expecting a colon to separate field name and value");
      }
      j = _skipWS();
    }
    switch (j)
    {
    default: 
      localObject = _handleOddValue(j);
    }
    while (bool)
    {
      _nextToken = ((JsonToken)localObject);
      return _currToken;
      _tokenIncomplete = true;
      localObject = JsonToken.VALUE_STRING;
      continue;
      if (!bool) {
        _parsingContext = _parsingContext.createChildArrayContext(_tokenInputRow, _tokenInputCol);
      }
      localObject = JsonToken.START_ARRAY;
      continue;
      if (!bool) {
        _parsingContext = _parsingContext.createChildObjectContext(_tokenInputRow, _tokenInputCol);
      }
      localObject = JsonToken.START_OBJECT;
      continue;
      _reportUnexpectedChar(j, "expected a value");
      _matchToken("true", 1);
      localObject = JsonToken.VALUE_TRUE;
      continue;
      _matchToken("false", 1);
      localObject = JsonToken.VALUE_FALSE;
      continue;
      _matchToken("null", 1);
      localObject = JsonToken.VALUE_NULL;
      continue;
      localObject = _parseNumber(j);
    }
    _currToken = ((JsonToken)localObject);
    return (JsonToken)localObject;
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
  
  public int releaseBuffered(Writer paramWriter)
    throws IOException
  {
    int i = _inputEnd - _inputPtr;
    if (i < 1) {
      return 0;
    }
    int j = _inputPtr;
    paramWriter.write(_inputBuffer, j, i);
    return i;
  }
  
  public void setCodec(ObjectCodec paramObjectCodec)
  {
    _objectCodec = paramObjectCodec;
  }
}

/* Location:
 * Qualified Name:     com.fasterxml.jackson.core.json.ReaderBasedJsonParser
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */